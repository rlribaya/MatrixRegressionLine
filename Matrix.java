
import java.util.*;

public class Matrix {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Please input the rows <space> columns of Matrix A: ");
        double matrixA[][] = new double[s.nextInt()][s.nextInt()];

        System.out.print("Please input the rows <space> columns of Matrix B: ");
        double matrixB[][] = new double[s.nextInt()][s.nextInt()];

        System.out.println("\nNow accepting inputs for Matrix A: ");
        arrayInput(matrixA);

        System.out.println("\nNow accepting inputs for Matrix B: ");
        arrayInput(matrixB);

        System.out.println("Matrix A:");
        arrayDisplay(matrixA);
        System.out.println("Matrix B:");
        arrayDisplay(matrixB);

        System.out.println("AB =");
        multiplyMatrix(matrixA, matrixB);

    }

    public static void arrayInput(double[][] matrix) {
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < matrix.length; i++) {
            System.out.println("Please input row " + (i+1) + " (separate each value by a <space>):");
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = s.nextDouble();
            }
        }
    }

    public static void arrayDisplay(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void multiplyMatrix(double matrixA[][], double matrixB[][]) {
        if (matrixA[0].length != matrixB.length) {
            System.out.println("Error: Matrix A columns must be equal to Matrix B rows");
        } else {
            double product[][] = new double[matrixA.length][matrixB[0].length];
            for (int i = 0; i < matrixA.length; i++) {
                for (int j = 0; j < matrixB[0].length; j++) {
                    for (int k = 0; k < matrixB.length; k++) {
                        product[i][j] += matrixA[i][k] * matrixB[k][j];
                    }
                }
            }
            arrayDisplay(product);
        }
    }
}
