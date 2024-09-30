import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;

public class AESCipherExample {

    // Método para generar una clave secreta de AES
    public static SecretKey generateSecretKey() throws Exception {
        // Creamos un generador de claves para AES
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // Clave de 128 bits
        return keyGenerator.generateKey(); // Generamos y devolvemos la clave secreta
    }

    // Método para generar un vector  aleatorio
    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16]; // AES usa un vector de 16 bytes
        SecureRandom random = new SecureRandom(); // Creamos un generador de números aleatorios seguros
        random.nextBytes(iv); // Llena el array de vector con bytes aleatorios
        return new IvParameterSpec(iv); //Devuelve el vector encapsulado en un IvParameterSpec
    }

    // Método para cifrar el archivo binario
    public static void encryptFile(String inputFile, String outputFile, SecretKey key, IvParameterSpec iv) throws Exception {
        // Instanciamos el cifrador AES
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv); // Inicia en modo cifrado con la clave y el vector

        // Leemos el archivo de entrada
        FileInputStream inputStream = new FileInputStream(new File(inputFile));
        byte[] inputBytes = new byte[(int) new File(inputFile).length()];
        inputStream.read(inputBytes);

        // Ciframos los bytes del archivo
        byte[] outputBytes = cipher.doFinal(inputBytes);

        // Escribimos el archivo cifrado en la salida
        FileOutputStream outputStream = new FileOutputStream(new File(outputFile));
        outputStream.write(outputBytes);

        // Cerramos los streams
        inputStream.close();
        outputStream.close();
    }

    // Método para descifrar el archivo binario
    public static void decryptFile(String inputFile, String outputFile, SecretKey key, IvParameterSpec iv) throws Exception {
        // Instanciamos el cifrador AES
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, iv); // Inicia en modo descifrado con la clave y el IV

        // Leemos el archivo cifrado de entrada
        FileInputStream inputStream = new FileInputStream(new File(inputFile));
        byte[] inputBytes = new byte[(int) new File(inputFile).length()];
        inputStream.read(inputBytes);

        // Desciframos los bytes del archivo
        byte[] outputBytes = cipher.doFinal(inputBytes);

        // Escribimos el archivo descifrado en la salida
        FileOutputStream outputStream = new FileOutputStream(new File(outputFile));
        outputStream.write(outputBytes);

        // Cerramos los streams
        inputStream.close();
        outputStream.close();
    }

    public static void main(String[] args) {
        try {
            // Definimos los nombres de los archivos
            String inputFile = "texto_original.txt";
            String encryptedFile = "texto_encriptado.bin";
            String decryptedFile = "texto_descifrado.txt";

            // Generamos una clave secreta y un vector
            SecretKey key = generateSecretKey();
            IvParameterSpec iv = generateIv();

            // Ciframos el archivo
            encryptFile(inputFile, encryptedFile, key, iv);
            System.out.println("Archivo cifrado correctamente.");

            // Desciframos el archivo
            decryptFile(encryptedFile, decryptedFile, key, iv);
            System.out.println("Archivo descifrado correctamente.");

        } catch (Exception e) {//Capturamos la posible excepción.
            e.printStackTrace();
        }
    }
}
