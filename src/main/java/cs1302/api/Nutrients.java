package cs1302.api;

/**
 * class that sets and returns information about nutrients in a meal.
 */
public class Nutrients {
    private double energy;
    private double protein;
    private double fat;
    private double carbohydrates;

    /**
     * returns the energy content of the meal.
     * @return energy
     */
    public double getEnergy() {
        return energy;
    }

    /**
     * sets the energy content of the meal.
     * @param energy
     */
    public void setEnergy(double energy) {
        this.energy = energy;
    }

    /**
     * returns the protein content in the meal.
     * @return protein
     */
    public double getProtein() {
        return protein;
    }

    /**
     * sets the protein content in the meal.
     * @param protein
     */
    public void setProtein(double protein) {
        this.protein = protein;
    }

    /**
     * returns the fat content in the meal.
     * @return fat
     */
    public double getFat() {
        return fat;
    }

    /**
     * sets the fat content in the meal.
     * @param fat
     */
    public void setFat(double fat) {
        this.fat = fat;
    }

    /**
     * returns the carbs content in the meal.
     * @return carbs
     */
    public double getCarbs() {
        return carbohydrates;
    }

    /**
     * sets the carbs content in the meal.
     * @param carbohydrates
     */
    public void setCarbs(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }
}
