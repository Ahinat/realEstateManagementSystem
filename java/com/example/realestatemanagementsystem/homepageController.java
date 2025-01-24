package com.example.realestatemanagementsystem;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class homepageController implements Initializable {

    public VBox forSaleLayer;
    public VBox forRentLayer;
    public VBox addPropertyLayer;
    public VBox newPropertyLayer;
    public VBox findAgentLayer;
    public VBox servicesLayer;
    public AnchorPane sideMenu;

    public VBox apartmentsSale;
    public VBox residentialSale;
    public VBox apartmentsRent;
    public VBox residentialRent;
    public VBox businessRent;
    public VBox newApartmentSale;
    public VBox newResidentialSale;
    public VBox reportLayer;

    public Button joinButton;
    public HBox userAccountContainer;
    public Label userAccountContainerLabel;
    public ImageView userAccountProfilePicture;
    
    public FlowPane showPropertiesFlowPane;
    public HBox pageNumberAndChangingHBox;
    public Label noPropertyFoundLabel;
    public Button previousPageButton;
    public Label currentPageNumber;
    public Button nextPageButton;

    // categories filter
    public ToggleButton apartmentCategoryInHomepage;
    public ToggleButton housesCategoryInHomepage;
    public ToggleButton landsCategoryInHomepage;
    public ToggleButton residentialUnitsCategoryInHomepage;
    public ToggleButton commericalUnitsCategoryInHomepage;
    public ToggleButton businessPlacesCategoryInHomepage;
    public Pane categories;

    // show property
    public ScrollPane showPropertyPane;
    public ImageView propertyPicturePrevious;
    public ImageView propertyPictureNext;
    public ImageView propertyPictureCurrent;
    public Button showNextPropertyPictureButton;
    public Button showPreviousPropertyPictureButton;
    public Pane apartmentFeaturesShow;
    public Label petPolicyShow, furniturePolicyShow, terracePreferrenceShow, parkingAvailabilityShow;
    public Pane houseFeaturesShow;
    public Label basementPreferrenceShow, securityFeaturesShow, garageAvailabilityShow;
    public Pane landFeaturesShow;
    public Label landTopographyShow, landUtilitiesShow, landRoadAccessShow;
    public Pane residentialUnitFeaturesShow;
    public Label residentialUnitAmenitiesShow, residentialUnitStorageShow, residentialUnitNoiseInsulationShow;
    public Pane commercialUnitFeaturesShow;
    public Label commercialUnitPurposesShow, commercialUnitFloorLayoutShow, commercialUnitAccessibilitiesShow;
    public Pane businessPlaceFeaturesShow;
    public Label businessPlacePurposeShow, businessPlaceVentilationFaciltiyShow, businessPlacePowerSupplyShow;
    public Label propertyAvailableForRentFromDateShow;
    public Label showPropertyPrice;
    public Label propertyTitleShow;
    public Pane propertyAvailableDatePane;
    public Label showPropertyLocation;
    public Pane apartmentSpecificFeaturesShow, houseSpecificFeaturesShow, landSpecificFeaturesShow, residentialUnitSpecificFeaturesShow, commercialUnitSpecificFeaturesShow, businessPlaceSpecificFeaturesShow;
    public Label apartmentBedroomNumberShow, apartmentBathroomNumberShow, apartmentFloorNumberShow, apartmentTotalFloorNumberShow, apartmentBalconyAvailabilityShow, apartmentMaintenanceServicesShow;
    public Label houseBedroomNumberShow, houseBathroomNumberShow, houseFloorNumberShow, houseRoofTypeShow, houseGardenShow, houseBalconyShow;
    public Label landTypeShow, landPlotSizeShow, landBorderShow, landPreviousDevelopmentShow, landNearbyInfrastructureShow;
    public Label residentialUnitConfigurationShow, residentialUnitSharedFacilitiesShow, residentialUnitOrientationShow, residentialUnitNaturalLightShow;
    public Label commercialUnitFacadeTypeShow, commercialUnitNearbyAttractionsShow, commercialUnitFireSafetyShow, commercialUnitAirConditioningShow;
    public Label businessPlaceLightingSetupShow, businessPlaceWaitingAreaShow, businessPlaceVisibilityFromRoadShow, businessPlaceInsuranceDetailsShow;
    public Label showPropertyDescription;
    public Button buyOrRentPropertyFromHomepage;

    // Filter properties
    public CheckBox salePropertyFilter, rentPropertyFilter;
    public TextField minPricePropertyFilter;
    public TextField maxPricePropertyFilter;
    public ChoiceBox<String> sortByProperty;
    public TextField searchKeywordInHomepage;
    public HBox propertyFoundLabelHBox;
    public Label searchKeyLabel;


    private Stage stage;
    private Scene scene;

    private int currentPage = 0;
    private final int pageSize = 4;
    int CurrentIndex;
    List<String> imagePaths = new ArrayList<>();

    public void activeTransitionOnY(Node c, double move){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(c);
        transition.setToY(move);
        transition.play();
    }

    public void activeTransitionOnX(Node c, double move){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(c);
        transition.setToX(move);
        transition.play();
    }

    public void onBuyActive(MouseEvent mouseEvent) {
        activeTransitionOnY(forSaleLayer, 0);
    }



    public void onBuyDeactive(MouseEvent mouseEvent) {
        // setting submenu deactive
        subMenuDeactive(apartmentsSale);
        subMenuDeactive(residentialSale);

        activeTransitionOnY(forSaleLayer, -105);
    }

    public void onRentActive(MouseEvent mouseEvent) {
        activeTransitionOnY(forRentLayer, 0);
    }

    public void onRentDeactive(MouseEvent mouseEvent) {
        // setting submenu deactive
        subMenuDeactive(apartmentsRent);
        subMenuDeactive(residentialRent);
        subMenuDeactive(businessRent);

        activeTransitionOnY(forRentLayer, -140);
    }

    public void onAddActive(MouseEvent mouseEvent) {
        activeTransitionOnY(addPropertyLayer, 0);
    }

    public void onAddDeactive(MouseEvent mouseEvent) {
        activeTransitionOnY(addPropertyLayer, -70);
    }

    public void onNewActive(MouseEvent mouseEvent) {
        activeTransitionOnY(newPropertyLayer, 0);
    }

    public void onNewDeactive(MouseEvent mouseEvent) {
        // setting submenu deactive
        subMenuDeactive(newApartmentSale);
        subMenuDeactive(newResidentialSale);

        activeTransitionOnY(newPropertyLayer, -105);
    }

    public void onFindAgentActive(MouseEvent mouseEvent) {
        activeTransitionOnY(findAgentLayer, 0);
    }

    public void onFindAgentDeactive(MouseEvent mouseEvent) {
        activeTransitionOnY(findAgentLayer, -105);
    }

    public void onServiceActive(MouseEvent mouseEvent) {
        activeTransitionOnY(servicesLayer, 0);
    }

    public void onServiceDeactive(MouseEvent mouseEvent) {
        // setting submenu deactive
        subMenuDeactive(reportLayer);

        activeTransitionOnY(servicesLayer, -70);
    }

    public void onBuyClicked(MouseEvent mouseEvent) {
        if(forSaleLayer.getTranslateY() == -105){
            onBuyActive(mouseEvent);

            // setting others deactive
            onRentDeactive(mouseEvent);
            onAddDeactive(mouseEvent);
            onNewDeactive(mouseEvent);
            onFindAgentDeactive(mouseEvent);
            onServiceDeactive(mouseEvent);
        }
        else if(forSaleLayer.getTranslateY() == 0){
            onBuyDeactive(mouseEvent);
        }
    }

    public void onRentClicked(MouseEvent mouseEvent) {
        if(forRentLayer.getTranslateY() == -140){
            onRentActive(mouseEvent);

            // setting others deactive
            onBuyDeactive(mouseEvent);
            onAddDeactive(mouseEvent);
            onNewDeactive(mouseEvent);
            onFindAgentDeactive(mouseEvent);
            onServiceDeactive(mouseEvent);
        }
        else if(forRentLayer.getTranslateY() == 0){
            onRentDeactive(mouseEvent);
        }
    }

    public void onAddClicked(MouseEvent mouseEvent) throws IOException {
        if(!YourPropertyUserSession.getInstance().isLoggedIn()){
            onJoinClicked();
        } else {
            String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

            String targetUrl = "http://localhost:8080/api/yourpropertyseller";

            try{
                JSONObject jsonObject = HTTPClient.sendGetRequest(targetUrl, userEmail);

                if(jsonObject != null) {
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addPropertyPage.fxml")));
                    Stage stage = (Stage) joinButton.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setTitle("Add New Property");
                    stage.setScene(scene);
                    stage.show();
                } else {
//                    updateSellerProfile.setVisible(false);
//                    createSellerProfile.setVisible(true);

                    // Redirect to the dashboard and show the create seller profile pane
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("profileDashboardPage.fxml"));
                    Parent root = loader.load();

                    // Get the controller for the dashboard
                    dashboardController dashboardController = loader.getController();

                    // Call the method to show the create seller profile pane
                    dashboardController.onManageSellerProfileOptionClicked();

                    // Switch to the dashboard scene
                    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Profile Dashboard");
                    stage.show();
                }

            } catch (RuntimeException | IOException e) {
                if (e.getMessage().contains("HTTP error code : 404")) {
                    // If a 404 error occurs, show create profile pane instead
//                    updateSellerProfile.setVisible(false);
//                    createSellerProfile.setVisible(true);
                    // Redirect to the dashboard and show the create seller profile pane
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("profileDashboardPage.fxml"));
                    Parent root = loader.load();

                    // Get the controller for the dashboard
                    dashboardController dashboardController = loader.getController();

                    // Call the method to show the create seller profile pane
                    dashboardController.onManageSellerProfileOptionClicked();

                    // Switch to the dashboard scene
                    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Profile Dashboard");
                    stage.show();
                } else {
                    // Handle other exceptions
                    e.printStackTrace();
                }
            }
        }

        if(addPropertyLayer.getTranslateY() == -70){
            onAddActive(mouseEvent);

            // setting others deactive
            onBuyDeactive(mouseEvent);
            onRentDeactive(mouseEvent);
            onNewDeactive(mouseEvent);
            onFindAgentDeactive(mouseEvent);
            onServiceDeactive(mouseEvent);
        }
        else if(addPropertyLayer.getTranslateY() == 0){
            onAddDeactive(mouseEvent);
        }
    }

    public void onNewClicked(MouseEvent mouseEvent) {
        if(newPropertyLayer.getTranslateY() == -105){
            onNewActive(mouseEvent);

            // setting others deactive
            onBuyDeactive(mouseEvent);
            onRentDeactive(mouseEvent);
            onAddDeactive(mouseEvent);
            onFindAgentDeactive(mouseEvent);
            onServiceDeactive(mouseEvent);
        }
        else if(newPropertyLayer.getTranslateY() == 0){
            onNewDeactive(mouseEvent);
        }
    }

    public void onFindAgentClicked(MouseEvent mouseEvent) {
        if(findAgentLayer.getTranslateY() == -105){
            onFindAgentActive(mouseEvent);

            // setting others deactive
            onBuyDeactive(mouseEvent);
            onRentDeactive(mouseEvent);
            onAddDeactive(mouseEvent);
            onNewDeactive(mouseEvent);
            onServiceDeactive(mouseEvent);
        }
        else if(findAgentLayer.getTranslateY() == 0){
            onFindAgentDeactive(mouseEvent);
        }
    }

    public void onServiceClicked(MouseEvent mouseEvent) {
        if(servicesLayer.getTranslateY() == -70){
            onServiceActive(mouseEvent);

            // setting others deactive
            onBuyDeactive(mouseEvent);
            onRentDeactive(mouseEvent);
            onAddDeactive(mouseEvent);
            onNewDeactive(mouseEvent);
            onFindAgentDeactive(mouseEvent);
        }
        else if(servicesLayer.getTranslateY() == 0){
            onServiceDeactive(mouseEvent);
        }
    }

    public void onJoinClicked() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signInPage.fxml")));
        Stage stage = (Stage) joinButton.getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Sign In");
        stage.setScene(scene);
        stage.show();
    }

    public void onMenuButtonClicked(ActionEvent event) {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.5));
        transition.setNode(sideMenu);
        transition.setToX(0);
        transition.play();
    }

    public void closeSideMenu(MouseEvent mouseEvent) {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.5));
        transition.setNode(sideMenu);
        transition.setToX(-183);
        transition.play();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Check if the user is logged in and set visibility of join button accordingly
        if(YourPropertyUserSession.getInstance().isLoggedIn()) {
            joinButton.setVisible(false);
            userAccountContainer.setVisible(true);

            // Fetch user email
            String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

            // Get user info from database
            String targetUrl = "http://localhost:8080/api/yourpropertyuser";
            try {
                JSONObject jsonUser = HTTPClient.sendGetRequest(targetUrl, userEmail);
                String firstName = jsonUser.getString("FirstName");
                String lastName = jsonUser.getString("LastName");
                String profilePicture = jsonUser.getString("ProfilePicture");


                userAccountContainerLabel.setText(firstName + " " + lastName);
                if(profilePicture != null) userAccountProfilePicture.setImage(new Image(new File(profilePicture).toURI().toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            joinButton.setVisible(true);
            userAccountContainer.setVisible(false);
        }

        sortByProperty.getItems().addAll(
                "Price(High -> Low)",
                "Price(Low -> High)"
        );

        sortByProperty.setValue("Price(Low -> High)");

        ToggleGroup toggleGroup = new ToggleGroup();
        apartmentCategoryInHomepage.setToggleGroup(toggleGroup);
        housesCategoryInHomepage.setToggleGroup(toggleGroup);
        landsCategoryInHomepage.setToggleGroup(toggleGroup);
        residentialUnitsCategoryInHomepage.setToggleGroup(toggleGroup);
        commericalUnitsCategoryInHomepage.setToggleGroup(toggleGroup);
        businessPlacesCategoryInHomepage.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                ToggleButton selectedToggleButton = (ToggleButton) newValue;

                String selectedCategory = selectedToggleButton.getText();
                loadProperties(selectedCategory, null , null, null, null);
            } else {
                loadProperties(null, null, null, null, null);
            }
        });

        loadProperties(null, null, null, null, null);
    }

    public void saleActive(Node c){
        c.setVisible(true);
        activeTransitionOnX(c, 163);
    }

    public void rentActive(Node c){
        c.setVisible(true);
        activeTransitionOnX(c, 159);
    }

    public void serviceActive(Node c){
        c.setVisible(true);
        activeTransitionOnX(c, 100);
    }

    public void subMenuDeactive(Node c){
        activeTransitionOnX(c, 0);
        c.setVisible(false);
    }

    public void onApartmentsSaleClicked(MouseEvent mouseEvent) {
        if(apartmentsSale.getTranslateX() == 0){
            saleActive(apartmentsSale);
            subMenuDeactive(residentialSale);
        } else if(apartmentsSale.getTranslateX() == 163){
            subMenuDeactive(apartmentsSale);
        }
    }

    public void onResidentialSaleClicked(MouseEvent mouseEvent) {
        if(residentialSale.getTranslateX() == 0){
            saleActive(residentialSale);
            subMenuDeactive(apartmentsSale);
        } else if(residentialSale.getTranslateX() == 163){
            subMenuDeactive(residentialSale);
        }
    }

    public void onCommercialSaleClicked(MouseEvent mouseEvent) {
        subMenuDeactive(apartmentsSale);
        subMenuDeactive(residentialSale);
    }


    public void onApartmentsRentClicked(MouseEvent mouseEvent) {
        if(apartmentsRent.getTranslateX() == 0) {
            rentActive(apartmentsRent);
            subMenuDeactive(residentialRent);
            subMenuDeactive(businessRent);
        } else if(apartmentsRent.getTranslateX() == 159){
            subMenuDeactive(apartmentsRent);
        }
    }

    public void onResidentialRentClicked(MouseEvent mouseEvent) {
        if(residentialRent.getTranslateX() == 0) {
            rentActive(residentialRent);
            subMenuDeactive(apartmentsRent);
            subMenuDeactive(businessRent);
        } else if(residentialRent.getTranslateX() == 159){
            subMenuDeactive(residentialRent);
        }
    }

    public void onCommercialRentClicked(MouseEvent mouseEvent) {
        subMenuDeactive(apartmentsRent);
        subMenuDeactive(residentialRent);
        subMenuDeactive(businessRent);
    }

    public void onBusinessRentClicked(MouseEvent mouseEvent) {
        if(businessRent.getTranslateX() == 0) {
            rentActive(businessRent);
            subMenuDeactive(residentialRent);
            subMenuDeactive(apartmentsRent);
        } else if(businessRent.getTranslateX() == 159){
            subMenuDeactive(businessRent);
        }
    }


    public void newApartmentSaleClicked(MouseEvent mouseEvent) {
        if(newApartmentSale.getTranslateX() == 0){
            saleActive(newApartmentSale);
            subMenuDeactive(newResidentialSale);
        } else if(newApartmentSale.getTranslateX() == 163){
            subMenuDeactive(newApartmentSale);
        }
    }

    public void newResidentialSaleClicked(MouseEvent mouseEvent) {
        if(newResidentialSale.getTranslateX() == 0){
            saleActive(newResidentialSale);
            subMenuDeactive(newApartmentSale);
        } else if(newResidentialSale.getTranslateX() == 163){
            subMenuDeactive(newResidentialSale);
        }
    }

    public void newCommercialSaleClicked(MouseEvent mouseEvent) {
        subMenuDeactive(newApartmentSale);
        subMenuDeactive(newResidentialSale);
    }


    public void onContactUsClicked(MouseEvent mouseEvent) {
        subMenuDeactive(reportLayer);
    }

    public void onReportClicked(MouseEvent mouseEvent) {
        if(reportLayer.getTranslateX() == 0) {
            serviceActive(reportLayer);
        } else if(reportLayer.getTranslateX() == 100){
            subMenuDeactive(reportLayer);
        }
    }


    public void userAccountBoxClicked(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profileDashboardPage.fxml")));
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Profile Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    public void onApplyFilterClicked(ActionEvent actionEvent) {
        String category = null;
        if(apartmentCategoryInHomepage.isSelected()) category = apartmentCategoryInHomepage.getText();
        else if(housesCategoryInHomepage.isSelected()) category = housesCategoryInHomepage.getText();
        else if(landsCategoryInHomepage.isSelected()) category = landsCategoryInHomepage.getText();
        else if(residentialUnitsCategoryInHomepage.isSelected()) category = residentialUnitsCategoryInHomepage.getText();
        else if(commericalUnitsCategoryInHomepage.isSelected()) category = commericalUnitsCategoryInHomepage.getText();
        else if(businessPlacesCategoryInHomepage.isSelected()) category = businessPlacesCategoryInHomepage.getText();

        String offerType = null;
        if(salePropertyFilter.isSelected() && !rentPropertyFilter.isSelected()) offerType = "For sale";
        else if(!salePropertyFilter.isSelected() && rentPropertyFilter.isSelected()) offerType = "For rent";

        BigDecimal minPrice = null, maxPrice = null;
        if(!minPricePropertyFilter.getText().isBlank()) minPrice = new BigDecimal(minPricePropertyFilter.getText());
        if(!maxPricePropertyFilter.getText().isBlank()) maxPrice = new BigDecimal(maxPricePropertyFilter.getText());

        String sortDirection = null;
        if(sortByProperty.getValue().equals("Price(High -> Low)")) sortDirection = "highToLow";
        else if(sortByProperty.getValue().equals("Price(Low -> High)")) sortDirection = "lowToHigh";
//        if(sortByProperty.getValue() != null) sortDirection = sortByProperty.getValue();

        loadProperties(category, offerType, minPrice, maxPrice, sortDirection);
    }

    private void loadProperties(String category, String offerType, BigDecimal minPrice, BigDecimal maxPrice, String sortOrder){
        try{
            StringBuilder targetUrl = new StringBuilder("http://localhost:8080/api/properties");

            PaginatedResponse<Property> response = HTTPClient.getProperties(targetUrl, currentPage, pageSize, category, offerType, minPrice, maxPrice, sortOrder);

            showPropertiesFlowPane.getChildren().clear();
            propertyFoundLabelHBox.setVisible(false);

            List<Property> properties = response.getContent();
            if(properties.size() > 0){
                for(Property property : properties){
                    Pane propertyPane = createPropertyPane(property);
                    showPropertiesFlowPane.getChildren().add(propertyPane);
                }
                pageNumberAndChangingHBox.setVisible(true);
                currentPageNumber.setText(Integer.valueOf(currentPage+1) + " / " + response.getTotalPages());
                previousPageButton.setDisable(currentPage == 0);
                nextPageButton.setDisable(properties.size() < pageSize);
            } else {
                showPropertiesFlowPane.getChildren().add(noPropertyFoundLabel);
                pageNumberAndChangingHBox.setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSearchClicked(ActionEvent actionEvent) {
        try{
            String targetUrl = "http://localhost:8080/api/properties/search";
            String searchKey = searchKeywordInHomepage.getText();

            showPropertiesFlowPane.getChildren().clear();

            List<Property> searchedProperties = HTTPClient.getPropertiesWithSearchKey(targetUrl, searchKey);
            if(searchedProperties.size() > 0){
                propertyFoundLabelHBox.setVisible(true);
                searchKeyLabel.setText('"' + searchKeywordInHomepage.getText() + '"');
                for(Property property : searchedProperties){
                    Pane propertyPane = createPropertyPane(property);
                    showPropertiesFlowPane.getChildren().add(propertyPane);
                }
            } else {
                showPropertiesFlowPane.getChildren().add(noPropertyFoundLabel);
            }

            pageNumberAndChangingHBox.setVisible(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    private void loadProperties(){
//        try{
//            String targetUrl = "http://localhost:8080/api/properties";
//            PaginatedResponse<Property> response = HTTPClient.getProperties(targetUrl, currentPage, pageSize);
//
//            showPropertiesFlowPane.getChildren().clear();
//
//            List<Property> properties = response.getContent();
////            System.out.println("Fetched Properties: " + properties.size());
////            System.out.println(properties.get(0).getPropertyType());
//            for(Property property : properties){
////                printProperty(property);
//                Pane propertyPane = createPropertyPane(property);
//                showPropertiesFlowPane.getChildren().add(propertyPane);
//            }
//
//            currentPageNumber.setText(currentPage+1 + " / " + response.getTotalPages());
//
//            previousPageButton.setDisable(currentPage == 0);
//            nextPageButton.setDisable(properties.size() < pageSize);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    private void printProperty(Property property){
//        System.out.println(property.getPropertyId());
//        System.out.println(property.getPropertyType());
//        System.out.println(property.getPropertyTitle());
//    }

    private Pane createPropertyPane(Property property){
        try {
            // Load the FXML for each property pane
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/propertyHomepageTemplatePane.fxml"));
            Pane propertyPane = loader.load();

            Label propertyType = (Label) propertyPane.lookup("#showPropertyType");
            Label propertyOfferType = (Label) propertyPane.lookup("#propertyOfferType");
            Label titleMyProperty = (Label) propertyPane.lookup("#titleMyProperty");
            Label myPropertyPrice = (Label) propertyPane.lookup("#myPropertyPrice");
            Label myPropertyLocation = (Label) propertyPane.lookup("#myPropertyLocation");
            ImageView propertyImage = (ImageView) propertyPane.lookup("#propertyImage");

            propertyOfferType.setText(property.getOfferType());
            titleMyProperty.setText(property.getPropertyTitle());
            if(property.getOfferType().equals("For sale")) myPropertyPrice.setText(property.getSalePrice().toString());
            else if(property.getOfferType().equals("For rent")) myPropertyPrice.setText(property.getRentPrice().toString() + " (" + property.getRentTerm() + ")");
            myPropertyLocation.setText(property.getStreet() + ", " + property.getCity() + ", " + property.getState() + ", " + property.getPostalCode() + ", " + property.getCountry());
            propertyImage.setImage(new Image(new File(property.getPicture().getFirst()).toURI().toString()));
            propertyType.setText(property.getPropertyType());

            Button viewPropertyButton = (Button) propertyPane.lookup("#viewPropertyButton");
            if(viewPropertyButton != null) {
                viewPropertyButton.setOnAction(_ -> {
                    showProperty(property);
                });
            }

            ToggleButton savePropertyToggle = (ToggleButton) propertyPane.lookup("#savePropertyToggleButtonHomepage");
            if(savePropertyToggle != null) {
                savePropertyToggle.setOnAction(_ -> {
                    if(YourPropertyUserSession.getInstance().isLoggedIn()) {
                        if(savePropertyToggle.isSelected()){
                            String targetUrl = "http://localhost:8080/api/saved-properties/save";
                            String email = YourPropertyUserSession.getInstance().getUserEmail();
                            String title = titleMyProperty.getText();
                            String address = myPropertyLocation.getText();

                            try {
                                Pair<Integer, String> pair = HTTPClient.saveProperty(targetUrl, email, title, address);

                                if(pair.getKey() == 201) {
                                    savePropertyToggle.setText("Saved");
                                    savePropertyToggle.setStyle("-fx-background-color: #571d4f; -fx-text-fill: #ffe6fe; -fx-background-radius: 8px;");

                                    Image savedImage = new Image(String.valueOf(getClass().getResource("/images/saved icon.png")));
                                    ImageView savedImageView = new ImageView(savedImage);
                                    savePropertyToggle.setGraphic(savedImageView);
                                } else {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Error");
                                    alert.setHeaderText("Something went wrong!");
                                    alert.setContentText(pair.getValue());
                            
                                    alert.showAndWait();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            savePropertyToggle.setText("Save");
                            savePropertyToggle.setStyle("-fx-background-color: #ffe6fe; -fx-text-fill: #571d4f; -fx-background-radius: 8px;");
    
                            Image savedImage = new Image(String.valueOf(getClass().getResource("/images/save icon.png")));
                            ImageView savedImageView = new ImageView(savedImage);
                            savePropertyToggle.setGraphic(savedImageView);
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Something went wrong!");
                        alert.setContentText("Please login first!");
                
                        alert.showAndWait();
                    }
                });
            }

            return propertyPane;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onPreviousPageButtonClicked(ActionEvent actionEvent) {
        if(currentPage > 0) {
            currentPage--;
            loadProperties(null, null, null, null, sortByProperty.getValue());
        }
    }

    public void onNextPageButtonClicked(ActionEvent actionEvent) {
        currentPage++;
        loadProperties(null, null, null, null, sortByProperty.getValue());
    }

    public void showProperty(Property property) {
        propertyTitleShow.setText(property.getPropertyTitle());
        showPropertyPrice.setText(property.getOfferType().equals("For sale") ? property.getSalePrice().toString() : property.getRentPrice().toString() + " (" + property.getRentTerm() + ")");
        if(property.getOfferType().equals("For rent")) {
            propertyAvailableDatePane.setVisible(true);
            propertyAvailableForRentFromDateShow.setText(property.getRentAvailableDate());
            buyOrRentPropertyFromHomepage.setText("Rent");
        } else {
            propertyAvailableDatePane.setVisible(false);
            buyOrRentPropertyFromHomepage.setText("Buy");
        }
        showPropertyLocation.setText(property.getStreet() + ", " + property.getCity() + ", " + property.getState() + ", " + property.getPostalCode() + ", " + property.getCountry());
        showPropertyDescription.setText(property.getPropertyDescription());

        CurrentIndex = 0;
        imagePaths = property.getPicture();
        loadImages(CurrentIndex, imagePaths);
        // propertyPictureCurrent.setImage(new Image(new File(property.getPicture().get(0)).toURI().toString()));

        // Set specific features based on property type
        showPropertyFeaturesInPropertyPreviewPane(property.getPropertyType());
        if (property instanceof Apartment) {
            Apartment apartment = (Apartment) property;
            
            petPolicyShow.setText(apartment.getPetPolicy() ? "Yes" : "No");
            furniturePolicyShow.setText(apartment.getFurniturePolicy() ? "Furnished" : "Unfurnished");
            terracePreferrenceShow.setText(apartment.getTerracePolicy() ? "Yes" : "No");
            parkingAvailabilityShow.setText(apartment.getParkingPolicy() ? "Covered" : "Uncovered");

            apartmentBedroomNumberShow.setText(String.valueOf(apartment.getBedrooms()));
            apartmentBathroomNumberShow.setText(String.valueOf(apartment.getBathrooms()));
            apartmentFloorNumberShow.setText(String.valueOf(apartment.getFloor()));
            apartmentTotalFloorNumberShow.setText(String.valueOf(apartment.getTotalFloor()));
            apartmentBalconyAvailabilityShow.setText(apartment.getBalconyPolicy() ? "Yes" : "No");
            apartmentMaintenanceServicesShow.setText(apartment.getMaintenanceServices());
        } else if (property instanceof House) {
            House house = (House) property;
            
            basementPreferrenceShow.setText(house.getBasementAvailability());
            securityFeaturesShow.setText(String.join(", ", house.getSecurityFeatures()));
            garageAvailabilityShow.setText(house.getGarageAvailability() ? "Yes" : "No");

            houseBedroomNumberShow.setText(String.valueOf(house.getBedrooms()));
            houseBathroomNumberShow.setText(String.valueOf(house.getBathrooms()));
            houseFloorNumberShow.setText(String.valueOf(house.getFloor()));
            houseRoofTypeShow.setText(house.getRoofType());
            houseGardenShow.setText(house.getGardenAvailability() ? "Yes" : "No");
            houseBalconyShow.setText(house.getBalconyAvailability() ? "Yes" : "No");
        } else if (property instanceof Land) {
            Land land = (Land) property;
            apartmentSpecificFeaturesShow.setVisible(false);
            houseSpecificFeaturesShow.setVisible(false);
            landSpecificFeaturesShow.setVisible(true);
            residentialUnitSpecificFeaturesShow.setVisible(false);
            commercialUnitSpecificFeaturesShow.setVisible(false);
            businessPlaceSpecificFeaturesShow.setVisible(false);

            landTypeShow.setText(land.getType());
            if(land.getLength() != null && land.getWidth() != null) {
                landPlotSizeShow.setText(land.getLength() + "x" + land.getWidth());
            } else {
                landPlotSizeShow.setText(land.getArea().toString());
            }
            landBorderShow.setText(land.getBorder());
            landPreviousDevelopmentShow.setText(land.getPreviousDevelopment() ? "Yes" : "No");
            landNearbyInfrastructureShow.setText(String.join(", ", land.getNearbyInfrastructure()));
        } else if (property instanceof ResidentialUnit) {
            ResidentialUnit residentialUnit = (ResidentialUnit) property;
            apartmentSpecificFeaturesShow.setVisible(false);
            houseSpecificFeaturesShow.setVisible(false);
            landSpecificFeaturesShow.setVisible(false);
            residentialUnitSpecificFeaturesShow.setVisible(true);
            commercialUnitSpecificFeaturesShow.setVisible(false);
            businessPlaceSpecificFeaturesShow.setVisible(false);

            residentialUnitConfigurationShow.setText(residentialUnit.getConfiguration().toString());
            residentialUnitSharedFacilitiesShow.setText(String.join(", ", residentialUnit.getSharedFacilities()));
            residentialUnitOrientationShow.setText(residentialUnit.getOrientation());
            residentialUnitNaturalLightShow.setText(residentialUnit.getNaturalLight());
        } else if (property instanceof CommercialUnit) {
            CommercialUnit commercialUnit = (CommercialUnit) property;
            apartmentSpecificFeaturesShow.setVisible(false);
            houseSpecificFeaturesShow.setVisible(false);
            landSpecificFeaturesShow.setVisible(false);
            residentialUnitSpecificFeaturesShow.setVisible(false);
            commercialUnitSpecificFeaturesShow.setVisible(true);
            businessPlaceSpecificFeaturesShow.setVisible(false);

            commercialUnitFacadeTypeShow.setText(commercialUnit.getFacade());
            commercialUnitNearbyAttractionsShow.setText(String.join(", ", commercialUnit.getNearbyAttractions()));
            commercialUnitFireSafetyShow.setText(String.join(", ", commercialUnit.getFireSafety()));
            commercialUnitAirConditioningShow.setText(commercialUnit.getAirCondition());
        } else if (property instanceof BusinessPlace) {
            BusinessPlace businessPlace = (BusinessPlace) property;
            apartmentSpecificFeaturesShow.setVisible(false);
            houseSpecificFeaturesShow.setVisible(false);
            landSpecificFeaturesShow.setVisible(false);
            residentialUnitSpecificFeaturesShow.setVisible(false);
            commercialUnitSpecificFeaturesShow.setVisible(false);
            businessPlaceSpecificFeaturesShow.setVisible(true);

            businessPlaceLightingSetupShow.setText(businessPlace.getLightingSetup());
            businessPlaceWaitingAreaShow.setText(businessPlace.getWaitingArea());
            businessPlaceVisibilityFromRoadShow.setText(businessPlace.getVisibilityFromRoad());
            businessPlaceInsuranceDetailsShow.setText(businessPlace.getInsuranceDetails());
        }

        // Show the property pane
        showPropertyPane.setVisible(true);

        activeTransitionOnY(showPropertyPane, 0);
    }

    private void showPropertyFeaturesInPropertyPreviewPane(String propertyType) {
        if(propertyType.equals("Apartment")){
            apartmentFeaturesShow.setVisible(true);
            apartmentSpecificFeaturesShow.setVisible(true);
        } else{
            apartmentFeaturesShow.setVisible(false);
            apartmentSpecificFeaturesShow.setVisible(false);
        }

        if(propertyType.equals("House")){
            houseFeaturesShow.setVisible(true);
            houseSpecificFeaturesShow.setVisible(true);
        } else{
            houseFeaturesShow.setVisible(false);
            houseSpecificFeaturesShow.setVisible(false);
        }

        if(propertyType.equals("Land")){
            landFeaturesShow.setVisible(true);
            landSpecificFeaturesShow.setVisible(true);
        } else{
            landFeaturesShow.setVisible(false);
            landSpecificFeaturesShow.setVisible(false);
        }

        if(propertyType.equals("Residential unit")){
            residentialUnitFeaturesShow.setVisible(true);
            residentialUnitSpecificFeaturesShow.setVisible(true);
        } else{
            residentialUnitFeaturesShow.setVisible(false);
            residentialUnitSpecificFeaturesShow.setVisible(false);
        }

        if(propertyType.equals("Commercial unit")){
            commercialUnitFeaturesShow.setVisible(true);
            commercialUnitSpecificFeaturesShow.setVisible(true);
        } else{
            commercialUnitFeaturesShow.setVisible(false);
            commercialUnitSpecificFeaturesShow.setVisible(false);
        }

        if(propertyType.equals("Business place")){
            businessPlaceFeaturesShow.setVisible(true);
            businessPlaceSpecificFeaturesShow.setVisible(true);
        } else{
            businessPlaceFeaturesShow.setVisible(false);
            businessPlaceSpecificFeaturesShow.setVisible(false);
        }
    }

    private void loadImages(int currentIndex, List<String> imagePaths) {
        int currIndex = (currentIndex + imagePaths.size()) % imagePaths.size();
        int prevIndex = (currIndex - 1 + imagePaths.size()) % imagePaths.size();
        int nextIndex = (currIndex + 1) % imagePaths.size();

        propertyPictureCurrent.setImage(new Image(new File(imagePaths.get(currIndex)).toURI().toString()));
        propertyPicturePrevious.setImage(new Image(new File(imagePaths.get(prevIndex)).toURI().toString()));
        propertyPictureNext.setImage(new Image(new File(imagePaths.get(nextIndex)).toURI().toString()));
    }

    public void onShowNextPropertyPictureClicked() {
        CurrentIndex++;
        loadImages(CurrentIndex, imagePaths);
    }

    public void onShowPreviousPropertyPictureClicked() {
        CurrentIndex--;
        loadImages(CurrentIndex, imagePaths);
    }

    public void onCancelShowPropertyClicked(ActionEvent actionEvent) {
        activeTransitionOnY(showPropertyPane, 642);
//        TranslateTransition transition1 = new TranslateTransition();
//        transition1.setDuration(Duration.seconds(0.7));
//        transition1.setNode(showPropertyPane);
//        transition1.setToY(642);
//        transition1.play();

        showPropertyPane.setVisible(false);
    }

    public void onBuyOrRentPropertyClicked(ActionEvent actionEvent) throws IOException {
        if(!YourPropertyUserSession.getInstance().isLoggedIn()) {
            onJoinClicked();
        } else {
            String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

            String targetUrl = "http://localhost:8080/api/yourproperty" + buyOrRentPropertyFromHomepage.getText().toLowerCase() + "er";
            System.out.println(targetUrl);

            try{
                JSONObject jsonObject = HTTPClient.sendGetRequest(targetUrl, userEmail);
                System.out.println(jsonObject);
                if(jsonObject != null) {
                    // Call the HTTPClient getPropertySeller method
                    JSONObject userJson = HTTPClient.sendGetPropertySellerRequest("http://localhost:8080/api/properties/propertyseller", propertyTitleShow.getText(), showPropertyLocation.getText());

                    // Create a new stage for the edit window
                    Stage stage = new Stage();
                    stage.setTitle("Chat with user");
                    stage.initModality(Modality.APPLICATION_MODAL);

                    // Create an FXMLLoader instance and load the FXML
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("chatBoxPage.fxml"));
                    Parent root = loader.load();

                    // Get the controller for chatBox
                    chatBoxController chatBoxController = loader.getController();
                    chatBoxController.setUserDetails(userJson.getString("sellerPicture"), userJson.getString("sellerFirstName") + userJson.getString("sellerLastName"));
                    chatBoxController.setUserEmail(userEmail, userJson.getString("sellerEmail"));
                    chatBoxController.setPropertyData(propertyTitleShow.getText(), showPropertyLocation.getText(), showPropertyPrice.getText(), propertyPictureCurrent.getImage().getUrl());
                    chatBoxController.setSellerEmail(userJson.getString("sellerEmail"));
                    chatBoxController.loadChatHistory();

                    // Set up the scene and show the stage
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.showAndWait();  // Waits until the edit window is closed
                } else {
                    // Redirect to the dashboard and show the create buyer or renter profile pane
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("profileDashboardPage.fxml"));
                    Parent root = loader.load();

                    // Get the controller for the dashboard
                    dashboardController dashboardController = loader.getController();

                    // Call the method to show the create buyer or renter profile pane
                    if(buyOrRentPropertyFromHomepage.getText().equals("Buy")) {
//                        System.out.println(buyOrRentPropertyFromHomepage.getText());
                        dashboardController.onManageBuyerProfileOptionClicked();
                    }
                    else if(buyOrRentPropertyFromHomepage.getText().equals("Rent")) {
//                        System.out.println(buyOrRentPropertyFromHomepage.getText());
                        dashboardController.onManageRenterProfileOptionClicked();
                    }

                    // Switch to the dashboard scene
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Profile Dashboard");
                    stage.show();
                }

            } catch (RuntimeException | IOException e) {
                if (e.getMessage().contains("HTTP error code : 404")) {
                    // Redirect to the dashboard and show the create buyer or renter profile pane
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("profileDashboardPage.fxml"));
                    Parent root = loader.load();

                    // Get the controller for the dashboard
                    dashboardController dashboardController = loader.getController();

                    // Call the method to show the create buyer or renter profile pane
                    if(buyOrRentPropertyFromHomepage.getText().equals("Buy")) dashboardController.onManageBuyerProfileOptionClicked();
                    else if(buyOrRentPropertyFromHomepage.getText().equals("Rent")) dashboardController.onManageRenterProfileOptionClicked();

                    // Switch to the dashboard scene
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Profile Dashboard");
                    stage.show();
                } else {
                    // Handle other exceptions
                    e.printStackTrace();
                }
            }
        }
    }
}
