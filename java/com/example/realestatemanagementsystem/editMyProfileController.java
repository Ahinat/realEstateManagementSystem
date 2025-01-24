package com.example.realestatemanagementsystem;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import org.json.JSONObject;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class editMyProfileController implements Initializable {

    public TextField newFirstName;
    public TextField newLastName;
    public DatePicker newBirthday;
    public RadioButton newMaleSelected;
    public RadioButton newFemaleSelected;
    public RadioButton newOtherSelected;
    public TextArea newBio;
    public PasswordField passwordForEditAccountDetails;

    public AnchorPane confirmEditLayer;

    public void onEditClicked(ActionEvent actionEvent) {
        confirmEditLayer.setVisible(true);
        TranslateTransition transition1 = new TranslateTransition();
        transition1.setDuration(Duration.seconds(0.5));
        transition1.setNode(confirmEditLayer);
        transition1.setToX(0);
        transition1.play();
    }

    public void onConfirmEditClicked(ActionEvent actionEvent) {
        // Fetch user email
        String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

        // Get user info from database
        String targetUrl = "http://localhost:8080/api/yourpropertyuser";
        try {
            JSONObject jsonUser = HTTPClient.sendGetRequest(targetUrl, userEmail);
            String userPassword = jsonUser.getString("Password");

            // Getting the input values
            String firstName = newFirstName.getText();
            String lastName = newLastName.getText();
            String newName = firstName + " " + lastName;

            LocalDate birthDayValue = this.newBirthday.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String newBirthday = birthDayValue.format(formatter);

            String newGender = "";
            if(newMaleSelected.isSelected()) newGender = "Male";
            else if(newFemaleSelected.isSelected()) newGender = "Female";
            else if(newOtherSelected.isSelected()) newGender = "Other";

            String bio = newBio.getText();

            // Create json object and generate jsonInputString
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Username", newName);
            jsonObject.put("Birthday", newBirthday);
            jsonObject.put("Gender", newGender);
            jsonObject.put("Bio", bio);

            String jsonInputString = jsonObject.toString();

            if(userPassword.equals(passwordForEditAccountDetails.getText())) {
                Pair<Integer, String> pair = HTTPClient.sendPutRequestForEditAccountDetails(targetUrl, jsonInputString, userEmail);
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

    public void onBackFromConfirmEditClicked(ActionEvent actionEvent) {
        TranslateTransition transition1 = new TranslateTransition();
        transition1.setDuration(Duration.seconds(0.5));
        transition1.setNode(confirmEditLayer);
        transition1.setToX(600);
        transition1.play();
        confirmEditLayer.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String userEmail = YourPropertyUserSession.getInstance().getUserEmail();
        String targetUrl = "http://localhost:8080/api/yourpropertyuser";
        try {
            JSONObject jsonUser = HTTPClient.sendGetRequest(targetUrl, userEmail);

            String name = jsonUser.getString("Username");
            String[] names = name.split(" ");
            newFirstName.setText(names[0]);
            newLastName.setText(names[1]);

            String birthday = jsonUser.getString("Birthday");
            newBirthday.setValue(LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            String gender = jsonUser.getString("Gender");
            if(gender.equals("Male")) newMaleSelected.setSelected(true);
            else if(gender.equals("Female")) newFemaleSelected.setSelected(true);
            else newOtherSelected.setSelected(true);

            String bio = jsonUser.getString("Bio");
            newBio.setText(bio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onNewMaleSelected(ActionEvent actionEvent) {
        newMaleSelected.setSelected(true);
        newFemaleSelected.setSelected(false);
        newOtherSelected.setSelected(false);
    }

    public void onNewFemaleSelected(ActionEvent actionEvent) {
        newMaleSelected.setSelected(false);
        newFemaleSelected.setSelected(true);
        newOtherSelected.setSelected(false);
    }

    public void onNewOtherSelected(ActionEvent actionEvent) {
        newMaleSelected.setSelected(false);
        newFemaleSelected.setSelected(false);
        newOtherSelected.setSelected(true);
    }
}
