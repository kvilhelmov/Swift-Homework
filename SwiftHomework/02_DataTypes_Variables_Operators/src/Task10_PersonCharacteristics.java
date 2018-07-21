public class Task10_PersonCharacteristics{

    public static void main(String[] args){
        
        String firstName = "Peter";
        String lastName = "Ivanov";
        short yearBorn = 1991;
        float weight = 81.5f;
        short height = 184;
        String occupation = "Java Programmer";
        
        final short currentYear = 2016;
        
        short age = (short)(currentYear - yearBorn);
        
        System.out.println(
            firstName + " " + lastName + " is " + age + " years old. " + 
            "He was born in " + yearBorn + ". " +
            "His weight is " + weight + " and he is " + height + " cm tall. " + 
            "He is a " + occupation + ".");
    }
}
