package org.proyecto;

import java.io.*;
import java.util.Scanner;

public class App {
    private final char[] baseAlphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '.', ',', '"', ':', '-', '!', '?', ' '};

    private Cipher cipher;
    private Decipher decipher;

    public App() {
        cipher = new Cipher();
        decipher = new Decipher();
    }

    public void run() {
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
            System.out.print("Elige una opción: ");

            try {
                option = Integer.valueOf(s.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("----> ¡OPCION: Error de tipo de dato.!");
                System.exit(0);
            }

            replay = switch (option) {
                case 1 -> cipher.cifrar(baseAlphabet);
                case 2 -> decipher.descifrar(baseAlphabet);
                case 3 -> decipher.descifrarPorFuerzaBruta(baseAlphabet);
                case 4 -> decipher.descifrarPorEstadistica(baseAlphabet);
                case 5 -> finalizarPrograma();
                default -> opcionIncorrecta();
            };

        } while (replay);
    }

    private boolean opcionIncorrecta() {
        System.out.println("Opción no válida...");
        return true;
    }

    private boolean finalizarPrograma() {
        System.out.println("Finalizando programa...");
        return false;
    }

    static StringBuilder readFromFile(String inputFileName) {
        StringBuilder textString = new StringBuilder("");
        StringBuilder textStringAux = new StringBuilder("");
        File inpurFile = new File(inputFileName);

        try (InputStream inputStream = new FileInputStream(inpurFile)) {
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                textString.append(line + ' ');
            }
            textStringAux.append(textString.substring(0, textString.length() - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("--> CIPHERFILE: Archivo vacío");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("--> INPUTFILE: Error al leer el archivo");
            System.exit(0);
        }

        return textStringAux;
    }

    static void saveOnFile(StringBuilder textString, String outputFileName) {
        File outputFile = new File(outputFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(textString.toString());
        } catch (IOException e) {
            System.out.println("--> OUTPUTFILE: Problemas al escribir en el archivo.");
            System.exit(0);
        }
    }

}
