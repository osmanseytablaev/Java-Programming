import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class DesktopClient extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage myStage) {
        myStage.setTitle("DesktopClient");
        FlowPane rootNode = new FlowPane(10, 10);
        rootNode.setAlignment(Pos.CENTER);
        Scene myScene = new Scene(rootNode, 250, 200);
        myStage.setScene(myScene);
        Label email = new Label("Text");
        TextField em = new TextField();
        em.setPromptText("Enter text");
        em.setPrefColumnCount(15);
        Button signup = new Button("Submit");
        Label responseLabel = new Label();
        responseLabel.setWrapText(true);
        responseLabel.setMaxWidth(240);
        signup.setOnAction(event -> {
            try {
                String responseText = URLEncoder.encode(em.getText(), StandardCharsets.UTF_8);
                String urlString = "http://localhost:4004/?email=" + responseText;
                URL url = new URL(urlString);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String response = reader.readLine();

                Platform.runLater(() -> responseLabel.setText(response));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        DropShadow shadow = new DropShadow();
        signup.setEffect(shadow);

        rootNode.getChildren().addAll(email, em, responseLabel, signup);

        myStage.show();

    }
    static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] pair = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }


}


/*Label password = new Label("Password");
        TextField pw = new TextField();
        pw.setPromptText("Enter password");
        pw.setPrefColumnCount(15);*/
/*Separator separator = new Separator();
        separator.setPrefWidth(200);
        Image image = new Image("file:Images/instagram.png");
        ImageView imageView = new ImageView(image);
        Button instagram = new Button("", imageView);
        instagram.setPrefSize(5, 5);
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        instagram.setStyle("-fx-background-color: transparent;");
        instagram.setOnAction(event -> {
            String url = "https://www.instagram.com/accounts/emailsignup/";
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
        Image image2 = new Image("file:Images/facebook.png");
        ImageView imageView2 = new ImageView(image2);
        Button facebook = new Button("", imageView2);
        facebook.setPrefSize(5, 5);
        imageView2.setFitWidth(30);
        imageView2.setFitHeight(30);
        facebook.setStyle("-fx-background-color: transparent;");
        facebook.setOnAction(event -> {
            String url = "https://www.facebook.com/reg/";
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
        Image image3 = new Image("file:Images/twitter.png");
        ImageView imageView3 = new ImageView(image3);
        Button twitter = new Button("", imageView3);
        twitter.setPrefSize(5, 5);
        imageView3.setFitWidth(30);
        imageView3.setFitHeight(30);
        twitter.setStyle("-fx-background-color: transparent;");
        twitter.setOnAction(event -> {
            String url = "https://twitter.com/i/flow/signup";
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });*/
