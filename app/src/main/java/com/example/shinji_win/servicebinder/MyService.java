package com.example.shinji_win.servicebinder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service {

    private final MyBinder binder = new MyBinder();

    private int count;

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    public void addCount() {
        ++this.count;
    }

    public int getCount() {
        return this.count;
    }
}
