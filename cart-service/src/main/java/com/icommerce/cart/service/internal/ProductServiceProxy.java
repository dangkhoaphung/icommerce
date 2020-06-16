package com.icommerce.cart.service.internal;

import com.icommerce.common.entity.Product;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created on 6/13/20
 *
 * @author dangkhoa.phung
 */
@FeignClient(name="product-service", path = "/product")
public interface ProductServiceProxy {
    @Cacheable("productDetail")
    @RequestMapping(value = "/internal/get", method = RequestMethod.GET, produces = "application/json")
    Product getDetail(@RequestParam("productId") int productId);
}
