package com.example.slg_corporation_be.controller;

import com.example.slg_corporation_be.dto.IProductDTO;
import com.example.slg_corporation_be.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user/product")
public class RestProductController {
    @Autowired
    private IProductService iProductService;
    @GetMapping("/list")
    public ResponseEntity<Page<IProductDTO>>getAllPageProduct(@RequestParam(defaultValue = "") String name,
                                                              @RequestParam(defaultValue = "0") long origin,
                                                              @PageableDefault(page = 0,size = 3)Pageable pageable){

        Page<IProductDTO> productPage = this.iProductService.getAllPageProduct(name,origin,pageable);
        if (productPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(productPage,HttpStatus.OK);
        }
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<IProductDTO>findProductById( @PathVariable long id){
        IProductDTO iProductDTO = this.iProductService.findProductById(id);
        if (iProductDTO == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(iProductDTO,HttpStatus.OK);
        }

    }
}
