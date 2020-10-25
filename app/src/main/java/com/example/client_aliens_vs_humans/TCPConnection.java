/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolas Penagos Montoya
 * nicolas.penagosm98@gmail.com
 *
 * @author Valentina Zapata Zapata
 * valzapataz@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.client_aliens_vs_humans;

import android.util.Log;

import com.example.client_aliens_vs_humans.events.OnMessageListener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/*
 * In this class you will find TCP Connection using Singleton and Observer patterns.
 */
public class TCPConnection extends Thread{

    // -------------------------------------
    // Global variables
    // -------------------------------------
    private static TCPConnection instance; //Unique Instance
    private BufferedWriter writer;
    private OnMessageListener observer;
    private boolean killThread;

    // -------------------------------------
    // Constructor
    // -------------------------------------
    private TCPConnection(){

    }

    // -------------------------------------
    // Singleton pattern
    // -------------------------------------
    public static TCPConnection getInstance(){

        if(instance == null ){
            instance = new TCPConnection();
        }
        return instance;

    }

    // -------------------------------------
    // TCP
    // -------------------------------------
    public void run(){

        try {

            killThread = false;
            Socket socket = new Socket("192.168.20.36", 5000);

            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(!killThread){

                String msg = reader.readLine();

                if(msg==null)
                    break;
                observer.onMessage(msg);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(String msg){

        new Thread(

                ()->{

                    try {

                        writer.write(msg+"\n");
                        writer.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

        ).start();

    }

    // -------------------------------------
    // Getters and setters
    // -------------------------------------
    public BufferedWriter getWriter() {
        return writer;
    }

    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }

    public OnMessageListener getObserver() {
        return observer;
    }

    public void setObserver(OnMessageListener observer) {
        this.observer = observer;
    }

    public boolean isKillThread() {
        return killThread;
    }

    public void setKillThread(boolean killThread) {
        this.killThread = killThread;
    }

}
