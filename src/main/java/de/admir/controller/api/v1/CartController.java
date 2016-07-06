package de.admir.controller.api.v1;

import de.admir.facade.cart.CartFacade;
import de.admir.model.cart.CartDto;
import de.admir.model.cart.CartEntryDto;
import de.admir.model.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static de.admir.Constants.API_V1_BASE_PATH;

@RestController
@RequestMapping(path = API_V1_BASE_PATH)
public class CartController {
    private static final Logger LOG = LoggerFactory.getLogger(CartController.class);
    private static CartDto cart = new CartDto();
    static {
        cart.setId(UUID.randomUUID());
    }

    @RequestMapping(path = "/cart/{sessionId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CartDto> getCartBySessionId(@PathVariable String sessionId) {
        return ResponseEntity.ok(CartFacade.getCartBySession(new Session(sessionId)));
    }

    @RequestMapping(path = "/cart/{cartId}/entries", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<CartEntryDto> addCartEntry(@RequestBody CartEntryDto entry, @PathVariable String cartId) {
        CartFacade.addToCart(cartId, entry);
        return ResponseEntity.ok(entry);
    }
}
