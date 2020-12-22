import java.util.Scanner;

public class ConnectFour {

    public static void printBoard(char[][] array) {
        // ex: 2 by 3 board -> row:0, and row 1 will both have 3 columns
        for (int i = array.length - 1; i >= 0; i--) {//This for loop will help print the the board top down
            for (int j = 0; j < array[0].length; j++) {//inspired by Brittney
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
        return;
    }

    public static void initializeBoard(char[][] array) {
        for (int i = array.length - 1; i >= 0; i--) {//Will add '-' to each spot in the board representing empty space
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = ('-');
            }
        }
    }

    public static int insertChip(char[][] array, int col, char chipType) {
        int i = 0;
        for (i = 0; i <= array.length - 1; i++) {
            if (array[i][col] == '-') {
                array[i][col] = chipType;//will add the appropriate chip in the place of '-'
                break;
            }
        }
        return i;
    }

    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {
        int i = 0;
        int rowCount = 0;//stores the counts horizontally
        for (i = 0; i < array.length; i++) {//for loop for the row.
            if (array[i][col] == chipType) {
                rowCount++;
                if (rowCount == 4) {//horizontally if there are 4 of the same chip type in a row
                    return true;//connects to the main and prints out a winning statement
                }
            } else {
                rowCount = 0;//start again from 0
            }

        }
        int colCount = 0;//stores the counts vertically
        for (i = 0; i < array[0].length; i++) {//for loop for the column. Similar to the row for loop above
            if (array[row][i] == chipType) {
                colCount++;
                if (colCount == 4) {//vertically if there are 4 of the same chip type in a row
                    return true;//connects to the main and prints out a winning statement
                }
            } else {
                colCount = 0;//start again from 0
            }
        }
        return false;
    }

    public static boolean tieOrNot (char[][]array, int col, int row, char chipType){
        //This method is used to print out a draw if no one wins
        int i = 0;
        int count = 0;//stores the number of empty slots in the board from the for loop
        for (i = 0; i < array.length; i++) {//keeping count of '-' on the board
            for(int j = 0; j < array[0].length; j++)
            if (array[i][j] == '-' ) {
                count++;

            }
        }
         if (count == 0)//if the count restarts back to 0 and there are no more empty '-' than enter this if statement
         {
             System.out.println("Draw. Nobody wins.");
             return true;
         }
         else{
             return false;
         }
    }





    public static void main (String[] args){

        //variable initialization/declaration
        Scanner scanner = new Scanner(System.in);
        int heightInput;//how long the column is
        int lengthInput;//how long the row is
        boolean gameStart = true;
        int playerTurn = 0;//keeps track of whose has the next turn in the game
        int player2InputCol;//column input for player 2
        int player1InputCol;//column input for player 1
        int store;//represents row

        System.out.print("What would you like the height of the board to be?");//ask user for input for height
        heightInput = scanner.nextInt();

        System.out.print(" What would you like the length of the board to be?");//ask user for input for length
        lengthInput = scanner.nextInt();

        char[][] board = new char[heightInput][lengthInput];
        initializeBoard(board);
        printBoard(board);//print the empty board

        System.out.println("Player 1: x");//chip type
        System.out.println("Player 2: o");//chip type

        while (gameStart){
            if(playerTurn % 2 == 0){//player1 input will be asked at even indices
                System.out.print("Player 1: Which column would you like to choose? ");
                player1InputCol = scanner.nextInt();

               store = insertChip(board,player1InputCol,'x');//inspired by Angela
               printBoard(board);//print the board with the chip type on the board

               if (checkIfWinner(board, player1InputCol, store, 'x')){
                   System.out.println("Player 1 won the game!");
                   break;
               }
               else if (tieOrNot(board, player1InputCol, store, 'x')){//if there is a draw
                    break;
               }

            }
            else {//player2 input will be asked at odd indices
                System.out.print("Player 2: Which column would you like to choose?");
                player2InputCol = scanner.nextInt();

                store = insertChip(board,player2InputCol,'o');
                printBoard(board);//print the board with the chip type on the board

                if (checkIfWinner(board, player2InputCol, store, 'o')) {
                    System.out.println("Player 2 won the game!");
                    break;
                }
                else if (tieOrNot(board, player2InputCol, store, 'o')){//if there is a draw
                    break;
                }
            }
            playerTurn++;

        }





    }

}
