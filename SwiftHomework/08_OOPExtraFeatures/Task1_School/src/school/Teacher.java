package school;


import java.util.Arrays;

public class Teacher extends Person{

    private Discipline[] disciplines;
    private int discipineCount;
    
    private Class[] classes;
    private int classCount;
    
    public Teacher(){
        this.disciplines = new Discipline[20];
        this.discipineCount = 0;
        
        this.classes = new Class[20];
        this.classCount = 0;
    }
    
    public void addDiscipline(Discipline newDiscipline) {
        
        for(Discipline discipline : disciplines){
            if(discipline.equals(newDiscipline)){
                return;
            }
        }

        newDiscipline.addTeacher(this);
        
        disciplines[discipineCount++] = newDiscipline;
    }
    
    public Discipline[] getDisciplines(){
        return Arrays.copyOf(disciplines, discipineCount);
    }

    void addClass(Class newClass) {
        for(Class currentClass : classes){
            if(currentClass.equals(newClass)){
                return;
            }
        }

        newClass.addTeacher(this);
        
        classes[classCount++] = newClass;
    }

}
