package commons;

import org.mindrot.jbcrypt.BCrypt;

import play.Logger;

public class BCryptPasswordSecurity implements PasswordSecurity {
    @Override
    public Boolean check(String password, String hashPassword) {
    	Logger.info("Hash:"+hashPassword);
        return BCrypt.checkpw(password, hashPassword);
    }

    @Override
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}