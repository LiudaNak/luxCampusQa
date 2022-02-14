package Animals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.Locale;

class TestCat {

    @Test
    public void checkCatSound() {
        //GIVEN
        Cat cat = new Cat("testCat", 3, "pet");
        String expectedSound = "meow meow";
        //WHEN
        String actualSound = cat.makeSound();
        //THEN
        Assertions.assertEquals(expectedSound, actualSound,
                String.format("Expected '%s', but was '%s'", expectedSound, actualSound));
    }

    @Test
    public void checkCatClass() {
        //GIVEN
        Cat cat = new Cat("testCat", 3, "pet");
        String expectedSimpleName = "Cat";

        //WHEN
        String actualSimpleName = cat.getClass().getSimpleName();

        //THEN
        Assertions.assertEquals(expectedSimpleName, actualSimpleName);
    }


    @Test
    public void checkCatType() {
        //GIVEN
        Cat cat = new Cat("Murchyk", 10, "SuperCat");
        String expectedCatType = "superCat".toLowerCase();

        //WHEN
        String actualCatType = cat.getType().toLowerCase();

        //THEN
        Assertions.assertEquals(expectedCatType, actualCatType,
                String.format("Expected '%s', but was '%s'", expectedCatType, actualCatType));
    }


    @Test
    public void checkCatModifier() {
        //GIVEN
        Cat cat = new Cat("Barsik", 1, "ForestCat");
        int expectedCatModifier = 1;

        //WHEN
        int actualCatModifier = cat.getClass().getModifiers();

        //THEN
        Assertions.assertEquals(expectedCatModifier, actualCatModifier,
                String.format("Expected '%s', but was '%s'", expectedCatModifier, actualCatModifier));
    }


    @Test
    public void checkCatCanonicalName() {
        //GIVEN
        Cat cat = new Cat("Barsik", 1, "ForestCat");
        String expectedcanonicalName = "Animals.Cat";

        //WHEN
        String actualCanonicalName = cat.getClass().getCanonicalName();

        //THEN
        Assertions.assertEquals(expectedcanonicalName, actualCanonicalName,
                String.format("Expected '%s', but was '%s'", expectedcanonicalName, actualCanonicalName));
    }
}