public class OldPasswordConflictException extends Exception {

    private int passwordIdx;
    
    public OldPasswordConflictException(int index) {
        super("Password matches a recently used one: " + index);
        passwordIdx = index;
    }
    
    public int getPasswordConflictIndex(){
        return passwordIdx;
    }
    
}
