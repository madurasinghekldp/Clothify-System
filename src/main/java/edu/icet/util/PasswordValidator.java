package edu.icet.util;

public class PasswordValidator {

    private static PasswordValidator instance;

    private PasswordValidator(){}

    public static PasswordValidator getInstance(){
        if(instance==null){
            return instance = new PasswordValidator();
        }
        return instance;
    }

    public boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasLetter = false;
        boolean hasDigit = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isLetter(ch)) {
                hasLetter = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            }

            if (hasLetter && hasDigit) {
                return true;
            }
        }

        return false;
    }
}
