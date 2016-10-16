package highscore;

import java.io.File;
import java.io.IOException;

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

public class ScoreBoard {

    private GameEntry[] board = new GameEntry[10];
    private int numEntries = 0;

    /**
     * Create a ScoreBoard object holding the 10 best scores.
     *
     * @param path path to highscores xml-file
     */
    public ScoreBoard(String path) {
        try {
            loadScores(path);
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void loadScores(String path)
        throws ParserConfigurationException, SAXException, IOException {
        File inputFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        NodeList gameEntries = doc.getElementsByTagName("gameEntry");
        for (int i = 0; i < gameEntries.getLength(); i++) {
            Node gameEntryNode = gameEntries.item(i);
            if (gameEntryNode.getNodeType() == Node.ELEMENT_NODE) {
                Element gameEntry = (Element) gameEntryNode;
                String name = gameEntry.getElementsByTagName("name").item(0).getTextContent();
                String scoreElement =
                        gameEntry.getElementsByTagName("score").item(0).getTextContent();
                GameEntry entry = new GameEntry(name, Integer.parseInt(scoreElement));
                add(entry);
            }
        }
    }

    /**
     * Save scores to xml file.
     */
    public void saveScores() {
        // save scores naar een xml bestand
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // build file
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("highscores");
            doc.appendChild(rootElement);

            for (int i = 0; i < numEntries; i++) {
                Element gameEntry = doc.createElement("gameEntry");
                rootElement.appendChild(gameEntry);

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(board[i].getName()));
                gameEntry.appendChild(name);

                Element score = doc.createElement("score");
                score.appendChild(doc.createTextNode(Integer.toString(board[i].getScore())));
                gameEntry.appendChild(score);
            }

            // Save file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/main/resources/highscores.xml"));
            transformer.transform(source, result); // Save

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public GameEntry[] getScores() {
        return board;
    }

    /**
     * Add game entry to scoreboard.
     *
     * @param entry game entry
     */
    public void add(GameEntry entry) {
        if (isHighscore(entry.getScore())) {
            int newScore = entry.getScore();
            if (numEntries < board.length || newScore > board[numEntries - 1].getScore()) {
                if (numEntries < board.length) {
                    numEntries++;
                }

                int maxNumEntries = this.numEntries - 1;
                while (maxNumEntries > 0 && board[maxNumEntries - 1].getScore() < newScore) {
                    board[maxNumEntries] = board[maxNumEntries - 1];
                    maxNumEntries--;
                }
                board[maxNumEntries] = entry;
            }
            saveScores();
        }
    }

    public boolean isHighscore(int score) {
        return (numEntries < board.length || score > board[numEntries - 1].getScore());
    }

    /**
     * Returns an ScoreBoard object as a string.
     *
     * @return scoreboard object as single-line string
     */
    public String toString() {
        String result = "{";
        for (int i = 0; i < numEntries; i++) {
            if (i > 0) {
                result += ", ";
            }
            result += board[i].toString();
        }
        result += "}";
        return result;
    }
}
