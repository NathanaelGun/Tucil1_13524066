package tucil1;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import tucil1.model.Board;
import tucil1.solver.Solver;
import tucil1.solver.SolverResult;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("         Queens Solver           ");
        System.out.println("=================================");
        System.out.print("Masukkan nama file test case: ");
        String filename = scanner.nextLine().trim();

        try {
            Board board = readBoardFromFile(filename);
            validateBoard(board);

            System.out.println("\nPapan awal:");
            board.printBoard();
            System.out.println();

            Solver solver = new Solver(board);
            System.out.println("Memulai pencarian dengan Brute Force...");
            System.out.println("---------------------------------");

            SolverResult result = solver.solve();

            System.out.println();
            System.out.println("---------------------------------");

            if (result.isSolved()) {
                System.out.println("✓ Solusi ditemukan!\n");
                board.printBoard();
                System.out.println();
                System.out.println("Waktu pencarian  : " + result.getExecutionTime() + " ms");
                System.out.println("Iterasi ditinjau : " + result.getIterationCount() + " kasus");

                System.out.print("\nApakah Anda ingin menyimpan solusi? (Ya/Tidak): ");
                String answer = scanner.nextLine().trim();
                if (answer.equalsIgnoreCase("Ya")) {
                    String savedPath = saveSolution(board, filename);
                    System.out.println("Solusi disimpan ke: " + savedPath);
                }
            } 
            else {
                System.out.println("Tidak ada solusi!\n");
                System.out.println("Waktu pencarian  : " + result.getExecutionTime() + " ms");
                System.out.println("Iterasi ditinjau : " + result.getIterationCount() + " kasus");
            }

        } catch (IOException e) {
            System.out.println("Error membaca file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Input tidak valid: " + e.getMessage());
        }

        System.out.println("=================================");
        scanner.close();
    }

    public static Board readBoardFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                content.append(line.trim()).append("\n");
            }
        }
        reader.close();

        String[] lines = content.toString().trim().split("\n");
        int size = lines.length;

        for (int i = 0; i < size; i++) {
            if (lines[i].length() != size) {
                throw new IllegalArgumentException(
                    "Papan tidak persegi! Baris " + (i+1) + 
                    " punya " + lines[i].length() + 
                    " kolom, harusnya " + size
                );
            }
        }

        char[][] cells = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = lines[i].charAt(j);
            }
        }

        return new Board(size, cells);
    }

    private static void validateBoard(Board board) {
        Set<Character> colors = new HashSet<>();
        char[][] cells = board.getCells();

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                colors.add(cells[i][j]);
            }
        }

        if (colors.size() != board.getSize()) {
            throw new IllegalArgumentException(
                "Jumlah warna (" + colors.size() + 
                ") harus sama dengan ukuran papan (" + board.getSize() + ")"
            );
        }
    }

    public static String saveSolution(Board board, String inputFilename) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = now.format(formatter);

        String outputFilename = inputFilename.replace(".txt", "_" + timestamp + "_solution.txt");
        try (PrintWriter writer = new PrintWriter(outputFilename)) {
            writer.print(board.getBoardAsString());
        } catch (IOException e) {
            System.out.println("Error menyimpan file: " + e.getMessage());
        }
        return outputFilename;
    }
}