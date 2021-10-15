package Utils;

import javax.swing.*;

import org.jgraph.*;

public class Canvas extends JFrame {

    /** Pour éviter un warning venant du JFrame */
    private static final long serialVersionUID = -8123406571694511514L;
    public JGraph graph;

    public Canvas() {
        super("Default window name");
        this.graph = new JGraph();
    }

    public Canvas(String name) {
        super(name);
        this.graph = new JGraph();
    }

    public void Draw() {
        this.getContentPane().add(new JScrollPane(this.graph));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);
    }
}