public class Item implements Comparable<Item>{
    String name;
    String category;
    Integer quantity;
    Double price;

    public Item(String name, String price,String quantity,String category) {
        this.name = name;
        this.category = category;
        this.quantity = Integer.valueOf(quantity);
        this.price = Double.valueOf(price);
    }

    @Override
    public int compareTo(Item o) {
        return this.quantity-o.quantity;
    }

    @Override
    public String toString() {
        return
                name + ' ' +
                        quantity +'\n';
    }
}

