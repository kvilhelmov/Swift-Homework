public class Task8_SecondsInBiggerIntervals{

    public static void main(String[] args){
        
        int totalSeconds = 129600;
        
        int seconds = totalSeconds;
        
        int minutes = seconds / 60;
        seconds %= 60;
        
        int hours = minutes / 60;
        minutes %= 60;
        
        int days = hours / 24;
        hours %= 24;
        
        System.out.println(days     + " days, "    + 
                                hours    + " hours, "   +
                                minutes  + " minutes, " + 
                                seconds  + " seconds");
    }
}
