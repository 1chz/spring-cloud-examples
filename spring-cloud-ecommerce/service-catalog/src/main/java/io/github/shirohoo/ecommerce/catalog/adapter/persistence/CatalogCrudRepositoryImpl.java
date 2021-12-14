package io.github.shirohoo.ecommerce.catalog.adapter.persistence;

import io.github.shirohoo.ecommerce.catalog.domain.Catalog;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CatalogCrudRepositoryImpl implements CatalogCrudRepository {

    private final CatalogJpaRepository jpaRepository;

    @Override
    public List<Catalog> findAll() {
        return jpaRepository.findAll()
            .stream()
            .map(CatalogEntity::toCatalog)
            .collect(Collectors.toUnmodifiableList());
    }

}
