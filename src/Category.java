public class Category implements Comparable<Category>{
    String name;
    Integer quantity;

    public Category(String name, String quantity) {
        this.name = name;
        this.quantity = Integer.valueOf(quantity);
    }

    @Override
    public int compareTo(Category o) {
        return o.quantity-this.quantity;
    }

    public String getIme() {
        return name;
    }

    @Override
    public String toString() {
        return
                name + '\'' +
                        quantity +  '\n'
                ;
    }
}

