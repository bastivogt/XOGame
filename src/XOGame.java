import java.util.Arrays;

public class XOGame {

    private int[][] board = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0},
    };
    public final static int X = 1;
    public final static int O = 2;

    private int currentPlayer = 0;
    
    private int counter = 0;
    private int state = 0;


    public XOGame() {
        reset();
    }

    public void reset() {
        currentPlayer = 0;
        state = 0;
        counter = 0;
        for(int i = 0; i < 3; i ++) {
            for(int j = 0; j < 3; j ++) {
                board[i][j] = 0;
            }
        }

    }




    private IUpdateListener updateListener = null;


    public void setUpdateListener(IUpdateListener listener) {
        updateListener = listener;
    }

    public void removeUpdateListener() {
        updateListener = null;
    }


    private IGameOverListener gameOverListener = null;

    public void setGameOverListener(IGameOverListener listener) {
        gameOverListener = listener;
    }

    public void removeGameOverListener() {
        gameOverListener = null;
    }




    public void printBoard() {
        System.out.println("------------------");
        for(int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("------------------");
    }


    public int[][] getBoard() {
        return board;
    }


    public int getState() {
        return state;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean setXO(int row, int col, int player) {
        boolean result = false;
        if(counter < 9 && board[row][col] == 0 && currentPlayer != player) {
            board[row][col] = player;
            state = checkBoard();
            counter ++;
            currentPlayer = player;
            result = true;
        }
        if(updateListener != null) {
            updateListener.onUpdate(this, state, counter, result);
        }

        if((counter >= 9 && gameOverListener != null) || state != 0) {
            gameOverListener.onGameOver(this, state);
        }
        return result;
    }

    public int checkBoard() {
        // horizontal
        for(int i = 0; i < 3; i ++) {
            //System.out.println(Arrays.toString(board[i]));
            if(board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }
        }
        // vertikal
        for(int i = 0; i < 3; i ++) {
            //System.out.println(Arrays.toString(board[i]));
            if(board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }
        // diagonal (links oben nach rechts unten)
        if(board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }

        // diagonal (rechts unten nach links oben)
        if(board[2][0] != 0 && board[2][0] == board[1][1] && board[1][1] == board[0][2]) {
            return board[2][0];
        }
        return 0;
    }
}
