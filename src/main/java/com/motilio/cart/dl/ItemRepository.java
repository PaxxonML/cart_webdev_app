package com.motilio.cart.dl;

import java.math.BigDecimal;

import com.motilio.cart.domain.Item;

public interface ItemRepository {
    
    public Item findItem(int itemId);

    public BigDecimal findItemInventory(int itemId);

    public Item addItem(Item item);

    public Item updateItem(Item item);

    public Item deleteItem(int itemId);

}
