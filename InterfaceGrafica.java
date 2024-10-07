import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class InterfaceGrafica extends JFrame {
    private Node tree;
    private JTextArea inputArea;
    private JTextArea outputArea;
    private Set<Character> usedChars;
    private TreePanel treePanel;

    public InterfaceGrafica() {
        setTitle("Código Morse - Árvore Binária");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Color darkRed = new Color(0x921A40);
        Color mediumRed = new Color(0xC75B7A);
        Color lightPink = new Color(0xD9ABAB);
        Color veryLightPink = new Color(0xF4D9D0);
        Font font = new Font("SansSerif", Font.BOLD, 16);

        tree = BinaryTree.initTree();
        treePanel = new TreePanel(tree);
        treePanel.setBackground(veryLightPink);
        add(treePanel, BorderLayout.CENTER);

        inputArea = new JTextArea(3, 20);
        inputArea.setBackground(lightPink);
        inputArea.setFont(font);
        outputArea = new JTextArea(3, 20);
        outputArea.setEditable(false);
        outputArea.setBackground(lightPink);
        outputArea.setFont(font);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.setBackground(veryLightPink);
        JLabel inputLabel = new JLabel("Mensagem/Código Morse:");
        inputLabel.setFont(font);
        JLabel outputLabel = new JLabel("Resultado:");
        outputLabel.setFont(font);
        inputPanel.add(inputLabel);
        inputPanel.add(inputArea);
        inputPanel.add(outputLabel);
        inputPanel.add(outputArea);
        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(mediumRed);
        JButton toMorseButton = new JButton("Mensagem para Morse");
        JButton toMessageButton = new JButton("Morse para Mensagem");

        toMorseButton.setFont(font);
        toMorseButton.setForeground(Color.WHITE);
        toMorseButton.setBackground(darkRed);
        toMessageButton.setFont(font);
        toMessageButton.setForeground(Color.WHITE);
        toMessageButton.setBackground(darkRed);

        usedChars = new HashSet<>();

        toMorseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = inputArea.getText().toUpperCase().trim();
                if (!message.isEmpty()) {
                    outputArea.setText(BinaryTree.getMorseCodeFromString(message));
                    updateUsedChars(message);
                } else {
                    outputArea.setText("Digite uma mensagem válida.");
                }
                treePanel.repaint();
            }
        });

        toMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String morseMessage = inputArea.getText().trim();
                if (!morseMessage.isEmpty()) {
                    String decodedMessage = BinaryTree.getStringFromMorseCode(morseMessage);
                    outputArea.setText(decodedMessage);
                    updateUsedChars(decodedMessage);  // Atualiza os caracteres usados com a mensagem decodificada
                } else {
                    outputArea.setText("Digite um código Morse válido.");
                }
                treePanel.repaint();
            }
        });

        buttonPanel.add(toMorseButton);
        buttonPanel.add(toMessageButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateUsedChars(String message) {
        usedChars.clear();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c) || Character.isDigit(c)) {
                usedChars.add(c);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfaceGrafica gui = new InterfaceGrafica();
            gui.setVisible(true);
        });
    }

    class TreePanel extends JPanel {
        private Node root;
        private int nodeWidth = 20;
        private int nodeHeight = 30;
        private int horizontalGap = 140;
        private int verticalGap = 55;

        public TreePanel(Node root) {
            this.root = root;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawFilteredTree(g, root, getWidth() / 2, 30, 200);
        }

        private void drawFilteredTree(Graphics g, Node node, int x, int y, int xOffset) {
            if (node != null) {
                if (node.value != null && !node.value.isEmpty() && usedChars.contains(node.value.charAt(0))) {
                    g.drawRect(x - nodeWidth / 2, y, nodeWidth, nodeHeight);
                    g.drawString(node.value, x - nodeWidth / 4, y + nodeHeight / 2);
                }
                
                if (node.left != null) {
                    g.drawLine(x, y + nodeHeight, x - xOffset, y + verticalGap);
                    drawFilteredTree(g, node.left, x - xOffset, y + verticalGap, xOffset / 2);
                }
                if (node.right != null) {
                    g.drawLine(x, y + nodeHeight, x + xOffset, y + verticalGap);
                    drawFilteredTree(g, node.right, x + xOffset, y + verticalGap, xOffset / 2);
                }
            }
        }

    }
}

