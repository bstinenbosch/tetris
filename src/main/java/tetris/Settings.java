package tetris;

import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Settings {
    private File savePath = new File("src/main/resources/settings.xml");
    private static final int BLOCK_SIZE = 20;
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 20;
    private static final int CORNER = 3;
    private KeyBindings keybindings = new KeyBindings();
    private Color[] colors = new Color[7];
    private GraphicsContext board;
    private GraphicsContext preview;

    /**
     * settings is an intelligent data class that is used to store game-wide
     * variables.
     */
    public Settings() {
        resetColors();
        try {
            loadSettings();
        } catch (ParserConfigurationException | SAXException | IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Creates a Settings object holding several game-wide settings.
     *
     * @param path path to settings xml-file
     */
    public Settings(String path) {
        savePath = new File(path);
        resetColors();
        try {
            loadSettings();
        } catch (ParserConfigurationException | SAXException | IOException exception) {
            exception.printStackTrace();
        }
    }

    private void resetColors() {
        colors = new Color[] { Color.CYAN, Color.BLUE, Color.DARKORANGE, Color.YELLOW, Color.LIME,
            Color.PURPLE, Color.RED };
    }

    private void loadSettings() throws ParserConfigurationException, SAXException, IOException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(savePath);
        doc.getDocumentElement().normalize();
        readKeyBindings(doc.getElementsByTagName("keyBindings").item(0));
        readColors(doc.getElementsByTagName("color"));
    }

    private void readColors(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) item;
                String red = element.getElementsByTagName("red").item(0).getTextContent();
                String green = element.getElementsByTagName("green").item(0).getTextContent();
                String blue = element.getElementsByTagName("blue").item(0).getTextContent();
                int intRed = Integer.parseInt(red);
                int intGreen = Integer.parseInt(green);
                int intBlue = Integer.parseInt(blue);
                colors[i] = Color.rgb(intRed, intGreen, intBlue);
            }
        }
    }

    private void readKeyBindings(Node node) {
        for (int i = 0; i < node.getAttributes().getLength(); i++) {
            String key = node.getAttributes().item(i).getNodeName();
            KeyCode binding = KeyCode.getKeyCode(node.getAttributes().item(i).getNodeValue());
            keybindings.put(key, binding);
        }
    }

    /**
     * Save settings to file.
     */
    public void saveSettings() {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = doc.createElement("settings");
            doc.appendChild(rootElement);
            writeKeyBindings(doc, rootElement);
            writeColors(doc, rootElement);
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(savePath);
            TransformerFactory.newInstance().newTransformer().transform(source, result);
        } catch (ParserConfigurationException | TransformerException exception) {
            exception.printStackTrace();
        }
    }

    private void writeColors(Document doc, Element rootElement) {
        Element docElement = doc.createElement("colors");
        rootElement.appendChild(docElement);
        for (int i = 0; i < colors.length; i++) {
            Element color = doc.createElement("color");
            docElement.appendChild(color);

            Element red = doc.createElement("red");
            double value = colors[i].getRed() * 255;
            long colorValue = Math.round(value);
            red.appendChild(doc.createTextNode(Long.toString(colorValue)));
            color.appendChild(red);

            Element green = doc.createElement("green");
            value = colors[i].getGreen() * 255;
            colorValue = Math.round(value);
            green.appendChild(doc.createTextNode(Long.toString(colorValue)));
            color.appendChild(green);

            Element blue = doc.createElement("blue");
            value = colors[i].getBlue() * 255;
            colorValue = Math.round(value);
            blue.appendChild(doc.createTextNode(Long.toString(colorValue)));
            color.appendChild(blue);
        }
    }

    private void writeKeyBindings(Document doc, Element rootElement) {
        String binding;
        String key;
        Element keyBindings = doc.createElement("keyBindings");
        rootElement.appendChild(keyBindings);
        for (Entry<KeyCode, String> entry : keybindings) {
            key = entry.getKey().getName();
            binding = entry.getValue();
            keyBindings.setAttribute(binding, key);
        }
    }

    public Color getColor(int index) {
        return colors[index - 1];
    }

    public void setColor(int index, Color color) {
        colors[index - 1] = color;
    }

    public int boardWidth() {
        return BOARD_WIDTH;
    }

    public int boardHeight() {
        return BOARD_HEIGHT;
    }

    public int blockSize() {
        return BLOCK_SIZE;
    }

    public int corner() {
        return CORNER;
    }

    public KeyBindings getKeyBindings() {
        return keybindings;
    }

    public void setBoard(GraphicsContext gc) {
        board = gc;
    }

    public GraphicsContext getBoard() {
        return board;
    }

    public void setPreview(GraphicsContext gc) {
        preview = gc;
    }

    public GraphicsContext getPreview() {
        return preview;
    }
}
