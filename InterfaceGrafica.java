import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGrafica extends JFrame {
    private Node tree;

    public InterfaceGrafica() {
        setTitle("Árvore Binária");
        setSize(900, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tree = BinaryTree.initTree();
        TreePanel treePanel = new TreePanel(tree);
        add(treePanel, BorderLayout.CENTER);

        JButton showMorseButton = new JButton("Mostrar Código Morse");
        showMorseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfaceGrafica gui = new InterfaceGrafica();
            gui.setVisible(true);
        });
    }
}