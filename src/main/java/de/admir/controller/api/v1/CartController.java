package de.admir.controller.api.v1;

import de.admir.facade.CartFacade;
import de.admir.model.cart.CartDto;
import de.admir.model.cart.CartEntryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static de.admir.Constants.API_V1_BASE_PATH;

@RestController
@RequestMapping(path = API_V1_BASE_PATH)
public class CartController {
    private static final Logger LOG = LoggerFactory.getLogger(CartController.class);

    @RequestMapping(path = "/cart", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CartDto> getCartBySessionId(@RequestParam String sessionId) {
        return ResponseEntity.ok(CartFacade.getCartBySessionId(sessionId));
    }

    @RequestMapping(path = "/cart/{cartId}/entries", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<CartEntryDto> addCartEntry(@RequestBody CartEntryDto entry, @PathVariable String cartId) {
        CartEntryDto persistedEntry = CartFacade.addToCart(cartId, entry);
        return ResponseEntity.ok(persistedEntry);
    }

    @RequestMapping(path = "/entries/{entryId}", method = RequestMethod.DELETE)
    public ResponseEntity removeCartEntry(@PathVariable String entryId) {
        CartFacade.removeFromCart(entryId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/cart/{cartId}/entries/{entryId}", method = RequestMethod.PUT)
    public ResponseEntity updateCartEntry(@PathVariable String cartId, @PathVariable String entryId, @RequestBody CartEntryDto entry) {
        CartFacade.updateCartEntry(cartId, entryId, entry);
        return ResponseEntity.ok().build();
    }
}
