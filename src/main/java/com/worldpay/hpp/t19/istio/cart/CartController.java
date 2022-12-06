package com.worldpay.hpp.t19.istio.cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CartController {

    Logger logger = LoggerFactory.getLogger(CartController.class);

    @RequestMapping("/cart/{itemId}")
    public UUID getItem(){
        logger.info("Add item in the cart");

        return UUID.randomUUID();
    }
}
