package Animals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestProduct {

    @Test
    public void checkNumberOfProductsWithPriceLessThan100(){
        //GIVEN
Main main = new Main();
long expectedCountForPrice = 3;
        //WHEN
        long actualCountForPrice = main.getCountForPriceLessThan100();
        //THEN
        Assertions.assertEquals(expectedCountForPrice, actualCountForPrice,
                String.format("Expected '%s', but was '%s'", expectedCountForPrice, actualCountForPrice));
    }


    @Test
    public void checkNumberOfProductsWithNameIsVegatable(){
        //GIVEN
        Main main = new Main();
        long expectedCountForName = 2;
        //WHEN
        long actualCountForName = main.getCountForNameInVegeteble();
        //THEN
        Assertions.assertEquals(expectedCountForName, actualCountForName,
                String.format("Expected '%s', but was '%s'", expectedCountForName, actualCountForName));
    }
}
