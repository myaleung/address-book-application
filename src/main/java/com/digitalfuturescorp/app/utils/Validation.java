package com.digitalfuturescorp.app.utils;

public abstract class Validation {
    private static final String emailRegEx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final String phoneNumRegEx = "^(((\\+44\\s?\\d{4}|\\(?0\\d{4}\\)?)\\s?\\d{3}\\s?\\d{3})|((\\+44\\s?\\d{3}|\\(?0\\d{3}\\)?)\\s?\\d{3}\\s?\\d{4})|((\\+44\\s?\\d{2}|\\(?0\\d{2}\\)?)\\s?\\d{4}\\s?\\d{4}))(\\s?\\#(\\d{4}|\\d{3}))?$";

    public static boolean isNull(String toValidate) {
        return toValidate == null;
    }

    public static boolean isEmpty(String toValidate) {
        return toValidate.trim().isEmpty();
    }

    public static boolean matchesEmailRegEx(String toValidate) {
        return toValidate.matches(emailRegEx);
    }

    public static boolean matchesPhoneNumberRegEx(String toValidate) {
        return toValidate.matches(phoneNumRegEx);
    }
}