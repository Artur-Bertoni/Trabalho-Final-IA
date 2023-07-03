package Problema03;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Maria Eduarda
 */
public class SudokuGame {

    private static final int SIZE = 9;
    private static final int EMPTY_CELL = 0;
    private static Scanner SCANNER;
    private final int[][] BOARD;
    private final Random RANDOM;

    public static void main(String[] args) {
        SudokuGame game = new SudokuGame();
        game.play();
        SCANNER.close();
    }

    public SudokuGame() {
        BOARD = new int[SIZE][SIZE];
        RANDOM = new Random();
        SCANNER = new Scanner(System.in);
    }

    public void generateBoard() {
        // Preenche o tabuleiro com números aleatórios válidos
        solveBoard();

        // Remove células para criar um jogo incompleto
        int cellsToRemove = 40; // Quantidade de células para remover (ajuste conforme desejado)
        while (cellsToRemove > 0) {
            int row = RANDOM.nextInt(SIZE);
            int col = RANDOM.nextInt(SIZE);
            if (BOARD[row][col] != EMPTY_CELL) {
                BOARD[row][col] = EMPTY_CELL;
                cellsToRemove--;
            }
        }
    }

    private void solveBoard() {
        solve(0, 0);
    }

    private boolean solve(int row, int col) {
        if (col == SIZE) {
            col = 0;
            row++;
            if (row == SIZE) {
                return true;
            }
        }

        if (BOARD[row][col] != EMPTY_CELL) {
            return solve(row, col + 1);
        }

        for (int num = 1; num <= SIZE; num++) {
            if (isValidPlacement(row, col, num)) {
                BOARD[row][col] = num;
                if (solve(row, col + 1)) {
                    return true;
                }
            }
        }

        BOARD[row][col] = EMPTY_CELL;
        return false;
    }

    private boolean isValidPlacement(int row, int col, int num) {
        // Verifica se o número não existe na mesma linha ou coluna
        for (int i = 0; i < SIZE; i++) {
            if (BOARD[row][i] == num || BOARD[i][col] == num) {
                return false;
            }
        }

        // Verifica se o número não existe no mesmo bloco 3x3
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (BOARD[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public void play() {
        generateBoard();
        boolean GotHint;

        while (!isGameComplete()) {
            printBoard();

            GotHint = false;

            System.out.print("Digite a linha (0-8), -1 para sair ou -2 para obter uma dica: ");
            int row = SCANNER.nextInt();
            if (row == -1) {
                System.out.println("Jogo encerrado.");
                return;
            } else if (row == -2) {
                if (!getHint()) {
                    System.out.println("Não há mais dicas disponíveis.");
                }
                GotHint = true;
            }

            if (!GotHint) {
                System.out.print("Digite a coluna (0-8) ou -1 para sair: ");
                int col = SCANNER.nextInt();
                if (col == -1) {
                    System.out.println("Jogo encerrado.");
                    return;
                }

                System.out.print("Digite o número (1-9): ");
                int num = SCANNER.nextInt();
                if (!isValidMove(row, col, num)) {
                    System.out.println("Movimento inválido. Tente novamente.");
                }
            }
        }

        System.out.println("Parabéns! Você completou o SudokuGame!");
    }

    private void printBoard() {
        System.out.println("\n+-----------------------+");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < SIZE; j++) {
                if (BOARD[i][j] == EMPTY_CELL) {
                    System.out.print("  ");
                } else {
                    System.out.print(BOARD[i][j] + " ");
                }
                if (j % 3 == 2) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if (i % 3 == 2) {
                System.out.println("+-----------------------+");
            }
        }
        System.out.println();
    }

    private boolean isValidMove(int row, int col, int num) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return false;
        }

        if (BOARD[row][col] != EMPTY_CELL) {
            return false;
        }

        if (!isValidPlacement(row, col, num)) {
            return false;
        }

        BOARD[row][col] = num;
        return true;
    }

    private boolean getHint() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (BOARD[row][col] == EMPTY_CELL) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValidMove(row, col, num)) {
                            BOARD[row][col] = num;
                            return true;
                        }
                    }
                    return false;
                }
            }
        }
        return false;
    }

    private boolean isGameComplete() {
        for (int[] row : BOARD) {
            if (Arrays.stream(row).anyMatch(cell -> cell == EMPTY_CELL)) {
                return false;
            }
        }
        return true;
    }

}