// For finding the least square regression line using matrices
// inverse method taken from https://www.sanfoundry.com/java-program-find-inverse-matrix/
import java.util.*;

public class MatrixRegressionLine {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Please input the rows <space> columns of Matrix X: ");
        double matrixX[][] = new double[s.nextInt()][s.nextInt()];

        System.out.print("Please input the rows <space> columns of Matrix Y: ");
        double matrixY[][] = new double[s.nextInt()][s.nextInt()];

        System.out.println("\nNow accepting inputs for Matrix X: ");
        arrayInput(matrixX);

        System.out.println("\nNow accepting inputs for Matrix Y: ");
        arrayInput(matrixY);

        System.out.println("\nX:");
        arrayDisplay(matrixX);
        System.out.println("\nY:");
        arrayDisplay(matrixY);
        
        double[][] xt = transpose(matrixX);
        System.out.println("\nX (transpose) =");
        arrayDisplay(xt);
        
        double xtx[][] = multiply(xt,matrixX);
        System.out.println("\nX (transpose) * X =");
        arrayDisplay(xtx);
        
        double xtxi[][] = invert(xtx);
        System.out.println("\n(X (transpose) * X) (inverted) = ");
        arrayDisplay(xtxi);
        
        double xty[][] = multiply(xt,matrixY);
        System.out.println("\nX (transpose) * Y =");
        arrayDisplay(xty);
        
        double final_ans[][] = multiply(xtxi,xty);
        System.out.println("\n(X (transpose) * X)(inverted) * (X (transpose) * Y) = A = ");
        arrayDisplay(final_ans);
        
        System.out.println("\nleast square regression line:\n ŷ = " + final_ans[0][0] + " + " + final_ans[1][0] + "x");
        s.close();
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
    
    public static double[][] transpose(double matrix[][]) {
        double result[][] = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }

    public static void arrayDisplay(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static double[][] multiply(double matrixA[][], double matrixB[][]) {
        double product[][] = new double[matrixA.length][matrixB[0].length];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                for (int k = 0; k < matrixB.length; k++) {
                    product[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return product;
    }
    
    public static double[][] invert(double a[][]) 
    {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i) 
            b[i][i] = 1;
 
 // Transform the matrix into an upper triangle
        gaussian(a, index);
 
 // Update the matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                    	    -= a[index[j]][i]*b[index[i]][k];
 
 // Perform backward substitutions
        for (int i=0; i<n; ++i) 
        {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j) 
            {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k) 
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }
 
// Method to carry out the partial-pivoting Gaussian
// elimination.  Here index[] stores pivoting order.
 
    public static void gaussian(double a[][], int index[]) 
    {
        int n = index.length;
        double c[] = new double[n];
 
 // Initialize the index
        for (int i=0; i<n; ++i) 
            index[i] = i;
 
 // Find the rescaling factors, one from each row
        for (int i=0; i<n; ++i) 
        {
            double c1 = 0;
            for (int j=0; j<n; ++j) 
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }
 
 // Search the pivoting element from each column
        int k = 0;
        for (int j=0; j<n-1; ++j) 
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i) 
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) 
                {
                    pi1 = pi0;
                    k = i;
                }
            }
 
   // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i) 	
            {
                double pj = a[index[i]][j]/a[index[j]][j];
 
 // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;
 
 // Modify other elements accordingly
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
    }

}
