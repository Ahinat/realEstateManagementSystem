package com.example.realestatemanagementsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class makePaymentController implements Initializable {
    public Pane propertyImagePane;
    public ImageView currentImage, previousImage, nextImage;
    public Button nextImageButton, previousImageButton;
    public Label propertyTitleShow, showPropertyPrice, showPropertyLocation;
    public Spinner<String> paymentMethod;
    public Label purchaserEmail, sellerEmail;
    public TextField cardNumber;
    public ChoiceBox<String> expiryMonth;
    public ChoiceBox<Integer> expiryYear;
    public TextField securityCode;
    public TextField nameOnCard;

    private List<String> propertyPictures = new ArrayList<>();
    private String propertyTitle;
    private String propertyPrice;
    private String propertyLocation;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private int currentPictureIndex = 0;

    public void setUsers(String sellerEmail, String purchaserEmail) {
        this.sellerEmail.setText(sellerEmail);
        this.purchaserEmail.setText(purchaserEmail);
    }

    public void setPropertyDetails(List<String> propertyPictures, String propertyTitle, String propertyPrice, String propertyLocation) {
        this.propertyPictures = propertyPictures;
        this.propertyTitle = propertyTitle;
        this.propertyPrice = propertyPrice;
        this.propertyLocation = propertyLocation;

        showPropertyData();
    }

    public void showPropertyData() {
        propertyTitleShow.setText(propertyTitle);
        showPropertyPrice.setText(propertyPrice);
        showPropertyLocation.setText(propertyLocation);

        showCurrentImage(propertyPictures.get(0));
    }

    public void showCurrentImage(String pictureName) {
//        currentImage.setImage(new Image(new File(propertyPictures.get(currentPictureIndex)).toURI().toString()));
        String currentImagePath = propertyPictures.get(currentPictureIndex);
        File file = new File(currentImagePath);

        if (file.exists()) {
            currentImage.setImage(new Image(file.toURI().toString()));
        } else {
            System.err.println("Image file not found: " + currentImagePath);
        }

        // Update visibility of buttons
        previousImageButton.setVisible(currentPictureIndex > 0);  // Hide previous button for the first image
        nextImageButton.setVisible(currentPictureIndex < propertyPictures.size() - 1);  // Hide next button for the last image
    }

    public void onShowNextImageClicked(ActionEvent actionEvent) {
        if (!propertyPictures.isEmpty() && currentPictureIndex < propertyPictures.size() - 1) {
            currentPictureIndex++;
            showCurrentImage(propertyPictures.get(currentPictureIndex));
        }
    }

    public void onShowPreviousImageClicked(ActionEvent actionEvent) {
        if (!propertyPictures.isEmpty() && currentPictureIndex > 0) {
            currentPictureIndex--;
            showCurrentImage(propertyPictures.get(currentPictureIndex));
        }
    }

    private void addOptionsToExpirationCheckboxes(){
        expiryMonth.getItems().addAll(
                "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
        );
        Integer currentYear = LocalDate.now().getYear();
        for(int i = 0; i < 5; i++){
            expiryYear.getItems().add(currentYear + i);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Setting up options in payment method
        String[] options = {"Credit card", "Debit card"};
        SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(javafx.collections.FXCollections.observableArrayList(options));
        paymentMethod.setValueFactory(valueFactory);

        addOptionsToExpirationCheckboxes();

        // Set an initial value
        paymentMethod.getValueFactory().setValue("Credit card");
    }


    public void onConfirmPaymentClicked(ActionEvent actionEvent) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            Map<String, String> paymentMap = new HashMap<>();
            paymentMap.put("purchaserEmail", purchaserEmail.getText());
            paymentMap.put("sellerEmail", sellerEmail.getText());
            paymentMap.put("propertyTitle", propertyTitle);
            paymentMap.put("propertyLocation", propertyLocation);
            paymentMap.put("cardType", paymentMethod.getValue());
            paymentMap.put("cardNumber", cardNumber.getText());
            paymentMap.put("expiryMonth", expiryMonth.getValue());
            paymentMap.put("expiryYear", expiryYear.getValue().toString());
            paymentMap.put("securityCode", securityCode.getText());
            paymentMap.put("nameOnCard", nameOnCard.getText());

            String jsonInputString = objectMapper.writeValueAsString(paymentMap);
            String targetUrl = "http://localhost:8080/api/payment/add";

            Pair<Integer, String> pair = HTTPClient.sendPostRequest(targetUrl, jsonInputString);
            if(pair.getKey() == 200 || pair.getKey() == 201){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText(pair.getValue());
                alert.showAndWait();

                FXMLLoader loader = new FXMLLoader(RealEstateApplication.class.getResource("profileDashBoardPage.fxml"));
                Parent dashboardPage = loader.load();

                dashboardController dashboardController = loader.getController();
                dashboardController.onMyPropertiesOptionClicked();

                Stage stage = (Stage) propertyImagePane.getScene().getWindow();
                stage.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
