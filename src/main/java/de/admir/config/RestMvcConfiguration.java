package de.admir.config;

import de.admir.Constants;
import de.admir.model.Customer;
import de.admir.model.order.Order;
import de.admir.model.order.OrderEntry;
import de.admir.model.product.Product;
import de.admir.model.Session;
import de.admir.model.product.ProductCard;
import de.admir.validation.OrderStatusValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
@EnableJpaRepositories(basePackages = "de.admir.repository")
public class RestMvcConfiguration extends RepositoryRestMvcConfiguration {
    @Autowired
    private OrderStatusValidator orderStatusValidator;

    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setBaseUri(Constants.API_REST_BASE_PATH);
        config.exposeIdsFor(Product.class, Session.class, ProductCard.class, Customer.class, Order.class, OrderEntry.class);
    }

    @Override
    protected void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener v) {
        v.addValidator("beforeSave", orderStatusValidator);
        v.addValidator("beforeCreate", orderStatusValidator);
    }
}
