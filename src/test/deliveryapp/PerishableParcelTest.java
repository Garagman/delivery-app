package test.deliveryapp;

import main.deliveryapp.model.PerishableParcel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PerishableParcelTest {

    @Test
    void testIsExpired_NotExpired() {
        PerishableParcel parcel = new PerishableParcel("Йогурт", 1.0, "Москва", 1, 3);
        assertFalse(parcel.isExpired(2));
    }

    @Test
    void testIsExpired_Expired() {
        PerishableParcel parcel = new PerishableParcel("Молоко", 2.0, "Москва", 1, 2);
        assertTrue(parcel.isExpired(4));
    }

    @Test
    void testIsExpired_BoundaryCase() {
        PerishableParcel parcel = new PerishableParcel("Мясо", 3.0, "Москва", 5, 4);
        assertFalse(parcel.isExpired(9));
    }

    @Test
    void testIsExpired_ZeroShelfLife() {
        PerishableParcel parcel = new PerishableParcel("Хлеб", 1.0, "Москва", 1, 0);
        assertFalse(parcel.isExpired(1));
    }
}
