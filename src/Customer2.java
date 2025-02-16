public class Customer2 implements Comparable <Customer2>{
    String name;
    Double total;

    public Customer2(String name, String total) {
        this.name = name;
        this.total = Double.valueOf(total);
    }

    @Override
    public int compareTo(Customer2 o) {
        return (int) (this.total-o.total);
    }
}

