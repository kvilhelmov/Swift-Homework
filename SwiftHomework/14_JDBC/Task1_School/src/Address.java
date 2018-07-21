
public class Address {

    String country;
    String city;
    String street;
    String number;
    int floor;
    int apartmentNo;

    Address(String country, String city, String street, String number, int floor, int apartmentNo) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
        this.floor = floor;
        this.apartmentNo = apartmentNo;
    }

    @Override
    public String toString() {
        return String.format("%s %s Street%nfl. %d, ap. %d%n%s, %s", number, street, floor, apartmentNo, city, country);
    }

}
