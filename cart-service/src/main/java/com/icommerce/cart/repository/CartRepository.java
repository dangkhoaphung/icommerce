package com.icommerce.cart.repository;

import com.icommerce.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created on 6/14/20
 *
 * @author dangkhoa.phung
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query("select c from Cart c where c.accountId=?1")
    Cart findByAccountId(int accountId);
}
