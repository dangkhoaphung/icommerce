package com.icommerce.cart.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Created on 6/14/20
 *
 * @author dangkhoa.phung
 */
@Entity
@Table(name = "cart")
@Data
public class Cart {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "accountId")
    private int accountId;

    @Column(name = "updatedDate")
    private Date updatedDate;
}
