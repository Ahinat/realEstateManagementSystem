package com.example.realestatemanagementsystem;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.util.Pair;
import org.json.JSONObject;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class logInSignUpController implements Initializable {

    // Create Account pages
    public AnchorPane createAccount;
    public AnchorPane welcomeLayer;
    public AnchorPane verifyAccountCA;
    public AnchorPane psDetails1;
    public AnchorPane psDetails2;

    // Sign in pages
    public AnchorPane signIn;
    public AnchorPane helloLayer;
    public AnchorPane forgotPassword;
    public AnchorPane verifyAccountInSI;
    public AnchorPane createPasswordInSI;
    public AnchorPane createSecurityQuestionInSI;

    // Create Account Parameters
    public TextField createGmail;
    public PasswordField createNewPassword;
    public PasswordField createReEnteredPassword;
    public TextField firstName;
    public TextField lastName;
    public DatePicker birthDay;
    public RadioButton genderMale;
    public RadioButton genderFemale;
    public RadioButton genderOther;
    public ComboBox<String> countryCodeMobileNumber;
    public TextField mobileNumber;
    public ComboBox<String> securityQuestionBoxInCA;
    public TextField securityAnswerInCA;
    public TextArea userBio;

    // Login Parameters
    public TextField loginGmail;
    public PasswordField loginPassword;
    public ComboBox<String> securityQuestionBoxInSI;
    public TextField securityAnswerInSI;
    public PasswordField newPasswordInSI;
    public PasswordField reEnteredNewPasswordInSI;
    public ComboBox<String> createNewSecurityQuestionBoxInSI;
    public TextField createNewSecurityAnswerInSI;

    // Verify email while creating account parameters
    public Label verifyAccountStatusLabel;
    public TextField userOTPInCA;
    public Hyperlink resendCodeInCAOption;
    public Label timerLabel;
    public Hyperlink resendCodeInSIOption;
    public TextField userOTPInSI;


    // Stage and scene to show
    private Stage stage;
    private Scene scene;

    private String generatedOTP;
    private Long currentOTPExpiryTime;
    private Timeline countdownTimer;



    public void onSignInClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signInPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Sign In");
        stage.setScene(scene);
        stage.show();
    }

    public void onSignUpClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("createAccountPage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Sign Up");
        stage.setScene(scene);
        stage.show();
    }

    public void onBackToHomePageClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepage.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Your Property");
        stage.setScene(scene);
        stage.show();
    }

    public void addItemsInSecurityQuestionBoxInCA() {
        securityQuestionBoxInCA.getItems().addAll(
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

    public void addItemsInSecurityQuestionBoxInSI() {
        securityQuestionBoxInSI.getItems().addAll(
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

    public void addItemsInCreateNewSecurityQuestionBoxInSI() {
        createNewSecurityQuestionBoxInSI.getItems().addAll(
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
        countryCodeMobileNumber.getItems().addAll(
                "+1",
                "+44",
                "+880",
                "+91",
                "+978"
        );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TranslateTransition transition1 = new TranslateTransition();
        transition1.setDuration(Duration.seconds(0.7));
        transition1.setNode(helloLayer);
        transition1.setToX(434.0);
        transition1.play();

        TranslateTransition transition2 = new TranslateTransition();
        transition2.setDuration(Duration.seconds(0.7));
        transition2.setNode(welcomeLayer);
        transition2.setToX(-434.0);
        transition2.play();

        if (securityQuestionBoxInCA != null) {
            addItemsInSecurityQuestionBoxInCA();
        }
        if (securityQuestionBoxInSI != null) {
            addItemsInSecurityQuestionBoxInSI();
        }
        if(createNewSecurityQuestionBoxInSI != null) {
            addItemsInCreateNewSecurityQuestionBoxInSI();
        }
        if(countryCodeMobileNumber != null){
            addItemsInCountryCodeMobileNumber();
        }
    }

    public void onsignUpInCAClicked(ActionEvent actionEvent) {
        // Ensuring that all credentials are filled and meet certain conditions
        if(createGmail.getText().isEmpty()){
            showError("You must enter your email!");
        } else if(createNewPassword.getText().isEmpty()){
            showError("You must enter your password!");
        } else if(createReEnteredPassword.getText().isEmpty()){
            showError("You must re-enter your password!");
        } else if(createNewPassword.getText().equals(createReEnteredPassword.getText())){
            if(createNewPassword.getText().length() < 8){
                showWarning("Password is too short!");
            } else {
                sendOTP(createGmail.getText(), "signup");
                createAccount.setVisible(false);
                verifyAccountCA.setVisible(true);
            }
        } else {
            showError("Your passwords do not match!");
        }
    }

    public void sendOTP(String userEmail, String source){
        // Generate OTP
        generatedOTP = String.format("%06d", new Random().nextInt(999999));

        // Set OTP expiry time (2 minutes from now)
        currentOTPExpiryTime = System.currentTimeMillis() + (2*60*1000);

        // Email configuration
        String host = "smtp.gmail.com";
        String senderEmail = "tanjiulhasib@gmail.com";
        String senderPassword = "uidryycczraflqfq"; // Consider using app-specific password for Gmail

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Your OTP Code");
            message.setText("Your OTP is: " + generatedOTP);

            Transport.send(message);
            verifyAccountStatusLabel.setText("A verification OTP is sent to " + userEmail + ", enter the code to verify yourself");
            if(source.equals("signup")){
                startTimerInCA();
            } else if(source.equals("signin")){
                startTimerInSI();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            verifyAccountStatusLabel.setText("Failed to send OTP. Check email configuration.");
        }
    }

    private void startTimerInCA() {
        resendCodeInCAOption.setDisable(true); // Disable "Resend Code" button
        final int[] timeLimit = {120}; // 2 minutes in seconds

        countdownTimer = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    int minutes = timeLimit[0] / 60;
                    int seconds = timeLimit[0] % 60;
                    timerLabel.setText("OTP expires in " + String.format("%02d:%02d", minutes, seconds));
                    if (timeLimit[0] > 0) {
                        timeLimit[0]--;
                    } else {
                        countdownTimer.stop();
                        timerLabel.setText("");
                        resendCodeInCAOption.setDisable(false); // Enable "Resend Code" button
                    }
                })
        );

        countdownTimer.setCycleCount(Timeline.INDEFINITE);
        countdownTimer.play();
    }

    private void startTimerInSI() {
        resendCodeInSIOption.setDisable(true); // Disable "Resend Code" button
        final int[] timeLimit = {120}; // 2 minutes in seconds

        countdownTimer = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    int minutes = timeLimit[0] / 60;
                    int seconds = timeLimit[0] % 60;
                    timerLabel.setText("OTP expires in " + String.format("%02d:%02d", minutes, seconds));
                    if (timeLimit[0] > 0) {
                        timeLimit[0]--;
                    } else {
                        countdownTimer.stop();
                        timerLabel.setText("");
                        resendCodeInSIOption.setDisable(false); // Enable "Resend Code" button
                    }
                })
        );

        countdownTimer.setCycleCount(Timeline.INDEFINITE);
        countdownTimer.play();
    }

    public void onResendCodeInCAClicked(ActionEvent actionEvent) {
        if(countdownTimer != null){
            countdownTimer.stop();
        }
        sendOTP(createGmail.getText(), "signup");
    }

    public void onResendCodeInSIClicked(ActionEvent actionEvent) {
        if(countdownTimer != null){
            countdownTimer.stop();
        }
        sendOTP(createGmail.getText(), "signin");
    }

    public void showError(String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Something went wrong!");
        alert.setContentText(text);

        alert.showAndWait();
    }

    public void showWarning(String text){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Something went wrong!");
        alert.setContentText(text);

        alert.showAndWait();
    }

    public void onVerifyCAClicked(ActionEvent actionEvent) {
        String enteredOTP = userOTPInCA.getText();
        if(enteredOTP.isEmpty()){
            showError("You must enter your OTP!");
        } else if(enteredOTP.equals(generatedOTP)){
            if(System.currentTimeMillis() > currentOTPExpiryTime){
                verifyAccountStatusLabel.setText("This OTP has been expired. Please request to resend a new one.");
                return;
            } else {
                verifyAccountCA.setVisible(false);
                welcomeLayer.setVisible(false);
                psDetails1.setVisible(true);
            }
        } else {
            verifyAccountStatusLabel.setText("Your OTP is incorrect!");
            verifyAccountStatusLabel.setStyle("-fx-text-fill: red");
        }
    }

    public void onForgetPasswordClicked(ActionEvent actionEvent) {
        if(loginGmail.getText().isEmpty()){
            showError("You must enter your email!");
            return;
        }

        String targetUrl = "http://localhost:8080/api/yourpropertyuser";
        try {
            JSONObject jsonUser = HTTPClient.sendGetRequest(targetUrl, loginGmail.getText());

            signIn.setVisible(false);
            forgotPassword.setVisible(true);
        } catch (RuntimeException | IOException e) {
            if (e.getMessage().contains("HTTP error code : 404")) {
                // If a 404 error occurs, show error instead
                showError("There's no account associated with this email");
            } else {
                // Handle other exceptions
                e.printStackTrace();
            }
        }
    }

    public void onSignInFromForgetPasswordClicked(ActionEvent actionEvent) {
        // Get user info from database
        String targetUrl = "http://localhost:8080/api/yourpropertyuser";
        try {
            JSONObject jsonUser = HTTPClient.sendGetRequest(targetUrl, loginGmail.getText());

            String securityQuestion = jsonUser.optString("SecurityQuestion", "");
            String securityAnswer = jsonUser.optString("SecurityAnswer", "");

            if(securityQuestionBoxInSI.getSelectionModel().getSelectedItem().equals(securityQuestion) &&  securityAnswer.equals(securityAnswerInSI.getText())){
                forgotPassword.setVisible(false);
                helloLayer.setVisible(false);
                createPasswordInSI.setVisible(true);

            } else if(!securityQuestionBoxInSI.getSelectionModel().getSelectedItem().equals(securityQuestion)){
                showError("Security Question does not match!");
            } else if(!securityAnswer.equals(securityAnswerInSI.getText())){
                showError("Security Answer does not match!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onFirstNextClicked(ActionEvent actionEvent) {
        if(firstName.getText().isEmpty()){
            showError("Please enter your first name!");
        } else if(lastName.getText().isEmpty()){
            showError("Please enter your last name!");
        } else if(birthDay.getValue() == null){
            showError("Please enter a birthday!");
        } else if(!genderMale.isSelected() && !genderFemale.isSelected() && !genderOther.isSelected()){
            showError("Please select gender!");
        } else if(countryCodeMobileNumber.getValue().isEmpty()){
            showError("Please enter your country code!");
        } else if(mobileNumber.getText().isEmpty()){
            showError("Please enter your mobile number!");
        } else{
            psDetails1.setVisible(false);
            psDetails2.setVisible(true);
        }
    }

    public void onSecondNextClicked(ActionEvent actionEvent) throws IOException {
        if(securityQuestionBoxInCA.getValue().isEmpty()){
            showError("Please select/enter your security question!");
        } else if(securityAnswerInCA.getText().isEmpty()){
            showError("Please enter your security answer!");
        } else {
            registerUser(actionEvent);
        }
    }

    public void registerUser(ActionEvent actionEvent) {
        String firstNameText = firstName.getText();
        String lastNameText = lastName.getText();
        String password = createNewPassword.getText();

        LocalDate birthDayValue = birthDay.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String Birthday = birthDayValue.format(formatter);

        String gender = "";
        if(genderMale.isSelected()) gender = "Male";
        else if(genderFemale.isSelected()) gender = "Female";
        else if(genderOther.isSelected()) gender = "Other";

        String email = createGmail.getText();
        String MobileNumber = countryCodeMobileNumber.getSelectionModel().getSelectedItem() + mobileNumber.getText();
        String SecurityQuestion = securityQuestionBoxInCA.getSelectionModel().getSelectedItem();
        String SecurityAnswer = securityAnswerInCA.getText();
        String Bio = userBio.getText();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("FirstName", firstNameText);
        jsonObject.put("LastName", lastNameText);
        jsonObject.put("Password", password);
        jsonObject.put("Birthday", Birthday);
        jsonObject.put("Gender", gender);
        jsonObject.put("Email", email);
        jsonObject.put("MobileNumber", MobileNumber);
        jsonObject.put("SecurityQuestion", SecurityQuestion);
        jsonObject.put("SecurityAnswer", SecurityAnswer);
        jsonObject.put("Bio", Bio);

        String jsonInputString = jsonObject.toString();

        String targetUrl = "http://localhost:8080/api/yourpropertyuser/register";

        try {
            Pair<Integer, String> pair = HTTPClient.sendPostRequest(targetUrl, jsonInputString);
            if(pair.getKey() == 201){
                // Changing user session info
                YourPropertyUserSession.getInstance().loginUser(email, password);

                // Shifting scene to user dashboard
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profileDashboardPage.fxml")));
                stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setTitle("Profile Dashboard");
                stage.setScene(scene);
                stage.show();
            } else if(pair.getKey() == 409){
                showError("You already have an active profile!");
            } else if(pair.getKey() == 500){
                showError("An error occurred!");
            }
        } catch (Exception e) {
            System.out.println("An error occurred!");
            e.printStackTrace();
        }
    }

    public void onVerifySIClicked(ActionEvent actionEvent) {
        String enteredOTP = userOTPInSI.getText();
        if(enteredOTP.isEmpty()){
            showError("You must enter your OTP!");
        } else if(enteredOTP.equals(generatedOTP)){
            if(System.currentTimeMillis() > currentOTPExpiryTime){
                verifyAccountStatusLabel.setText("This OTP has been expired. Please request to resend a new one.");
                return;
            } else {
                verifyAccountInSI.setVisible(false);
                helloLayer.setVisible(false);
                createPasswordInSI.setVisible(true);
            }
        } else {
            verifyAccountStatusLabel.setText("Your OTP is incorrect!");
            verifyAccountStatusLabel.setStyle("-fx-text-fill: red");
        }
    }

    public void onForgetSecurityClicked(ActionEvent actionEvent) {
        sendOTP(loginGmail.getText(), "signin");
        forgotPassword.setVisible(false);
        verifyAccountInSI.setVisible(true);
    }

    public void onCreatePasswordContinueInSIClicked(ActionEvent actionEvent) {
        if(newPasswordInSI.getText().isEmpty()){
            showError("You must enter your new password!");
        } else if(reEnteredNewPasswordInSI.getText().isEmpty()){
            showError("You must re-enter your new password!");
        } else if(newPasswordInSI.getText().equals(reEnteredNewPasswordInSI.getText())){
            if(newPasswordInSI.getText().length() < 8){
                showWarning("New password is too short!");
            } else {
                createPasswordInSI.setVisible(false);
                createSecurityQuestionInSI.setVisible(true);
            }
        } else {
            showError("Your passwords do not match!");
        }
    }

    public void onCreateSecurityContinueInSIClicked(ActionEvent actionEvent) {
        if(createNewSecurityQuestionBoxInSI.getValue().isEmpty()){
            showError("Please select/enter your new security question!");
        } else if(createNewSecurityAnswerInSI.getText().isEmpty()){
            showError("Please enter your new security answer!");
        } else {
            String newPassword = newPasswordInSI.getText();
            String newSecurityQuestion = createNewSecurityQuestionBoxInSI.getSelectionModel().getSelectedItem();
            String newSecurityAnswer = createNewSecurityAnswerInSI.getText();

            // Create json object and generate jsonInputString
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Password", newPassword);
            jsonObject.put("SecurityQuestion", newSecurityQuestion);
            jsonObject.put("SecurityAnswer", newSecurityAnswer);

            String jsonInputString = jsonObject.toString();

            String targetUrl = "http://localhost:8080/api/yourpropertyuser";

            try{
                Pair<Integer, String> pair = HTTPClient.sendPutRequestForEditProfileDetails(targetUrl, jsonInputString, loginGmail.getText());

                if(pair.getKey() == 200){
                    // Changing user session info
                    YourPropertyUserSession.getInstance().loginUser(loginGmail.getText(), newPassword);

                    // Shifting the scene to user dashboard
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profileDashboardPage.fxml")));
                    stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setTitle("Profile Dashboard");
                    stage.setScene(scene);
                    stage.show();
                } else {
                    showError("An error occurred!");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // This function will be used for a user to add his own custom security question
    // This will be individually work for each user and store their data
    // This must be modified after adding the database
    public void addCustomSecurityQuestion(ActionEvent actionEvent) {
//        if(securityQuestionBoxInCA.getItems().contains(securityQuestionBoxInCA.getValue())){
//            return;
//        }
//        else{
//            securityQuestionBoxInCA.getItems().add(securityQuestionBoxInCA.getValue());
//        }
    }

    public void addCustomCountryCode(ActionEvent actionEvent) {

    }

    public void onMaleSelected(ActionEvent actionEvent) {
        genderMale.setSelected(true);
        genderFemale.setSelected(false);
        genderOther.setSelected(false);
    }

    public void onFemaleSelected(ActionEvent actionEvent) {
        genderMale.setSelected(false);
        genderFemale.setSelected(true);
        genderOther.setSelected(false);
    }

    public void onOtherSelected(ActionEvent actionEvent) {
        genderMale.setSelected(false);
        genderFemale.setSelected(false);
        genderOther.setSelected(true);
    }

    public void onLoginSIClicked(ActionEvent actionEvent) {
        if(loginGmail.getText().isEmpty()) {
            showError("Please enter your email!");
        } else if (loginPassword.getText().isEmpty()) {
            showError("Please enter your password!");
        } else {
            loginUser(actionEvent);
        }
    }

    public void loginUser(ActionEvent actionEvent){
        String email = loginGmail.getText();
        String password = loginPassword.getText();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Email", email);
        jsonObject.put("Password", password);

        String jsonInputString = jsonObject.toString();
        String targetUrl = "http://localhost:8080/api/yourpropertyuser/login";

        try {
            Pair<Integer, String> pair = HTTPClient.sendPostRequest(targetUrl, jsonInputString);

            if(pair.getKey() == 200){
                // Changing user session info
                YourPropertyUserSession.getInstance().loginUser(email, password);

                // Shifting the scene to user dashboard
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profileDashboardPage.fxml")));
                stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setTitle("Profile Dashboard");
                stage.setScene(scene);
                stage.show();
            } else if(pair.getKey() == 401){
                showError("Invalid email or password!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // We will need to add another function to get security question for the user in the signIn segment
    // It should be modified further
}