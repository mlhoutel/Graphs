package Drawing;

import java.util.Map;

import java.awt.BorderLayout;
import javax.swing.*;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.view.mxGraph;

public class Canvas extends JFrame {

    /** Pour éviter un warning venant du JFrame */
    private static final long serialVersionUID = -8123406571694511514L;
    public mxGraph graph;
    public mxGraphComponent component;

    public Canvas(String name) {
        super(name);
        this.graph = new mxGraph();
        this.component = new mxGraphComponent(this.graph);
    }

    public void Draw() {
        this.add(new JScrollPane(this.component), BorderLayout.CENTER);
        // getContentPane().add(this.component);
        
        new mxHierarchicalLayout(this.graph).execute(this.graph.getDefaultParent());
        
        Map<String, Object> edges = this.graph.getStylesheet().getDefaultEdgeStyle();
        edges.put(mxConstants.STYLE_STROKECOLOR , "#ad8aa8");
        edges.put(mxConstants.STYLE_STROKEWIDTH, 3);

        Map<String, Object> vertexs = this.graph.getStylesheet().getDefaultVertexStyle();
        vertexs.put(mxConstants.STYLE_FILLCOLOR, "#1f8bdd");
        vertexs.put(mxConstants.STYLE_STROKECOLOR, "#1f8bdd");
        vertexs.put(mxConstants.STYLE_FONTCOLOR, "#edf1f4");
        vertexs.put(mxConstants.STYLE_FONTSIZE, 17);
        vertexs.put(mxConstants.STYLE_FONTFAMILY, "Roboto");
        vertexs.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_BOLD);
        vertexs.put(mxConstants.STYLE_ROUNDED, true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);
    }
}