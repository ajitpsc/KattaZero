package com.example.android.kattazero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.x;
import static android.R.attr.y;
import static com.example.android.kattazero.PlayerSymbol.EMPTY_PLAYER;
import static com.example.android.kattazero.PlayerSymbol.KATTA_PLAYER;
import static com.example.android.kattazero.PlayerSymbol.ZERO_PLAYER;
import static com.example.android.kattazero.R.id.board_0_0;
import static com.example.android.kattazero.R.id.board_0_1;
import static com.example.android.kattazero.R.id.board_0_2;
import static com.example.android.kattazero.R.id.board_1_0;
import static com.example.android.kattazero.R.id.board_1_1;
import static com.example.android.kattazero.R.id.board_1_2;
import static com.example.android.kattazero.R.id.board_2_0;
import static com.example.android.kattazero.R.id.board_2_1;
import static com.example.android.kattazero.R.id.board_2_2;
import static com.example.android.kattazero.R.id.reset_button;

public class MainActivity extends AppCompatActivity{


//    private final char KATTA ='X';
//    private final char ZERO ='0';
//    private char player =ZERO;
//    static int  i;
    private KattaZero kattaZero = new KattaZero();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kattaZero.setKattaZeroListener(new KattaZeroListener(){

            @Override
            public void onCellClick(int posX, int posY, PlayerSymbol p, View view) {
                ImageView imageView =(ImageView)view;
                imageView.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

                switch(p)
                {
                    case KATTA_PLAYER:
                        imageView.setImageResource(R.drawable.katta);
                        break;
                    case ZERO_PLAYER:
                        imageView.setImageResource(R.drawable.zero);
                        break;
                    case EMPTY_PLAYER:
                        imageView.setBackgroundColor(getResources().getColor(android.R.color.white));
                        imageView.setImageResource(android.R.color.transparent);
                        break;

                    default:
                        Log.e("Ajit ", "Not RIght player Symbol to de shown!!");
                }

            }

            @Override
            public void onCellClickUpdateLast(int posX, int posY, PlayerSymbol p, View view) {
                if(view ==null){
                    return;
                }
                ImageView imageView =(ImageView)view;
                imageView.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                switch(p)
                {
                    case KATTA_PLAYER:
                        imageView.setImageResource(R.drawable.katta);
                        break;
                    case ZERO_PLAYER:
                        imageView.setImageResource(R.drawable.zero);
                        break;
                    case EMPTY_PLAYER:
                        imageView.setBackgroundColor(getResources().getColor(android.R.color.white));
                        imageView.setImageResource(android.R.color.transparent);
                        break;

                    default:
                        Log.e("Ajit ", "Last Update is not RIght");
                }
            }

            @Override
            public void onGameOver(PlayerSymbol winner) {

                if (winner ==PlayerSymbol.ZERO_PLAYER){
                    Toast.makeText(MainActivity.this ,"ZERO is Winner",Toast.LENGTH_LONG).show();
                }else if(winner ==PlayerSymbol.KATTA_PLAYER){
                    Toast.makeText(MainActivity.this ,"KATTA is Winner",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this ,"GAME Over . Draw!!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onRemoveClick(int posX, int posY,View view) {
                ImageView imageView =(ImageView)view;
                imageView.setBackgroundColor(getResources().getColor(android.R.color.white));
                imageView.setImageResource(android.R.color.transparent);
            }

            @Override
            public void onGameReset() {
                setContentView(R.layout.activity_main);
            }
        });

    }





    public void boardClick(View view){
        int x,y;
        switch (view.getId()){

            case board_0_0:
                x=0;
                y=0;
                kattaZero.clickBoardAt(x,y,MainActivity.this,view);
                break;
            case board_0_1:
                x=0;
                y=1;
                kattaZero.clickBoardAt(x,y,MainActivity.this,view);
                break;

            case board_0_2:
                x=0;
                y=2;
                kattaZero.clickBoardAt(x,y,MainActivity.this,view);
                break;

            case board_1_0:
                x=1;
                y=0;
                kattaZero.clickBoardAt(x,y,MainActivity.this,view);
                break;

            case board_1_1:
                x=1;
                y=1;
                kattaZero.clickBoardAt(x,y,MainActivity.this,view);
                break;

            case board_1_2:
                x=1;
                y=2;
                kattaZero.clickBoardAt(x,y,MainActivity.this,view);
                break;

            case board_2_0:
                x=2;
                y=0;
                kattaZero.clickBoardAt(x,y,MainActivity.this,view);
                break;

            case board_2_1:
                x=2;
                y=1;
                kattaZero.clickBoardAt(x,y,MainActivity.this,view);
                break;

            case board_2_2:
                x=2;
                y=2;
                kattaZero.clickBoardAt(x,y,MainActivity.this,view);
                break;


            case reset_button:
                Log.v("Ajit" ," CLicked on number reset_button ");
                kattaZero.resetBoard();
                break;


        }
    }



}
