package com.nurisezgin.securesharedpreferences.pref.crypto;

/**
 * Created by nurisezgin on 08/02/2017.
 */

public interface ICryptography {

    String encode(String raw);

    String decode(String encoded);

}
