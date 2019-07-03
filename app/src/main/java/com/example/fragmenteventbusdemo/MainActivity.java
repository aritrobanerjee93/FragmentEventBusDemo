package com.example.fragmenteventbusdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
    }

    public void onButton1Press(View view){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.details_container,new FirstFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    public void onButton2Press(View view){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.details_container,new SecondFragment());
        ft.addToBackStack(null);
        ft.commit();

    }

    public void onButton3Press(View view){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = fm.findFragmentById(R.id.details_container);
        ft.remove(fragment);
        ft.addToBackStack(null);
        ft.commit();

    }

    public void onButton4Press(View view){
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void justToast(Pojo pojo){
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }
}
