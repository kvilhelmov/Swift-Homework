
public class SwiftDate {

    int year;
    int month;
    int day;

    SwiftDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    boolean isLeapYear(){
        return isLeapYear(this.year);
    }

    int getCentury() {
        return year / 100 + 1;
    }

    int getDaysDifference(SwiftDate other) {

        SwiftDate older;
        SwiftDate recent;

        if (this.compareTo(other) > 0) {
            recent = this;
            older = other;
        } else {
            recent = other;
            older = this;
        }

        int count = 0;

        int y = older.year;
        int m = older.month;
        int d = older.day;

        while (!(d == recent.day && m == recent.month && y == recent.year)) {
            d++;
            count++;

            if (d > getDaysInMonth(m, y)) {
                d = 1;
                m++;
                if (m > 12) {
                    m = 1;
                    y++;
                }
            }
        }

        return ++count;
    }

    String getInfo() {
        String result = String.format("%4d %02d %02d - %02d century.",
                year, month, day, getCentury());

        if (isLeapYear()) {
            result += " It is LEAP year.";
        }

        return result;
    }

    private int compareTo(SwiftDate other) {
        if (this.year > other.year) {
            return 1;
        } else if (this.year < other.year) {
            return -1;
        }

        if (this.month > other.month) {
            return 1;
        } else if (this.month < other.month) {
            return -1;
        }

        if (this.day > other.day) {
            return 1;
        } else if (this.day < other.day) {
            return -1;
        }

        return 0;
    }

    static private int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (isLeapYear(year)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1;
        }
    }
    
    static boolean isLeapYear(int year){

        if (year % 400 == 0) {
            return true;
        }

        if (year % 4 == 0 && year % 100 != 0) {
            return true;
        }

        return false;
    }

}
