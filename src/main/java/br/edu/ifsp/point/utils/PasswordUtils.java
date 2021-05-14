package br.edu.ifsp.point.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtils {

    public static String encrypt(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static Boolean checkPassword(String password, String passwordEncrypted) {
        return BCrypt.checkpw(password, passwordEncrypted);
    }

}
