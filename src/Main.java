import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        XOGame game = new XOGame();
        game.printBoard();

        game.setUpdateListener(new IUpdateListener() {
            @Override
            public void onUpdate(XOGame target, int state, int count, boolean correctInput) {
                target.printBoard();
                if(state != 0) {
                    System.out.println("Game over! Winner is " + state);
                }
            }
        });

        game.setGameOverListener(new IGameOverListener() {
            @Override
            public void onGameOver(XOGame target, int state) {
                System.out.println("Game over from GameOverListener!!! Winner is " + state);
            }
        });
        game.setXO(0, 0, XOGame.X);
        game.setXO(1, 0, XOGame.O);
        game.setXO(0, 1, XOGame.X);
        game.setXO(2, 0, XOGame.O);
        game.setXO(0, 2, XOGame.X);


        /*
        game.reset();
        game.printBoard();
        */



    }

}