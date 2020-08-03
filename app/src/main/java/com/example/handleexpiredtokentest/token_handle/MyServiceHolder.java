package com.example.handleexpiredtokentest.token_handle;

import androidx.annotation.Nullable;

public class MyServiceHolder {
    MyService myService = null;



    @Nullable

    public MyService get() {

        return myService;

    }



    public void set(MyService myService) {

        this.myService = myService;

    }
}
