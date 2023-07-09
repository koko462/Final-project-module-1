import java.util.Scanner;

public class Main {

    public static boolean isMatrixIdentity(double[][] matrix) {
        int n = matrix.length;
        if (n != matrix[0].length) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && matrix[i][j] != 0) {
                    return false;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (matrix[i][i] != 1) {
                return false;
            }
        }
        return true;
    }
    public static double[][] calculateInverse(double[][] matrix) {
        int n = matrix.length;
        if (n != matrix[0].length) {
            return null;
        }

        double[][] augmentedMatrix = new double[n][2 * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmentedMatrix[i][j] = matrix[i][j];
                augmentedMatrix[i][j + n] = (i == j) ? 1 : 0;
            }
        }

        for (int i = 0; i < n; i++) {
            if (augmentedMatrix[i][i] == 0) {
                boolean success = false;
                for (int j = i + 1; j < n; j++) {
                    if (augmentedMatrix[j][i] != 0) {
                        double[] temp = augmentedMatrix[i];
                        augmentedMatrix[i] = augmentedMatrix[j];
                        augmentedMatrix[j] = temp;
                        success = true;
                        break;
                    }
                }
                if (!success) {
                    return null;
                }
            }

            double pivot = augmentedMatrix[i][i];
            for (int j = 0; j < 2 * n; j++) {
                augmentedMatrix[i][j] /= pivot;
            }

            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double ratio = augmentedMatrix[j][i];
                    for (int k = 0; k < 2 * n; k++) {
                        augmentedMatrix[j][k] -= ratio * augmentedMatrix[i][k];
                    }
                }
            }
        }

        double[][] inverse = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverse[i][j] = augmentedMatrix[i][j + n];
            }
        }
        return inverse;
    }

    public static double calculateDeterminant(int[][] matrix) {
        int n = matrix.length;

        if (n == 1) {
            return matrix[0][0];
        } else if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else {
            double determinant = 0;

            for (int j = 0; j < n; j++) {
                int[][] subMatrix = new int[n - 1][n - 1];

                for (int i = 1; i < n; i++) {
                    for (int k = 0, l = 0; k < n; k++) {
                        if (k != j) {
                            subMatrix[i - 1][l] = matrix[i][k];
                            l++;
                        }
                    }
                }

                determinant += Math.pow(-1, j) * matrix[0][j] * calculateDeterminant(subMatrix);
            }

            return determinant;
        }
    }

    public static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int columns1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int columns2 = matrix2[0].length;

        if (columns1 != rows2) {
            return null;
        }
        int[][] product = new int[rows1][columns2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns2; j++) {
                int sum = 0;
                for (int k = 0; k < columns1; k++) {
                    sum += matrix1[i][k] * matrix2[k][j];
                }
                product[i][j] = sum;
            }
        }

        return product;
    }

    public static int[][] addMatrices(int[][] matrix1, int[][] matrix2) {
        int rows = matrix1.length;
        int columns = matrix1[0].length;
        int[][] sum = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sum[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        return sum;
    }

    public static int[][] subtractMatrices(int[][] matrix1, int[][] matrix2) {
        int rows = matrix1.length;
        int columns = matrix1[0].length;
        int[][] difference = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                difference[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }

        return difference;
    }

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

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        // first subtask
        System.out.print("Enter rows of matrix: ");
        int rows = console.nextInt();
        System.out.print("Enter columns of matrix: ");
        int columns = console.nextInt();
        int[][] matrix = enterInputMatrix(rows, columns);

        System.out.println(); // empty row
        System.out.println(); // empty row

        // second subtask
        printMatrix(matrix);

        System.out.println(); // empty row
        System.out.println(); // empty row

        // third subtask
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] matrix2 = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };

        int[][] sum = addMatrices(matrix1, matrix2);
        int[][] difference = subtractMatrices(matrix1, matrix2);

        System.out.println("Sum of matrix:");
        printMatrix(sum);

        System.out.println("Difference of matrix:");
        printMatrix(difference);

        System.out.println(); // empty row
        System.out.println(); // empty row

        // fourth subtask
        int[][] matrixOne = {
                {1, 2, 3},
                {4, 5, 6}
        };
        
        int[][] matrixTwo = {
                {7, 8},
                {9, 10},
                {11, 12}
        };

        int[][] product = multiplyMatrices(matrixOne, matrixTwo);
        if (product != null) {
            System.out.println("Multiplication of matrices:");
            printMatrix(product);
        } else {
            System.out.println("The dimensions of matrices are not suitable for multiplication.");
        }

        System.out.println(); // empty row
        System.out.println(); // empty row

        // fifth subtask
        int[][] inputMatrix = {
                {2, 3, 1},
                {4, 1, -1},
                {-2, 5, 3}
        };

        double determinant = calculateDeterminant(inputMatrix);
        System.out.println("Determinant of matrix is: " + determinant);

        System.out.println(); // empty row
        System.out.println(); // empty row

        // sixth subtask
        double[][] matrixFirst = {
                {1, 2},
                {3, 4}
        };

        double[][] inverse = calculateInverse(matrixFirst);
        if (inverse == null) {
            System.out.println("Matrix can not reverse!");
        } else {
            System.out.println("Reverse matrix is:");
            for (int i = 0; i < inverse.length; i++) {
                for (int j = 0; j < inverse[0].length; j++) {
                    System.out.print(inverse[i][j] + " ");
                }
                System.out.println();
            }
        }

        System.out.println(); // empty row
        System.out.println(); // empty row

        // seventh subtask
        double[][] squareMatrix = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };

        boolean isIdentity = isMatrixIdentity(squareMatrix);
        if (isIdentity) {
            System.out.println("Square matrix can be convert to unit matrix!");
        } else {
            System.out.println("Square matrix can not be convert to unit matrix!");
        }
    }
}