package main.deliveryapp.model;

import main.deliveryapp.constants.Constants;

public class PerishableParcel extends Parcel {
    protected int timeToLive;

    public PerishableParcel(String description, double weight, String deliveryAddress, int sendDay, int timeToLive) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
        this.price = Constants.PERISHABLE_PRICE;
        this.timeToLive = timeToLive;
    }

    public boolean isExpired(int currentDay) {
        return (sendDay + timeToLive) < currentDay;
    }
}

