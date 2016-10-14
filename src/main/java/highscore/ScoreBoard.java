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
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("gameEntry");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                String sScore = eElement.getElementsByTagName("score").item(0).getTextContent();
                int score = Integer.parseInt(sScore);
                GameEntry entry = new GameEntry(name, score);
                add(entry);
            }
        }
    }

    public void SaveScores() {
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

    public void add(GameEntry entry) {
        if (isHighscore(entry.getScore())) {
            int newScore = entry.getScore();
            if (numEntries < board.length || newScore > board[numEntries - 1].getScore()) {
                if (numEntries < board.length)
                    numEntries++;

                int i = numEntries - 1;
                while (i > 0 && board[i - 1].getScore() < newScore) {
                    board[i] = board[i - 1];
                    i--;
                }board[i] = entry;
            }
            SaveScores();
        }
    }

    public boolean isHighscore(int score) {
        return (numEntries < board.length || score > board[numEntries - 1].getScore());
    }

    public String toString() {
        String result = "{";
        for (int i = 0; i < numEntries; i++) {
            if (i > 0)
                result += ", ";
            result += board[i].toString();
        }
        result += "}";
        return result;
    }
}
