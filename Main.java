import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Node tree = BinaryTree.initTree();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Codificar mensagem para código Morse");
            System.out.println("2 - Decodificar código Morse para mensagem");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("\nEscolha uma mensagem para transformar em código Morse: ");
                    String message = scanner.nextLine().toUpperCase();

                    StringBuilder morseCode = new StringBuilder();
                    for (char character : message.toCharArray()) {
                        ArrayList<String> dotsDashes = new ArrayList<>();
                        BinaryTree.getMorseCode(tree, String.valueOf(character), dotsDashes);
                        String code = String.join("", dotsDashes);
                        morseCode.append(code).append(" ");
                    }
                    System.out.println("Código Morse: " + morseCode.toString());
                    break;

                case 2:
                    System.out.print("\nDigite o código Morse para decodificar (separe os caracteres por espaço): ");
                    String morseMessage = scanner.nextLine().trim();
                    String[] morseLetters = morseMessage.split(" ");

                    StringBuilder decodedMessage = new StringBuilder();
                    for (String morseLetter : morseLetters) {
                        String decodedChar = BinaryTree.decodeMorse(tree, morseLetter);
                        decodedMessage.append(decodedChar);
                    }
                    System.out.println("Mensagem decodificada: " + decodedMessage.toString());
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}