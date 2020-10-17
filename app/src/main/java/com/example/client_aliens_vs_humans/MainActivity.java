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

import com.example.client_aliens_vs_humans.events.OnMessageListener;

public class MainActivity extends AppCompatActivity implements OnMessageListener {

    private TCPConnection tcp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tcp = TCPConnection.getInstance();
        tcp.setObserver(this);
        tcp.start();

    }

    @Override
    public void onMessage(String msg) {

    }
}