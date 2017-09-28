package  com.example.android.kattazero;

import android.content.Context;
import android.util.Log;
import android.view.View;

import static com.example.android.kattazero.PlayerSymbol.EMPTY_PLAYER;
import static com.example.android.kattazero.PlayerSymbol.ERROR_PLAYER;
import static com.example.android.kattazero.PlayerSymbol.KATTA_PLAYER;
import static com.example.android.kattazero.PlayerSymbol.ZERO_PLAYER;
import static com.example.android.kattazero.cellState.EMPTY;
import static com.example.android.kattazero.cellState.KATTA;
import static com.example.android.kattazero.cellState.ZERO;


public class KattaZero {
    //check if comment updated
    private int ajit=0;
    private final int SIZE = 3;
    private final int INVALID_LOCATION = -1;
    public int lastPosX = INVALID_LOCATION;
    public int lastPosY = INVALID_LOCATION;
    private PlayerSymbol lastFilledPlayerSymbol=EMPTY_PLAYER;
    private View lastView=null;

    KattaZeroListener kattaZeroListener  ;
    public PlayerSymbol winner = EMPTY_PLAYER;
    public PlayerSymbol currentPlayer = ZERO_PLAYER;

    public void setKattaZeroListener(KattaZeroListener k){
        this.kattaZeroListener=k;
    }


    private cellState[][] board = {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
    };

    private void changePlayer() {
        currentPlayer = (currentPlayer == ZERO_PLAYER) ? KATTA_PLAYER : ZERO_PLAYER;
    }



    /*
     * Constructor for KattaZero
     * It do Nothing.
     */
    public KattaZero() {
        Log.v("Ajit ", "Katta Zero Initiaized");
    }

    private void updateLastClickedAt(int x , int y,PlayerSymbol p,View view){
        lastPosX=x;
        lastPosY=y;
        lastFilledPlayerSymbol=p;
        lastView=view;
    }

    private boolean isCellEmptyAt(int x, int y){
        if(board[x][y]==cellState.EMPTY){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isPositionValid(int x ,int y){
        if(x>=0 && x<SIZE && y>=0 && y<SIZE ){
            return true;
        }
        else{
            Log.e("AJit" ,"Illegal Position");
            return  false;
        }
    }

   /*
    *This Method is called when user Clicks on board cell.
    */

    public void clickBoardAt(int posX, int posY, Context context, View view) {

        //Check if supplied position is , Do nothing if position is not Valid.

        if(!isPositionValid(posX ,posY)){
            return;
        }

        //Case When User Clicks on Empty Board Cell
        if(isCellEmptyAt(posX,posY)){
            Log.v("Ajit","Yes Cell is Empty");
            kattaZeroListener.onCellClickUpdateLast(lastPosX,lastPosY, lastFilledPlayerSymbol,lastView);
            PlayerSymbol filledPlayerSymbol=fillBoard(posX,posY);
            kattaZeroListener.onCellClick(posX,posY, filledPlayerSymbol,view);
            ;
            updateLastClickedAt(posX,posY,filledPlayerSymbol,view);

            if(checkIfGameOver()) {
                kattaZeroListener.onGameOver(winner);
            }
            changePlayer();
            return;

        }
        else{
            Log.v("Ajit","No Cell is not Empty");
            if(isLastFilled(posX,posY)){
                unfillBoard(posX,posY);
                changePlayer();
                updateLastClickedAt(INVALID_LOCATION,INVALID_LOCATION,ERROR_PLAYER,null);
                kattaZeroListener.onRemoveClick(posX,posY,view);
            }
        }

    }

    private boolean isLastFilled(int x ,int y){
        if(lastPosX ==x && lastPosY ==y){
            return true;
        }
        else{
            return false;
        }

    }

    private PlayerSymbol fillBoard(int posX, int posY){
        if(currentPlayer == KATTA_PLAYER){
            board[posX][posY]=KATTA;
            return currentPlayer;
        }
        else if(currentPlayer == ZERO_PLAYER){
            board[posX][posY]=ZERO;
            return currentPlayer;
        }
        else {
            Log.e("Ajit" , "Check current Player.");
            return ERROR_PLAYER;
        }
    }

    private void unfillBoard(int posX,int posY){
        board[posX][posY]=EMPTY;
    }



    public void resetBoard() {
        winner=EMPTY_PLAYER;
        lastPosY=INVALID_LOCATION;
        lastPosX=INVALID_LOCATION;
        lastFilledPlayerSymbol=ERROR_PLAYER;
        lastView=null;
        currentPlayer=ZERO_PLAYER;
        for(int i=0;i<3; i++) {
            for(int j=0;j<3;j++) {
                board[i][j]=EMPTY;
            }
        }
        kattaZeroListener.onGameReset();
    }

    private boolean checkIfGameOver() {

        if(checkIfWinner()) {
            return true;
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.println(board[i][j]);
                if (board[i][j] == EMPTY) {
                    //System.out.println("here 2");
                    return false;
                }
            }
        }

        return true;
    }



    /*
     * Method to be called after setting a value.
     * It sets winner.
     */
    private boolean checkIfWinner() {
        cellState cellStateOfCurrentPlayer;
        if(currentPlayer ==KATTA_PLAYER){
            cellStateOfCurrentPlayer=cellState.KATTA;
        }
        else if(currentPlayer ==ZERO_PLAYER){
            cellStateOfCurrentPlayer= ZERO;
        }
        else {
            Log.e("Ajit" ,"Wrong Current PLayer in CheckWInnerFUnction");
            return false;
        }

        for(int i =0; i<SIZE ;i++) {
            if(board[i][0]==cellStateOfCurrentPlayer &&board[i][1]==cellStateOfCurrentPlayer &&board[i][2]==cellStateOfCurrentPlayer ) {
                winner=currentPlayer;
                return true;
            }
            if(board[0][i]==cellStateOfCurrentPlayer &&board[1][i]==cellStateOfCurrentPlayer &&board[2][i]==cellStateOfCurrentPlayer ) {
                winner=currentPlayer;
                return true;
            }

        }
        if(board[0][0]==cellStateOfCurrentPlayer && board[1][1]==cellStateOfCurrentPlayer &&board[2][2]==cellStateOfCurrentPlayer) {
            winner=currentPlayer;
            return true;
        }
        if(board[2][0]==cellStateOfCurrentPlayer && board[1][1]==cellStateOfCurrentPlayer &&board[0][2]==cellStateOfCurrentPlayer) {
            winner=currentPlayer;
            return true;
        }
        return false;
    }

}