
public class FolderObject extends FileSystemObject {

    FileSystemObject[] children;
    int childrenCount;

    public FolderObject(String name, FolderObject parent) {
        super(name, parent);
        children = new FileSystemObject[200];
        childrenCount = 0;
    }

    void addChild(FileSystemObject obj) {
        children[childrenCount++] = obj;
    }

    FolderObject getFileSystemObject(String path) {

        String current = path;

        int idx = path.indexOf("/");
        if (idx != -1) {
            current = current.substring(0, idx);
        }

        for (int i = 0; i < childrenCount; i++) {
            FileSystemObject child = children[i];
            
            if (child.getName().equalsIgnoreCase(current)) {
                if (child instanceof FolderObject) {
                    if (idx == -1) {
                        return (FolderObject) child;
                    } else {
                        return ((FolderObject) child).getFileSystemObject(path.substring(idx + 1));
                    }
                } else {
                    throw new IllegalArgumentException("Illegal operation");
                }
            }
        }

        throw new IllegalArgumentException("Directory does not exist");
    }

}
