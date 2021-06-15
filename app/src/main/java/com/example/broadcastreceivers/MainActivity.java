package com.example.broadcastreceivers;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.broadcastreceivers.receivers.AirplaneModeChangeReceiver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();



    @BindView(R.id.broadcastButton)
    TextView broadcastButton;
    @BindView(R.id.AbortbroadcastButton)
    TextView AbortbroadcastButton;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    AirplaneModeChangeReceiver receiver;
    IntentFilter intentFilter;
    String register = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("Braodcast Receivers");
        setSupportActionBar(toolbar);

        receiver = new AirplaneModeChangeReceiver();
        intentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);

    }

    // send Broadcast
    @OnClick(R.id.broadcastButton)
    public void sendBroadCast() {
        registerReceiver(receiver, intentFilter);
        register = "register";
        Toast.makeText(this, "Broadcast Receiver registered", Toast.LENGTH_SHORT).show();

    }

    /// Abort Braodcast
    @OnClick(R.id.AbortbroadcastButton)
    public void sendAbortBroadCast() {
        if (register == "") {
            Toast.makeText(this, "Register Broadcast First", Toast.LENGTH_SHORT).show();
        } else{
            unregisterReceiver(receiver);
        Toast.makeText(this, "Stop a Broadcast Receiver", Toast.LENGTH_SHORT).show();
        register = "";
    }
    }
}
