package Drawing;

import java.util.Map;

import java.awt.BorderLayout;
import javax.swing.*;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.orthogonal.mxOrthogonalLayout;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxOrganicLayout;
import com.mxgraph.layout.mxPartitionLayout;
import com.mxgraph.layout.mxStackLayout;

import com.mxgraph.view.mxGraph;

public class Canvas extends JFrame {

    /** Pour éviter un warning venant du JFrame */
    private static final long serialVersionUID = -8123406571694511514L;
    public static enum Layout { HIERARCHICAL, CIRCLE, TREE, ORGANIC, PARTITION, STACK };

    Boolean directed;
    Layout layout;
    public mxGraph graph;
    public mxGraphComponent component;

    public Canvas(String name, Boolean directed, Layout layout) {
        super(name);
        this.directed = directed;
        this.layout = layout;
        this.graph = new mxGraph();
        this.component = new mxGraphComponent(this.graph);
    }

    public void Draw() {
        this.add(new JScrollPane(this.component), BorderLayout.CENTER);
        // getContentPane().add(this.component);
        
        switch (this.layout) {
            case HIERARCHICAL: {
                mxHierarchicalLayout ly = new mxHierarchicalLayout(this.graph);
                ly.execute(this.graph.getDefaultParent());
            } break;
            case CIRCLE: {
                mxCircleLayout ly = new mxCircleLayout(this.graph, 200.0);
                ly.execute(this.graph.getDefaultParent());
            } break;
            case TREE: {
                new mxCompactTreeLayout(this.graph).execute(this.graph.getDefaultParent());
            } break;
            case ORGANIC: {
                new mxFastOrganicLayout(this.graph).execute(this.graph.getDefaultParent());
            } break;
            case PARTITION: {
                new mxPartitionLayout(this.graph).execute(this.graph.getDefaultParent());
            } break;
            case STACK: {
                new mxStackLayout(this.graph).execute(this.graph.getDefaultParent());
            } break;
        }

        Map<String, Object> vertexs = this.graph.getStylesheet().getDefaultVertexStyle();
        vertexs.put(mxConstants.STYLE_FILLCOLOR, "#1f8bdd");
        vertexs.put(mxConstants.STYLE_STROKECOLOR, "#1f8bdd");
        vertexs.put(mxConstants.STYLE_FONTCOLOR, "#edf1f4");
        vertexs.put(mxConstants.STYLE_FONTSIZE, 17);
        vertexs.put(mxConstants.STYLE_FONTFAMILY, "Roboto");
        vertexs.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_BOLD);
        vertexs.put(mxConstants.STYLE_ROUNDED, true);

        Map<String, Object> edges = graph.getStylesheet().getDefaultEdgeStyle();
        edges.put(mxConstants.STYLE_STROKECOLOR , "#ad8aa8");
        edges.put(mxConstants.STYLE_STROKEWIDTH, 3);
        if (!this.directed) {
            edges.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);
        }
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);
    }
}