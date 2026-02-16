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
        Scanner scanner = new Scanner(System.in);
        
        // Input file
        System.out.print("Masukkan nama file test case: ");
        String filename = scanner.nextLine();
        
        try {
            // Baca file
            Board board = readBoardFromFile(filename);
            
            System.out.println("Papan awal:");
            board.printBoard();
            System.out.println();
            
            // Solve dengan brute force
            Solver solver = new Solver(board);
            System.out.println("Memulai pencarian dengan Brute Force...\n");
            
            boolean solved = solver.solve();
            
            System.out.println("\n===============================");
            if (solved) {
                System.out.println("Solusi ditemukan!");
                System.out.println();
                board.printBoard();
                System.out.println();
                System.out.println("Waktu pencarian: " + solver.getElapsedTime() + " ms");
                System.out.println("Banyak kasus yang ditinjau: " + solver.getIterationCount() + " kasus");
                
                // Tanya simpan
                System.out.print("\nApakah Anda ingin menyimpan solusi? (Ya/Tidak): ");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("Ya")) {
                    saveSolution(board, filename);
                    System.out.println("Solusi disimpan!");
                }
            } else {
                System.out.println("Tidak ada solusi!");
                System.out.println("Waktu pencarian: " + solver.getElapsedTime() + " ms");
                System.out.println("Banyak kasus yang ditinjau: " + solver.getIterationCount() + " kasus");
            }
            
        } catch (IOException e) {
            System.out.println("Error membaca file: " + e.getMessage());
        }
        
        scanner.close();
    }
    
    private static Board readBoardFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        StringBuilder content = new StringBuilder();
        
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        
        String[] lines = content.toString().trim().split("\n");
        int size = lines.length;
        char[][] cells = new char[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size && j < lines[i].length(); j++) {
                cells[i][j] = lines[i].charAt(j);
            }
        }
        
        return new Board(size, cells);
    }
    
    private static void saveSolution(Board board, String inputFilename) {
        String outputFilename = inputFilename.replace(".txt", "_solution.txt");
        try (PrintWriter writer = new PrintWriter(outputFilename)) {
            writer.print(board.getBoardAsString());
        } catch (IOException e) {
            System.out.println("Error menyimpan file: " + e.getMessage());
        }

    }

}
