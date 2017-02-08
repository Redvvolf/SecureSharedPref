package com.nurisezgin.securesharedpreferences.pref;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.nurisezgin.securesharedpreferences.pref.crypto.ICryptography;
import com.nurisezgin.securesharedpreferences.util.checker.InValidContentException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by nurisezgin on 08/02/2017.
 */
@RunWith(AndroidJUnit4.class)
public class SharedPreferencesProviderInstrumentationTest {

    private final static String PREF_NAME = "sample";

    @Mock
    ICryptography mockCryptography;

    @Mock
    SharedPreferencesProperties mockProperties;

    Context ctx;

    SharedPreferencesProvider provider;

    @Before
    public void setUp() throws InValidContentException {
        ctx = InstrumentationRegistry.getTargetContext();
        System.setProperty("dexmaker.dexcache", ctx.getCacheDir().getPath());
        MockitoAnnotations.initMocks(this);
        initializeMockCryptography();
        initializeMockProperties();
        provider = new SharedPreferencesProvider(mockProperties);
        provider.wipe();
    }

    @After
    public void tearDown() {
        provider.wipe();
    }

    @Test (expected = InValidContentException.class)
    public void should_InitializeError() throws InValidContentException {
        SharedPreferencesProperties mockPropWithInValidName = mock(SharedPreferencesProperties.class);
        when(mockPropWithInValidName.getCryptography()).thenReturn(mockCryptography);
        when(mockPropWithInValidName.getApplicationContext()).thenReturn(ctx);
        when(mockPropWithInValidName.getPreferencesName()).thenReturn(null);
        new SharedPreferencesProvider(mockPropWithInValidName);
    }

    @Test
    public void should_GetCorrect() throws InValidContentException {
        final String key = "getKey";
        final String value = "getValue";
        provider.put(key, value);
        String actualValue = provider.get(key);
        assertThat(value, is(equalTo(actualValue)));
    }

    @Test
    public void should_PutCorrect() throws InValidContentException {
        final String key = "putKey";
        final String value = "putValue";
        boolean isPuttedCorrect = provider.put(key, value);
        assertThat(isPuttedCorrect, is(true));
    }

    @Test
    public void should_RemoveCorrect() throws InValidContentException {
        final String key = "removeKey";
        final String value = "removeValue";
        provider.put(key, value);
        provider.remove(key);
        String actualValue = provider.get(key);
        assertThat(actualValue, is(nullValue()));
    }

    @Test
    public void should_WipeCorrect() throws InValidContentException {
        final String key = "wipeKey";
        final String value = "wipeValue";
        provider.put(key, value);
        provider.wipe();
        String actualValue = provider.get(key);
        assertThat(actualValue, is(nullValue()));
    }

    private void initializeMockProperties() {
        when(mockProperties.getApplicationContext()).thenReturn(ctx);
        when(mockProperties.getCryptography()).thenReturn(mockCryptography);
        when(mockProperties.getPreferencesName()).thenReturn(PREF_NAME);
    }

    private void initializeMockCryptography() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                String raw = (String) invocation.getArguments()[0];
                return raw;
            }
        }).when(mockCryptography).encode(anyString());
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                String encoded = (String) invocation.getArguments()[0];
                return encoded;
            }
        }).when(mockCryptography).decode(anyString());
    }

}