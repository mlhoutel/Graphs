
import javax.swing.*;

import org.jgraph.*;

public class Draw extends JFrame {

    /** Pour éviter un warning venant du JFrame */
    private static final long serialVersionUID = -8123406571694511514L;

    public Draw() {
        super("JGrapghX tutoriel: Exemple 1");

        JGraph graph = new JGraph();
        this.getContentPane().add(new JScrollPane(graph));
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Draw frame = new Draw();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 320);
        frame.setVisible(true);
    }
}