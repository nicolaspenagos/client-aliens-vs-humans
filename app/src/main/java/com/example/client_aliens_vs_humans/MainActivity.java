/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolas Penagos Montoya
 * nicolas.penagosm98@gmail.com
 *
 * @author Valentina Zapata Zapata
 * valzapataz@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.client_aliens_vs_humans;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.client_aliens_vs_humans.events.OnMessageListener;
import com.example.client_aliens_vs_humans.tcpmodel.Direction;
import com.google.gson.Gson;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements OnMessageListener, View.OnClickListener{

    private TCPConnection tcp;
    private Button upButton;
    private Button downButton;
    private Button rightBurron;
    private Button leftButton;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gson = new Gson();

        upButton = findViewById(R.id.upButton);
        downButton = findViewById(R.id.downButton);
        rightBurron = findViewById(R.id.rightButton);
        leftButton = findViewById(R.id.leftButton);

        tcp = TCPConnection.getInstance();
        tcp.setObserver(this);
        tcp.start();

        upButton.setOnClickListener(this);
        downButton.setOnClickListener(this);
        rightBurron.setOnClickListener(this);
        leftButton.setOnClickListener(this);

    }

    @Override
    public void onMessage(String msg) {
        runOnUiThread(()->Toast.makeText(this, msg, Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onClick(View view) {

        Direction direction;
        String json;
        char dir;

        switch (view.getId()){

            case R.id.upButton:

                direction = new Direction(UUID.randomUUID().toString(), Direction.UP, "Is the direction of the player on the matrix");
                json = gson.toJson(direction);

                tcp.sendMessage(json);

                break;

            case R.id.downButton:

                direction = new Direction(UUID.randomUUID().toString(), Direction.DOWN, "Is the direction of the player on the matrix");
                json = gson.toJson(direction);

                tcp.sendMessage(json);

                break;

            case R.id.rightButton:

                direction = new Direction(UUID.randomUUID().toString(), Direction.RIGHT, "Is the direction of the player on the matrix");
                json = gson.toJson(direction);

                tcp.sendMessage(json);

                break;

            case R.id.leftButton:

                direction = new Direction(UUID.randomUUID().toString(), Direction.LEFT, "Is the direction of the player on the matrix");
                json = gson.toJson(direction);

                tcp.sendMessage(json);

                break;
        }
    }
}