package com.example.slg_corporation_be.controller;

import com.example.slg_corporation_be.dto.IOrderDetailDTO;
import com.example.slg_corporation_be.dto.OrderDetailDTO;
import com.example.slg_corporation_be.model.OrderDetail;
import com.example.slg_corporation_be.model.Orders;
import com.example.slg_corporation_be.model.Product;
import com.example.slg_corporation_be.service.IOrderDetailService;
import com.example.slg_corporation_be.service.IOrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/user/cart")
public class RestCartController {
    @Autowired
    private IOrderDetailService iOrderDetailService;
    @Autowired
    private IOrdersService iOrdersService;

    @GetMapping("/list/{email}")
    public ResponseEntity<?> getCart(@PathVariable String email) {
        List<IOrderDetailDTO> cart = this.iOrderDetailService.getCartByUser(email);
        if (cart.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<OrderDetailDTO> cartDTO = new ArrayList<>();

        List<Product> productList = this.iOrderDetailService.findProductsByEmail(email);

        for (IOrderDetailDTO item : cart) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            BeanUtils.copyProperties(item, orderDetailDTO);
            for (Product product : productList) {
                if (product.getId() == item.getId()) {
                    orderDetailDTO.setImageList(new ArrayList<>(product.getImageSet()));
                }
            }
            cartDTO.add(orderDetailDTO);
        }

        return new ResponseEntity<>(cartDTO, HttpStatus.OK);

    }

    @PostMapping("/addCart")
    public ResponseEntity<?> addCart(@RequestParam long idProduct, @RequestParam String email, @RequestParam int amount) {
        this.iOrdersService.addOrders(idProduct, email, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
