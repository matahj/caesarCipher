package org.proyecto;

import java.util.Hashtable;
import java.util.Scanner;

public class Cipher {

    private static Hashtable<Character, Character> substitutionalAlphabet;

    public boolean cifrar(char[] baseAlphabet) {

        System.out.println("***CIFRAR***");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Archivo de entrada: ");
        String inputFileName = scanner.nextLine();

        System.out.print("Llave de cifrado: ");
        int cipherKey = 0;
        try {
            cipherKey = Integer.valueOf(scanner.nextLine()) % baseAlphabet.length;
        } catch (NumberFormatException e) {
            System.out.println("----> ¡CIPHERKEY: Error de tipo de dato.!");
            System.exit(0);
        }

        System.out.print("Archivo de salida:");
        String outputFileName = scanner.nextLine();

        System.out.println("Procesando...");

        StringBuilder plainTextString = App.readFromFile(inputFileName);
        setSubstitutionAlphabet(cipherKey, baseAlphabet);
        StringBuilder cipherTextString = encriptMessage(plainTextString);
        App.saveOnFile(cipherTextString, outputFileName);

        return true;
    }

    private StringBuilder encriptMessage(StringBuilder plainTextString) {
        StringBuilder cipherText = new StringBuilder();

        char[] plainTextArray = plainTextString.toString().toCharArray();

        for (int i = 0; i < plainTextArray.length; i++) {
            if (substitutionalAlphabet.get(plainTextArray[i]) != null) {
                cipherText.append(substitutionalAlphabet.get(plainTextArray[i]));
            }
        }

        return cipherText;
    }

    private void setSubstitutionAlphabet(int cipherKey, char[] baseAlphabet) {

        substitutionalAlphabet = new Hashtable<>();

        for (int i = 0; i < baseAlphabet.length; i++) {
            substitutionalAlphabet.put(baseAlphabet[i], baseAlphabet[(i + cipherKey) % baseAlphabet.length]);
        }

    }

}
