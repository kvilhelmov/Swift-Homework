
public class FileObject extends FileSystemObject {

    public FileObject(String name, FolderObject parent) {
        super(name, parent);

        int idx = name.lastIndexOf(".");

        if (idx == -1 || (name.length() - name.lastIndexOf(".") - 1 > 7)) {
            throw new IllegalArgumentException("Invalid name specified");
        }
    }

}
