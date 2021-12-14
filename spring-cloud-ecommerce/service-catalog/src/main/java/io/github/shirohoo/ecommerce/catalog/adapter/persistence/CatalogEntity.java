package io.github.shirohoo.ecommerce.catalog.adapter.persistence;

import io.github.shirohoo.ecommerce.catalog.domain.Catalog;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Table(name = "CATALOG")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CatalogEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Integer unitPrice;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Catalog toCatalog() {
        return Catalog.builder()
            .productId(productId)
            .productName(productName)
            .stock(stock)
            .unitPrice(unitPrice)
            .createdAt(createdAt)
            .build();
    }

}
