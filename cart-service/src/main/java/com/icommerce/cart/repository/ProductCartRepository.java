package com.icommerce.cart.repository;

import com.icommerce.cart.entity.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 6/14/20
 *
 * @author dangkhoa.phung
 */
@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart, Integer> {
    @Query("select c from ProductCart c where c.cartId=?1 and c.productId=?2")
    ProductCart findByCartIdAndProductId(int cartId, int productId);

    @Query("select c from ProductCart c where c.cartId=?1")
    List<ProductCart> findByCartId(int cartId);
}
