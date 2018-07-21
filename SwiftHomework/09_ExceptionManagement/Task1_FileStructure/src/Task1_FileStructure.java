
import java.util.Scanner;

public class Task1_FileStructure {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line;

        FolderObject root = new FolderObject("root", null);
        int count = 0;

        while (!(line = sc.nextLine()).equalsIgnoreCase("END")) {
            String[] split = line.split(" ");
            String command = split[0].trim();
            String fullPath = split[1].trim();
            count++;

            int idx = fullPath.lastIndexOf("/");
            try {

                FolderObject obj = root;
                if (idx != -1) {
                    String path = fullPath.substring(0, idx);
                    obj = root.getFileSystemObject(path);
                }
                String name = fullPath.substring(idx + 1);

                switch (command.toLowerCase()) {
                    case "mkdir":
                        new FolderObject(name, obj);
                        break;
                    case "touch":
                        new FileObject(name, obj);
                        break;
                    default:
                        //should never happen
                        return;
                }
            } catch (IllegalArgumentException ex) {
                System.out.printf("%d - %s%n", count, ex.getMessage());
            }
        }
    }
}
