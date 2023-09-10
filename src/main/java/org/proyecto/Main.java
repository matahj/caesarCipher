package org.proyecto;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static char[] baseAlphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '.', ',', '"', ':', '-', '!', '?', ' '};
    private static Hashtable<Character, Character> substitutionalAlphabet;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int option = 5;
        boolean replay = true;

        do {
            System.out.println("\n****JULIO CESAR****");
            System.out.println("1.-Cifrar");
            System.out.println("2.-Descifrar");
            System.out.println("3.-Descifrar por fuerza bruta");
            System.out.println("4.-Descifrar por estadística");
            System.out.println("5.Finalizar programa");
            System.out.print("Elije una opción: ");

            try {
                option = Integer.valueOf(s.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("----> ¡OPCION: Error de tipo de dato.!");
                System.exit(0);
            }

            replay = switch (option) {
                case 1 -> cifrar();
                case 2 -> descifrar();
                case 3 -> descifrarPorFuerzaBruta();
                case 4 -> descifrarPorEstadistica();
                case 5 -> finalizarPrograma();
                default -> opcionIncorrecta();
            };

        } while (replay);
    }

    private static boolean opcionIncorrecta() {
        System.out.println("Opción no válida...");
        return true;
    }

    private static boolean finalizarPrograma() {
        System.out.println("Finalizando programa...");
        return false;
    }

    private static boolean descifrarPorEstadistica() {
        System.out.println("Ataque por estadística...");
        return true;
    }

    private static boolean descifrarPorFuerzaBruta() {
        System.out.println("Ataque por fuerza bruta...");
        return true;
    }

    private static boolean descifrar() {
        System.out.println("Descifrando...");
        return true;
    }

    private static boolean cifrar() {
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

        //Leer archivo try-catch con recursos.
        String plainTextString = "Hola este es un mensaje en claro!";
        //guardar contenido en un arreglo.
        char[] plainTextArray = plainTextString.toCharArray();
        System.out.println(Arrays.toString(plainTextArray));

        //Genera substitution Alphabet
        setSubstitutionAlphabet(cipherKey);
        System.out.println(Arrays.toString(baseAlphabet));
        for (int i = 0; i < baseAlphabet.length; i++) {
            System.out.print(substitutionalAlphabet.get(baseAlphabet[i]) + ", ");
        }
        System.out.println();

        //Cifrar
        char[] cipherTextArray = encriptMessage(plainTextArray);
        System.out.println(Arrays.toString(cipherTextArray));

        //Guardar arreglo en string y en archivo con recursos
        String cipherTextString = cipherTextArray.toString();

        return true;
    }

    private static char[] encriptMessage(char[] plainTextArray) {
        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < plainTextArray.length; i++) {
            if (substitutionalAlphabet.get(plainTextArray[i]) != null) {
                cipherText.append(substitutionalAlphabet.get(plainTextArray[i]));
            }
        }
        return cipherText.toString().toCharArray();
    }

    private static void setSubstitutionAlphabet(int cipherKey) {
        substitutionalAlphabet = new Hashtable<>();
        for (int i = 0; i < baseAlphabet.length; i++) {
            substitutionalAlphabet.put(baseAlphabet[i], baseAlphabet[(i + cipherKey) % baseAlphabet.length]);
        }
    }
}

