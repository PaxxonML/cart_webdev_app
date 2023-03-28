package com.motilio.cart.dl;

import java.util.Map;

import com.motilio.cart.domain.*;

public class CustomerDatabase implements CustomerRepository {
    
    private Map<Integer, Customer> customers = Map.of(
        0, new Customer(0, "Paco", "Murillo", "a01374561@tec.mx", "555-555-5555"),
        1, new Customer(1, "Marco", "Peña", "motilio@tec.mx", "555-555-5555"),
        2, new Customer(2, "Angeles", "Constantino", "aconstan@tec.mx", "555-555-5555"),
        3, new Customer(3, "Juan", "Perez", "jperez@tec.mx", "555-555-5555"),
        4, new Customer(4, "Maria", "Gonzalez", "mgonzalez@tec.mx", "555-555-5555"),
        5, new Customer(5, "Pedro", "Martinez", "pmartinez@tec.mx", "555-555-5555")
    );

    @Override
    public Customer findCustomer(int customerId) {
        return customers.get(customerId);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        customers.put(customer.customerId(), customer);
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        if (customers.containsKey(customer.customerId())) {
            customers.put(customer.customerId(), customer);
            return customer;
        } else {
            return null;
        }
    }

    @Override
    public Customer deleteCustomer(int customerId) {
        if (customers.containsKey(customerId)) {
            return customers.remove(customerId);
        } else {
            return null;
        }
    }
}
