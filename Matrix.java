/**
 * A simple m x n matrix class.
 * <p>
 * @author Devdutt Santhosh
 * @version Sept. 2017
 */

public class Matrix {
    /**
     * @param theta The rotation angle.
     * @return The homogeneous rotation matrix for a given value for theta.
     */
    public static Matrix rotationH2D(double theta) {
        double[][] R = {{Math.cos(theta), -Math.sin(theta), 0},
                {Math.sin(theta), Math.cos(theta), 0},
                {0, 0, 1}};
        return new Matrix(R);
    }

    /**
     * @param tx The amount to translate in the x direction.
     * @param ty The amount to translate in the y direction.
     * @return The matrix representing a translation of tx, ty.
     */
    public static Matrix translationH2D(double tx, double ty) {
        double[][] R = {{1, 0, 0},
                {0, 1, 0},
                {tx, ty, 1}};
        return new Matrix(R);
    }

    /**
     * @param x The x coordinate
     * @param y The y coordinate
     * @return The column matrix representing in homogeneous coordinates the point (x, y).
     */
    public static Matrix vectorH2D(double x, double y) {
        double[][] R = {{x, 0, 0},
                {0, y, 0},
                {0, 0, 1}};
        return new Matrix(R);
    }

    /**
     * @param n The dimension of the matrix. Recall that the identity matrix has 1's for any entry that is in the same row index as its column index, 0's everywhere else.
     * @return the nxn identity matrix
     */
    public static Matrix identity(int n) {
        double[][] R = new double[n][n];
        for (int i = 0; i < n; i++)
            R[i][i] = 1;
        return new Matrix(R);
    }

    /**
     * Computes the mxn identity matrix which has 1's for every entry at the same row and column index and
     * 0 for all other entries.
     *
     * @param m
     * @param n
     * @return the mxn identity matrix.
     */
    public static Matrix identity(int m, int n) {
        double[][] R = new double[m][n];
        int k;
        if (m > n) {
            k = n;
        } else {
            k = m;
        }
        for (int i = 0; i < k; i++)
            R[i][i] = 1;
        return new Matrix(R);
    }

    private int m, n;
    private double[][] M;

    public Matrix(double[][] array) {
        M = array;
        m = array.length;
        n = array[0].length;
    }

    /**
     * @return The number of columns in the matrix.
     */
    public int nCols() {
        return n;
    }

    /**
     * @return the number of rows.
     */
    public int nRows() {
        return m;
    }

    /**
     * @param i
     * @param j
     * @return The entry at row i column j.
     */
    public double entry(int i, int j) {
        return M[i][j];
    }

    /**
     * Computes the dot product of this matrix with the parameter that. (Return value is this . that)
     * Recall that the dot product is the typical matrix multiplication.
     *
     * @param that The matrix to apply this matrix to.
     * @return The dot product of this matrix with that.
     * @throws UndefinedMatrixOpException If this.nCols() != that.nRows() because the dot product is not defined
     */
    public Matrix dot(Matrix that) throws UndefinedMatrixOpException {
        int m1 = this.nRows();
        int n1 = this.nCols();
        int m2 = that.nRows();
        int n2 = that.nCols();
        if (n1 != m2) {
            throw new UndefinedMatrixOpException("Dimensions are not equal", this, that);
        }
        double[][] c = new double[m1][n2];
        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n2; j++)
                for (int k = 0; k < n1; k++)
                    c[i][j] += this.M[i][k] * that.M[k][j];
        Matrix t = new Matrix(c);
        return t;
    }

    /**
     * Add this matrix to that and returns the result. (Return value is this + that)
     *
     * @param that the matrix to add this matrix to.
     * @return The sum of the this and that.
     * @throws BadDimensionException If the dimension of the two matrices are not identical.
     */
    public Matrix plus(Matrix that) throws UndefinedMatrixOpException {
        int m1 = this.nRows();
        int n1 = this.nCols();
        int m2 = that.nRows();
        int n2 = that.nCols();
        if (m1 != m2 || n1 != n2) {
            throw new UndefinedMatrixOpException("Dimensions are not equal", this, that);
        }
        double[][] c = new double[m1][n1];
        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n1; j++)
                c[i][j] = this.M[i][j] + that.M[i][j];
        Matrix t = new Matrix(c);
        return t;
    }

    /**
     * A little helpful toString() in case you want to print your matrix to System.out
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(M[i][j]);
                sb.append('\t');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
