package Users;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Authentication implements Serializable {
    private String username;
    private String password = this.encrypt("password");
    private Boolean authenticated = false;

    public Authentication(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        if (authenticated) {
            this.password = this.encrypt(password);
        } else {
            System.out.println("Please login to change your password...");
        }
    }

    public String getUsername() {
        return this.username;
    }

    public void setAuthenticated(String username, String password) {
        this.authenticated = this.username.toLowerCase().equals(username.toLowerCase())
                && this.password.equals(this.encrypt(password));
    }

    public boolean getauthenticated() {
        return this.authenticated;
    }

    private String encrypt(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(input.getBytes());
            byte[] hash = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
