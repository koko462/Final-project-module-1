import java.util.Scanner;

public class Main {
    public static int[][] enterInputMatrix(int rows, int columns){
        Scanner console = new Scanner(System.in);
        int[][] matrix = new int[rows][columns];

        System.out.println("Enter elements of matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("Element [" + i + "][" + j + "]: ");
                matrix[i][j] = console.nextInt();
            }
        }
        return matrix;
    }
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        //Entering rows and columns of matrix
        System.out.print("Enter rows of matrix: ");
        int rows = console.nextInt();
        System.out.print("Enter columns of matrix: ");
        int columns = console.nextInt();

        int[][] matrix = enterInputMatrix(rows, columns);
    }
}