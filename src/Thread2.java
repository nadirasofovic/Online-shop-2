import java.util.ArrayList;
import java.util.Collections;

public class Thread2 implements Runnable{
    ArrayList<Customer3> customers3;

    public Thread2(ArrayList<Customer3> customers3) {
        this.customers3 = customers3;
    }

    @Override
    public void run() {
        Collections.sort(customers3);
        int num = 0;
        if (customers3.size() > 10)
            num = 10;
        else customers3.size();

        for (int i=0; i< num; i++)
            Main.Data.customerslist2.add(customers3.get(i));
    }
}