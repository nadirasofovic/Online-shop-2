public class Customer3 implements Comparable<Customer3>{
    Customer customer;

    public Customer3(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int compareTo(Customer3 o) {
        return (int) (o.customer.totalSpent-this.customer.totalSpent);
    }
}
