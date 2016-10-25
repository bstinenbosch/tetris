package robot.GeneticAlgorithm;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;

import logging.Logger;
import robot.ANN.RandomNeuralNetwork;
import robot.ANN.Neuron.IInput;
import robot.ANN.Neuron.IOutput;
import robot.ANN.functions.EvaluationFunction;

public class GeneticAlgorithm {

    private static final double MUTATION_PROBABILITY = 0.05;
    private LinkedList<IChromosome> population = new LinkedList<>();
    private LinkedList<IChromosome> newborns = new LinkedList<>();
    private ArrayList<Double> generationFitness = new ArrayList<>();
    private final double generationSize;
    private double totalFitness;
    Random random = new Random();

    public GeneticAlgorithm(int populationStart, EvaluationFunction function, IInput[] inputs,
        IOutput[] outputs) {
        generationSize = populationStart;
        while (newborns.size() < generationSize) {
            newborns.push(new RandomNeuralNetwork(function, inputs, outputs));
        }
    }

    /**
     * return the next newborn and if necessary cycle the generation.
     * 
     * @return the next newborn.
     */
    public IChromosome getNextRobot() {
        if (newborns.isEmpty()) {
            cycle();
        }
        population.push(newborns.pop());
        return population.getFirst();

    }

    /**
     * perform the three evolutionary steps: kill, procreate, mutate.
     */
    private void cycle() {
        recountTotalFitness();
        generationFitness.add(totalFitness / generationSize);
        saveState();
        cullUnfit();
        procreate();
        mutatePopulation();
    }

    private void saveState() {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Attr attr = doc.createAttribute("fitness");
            attr.setNodeValue(Double.toString(totalFitness / generationSize));
            doc.appendChild(attr);
            for (IChromosome chromosome : population) {
                chromosome.saveState(doc);
            }
            saveStateToFile(doc);
        } catch (ParserConfigurationException exception) {
            Logger.warning(this,
                "Could not save state of the genetic algorithm. Is the parser correctly configured?");
        }
    }

    private void saveStateToFile(Document doc) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/main/resources/gen_algo"
                + new Timestamp(new Date().getTime()).toString() + ".xml"));
            try {
                transformer.transform(source, result);
            } catch (TransformerException exception) {
                Logger.warning(this, "Could not write the saved state to file.");
            } // Save
        } catch (TransformerConfigurationException exception1) {
            Logger.warning(this,
                "Could not transform the state to a file. Is the transformer configured correctly?");
        }
    }

    /**
     * let the selectedMates repopulate civilization.
     * 
     * @param selectedMates
     * 
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private void procreate() {
        while (population.size() + newborns.size() < generationSize) {
            IChromosome mother, father;
            mother = population.get(random.nextInt(population.size()));
            father = population.get(random.nextInt(population.size()));
            // yes, possibly mother==father. Keep an open mind!
            newborns.push(mother.procreate(father));
        }
    }

    /**
     * Be that eugenicist and mark everyone for culling who performs below
     * average.
     * 
     */
    private void cullUnfit() {
        population
            .removeIf((chromosome) -> chromosome.getFitness() < totalFitness / generationSize);
    }

    /**
     * Mutate a small portion of the member of the population.
     */
    private void mutatePopulation() {
        for (int i = 0; i < population.size(); i++) {
            if (random.nextDouble() < MUTATION_PROBABILITY || newborns.size() == 0) {
                population.get(i).mutate();
                newborns.push(population.remove(i));
            }
        }
    }

    /**
     * update the fitness of each individual and return the population average
     * fitness.
     * 
     */
    private void recountTotalFitness() {
        totalFitness = 0;
        for (IChromosome chromosome : population) {
            totalFitness += chromosome.getFitness();
        }
    }
}
