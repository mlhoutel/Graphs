
import javax.swing.*;

import org.jgraph.*;

public class Canvas extends JFrame {

    /** Pour éviter un warning venant du JFrame */
    private static final long serialVersionUID = -8123406571694511514L;

    public Canvas(String name) {
        super(name);
        JGraph graph = new JGraph();
        this.getContentPane().add(new JScrollPane(graph));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 320);
        this.setVisible(true);
    }