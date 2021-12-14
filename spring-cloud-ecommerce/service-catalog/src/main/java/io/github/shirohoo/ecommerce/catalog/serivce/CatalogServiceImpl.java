package io.github.shirohoo.ecommerce.catalog.serivce;

import io.github.shirohoo.ecommerce.catalog.adapter.persistence.CatalogCrudRepository;
import io.github.shirohoo.ecommerce.catalog.domain.Catalog;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CatalogCrudRepository catalogCrudRepository;

    @Override
    public List<Catalog> findAllCatalog() {
        return catalogCrudRepository.findAll();
    }

}
