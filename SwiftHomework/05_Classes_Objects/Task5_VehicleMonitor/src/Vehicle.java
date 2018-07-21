
import java.time.Year;

class Vehicle {

    static short currentYear = (short) Year.now().getValue();

    String type;
    String model;
    short power;
    float fuelConsumption;
    float weight;
    short year;
    String color;
    String licenseNo;

    Vehicle(String licenseNo, String type, String model, short power, float fuelConsumption, short year, float weight, String color) {
        this.licenseNo = licenseNo;
        this.type = type;
        this.model = model;
        this.power = power;
        this.fuelConsumption = fuelConsumption;
        this.year = year;
        this.weight = weight;
        this.color = color;
    }

    Vehicle(String licenseNo, String type, String model, short power, float fuelConsumption, short year) {
        this(licenseNo, type, model, power, fuelConsumption, year, -1, "N/A");
    }

    double calculateTripPrice(double fuelPrice, double distance) {
        return distance * (fuelConsumption / 100) * fuelPrice;
    }

    double getInsurancePrice() {
        double insurancePrice = 0.01 * power * (currentYear - year) * fuelConsumption;

        switch (type) {
            case "car":
                insurancePrice *= 1;
                break;
            case "suv":
                insurancePrice *= 1.12;
                break;
            case "truck":
                insurancePrice *= 1.2;
                break;
            case "motorcycle":
                insurancePrice *= 1.5;
                break;
            default:
                // should be impossible
                insurancePrice = -1;
                break;
        }

        return insurancePrice;
    }

    @Override
    public String toString() {

        String result = String.format("%s - %s, %s", this.licenseNo, this.model, this.year);
        if (this.color != null) {
            result += String.format(", %s", this.color);
        }
        return result;
    }

}
