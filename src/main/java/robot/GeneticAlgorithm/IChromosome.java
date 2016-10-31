package robot.GeneticAlgorithm;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface IChromosome {

    /**
     * change a selection of details on the chromosome.
     */
    public void mutate();

    /**
     * combine yourself with another cromosme and deliver a baby.
     */
    public IChromosome procreate(IChromosome partner);

    /**
     * report the fitness of this chromosome.
     */
    public double getFitness();

    /**
     * set the fitness of this chromosome.
     * 
     * @param fitness
     *            the fitness.
     */
    public void setFitness(double fitness);

    /**
     * evaluate the state of the network; do not return but set the state of the
     * network accordingly.
     */
    public void evaluate();

    /**
     * save the state in an XML file.
     * 
     * @param root
     */
    public void saveState(Document doc, Element root);
}
