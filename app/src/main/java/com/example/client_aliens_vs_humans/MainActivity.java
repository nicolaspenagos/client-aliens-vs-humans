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
import android.widget.Button;
import android.widget.Toast;

import com.example.client_aliens_vs_humans.events.OnMessageListener;

public class MainActivity extends AppCompatActivity implements OnMessageListener {

    private TCPConnection tcp;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        tcp = TCPConnection.getInstance();
        tcp.setObserver(this);
        tcp.start();

        button.setOnClickListener(

                (v)->{
                    tcp.sendMessage("Hola desde android");
                }

        );

    }

    @Override
    public void onMessage(String msg) {
        runOnUiThread(()->Toast.makeText(this, msg, Toast.LENGTH_SHORT).show());
    }
}