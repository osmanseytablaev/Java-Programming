import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import java.awt.*;
import java.net.URI;

public class RegistrationV3 extends Application {
    public static void main(String args[]) {
        launch(args);
    }

    public void start(Stage myStage) throws Exception {
        myStage.setTitle("Registration");
        FlowPane rootNode = new FlowPane(10, 10);
        rootNode.setAlignment(Pos.CENTER);
        Scene myScene = new Scene(rootNode, 250, 160);
        myStage.setScene(myScene);
        Label email = new Label("Email");
        TextField em = new TextField();
        em.setPromptText("Enter email");
        em.setPrefColumnCount(15);
        Label password = new Label("Password");
        TextField pw = new TextField();
        em.setPromptText("Enter password");
        em.setPrefColumnCount(15);
        Button signup = new Button("Sign up");
        signup.setOnAction(event -> {
            String url = "http://localhost:3000/test";
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Separator separator = new Separator();
        separator.setPrefWidth(180);
        rootNode.getChildren().addAll(email, em, password, pw, signup);
        myStage.show();
    }
}
