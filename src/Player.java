public abstract class Player {

    protected String name;
    protected static int totalGames = 0;
    protected int gamesWon;
    protected static int gamesTied = 0;
    protected int currentXmove;
    protected int currentYmove;

    public Player() {
        this.name = "";
        this.gamesWon = 0;
    }

    public void setName(String playerNumber){ this.name = "Player" + playerNumber; }

    public String getName(){ return this.name; }

    public static int getTotalGames(){ return totalGames; }

    public int getGamesWon(){ return this.gamesWon; }

    public static int getGamesTied(){ return gamesTied; }

    public  int getCurrentXmove(){ return this.currentXmove; }

    public  int getCurrentYmove(){ return this.currentYmove; }

    public abstract void makeMove(Board aBoard) throws InterruptedException;

    public static void setTotalGames(int theTotalGames){ totalGames = theTotalGames; }

    public void setGamesWon(int theGamesWon){ this.gamesWon = theGamesWon; }

    public static void setGamesTied(int theGamesTied){ gamesTied = theGamesTied; }

}
