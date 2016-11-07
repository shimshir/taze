package de.admir.taze.config;

import de.admir.taze.Constants;
import de.admir.taze.model.Customer;
import de.admir.taze.model.order.Order;
import de.admir.taze.model.order.OrderEntry;
import de.admir.taze.model.order.OrderStatus;
import de.admir.taze.model.product.Product;
import de.admir.taze.model.Session;
import de.admir.taze.model.product.ProductCard;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
@EnableJpaRepositories(basePackages = "de.admir.taze.repository")
public class RestRepositoryConfiguration extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setBasePath(Constants.API_REST_CONTEXT_PATH);
        config.exposeIdsFor(Product.class, Session.class, ProductCard.class, Customer.class, Order.class, OrderEntry.class, OrderStatus.class);
    }
}
