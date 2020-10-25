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

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.client_aliens_vs_humans.model.Player;
import com.google.gson.Gson;

/*
 * This class will represents the controller of the each player.
 */
public class ControllerActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener{

    // -------------------------------------
    // XML references
    // -------------------------------------
    private ImageView walkerButton;
    private ImageView shooterButton;
    private ImageView bombButton;
    private ImageView upArrowButton;
    private ImageView downArrowButton;
    private ImageView rightArrowButton;
    private ImageView leftArrowButton;
    private ImageView putButton;

    // -------------------------------------
    // Global assets
    // -------------------------------------
    private TCPConnection tcp;
    private Gson gson;

    // -------------------------------------
    // Global variables
    // -------------------------------------
    private int player;

    // -------------------------------------
    // Android methods
    // -------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        walkerButton = findViewById(R.id.imageViewWalker);
        shooterButton = findViewById(R.id.imageViewShooter);
        bombButton = findViewById(R.id.imageViewBomb);
        upArrowButton = findViewById(R.id.imageViewUpArrow);
        downArrowButton = findViewById(R.id.imageViewDownArrow);
        rightArrowButton = findViewById(R.id.imageViewRightArrow);
        leftArrowButton = findViewById(R.id.imageViewLeftArrow);
        putButton = findViewById(R.id.imageViewPut);

        player = 0;

        walkerButton.setOnClickListener(this);
        shooterButton.setOnClickListener(this);
        bombButton.setOnClickListener(this);

        upArrowButton.setOnTouchListener(this);
        downArrowButton.setOnTouchListener(this);
        rightArrowButton.setOnTouchListener(this);
        leftArrowButton.setOnTouchListener(this);
        putButton.setOnTouchListener(this);

        loadImage();

    }

    // -------------------------------------
    // Buttons actions and feedback
    // -------------------------------------
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent event) {

        int option;

        switch (view.getId()){

            case R.id.imageViewUpArrow:

                option = event.getAction();

                if(option == MotionEvent.ACTION_DOWN){
                    upArrowButton.setImageResource(R.drawable.up_arrow_pressed);
                }else if(option == MotionEvent.ACTION_UP){
                    upArrowButton.setImageResource(R.drawable.up_arrow);
                }

                break;

            case R.id.imageViewDownArrow:

                option = event.getAction();

                if(option == MotionEvent.ACTION_DOWN){
                    downArrowButton.setImageResource(R.drawable.down_arrow_pressed);
                }else if(option == MotionEvent.ACTION_UP){
                    downArrowButton.setImageResource(R.drawable.down_arrow);
                }

                break;

            case R.id.imageViewRightArrow:

                option = event.getAction();

                if(option == MotionEvent.ACTION_DOWN){
                    rightArrowButton.setImageResource(R.drawable.right_arrow_pressed);
                }else if(option == MotionEvent.ACTION_UP){
                    rightArrowButton.setImageResource(R.drawable.right_arrow);
                }

                break;

            case R.id.imageViewLeftArrow:

                option = event.getAction();

                if(option == MotionEvent.ACTION_DOWN){
                    leftArrowButton.setImageResource(R.drawable.left_arrow_pressed);
                }else if(option == MotionEvent.ACTION_UP){
                    leftArrowButton.setImageResource(R.drawable.left_arrow);
                }

                break;

            case R.id.imageViewPut:

                option = event.getAction();

                if(option == MotionEvent.ACTION_DOWN){
                        putButton.setImageResource(R.drawable.put_pressed);
                }else if(option == MotionEvent.ACTION_UP){
                    putButton.setImageResource(R.drawable.put);
                }

                break;

        }
        return true;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.imageViewWalker:

                if(player == Player.PLAYER1){

                    walkerButton.setImageResource(R.drawable.pressed_human_walker);
                    shooterButton.setImageResource(R.drawable.human_shooter);
                    bombButton.setImageResource(R.drawable.human_bomb);

                }else if(player == Player.PLAYER2){

                    walkerButton.setImageResource(R.drawable.pressed_aliens_walker);
                    shooterButton.setImageResource(R.drawable.shooter_aliens);
                    bombButton.setImageResource(R.drawable.aliens_bomb);

                }

                break;

            case R.id.imageViewShooter:

                if(player == Player.PLAYER1){

                    walkerButton.setImageResource(R.drawable.human_walker);
                    shooterButton.setImageResource(R.drawable.pressed_human_shooter);
                    bombButton.setImageResource(R.drawable.human_bomb);

                }else if(player == Player.PLAYER2){

                    walkerButton.setImageResource(R.drawable.aliens_walker);
                    shooterButton.setImageResource(R.drawable.pressed_aliens_shooter);
                    bombButton.setImageResource(R.drawable.aliens_bomb);

                }

                break;

            case R.id.imageViewBomb:

                if(player == Player.PLAYER1){

                    walkerButton.setImageResource(R.drawable.human_walker);
                    shooterButton.setImageResource(R.drawable.human_shooter);
                    bombButton.setImageResource(R.drawable.pressed_human_bomb);

                }else if(player == Player.PLAYER2){

                    walkerButton.setImageResource(R.drawable.aliens_walker);
                    shooterButton.setImageResource(R.drawable.shooter_aliens);
                    bombButton.setImageResource(R.drawable.pressed_aliens_bomb);

                }

                break;

        }

    }

    // -------------------------------------
    // Logic methods
    // -------------------------------------
    public void loadImage(){

        if(player == Player.PLAYER1){

            walkerButton.setImageResource(R.drawable.human_walker);
            shooterButton.setImageResource(R.drawable.human_shooter);
            bombButton.setImageResource(R.drawable.human_bomb);

        }else if(player == Player.PLAYER2){

            walkerButton.setImageResource(R.drawable.aliens_walker);
            shooterButton.setImageResource(R.drawable.shooter_aliens);
            bombButton.setImageResource(R.drawable.aliens_bomb);

        }

    }

}