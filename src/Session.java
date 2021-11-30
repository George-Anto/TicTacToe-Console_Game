import java.util.Scanner;

public class Session {

    private final Player player1;
    private Player player2;
    private Board myBoard;
    private final int VICTORYPOINTS = 3;

    public Session() {
        this.player1 = new HumanPlayer();
        this.myBoard = new Board();
    }

    public void startSession() throws InterruptedException {

        boolean isMultiplayer = this.isMultiplayer();

        if (isMultiplayer)
            this.player2 = new HumanPlayer();
        else {
            this.player2 = new PCPlayer();
        }

        player1.setName("1");

        if (isMultiplayer)
            player2.setName("2");

        do{

            myBoard.showBoardLayout();

            while(true){

                do {
                    player1.makeMove(myBoard);

                }while (!myBoard.drawPlayersMove("X", player1.getCurrentXmove(), player1.getCurrentYmove()));

                myBoard.showBoardLayout();
                String gameStatus = this.getGameStatus(myBoard, player1);

                if (gameStatus.equals("won")){

                    this.printVictoryStats(player1);
                    break;
                }else if (gameStatus.equals("tie")){

                    this.printTieStats();
                    break;
                }
                    do {
                        player2.makeMove(myBoard);

                    }while (!myBoard.drawPlayersMove("O", player2.getCurrentXmove(), player2.getCurrentYmove()));

                    myBoard.showBoardLayout();
                    gameStatus = this.getGameStatus(myBoard, player2);

                    if (gameStatus.equals("won")){

                        this.printVictoryStats(player2);
                        break;
                    }else if (gameStatus.equals("tie")){

                        this.printTieStats();
                        break;
                    }
            }
        }while(this.isGameRepeated());

        this.showOverallStats();
    }

    private boolean isGameRepeated(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want a rematch? (Y = yes, N = no)");
        String answer = scanner.nextLine();

        if(answer.equals("Y") || answer.equals("y")){
            this.myBoard = new Board();
            return true;
        }
        return false;
    }

    private String getGameStatus(Board theBoard, Player thePlayer){

        int currentXPosition = thePlayer.getCurrentXmove();
        int currentYPosition = thePlayer.getCurrentYmove();
        String[][] theBoardLayout = theBoard.getBoardLayout();
        String theMarker = theBoardLayout[currentXPosition][currentYPosition];
        int xDimention = theBoard.getXDIMENSION();
        int yDimention = theBoard.getYDIMENSION();

        int xCounter = 0;
        int yCounter = 0;
        int leftDiagonalCounter = 0;
        int rightDiagonalCounter = 0;

        for (int i = 0; i < xDimention; i++){
            for (int j = 0; j < yDimention; j++){
                if (i == currentXPosition && theBoardLayout[i][j].equals(theMarker))
                    xCounter++;
                if (j == currentYPosition && theBoardLayout[i][j].equals(theMarker))
                    yCounter++;
                if (i == j && theBoardLayout[i][j].equals(theMarker))
                    leftDiagonalCounter++;
                if (i == yDimention - 1 - j && theBoardLayout[i][j].equals(theMarker))
                    rightDiagonalCounter++;
            }
        }

        if (xCounter == VICTORYPOINTS || yCounter == VICTORYPOINTS ||
                leftDiagonalCounter == VICTORYPOINTS || rightDiagonalCounter == VICTORYPOINTS)
            return "won";

        boolean isBoardFull = true;

        for (int i = 0; i < xDimention; i++){
            for (int j = 0; j < yDimention; j++){
                if (theBoardLayout[i][j].equals("-")) {
                    isBoardFull = false;
                    break;
                }
            }
        }
        if (isBoardFull)
            return "tie";

        return "";
    }

    private void printVictoryStats(Player thePlayer){
        System.out.println(thePlayer.getName() + " has won this match!!");
        thePlayer.setGamesWon(thePlayer.getGamesWon() + 1);
        Player.setTotalGames(Player.getTotalGames() + 1);
        System.out.println(thePlayer.getName() + " has won " + thePlayer.getGamesWon() + " out of " + Player.getTotalGames());
        System.out.println("Games tied " + Player.getGamesTied());
    }

    private void printTieStats(){
        Player.setTotalGames(Player.getTotalGames() + 1);
        Player.setGamesTied(Player.getGamesTied() + 1);
        System.out.println("Its a tie!!");
    }

    private boolean isMultiplayer(){
        Scanner scanner = new Scanner(System.in);
        String answer;
        do {
            System.out.println("Do you want to play 1vs1 or against the pc?");
            System.out.println("Press 1 for 1vs1 or 2 for player vs pc.");
            answer = scanner.nextLine();
        }while (!answer.equals("1") && !answer.equals("2"));
        return answer.equals("1");
    }

    private void showOverallStats(){
        System.out.println(player1.getName() + " has won " + player1.getGamesWon() + " game(s).");
        System.out.println(player2.getName() + " has won " + player2.getGamesWon() + " game(s).");
        System.out.println("Ties: " + Player.getGamesTied());
        System.out.println("Total games: " + Player.getTotalGames());
    }
}
