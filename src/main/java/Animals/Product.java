package Animals;

import java.util.StringJoiner;

public class Product {
    private String name;
    private int price;
    private String description;
    private final Product.Category Category;
    enum Category {Low, High, Premium }

    //compare of price
   /* @Override
    public int compareTo(Product o) {
        return this.getPrice() - o.getPrice();
    }*/


    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("price=" + price)
                .add("description='" + description + "'")
                .add("Category=" + Category)
                .toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        if (price != product.price) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        return Category == product.Category;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + price;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (Category != null ? Category.hashCode() : 0);
        return result;
    }


    public Product.Category getCategory() {
        return Category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }



    public Product(String name, int price, String description, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.Category = category;
    }
}

