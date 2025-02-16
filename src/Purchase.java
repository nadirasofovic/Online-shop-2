public class Purchase {
    String customerName;
    String itemName;
    Integer quantity;
    Double totalPrice;

    public Purchase(String customerName, String itemName, String quantity) {
        this.customerName = customerName;
        this.itemName = itemName;
        this.quantity = Integer.valueOf(quantity);
    }

    public void getTotal (Double d) {
        totalPrice = quantity * d;
    }

    @Override
    public String toString() {
        return
                customerName + ' ' +
                        + totalPrice + '\n';
    }
}
