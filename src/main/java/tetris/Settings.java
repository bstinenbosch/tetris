package tetris;

import java.io.File;
import java.io.IOException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
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
    private static final int BLOCK_SIZE = 20;
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 20;
    private static final int CORNER = 3;
    private KeyBindings keybindings;
    private Color[] colors;
    private GraphicsContext board;
    private GraphicsContext preview;

    /**
     * settings is an intelligent data class that is used to store game-wide
     * variables.
     */
    public Settings() {
        keybindings = new KeyBindings();
        resetColors();
    }

    public Settings(String path) {
        try {
            loadSettings(path);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            keybindings = new KeyBindings();
            resetColors();
        }
    }

    private void resetColors() {
        colors = new Color[] { Color.CYAN, Color.BLUE, Color.DARKORANGE, Color.YELLOW, Color.LIME,
            Color.PURPLE, Color.RED };
    }

    private void loadSettings(String path)
        throws ParserConfigurationException, SAXException, IOException {
        File inputFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        // get key bindings
        String left = doc.getElementsByTagName("left").item(0).getTextContent();
        String right = doc.getElementsByTagName("right").item(0).getTextContent();
        String rLeft = doc.getElementsByTagName("rotate_left").item(0).getTextContent();
        String rRight = doc.getElementsByTagName("rotate_right").item(0).getTextContent();
        String sDrop = doc.getElementsByTagName("soft_drop").item(0).getTextContent();
        String hDrop = doc.getElementsByTagName("hard_drop").item(0).getTextContent();
        keybindings = new KeyBindings(left, right, rLeft, rRight, sDrop, hDrop);

        // get colors
        colors = new Color[7];
        NodeList nList = doc.getElementsByTagName("color");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String red = eElement.getElementsByTagName("red").item(0).getTextContent();
                String green = eElement.getElementsByTagName("green").item(0).getTextContent();
                String blue = eElement.getElementsByTagName("blue").item(0).getTextContent();
                int intRed = Integer.parseInt(red);
                int intGreen = Integer.parseInt(green);
                int intBlue = Integer.parseInt(blue);
                colors[i] = Color.rgb(intRed, intGreen, intBlue);
            }

        }

    }

    public void saveSettings() {
        // save scores naar een xml bestand
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // build file
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("settings");
            doc.appendChild(rootElement);

            // set keyBindings
            Element keyBindings = doc.createElement("keyBindings");
            rootElement.appendChild(keyBindings);
            String[] keys = keybindings.getKeys().toArray(new String[6]);
            // set Left
            Element left = doc.createElement("left");
            left.appendChild(doc.createTextNode(keys[0]));
            keyBindings.appendChild(left);
            // set Right
            Element right = doc.createElement("right");
            right.appendChild(doc.createTextNode(keys[1]));
            keyBindings.appendChild(right);
            // set rotate_left
            Element rotate_left = doc.createElement("rotate_left");
            rotate_left.appendChild(doc.createTextNode(keys[2]));
            keyBindings.appendChild(rotate_left);
            // set rotate_right
            Element rotate_right = doc.createElement("rotate_right");
            rotate_right.appendChild(doc.createTextNode(keys[3]));
            keyBindings.appendChild(rotate_right);
            // set soft_drop
            Element soft_drop = doc.createElement("soft_drop");
            soft_drop.appendChild(doc.createTextNode(keys[4]));
            keyBindings.appendChild(soft_drop);
            // set soft_drop
            Element hard_drop = doc.createElement("hard_drop");
            hard_drop.appendChild(doc.createTextNode(keys[5]));
            keyBindings.appendChild(hard_drop);

            // Set colors
            Element eColors = doc.createElement("colors");
            rootElement.appendChild(eColors);
            for (int i = 0; i < colors.length; i++) {
                Element color = doc.createElement("color");
                eColors.appendChild(color);

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

            // Save file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/main/resources/settings.xml"));
            transformer.transform(source, result); // Save

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
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
