import java.util.ArrayList;

public class Customer implements Comparable<Customer>{

    String name;
    String email;
    String favoriteCategory;
    ArrayList<Item> items;
    ArrayList<Purchase> purchases = new ArrayList<>();
    Double credit;
    Double totalSpent;
    Item recommended = new Item("none", "0", "0", "none");
    public Customer(String name, String email, String credit, String favoriteCategory) {
        this.name = name;
        this.email = email;
        this.credit = Double.valueOf(credit);
        this.favoriteCategory = favoriteCategory;
    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addList(ArrayList<Item> i){
        items = i;
        Calculate();
    }

    public void Calculate() {
        Double current = 0.0;
        if (credit != 0) {
            for (Item item : items) {
                if (item.category.equals(favoriteCategory) && item.price <= credit && item.price > current) {
                    current = item.price;
                    recommended = item;
                }
            }
        }
        else recommended= new Item("none","0","0","none");
    }

    public void getTotal (){
        Double total = 0.0;
        for (Purchase purchase : purchases) {
            for (Item item : items) {
                if (item.name.equals(purchase.itemName))
                    total += item.price * purchase.quantity;
            }
        }
        totalSpent=total;
    }

    @Override
    public int compareTo(Customer o) {
        return (int) (this.credit-o.credit);
    }
}

