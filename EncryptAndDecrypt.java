package com.company.Cryptography;


import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.Signature;
import javax.crypto.Cipher;
import java.io.File;
import java.util.Scanner;


public class EncryptAndDecrypt {
    public static void main(String [] args) throws Exception {


                    //Creating signature object
                    Signature sign = Signature.getInstance("SHA256withRSA");

                    // Creating KeyPair generator Object
                    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

                    // Initializing the key pair Genrator
                    keyPairGen.initialize(2048);

                    // Generate the pair the key
                    KeyPair pair = keyPairGen.generateKeyPair();

                    //Getting the public key from the key pair
                    PublicKey publicKey = pair.getPublic();

                    // Creating a Cipher object
                    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

                    //Initializing the cipher object
                    cipher.init(Cipher.ENCRYPT_MODE, publicKey);


                    // File Reading
                    try{
                        File myObj = new File("src\\com\\company\\Cryptography\\Data");
                        Scanner myReader = new Scanner(myObj);
                        while (myReader.hasNext()) {
                            String data = myReader.nextLine();
                            System.out.println(data);

                    //Add data to the cipher
                    String message = data;
                    System.out.println(message.length());
                    byte[] input = message.getBytes();
                    cipher.update(input);

                    //encrypt the data
                    byte[] cipherText = cipher.doFinal();
                    System.out.println(new String(cipherText, "UTF8"));

                            FileWriter myWriter = new FileWriter("Encrypted.txt");
                            myWriter.write(String.valueOf(cipherText));
                            myWriter.close();
                            System.out.println("Sucessfully Wrote the File SIR, Check it should be encrypted!!!");


                            //Initializing the same cipher for decryption
                    cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());


                    //Decrypt the text
                    byte[] decipheredText = cipher.doFinal(cipherText);
                    System.out.println(new String(decipheredText));
                }
                myReader.close();
            } catch( FileNotFoundException e) {
                System.out.println("An error occured");
                e.printStackTrace();
            }
    }

}