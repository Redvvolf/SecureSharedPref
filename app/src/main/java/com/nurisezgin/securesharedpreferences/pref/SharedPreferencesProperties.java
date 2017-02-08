package com.nurisezgin.securesharedpreferences.pref;

import android.content.Context;

import com.nurisezgin.securesharedpreferences.pref.crypto.ICryptography;

/**
 * Created by nurisezgin on 08/02/2017.
 */

public interface SharedPreferencesProperties {

    Context getApplicationContext();

    String getPreferencesName();

    ICryptography getCryptography();

}
