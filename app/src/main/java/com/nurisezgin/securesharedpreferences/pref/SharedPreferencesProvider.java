package com.nurisezgin.securesharedpreferences.pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.nurisezgin.securesharedpreferences.pref.crypto.ICryptography;
import com.nurisezgin.securesharedpreferences.util.checker.InValidContentException;

import java.util.Map;

import static com.nurisezgin.securesharedpreferences.util.checker.ParamChecker.check;
import static com.nurisezgin.securesharedpreferences.util.checker.ParamChecker.isNullOrEmpty;

/**
 * Created by nurisezgin on 08/02/2017.
 */

public class SharedPreferencesProvider implements ISharedPreferences {

    private SharedPreferencesProperties prop;
    private ICryptography cryptography;
    private SharedPreferences mSharedPreferences;

    public SharedPreferencesProvider(SharedPreferencesProperties prop) throws InValidContentException {
        this.prop = prop;
        this.cryptography = prop.getCryptography();
        check(prop.getPreferencesName(), isNullOrEmpty());
        mSharedPreferences = prop.getApplicationContext()
                .getSharedPreferences(prop.getPreferencesName(), Context.MODE_PRIVATE);
    }

    @Override
    public String get(String key) throws InValidContentException {
        check(key, isNullOrEmpty());
        String encodedKey = cryptography.encode(key);
        String encodedValue =  mSharedPreferences.getString(encodedKey, null);
        return cryptography.decode(encodedValue);
    }

    @Override
    public boolean put(String key, String value) throws InValidContentException {
        check(key, isNullOrEmpty());
        String encodedKey = cryptography.encode(key);
        String encodedValue = cryptography.encode(value);
        return mSharedPreferences.edit().putString(encodedKey, encodedValue).commit();
    }

    @Override
    public boolean remove(String key) throws InValidContentException {
        check(key, isNullOrEmpty());
        String encodedKey = cryptography.encode(key);
        return mSharedPreferences.edit().remove(encodedKey).commit();
    }

    @Override
    public void wipe() {
        Map<String, ?> entries = mSharedPreferences.getAll();
        for (String k : entries.keySet()) {
            mSharedPreferences.edit().remove(k)
                    .commit();
        }
    }

}
