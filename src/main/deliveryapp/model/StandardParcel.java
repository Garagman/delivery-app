package main.deliveryapp.model;

import main.deliveryapp.constants.Constants;

public class StandardParcel extends Parcel {

    public StandardParcel(String description, double weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
        this.price = Constants.STANDARD_PRICE;
    }
}
