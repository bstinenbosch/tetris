package robot.ANN;

import java.util.LinkedList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Path extends LinkedList<PathNode> {
    private static final long serialVersionUID = -241445188879056618L;

    public void saveState(Document doc, Element network) {
        Element path = doc.createElement("path");
        network.appendChild(path);
        for (PathNode node : this) {
            Element docNode = doc.createElement("node");
            path.appendChild(docNode);
            docNode.setAttribute("weight", String.valueOf(node.getWeight()));
            docNode.setAttribute("neuron", node.getNeuron().toString());
        }
    }

}
