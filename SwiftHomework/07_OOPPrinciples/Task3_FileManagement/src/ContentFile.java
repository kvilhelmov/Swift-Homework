import java.time.LocalDateTime;

abstract class ContentFile extends File{
    
    char symbol;
    
    String content;
    LocalDateTime lastModifiedDate;

    public ContentFile(String name, String location, String content) {
        super(name, location);
        this.content = content;
        this.lastModifiedDate = getCreationDate();
    }
    
    void modify(String content){
        this.content = content;
        lastModifiedDate = LocalDateTime.now();
    }

    @Override
    String getInfo() {
        return String.format("%sLast modification date: %s", getCommonInfo(), lastModifiedDate);
    }
    
    @Override
    String execute(){
        if(isDeleted) 
            return "";
        
        return String.format("%1$s%1$s%1$s %2$s%n%3$s", symbol, getFullPath(), content);
    }
    
    @Override
    File copy(String newLocation) {
        
        try {
            return (ContentFile)this.clone();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }
}