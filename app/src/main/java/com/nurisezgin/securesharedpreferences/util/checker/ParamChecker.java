package com.nurisezgin.securesharedpreferences.util.checker;

/**
 * Created by nurisezgin on 07/02/2017.
 */

public class ParamChecker {

    public static <T> void check(
            T param, ParamMatcher<T> matcher) throws InValidContentException {
        matcher.matches(param);
    }

    public static ParamMatcher<String> isNull() {
        return new ParamMatcher<String>() {
            @Override
            public void matches(String param) throws InValidContentException {
                if (param == null) {
                    throw new InValidContentException("String was null");
                }
            }
        };
    }

    public static ParamMatcher<String> isEmpty() {
        return new ParamMatcher<String>() {
            @Override
            public void matches(String param) throws InValidContentException {
                if (param.isEmpty()) {
                    throw new InValidContentException("String was empty");
                }
            }
        };
    }

    public static ParamMatcher<String> isNullOrEmpty() {
        return new ParamMatcher<String>() {
            @Override
            public void matches(String param) throws InValidContentException {
                isNull().matches(param);
                isEmpty().matches(param);
            }
        };
    }

}
