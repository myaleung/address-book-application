package com.digitalfuturescorp.app.utils;

public abstract class Validation {
    private static final String emailRegEx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public static boolean matchesEmailRegEx(String toValidate) {
        return toValidate.matches(emailRegEx);
    }
}
