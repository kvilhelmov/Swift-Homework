

import java.time.LocalDate;

public class SecondaryEducation  {
    private float finalGrade;
    private boolean graduated;
    private LocalDate enrollmentDate;
    private LocalDate graduationDate;
    private String institutionName;
    
    public SecondaryEducation(String institutionName, LocalDate enrollmentDate, LocalDate graduationDate) {
        this.institutionName = institutionName;
        this.enrollmentDate = enrollmentDate;
        this.graduationDate = graduationDate;
    }
    
    public LocalDate getEnrollmentDate(){
        return enrollmentDate;
    }
    
    public LocalDate getGraduationDate(){
        return graduationDate;
    }
    
    public String getInstitutionName(){
        return institutionName;
    }
    
    public void gotGraduated(float finalGrade){
        this.finalGrade = finalGrade;
        this.graduated = true;
    }
    
    public float getFinalGrade(){
        if(graduated){
            return finalGrade;
        }
        
        return -1;
    }
    
    public boolean isGraduated(){
        return graduated;
    }
}
