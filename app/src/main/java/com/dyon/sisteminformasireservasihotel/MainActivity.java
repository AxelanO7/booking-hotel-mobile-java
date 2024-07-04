package com.dyon.sisteminformasireservasihotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnRoom, btnBooking, btnGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBooking = findViewById(R.id.btnBooking);
        btnRoom = findViewById(R.id.btnRoom);
        btnGuest = findViewById(R.id.btnGuest);

        btnBooking.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, BookingActivity.class);
            startActivity(i);
        });

        btnRoom.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, RoomActivity.class);
            startActivity(i);
        });

        btnGuest.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, GuestActivity.class);
            startActivity(i);
        });
    }
}
