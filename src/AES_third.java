import java.util.ArrayList;

public class AES_third
{
    private AES_first aes_first;

    public AES_third()
    {
        this.aes_first = new AES_first();
    }

    /**
     * aes_third encrypt function.
     * Using aes_first_encrypt function, 3 time, 3 different keys.
     * @param plainText-text to encrypt.
     * @param key-keys to encrypt with.
     * @return the encrypted text.
     */
    public ArrayList<Byte[][]> aes_third_encrypt(ArrayList<Byte[][]> plainText, ArrayList<Byte[][]> key)
    {
        ArrayList<Byte[][]> ciperText;

        ciperText = aes_first.aes_first_encrypt(plainText, key.get(0));

        ciperText = aes_first.aes_first_encrypt(ciperText, key.get(1));

        ciperText = aes_first.aes_first_encrypt(ciperText, key.get(2));

        return ciperText;
    }

    /**
     * aes_third decrypt function.
     * Using aes_first_decrypt function, 3 time, 3 different keys.
     * @param ciperText-text to decrypt.
     * @param key-keys to decrypt with.
     * @return the decrypted text.
     */
    public ArrayList<Byte[][]> aes_third_decrypt(ArrayList<Byte[][]> ciperText, ArrayList<Byte[][]>key)
    {
        ArrayList<Byte[][]> plainText;

        plainText = aes_first.aes_first_decrypt(ciperText, key.get(2));

        plainText = aes_first.aes_first_decrypt(plainText, key.get(1));

        plainText = aes_first.aes_first_decrypt(plainText, key.get(2));

        return plainText;
    }
}
