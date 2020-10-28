/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * @author Nicolas Penagos Montoya
 * nicolas.penagosm98@gmail.com
 * @author Valentina Zapata Zapata
 * valzapataz@gmail.com
 * *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.client_aliens_vs_humans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.client_aliens_vs_humans.events.OnMessageListener;
import com.example.client_aliens_vs_humans.tcpmodel.Generic;
import com.example.client_aliens_vs_humans.tcpmodel.PlayerNumber;
import com.example.client_aliens_vs_humans.tcpmodel.Star;
import com.google.gson.Gson;

/*
 * This class will represent the connection activity.
 */
public class MainActivity extends AppCompatActivity implements OnMessageListener {

    // -------------------------------------
    // Global assets
    // -------------------------------------
    private TCPConnection tcp;

    // -------------------------------------
    // Global assets
    // -------------------------------------
    private int player;

    // -------------------------------------
    // XML references
    // -------------------------------------
    private Button connectButton;
    private Star star;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gson = new Gson();

        connectButton = findViewById(R.id.connectButton);

        connectButton.setOnClickListener((v) -> {

            tcp = TCPConnection.getInstance();
            tcp.setObserver(this);
            tcp.start();

        });

    }

    @Override
    public void onMessage(String msg) {
        //runOnUiThread(()->Toast.makeText(this, msg, Toast.LENGTH_SHORT).show());
        Generic generic = gson.fromJson(msg, Generic.class);

        switch (generic.type) {

            case "Star":

                Star currentStar = gson.fromJson(msg, Star.class);
                star = currentStar;

                break;

            case "PlayerNumber":

                PlayerNumber playerNumber = gson.fromJson(msg, PlayerNumber.class);
                player = playerNumber.getPlayerNumber();

                runOnUiThread(

                        () -> {

                            Intent i = new Intent(this, ControllerActivity.class);
                            i.putExtra("player", player);
                            startActivity(i);
                            finish();

                        }

                );

                break;

        }


    }

}