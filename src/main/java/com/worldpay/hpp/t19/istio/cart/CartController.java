package com.worldpay.hpp.t19.istio.cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CartController {

    Logger logger = LoggerFactory.getLogger(CartController.class);

    @RequestMapping("/cart/{itemId}")
    public UUID getItem(@PathVariable int itemId){
        logger.info("Add item in the cart");

        if(itemId == 99){
            logger.info("item {} not found!", itemId);
            throw new RuntimeException("item not found!");
        }

        return UUID.randomUUID();
    }
}
