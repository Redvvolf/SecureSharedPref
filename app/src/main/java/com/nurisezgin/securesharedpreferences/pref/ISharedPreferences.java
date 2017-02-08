package com.nurisezgin.securesharedpreferences.pref;

import com.nurisezgin.securesharedpreferences.util.checker.InValidContentException;

/**
 * Created by nurisezgin on 07/02/2017.
 */

public interface ISharedPreferences {

    String get(String key) throws InValidContentException;

    boolean put(String key, String value) throws InValidContentException;

    boolean remove(String key) throws InValidContentException;

    void wipe();

}
