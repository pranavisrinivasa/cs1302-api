package cs1302.api;

import java.util.List;

/**
 * class that gets and sets the name of the meals and stores it in a list of meals.
 */
public class RandomRecipe {
    private List<Meal> meals;

    /**
     * returns the list of meals.
     * @return meals
     */
    public List<Meal> getMeals() {
        return meals;
    }


    /**
     * sets the list of meals.
     * @param meals
     */
    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }


    /**
     * class that sets and gets information about the meals.
     */
    public static class Meal {
        private String strMeal;
        private String strMealThumb;

        /**
         * returns the name of the meal that was generated.
         * @return strMeal
         */
        public String getStrMeal() {
            return strMeal;
        }

        /**
         * sets the name of the meal that was generated.
         * @param strMeal
         */
        public void setStrMeal(String strMeal) {
            this.strMeal = strMeal;
        }

        /**
         * returns the image url of the meal that was generated.
         * @return strMealThumb
         */
        public String getStrMealThumb() {
            return strMealThumb;
        }

        /**
         * sets the image url of the meal that was generated.
         */
        public void setStrMealThumb() {
            this.strMealThumb = strMealThumb;
        }

    }
}
