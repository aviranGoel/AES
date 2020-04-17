import sun.security.util.BitArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        //Path keyPath1 = Paths.get("C:\\Users\\USER\\Desktop\\לימודים\\'שנה ג\\'סמסטר ב\\אבטחת מחשבים\\עבודות\\עבודה 1\\test files\\key_long");
        //Path inputPath1 = Paths.get("C:\\Users\\USER\\Desktop\\לימודים\\'שנה ג\\'סמסטר ב\\אבטחת מחשבים\\עבודות\\עבודה 1\\test files\\cipher_long");

        // arguments for test encrypt short:
        //-e -k "C:\Users\avira\Desktop\הנדסת מערכות מידע\שנה ד\סמסטר ב\אבטחת מחשבים ורשתות תקשורת\עבודות\עבודה 1-הגשה 19.04.2020\AES3_test_files\test files\key_short" -i "C:\Users\avira\Desktop\הנדסת מערכות מידע\שנה ד\סמסטר ב\אבטחת מחשבים ורשתות תקשורת\עבודות\עבודה 1-הגשה 19.04.2020\AES3_test_files\test files\message_short" -o "C:\Users\avira\Desktop\הנדסת מערכות מידע\שנה ד\סמסטר ב\אבטחת מחשבים ורשתות תקשורת\עבודות\עבודה 1-הגשה 19.04.2020\AES3_test_files\test files\cipher_shortOut"

        // arguments for test encrypt long:
        // -e -k "C:\Users\avira\Desktop\הנדסת מערכות מידע\שנה ד\סמסטר ב\אבטחת מחשבים ורשתות תקשורת\עבודות\עבודה 1-הגשה 19.04.2020\AES3_test_files\test files\key_long" -i "C:\Users\avira\Desktop\הנדסת מערכות מידע\שנה ד\סמסטר ב\אבטחת מחשבים ורשתות תקשורת\עבודות\עבודה 1-הגשה 19.04.2020\AES3_test_files\test files\message_long" -o "C:\Users\avira\Desktop\הנדסת מערכות מידע\שנה ד\סמסטר ב\אבטחת מחשבים ורשתות תקשורת\עבודות\עבודה 1-הגשה 19.04.2020\AES3_test_files\test files\cipher_longOut"

        // arguments for test decrypt short:
        //-d -k "C:\Users\avira\Desktop\הנדסת מערכות מידע\שנה ד\סמסטר ב\אבטחת מחשבים ורשתות תקשורת\עבודות\עבודה 1-הגשה 19.04.2020\AES3_test_files\test files\key_short" -i "C:\Users\avira\Desktop\הנדסת מערכות מידע\שנה ד\סמסטר ב\אבטחת מחשבים ורשתות תקשורת\עבודות\עבודה 1-הגשה 19.04.2020\AES3_test_files\test files\cipher_short" -o "C:\Users\avira\Desktop\הנדסת מערכות מידע\שנה ד\סמסטר ב\אבטחת מחשבים ורשתות תקשורת\עבודות\עבודה 1-הגשה 19.04.2020\AES3_test_files\test files\message_shortOut"

        // arguments for test decrypt long:
        //-d -k "C:\Users\avira\Desktop\הנדסת מערכות מידע\שנה ד\סמסטר ב\אבטחת מחשבים ורשתות תקשורת\עבודות\עבודה 1-הגשה 19.04.2020\AES3_test_files\test files\key_long" -i "C:\Users\avira\Desktop\הנדסת מערכות מידע\שנה ד\סמסטר ב\אבטחת מחשבים ורשתות תקשורת\עבודות\עבודה 1-הגשה 19.04.2020\AES3_test_files\test files\cipher_long" -o "C:\Users\avira\Desktop\הנדסת מערכות מידע\שנה ד\סמסטר ב\אבטחת מחשבים ורשתות תקשורת\עבודות\עבודה 1-הגשה 19.04.2020\AES3_test_files\test files\message_longOut"

        // arguments for break:
        //-b -m "C:\Users\avira\Desktop\הנדסת מערכות מידע\שנה ד\סמסטר ב\אבטחת מחשבים ורשתות תקשורת\עבודות\עבודה 1-הגשה 19.04.2020\AES3_test_files\test files\message_short" -c "C:\Users\avira\Desktop\הנדסת מערכות מידע\שנה ד\סמסטר ב\אבטחת מחשבים ורשתות תקשורת\עבודות\עבודה 1-הגשה 19.04.2020\AES3_test_files\test files\cipher_short" -o "C:\Users\avira\Desktop\הנדסת מערכות מידע\שנה ד\סמסטר ב\אבטחת מחשבים ורשתות תקשורת\עבודות\עבודה 1-הגשה 19.04.2020\AES3_test_files\test files\keyOut"

        // arguments for test encrypt with founded keys:
        //-e -k "C:\Users\avira\Desktop\הנדסת מערכות מידע\שנה ד\סמסטר ב\אבטחת מחשבים ורשתות תקשורת\עבודות\עבודה 1-הגשה 19.04.2020\AES3_test_files\test files\keyOut" -i "C:\Users\avira\Desktop\הנדסת מערכות מידע\שנה ד\סמסטר ב\אבטחת מחשבים ורשתות תקשורת\עבודות\עבודה 1-הגשה 19.04.2020\AES3_test_files\test files\message_short" -o "C:\Users\avira\Desktop\הנדסת מערכות מידע\שנה ד\סמסטר ב\אבטחת מחשבים ורשתות תקשורת\עבודות\עבודה 1-הגשה 19.04.2020\AES3_test_files\test files\cipherOut"

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

            ArrayList<Byte[][]> outputList2DByteArray = new ArrayList<>();

            //User command is to encrypt:
            if(args[0].equals("-e"))
            {
                outputList2DByteArray = aes_third.aes_third_encrypt(inputList2DByteArray, keyList2DByteArray);
            }
            //User command is to decrypt:
            //else
            if(args[0].equals("-d"))
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
//
//            System.out.println("START CHECK EQUALS");
//
//            Path origin = Paths.get("C:\\Users\\avira\\Desktop\\הנדסת מערכות מידע\\שנה ד\\סמסטר ב\\אבטחת מחשבים ורשתות תקשורת\\עבודות\\עבודה 1-הגשה 19.04.2020\\AES3_test_files\\test files\\message_long");
//            byte[] originCipher1DByteArray = Files.readAllBytes(origin);
//
//            Path newFile = Paths.get(args[6]);
//            byte[] newCipher1DByteArray = Files.readAllBytes(newFile);
//
//            System.out.println("CHECK EQUALS: ");
//            checkEquals(originCipher1DByteArray, newCipher1DByteArray);
        }

        //User command is to break the algorithm:
        else if(args[0].equals("-b"))
        {
            //Get message path and cipher path inorder to read their bytes:

            //args[1] it's "-m"
            //args[2] it's <path-to-message>
            Path messagePath = Paths.get(args[2]);

            //args[3] it's "-c"
            //args[4] it's <path-to-cipher>
            Path cipherPath = Paths.get(args[4]);

            //Get the 1D byte array of the files from the path:
            byte[] message1DByteArray = Files.readAllBytes(messagePath);
            byte[] cipher1DByteArray = Files.readAllBytes(cipherPath);

            //Split the 1D byte array to ArrayList of 2D byte array-4*4 block=128 bit each block:
            ArrayList<Byte[][]> messageList2DByteArray = splitByteArrayIntoBlocksArrayList(message1DByteArray);
            ArrayList<Byte[][]> cipherList2DByteArray = splitByteArrayIntoBlocksArrayList(cipher1DByteArray);

            ArrayList<Byte[][]> key3_2DByteArray = breakThe_aes_third_findThridKey(messageList2DByteArray.get(0), cipherList2DByteArray.get(0));

            byte[] key3 = reverse_SplitByteArrayIntoBlocksArrayList(key3_2DByteArray);

            byte[] key1 = randomKeys(16);
            byte[] key2;

            Byte[][] k1ByteArray = splitByteArrayIntoBlocksArrayList(key1).get(0);
            Byte[][] k2ByteArray = matrixShiftColumns(k1ByteArray);
            ArrayList k2Array = new ArrayList();
            k2Array.add(k2ByteArray);
            key2 = reverse_SplitByteArrayIntoBlocksArrayList(k2Array);

//            for (int i = 0; i < key1.length; i++)
//            {
//                if(i % 4 == 0 && i != 0)
//                {
//                    System.out.println();
//                }
//                System.out.print(key1[i] + " ,");
//            }
//            System.out.println();
//            System.out.println("----------------");
//            for (int i = 0; i < key2.length; i++)
//            {
//                if(i % 4 == 0 && i != 0)
//                {
//                    System.out.println();
//                }
//                System.out.print(key2[i] + " ,");
//            }
//            System.out.println();
//            System.out.println("----------------");
//            for (int i = 0; i < key3.length; i++)
//            {
//                if(i % 4 == 0 && i != 0)
//                {
//                    System.out.println();
//                }
//                System.out.print(key3[i] + " ,");
//            }
//            System.out.println();
//            System.out.println("----------------");

            byte[] all3Keys = CombineAll3keys(key1,key2,key3);

            //Open File to write the output 1D byte array to:

            //args[5] it's "-o"
            //args[6] it's <path-to-output-file>
            FileOutputStream fos = new FileOutputStream(args[6]);
            fos.write(all3Keys);
            fos.close();
        }
    }

    private static void checkEquals(byte[] originCipher1DByteArray, byte[] newCipher1DByteArray)
    {
        for (int i = 0; i < originCipher1DByteArray.length; i++)
        {
            if (originCipher1DByteArray[i] != newCipher1DByteArray[i])
            {
                System.out.println("false: " + i);
            }
        }
        System.out.println("TRUE");
    }

    private static ArrayList<Byte[][]> breakThe_aes_third_findThridKey(Byte[][] message, Byte[][] cipher)
    {
        ArrayList<Byte[][]> key3_2DByteArray = new ArrayList<>();

        Byte[][] key3_1DByteArray = new Byte[4][4];

        key3_1DByteArray = tripleShiftColumns(message);

        key3_1DByteArray = addRoundKey(key3_1DByteArray, cipher);

        key3_2DByteArray.add(key3_1DByteArray);

        return key3_2DByteArray;
    }

    private static Byte[][] tripleShiftColumns(Byte[][] matrix)
    {
        matrix = matrixShiftColumns(matrix);
        matrix = matrixShiftColumns(matrix);
        matrix = matrixShiftColumns(matrix);

        return matrix;
    }

    private static Byte[][] matrixShiftColumns(Byte[][] matrix)
    {
        Byte[][] shiftedMatrix = new Byte[4][4];

        //col=0 -> no shifting.
        for (int row = 0; row < 4; row++)
        {
            shiftedMatrix[row][0] = matrix[row][0];
        }
        for (int col = 1; col < 4; col++)
        {
            int matrixCol = 0;
            for (int row = 0; row < 4; row++)
            {
                if (row + col < 4)
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

    private static Byte[][] addRoundKey(Byte[][] shiftedMessage, Byte[][] cipher)
    {
        Byte[][] xorMatrix = new Byte[4][4];

        for (int row = 0; row < 4; row++)
        {
            for (int col = 0; col < 4; col++)
            {
                xorMatrix[row][col] = (byte)(shiftedMessage[row][col] ^ cipher[row][col]);
            }
        }
        return xorMatrix;
    }

    private static byte[] CombineAll3keys( byte[] key1 , byte [] key2 , byte[] key3){
        byte[] all3Keys = new byte[key1.length*3];

        for (int index = 0; index <all3Keys.length ; index++) {
            if(index<key1.length){
                all3Keys[index]=key1[index];
            }
            else if(index<key1.length*2){
                all3Keys[index]=key2[index-key1.length];
            }
            else if(index<key1.length*3){
                all3Keys[index]=key3[index-2*key1.length];
            }
        }
        return all3Keys;
    }

    private static byte[] randomKeys(int keySize) {

        //int size = keySize/8;
        //size = (int) Math.sqrt(size);

        byte[] key = new byte[keySize];

        Random random = new Random();
        random.nextBytes(key);

        return key;

    }

    private static byte[] reverse_SplitByteArrayIntoBlocksArrayList(ArrayList<Byte[][]> messageInBlocks) {

        Byte [] message = new Byte[16*messageInBlocks.size()];

        int index = 0;
        for (int numOfBlocks = 0; index < message.length ; numOfBlocks++) {

            for (int row = 0; row < 4; row++) {

                for (int col = 0; col < 4; col++) {

                    message[index]=messageInBlocks.get(numOfBlocks)[col][row];
                    index++;
                }
            }
        }

        byte [] messageByteArray = new byte[message.length];
        for (int i = 0; i <message.length ; i++) {
            messageByteArray[i] = message[i];
        }

        return messageByteArray;

    }

    private static ArrayList<Byte[][]> splitByteArrayIntoBlocksArrayList(byte[] message) {

        //check how many blocks
        int amountOfBlocks = message.length / 16;
        ArrayList<Byte[][]> messageSplitToBlocks = new ArrayList();

        //int index=0;
        for (int i = 0; i < amountOfBlocks ; i++) {
            messageSplitToBlocks.add(new Byte[4][4]);
        }

        int counterOfBlocks = -1;
        for (int index = 0; index < message.length;) {

            counterOfBlocks++;

            for (int row = 0; row < 4; row++) {

                for (int col = 0; col < 4; col++) {

                    messageSplitToBlocks.get(counterOfBlocks)[col][row] = message[index];
                    index++;
                }

            }
        }
        return messageSplitToBlocks;
    }
}
