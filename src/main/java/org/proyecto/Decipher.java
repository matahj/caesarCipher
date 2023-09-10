package org.proyecto;

import java.util.Hashtable;
import java.util.Scanner;

public class Decipher {

    private static Hashtable<Character, Character> substitutionalAlphabet;

    public boolean descifrar(char[] baseAlphabet) {
        System.out.println("***DESCIFRANDO***");
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


        setSubstitutionAlphabet(cipherKey, baseAlphabet);
        StringBuilder cipherTextString = App.readFromFile(inputFileName);
        StringBuilder plainTextString = decriptMessage(cipherTextString);
        App.saveOnFile(plainTextString, outputFileName);

        return true;
    }

    private StringBuilder decriptMessage(StringBuilder cipherTextString) {
        StringBuilder plainText = new StringBuilder();

        char[] cipherTextArray = cipherTextString.toString().toCharArray();

        for (int i = 0; i < cipherTextArray.length; i++) {
            if (substitutionalAlphabet.get(cipherTextArray[i]) != null) {
                plainText.append(substitutionalAlphabet.get(cipherTextArray[i]));
            }
        }

        return plainText;
    }

    public boolean descifrarPorFuerzaBruta(char[] baseAlphabet) {
        System.out.println("***Descifrando con fuerza bruta***");
        return true;
    }

    public boolean descifrarPorEstadistica(char[] baseAlphabet) {
        System.out.println("***Descifrando con estadística***");
        return true;
    }




    private void setSubstitutionAlphabet(int cipherKey, char[] baseAlphabet) {

        substitutionalAlphabet = new Hashtable<>();

        for (int i = 0; i < baseAlphabet.length; i++) {
            substitutionalAlphabet.put(baseAlphabet[i], baseAlphabet[(i + baseAlphabet.length - cipherKey) % baseAlphabet.length]);
        }

    }
}
