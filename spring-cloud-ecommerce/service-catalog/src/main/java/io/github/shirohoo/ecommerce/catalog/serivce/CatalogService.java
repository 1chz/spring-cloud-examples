package io.github.shirohoo.ecommerce.catalog.serivce;

import io.github.shirohoo.ecommerce.catalog.domain.Catalog;
import java.util.List;

public interface CatalogService {

    List<Catalog> findAllCatalog();

}
