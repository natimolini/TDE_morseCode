import javax.swing.*;
import java.awt.*;

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
        drawTree(g, root, getWidth() / 2, 30, 200);
    }

    private void drawTree(Graphics g, Node node, int x, int y, int xOffset) {
        if (node != null) {
            g.drawRect(x - nodeWidth / 2, y, nodeWidth, nodeHeight);
            g.drawString(node.value, x - nodeWidth / 4, y + nodeHeight / 2);

            if (node.left != null) {
                g.drawLine(x, y + nodeHeight, x - xOffset, y + verticalGap);
                drawTree(g, node.left, x - xOffset, y + verticalGap, xOffset / 2);
            }
            if (node.right != null) {
                g.drawLine(x, y + nodeHeight, x + xOffset, y + verticalGap);
                drawTree(g, node.right, x + xOffset, y + verticalGap, xOffset / 2);
            }
        }
    }
}
