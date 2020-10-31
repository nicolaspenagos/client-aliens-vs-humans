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

import com.example.client_aliens_vs_humans.events.OnMessageListener;
import com.example.client_aliens_vs_humans.model.Player;
import com.example.client_aliens_vs_humans.tcpmodel.Direction;
import com.example.client_aliens_vs_humans.tcpmodel.Generic;
import com.example.client_aliens_vs_humans.tcpmodel.Star;
import com.example.client_aliens_vs_humans.tcpmodel.Character;
import com.google.gson.Gson;

import java.util.UUID;

/*
 * This class will represents the controller of the each player.
 */
public class ControllerActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener, OnMessageListener {

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
    private ImageView starButton;

    // -------------------------------------
    // Global assets
    // -------------------------------------
    private TCPConnection tcp;
    private Gson gson;
    private Star star;

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
        starButton = findViewById(R.id.imageViewStar);

        player = getIntent().getExtras().getInt("player", 0);
        tcp = TCPConnection.getInstance();
        tcp.setObserver(this);

        gson = new Gson();

        walkerButton.setOnClickListener(this);
        shooterButton.setOnClickListener(this);
        bombButton.setOnClickListener(this);
        starButton.setOnClickListener(this);

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
        Direction direction;

        switch (view.getId()){

            case R.id.imageViewUpArrow:

                option = event.getAction();

                if(option == MotionEvent.ACTION_DOWN){

                    upArrowButton.setImageResource(R.drawable.up_arrow_pressed);
                    direction = new Direction(UUID.randomUUID().toString(), Direction.UP, "Direction of the movement");
                    tcp.sendMessage(gson.toJson(direction));

                }else if(option == MotionEvent.ACTION_UP){
                    upArrowButton.setImageResource(R.drawable.up_arrow);
                }

                break;

            case R.id.imageViewDownArrow:

                option = event.getAction();

                if(option == MotionEvent.ACTION_DOWN){

                    downArrowButton.setImageResource(R.drawable.down_arrow_pressed);
                    direction = new Direction(UUID.randomUUID().toString(), Direction.DOWN, "Direction of the movement");
                    tcp.sendMessage(gson.toJson(direction));

                }else if(option == MotionEvent.ACTION_UP){
                    downArrowButton.setImageResource(R.drawable.down_arrow);
                }

                break;

            case R.id.imageViewRightArrow:

                option = event.getAction();

                if(option == MotionEvent.ACTION_DOWN){

                    rightArrowButton.setImageResource(R.drawable.right_arrow_pressed);
                    direction = new Direction(UUID.randomUUID().toString(), Direction.RIGHT, "Direction of the movement");
                    tcp.sendMessage(gson.toJson(direction));

                }else if(option == MotionEvent.ACTION_UP){
                    rightArrowButton.setImageResource(R.drawable.right_arrow);
                }

                break;

            case R.id.imageViewLeftArrow:

                option = event.getAction();

                if(option == MotionEvent.ACTION_DOWN){

                    leftArrowButton.setImageResource(R.drawable.left_arrow_pressed);
                    direction = new Direction(UUID.randomUUID().toString(), Direction.LEFT, "Direction of the movement");
                    tcp.sendMessage(gson.toJson(direction));

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

        Character character;

        switch (view.getId()){

            case R.id.imageViewWalker:

                if(player == Player.PLAYER1){

                    walkerButton.setImageResource(R.drawable.pressed_human_walker);
                    shooterButton.setImageResource(R.drawable.human_shooter);
                    bombButton.setImageResource(R.drawable.human_bomb);
                    character = new Character(UUID.randomUUID().toString(), Character.HUMAN_WALKER_PRESSED, "The character pressed by the player");
                    tcp.sendMessage(gson.toJson(character));

                }else if(player == Player.PLAYER2){

                    walkerButton.setImageResource(R.drawable.pressed_aliens_walker);
                    shooterButton.setImageResource(R.drawable.shooter_aliens);
                    bombButton.setImageResource(R.drawable.aliens_bomb);
                    character = new Character(UUID.randomUUID().toString(), Character.ALIEN_WALKER_PRESSED, "The character pressed by the player");
                    tcp.sendMessage(gson.toJson(character));

                }

                break;

            case R.id.imageViewShooter:

                if(player == Player.PLAYER1){

                    walkerButton.setImageResource(R.drawable.human_walker);
                    shooterButton.setImageResource(R.drawable.pressed_human_shooter);
                    bombButton.setImageResource(R.drawable.human_bomb);
                    character = new Character(UUID.randomUUID().toString(), Character.HUMAN_SHOOTER_PRESSED, "The character pressed by the player");
                    tcp.sendMessage(gson.toJson(character));

                }else if(player == Player.PLAYER2){

                    walkerButton.setImageResource(R.drawable.aliens_walker);
                    shooterButton.setImageResource(R.drawable.pressed_aliens_shooter);
                    bombButton.setImageResource(R.drawable.aliens_bomb);
                    character = new Character(UUID.randomUUID().toString(), Character.ALIEN_SHOOTER_PRESSED, "The character pressed by the player");
                    tcp.sendMessage(gson.toJson(character));

                }

                break;

            case R.id.imageViewBomb:

                if(player == Player.PLAYER1){

                    walkerButton.setImageResource(R.drawable.human_walker);
                    shooterButton.setImageResource(R.drawable.human_shooter);
                    bombButton.setImageResource(R.drawable.pressed_human_bomb);
                    character = new Character(UUID.randomUUID().toString(), Character.HUMAN_BOMB_PRESSED, "The character pressed by the player");
                    tcp.sendMessage(gson.toJson(character));

                }else if(player == Player.PLAYER2){

                    walkerButton.setImageResource(R.drawable.aliens_walker);
                    shooterButton.setImageResource(R.drawable.shooter_aliens);
                    bombButton.setImageResource(R.drawable.pressed_aliens_bomb);
                    character = new Character(UUID.randomUUID().toString(), Character.ALIEN_BOMB_PRESSED, "The character pressed by the player");
                    tcp.sendMessage(gson.toJson(character));

                }

                break;

            case R.id.imageViewStar:

                if(star.getOwner()==3){

                    star.setOwner(player);
                    tcp.sendMessage(gson.toJson(star));
                    starButton.setImageResource(R.drawable.star_pressed);

                }


                break;

        }

    }

    // -------------------------------------
    // Tcp methods
    // -------------------------------------
    @Override
    public void onMessage(String msg) {

        Generic generic = gson.fromJson(msg, Generic.class);

        switch (generic.type) {

            case "Star":

                Star currentStar = gson.fromJson(msg, Star.class);

                if(currentStar.getOwner()==Star.OWNED_STAR) {
                    starButton.setImageResource(R.drawable.star_pressed);
                }else{

                    star = currentStar;
                    starButton.setImageResource(R.drawable.star);

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