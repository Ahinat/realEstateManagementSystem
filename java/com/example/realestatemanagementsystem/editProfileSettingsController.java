package com.example.realestatemanagementsystem;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import org.json.JSONObject;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class editProfileSettingsController implements Initializable {

    public TextField newEmail;
    public PasswordField newPassword;
    public PasswordField reEnteredNewPassword;
    public ComboBox<String> newCountryCode;
    public TextField newMobileNumber;
    public ComboBox<String> newSecurityQuestionBox;
    public TextField newSecurityAnswer;
    public PasswordField passwordForEditLoginDetails;

    public AnchorPane changeSecurityCredentialsLayer;
    public AnchorPane confirmEditLayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(newSecurityQuestionBox != null) addItemsInSecurityQuestionBox();
        if(newCountryCode != null) addItemsInCountryCodeMobileNumber();

        String userEmail = YourPropertyUserSession.getInstance().getUserEmail();
        String targetUrl = "http://localhost:8080/api/yourpropertyuser";
        try {
            JSONObject jsonUser = HTTPClient.sendGetRequest(targetUrl, userEmail);

            String email = jsonUser.getString("Email");
            newEmail.setText(email);

            String password = jsonUser.getString("Password");
            newPassword.setText(password);
            reEnteredNewPassword.setText(password);

            String mobileNumber = jsonUser.getString("MobileNumber");
            if(mobileNumber.startsWith("+1")){
                newCountryCode.setValue("+1");
                newMobileNumber.setText(mobileNumber.substring(2));
            }
            else if(mobileNumber.startsWith("+44")) {
                newCountryCode.setValue("+44");
                newMobileNumber.setText(mobileNumber.substring(3));
            }
            else if(mobileNumber.startsWith("+880")) {
                newCountryCode.setValue("+880");
                newMobileNumber.setText(mobileNumber.substring(4));
            }
            else if(mobileNumber.startsWith("+91")) {
                newCountryCode.setValue("+91");
                newMobileNumber.setText(mobileNumber.substring(3));
            }
            else if(mobileNumber.startsWith("+978")) {
                newCountryCode.setValue("+978");
                newMobileNumber.setText(mobileNumber.substring(4));
            }

            String securityQuestion = jsonUser.getString("SecurityQuestion");
            newSecurityQuestionBox.setValue(securityQuestion);

            String securityAnswer = jsonUser.getString("SecurityAnswer");
            newSecurityAnswer.setText(securityAnswer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addItemsInSecurityQuestionBox(){
        newSecurityQuestionBox.getItems().addAll(
                "What's your favorite food?",
                "What's your favorite movie?",
                "What's your favorite place?",
                "What's the name of the street your grew in?",
                "What's your favorite animal?",
                "What's your pet name?",
                "What's your bestfriend's name?",
                "What do you hate the most?"
        );
    }

    public void addItemsInCountryCodeMobileNumber(){
        newCountryCode.getItems().addAll(
                "+1",
                "+44",
                "+880",
                "+91",
                "+978"
        );
    }

    public void onNextClicked(ActionEvent actionEvent) {
        changeSecurityCredentialsLayer.setVisible(true);
        TranslateTransition transition1 = new TranslateTransition();
        transition1.setDuration(Duration.seconds(0.5));
        transition1.setNode(changeSecurityCredentialsLayer);
        transition1.setToX(0);
        transition1.play();
    }

    public void onBackFromEditSecurityClicked(ActionEvent actionEvent) {
        TranslateTransition transition1 = new TranslateTransition();
        transition1.setDuration(Duration.seconds(0.5));
        transition1.setNode(changeSecurityCredentialsLayer);
        transition1.setToX(600);
        transition1.play();
        changeSecurityCredentialsLayer.setVisible(false);
    }

    public void onEditClicked(ActionEvent actionEvent) {
        confirmEditLayer.setVisible(true);
        TranslateTransition transition1 = new TranslateTransition();
        transition1.setDuration(Duration.seconds(0.5));
        transition1.setNode(confirmEditLayer);
        transition1.setToX(0);
        transition1.play();
    }

    public void onBackFromConfirmEditClicked(ActionEvent actionEvent) {
        TranslateTransition transition1 = new TranslateTransition();
        transition1.setDuration(Duration.seconds(0.5));
        transition1.setNode(confirmEditLayer);
        transition1.setToX(600);
        transition1.play();
        confirmEditLayer.setVisible(false);
    }

    public void addCustomSecurityQuestion(ActionEvent actionEvent) {

    }

    public void onConfirmEditClicked(ActionEvent actionEvent) {
        // Fetch user email
        String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

        // Get user info from database
        String targetUrl = "http://localhost:8080/api/yourpropertyuser";

        try{
            JSONObject jsonUser = HTTPClient.sendGetRequest(targetUrl, userEmail);
            String userPassword = jsonUser.getString("Password");

            // Getting user input
            String email = newEmail.getText();
            String password = newPassword.getText();
            String mobileNumber = newCountryCode.getSelectionModel().getSelectedItem() + newMobileNumber.getText();
            String securityQuestion = newSecurityQuestionBox.getValue();
            String securityAnswer = newSecurityAnswer.getText();

            // Changing user session email
            YourPropertyUserSession.getInstance().setUserEmail(email);

            // Create json object and generate jsonInputString
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Email", email);
            jsonObject.put("Password", password);
            jsonObject.put("MobileNumber", mobileNumber);
            jsonObject.put("SecurityQuestion", securityQuestion);
            jsonObject.put("SecurityAnswer", securityAnswer);

            String jsonInputString = jsonObject.toString();

            if(userPassword.equals(passwordForEditLoginDetails.getText())) {
                Pair<Integer, String> pair = HTTPClient.sendPutRequestForEditLoginDetails(targetUrl, jsonInputString, userEmail);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText(pair.getValue());
                alert.showAndWait();

                // Close the edit window
                Stage stage = (Stage) confirmEditLayer.getScene().getWindow();
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect Password");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
