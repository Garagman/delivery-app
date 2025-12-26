package test.deliveryapp;

import main.deliveryapp.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParcelCostCalculationTest {
    @Test
    void testStandardParcelCostCalculation() {
        // Стандартная весовая область стандартной посылки
        StandardParcel parcel = new StandardParcel("Рычаг", 2.5, "Плотина", 1);
        assertEquals(5, parcel.calculateDeliveryCost(), "Стоимость стандартной посылки при стандартном весе высчитана неправильно!");

        // Граничный случай стандартной посылки: Минимальный вес
        StandardParcel lightParcel = new StandardParcel("Камень", 0.1, "Канал", 1);
        assertEquals(0.2, lightParcel.calculateDeliveryCost(), "Стоимость стандартной посылки при минимальном весе высчитана неправильно!");
    }

    @Test
    void testFragileParcelCostCalculation() {
        // Стандартная весовая область хрупкой посылки
        FragileParcel parcel = new FragileParcel("Ваза", 5.0, "Санкт-Петербург", 1);
        assertEquals(20, parcel.calculateDeliveryCost(), "Стоимость хрупкой посылки при стандартном весе высчитана неправильно!");

        // Граничный случай хрупкой посылки: Максимальный вес
        FragileParcel heavyParcel = new FragileParcel("Стеклянный шкаф", 20.0, "Санкт-Петербург", 1);
        assertEquals(80, heavyParcel.calculateDeliveryCost(), "Стоимость хрупкой посылки при максимальном весе высчитана неправильно!");
    }

    @Test
    void testPerishableParcelCostCalculation() {
        // Стандартная весовая область скоропортящейся посылки
        PerishableParcel parcel = new PerishableParcel("Яблоки", 5.0, "Москва", 1, 3);
        assertEquals(15, parcel.calculateDeliveryCost(), "Стоимость скоропортящейся посылки при стандартном весе высчитана неправильно!");

        // Граничный случай скоропортящейся посылки: вес 0
        PerishableParcel zeroWeightParcel = new PerishableParcel("Дубовый лист", 0.0, "Москва", 1, 3);
        assertEquals(0.0, zeroWeightParcel.calculateDeliveryCost(), "Стоимость скоропортящейся посылки при нулевом весе высчитана неправильно!");
    }
}

