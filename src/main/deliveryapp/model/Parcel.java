package main.deliveryapp.model;

public abstract class Parcel {
    public String description;
    public double weight;
    protected String deliveryAddress;
    protected int sendDay;
    protected int price;


    public void packageItem() {
        System.out.println("Посылка <<" + description + ">> упакована");
    }

    public void deliver() {
        System.out.println("Посылка <<" + description + ">> доставлена по адресу " + deliveryAddress);
    }

    public double calculateDeliveryCost() {
        return weight * price;
    }
}
