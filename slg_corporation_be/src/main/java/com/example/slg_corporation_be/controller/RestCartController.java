package com.example.slg_corporation_be.controller;

import com.example.slg_corporation_be.dto.IOrderDetailDTO;
import com.example.slg_corporation_be.dto.OrderDetailDTO;
import com.example.slg_corporation_be.model.Account;
import com.example.slg_corporation_be.model.OrderDetail;
import com.example.slg_corporation_be.model.Orders;
import com.example.slg_corporation_be.model.Product;
import com.example.slg_corporation_be.security_authentication.jwt.JwtUtility;
import com.example.slg_corporation_be.service.IAccountService;
import com.example.slg_corporation_be.service.IOrderDetailService;
import com.example.slg_corporation_be.service.IOrdersService;
import com.example.slg_corporation_be.service.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.example.slg_corporation_be.security_authentication.jwt.JwtFilter.parseJwt;

@RestController
@CrossOrigin("*")
@RequestMapping("api/user/cart")
public class RestCartController {
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IOrderDetailService iOrderDetailService;
    @Autowired
    private IOrdersService iOrdersService;
    @Autowired
    private IProductService iProductService;


    @GetMapping("/list/{email}")
    public ResponseEntity<?> getCart(@PathVariable String email) {
        List<IOrderDetailDTO> cart = this.iOrderDetailService.getCartByUser(email);
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

    @PostMapping("/buyNowCart")
    public ResponseEntity<?> addCart(@RequestParam long idProduct, @RequestParam String email) {
        this.iOrdersService.buyNowOrders(idProduct, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateCart")
    public ResponseEntity<?> updateCart(@RequestParam long id, @RequestParam int amount, @RequestParam String email) {
        this.iOrderDetailService.updateOrDetail(id, amount, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteCart")
    public ResponseEntity<?> deleteCart(@RequestParam long idProduct, @RequestParam String email) {
        this.iOrderDetailService.remove(idProduct, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/payCart")
    public ResponseEntity<?> payCart(HttpServletRequest request) {
        Set<String> listOrderDetailError = new HashSet<>();
        String jwt = parseJwt(request);
        String email = jwtUtility.getUserNameFromJwtToken(jwt);
        Account account = iAccountService.findAccountByAppUserEmail(email);
        long id = account.getAppUser().getId();
        List<Product> productList = this.iProductService.getAllProduct();
        List<OrderDetail> orderDetails = this.iOrderDetailService.findOrderDetailByEmail(email);
        int total = 0;
        for (OrderDetail odd : orderDetails) {
            total += odd.getAmount()*odd.getProduct().getPrice();
            for (Product p : productList) {
                if (odd.getProduct().getId() == p.getId()) {
                    if (odd.getAmount() <= p.getQuantity()) {
                        p.setQuantity(p.getQuantity() - odd.getAmount());
                    } else {
                        listOrderDetailError.add("Sản phẩm " + p.getName() + " còn có trong kho là :" + p.getQuantity() + " sản phẩm.");
                    }
                }
            }

        }
        if (listOrderDetailError.isEmpty()) {
            for (OrderDetail odd : orderDetails) {
                odd.setDelete(true);
                this.iOrderDetailService.payCart(odd);
            }
            Orders orders = this.iOrdersService.findOrderById(email);
            orders.setStatusPayment(true);
            orders.setTotalPrice(String.valueOf(total));
            this.iOrdersService.updateOrder(orders);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(listOrderDetailError, HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/historyOrder")
    public ResponseEntity<?>getHistoryOrder( HttpServletRequest request){
        String jwt = parseJwt(request);
        String email = jwtUtility.getUserNameFromJwtToken(jwt);
        List<Orders> ordersList = this.iOrdersService.getHistoryListOrder(email);
        if (ordersList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ordersList,HttpStatus.OK);
    }
    @GetMapping("/historyOrderDetail")
    public ResponseEntity<?>getHistoryOrderDetail( HttpServletRequest request,@RequestParam long idOrder){
        String jwt = parseJwt(request);
        String email = jwtUtility.getUserNameFromJwtToken(jwt);
        List<OrderDetail> orderDetailList = this.iOrderDetailService.getHistoryOrderDetail(email,idOrder);;
        if (orderDetailList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderDetailList,HttpStatus.OK);
    }
}
