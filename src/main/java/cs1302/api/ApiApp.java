package cs1302.api;

import java.net.http.HttpClient;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.Map;
import javafx.scene.control.ComboBox;
import java.net.URI;
import java.io.IOException;
import java.net.URLEncoder;
import javafx.geometry.Pos;
import java.util.Random;
import javafx.scene.control.TextField;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javafx.scene.layout.StackPane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.geometry.Insets;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * class that extends the Application class.
 * creates a GUI application that asks the user the type of dish they want to make
 * and then gives them the name of a random dish that falls into that category.
 * It uses the name of the dish and queries it into a second API that derives the nutritional
 * value of the dish.
 */
public class ApiApp extends Application {
    Stage stage;
    Scene scene;
    VBox root;
    HBox hbox;
    VBox left;
    VBox right;
    HBox bar;
    VBox instr;
    ComboBox<String> categoryComboBox = new ComboBox<>();


    /**
     * Constructs an {@code ApiApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */

    String mealName;
    String query;
    String result;
    String mealImg;
    int size;

//    String result;

    /**
     * constructor that initializes the scene graph for the GUI application.
     */
    public ApiApp() {
        root = new VBox();
        hbox = new HBox();
        left = new VBox(50);
        right = new VBox();
        bar = new HBox();
        instr = new VBox();
    } // ApiApp

    /** {@inheritDoc} */

    @Override
    public void start(Stage stage) {

        this.stage = stage;

        // demonstrate how to load local asset using "file:resources/"
        Image bannerImage = new Image("file:resources/header.png");
        Image boxLeft = new Image("file:resources/box.png");
        Image boxRight = new Image("file:resources/box.png");
        // Image mealThumb = new Image();
        ImageView banner = new ImageView(bannerImage);
        ImageView imgLeft = new ImageView(boxLeft);
        ImageView imgRight = new ImageView(boxRight);
        ImageView dishImg = new ImageView();
        TextField searchBar = new TextField("type your query here");
        Button searchButton = new Button("Search");
        Label resultLabel = new Label();
        Label mealLabel = new Label();
        Label instructions = new Label("Can't figure out what dish to make? \n Choose a category " +
            "and get a random dish that you \n can make along with its nutritional value");

        banner.setPreserveRatio(true);
        banner.setFitWidth(640);
        imgLeft.setPreserveRatio(true);
        imgLeft.setFitWidth(320);
        imgRight.setPreserveRatio(true);
        imgRight.setFitWidth(320);
        dishImg.setPreserveRatio(true);
        dishImg.setFitWidth(300);
        dishImg.setFitHeight(200);
        mealLabel.setStyle("-fx-font-size: 20px;");
        mealLabel.setUnderline(true);
        resultLabel.setStyle("-fx-font-weight: bold;");
        instructions.setStyle("-fx-font-weight: bold; -fx-text-alignment: center;");
        instr.setAlignment(Pos.CENTER);
//        instr.setBackground(new Background(new BackgroundFill(Color.LIGHTBROWN,
        //          CornerRadii.EMPTY, Insets.EMPTY)));
        instr.setStyle("-fx-background-color: #ffda7c;");

        // some labels to display information
        Label notice = new Label("Modify the starter code to suit your needs.");
        //make comboBox
        categoryComboBox.getItems().addAll("Dessert", "Seafood", "Side",
            "Starter", "Vegan", "Vegetarian");
        categoryComboBox.getSelectionModel().selectFirst();

        // setup scene
        bar.getChildren().addAll(categoryComboBox, searchButton);
        left.getChildren().addAll(mealLabel, dishImg);
        right.getChildren().addAll(resultLabel);
        instr.getChildren().addAll(instructions);
        StackPane spR = new StackPane(imgRight, right);
        StackPane sp = new StackPane(imgLeft, left);
        left.setAlignment(Pos.CENTER);
        right.setAlignment(Pos.CENTER);
        bar.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(sp, spR);
        root.getChildren().addAll(banner, instr, bar, hbox, notice);
        scene = new Scene(root);

        // setup stage
        stage.setTitle("ApiApp!");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.sizeToScene();
        stage.show();


        searchButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    query = categoryComboBox.getValue().toString();
                    System.out.println("User query: " + query);
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://www.themealdb.com/api/json/v1/1/filter.php?c=" + query))
                        .build();
                    HttpResponse<String> response;
                    try {
                        response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        Gson gson = new Gson();
//                        String responseBody = response.body();
                        RandomRecipe randomDish = gson.fromJson(response.body(),RandomRecipe.class);
                        Random random = new Random();
                        size = randomDish.getMeals().size();
                        System.out.println(size);
                        int randomNumber = random.nextInt(size);
                        System.out.println(randomNumber);
                        mealName = randomDish.getMeals().get(randomNumber).getStrMeal();
                        mealImg = randomDish.getMeals().get(randomNumber).getStrMealThumb();
                        mealLabel.setText(mealName);
                        System.out.println(mealImg);
                    } catch (Exception e) {
                        return;
                    }

                    String apiKey = "8b8990c5e53c9caf9a1202885abb11ce";
                    String apiId = "728f2e2a";
                    String apiEndpoint = "https://api.edamam.com/api/food-database/v2/parser";
                    String encodedDishName = URLEncoder.encode(mealName, StandardCharsets.UTF_8);
                    String requestBody = "{\"ingr\":[\"" + encodedDishName + "\"]}";
                    HttpRequest request2 = HttpRequest.newBuilder()
                        .uri(URI.create(apiEndpoint + "?app_id=" + apiId + "&app_key="
                        + apiKey + "&ingr=" + encodedDishName))
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .build();
                    HttpResponse<String> response2;
                    try {
                        response2 = client.send(request2, HttpResponse.BodyHandlers.ofString());
                        // String responseBody = response2.body();
                        Gson gson = new Gson();
                        EdamamResponse foodItem = gson.fromJson(response2.body(),
                            EdamamResponse.class);
                        Gson gson2 = new Gson();
                        Map<String, Object> map = gson2.fromJson(response2.body(), Map.class);
                        List<Map<String, Object>> hints = (List<Map<String, Object>>)
                            map.get("hints");
                        Map<String, Object> food = (Map<String, Object>) hints.get(1).get("food");
                        Map<String, Object> nutrients = (Map<String, Object>) food.get("nutrients");
                        String energy = ((Double) nutrients.get("ENERC_KCAL")).intValue() + " kcal";
                        String protein = nutrients.get("PROCNT") + " g";
                        String fat = nutrients.get("FAT") + " g";
                        String carbs = nutrients.get("CHOCDF") + " g";
                        result = String.format("Energy: %s\nProtein: %s\nFat: %s\nCarbs: %s"
                        , energy, protein, fat, carbs);
                        resultLabel.setText(result);
                    } catch (Exception e) {
                        return;
                    }
                    System.out.println(searchBar);
                    Image mealThumb = new Image(mealImg);
                    dishImg.setImage(mealThumb);
                } // handle;
            });

    } // start
    /*
    public void handleSearch(ActionEvent event) {
        query = categoryComboBox.getValue().toString();
        System.out.println("User query: " + query);
        try {
            RandomRecipe randomDish = getRandomDish(query);
            mealName = randomDish.getMeals().get(random.nextInt(size)).getStrMeal();
            mealImg = randomDish.getMeals().get(random.nextInt(size)).getStrMealThumb();
            mealLabel.setText(mealName);
            EdamamResponse foodItem = getFoodItem(mealName);
            String energy = ((Double) foodItem.hints.get(0).getFood().getNutrients()
            .get("ENERC_KCAL")).intValue() + " kcal";
            String protein = foodItem.getHints().get(0).getFood().getNutrients()
            .get("PROCNT") + " g";
            String fat = foodItem.getHints().get(0).getFood().getNutrients().get("FAT") + " g";
            String carbs = foodItem.getHints().get(0).getFood().getNutrients().get("CHOCDF") + " g";
            result = String.format("Energy: %s\nProtein: %s\nFat: %s\nCarbs: %s",
            energy, protein, fat, carbs);
            resultLabel.setText(result);
            Image mealThumb = new Image(mealImg);
            dishImg.setImage(mealThumb);
        } catch (Exception e) {
            return;
        }
    }

    public RandomRecipe getRandomDish(String query) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://www.themealdb.com/api/json/v1/1/filter.php?c=" + query))
            .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        RandomRecipe randomDish = gson.fromJson(response.body(), RandomRecipe.class);
        size = randomDish.getMeals().size();
        System.out.println(size);
        return randomDish;
    }

    public EdamamResponse getFoodItem(String mealName) throws IOException, InterruptedException {
        String apiKey = "8b8990c5e53c9caf9a1202885abb11ce";
        String apiId = "728f2e2a";
        String apiEndpoint = "https://api.edamam.com/api/food-database/v2/parser";
        String encodedDishName = URLEncoder.encode(mealName, StandardCharsets.UTF_8);
        String requestBody = "{\"ingr\":[\"" + encodedDishName + "\"]}";
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiEndpoint + "?app_id=" + apiId + "&app_key="
            + apiKey + "&ingr=" + encodedDishName))
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        EdamamResponse foodItem = gson.fromJson(response.body(), EdamamResponse.class);
        return foodItem;
        } */
} // ApiApp
