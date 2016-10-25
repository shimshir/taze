package de.admir.config;

import de.admir.Constants;
import de.admir.model.Customer;
import de.admir.model.order.Order;
import de.admir.model.order.OrderEntry;
import de.admir.model.product.Product;
import de.admir.model.Session;
import de.admir.model.product.ProductCard;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
@EnableJpaRepositories(basePackages = "de.admir.repository")
public class RestMvcConfiguration extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setBasePath(Constants.API_REST_BASE_PATH);
        config.exposeIdsFor(Product.class, Session.class, ProductCard.class, Customer.class, Order.class, OrderEntry.class);
    }
}
