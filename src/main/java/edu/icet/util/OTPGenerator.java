package edu.icet.util;

import java.util.Random;

public class OTPGenerator {
    public String generateOTP() {
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp = new char[5];
        for (int i = 0; i < 5; i++) {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return new String(otp);
    }
}
