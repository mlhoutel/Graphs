package Drawing.DrawCanvas;

import java.awt.*;
import java.util.Map;

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


    public static final String BACKGROUND = "#b3bab9";
    public static final String NODES_LABEL = "#d5e1e8";
    public static final String EDGES_LABEL = "#2C3A47";
    public static final String NODES_BACKGROUND = "#364240";
    public static final String EDGES_BACKGROUND = "#1B9CFC";

    public static final Integer WIDTH = 35;
    public static final Integer HEIGHT = 35;

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

        this.Styling();
    }

    public void Styling() {
        //this.setUndecorated(true);

        //mxConstants.RECTANGLE_ROUNDING_FACTOR = 10;

        this.component.getViewport().setOpaque(true);
        this.component.getViewport().setBackground(Color.decode(BACKGROUND));

        Map<String, Object> vertexs = this.graph.getStylesheet().getDefaultVertexStyle();
        vertexs.put(mxConstants.STYLE_FONTSIZE, 16);
        vertexs.put(mxConstants.STYLE_FONTFAMILY, "Roboto");
        vertexs.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_BOLD);
        vertexs.put(mxConstants.STYLE_ROUNDED, true);
        vertexs.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        vertexs.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_MIDDLE);

        vertexs.put(mxConstants.STYLE_FILLCOLOR, NODES_BACKGROUND);
        vertexs.put(mxConstants.STYLE_STROKECOLOR, NODES_BACKGROUND);
        vertexs.put(mxConstants.STYLE_FONTCOLOR, NODES_LABEL);

        Map<String, Object> edges = graph.getStylesheet().getDefaultEdgeStyle();
        edges.put(mxConstants.STYLE_STROKECOLOR , EDGES_BACKGROUND);
        edges.put(mxConstants.STYLE_STROKEWIDTH, 3);
        edges.put(mxConstants.STYLE_FONTSIZE, 16);
        edges.put(mxConstants.STYLE_FONTFAMILY, "Roboto");
        edges.put(mxConstants.STYLE_FONTCOLOR, EDGES_LABEL);
        edges.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_BOLD);

        if (!this.directed) {
            edges.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);
        }


    }

    public void Draw() {
        this.getContentPane();
        JScrollPane scroll = new JScrollPane(this.component);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        this.add(scroll, BorderLayout.CENTER);
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

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
    }
}