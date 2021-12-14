package io.github.shirohoo.ecommerce.catalog.adapter.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.github.shirohoo.ecommerce.catalog.domain.Catalog;
import io.github.shirohoo.ecommerce.catalog.serivce.CatalogService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalogs/")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping
    public ResponseEntity<List<ResponseCatalog>> findAll() {
        return ResponseEntity.ok(
            catalogService.findAll()
                .stream()
                .map(ResponseCatalog::convert)
                .collect(Collectors.toUnmodifiableList())
        );
    }

    @JsonInclude(Include.NON_NULL)
    @Value(staticConstructor = "of")
    public static class ResponseCatalog {

        String productId;
        String productName;
        Integer unitPrice;
        Integer stock;
        LocalDateTime createdAt;

        @Builder
        private ResponseCatalog(String productId, String productName, Integer unitPrice, Integer stock, LocalDateTime createdAt) {
            this.productId = productId;
            this.productName = productName;
            this.unitPrice = unitPrice;
            this.stock = stock;
            this.createdAt = createdAt;
        }

        public static ResponseCatalog convert(Catalog catalog) {
            return ResponseCatalog.builder()
                .productId(catalog.getProductId())
                .productName(catalog.getProductName())
                .unitPrice(catalog.getUnitPrice())
                .stock(catalog.getStock())
                .createdAt(catalog.getCreatedAt())
                .build();
        }

    }

}
