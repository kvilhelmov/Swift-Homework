public class Credentials{
    private String      _username;
    private String      _password;
    
    private int         _oldPasswordCount;
    private String[]    _oldPasswords;
    
    public Credentials(String username, String password){
        _username = username;
        _password = password;
        _oldPasswordCount = 0;
        _oldPasswords = new String[100];
    }
    
    public String getUsername(){
        return _username;
    }
    
    public boolean matchPassword(String password){
        return _password.equals(password);
    }
    
    public void changePassword(String oldPassword, String newPassword) throws OldPasswordConflictException{
        
        if( !matchPassword(oldPassword) ){
           
            throw new IllegalArgumentException("Wrong password.");
        }
        
        if( matchPassword(newPassword) ){
            throw new OldPasswordConflictException(0);
        }
        
        for(int i = 0 ; i < _oldPasswordCount ; i++){
            if(_oldPasswords[i].equals(newPassword)){
                throw new OldPasswordConflictException(_oldPasswordCount - i);
            }
        }
    
        _oldPasswords[_oldPasswordCount++] = _password;
        _password = newPassword;
    }
}