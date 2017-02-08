# SecureSharedPref
[![Build Status](https://travis-ci.org/Redvvolf/SecureSharedPref.svg?branch=master)](https://travis-ci.org/Redvvolf/SecureSharedPref)

## This library store data as secured. Very easy to implement your project.

First of all, create own crypto algorithm for what do you need.
```java
    public class SampleCrypto implements ICryptography {

        @Override
        public String encode(String raw) {
            return null;
        }

        @Override
        public String decode(String encoded) {
            return null;
        }
    }
```
After that, create properties about 'SharedPreferencesProvider'
```java
    public class SampleProperties implements SharedPreferencesProperties {

        private Context context;

        public SampleProperties(Context context) {
            this.context = context;
        }

        @Override
        public Context getApplicationContext() {
            return context;
        }

        @Override
        public String getPreferencesName() {
            return "SamplePref";
        }

        @Override
        public ICryptography getCryptography() {
            return new SampleCrypto();
        }
    }
```
After all
```
        SharedPreferencesProvider provider = 
                new SharedPreferencesProvider(new SampleProperties(context));
        provider.put(...) // or
        provider.get(...)
```