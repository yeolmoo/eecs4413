package com.evstore.ecommerce.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.evstore.ecommerce.model.Cart;
import com.evstore.ecommerce.model.CartItem;
import com.evstore.ecommerce.model.Vehicle;
import com.evstore.ecommerce.repository.CartRepository;
import com.evstore.ecommerce.repository.VehicleRepository;
import com.evstore.ecommerce.user.User;
import com.evstore.ecommerce.userrepository.UserRepository;

public class CartService {

    
    private UserRepository userRepository;
    
    private VehicleRepository vehicleRepository;

    private CartRepository cartRepository;




public Cart getCartFromUser(Long userID) {
    User user = userRepository.findById(userID).orElse(null);
    if (user == null){
        throw new RuntimeException("User doesnt exists.");

    }

    Cart cart = cartRepository.findByUser(user).orElse(null);
    if(cart == null){

        cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);

    }

    return cart;
}



public void addToCart(Long userID, int carID,int quanity){

Cart cart = getCartFromUser(userID);

Vehicle vehicle = vehicleRepository.findById(carID).orElse(null);
if (vehicle == null){
    throw new RuntimeException("car doesnt exists.");
}


CartItem itemAlreadyExist = null; 
    
// loops thru all cartItems till cartItemID matches Id of car im looking for then i will assign that item to varible itemAlreadyExist
for(CartItem item : cart.getCartItems()){
    if(item.getCar().getId() == (carID)){
        itemAlreadyExist = item;
        break;
    }
}

    if(itemAlreadyExist != null){

        int inceaseQuantity = itemAlreadyExist.getQuanity() + quanity;
        itemAlreadyExist.setQuanity(inceaseQuantity);
        //cart.setQuanity

        BigDecimal cartTotalPrice = cart.getTotalPrice();

        BigDecimal alreadyExistItemTotalPrice = itemAlreadyExist.getPrice().multiply(BigDecimal.valueOf(itemAlreadyExist.getQuanity()));


        cart.setTotalPrice(cartTotalPrice.add(alreadyExistItemTotalPrice));


    }
    else{

        // if first item then make new item and add to cart
    CartItem item = new CartItem(vehicle,cart,quanity,vehicle.getPrice());
    cart.getCartItems().add(item);
    cart.setQuanity(cart.getQuanity() + quanity);
    BigDecimal itemTotalPrice = item.getPrice().multiply(BigDecimal.valueOf(quanity));
    cart.setTotalPrice(cart.getTotalPrice().add(itemTotalPrice));

}
cartRepository.save(cart);
}


public void removeFromCart(Long userID, int carID){

    // get the cart of the user
    Cart cart = getCartFromUser(userID);
    CartItem removeItem = null; 
    
    // loops thru all cartItems till cartItemID matches Id of car im looking for then i will assign that item to varible removeItem
    for(CartItem item : cart.getCartItems()){
        if(item.getCar().getId() == (carID)){
            removeItem = item;
            break;
        }
    }

    if(removeItem != null){

        BigDecimal cartTotalPrice = cart.getTotalPrice();

        cart.setQuanity(cart.getQuanity() - removeItem.getQuanity());

        BigDecimal removedItemTotalPrice = removeItem.getPrice().multiply(BigDecimal.valueOf(removeItem.getQuanity()));

        cart.setTotalPrice(cartTotalPrice.subtract(removedItemTotalPrice));

    }

    // remove item that i want to remove from cart.
    cart.getCartItems().remove(removeItem);
    cartRepository.save(cart);

}






}
