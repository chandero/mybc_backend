package commons;

public interface PasswordSecurity {
    Boolean check(String password, String hashPassword);

    String hashPassword(String password);
}