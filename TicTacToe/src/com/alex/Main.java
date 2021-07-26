package com.alex;

import java.util.*;

public class Main {

    //global variables are used to check if the player has 3 turns in one of the winning patterns
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
        //2D array of characters to create the game board
        //3 rows with 2 additional ones for the lines separating them in-between
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '}, // 1, 2, 3
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}, //4, 5, 6
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}}; //7, 8, 9

        printGameBoard(gameBoard);

        while(true) {
            //Create a scanner to read the incoming values from a player
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your X-placement with an integer 1-9: \n");

            //reads in an integer from the player
            int playerPos = scan.nextInt();
            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)) {
                System.out.println("Position taken! Enter another integer 1-9: \n");
                playerPos = scan.nextInt();
            }
            placePiece(gameBoard, playerPos, "player");
            String result = checkWinner();

            //checks winner and result after each move
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }

            //random piece for the cpu
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(cpuPositions.contains(cpuPos) || playerPositions.contains(cpuPos)) {
                System.out.println("Position taken! Enter another integer 1-9: \n");
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);

            //checks winner and result after each move
            result = checkWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }

        }

    }

    public static void printGameBoard(char[][] gameBoard) {
        //for-loop to print out game board
        for(char[] row : gameBoard) {
            for(char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user) {

        char symbol = ' ';

        //since it's a string, must use .equals instead of ==
        if(user.equals("player")) {
            symbol  = 'X';
            playerPositions.add(pos);
        }
        else {
            symbol = 'O';
            cpuPositions.add(pos);
        }

        //switch case could be used instead of many if-else
        switch(pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;

            case 2:
                gameBoard[0][2] = symbol;
                break;

            case 3:
                gameBoard[0][4] = symbol;
                break;

            case 4:
                gameBoard[2][0] = symbol;
                break;

            case 5:
                gameBoard[2][2] = symbol;
                break;

            case 6:
                gameBoard[2][4] = symbol;
                break;

            case 7:
                gameBoard[4][0] = symbol;
                break;

            case 8:
                gameBoard[4][2] = symbol;
                break;

            case 9:
                gameBoard[4][4] = symbol;
                break;

            default:
                break;
        }
    }

    public static String checkWinner() {
        //all possible winning positions as rows
        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);

        //all possible winning positions as rows
        List leftColumn = Arrays.asList(1, 4, 7);
        List middleColumn = Arrays.asList(2, 5, 8);
        List lastColumn = Arrays.asList(3, 6, 9);

        //all possible winning positions as diagonals
        List diagonalRight = Arrays.asList(1, 5, 9);
        List diagonalLeft = Arrays.asList(3, 5, 7);

        List<List> winningPositions = new ArrayList<List>();
        winningPositions.add(topRow);
        winningPositions.add(middleRow);
        winningPositions.add(bottomRow);

        winningPositions.add(leftColumn);
        winningPositions.add(middleColumn);
        winningPositions.add(lastColumn);

        winningPositions.add(diagonalRight);
        winningPositions.add(diagonalLeft);

        for(List l : winningPositions) {
            //player wins
            if(playerPositions.containsAll(l)){
                return "Congratulations you won Tic-Tac-Toe!";
            }
            //cpu wins
            else if(cpuPositions.containsAll(l)) {
                return "Sorry you lost! CPU Wins!";
            }
            //tie game
            else if(playerPositions.size() + cpuPositions.size() == 9) {
                return "Tie Game!";
            }
        }

        return "";
    }
}