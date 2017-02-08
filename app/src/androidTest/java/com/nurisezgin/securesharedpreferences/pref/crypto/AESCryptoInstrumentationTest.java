package com.nurisezgin.securesharedpreferences.pref.crypto;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by nurisezgin on 08/02/2017.
 */
@RunWith(AndroidJUnit4.class)
public class AESCryptoInstrumentationTest {

    @Test
    public void should_EncodeCorrect(){
        final String sample = "sample";
        String encoded = new AESCrypto().encode(sample);
        assertThat(encoded, is(notNullValue()));
        assertThat(encoded, is(not(equalTo(sample))));
    }

    @Test
    public void should_DecodeCorrect(){
        final String sample = "sample";
        AESCrypto crypto = new AESCrypto();
        String encoded = crypto.encode(sample);
        String actualString = crypto.decode(encoded);
        assertThat(actualString, is(equalTo(sample)));
    }

}