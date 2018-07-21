
public class Car {

    int currentYear = 2017;

    String brand;
    String model;
    int horsePower;
    int yearProduced;

    int insuranceCategory() {

        int age = currentYear - yearProduced;

        if (age < 8) {
            return 1;
        } else if (age < 15) {
            return 2;
        } else if (age < 25) {
            return 3;
        } else {
            return 4;
        }
    }

    double insurancePrice() {
        int category = insuranceCategory();

        double price = 0;
        switch (category) {
            case 1:
                price = 150;
                break;
            case 2:
                price = 200;
                break;
            case 3:
                price = 300;
                break;
            case 4:
                price = 500;
                break;
        }

        if (horsePower < 80) {
            return price * 1.2;
        } else if (horsePower > 140) {
            return price * 1.45;
        } else {
            return price;
        }
    }
}
