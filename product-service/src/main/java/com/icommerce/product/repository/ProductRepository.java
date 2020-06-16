package com.icommerce.product.repository;

import com.icommerce.common.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 6/14/20
 *
 * @author dangkhoa.phung
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
