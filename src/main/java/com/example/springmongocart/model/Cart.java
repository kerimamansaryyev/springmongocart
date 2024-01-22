package com.example.springmongocart.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "carts")
@CompoundIndex(def = "{'customer.id': 1}", unique = true)
public class Cart {

    @Id
    private String id;
    @DBRef
    private @NonNull Customer customer;

    @DBRef
    private List<CartItem> items;


    public void setItemAtIndex(int index,CartItem item){
        items.set(index, item);
    }

    public void addItem(CartItem cartItem){
        items.add(cartItem);
    }

    public void remove(CartItem item){
        items.remove(item);
    }
}
