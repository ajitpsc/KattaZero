package com.example.android.kattazero;

import android.view.View;

/**
 * Created by ajit on 19/8/17.
 */

public interface KattaZeroListener {
    public void onCellClick(int posX, int posY, PlayerSymbol p, View view);
    public void onCellClickUpdateLast(int posX, int posY, PlayerSymbol p, View view);
    public void onGameOver(PlayerSymbol winner);
    public void onRemoveClick(int posX , int posY,View view);
    public void onGameReset();
}
