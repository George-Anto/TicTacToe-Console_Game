public class Board {

    private final String[][] boardLayout;
    private final int XDIMENSION = 3;
    private final int YDIMENSION = 3;

    public Board(){
        this.boardLayout = new String[XDIMENSION][YDIMENSION];
        for (int i = 0; i < XDIMENSION; i++){
            for (int j = 0; j < YDIMENSION; j++){
                this.boardLayout[i][j] = "-";
            }
        }
    }

    public String[][] getBoardLayout() {
        return this.boardLayout;
    }

    public int getXDIMENSION() {
        return XDIMENSION;
    }

    public int getYDIMENSION() {
        return YDIMENSION;
    }

    public void showBoardLayout(){

        for(int i = 0; i < boardLayout.length; i++) {
            for(int j = 0; j < boardLayout[i].length; j++) {
                System.out.print(boardLayout[i][j] + "  ");
            }
            if (i < boardLayout.length - 1)
                System.out.println("\n");
        }
        System.out.println();
        System.out.println("_______");
    }

    public boolean drawPlayersMove(String marker, int x, int y){

        if ((x >= 0 && x < XDIMENSION) && (y >= 0 && y < YDIMENSION)){
            if(this.boardLayout[x][y].equals("-")){
                this.boardLayout[x][y] = marker;
                return true;
            }
        }
        return false;
    }

    public void clearBoardLayout(){
        for (int i = 0; i < XDIMENSION; i++){
            for (int j = 0; j < YDIMENSION; j++){
                this.boardLayout[i][j] = "-";
            }
        }
    }
}
