
import java.time.LocalDateTime;

class ExecutableFile extends File{
    File[] requiredResources;
    LocalDateTime lastExecutionDate;

    public ExecutableFile(String name, String location, File[] requiredResources) {
        super(name, location);
        this.requiredResources = requiredResources;
    }
    
    @Override
    String execute(){
        
        if(isDeleted)
            return "";
        
        lastExecutionDate = LocalDateTime.now();
        
        String result = String.format("Executing %s", this.getFullPath());
        
        for(File file : requiredResources){
            if(!file.isDeleted)
                result += String.format("%n%s", file.execute());
        }
        
        return result;
    }
    
    @Override
    String getInfo() {
        String result = String.format(
                "%sLast execution date: %s%n"
                + "Required resources:", 
                getCommonInfo(), 
                lastExecutionDate != null ? lastExecutionDate : "NEVER");
        
        for(File file : requiredResources){
            result += String.format("%n\t" + file.getName());
        }
        
        return result;
    }

    @Override
    File copy(String newLocation) {
        
        return new ExecutableFile(getName(), newLocation, requiredResources.clone());
    }
}