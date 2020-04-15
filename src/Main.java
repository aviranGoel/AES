import sun.security.util.BitArray;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        //User command is to encrypt or decrypt:
        if(args[0].equals("-e") || args[0].equals("-d"))
        {
            //Get key path and input path inorder to read their bytes:

            //args[1] it's "-k"
            //args[2] it's <path-to-key-file>
            Path keyPath = Paths.get(args[2]);

            //args[3] it's "-i"
            //args[4] it's <path-to-input-file>
            Path inputPath = Paths.get(args[4]);

            //Get the 1D byte array of the files from the path:
            byte[] key1DByteArray = Files.readAllBytes(keyPath);
            byte[] input1DByteArray = Files.readAllBytes(inputPath);

            //Split the 1D byte array to ArrayList of 2D byte array-4*4 block=128 bit each block:
            ArrayList<Byte[][]> keyList2DByteArray = splitByteArrayIntoBlocksArrayList(key1DByteArray);
            ArrayList<Byte[][]> inputList2DByteArray = splitByteArrayIntoBlocksArrayList(input1DByteArray);

            AES_third aes_third = new AES_third();

            ArrayList<Byte[][]> outputList2DByteArray;

            //User command is to encrypt:
            if(args[0].equals("-e"))
            {
                outputList2DByteArray = aes_third.aes_third_encrypt(inputList2DByteArray, keyList2DByteArray);
            }
            //User command is to decrypt:
            else
            //if(args[0].equals("-d"))
            {
                outputList2DByteArray = aes_third.aes_third_decrypt(inputList2DByteArray, keyList2DByteArray);
            }

            byte[] output1DByteArray = reverse_SplitByteArrayIntoBlocksArrayList(outputList2DByteArray);

            //Open File to write the output 1D byte array to:

            //args[5] it's "-o"
            //args[6] it's <path-to-output-file>
            FileOutputStream fos = new FileOutputStream(args[6]);
            fos.write(output1DByteArray);
            fos.close();
        }

        //User command is to break the algorithm:
        else if(args[0].equals("-b"))
        {
            //Get message path and ciper path inorder to read their bytes:

            //args[1] it's "-m"
            //args[2] it's <path-to-message>
            Path messagePath = Paths.get(args[2]);

            //args[3] it's "-c"
            //args[4] it's <path-to-ciper>
            Path ciperPath = Paths.get(args[4]);

            //Get the 1D byte array of the files from the path:
            byte[] message1DByteArray = Files.readAllBytes(messagePath);
            byte[] ciper1DByteArray = Files.readAllBytes(ciperPath);

            //Split the 1D byte array to ArrayList of 2D byte array-4*4 block=128 bit each block:
            ArrayList<Byte[][]> messageList2DByteArray = splitByteArrayIntoBlocksArrayList(message1DByteArray);
            ArrayList<Byte[][]> ciperList2DByteArray = splitByteArrayIntoBlocksArrayList(ciper1DByteArray);

            AES_third aes_third = new AES_third();

            Byte[][] key1_2DByte = randomKeys();
            Byte[][] key2_2DByte = randomKeys();
            //NOTE: key1 and key2 need to be different.

            ArrayList<Byte[][]> key3_2DByteArray = aes_third.aes_third_break(messageList2DByteArray, ciperList2DByteArray, key1_2DByte, key2_2DByte);/

            byte[] key3_2DByte = reverse_SplitByteArrayIntoBlocksArrayList(key3_2DByteArray);

            //TODO-Combine all 3 keys together
            //NOTE: all 3 keys need to be different.

            byte[] all3Keys = null;

            //Open File to write the output 1D byte array to:

            //args[5] it's "-o"
            //args[6] it's <path-to-output-file>
            FileOutputStream fos = new FileOutputStream(args[6]);
            fos.write(all3Keys);
            fos.close();
        }









//        byte a;
//        byte b;
//        byte c;
//
//        a = 0;
//        b = 0;
//        c = (byte) (a^b);
//        System.out.println(+ a + "^" + b + "=" + c);
//
//        a = 0;
//        b = 1;
//        c = (byte) (a^b);
//        System.out.println(+ a + "^" + b + "=" + c);
//
//        a = 1;
//        b = 0;
//        c = (byte) (a^b);
//        System.out.println(+ a + "^" + b + "=" + c);
//
//        a = 1;
//        b = 1;
//        c = (byte) (a^b);
//        System.out.println(+ a + "^" + b + "=" + c);
//
//        System.out.println();
//
//        int matrixSize = 4;
//        int[][] matrix = new int[matrixSize][matrixSize];
//        int count = 0;
//
//        for (int row = 0; row < matrixSize; row++)
//        {
//            for (int col = 0; col < matrixSize; col++)
//            {
//                matrix[row][col] = count;
//                count++;
//            }
//        }
//
//        System.out.println("Origin Matrix");
//        print2DMatrix(matrix, matrixSize);
//        System.out.println();
//
//        int[][] shiftedMatrix;
//
//        shiftedMatrix = shiftColumns(matrix, "encrypt");
//
//        System.out.println("Shifted Matrix");
//        print2DMatrix(shiftedMatrix, matrixSize);
//        System.out.println();
//
//        shiftedMatrix = shiftColumns(shiftedMatrix, "decrypt");
//
//        System.out.println("Origin matrix-Shifted Shifted Matrix");
//        print2DMatrix(shiftedMatrix, matrixSize);
//        System.out.println();
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
