import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Board myBoard = new Board();
//        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", myBoard);
//        Fen.load("7k/1PPPP3/8/8/8/8/3pppp1/K7 w - - 0 1", myBoard);

        int turns = 0;

        while (!myBoard.isGameOver()) {

            int valid_input = 0;

            while (valid_input == 0) { // keeps prompting the user to give valid input

                System.out.println(myBoard.toString());

                if (turns % 2 == 0) {
                    System.out.println("It is currently white\'s turn to play");
                } else {
                    System.out.println("It is currently black\'s turn to play");
                }


                System.out.println("What is your move? (format: [start row] [start col] [end row] [end col])");
                String input = scan.nextLine();
                int[] lst = new int[4];


                //            line 29 to 42 is to convert the input which is string type into a int[4] list
                String[] xxx = input.split(" ");
                if (xxx.length != 4) {
                    System.out.println("Invalid input");
                } else {
                    for (int i = 0; i < 4; i++) {
                        lst[i] = Integer.parseInt(xxx[i]);
                        if (lst[i] < 0 || lst[i] > 7) {
                            System.out.println("Invalid input");
                            break;
                        }
                    }
                }

                //            if can successfully move the piece, then move the piece and next turn
                if (myBoard.movePiece(lst[0], lst[1], lst[2], lst[3]) == true) {
                    myBoard.movePiece(lst[0], lst[1], lst[2], lst[3]);
                    valid_input = 1;
                    turns++;
                }

                if ((myBoard.getPiece(lst[2], lst[3]).getCharacter() == '\u2659' || myBoard.getPiece(lst[2], lst[3]).getCharacter() == '\u265f') && (lst[2] == 0 || lst[2] == 7)) {
                    myBoard.getPiece(lst[2], lst[3]).pawnPromotion(lst[2]);
                }
            }
        }

        if (turns % 2 == 1){
            System.out.println("White has won the game!");
        } else {
            System.out.println("Black has won the game!");
        }
    }
}
