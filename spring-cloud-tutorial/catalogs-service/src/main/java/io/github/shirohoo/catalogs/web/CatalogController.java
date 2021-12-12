package io.github.shirohoo.catalogs.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    @GetMapping("/")
    public String index() {
        return "This is Catalogs Service";
    }
    
}
