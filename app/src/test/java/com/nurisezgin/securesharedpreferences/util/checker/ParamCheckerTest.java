package com.nurisezgin.securesharedpreferences.util.checker;

import org.junit.Test;

import static com.nurisezgin.securesharedpreferences.util.checker.ParamChecker.*;

/**
 * Created by nurisezgin on 07/02/2017.
 */
public class ParamCheckerTest {

    @Test (expected = InValidContentException.class)
    public void should_isNullCorrect() throws InValidContentException {
        check(null, isNull());
    }

    @Test (expected = InValidContentException.class)
    public void should_isEmptyCorrect() throws InValidContentException {
        check("", isEmpty());
    }

    @Test (expected = InValidContentException.class)
    public void should_isNullOrEmpty() throws InValidContentException {
        check(null, isNullOrEmpty());
    }

    @Test (expected = InValidContentException.class)
    public void should_isNullOrEmpty1() throws InValidContentException {
        check("", isNullOrEmpty());
    }

}