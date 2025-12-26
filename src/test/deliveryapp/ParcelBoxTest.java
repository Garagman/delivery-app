package test.deliveryapp;

import main.deliveryapp.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParcelBoxTest {

    @Test
    void testAddParcel_StandardBox_WithinLimit() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(30.0);
        StandardParcel parcel1 = new StandardParcel("Блины", 10.0, "Москва", 1);
        StandardParcel parcel2 = new StandardParcel("Гриф", 15.0, "Москва", 1);

        box.addParcel(parcel1);
        box.addParcel(parcel2);

        assertEquals(2, box.getAllParcels().size());
    }

    @Test
    void testAddParcel_StandardBox_ExceedsLimit() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(30.0);
        StandardParcel parcel1 = new StandardParcel("Книги", 20.0, "Москва", 1);
        StandardParcel parcel2 = new StandardParcel("Часы", 15.0, "Москва", 1);

        box.addParcel(parcel1);
        box.addParcel(parcel2);

        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    void testAddParcel_FragileBox_BoundaryCase() {
        ParcelBox<FragileParcel> box = new ParcelBox<>(20.0);
        FragileParcel parcel = new FragileParcel("Зеркало", 20.0, "Санкт-Петербург", 1);

        box.addParcel(parcel);

        assertEquals(1, box.getAllParcels().size());
    }
}