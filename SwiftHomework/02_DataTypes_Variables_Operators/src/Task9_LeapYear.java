
public class Task9_LeapYear {

    public static void main(String[] args) {

        int year = 1900;

        boolean isLeapYear = (year % 4 == 0) && (year % 100 != 0);

        isLeapYear = isLeapYear || (year % 400 == 0);

        System.out.println(isLeapYear);

    }
}
