package cs1302.api;

import java.util.List;

/**
 * class that stores the responses received from the second api.
 */
public class EdamamResponse {
    private String foodId;
    private String label;
    private Nutrients nutrients;

    /**
     * returns the food id of the meal.
     * @return foodId;
     */
    public String getFoodId() {
        return foodId;
    }

    /**
     * sets the food id of the meal.
     * @param foodId
     */
    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    /**
     * returns the label of the meal.
     * @return label;
     */
    public String getLabel() {
        return label;
    }

    /**
     * sets the label of the meal.
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * returns the nutrients of the meal.
     * @return nutrients
     */
    public Nutrients getNutrients() {
        return nutrients;
    }

    /**
     * sets the nutrients of the meal.
     * @param nutrients
     */
    public void setNutrients(Nutrients nutrients) {
        this.nutrients = nutrients;
    }
}
