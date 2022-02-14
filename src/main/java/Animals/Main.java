package Animals;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    Product product1 = new Product("flower", 100, "Rose", Product.Category.Premium);
    Product product2 = new Product("vegetable", 20, "onion", Product.Category.Premium);
    Product product3 = new Product("fruit", 50, "apple", Product.Category.High);
    Product product4 = new Product("device", 100000, "iPhone", Product.Category.Low);
    Product product5 = new Product("Vegetable", 60, "cucumber", Product.Category.High);

    //Переводжу масив у ліст
    Product[] productArray = {product1, product2, product3, product4, product5};
    List<Product> listOfProducts = Arrays.asList(productArray);

    public static void main(String[] arg) {
        Main main = new Main();
        main.getCollectionsByCategories();
        main.getCountForPriceLessThan100();
        main.getCountForNameInVegeteble();
    }


        private void getCollectionsByCategories(){
            //роблю три стріми, кожен фільтрую за категорією і знов переводжу в ліст
        List<Product> listOfHighCatgory = listOfProducts.stream()
                .filter(e -> e.getCategory().equals(Product.Category.High))
                .collect(Collectors.toList());

        List<Product> listOfPremiumCategory = listOfProducts.stream()
                .filter(e -> e.getCategory().equals(Product.Category.Premium))
                .collect(Collectors.toList());

        List<Product> listOfLowCategoty = listOfProducts.stream()
                .filter(e -> e.getCategory().equals(Product.Category.Low))
                .collect(Collectors.toList());

        // виводжу на екран лісти за категоріями
        System.out.println("Products with high category: " + listOfHighCatgory);
        System.out.println("Products with premium category: " + listOfPremiumCategory);
        System.out.println("Products with low category" + listOfLowCategoty);
    }



    public long getCountForPriceLessThan100(){
        //Посчитать количество продуктов с ценой ниже 100
        //створюю стрім і фільтрую, потім підраховую результат
        long countForPrice = listOfProducts.stream()
                .filter(e -> e.getPrice() < 100)
                .count();
        System.out.println("There are " + countForPrice + " products which price is less than 100 ghn");
        return countForPrice;
    }



    public long getCountForNameInVegeteble() {
        //Посчитать количество объектов которые имеют "vegetable" в названии
        //фільтрую, переводжу в нижній регістр і підраховуюю
    long countForName = listOfProducts.stream().filter(e -> e.getName().toLowerCase().equals("vegetable")).count();
            System.out.println("There are "+ countForName +" products which Name is \"Vegetable\"");
            return countForName;
}
    }
