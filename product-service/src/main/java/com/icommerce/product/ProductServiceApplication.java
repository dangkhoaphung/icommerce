package com.icommerce.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created on 6/13/20
 *
 * @author dangkhoa.phung
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.icommerce.product.repository" })
@EntityScan(basePackages = { "com.icommerce.common.entity"})
public class ProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}
