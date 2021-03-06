package com.example.order.feign;

import com.example.common.product.entity.Product;
import com.example.order.config.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @desc:
 * @author: cao_wencao
 * @date: 2020-09-22 23:43
 */
@FeignClient(value = "product-service",configuration = FeignRequestInterceptor.class)
public interface ProductService {
    //@FeignClient的value +  @RequestMapping的value值  其实就是完成的请求地址  "http://product-service/product/" + pid
    //指定请求的URI部分
    @RequestMapping("/product/product/{pid}")
    Product findByPid(@PathVariable Integer pid);

    //扣减库存,模拟全局事务提交
    //参数一: 商品标识
    //参数二:扣减数量
    @RequestMapping("/product/reduceInventory/commit")
    void reduceInventoryCommit(@RequestParam("pid") Integer pid,
                               @RequestParam("number") Integer number);

    //扣减库存,模拟全局事务回滚
    //参数一: 商品标识
    //参数二:扣减数量
    @RequestMapping("/product/reduceInventory/rollback")
    void reduceInventoryRollback(@RequestParam("pid") Integer pid,
                         @RequestParam("number") Integer number);


}