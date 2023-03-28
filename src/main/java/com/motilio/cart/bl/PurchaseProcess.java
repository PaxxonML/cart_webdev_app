package com.motilio.cart.bl;

import java.math.BigDecimal;

import com.motilio.cart.domain.Customer;

public interface PurchaseProcess {
    
    public void AddItemToCart(Integer itemId, BigDecimal quantity);

    public void RemoveItemFromCart(Integer itemId, BigDecimal quantity);

    public void Checkout();

    public Customer setCustomer(int customerId);

    public Customer getCustomer();

}
