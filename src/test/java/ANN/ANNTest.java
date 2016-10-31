package ANN;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import robot.ANN.RandomNeuralNetwork;
import robot.ANN.functions.EvaluationFunction;

public class ANNTest {

    @Test
    public void testPathGeneration() throws Exception {
        RandomNeuralNetwork network = new RandomNeuralNetwork(EvaluationFunction.SIGMOID);
        network.addInputToNetwork(() -> .5);
        network.addInputToNetwork(() -> .2);
        network.addOutputToNetwork((charge) -> {
        });
        for (int i = 0; i < 40; i++) {
            network.mutate();
        }

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // build file
        Document doc = docBuilder.newDocument();
        Element root = doc.createElement("Root");
        doc.appendChild(root);
        network.saveState(doc, root);

        // Save file
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("path.xml"));
        transformer.transform(source, result); // Save

    }
}
