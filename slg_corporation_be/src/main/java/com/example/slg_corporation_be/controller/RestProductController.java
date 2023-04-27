package com.example.slg_corporation_be.controller;

import com.example.slg_corporation_be.dto.IProductDTO;
import com.example.slg_corporation_be.model.Image;
import com.example.slg_corporation_be.model.Product;
import com.example.slg_corporation_be.model.ProductType;
import com.example.slg_corporation_be.service.IProductTypeService;
import com.example.slg_corporation_be.service.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user/product")
public class RestProductController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IProductTypeService iProductTypeService;
    @GetMapping("/productType")
    public ResponseEntity<List<ProductType>>getAllImage(){
        List<ProductType>imageList = this.iProductTypeService.getAll();
        return new ResponseEntity<>(imageList,HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<Page<?>>getAllPageProduct(@RequestParam(defaultValue = "") String name,
                                                    @RequestParam(required = false, defaultValue = "0") int page,
                                                    @RequestParam(required = false, defaultValue = "3") int pageSize){

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> productPage = this.iProductService.getAllPageProduct(name,pageable);
        Page<IProductDTO> productDTOPage ;
        if (productPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            List<IProductDTO> productDTOList = new ArrayList<>();
            for (Product product:productPage.getContent()){
                IProductDTO productDTO = new IProductDTO();
                BeanUtils.copyProperties(product,productDTO);
                productDTO.setImageList(new ArrayList<>(product.getImageSet()));
                productDTOList.add(productDTO);
            }
            productDTOPage = new PageImpl<>(productDTOList,productPage.getPageable(),productPage.getTotalElements());


            return new ResponseEntity<>(productDTOPage,HttpStatus.OK);
        }
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<?>findProductById( @PathVariable long id){
        Product product = this.iProductService.findProductDTOById(id);
        if (product == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            IProductDTO productDTO = new IProductDTO();
            BeanUtils.copyProperties(product,productDTO);
            productDTO.setImageList(new ArrayList<>(product.getImageSet()));
            return new ResponseEntity<>(productDTO,HttpStatus.OK);
        }
    }
}
