package com.motilio.cart.bl;

import java.math.BigDecimal;

import com.motilio.cart.dl.*;
import com.motilio.cart.domain.*;

public class BasicPurchaseProcess implements PurchaseProcess {
 
    // Repositories
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;

    private Customer customer;
    private ShoppingCart shoppingCart;

    public BasicPurchaseProcess() {
        this.customerRepository = new CustomerDatabase();
        this.itemRepository = new ItemDatabase();
        this.shoppingCart = new ShoppingCart();
    }

    @Override
    public void AddItemToCart(Integer itemId, BigDecimal quantity) {
        var item = itemRepository.findItem(itemId);
        this.shoppingCart.AddItemToCart(item, quantity);
    }

    @Override
    public void RemoveItemFromCart(Integer itemId, BigDecimal quantity) {
        if (quantity.compareTo(BigDecimal.ZERO) == 0) {
            return;
        } 
        
        var currentQuantity = this.shoppingCart.GetItemQuantity(itemRepository.findItem(itemId));
        if (currentQuantity.compareTo(quantity) <= 0) {
            this.shoppingCart.RemoveItemFromCart(itemRepository.findItem(itemId));
        } else if (currentQuantity.compareTo(quantity) > 0) {
            this.shoppingCart.UpdateItemInCart(itemRepository.findItem(itemId), currentQuantity.subtract(quantity));
        } 
    }

    @Override
    public String[] Checkout() {
        System.out.println("Checking out...");
        System.out.println("Total: " + this.shoppingCart.getTotal());
        int totalDefinedItems = 4;
        String[] output = new String[totalDefinedItems+1];
        String product;
        BigDecimal price, quantity;
        for (int i = 1; i <= totalDefinedItems; i++) {
            quantity = this.shoppingCart.GetItemQuantity(i);
            if (quantity.compareTo(BigDecimal.ZERO) > 0) {
                product = itemRepository.findItem(i).name();
                price = itemRepository.findItem(i).price();
                output[i-1] = product + "," + price + "," + quantity + "," + price.multiply(quantity);
            }
        }
        output[output.length-1] = String.valueOf(this.shoppingCart.getTotal());
        return output;
    }

    @Override
    public Customer setCustomer(int customerId) {
        this.customer = customerRepository.findCustomer(customerId);
        return this.customer;
    }

    @Override
    public Customer getCustomer() {
       return this.customer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // sb.append("Customer: " + this.customer.email()+"\n");
        var items = this.shoppingCart.getItems();
        
        items.forEach((key, value) -> {
            sb.append("Item: " + key.toString() + " Quantity: " + value + "\n");
        });

        return sb.toString();
    }

}
