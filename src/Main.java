public class Main
{
    public static void main(String[] args) {
        byte a;
        byte b;
        byte c;

        a = 0;
        b = 0;
        c = (byte) (a^b);
        System.out.println(+ a + "^" + b + "=" + c);

        a = 0;
        b = 1;
        c = (byte) (a^b);
        System.out.println(+ a + "^" + b + "=" + c);

        a = 1;
        b = 0;
        c = (byte) (a^b);
        System.out.println(+ a + "^" + b + "=" + c);

        a = 1;
        b = 1;
        c = (byte) (a^b);
        System.out.println(+ a + "^" + b + "=" + c);

        System.out.println();

        int matrixSize = 4;
        int[][] matrix = new int[matrixSize][matrixSize];
        int count = 0;

        for (int row = 0; row < matrixSize; row++)
        {
            for (int col = 0; col < matrixSize; col++)
            {
                matrix[row][col] = count;
                count++;
            }
        }

        System.out.println("Origin Matrix");
        print2DMatrix(matrix, matrixSize);
        System.out.println();

        int[][] shiftedMatrix;

        shiftedMatrix = shiftColumns(matrix, "encrypt");

        System.out.println("Shifted Matrix");
        print2DMatrix(shiftedMatrix, matrixSize);
        System.out.println();

        shiftedMatrix = shiftColumns(shiftedMatrix, "decrypt");

        System.out.println("Origin matrix-Shifted Shifted Matrix");
        print2DMatrix(shiftedMatrix, matrixSize);
        System.out.println();
    }

    private static int[][] shiftColumns(int[][] matrix, String action)
    {
        int matrixSize = 4;

        int[][] shiftedMatrix = new int[matrixSize][matrixSize];

        //col=0 -> no shifting.
        for (int row = 0; row < matrixSize; row++)
        {
            shiftedMatrix[row][0] = matrix[row][0];
        }

        if (action.equals("encrypt"))
        {
            //col=1/2/3 -> shift 1/2/3 up.
            return matrixShiftColumnsUp(matrix, shiftedMatrix);
        }
        //else (action.equals("decrypt"))
        //col=1/2/3 -> shift 1/2/3 down-the revers shifting.
        return matrixShiftColumnsDown(matrix, shiftedMatrix);
    }

    private static int[][] matrixShiftColumnsDown(int[][] matrix, int[][] shiftedMatrix)
    {
        int matrixSize = 4;

        for (int col = 1; col < matrixSize; col++)
        {
            int matrixCol = 3;
            for (int row = matrixSize - 1; row >= 0; row--)
            {
                if (row > col - 1)
                {
                    shiftedMatrix[row][col] = matrix[row - col][col];
                }
                else
                {
                    shiftedMatrix[row][col] = matrix[matrixCol][col];
                    matrixCol--;
                }
            }
        }
        return shiftedMatrix;
    }

    private static int[][] matrixShiftColumnsUp(int[][] matrix, int[][] shiftedMatrix)
    {
        int matrixSize = 4;

        //col=1 -> shift 1 up.
        for (int col = 1; col < matrixSize; col++)
        {
            int matrixCol = 0;
            for (int row = 0; row < matrixSize; row++)
            {
                if (row + col < matrixSize)
                {
                    shiftedMatrix[row][col] = matrix[row + col][col];
                }
                else
                {
                    shiftedMatrix[row][col] = matrix[matrixCol][col];
                    matrixCol++;
                }
            }
        }
        return shiftedMatrix;
    }

    private static void print2DMatrix(int[][] matrix, int matrixSize)
    {
        for (int row = 0; row < matrixSize; row++)
        {
            for (int col = 0; col < matrixSize; col++)
            {
                System.out.print(matrix[row][col] + ",");
            }
            System.out.println();
        }
    }
}
