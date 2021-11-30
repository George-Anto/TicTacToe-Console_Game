import java.util.Scanner;

public class HumanPlayer extends Player{

    public HumanPlayer() {
        super();
    }

    @Override
    public void setName(String playerNumber){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Player " + playerNumber + " nickname: ");
        System.out.println("(Press Enter for default player name)");
        String aName = scanner.nextLine().trim();
        if (aName.equals(""))
            super.setName(playerNumber);
        else
            super.name = aName;
    }

    @Override
    public void makeMove(Board aBoard){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please give coordinates: ");
        String answer = scanner.nextLine();

        isBoardReset(aBoard, answer);

        String[] answers = answer.split(",");
        String stringXCoord = "-1";
        String stringYCoord = "-1";

        for (int i = 0; i < answers.length; i++){
            if (i == 0)
                stringYCoord = answers[0];
            if (i == 1)
                stringXCoord = answers[1];
        }

        try {
            this.currentXmove = aBoard.getXDIMENSION() - Integer.parseInt(stringXCoord);
            this.currentYmove = Integer.parseInt(stringYCoord) - 1;
        } catch (NumberFormatException e){
            this.currentXmove = -1;
            this.currentYmove = -1;
        }
    }

    private void isBoardReset(Board aBoard, String answer) {
        if (answer.equals("cls")){
            aBoard.clearBoardLayout();
            aBoard.showBoardLayout();
        }
    }
}
