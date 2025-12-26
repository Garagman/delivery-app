package main.deliveryapp.model;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private List<T> parcels;
    private double maxWeight;
    private double currentWeight;

    public ParcelBox(double maxWeight) {
        this.parcels = new ArrayList<>();
        this.maxWeight = maxWeight;
        this.currentWeight = 0.0;
    }

    public void addParcel(T parcel) {
        if (currentWeight + parcel.weight > maxWeight) {
            System.out.println("Максимальный вес коробки (" + maxWeight + " кг) превышен!!! Посылку <<" + parcel.description + ">> невозможно добавит!!!");
            return;
        }
        parcels.add(parcel);
        currentWeight += parcel.weight;
        System.out.println("Посылка <<" + parcel.description + ">> успешна добавлена в коробку. Текущий вес коробки: " + currentWeight + "/" + maxWeight + " кг");
    }

    public List<T> getAllParcels() {
        return new ArrayList<>(parcels);
    }

    public boolean isEmpty(){
        return  currentWeight == 0.0;
    }
}

