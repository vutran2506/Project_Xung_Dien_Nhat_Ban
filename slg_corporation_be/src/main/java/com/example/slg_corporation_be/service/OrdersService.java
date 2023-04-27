//package com.example.slg_corporation_be.service;
//
//import com.example.slg_corporation_be.model.OrderDetail;
//import com.example.slg_corporation_be.model.Orders;
//import com.example.slg_corporation_be.model.Product;
//import com.example.slg_corporation_be.repository.IOrdersRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//public class OrdersService implements IOrdersService {
//    @Autowired
//    private IOrdersRepository iOrdersRepository;
//    @Autowired
//    private IProductService iProductService;
//
//    @Override
//    public Orders addOrdersFirst(long productId, long orderId, int amount) {
//        Orders orders = new Orders();
//        OrderDetail oderDetail = new OrderDetail();
//        oderDetail.setAmount(oderDetail.getAmount());
//        oderDetail.setDayPurchase(String.valueOf(new Date()));
//        oderDetail.setProduct(iProductService.findProductById(productId));
//        orders.getOrderDetailSet().add(oderDetail);
//        return  this.iOrdersRepository.save(orders);
//
//    }
//
//    @Override
//    public Orders addOrdersExist(long productId, long orderId, int amount) {
//        Orders orders = iOrdersRepository.findByCodeOrderContaining(orderId);
//        Product p =  iProductService.findProductById(productId);
//        for (OrderDetail odd : orders.getOrderDetailSet()) {
//            if (p.getId()== odd.getProduct().getId()){
//                odd.setAmount(odd.getAmount()+amount);
//                return this.iOrdersRepository.save(orders);
//            }
//        }
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setDayPurchase(String.valueOf(new Date()));
//        orderDetail.setAmount(amount);
//        orderDetail.setProduct(p);
//        orders.getOrderDetailSet().add(orderDetail);
//        return this.iOrdersRepository.save(orders);
//    }
//}
