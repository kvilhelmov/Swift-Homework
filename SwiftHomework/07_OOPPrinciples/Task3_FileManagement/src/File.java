
import java.time.LocalDateTime;

abstract class File{
    private String name;
    private String location;
    private LocalDateTime creationDate;
    protected boolean isDeleted;
    
    File(String name, String location){
        this.name = name;
        this.location = location;
        creationDate = LocalDateTime.now();
        isDeleted = false;
    }
    
    abstract File copy(String newLocation);
    abstract String execute();
    abstract String getInfo();
    
    void move(String newLocation){
        location = newLocation;
    }
    
    void delete(){
        isDeleted = true;
    }
    
    protected String getCommonInfo(){
        return String.format("Name: %s%s%n"
                + "Creation date: %s%n",
                
                getFullPath(), 
                isDeleted ? " [DELETED]" : "", 
                creationDate);
    }
    
    String getFullPath(){
        return String.format("%s/%s", location, name);
    }

    String getName() {
        return name;
    }
    
    final LocalDateTime getCreationDate(){
        return creationDate;
    }
}