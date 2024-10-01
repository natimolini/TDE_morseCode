import java.util.ArrayList;
import java.util.Scanner;

public class  Main {
    public static void main(String[] args) {
        Node tree = BinaryTree.initTree();
        Scanner scanner = new Scanner(System.in);

        System.out.println("----CÓDIGO MORSE----");

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Transforme sua mensagem em código morse");
            System.out.println("2 - Transforme seu código morse em mensagem");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.print("\nEscolha uma mensagem para transformar em código Morse: ");
                    String message = scanner.nextLine().toUpperCase();

                    StringBuilder morseCode = new StringBuilder();
                    for (char character : message.toCharArray()) {
                        ArrayList<String> dotsDashes = new ArrayList<>();
                        BinaryTree.getMorseCode(tree, String.valueOf(character), dotsDashes);
                        String code = String.join("", dotsDashes);
                        morseCode.append(code).append(" ");
                    }
                    System.out.println("Código Morse: " + morseCode);
                    break;

                case "2":
                    System.out.print("\nDigite o código Morse para decodificar (separe os caracteres por espaço): ");
                    String morseMessage = scanner.nextLine().trim();
                    String[] morseLetters = morseMessage.split(" ");

                    StringBuilder decodedMessage = new StringBuilder();
                    for (String morseLetter : morseLetters) {
                        String decodedChar = BinaryTree.decodeMorse(tree, morseLetter);
                        decodedMessage.append(decodedChar);
                    }

                    System.out.println("Mensagem decodificada: " + decodedMessage);
                    break;

                case "0":
                    System.out.println("Encerrando...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");

            }
        }
    }
}