import java.util.Currency;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CurrencyConverterApp extends Application {

    private static final double USD_TO_EUR = 0.84;
    private static final double USD_TO_JPY = 109.29;

    private TextField amountTextField;
    private ComboBox<Currency> fromComboBox;
    private ComboBox<Currency> toComboBox;
    private Label resultLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        amountTextField = new TextField();
        amountTextField.setPromptText("Enter amount");

        fromComboBox = new ComboBox<>();
        fromComboBox.getItems().addAll(Currency.getInstance("USD"), Currency.getInstance("EUR"), Currency.getInstance("JPY"));
        fromComboBox.setValue(Currency.getInstance("USD"));

        toComboBox = new ComboBox<>();
        toComboBox.getItems().addAll(Currency.getInstance("USD"), Currency.getInstance("EUR"), Currency.getInstance("JPY"));
        toComboBox.setValue(Currency.getInstance("EUR"));

        resultLabel = new Label();

        Button convertButton = new Button("Convert");
        convertButton.setOnAction(e -> convert());

        grid.add(new Label("Amount:"), 0, 0);
        grid.add(amountTextField, 1, 0);
        grid.add(new Label("From:"), 0, 1);
        grid.add(fromComboBox, 1, 1);
        grid.add(new Label("To:"), 0, 2);
        grid.add(toComboBox, 1, 2);
        grid.add(convertButton, 0, 3);
        grid.add(resultLabel, 1, 3);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Currency Converter");
        primaryStage.show();
    }

    private void convert() {
        try {
            double amount = Double.parseDouble(amountTextField.getText());
            Currency from = fromComboBox.getValue();
            Currency to = toComboBox.getValue();
            double convertedAmount = convert(amount, from, to);
            resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, from.getCurrencyCode(), convertedAmount, to.getCurrencyCode()));
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input");
        } catch (IllegalArgumentException e) {
            resultLabel.setText(e.getMessage());
        }
    }

    private double convert(double amount, Currency from, Currency to) {
        if (from == Currency.getInstance("USD")) {
            if (to == Currency.getInstance("EUR")) {
                return amount * USD_TO_EUR;
            } else if (to == Currency.getInstance("JPY")) {
                return amount * USD_TO_JPY;
            }
        } else if (from == Currency.getInstance("EUR")) {
            if (to == Currency.getInstance("USD")) {
                return amount / USD_TO_EUR;
            }
        } else if (from == Currency.getInstance("JPY")) {
            if (to == Currency.getInstance("USD")) {
                return amount / USD_TO_JPY;
            }
        }
        throw new IllegalArgumentException("Unsupported conversion");
    }
}
