package cs1302.api;

import java.net.http.HttpClient;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.control.Button;
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

/**
 * REPLACE WITH NON-SHOUTING DESCRIPTION OF YOUR APP.
 */
public class ApiApp extends Application {
/*    public static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .folloRedirects(HttpClient.Redirect.NORMAL)
        .build();

    public static Gson GSON = new GsonBuilder()
        .setprettyPrinting()
        .create();
*/
    Stage stage;
    Scene scene;
    VBox root;
    HBox hbox;
    VBox left;
    VBox right;

    /**
     * Constructs an {@code ApiApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */

    public ApiApp() {
        root = new VBox();
        hbox = new HBox();
        left = new VBox();
        right = new VBox();

    } // ApiApp

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {

        this.stage = stage;

        // demonstrate how to load local asset using "file:resources/"
        Image bannerImage = new Image("file:resources/header.png");
        Image boxLeft = new Image("file:resources/box.png");
        Image boxRight = new Image("file:resources/box.png");

        ImageView banner = new ImageView(bannerImage);
        ImageView imgLeft = new ImageView(boxLeft);
        ImageView imgRight = new ImageView(boxRight);
        banner.setPreserveRatio(true);
        banner.setFitWidth(640);
        imgLeft.setPreserveRatio(true);
        imgLeft.setFitWidth(320);
        imgRight.setPreserveRatio(true);
        imgRight.setFitWidth(320);

        // buttons
        Button generate = new Button("Generate recipe");
        generate.setLayoutX(100);
        generate.setLayoutY(10);
        // some labels to display information
        Label notice = new Label("Modify the starter code to suit your needs.");

        // setup scene
        //       left.getChildren().addAll(generate, imgLeft);
        right.getChildren().addAll(imgRight);
        StackPane sp = new StackPane(imgLeft, generate);
        hbox.getChildren().addAll(sp, right);
        root.getChildren().addAll(banner, hbox, notice);
        scene = new Scene(root);

        // setup stage
        stage.setTitle("ApiApp!");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.sizeToScene();
        stage.show();

    } // start

} // ApiApp
