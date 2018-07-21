package school;


import java.util.Arrays;

public class Discipline  {
    private String name;
    private int hoursPerWeek;
    
    private Teacher[] teachers;
    private int tutorCount;
    
    
    public Discipline(String name, int hoursPerWeek){
        this.name = name;
        this.hoursPerWeek = hoursPerWeek;
        
        teachers = new Teacher[20];
        tutorCount = 0;
    }
    
    public void addTeacher(Teacher newTeacher){
        
        for(Teacher teacher : teachers){
            if(teacher.equals(newTeacher)){
                return;
            }
        }
        
        newTeacher.addDiscipline(this);
        
        teachers[tutorCount++] = newTeacher;
    }
    
    public Teacher[] getTeachers(){
        return Arrays.copyOf(teachers, tutorCount);
    }
}
