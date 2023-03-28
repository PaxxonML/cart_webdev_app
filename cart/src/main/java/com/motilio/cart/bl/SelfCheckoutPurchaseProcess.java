package com.motilio.cart.bl;

import java.math.BigDecimal;

import com.motilio.cart.domain.Customer;

public class SelfCheckoutPurchaseProcess implements PurchaseProcess {

    @Override
    public void AddItemToCart(Integer itemId, BigDecimal quantity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'AddItemToCart'");
    }

    @Override
    public void RemoveItemFromCart(Integer itemId, BigDecimal quantity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'RemoveItemFromCart'");
    }

    @Override
    public void Checkout() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Checkout'");
    }

    @Override
    public Customer setCustomer(int customerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCustomer'");
    }

    @Override
    public Customer getCustomer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCustomer'");
    }
    
}
