//package com.example.slg_corporation_be.controller;
//import com.example.slg_corporation_be.model.OrderDetail;
//import com.example.slg_corporation_be.service.IOrdersService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/user")
//@CrossOrigin("*")
//public class RestOrderController {
//    @Autowired
//    private IOrdersService iOrdersService;
//
//    @PostMapping("/addToCart")
//    public ResponseEntity<?> addToCart( @RequestBody OrderDetail orderDetail) {
//        if (orderDetail.getOrders().getId()==0) {
//            this.iOrdersService.addOrdersFirst();
//        }else {
//            this.iOrdersService.addOrdersExist();
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//}