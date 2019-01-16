package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;

import java.math.RoundingMode;
import java.text.NumberFormat;

public class TipCalculatorController {
   private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
   private static final NumberFormat percent = NumberFormat.getPercentInstance();
   private double tipPercentage = 0.15;

   @FXML
   private Label tipPercentageLabel;

   @FXML
   private TextField amountTextField;

   @FXML
   private TextField tipTextField;

   @FXML
   private TextField totalTextField;

   @FXML
         private Slider tipPercentageSlider;

         @FXML
         public void calculateButtonPressed(ActionEvent actionEvent) {
            try {
               double amount = Double.parseDouble(amountTextField.getText());
               double tip = amount * tipPercentage;
               double total = amount + tip;

               tipTextField.setText(currency.format(tip));
               totalTextField.setText(currency.format(total));
            } catch (NumberFormatException e) {
               amountTextField.setText("Enter amount in dollars");
               amountTextField.selectAll();
               amountTextField.requestFocus();
      }
   }

   public void initialize() {
      currency.setRoundingMode(RoundingMode.HALF_UP);

      //listener for changes to tipPercentage slider
      tipPercentageSlider.valueProperty().addListener(
            new ChangeListener<Number>() {
               @Override
               public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                  tipPercentage = newValue.intValue() / 100.0;
                  tipPercentageLabel.setText(percent.format(tipPercentage));
               }
            }
      );
   }
}
