package com.game;

import java.util.Scanner;

class TicTacToe {
    static char[][] board;

    public TicTacToe() {
        board = new char[3][3];
        initBoard();
    }

    void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    static void displayBoard() {
        System.out.println("----------");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("----------");
        }
    }

    static boolean placeMark(int row, int col, char mark) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2 && board[row][col] == ' ') {
            board[row][col] = mark;
            return true;
        } else {
            System.out.println("Invalid Position. Try again.");
            return false;
        }
    }

    static boolean checkRowWin() {
        for (int i = 0; i <= 2; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    static boolean checkColumnWin() {
        for (int j = 0; j <= 2; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    static boolean checkDiagonalWin() {
        return (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
               (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]);
    }

    static boolean isBoardFull() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}

class HumanPlayer {
    String name;
    char mark;

    HumanPlayer(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    void makeMove(Scanner scan) {
        int row, col;
        do {
            System.out.println(name + " enter the row and column (0-2):");
            row = scan.nextInt();
            col = scan.nextInt();
        } while (!TicTacToe.placeMark(row, col, mark));
    }
}

public class Game {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        TicTacToe t = new TicTacToe();
        HumanPlayer p1 = new HumanPlayer("Sravika", 'X');
        HumanPlayer p2 = new HumanPlayer("Sindhu", 'O');
        HumanPlayer cp = p1;

        while (true) {
            System.out.println(cp.name + "'s turn");
            cp.makeMove(scan);
            TicTacToe.displayBoard();

            if (TicTacToe.checkRowWin() || TicTacToe.checkColumnWin() || TicTacToe.checkDiagonalWin()) {
                System.out.println(cp.name + " has won!");
                break;
            }

            if (TicTacToe.isBoardFull()) {
                System.out.println("It's a draw!");
                break;
            }

            cp = (cp == p1) ? p2 : p1;
        }

        scan.close();
    }
}
