import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

class FileManagement{
    
    static int fileCount = 0;
    static File[] files = new File[500];
    
    public static void main(String[] args){
        
        Scanner sc;
        try{
            sc = new Scanner(new FileReader("inputs/in1"));
        } catch(FileNotFoundException ex){
            System.out.println("File not found.");
            return;
        }
        
        //Scanner sc = new Scanner(System.in);
        
        String line;
        
        while( ! ( line = sc.nextLine() ).equalsIgnoreCase("END") ){
            String[] split = line.split(" ");
            String command = split[0].trim();
            String name = split[1].trim();
            String location;
            File file;
                    
            switch(command.toUpperCase()){
                case "MAKE":
                    location = split[2].trim();
                        
                    if(split.length > 3 && split[3].startsWith("CONTENT")){
                        String stringContent = split[3].substring(split[3].indexOf("=") + 1).trim();
                        
                        //byte[] content = stringToByteArr(stringContent);
                        String content = stringContent;
                        
                        if(name.endsWith(".mp3") || name.endsWith(".avi")){
                            files[fileCount++] = new MediaContentFile(name, location, content);
                        } else{
                            files[fileCount++] = new DocumentContentFile(name, location, content);
                        }    
                    } else {
                        File[] requiredResources = null;
                        
                        if(split.length > 3) {
                            requiredResources = new File[split.length - 3];
                        }
                        
                        for(int i = 0 ; i < split.length - 3; i++){
                            requiredResources[i] = findFile(split[i + 3]);
                        }
                        
                        files[fileCount++] = new ExecutableFile(name, location, requiredResources);
                    }
                    break;
                case "MOVE":
                    location = split[2].trim();
                    
                    file = findFile(name);
                    file.move(location);
                    break;
                case "MOD":
                    String content = split[2].trim();
                    
                    file = findFile(name);
                    ((ContentFile)file).modify(content);
                    break;
                case "COPY":
                    location = split[2].trim();
                    
                    file = findFile(name);
                    files[fileCount++] = file.copy(location);
                    break;
                case "DEL":
                    file = findFile(name);
                    file.delete();
                    break;
                case "EXEC":
                    file = findFile(name);
                    System.out.println(file.execute());
                    System.out.println();
                    break;
                case "INFO":
                    file = findFile(name);
                    System.out.println(file.getInfo());
                    System.out.println();
                    break;
            }
        }
    }

    private static File findFile(String fileName) {
        for(int i = 0 ; i < fileCount ; i++){
            if(files[i].getName().equals(fileName)){
                return files[i];
            }
        }
        
        return null;
    }
    
    /*
    private static byte[] stringToByteArr(String stringContent) {
        int end = stringContent.length() - 1;
        int start = end - 8;
        
        int idx = 0;
        byte[] arr = new byte[10000];
        
        while(start > 0){
            arr[idx++] = (byte)Integer.parseInt(stringContent.substring(start, end), 2);
            end = start;
            start -= 8;
        }
        
        arr[idx++] = (byte)Integer.parseInt(stringContent.substring(0, end), 2);
        
        return Arrays.copyOfRange(arr, 0, idx);
    }*/
}