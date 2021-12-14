package io.github.shirohoo.ecommerce.catalog.adapter.persistence;

import io.github.shirohoo.ecommerce.catalog.domain.Catalog;
import java.util.List;

public interface CatalogCrudRepository {

    List<Catalog> findAll();

}
