import java.util.Random;

public class PCPlayer extends Player{

    public PCPlayer() {
        super();
        super.name = "NPC";
    }

    @Override
    public void makeMove(Board theBoard) throws InterruptedException {

        Random random = new Random();
        //Generates a number from 0.0 to 1.0
        double randomDelay = random.nextDouble();
        //Make the delay a bit longer (* 1,2) and in ms (* 1000)
        randomDelay = randomDelay * 1200;

        //The param needs to be of type long and in ms, hence the multiplication above
        Thread.sleep((long) randomDelay);

        if (this.makeAttackMove(theBoard))
            return;

        this.makeDefenseMove(theBoard);
    }

    private void makeRandomMove(Board theBoard){

        Random random = new Random();

        int randomI;
        int randomJ;

        do {
            randomI = random.nextInt(theBoard.getXDIMENSION());
            randomJ = random.nextInt(theBoard.getYDIMENSION());
        } while (!theBoard.getBoardLayout()[randomI][randomJ].equals("-"));

        this.currentXmove = randomI;
        this.currentYmove = randomJ;
    }

    private void makeCentralMove(Board theBoard){

        if (theBoard.getBoardLayout()[1][1].equals("-")){
            int centralI = (theBoard.getXDIMENSION() - 1) / 2;
            int centralJ = (theBoard.getYDIMENSION() - 1) / 2;

            Random random = new Random();
            int probability = random.nextInt(5);

            if (probability <= 2){
                this.currentXmove = (int) Math.floor(centralI);
                this.currentYmove = (int) Math.floor(centralJ);
                return;
            }
        }
        this.makeRandomMove(theBoard);
    }

    private void makeGuessMove(Board theBoard){

        String[][] theBoardLayout = theBoard.getBoardLayout();
        int xDimension = theBoard.getXDIMENSION();
        int yDimension = theBoard.getYDIMENSION();

        for (int i = 0; i < xDimension; i++) {
            for (int j = 0; j < yDimension; j++) {
                if (((i == 0 && j == 0) || (i == 0 && j == yDimension - 1) || (i == xDimension - 1 && j == 0) || (i == xDimension - 1 && j == yDimension - 1)) && theBoardLayout[i][j].equals("-")){

                    Random random = new Random();
                    int probability = random.nextInt(12);

                    if (probability <= 2){
                        this.currentXmove = i;
                        this.currentYmove = j;
                        return;
                    }
                }
            }
        }
        this.makeCentralMove(theBoard);
    }

    private boolean makeAttackMove(Board theBoard){

        String[][] theBoardLayout = theBoard.getBoardLayout();
        int xDimension = theBoard.getXDIMENSION();
        int yDimension = theBoard.getYDIMENSION();
        String theMarker = "O";

        int xCounter = 0;
        int yCounter = 0;
        int leftDiagonalCounter = 0;
        int rightDiagonalCounter = 0;

        int xPosition = 0;
        int yPosition = 0;

        boolean foundXPositionAttackOpportunity = false;
        boolean foundYPositionAttackOpportunity = false;

        for (int i = 0; i < xDimension; i++){
            for (int j = 0; j < yDimension; j++){
                if (theBoardLayout[i][j].equals(theMarker))
                    xCounter++;
                if (xCounter == 2){
                    xPosition = i;
                    foundXPositionAttackOpportunity = true;
                }
                if (theBoardLayout[j][i].equals(theMarker))
                    yCounter++;
                if (yCounter == 2){
                    yPosition = i;
                    foundYPositionAttackOpportunity = true;
                }
                if (i == j && theBoardLayout[i][j].equals(theMarker))
                    leftDiagonalCounter++;
                if (i == yDimension - 1 - j && theBoardLayout[i][j].equals(theMarker))
                    rightDiagonalCounter++;
            }
            xCounter = 0;
            yCounter = 0;
        }

        if (foundXPositionAttackOpportunity){
            for (int j = 0; j < yDimension; j++){
                if (theBoardLayout[xPosition][j].equals("-")){
                    this.currentXmove = xPosition;
                    this.currentYmove = j;
                    return true;
                }
            }
        }else if (foundYPositionAttackOpportunity){
            for (int i = 0; i < xDimension; i++){
                if (theBoardLayout[i][yPosition].equals("-")){
                    this.currentXmove = i;
                    this.currentYmove = yPosition;
                    return true;
                }
            }
        }else if (leftDiagonalCounter >= 2){
            for (int i = 0; i < xDimension; i++) {
                for (int j = 0; j < yDimension; j++) {
                    if (i == j && theBoardLayout[i][j].equals("-")){
                        this.currentXmove = i;
                        this.currentYmove = j;
                        return true;
                    }
                }
            }
        }else if (rightDiagonalCounter >= 2){
            for (int i = 0; i < xDimension; i++) {
                for (int j = 0; j < yDimension; j++) {
                    if (i == yDimension - 1 - j && theBoardLayout[i][j].equals("-")){
                        this.currentXmove = i;
                        this.currentYmove = j;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void makeDefenseMove(Board theBoard){

        String[][] theBoardLayout = theBoard.getBoardLayout();
        int xDimension = theBoard.getXDIMENSION();
        int yDimension = theBoard.getYDIMENSION();
        String opponentsMarker = "X";

        int xCounter = 0;
        int yCounter = 0;
        int leftDiagonalCounter = 0;
        int rightDiagonalCounter = 0;

        int xPosition = 0;
        int yPosition = 0;

        boolean foundXPositionThreat = false;
        boolean foundYPositionThreat = false;

        for (int i = 0; i < xDimension; i++){
            for (int j = 0; j < yDimension; j++){
                if (theBoardLayout[i][j].equals(opponentsMarker))
                    xCounter++;
                if (xCounter >= 2){
                    xPosition = i;
                    foundXPositionThreat = true;
                }
                if (theBoardLayout[j][i].equals(opponentsMarker))
                    yCounter++;
                if (yCounter >= 2){
                    yPosition = i;
                    foundYPositionThreat = true;
                }
                if (i == j && theBoardLayout[i][j].equals(opponentsMarker))
                    leftDiagonalCounter++;
                if (i == yDimension - 1 - j && theBoardLayout[i][j].equals(opponentsMarker))
                    rightDiagonalCounter++;
            }
            xCounter = 0;
            yCounter = 0;
        }

        boolean isMoveMade = false;

        if (foundXPositionThreat){
            for (int j = 0; j < yDimension; j++){
                if (theBoardLayout[xPosition][j].equals("-")){
                    this.currentXmove = xPosition;
                    this.currentYmove = j;
                    isMoveMade = true;
                }
            }
        }
        if (foundYPositionThreat && !isMoveMade){
            for (int i = 0; i < xDimension; i++){
                if (theBoardLayout[i][yPosition].equals("-")){
                    this.currentXmove = i;
                    this.currentYmove = yPosition;
                    isMoveMade = true;
                }
            }
        }
        if (leftDiagonalCounter >= 2 && !isMoveMade){
            for (int i = 0; i < xDimension; i++) {
                for (int j = 0; j < yDimension; j++) {
                    if (i == j && theBoardLayout[i][j].equals("-")){
                        this.currentXmove = i;
                        this.currentYmove = j;
                        isMoveMade = true;
                    }
                }
            }
        }
        if (rightDiagonalCounter >= 2 && !isMoveMade){
            for (int i = 0; i < xDimension; i++) {
                for (int j = 0; j < yDimension; j++) {
                    if (i == yDimension - 1 - j && theBoardLayout[i][j].equals("-")){
                        this.currentXmove = i;
                        this.currentYmove = j;
                        isMoveMade = true;
                    }
                }
            }
        }
        if (!isMoveMade) {
            this.makeGuessMove(theBoard);
        }
    }
}
