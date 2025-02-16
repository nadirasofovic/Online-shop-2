public class Inventory {
    String name;
    Integer quantity;

    public Inventory(String name, String quantity) {
        this.name = name;
        this.quantity = Integer.valueOf(quantity);
    }

    @Override
    public String toString() {
        return
                name + ' ' + " " +
                        quantity +'\n';
    }

    public void Edit (Integer i){
        quantity -= i;

    }
}

