package fr.anthonyquere.fizzbuzz;

import fr.anthonyquere.teashop.Tea;
import fr.anthonyquere.teashop.TeaCup;
import fr.anthonyquere.teashop.TeaShop;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeaShopTest {

    private Tea tea1;
    private TeaCup teaCup;
    private TeaShop teaShop;

    @BeforeEach
    void setUp(){
        teaShop = new TeaShop(100);
        teaCup = new TeaCup();
        tea1 = new Tea("Black Tea", 80, 80, true);
        Tea tea2 = new Tea("Oolong Tea", 60, 85, false);
        teaShop.addTea(tea2);
    }
    @Test
    void should_not_be_drinkable_if_empty(){
        // une Tasse vide n'est pas buvable.
        assertThat(teaCup.isReadyToDrink()).isFalse();
    }

    @Test
    void should_not_be_drinkable_after_adding_only_water(){
        teaCup.addWater(80);

        // Une tasse sans thé n'est pas buvable.
        assertThat(teaCup.isReadyToDrink()).isFalse();
    }

    @Test
    void should_throw_exception_when_adding_tea_without_water(){
        // Tester que l'ajout de thé sans eau lève une exception.
        assertThrowsExactly(IllegalStateException.class, () -> teaCup.addTea(tea1));
    }

    @Test
    void should_not_be_drinkable_if_temperature_is_not_ideal(){
        teaCup.addWater(90);

        // Une tasse avec une température pas idéale n'est pas buvable.
        assertThat(teaCup.isReadyToDrink()).isFalse();
    }

    @Test
    void should_not_be_drinkable_without_steeping_time(){
        teaCup.addWater(80);
        teaCup.addTea(tea1);

        // Tasse qui n'a pas eu le temps d'infuser n'est pas buvable.
        assertThat(teaCup.isReadyToDrink()).isFalse();
    }

    @Test
    void should_add_tea(){
        teaShop.addTea(tea1);
        teaCup = teaShop.prepareTea("Black Tea");

        // Tester l'ajout d'un thé indirectement en le préparant.
        assertThat(teaCup).isNotNull();
    }

    @Test
    void should_prepare_tea(){
        teaCup = teaShop.prepareTea("Oolong Tea");

        // Tester la préparation d'un thé déjà dans le Shop.
        assertThat(teaCup).isNotNull();
    }

    @Test
    void should_throw_exception_if_trying_to_prepare_a_non_existing_tea(){
        // Tester que l'ajout d'un thé qui n'existe pas lève une exception.
        assertThrowsExactly(IllegalArgumentException.class, () -> teaShop.prepareTea("Chai Tea"));
    }

    @Test
    void should_be_valid_water_temperature(){
        teaShop.setWaterTemperature(59);

        // Tester la nouvelle température indirectement.
        assertThat(teaShop).isNotNull();
    }

    @Test
    void should_throw_exception_if_not_valid_water_temperature(){
        // Tester qu'une température d'eau incorrecte lève une exception.
        assertThrowsExactly(IllegalArgumentException.class, () -> teaShop.setWaterTemperature(1000));
    }
}
