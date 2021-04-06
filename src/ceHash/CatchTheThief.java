package ceHash;

import java.util.Scanner;

public class CatchTheThief {

    private static int WINNING_SCORE;
    private int policeScore;
    private int userScore;

    public void startChase(){
        WINNING_SCORE = 8;
        policeScore = 0;
        userScore = 0;
        Predictor predictor = new Predictor();
        while(!done()){
            Move lastMove = getLastMove();
            predictor.recordMove(lastMove);
            Move predictedMove = predictor.predict();
            updateScore(predictedMove, lastMove);
        }
        displayResult();
    }

    private boolean done(){
        return (policeScore == WINNING_SCORE || userScore  == WINNING_SCORE);
    }

    private Move getLastMove(){
        Scanner input = new Scanner(System.in);
        System.out.println("Next move L(left) or R(right): ");
        while(true){
            try{
                String nextMove = input.nextLine();
                nextMove = nextMove.substring(0,1);
                nextMove = nextMove.toLowerCase();
                if(nextMove.equals("l")) return Move.LEFT;
                if(nextMove.equals("r")) return Move.RIGHT;
                System.out.println("Invalid input");
                System.out.println("Input either Left or Right");
            } catch (Exception e) {
                System.out.println("Invalid input");
                System.out.println("Input either Left or Right");
                input.next();
                continue;
            }
        }
    }

    private void updateScore(Move prediction, Move userChoice){
        if(prediction == userChoice) {
            policeScore++;
            System.out.print("The prediction was correct. ");
            printScore();
        }
        else {
            userScore++;
            System.out.print("The prediction was incorrect. ");
            printScore();
        }
    }

    private void printScore(){
        System.out.println("Thief: " + userScore + " Police: " + policeScore);
    }

    private void displayResult(){
        if((policeScore == WINNING_SCORE)){
            System.out.println("The police outsmarted the thief and the goods have been returned!");
        } else {
            System.out.println("The thief outsmarted the police and has gotten away with the goods!");
        }
    }
}
