package edu.icet.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private static EmailValidator instance;

    private EmailValidator(){}

    public static EmailValidator getInstance(){
        if(instance==null){
            return instance = new EmailValidator();
        }
        return instance;
    }

    public String validatedEmail(String email){
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if(matcher.matches()){
            return email;
        }
        return null;
    }
}
