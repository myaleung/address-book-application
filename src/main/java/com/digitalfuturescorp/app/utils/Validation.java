package com.digitalfuturescorp.app.utils;

import com.digitalfuturescorp.app.Contact;

public abstract class Validation {
    private static final String emailRegEx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final String phoneNumRegEx = "^(((\\+44\\s?\\d{4}|\\(?0\\d{4}\\)?)\\s?\\d{3}\\s?\\d{3})|((\\+44\\s?\\d{3}|\\(?0\\d{3}\\)?)\\s?\\d{3}\\s?\\d{4})|((\\+44\\s?\\d{2}|\\(?0\\d{2}\\)?)\\s?\\d{4}\\s?\\d{4}))(\\s?\\#(\\d{4}|\\d{3}))?$";
    private static final String mainMenuOptionRegEx = "^([0-5])?$";
    private static final String exitMenuOptionRegEx = "^([0-1])?$";

    public static boolean isNull(String toValidate) {
        return toValidate == null;
    }

    public static boolean isNull(Contact toValidate) {
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

    public static boolean matchesMainMenuOptionRegEx(String toValidate) {
        return toValidate.matches(mainMenuOptionRegEx);
    }

    public static boolean matchesExitMenuOptionRegEx(String toValidate) {
        return toValidate.matches(exitMenuOptionRegEx);
    }
}