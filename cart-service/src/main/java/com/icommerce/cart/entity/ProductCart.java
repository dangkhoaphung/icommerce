package com.icommerce.cart.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created on 6/14/20
 *
 * @author dangkhoa.phung
 */
@Entity
@Table(name = "product_cart")
@Data
public class ProductCart {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "productId")
    private int productId;

    @Column(name = "cartId")
    private int cartId;

    @Column(name = "quantity")
    private int quantity;
}
