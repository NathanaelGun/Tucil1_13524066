package tucil1;

import java.io.*;
import java.util.Scanner;

import tucil1.model.Board;
import tucil1.solver.Solver;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        char[][] cells = {
            {'A', 'B', 'C', 'D'},
            {'B', 'C', 'D', 'A'},
            {'C', 'D', 'A', 'B'},
            {'D', 'A', 'B', 'C'}

        };

        int size = 4;

        Board board = new Board(size,cells);
        Solver solver = new Solver(board);

        boolean solved = solver.solve(0);

        if (solved) {
            System.out.println("Solusi ditemukan:");
            printBoard(board);
        } else {
            System.out.println("Tidak ada solusi.");
        }
    }
    private static void printBoard(Board board) {
        int size = board.getSize();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {

                if (board.getQueenPosition(row) == col) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

}
