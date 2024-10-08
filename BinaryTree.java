import java.util.ArrayList;
public class BinaryTree {
    public static Node initTree() {
        Node tree = new Node(" ");

        tree.left = new Node("E");
        tree.right = new Node("T");

        tree.left.left = new Node("I");
        tree.left.right = new Node("A");
        tree.right.left = new Node("N");
        tree.right.right = new Node("M");

        tree.left.left.left = new Node("S");
        tree.left.left.right = new Node("U");
        tree.left.right.left = new Node("R");
        tree.left.right.right = new Node("W");
        tree.right.left.left = new Node("D");
        tree.right.left.right = new Node("K");
        tree.right.right.left = new Node("G");
        tree.right.right.right = new Node("O");

        tree.left.left.left.left = new Node("H");
        tree.left.left.left.right = new Node("V");
        tree.left.left.right.left = new Node("F");
        tree.left.left.right.right = new Node("");
        tree.left.right.left.left = new Node("L");
        tree.left.right.left.right = new Node("");
        tree.left.right.right.left = new Node("P");
        tree.left.right.right.right = new Node("J");
        tree.right.left.left.left = new Node("B");
        tree.right.left.left.right = new Node("X");
        tree.right.left.right.left = new Node("C");
        tree.right.left.right.right = new Node("Y");
        tree.right.right.left.left = new Node("Z");
        tree.right.right.left.right = new Node("Q");
        tree.right.right.right.left = new Node("");
        tree.right.right.right.right = new Node("");

        tree.left.left.left.left.left = new Node("5");
        tree.left.left.left.left.right = new Node("4");
        tree.left.left.left.right.left = new Node("");
        tree.left.left.left.right.right = new Node("3");
        tree.left.left.right.left.left = new Node("");
        tree.left.left.right.left.right = new Node("");
        tree.left.left.right.right.left = new Node("");
        tree.left.left.right.left.right = new Node("2");
        tree.left.right.left.left.left = new Node("");
        tree.left.right.left.left.right = new Node("");
        tree.left.right.left.right.left = new Node("+");
        tree.left.right.left.right.right = new Node("");
        tree.left.right.right.left.left = new Node("");
        tree.left.right.right.left.right = new Node("");
        tree.left.right.right.right.left = new Node("");
        tree.left.right.right.right.right = new Node("1");
        tree.right.left.left.left.left = new Node("6");
        tree.right.left.left.left.right = new Node("=");
        tree.right.left.left.right.left = new Node("/");
        tree.right.left.left.right.right = new Node("");
        tree.right.left.right.left.left = new Node("");
        tree.right.left.right.left.right = new Node("");
        tree.right.left.right.right.left = new Node("");
        tree.right.left.right.right.right = new Node("");
        tree.right.right.left.left.left = new Node("7");
        tree.right.right.left.left.right = new Node("");
        tree.right.right.left.right.left = new Node("");
        tree.right.right.left.right.right = new Node("");
        tree.right.right.right.left.left = new Node("8");
        tree.right.right.right.left.right = new Node("");
        tree.right.right.right.right.left = new Node("9");
        tree.right.right.right.right.right = new Node("0");

        return tree;
    }

    private static boolean getMorseCode(Node node, String character, ArrayList<String> code) {
        if (node == null) {
            return false;
        } else if (node.value.equals(character)) {
            return true;
        } else {
            if (getMorseCode(node.left, character, code)) {
                code.add(0, ".");
                return true;
            } else if (getMorseCode(node.right, character, code)) {
                code.add(0, "-");
                return true;
            }
        }
        return false;
    }

    public static String getMorseCodeFromString(String message){
        Node tree = initTree();
        StringBuilder morseCode = new StringBuilder();
        for (char character : message.toCharArray()) {
            ArrayList<String> dotsDashes = new ArrayList<>();
            getMorseCode(tree, String.valueOf(character), dotsDashes);
            String code = String.join("", dotsDashes);
            morseCode.append(code).append(" ");
        }
        return morseCode.toString();
    }


    private static String decodeMorse(Node node, String morseCode) {
        Node current = node;
        for (char symbol : morseCode.toCharArray()) {
            if (symbol == '.') {
                current = current.left;
            } else if (symbol == '-') {
                current = current.right;
            } else if ((symbol != '.') && (symbol != '-')) {
                System.out.println("Só pode '.' ou '-'");
                return "Sem Mensagem";
            }

            if (current == null) {
                return "Código morse inválido!";
            }

        }
        return current.value;
    }

    public static String getStringFromMorseCode(String morseCode){
        Node tree = initTree();
        StringBuilder decodedMessage = new StringBuilder();
        String[] morseLetters = morseCode.split(" ");
        for (String morseLetter : morseLetters) {
            String decodedChar = decodeMorse(tree, morseLetter);
            decodedMessage.append(decodedChar);
        }
        return decodedMessage.toString();
    }
}
