package com.motilio.cart.dl;

import java.math.BigDecimal;
import java.util.Map;

import com.motilio.cart.domain.*;

public class ItemDatabase implements ItemRepository {
    
    private Map<Integer, Item> items = Map.of(
        1, new Item(1, "Tomate", "Tomate Huaje", new BigDecimal(43.0)),
        2, new Item(2, "Papa", "Papa Blanca", new BigDecimal(24.0)),
        3, new Item(3, "Cebolla", "Cebolla Morada", new BigDecimal(15.0)),
        4, new Item(4, "Chile", "Chile Serrano", new BigDecimal(43.0))
    );

    @Override
    public Item findItem(int itemId) {
       return items.get(itemId);
    }

    @Override
    public BigDecimal findItemInventory(int itemId) {
        return items.get(itemId).price();
    }

    @Override
    public Item addItem(Item item) {
        if (items.containsKey(item.itemId())) {
            return null;
        } else {
            items.put(item.itemId(), item);
            return item;
        }
    }

    @Override
    public Item updateItem(Item item) {
        if (items.containsKey(item.itemId())) {
            items.put(item.itemId(), item);
            return item;
        } else {
            return null;
        }
    }

    @Override
    public Item deleteItem(int itemId) {
        if (items.containsKey(itemId)) {
            return items.remove(itemId);
        } else {
            return null;
        }
    }
}
