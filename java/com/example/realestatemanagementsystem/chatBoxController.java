package com.example.realestatemanagementsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class chatBoxController implements Initializable {
    // ChatBox fields
    public TextArea newTextMessage;
    public ImageView profilePicture;
    public Label userName;
    public VBox chatListContainer;
    public HBox dateContainer;
    public HBox receivedMessageContainer;
    public HBox sentMessageContainer;
    public Label dateLabel;
    public Label receivedMessage;
    public Label receivedMessageTime;
    public Label sentMessage;
    public Label sentMessageTime;
    public ImageView picture;
    public Label title;
    public Label price;
    public Button suggestPriceButton;
    public Button makePaymentButton;
    public ToggleButton confirmPurchaseToggleButton;

    // Users and property details
    private String senderEmail;
    private String receiverEmail;
    private String sellerEmail;
    private String propertyTitle;
    private String propertyLocation;
    private String propertyPrice;
    private String propertyPicture;

    private final ObjectMapper mapper = new ObjectMapper();
    private LocalDate currentDate = null;

    private BigDecimal salePrice = null;
    private BigDecimal rentPrice = null;
    private String rentTerm = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chatListContainer.getChildren().clear();
    }

    public void setUserDetails(String picture, String name) {
        userName.setText(name);
        profilePicture.setImage(new Image(new File(picture).toURI().toString()));
    }

    public void setUserEmail(String senderEmail, String receiverEmail) {
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;

        if(senderEmail.equals(this.sellerEmail)) {
            suggestPriceButton.setVisible(false);
            makePaymentButton.setVisible(false);
            confirmPurchaseToggleButton.setVisible(true);

            try{
                String targetUrl = "http://localhost:8080/api/purchase-confirmation/get";
                Map<String, Object> confirmPurchase = new HashMap<>();
                confirmPurchase.put("purchaserEmail", receiverEmail);
                confirmPurchase.put("sellerEmail", this.sellerEmail);
                confirmPurchase.put("propertyTitle", propertyTitle);
                confirmPurchase.put("propertyLocation", propertyLocation);

                String jsonInputString = mapper.writeValueAsString(confirmPurchase);

                Pair<Integer, String> pair = HTTPClient.sendPostRequest(targetUrl, jsonInputString);
                if(pair.getKey() == 200) {
                    if(pair.getValue().equals("true")) {
                        confirmPurchaseToggleButton.setText("Confirmed");
                        confirmPurchaseToggleButton.setStyle("-fx-background-color: #762179; -fx-text-fill:  #c874cd; -fx-background-radius: 8px;");
                        Image confirmedPurchaseImage = new Image(String.valueOf(getClass().getResource("/images/confirmed purchase icon.png")));
                        ImageView confirmedPurchaseImageView = new ImageView(confirmedPurchaseImage);
                        confirmPurchaseToggleButton.setGraphic(confirmedPurchaseImageView);
                        confirmPurchaseToggleButton.setSelected(true);
                    } else {
                        if(salePrice != null) {
                            confirmPurchaseToggleButton.setText("Confirm Buyer");
                        } else if(rentPrice != null) {
                            confirmPurchaseToggleButton.setText("Confirm Renter");
                        }
                        confirmPurchaseToggleButton.setStyle("-fx-background-color: #c874cd; -fx-text-fill:  #762179; -fx-background-radius: 8px;");
                        Image confirmedPurchaseImage = new Image(String.valueOf(getClass().getResource("/images/confirm purchase icon.png")));
                        ImageView confirmedPurchaseImageView = new ImageView(confirmedPurchaseImage);
                        confirmPurchaseToggleButton.setGraphic(confirmedPurchaseImageView);
                        confirmPurchaseToggleButton.setSelected(false);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            suggestPriceButton.setVisible(true);
            makePaymentButton.setVisible(true);
            confirmPurchaseToggleButton.setVisible(false);
        }
    }

    public void setPropertyData(String propertyTitle, String propertyLocation, String propertyPrice, String propertyPicture) {
        this.propertyTitle = propertyTitle;
        this.propertyLocation = propertyLocation;
        this.propertyPrice = propertyPrice;
        this.propertyPicture = propertyPicture;
        title.setText(this.propertyTitle);
        price.setText(this.propertyPrice);
        picture.setImage(new Image(this.propertyPicture));

        setPropertyPrice(this.propertyPrice);
    }

    public void setPropertyPrice(String propertyPrice) {
        if(propertyPrice.charAt(propertyPrice.length() - 1) == ')') {
            String[] parts = propertyPrice.split(" ");
            this.rentPrice = new BigDecimal(parts[0]);
            this.rentTerm = parts[1].replace("(", "").replace(")", "");

            System.out.println(rentPrice + " (" + rentTerm + ")");
        } else {
            this.salePrice = new BigDecimal(propertyPrice);

            System.out.println(salePrice);
        }
    }

    public void onSendMessageClicked() throws IOException {
        try{
            Pair<Integer, String> chatResponse = HTTPClient.sendChatMessage("http://localhost:8080/api/chat/save", senderEmail, receiverEmail, propertyTitle, propertyLocation, newTextMessage.getText());
            if(chatResponse.getKey() == 200) {
                if(currentDate == null || currentDate.isBefore(LocalDate.now())) {
                    addDateLabelToChat(LocalDate.now());
                    currentDate = LocalDate.now();
                }

                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setSender(senderEmail);
                chatMessage.setReceiver(receiverEmail);
                chatMessage.setMessage(newTextMessage.getText());
                chatMessage.setTimeStamp(LocalDateTime.now());
                addSentMessageToChat(chatMessage);

                newTextMessage.clear();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public void loadChatHistory() {
//        String targetUrl = "http://localhost:8080/api/chat/history";
//        try {
//            List<ChatMessage> chatMessagesList = HTTPClient.getChatMessages(targetUrl, senderEmail, receiverEmail, propertyTitle, propertyLocation);
//            if (chatMessagesList != null && !chatMessagesList.isEmpty()) {
//                currentDate = chatMessagesList.get(0).getTimeStamp().toLocalDate();
//
//                // Add the initial date label
//                addDateLabelToChat(currentDate);
//
//                for (ChatMessage chatMessage : chatMessagesList) {
//                    // Check if the date has changed
//                    if (!chatMessage.getTimeStamp().toLocalDate().equals(currentDate)) {
//                        currentDate = chatMessage.getTimeStamp().toLocalDate();
//                        addDateLabelToChat(currentDate);
//                    }
//
//                    // Add the chat message
//                    if (chatMessage.getSender().equals(YourPropertyUserSession.getInstance().getUserEmail())) {
//                        addSentMessageToChat(chatMessage);
//                    } else if (chatMessage.getReceiver().equals(YourPropertyUserSession.getInstance().getUserEmail())) {
//                        addReceivedMessageToChat(chatMessage);
//                    }
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void loadChatHistory() {
        String targetUrl = "http://localhost:8080/api/chat/history";
        try {
            List<Object> chatAndNegotiationHistory = HTTPClient.getChatAndNegotiationHistory(targetUrl, senderEmail, receiverEmail, propertyTitle, propertyLocation);

            if (chatAndNegotiationHistory != null && !chatAndNegotiationHistory.isEmpty()) {
                for (Object object : chatAndNegotiationHistory) {
                    LocalDateTime timeStamp;
                    if (object instanceof ChatMessage) {
                        ChatMessage chatMessage = (ChatMessage) object;
                        timeStamp = chatMessage.getTimeStamp();

                        // Check if the date has changed
                        if (currentDate == null || !timeStamp.toLocalDate().equals(currentDate)) {
                            currentDate = timeStamp.toLocalDate();
                            addDateLabelToChat(currentDate);
                        }

                        // Add the chat message
                        if (chatMessage.getSender().equals(YourPropertyUserSession.getInstance().getUserEmail())) {
                            addSentMessageToChat(chatMessage);
                        } else {
                            addReceivedMessageToChat(chatMessage);
                        }
                    } else if (object instanceof Negotiation) {
                        Negotiation negotiation = (Negotiation) object;
                        timeStamp = negotiation.getOfferedAt();

                        // Check if the date has changed
                        if (currentDate == null || !timeStamp.toLocalDate().equals(currentDate)) {
                            currentDate = timeStamp.toLocalDate();
                            addDateLabelToChat(currentDate);
                        }

                        // Add the negotiation message
                        addNegotiationToChat(negotiation);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Helper method to add a date label
    private void addDateLabelToChat(LocalDate date) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/chatMessagesAndDateTemplate.fxml"));
        Pane chatTemplatePane = loader.load();

        HBox dateContainer = (HBox) chatTemplatePane.lookup("#dateContainer");

        if (dateContainer != null) {
            Label dateLabel = (Label) dateContainer.lookup("#dateLabel");
            dateLabel.setText(date.format(DateTimeFormatter.ofPattern("dd MMM, yyyy")));
            chatListContainer.getChildren().add(dateContainer);
        }
    }

    // Helper method to add a sent message
    private void addSentMessageToChat(ChatMessage chatMessage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/chatMessagesAndDateTemplate.fxml"));
        Pane chatTemplatePane = loader.load();

        HBox sentMessageContainer = (HBox) chatTemplatePane.lookup("#sentMessageContainer");

        if (sentMessageContainer != null) {
            Text sentMessageText = (Text) sentMessageContainer.lookup("#sentMessage");
            Label sentMessageTimeLabel = (Label) sentMessageContainer.lookup("#sentMessageTime");

            sentMessageText.setText(chatMessage.getMessage());
            sentMessageTimeLabel.setText(chatMessage.getTimeStamp().toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a")));
            chatListContainer.getChildren().add(sentMessageContainer);
        }
    }

    // Helper method to add a received message
    private void addReceivedMessageToChat(ChatMessage chatMessage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/chatMessagesAndDateTemplate.fxml"));
        Pane chatTemplatePane = loader.load();

        HBox receivedMessageContainer = (HBox) chatTemplatePane.lookup("#receivedMessageContainer");

        if (receivedMessageContainer != null) {
            Text receivedMessageText = (Text) receivedMessageContainer.lookup("#receivedMessage");
            Label receivedMessageTimeLabel = (Label) receivedMessageContainer.lookup("#receivedMessageTime");

            receivedMessageText.setText(chatMessage.getMessage());
            receivedMessageTimeLabel.setText(chatMessage.getTimeStamp().toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a")));
            chatListContainer.getChildren().add(receivedMessageContainer);
        }
    }

    // Helper method to add a suggest price message
    private void addNegotiationToChat(Negotiation negotiation) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/chatMessagesAndDateTemplate.fxml"));
        Pane chatTemplatePane = loader.load();

        HBox priceSuggestionContainer = (HBox) chatTemplatePane.lookup("#priceSuggestionContainer");

        if (priceSuggestionContainer != null) {
            Label suggestedPriceLabel = (Label) priceSuggestionContainer.lookup("#suggestedPrice");
            Label priceSuggestionTimeLabel = (Label) priceSuggestionContainer.lookup("#priceSuggestionTime");

            if(negotiation.getPropertyOfferType().equals("For sale")) {
                suggestedPriceLabel.setText(negotiation.getNegotiatedSalePrice().toString());
            } else if (negotiation.getPropertyOfferType().equals("For rent")) {
                suggestedPriceLabel.setText(negotiation.getNegotiatedRentPrice().toString() + " (" + negotiation.getNegotiatedRentTerm() + ")");
            }
            priceSuggestionTimeLabel.setText(negotiation.getOfferedAt().toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a")));

            if(!negotiation.getNegotiator().equals(YourPropertyUserSession.getInstance().getUserEmail())) {
                priceSuggestionContainer.setStyle("-fx-alignment: CENTER_LEFT");
            }

            VBox offerVBox = (VBox) priceSuggestionContainer.lookup("#offerVBox");
            HBox offerButtonHBox = (HBox) offerVBox.lookup("#offerButtonsHBox");
            HBox acceptedButtonHBox = (HBox) offerVBox.lookup("#acceptedButtonsHBox");
            HBox cancelledButtonHBox = (HBox) offerVBox.lookup("#cancelledButtonsHBox");

            offerVBox.getChildren().remove(offerButtonHBox);
            offerVBox.getChildren().remove(acceptedButtonHBox);
            offerVBox.getChildren().remove(cancelledButtonHBox);

            if(!negotiation.getNegotiator().equals(YourPropertyUserSession.getInstance().getUserEmail())) {
                if(negotiation.getStatus().equals(Negotiation.Status.OFFERED)) {
                    offerVBox.getChildren().add(offerButtonHBox);

                    Button acceptButton = (Button) offerButtonHBox.lookup("#acceptNegotiationButton");
                    if(acceptButton != null) {
                        acceptButton.setOnAction(e -> {
                            try {
                                updateNegotiation(negotiation.getNegotiationId(), Negotiation.Status.ACCEPTED);
                                offerVBox.getChildren().remove(acceptButton.getParent());
                                offerVBox.getChildren().add(acceptedButtonHBox);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        });
                    }

                    Button cancelButton = (Button) offerButtonHBox.lookup("#cancelNegotiationButton");
                    if(cancelButton != null) {
                        cancelButton.setOnAction(e -> {
                            try {
                                updateNegotiation(negotiation.getNegotiationId(), Negotiation.Status.CANCELLED);
                                offerVBox.getChildren().remove(cancelButton.getParent());
                                offerVBox.getChildren().add(cancelledButtonHBox);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        });
                    }

                    Button counterOfferButton = (Button) offerButtonHBox.lookup("#counterNegotiationButton");
                    if(counterOfferButton != null) {
                        counterOfferButton.setOnAction(e -> {
                            onSuggestPriceClicked();
                        });
                    }
                } else if(negotiation.getStatus().equals(Negotiation.Status.ACCEPTED)) {
                    offerVBox.getChildren().add(acceptedButtonHBox);

                    if(salePrice != null) {
                        price.setText(negotiation.getNegotiatedSalePrice().toString());
                    } else if(rentPrice != null) {
                        price.setText(negotiation.getNegotiatedRentPrice().toString() + " (" + negotiation.getNegotiatedRentTerm() + ")");
                    }

                    Button undoButton = (Button) acceptedButtonHBox.lookup("#undoAcceptanceButton");
                    if(undoButton != null) {
                        undoButton.setOnAction(e -> {
                            try {
                                updateNegotiation(negotiation.getNegotiationId(), Negotiation.Status.OFFERED);
                                offerVBox.getChildren().remove(undoButton.getParent());
                                offerVBox.getChildren().add(offerButtonHBox);

//                                String propertyPrice = price.getText();
//                                if(propertyPrice.charAt(propertyPrice.length() - 1) == ')') {
//                                    String[] parts = propertyPrice.split(" ");
//                                    BigDecimal rentPrice = new BigDecimal(parts[0]);
//                                    String rentTerm = parts[1].replace("(", "").replace(")", "");
//
//                                }
//
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        });
                    }
                } else if(negotiation.getStatus().equals(Negotiation.Status.CANCELLED)) {
                    offerVBox.getChildren().add(cancelledButtonHBox);

                    Button undoButton = (Button) cancelledButtonHBox.lookup("#undoCancellationButton");
                    if(undoButton != null) {
                        undoButton.setOnAction(e -> {
                            try {
                                updateNegotiation(negotiation.getNegotiationId(), Negotiation.Status.OFFERED);
                                offerVBox.getChildren().remove(undoButton.getParent());
                                offerVBox.getChildren().add(offerButtonHBox);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        });
                    }
                }
            } else {
                if(negotiation.getStatus().equals(Negotiation.Status.ACCEPTED)) {
                    if(salePrice != null) {
                        price.setText(negotiation.getNegotiatedSalePrice().toString());
                    } else if(rentPrice != null) {
                        price.setText(negotiation.getNegotiatedRentPrice().toString() + " (" + negotiation.getNegotiatedRentTerm() + ")");
                    }
                }
            }

            chatListContainer.getChildren().add(priceSuggestionContainer);
        }
    }

    private void updateNegotiation(Long negotiationId, Negotiation.Status status) throws IOException {
        String targetUrl = "http://localhost:8080/api/negotiations/updateStatus";
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("negotiationId", negotiationId);
        jsonMap.put("status", status);
        String jsonInputString = mapper.writeValueAsString(jsonMap);

        Pair<Integer, String> pair = HTTPClient.sendPostRequest(targetUrl, jsonInputString);
        if(pair.getKey() == 200) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Negotiation Status Update");
            alert.setHeaderText("Negotiation Updated");
            alert.setContentText("Negotiation Status Updated Successfully");
            alert.showAndWait();
        }
    }

    public void onSuggestPriceClicked() {
        try{
            // Create a new stage for the edit window
            Stage stage = new Stage();
            stage.setTitle("Chat with user");
            stage.initModality(Modality.APPLICATION_MODAL);

            // Create an FXMLLoader instance and load the FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/suggestionPriceTemplate.fxml"));
            Parent suggestPricePane = loader.load();

            Pane salePricePane = (Pane) suggestPricePane.lookup("#propertySellPricePane");
            Pane rentPricePane = (Pane) suggestPricePane.lookup("#propertyRentTermAndPriceAndDatePane");
            if(propertyPrice.charAt(propertyPrice.length()-1) == ')') {
                salePricePane.setVisible(false);
                rentPricePane.setVisible(true);

                ChoiceBox<String> rentTermBox = (ChoiceBox<String>) rentPricePane.lookup("#propertyRentTermBox");
                rentTermBox.getItems().addAll(
                        "Weekly",
                        "Biweekly",
                        "Monthly",
                        "Yearly"
                );
            } else {
                salePricePane.setVisible(true);
                rentPricePane.setVisible(false);
            }

            Button suggestButton = (Button) suggestPricePane.lookup("#suggestButton");
            if(suggestButton != null) {
                suggestButton.setOnAction(_ -> {
                    Map<String, Object> negotiationOffer = new HashMap<>();
                    negotiationOffer.put("negotiatorEmail", senderEmail);
                    negotiationOffer.put("recipientEmail", receiverEmail);
                    negotiationOffer.put("propertyTitle", propertyTitle);
                    negotiationOffer.put("propertyLocation", propertyLocation);
                    if(salePricePane.isVisible() && !rentPricePane.isVisible()) {
                        TextField salePrice = (TextField) suggestPricePane.lookup("#propertySalePrice");
                        negotiationOffer.put("salePrice", salePrice.getText());
//                        try {
//                            addNegotiationToChat(salePrice.getText());
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
                    } else {
                        TextField rentPrice = (TextField) suggestPricePane.lookup("#propertyRentPrice");
                        ChoiceBox<String> rentTermBox = (ChoiceBox<String>) rentPricePane.lookup("#propertyRentTermBox");
                        negotiationOffer.put("rentPrice", rentPrice.getText());
                        negotiationOffer.put("rentTerm", rentTermBox.getValue());
//                        try {
//                            addNegotiationToChat(rentPrice.getText() + " (" + rentTermBox.getValue() + ")");
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
                    }

                    try {
                        String targetUrl = "http://localhost:8080/api/negotiations/offer";
                        String jsonInputString = mapper.writeValueAsString(negotiationOffer);

                        Pair<Integer, String> pair = HTTPClient.sendPostRequest(targetUrl, jsonInputString);
                        if(pair.getKey() == 200) {
                            ObjectMapper objectMapper = new ObjectMapper();
                            objectMapper.findAndRegisterModules(); // For date-time support (e.g., LocalDateTime)
                            Negotiation negotiation = objectMapper.readValue(pair.getValue(), Negotiation.class);

                            addNegotiationToChat(negotiation);
//                            if(salePricePane.isVisible() && !rentPricePane.isVisible())
//                                addNegotiationToChat((String) negotiationOffer.get("salePrice"));
//                            else {
//                                addNegotiationToChat(negotiationOffer.get("rentPrice") + " (" + negotiationOffer.get("rentTerm") + ")");
//                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Suggest price");
                            alert.setHeaderText("Something went wrong!");
                            alert.setContentText("An error occured!");
                            alert.showAndWait();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    Stage stage1 = (Stage) suggestPricePane.getScene().getWindow();
                    stage1.close();
                });
            }

            // Set up the scene and show the stage
            Scene scene = new Scene(suggestPricePane);
            stage.setScene(scene);
            stage.showAndWait();  // Waits until the edit window is closed
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onConfirmPurchaseClicked() throws IOException {
        Boolean confirmationStatus = false;
        if(confirmPurchaseToggleButton.isSelected()) {
            confirmationStatus = true;
        }
        try{
            String targetUrl = "http://localhost:8080/api/purchase-confirmation/confirm";
            Map<String, Object> confirmPurchase = new HashMap<>();
            confirmPurchase.put("purchaserEmail", receiverEmail);
            confirmPurchase.put("sellerEmail", sellerEmail);
            confirmPurchase.put("propertyTitle", propertyTitle);
            confirmPurchase.put("propertyLocation", propertyLocation);
            confirmPurchase.put("confirmationStatus", confirmationStatus);

            String jsonInputString = mapper.writeValueAsString(confirmPurchase);

            Pair<Integer, String> pair = HTTPClient.sendPostRequest(targetUrl, jsonInputString);
            if(pair.getKey() == 200) {
                if(confirmationStatus == true) {
                    confirmPurchaseToggleButton.setText("Confirmed");
                    confirmPurchaseToggleButton.setStyle("-fx-background-color: #762179; -fx-text-fill:  #c874cd; -fx-background-radius: 8px;");
                    Image confirmedPurchaseImage = new Image(String.valueOf(getClass().getResource("/images/confirmed purchase icon.png")));
                    ImageView confirmedPurchaseImageView = new ImageView(confirmedPurchaseImage);
                    confirmPurchaseToggleButton.setGraphic(confirmedPurchaseImageView);
                } else {
                    if(salePrice != null) {
                        confirmPurchaseToggleButton.setText("Confirm Buyer");
                    } else if(rentPrice != null) {
                        confirmPurchaseToggleButton.setText("Confirm Renter");
                    }
                    confirmPurchaseToggleButton.setStyle("-fx-background-color: #c874cd; -fx-text-fill:  #762179; -fx-background-radius: 8px;");
                    Image confirmedPurchaseImage = new Image(String.valueOf(getClass().getResource("/images/confirm purchase icon.png")));
                    ImageView confirmedPurchaseImageView = new ImageView(confirmedPurchaseImage);
                    confirmPurchaseToggleButton.setGraphic(confirmedPurchaseImageView);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onMakePaymentClicked() {
        try{
            String targetUrl = "http://localhost:8080/api/purchase-confirmation/get";
            Map<String, Object> confirmPurchase = new HashMap<>();
            confirmPurchase.put("purchaserEmail", senderEmail);
            confirmPurchase.put("sellerEmail", sellerEmail);
            confirmPurchase.put("propertyTitle", propertyTitle);
            confirmPurchase.put("propertyLocation", propertyLocation);

            String jsonInputString = mapper.writeValueAsString(confirmPurchase);

            Pair<Integer, String> pair = HTTPClient.sendPostRequest(targetUrl, jsonInputString);
            if(pair.getKey() == 200) {
                if(pair.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Purchase confirmation error!");
                    alert.setHeaderText("Something went wrong!");
                    alert.setContentText("The seller didn't confirm you to purchase this property yet.");
                    alert.showAndWait();
                } else if(pair.getValue().equals("true")) {
                    FXMLLoader loader = new FXMLLoader(RealEstateApplication.class.getResource("makePaymentPage.fxml"));
                    Parent makePaymentPage = loader.load();

                    makePaymentController makePaymentController = loader.getController();
                    makePaymentController.setUsers(sellerEmail, senderEmail);

                    Property propertyClass = HTTPClient.getProperty("http://localhost:8080/api/properties/property/get", propertyTitle, propertyLocation);
                    makePaymentController.setPropertyDetails(propertyClass.getPicture(), propertyTitle, price.getText(), propertyLocation);

                    Stage stage = (Stage) price.getScene().getWindow();
                    Scene scene = new Scene(makePaymentPage);
                    stage.setTitle("Make Payment");
                    stage.setScene(scene);
                    stage.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Purchase confirmation error!");
                    alert.setHeaderText("Something went wrong!");
                    alert.setContentText("The seller didn't confirm you to purchase this property yet.");
                    alert.showAndWait();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
