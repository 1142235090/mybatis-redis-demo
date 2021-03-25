package com.chrise.demo.bean;

import java.io.Serializable;

/**
 * Car
 *
 * @author hanzhao
 * @date 2021/3/25
 */
public class Car implements Serializable {
    private double price;
    private String colour;

    public Car(){
    }

    public Car(double price, String colour){
        this.price = price;
        this.colour = colour;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString(){
        return colour +"car costs $"+price;
    }
}
