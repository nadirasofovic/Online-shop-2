import java.util.ArrayList;
import java.util.Collections;

public class Thread1 implements Runnable{
    ArrayList<Customer> customers;

    public Thread1(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public void run() {
        int num;
        Collections.sort(customers);
        if (customers.size()>10)
            num=10;
        else num = customers.size();
        for (int i=0; i<num; i++){
            Main.Data.customerslist.add(customers.get(i));
        }
    }
}

