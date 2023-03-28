package com.motilio.cart.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.motilio.cart.bl.BasicPurchaseProcess;
import com.motilio.cart.bl.PurchaseProcess;

@Controller
public class CartFrontend {
    
    PurchaseProcess purchase;

    public CartFrontend() {
        purchase = new BasicPurchaseProcess();
    }

    public static void main(String[] args) {
        //do nothing
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        addCommonAttributes(model, "Home", "Home");
        return "home";
    }

    @GetMapping("/products")
    public String products(Model model, @RequestParam(required = false) String itemid, @RequestParam(required = false) String quantity) {
        addCommonAttributes(model, "Productos", "Products");
        if (itemid != null && quantity != null) {
            System.out.println("------------------ Adding item to cart ------------------\n");
            purchase.AddItemToCart(Integer.parseInt(itemid), new java.math.BigDecimal(quantity));
            System.out.println("Added item: " + itemid + " with quantity: " + quantity + "\n");
            System.out.println("------------------ Finished adding item to cart ------------------\n");
        }
        return "home";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        addCommonAttributes(model, "Carrito de compras", "Cart");
        String[] output = purchase.Checkout();
        List<String[]> cart = new LinkedList<String[]>();
        for (int i = 0; i < output.length-1; i++) {
            if (output[i] != null) {
                cart.add(output[i].split(","));
            }
        }
        model.addAttribute("cart", cart);
        model.addAttribute("total", output[output.length-1]);
        System.out.println(purchase);
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        addCommonAttributes(model, "Iniciar sesión", "Login");
        return "home";
    }

    private void addCommonAttributes(Model model, String title, String navBarActiveTab) {
        System.out.println("------------------ Adding common attributes ------------------\n");
        System.out.println("navBarActiveTab: " + navBarActiveTab + "\n");
        model.addAttribute("title", title);
        String[] navStrings = { "Home", "Products", "Cart", "Login" };
        for (String navString : navStrings) {
            String isActiveString = String.format("is%sActive", navString);
            boolean isActive = navString.equals(navBarActiveTab);
            String hrefString = String.format("href%s", navString);
            String hrefValueString;
            String displayString = String.format("%sDisplay", navString.toLowerCase());
            if (isActive) {
                model.addAttribute(isActiveString, "active");
                hrefValueString = "#";
                model.addAttribute(hrefString, "#");
                model.addAttribute(displayString, "display: block;");
            } else {
                model.addAttribute(isActiveString, "");
                hrefValueString = String.format("/%s", navString.toLowerCase());
                model.addAttribute(hrefString, hrefValueString);
                model.addAttribute(displayString, "display: none;");
            }
            System.out.println("navString: " + navString);
            System.out.println(isActiveString + " set to: " + (isActive ? "active" : "empty_string"));
            System.out.println(hrefString + " set to: " + hrefValueString);
            System.out.println(displayString + " set to: " + (isActive ? "display: block;" : "display: none;") + "\n");
        }
        System.out.println(navBarActiveTab + " equals: " + navBarActiveTab.equals("Products") + "\n");
        System.out.println("------------------ Finished adding common attributes ------------------\n");
    }
}
