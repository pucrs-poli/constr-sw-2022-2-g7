package br.pucrs.auth.utils;

import java.util.regex.Pattern;

public class ValidationUtils {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String email) {
        return VALID_EMAIL_ADDRESS_REGEX.matcher(email).find();
    }
}
