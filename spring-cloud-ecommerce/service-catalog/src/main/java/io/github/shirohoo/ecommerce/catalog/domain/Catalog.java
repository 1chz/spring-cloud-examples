package io.github.shirohoo.ecommerce.catalog.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Catalog implements Serializable {

    private String productId;
    private String productName;
    private Integer unitPrice;
    private Integer stock;
    private LocalDateTime createdAt;

    @Builder
    private Catalog(String productId, String productName, Integer unitPrice, Integer stock, LocalDateTime createdAt) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.createdAt = createdAt;
    }

}
