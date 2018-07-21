
public abstract class FileSystemObject {

    static String allowedSpecials = " ()_!.&";

    String name;
    FolderObject parent;

    public FileSystemObject(String name, FolderObject parent) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Invalid name specified");
        }

        if (allowedSpecials.contains(Character.toString(name.charAt(0)))) {
            throw new IllegalArgumentException("Invalid name specified");
        }
        
        for (Character c : name.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && !allowedSpecials.contains(Character.toString(c))) {
                throw new IllegalArgumentException("Invalid name specified");
            }
        }

        this.name = name;
        this.parent = parent;
        if (parent != null) {
            parent.addChild(this);
        }
    }

    String getName() {
        return name;
    }
}
