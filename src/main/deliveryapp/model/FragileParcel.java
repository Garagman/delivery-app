package main.deliveryapp.model;

import main.deliveryapp.service.Trackable;
import main.deliveryapp.constants.Constants;

public class FragileParcel extends Parcel implements Trackable {

    public FragileParcel(String description, double weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
        this.price = Constants.FRAGILE_PRICE;
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка <<" + description + ">> обёрнута в защитную плёнку");
        System.out.println("Посылка <<" + description + ">> упакована");
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка <<" + description + ">> изменила местоположение на " + newLocation);
    }
}
