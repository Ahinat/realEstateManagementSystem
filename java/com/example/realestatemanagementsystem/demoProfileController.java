package com.example.realestatemanagementsystem;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class demoProfileController implements Initializable {

    // Dashboard layers
    public AnchorPane menuBar;
    public AnchorPane profileLayer;
    public AnchorPane myProfileLayer;
    public AnchorPane profileSettingsLayer;

    // My profile variables
    public Label nameInMyProfile;
    public Label birthdayInMyProfile;
    public Label genderInMyProfile;
    public Label bioInMyProfile;

    // Profile settings variables
    public Label emailInProfileSettings;
    public Label mobileNumberInProfileSettings;
    public Label securityQuestionInProfileSettings;
    public Label securityAnswerInProfileSettings;
    public Label passwordInProfileSettings;


    public void onMenuButtonClicked(ActionEvent event) {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(menuBar);
        transition.setToX(0);
        transition.play();
    }

    public void onLogoClicked(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepage.fxml")));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Your Property");
        stage.setScene(scene);
        stage.show();
    }

    public void onCancelClicked(MouseEvent mouseEvent) {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(menuBar);
        transition.setToX(-185);
        transition.play();
    }

    public void onLogOutClicked(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        ButtonType userConfirmation = alert.showAndWait().get();

        if(ButtonType.OK.equals(userConfirmation)) {
            YourPropertyUserSession.getInstance().logoutUser();
        } else {
            alert.close();
        }
    }

    public void onBackFromMyProfileClicked(ActionEvent actionEvent) {
        myProfileLayer.setVisible(false);
        profileLayer.setVisible(true);
    }

    public void onMyProfileClicked(MouseEvent mouseEvent) throws IOException {
        if(YourPropertyUserSession.getInstance().isLoggedIn()){
            profileLayer.setVisible(false);
            myProfileLayer.setVisible(true);

            refreshUserAccountDetails();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error accessing my profile");
            alert.setHeaderText(null);
            alert.setContentText("You are not logged in");
            alert.showAndWait();
        }
    }

    public void onEditMyProfileClicked(ActionEvent actionEvent) throws IOException {
        // Create a new stage for the edit window
        Stage stage = new Stage();
        stage.setTitle("Edit your login details");
        stage.initModality(Modality.APPLICATION_MODAL);

        // Create an FXMLLoader instance and load the FXML
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("editMyProfilePage.fxml")));

        // Set up the scene and show the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();  // Waits until the edit window is closed

        // Once the edit window is closed, refresh data in the parent window
        refreshUserAccountDetails();
    }

    public void refreshUserAccountDetails() {
        // Fetch user email
        String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

        // Get user info from database
        String targetUrl = "http://localhost:8080/api/yourpropertyuser";
        try {
            JSONObject jsonUser = HTTPClient.sendGetRequest(targetUrl, userEmail);
            String userName = jsonUser.getString("Username");
            String birthday = jsonUser.getString("Birthday");
            String gender = jsonUser.getString("Gender");
            String bio = jsonUser.getString("Bio");

            nameInMyProfile.setText(userName);
            birthdayInMyProfile.setText(birthday);
            genderInMyProfile.setText(gender);
            bioInMyProfile.setText(bio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profileLayer.setVisible(true);
        myProfileLayer.setVisible(false);
        profileSettingsLayer.setVisible(false);
    }

    public void onProfileSettingsClicked() {
        if(YourPropertyUserSession.getInstance().isLoggedIn()){
            profileLayer.setVisible(false);
            profileSettingsLayer.setVisible(true);

            refreshUserLoginData();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error accessing profile settings");
            alert.setHeaderText(null);
            alert.setContentText("You are not logged in");
            alert.showAndWait();
        }
    }

    public void onBackFromProfileSettingsClicked(ActionEvent actionEvent) {
        profileSettingsLayer.setVisible(false);
        profileLayer.setVisible(true);
    }

    public void onEditProfileSettingsClicked(ActionEvent actionEvent) throws IOException {
        // Create a new stage for the edit window
        Stage stage = new Stage();
        stage.setTitle("Edit your login details");
        stage.initModality(Modality.APPLICATION_MODAL);

        // Create an FXMLLoader instance and load the FXML
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("editProfileSettingsPage.fxml")));

        // Set up the scene and show the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();  // Waits until the edit window is closed

        // Once the edit window is closed, refresh data in the parent window
        refreshUserLoginData();
    }

    public void refreshUserLoginData() {
        // Fetch user email
        String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

        // Get user info from database
        String targetUrl = "http://localhost:8080/api/yourpropertyuser";
        try {
            JSONObject jsonUser = HTTPClient.sendGetRequest(targetUrl, userEmail);
            String email = jsonUser.getString("Email");
            String password = jsonUser.getString("Password");
            String mobileNumber = jsonUser.getString("MobileNumber");
            String securityQuestion = jsonUser.getString("SecurityQuestion");
            String securityAnswer = jsonUser.getString("SecurityAnswer");

            emailInProfileSettings.setText(email);
            passwordInProfileSettings.setText(password.replaceAll(".", "*"));
            mobileNumberInProfileSettings.setText(mobileNumber);
            securityQuestionInProfileSettings.setText(securityQuestion);
            securityAnswerInProfileSettings.setText(securityAnswer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onPasswordInProfileSettingsClicked(MouseEvent mouseEvent) {
        passwordInProfileSettings.setText(YourPropertyUserSession.getInstance().getUserPassword());
    }


    public void onDeleteAccountClicked(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete your account");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete your account?");

        ButtonType userConfirmation = alert.showAndWait().get();

        if(ButtonType.OK.equals(userConfirmation)) {
            // Fetch user email
            String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

            // Get user info from database
            String targetUrl = "http://localhost:8080/api/yourpropertyuser";
            try {
                JSONObject jsonUser = HTTPClient.sendGetRequest(targetUrl, userEmail);
                Long userId = jsonUser.getLong("UserId");

                HTTPClient.deleteUserAccount(userId);
            } catch(Exception e){
                e.printStackTrace();
            }
        } else {
            alert.close();
        }
    }
}
