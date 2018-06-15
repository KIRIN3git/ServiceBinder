package com.example.shinji_win.servicebinder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MyService service;
    private boolean bound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!bound) { return; }

                service.addCount();
                int count = service.getCount();

                Toast.makeText(MainActivity.this, "count=" + count,Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        Intent intent = new Intent(this, MyService.class);
        bindService(intent, this.connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(this.bound){
            unbindService(this.connection);
            this.bound = false;
        }
    }

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {

            MyService.MyBinder binder = (MyService.MyBinder) iBinder;
            MainActivity.this.service = binder.getService();
            MainActivity.this.bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            MainActivity.this.bound = false;
        }
    };
}
