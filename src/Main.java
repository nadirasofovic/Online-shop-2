import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static class Data{
        public static ArrayList<Customer> customerslist = new ArrayList<>();
        public static ArrayList<Customer3> customerslist2 = new ArrayList<Customer3>();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HashMap<String, String> categoryNames = new HashMap<>();
        HashMap<String, ArrayList<Purchase>> customersAndItems = new HashMap<>();
        HashMap<Double, Item> itemHashMap = new HashMap<>();
        HashMap<String, ArrayList<Double>> totalPrice = new HashMap<>();
        HashMap<String, Integer> itemQuantity = new HashMap<>();

        ArrayList<Inventory> inventories = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Purchase> purchases = new ArrayList<>();
        ArrayList<Category> categories = new ArrayList<>();
        ArrayList<Customer2> customers = new ArrayList<>();

        File inputFile1 = new File("items.csv");
        Scanner scanner1 = new Scanner(inputFile1);
        scanner1.nextLine();

        while (scanner1.hasNextLine()) {
            String line = scanner1.nextLine();
            String parts[] = line.split(",");

            Inventory inventory = new Inventory(parts[0], parts[2]);
            inventories.add(inventory);
            Item item = new Item(parts[0], parts[1], parts[2], parts[3]);
            categoryNames.put(parts[0], parts[3]);
            items.add(item);
            itemHashMap.put(item.price, item);
        }

        Collections.sort(items);
        Collections.reverse(items);

        File outputFile1 = new File("TopSellingItemsReport.txt");
        FileWriter fw1 = new FileWriter(outputFile1);
        for (Item a : items) {
            fw1.write(a.toString());
        }
        fw1.close();

        File inputFile2 = new File("purchases.csv");
        Scanner scanner2 = new Scanner(inputFile2);
        scanner2.nextLine();

        while (scanner2.hasNextLine()) {
            String line = scanner2.nextLine();
            String parts[] = line.split(",");

            Purchase purchase = new Purchase(parts[0], parts[1], parts[2]);
            purchases.add(purchase);

            if (!customersAndItems.containsKey(parts[0]))
                customersAndItems.put(parts[0], new ArrayList<>());
            customersAndItems.get(parts[0]).add(purchase);

            for (Inventory inventory : inventories) {
                if (purchase.itemName.equals(inventory.name))
                    inventory.Edit(purchase.quantity);
            }
        }

        for (Purchase purchase : purchases) {
            for (Item item : items) {
                if (purchase.itemName.equals(item.name))
                    purchase.getTotal(item.price);
            }
        }

        for (Purchase purchase : purchases) {
            if (!totalPrice.containsKey(purchase.customerName))
                totalPrice.put(purchase.customerName, new ArrayList<>());
        }

        for (Purchase purchase : purchases) {
            totalPrice.get(purchase.customerName).add(purchase.totalPrice);
        }

        for (String string : totalPrice.keySet()) {
            Double d = 0.;
            for (Double d2 : totalPrice.get(string)) {
                d += d2;
            }
            totalPrice.get(string).add(d);
        }

        File outputFile2 = new File("TopCustomersReport.txt");
        FileWriter fw2 = new FileWriter(outputFile2);

        for (String s : totalPrice.keySet()) {
            fw2.write(s + "," + totalPrice.get(s).get(totalPrice.get(s).size() - 1) + '\n');
        }
        fw2.close();

        File inputFile3 = new File("TopCustomersReport.txt");
        Scanner scanner3 = new Scanner(inputFile3);

        while (scanner3.hasNextLine()) {
            String line = scanner3.nextLine();
            String parts[] = line.split(",");
            Customer2 customer = new Customer2(parts[0], parts[1]);
            customers.add(customer);
        }
        Collections.sort(customers);

        FileWriter fw3 = new FileWriter("TopCustomersReportOrdered.txt");
        for (Customer2 customer : customers) {
            fw3.write(customer.name + " " + customer.total + '\n');
        }
        fw3.close();

        for (Purchase purchase : purchases) {
            itemQuantity.put(purchase.itemName, purchase.quantity);
        }

        for (String string : categoryNames.keySet()) {
            int quantity = 0;
            String name = "";
            for (String string2 : itemQuantity.keySet()) {
                if (string.equals(string2)) {
                    quantity = itemQuantity.get(string2);
                    name = categoryNames.get(string);
                }
            }

            if (quantity != 0) {
                Category category = new Category(name, String.valueOf(quantity));
                categories.add(category);
            }
        }

        Collections.sort(categories);

        File outputFile4 = new File("CategorySalesReport.txt");
        FileWriter fw4 = new FileWriter(outputFile4);

        for (Category category : categories)
            fw4.write(category.toString());
        fw4.close();

        File outputFile5 = new File("InventoryReport.txt");
        FileWriter fw5 = new FileWriter(outputFile5);

        for (Inventory inventory : inventories) {
            fw5.write(inventory.toString());
        }
        fw5.close();

        File outputFile6 = new File("CustomerPurchaseHistoryReport.txt");
        FileWriter fw6 = new FileWriter(outputFile6);
        String s = "";

        for (String string : customersAndItems.keySet()) {
            s = "";
            Double total = 0.;
            for (Purchase purchase : customersAndItems.get(string)) {
                total += purchase.totalPrice;
                s += purchase.itemName + " " + purchase.quantity + " " + purchase.totalPrice + " ";
            }
            fw6.write(string + " " + s + " he spent a total of: " + total + '\n');
        }
        fw6.close();

        File inputFile4 = new File("customers_new.csv");
        Scanner scanner4 = new Scanner(inputFile4);

        scanner4.nextLine();
        ArrayList<Customer> customersNew = new ArrayList<>();
        while (scanner4.hasNextLine()){
            String line = scanner4.nextLine();
            String parts[] = line.split(",");

            Customer customer = new Customer(parts[0],parts[1],parts[2],parts[3]);
            customersNew.add(customer);
        }

        for (Customer customer : customersNew) {
            customer.addList(items);
            customer.Calculate();
        }

        File outputFile7 = new File("FirstReport.txt");
        FileWriter fw7 = new FileWriter(outputFile7);

        for (Customer customer : customersNew) {
            fw7.write(customer.name + " is recommended to buy " + customer.recommended.name + " with a price of " +
                    customer.recommended.price + " because he has " + customer.credit + " credit" + '\n');
        }
        fw7.close();

        Thread thread1 = new Thread(new Thread1(customersNew));
        thread1.start();
        thread1.join();

        File outputFile8 = new File("SecondReport.txt");
        FileWriter fw8 = new FileWriter(outputFile8);

        for (Customer customer: Data.customerslist) {
            fw8.write(customer.name + " " + customer.credit + '\n');
        }
        fw8.close();

        for (Customer customer : customersNew) {
            customer.getTotal();
        }

        ArrayList<Customer3> customers3 = new ArrayList<>();
        for (Customer customer: customersNew) {
            customers3.add(new Customer3(customer));
        }
        Collections.sort(customers3);

        Thread thread2 = new Thread(new Thread2(customers3));
        thread2.start();
        thread2.join();
        File outputFile9 = new File("ThirdReport.txt");
        FileWriter fw9 = new FileWriter(outputFile9);
        for (Customer3 customer : customers3) {
            fw9.write(customer.customer.name + " " + customer.customer.totalSpent + '\n');
        }
        fw9.close();
    }
}