
import java.util.Scanner;

class Market {

    static Person[] people;
    static Product[] products;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = sc.nextLine();
        if (!tryParsePeople(line)) {
            return;
        }

        line = sc.nextLine();
        if (!tryParseProducts(line)) {
            return;
        }

        while (!(line = sc.nextLine()).equalsIgnoreCase("END")) {

            int lastIdx = line.lastIndexOf(" ");
            String username = line.substring(0, lastIdx);
            String productName = line.substring(lastIdx + 1);

            Person person = findPerson(username);
            Product product = findProduct(productName);
            if (person.tryBuy(product)) {
                System.out.println(person.getName() + " bought " + product.getName());
            } else {
                System.out.println(person.getName() + " can't afford " + product.getName());
            }
        }

        for (Person person : people) {

            String result = person.getName() + " - ";

            Product[] productsBought = person.getProductsBought();

            if (productsBought.length == 0) {
                result += "Nothing bought";
            } else {

                for (int i = 0; i < productsBought.length - 1; i++) {
                    result += productsBought[i].getName() + ", ";
                }

                result += productsBought[productsBought.length - 1].getName();
            }

            System.out.println(result);
        }

    }

    private static boolean tryParsePeople(String line) throws NumberFormatException {
        String[] split = line.split(";");
        people = new Person[split.length];
        for (int i = 0; i < split.length; i++) {
            String personInfo = split[i].trim();
            String[] newSplit = personInfo.split("=");
            String name = newSplit[0].trim();
            double balance = Double.parseDouble(newSplit[1].trim());
            if (name.isEmpty()) {
                System.out.println("Name can't be empty.");
                return false;
            }
            if (balance < 0) {
                System.out.println("Balance can't be negative.");
                return false;
            }
            people[i] = new Person(name, balance);
        }

        return true;
    }

    private static boolean tryParseProducts(String line) {
        String[] split = line.split(";");
        products = new Product[500];

        for (int i = 0; i < split.length; i++) {
            String productInfo = split[i].trim();
            String[] newSplit = productInfo.split("=");
            String name = newSplit[0].trim();

            double price = Double.parseDouble(newSplit[1].trim());

            if (name.isEmpty()) {
                System.out.println("Name can't be empty.");
                return false;
            }
            if (price < 0) {
                System.out.println("Price can't be negative.");
                return false;
            }

            products[i] = new Product(name, price);
        }

        return true;
    }

    private static Person findPerson(String username) {
        for (Person person : people) {
            if (person.getName().equals(username)) {
                return person;
            }
        }

        return null;
    }

    private static Product findProduct(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }

        return null;
    }

}
