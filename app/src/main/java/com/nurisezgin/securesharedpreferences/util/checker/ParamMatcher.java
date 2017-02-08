package com.nurisezgin.securesharedpreferences.util.checker;

/**
 * Created by nurisezgin on 07/02/2017.
 */

public interface ParamMatcher<T> {

    void matches(T param) throws InValidContentException;

}
