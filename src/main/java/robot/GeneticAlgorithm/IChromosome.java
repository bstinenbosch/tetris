package robot.GeneticAlgorithm;

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

    public void evaluate();

}
