package com.istep.market.subscribe;

/**
 * PriceListener interface is created for future, when application will be listening market prices from external source.
 * For example after adding some message system like RabbitMQ the method onMessage(String message) should be implemented.
 */
public interface PriceListener {
    void onMessage(String message);
}
