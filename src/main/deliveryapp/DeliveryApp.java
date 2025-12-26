package main.deliveryapp;

import main.deliveryapp.constants.Constants;
import main.deliveryapp.model.*;
import main.deliveryapp.service.Trackable;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<Parcel>();
    private static List<Trackable> trackableParcels = new ArrayList<Trackable>();

    private static ParcelBox<StandardParcel> standardParcelBox = new ParcelBox<>(Constants.STANDARD_BOX_MAX_WEIGHT);
    private static ParcelBox<FragileParcel> fragileParcelBox = new ParcelBox<>(Constants.FRAGILE_BOX_MAX_WEIGHT);
    private static ParcelBox<PerishableParcel> perishableParcelBox = new ParcelBox<>(Constants.PERISHABLE_BOX_MAX_WEIGHT);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    reportAllStatus();
                    break;
                case 5:
                    showBoxContents();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 - Обновить статус трекинга");
        System.out.println("5 - Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Процесс добавления посылки...");
        System.out.println("Выберите тип вашей посылки: 1. Стандартная; 2. Хрупкая; 3. Скоропортящаяся");
        int type = Integer.parseInt(scanner.nextLine());
        System.out.println();

        System.out.print("Опиcание посылки: ");
        String description = scanner.nextLine();
        System.out.println();

        System.out.print("Вес посылки: ");
        double weight = Double.parseDouble(scanner.nextLine());
        System.out.println();

        System.out.print("Адрес доставки: ");
        String deliveryAddress = scanner.nextLine();
        System.out.println();

        System.out.print("День отправки: ");
        int sendDay = Integer.parseInt(scanner.nextLine());
        System.out.println();

        Parcel parcel;
        if (type == 1) {
            StandardParcel standardParcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
            parcel = standardParcel;
            standardParcelBox.addParcel(standardParcel);
        } else if (type == 2) {
            FragileParcel fragileParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
            parcel = fragileParcel;
            fragileParcelBox.addParcel(fragileParcel);
            trackableParcels.add(fragileParcel);
        } else if (type == 3) {
            System.out.print("Срок годности: ");
            int timeToLive = Integer.parseInt(scanner.nextLine());
            PerishableParcel perishableParcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
            parcel = perishableParcel;
            perishableParcelBox.addParcel(perishableParcel);
        } else {
            System.out.println("Неверный тип посылки!");
            System.out.println();
            return;

        }
        allParcels.add(parcel);
        System.out.println("Посылка добавлена!");
        System.out.println();
    }

    private static void sendParcels() {
        if (allParcels.isEmpty()) {
            System.out.println("Нет посылок для отправки!");
            return;
        }
        System.out.println("Процесс добавления посылок...");
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
            System.out.println();
        }
        System.out.println("Все посылки успешно отправлены!");
    }

    private static void calculateCosts() {
        if (allParcels.isEmpty()) {
            System.out.println("Нет посылок для расчета стоимости!!!");
            return;
        }// Посчитать общую стоимость всех доставок и вывести на экран
        System.out.println("Процесс расчета стоимости...");
        double totalCost = 0;

        for (Parcel parcel : allParcels) {
            double cost = parcel.calculateDeliveryCost();
            totalCost += cost;
        }
        System.out.println("Общая стоимость: " + totalCost + "руб.");
    }

    private static void reportAllStatus() {
        if (trackableParcels.isEmpty()) {
            System.out.println("Нет посылок с поддержкой системы трекинга позиции ");
            return;
        }

        System.out.println("Процесс обновления статуса трекинга...");
        System.out.print("Введите новое местоположение: ");
        String newLocation = scanner.nextLine();

        for (Trackable trackable : trackableParcels) {
            trackable.reportStatus(newLocation);
        }
        System.out.println("Статусы всех посылок успешно обновлены!");
    }

    private static void showBoxContents() {
        System.out.println("Выберите коробку для просмотра посылок: 1. Стандартные Посылки; 2. Хрупкие Посылки; 3. Скоропортящиеся посылки");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                if (standardParcelBox.isEmpty()) {
                    System.out.println("Коробка пуста!");
                } else {
                    System.out.println("Содержимое коробки со стандартными посылками:");
                    List<StandardParcel> standartParcels = standardParcelBox.getAllParcels();
                    for (Parcel parcel : standartParcels) {
                        System.out.println("* " + parcel.description + " (вес: " + parcel.weight + " кг)");
                    }
                }
                break;
            case 2:
                if (fragileParcelBox.isEmpty()){
                    System.out.println("Коробка пуста!");
                } else {
                System.out.println("Содержимое коробки с хрупкими посылками:");
                List<FragileParcel> fragileParcels = fragileParcelBox.getAllParcels();
                for (Parcel parcel : fragileParcels) {
                    System.out.println("* " + parcel.description + " (вес: " + parcel.weight + " кг)");
                }
        }
                break;
            case 3:
                if (perishableParcelBox.isEmpty()){
                    System.out.println("Коробка пуста!");
                } else {
                    System.out.println("Содержимое коробки со скоропортящимися посылками:");
                    List<PerishableParcel> perishableParcels = perishableParcelBox.getAllParcels();
                    for (Parcel parcel : perishableParcels) {
                        System.out.println("* " + parcel.description + " (вес: " + parcel.weight + " кг)");
                    }
                }
                break;
            default:
                System.out.println("Такой коробки нет");
        }
    }

}