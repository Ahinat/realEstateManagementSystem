package com.example.realestatemanagementsystem;


import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class addPropertyController implements Initializable {


    public AnchorPane menuBar;
    public Button menuButton;
    public Pane logoPane;

    public ChoiceBox<String> offerTypeChoiceBox;
    public TextField propertyTitle;
    public ChoiceBox<String> propertyType;
    public TextArea propertyDescription;

    // Property Key Features
    public Pane apartmentFeatures, houseFeatures, landFeatures, residentialUnitFeatures, commercialUnitFeatures, businessPlacesFeatures;
    public RadioButton PetPolicyYes, PetPolicyNo;
    public RadioButton furnishedPolicyYes, furnishedPolicyNo;
    public RadioButton terraceAvailabilityYes, terraceAvailabilityNo;
    public RadioButton parkingAvailabilityYes, parkingAvailabilityNo;
    public RadioButton basementPreferenceYes, basementPreferrenceNo;
    public CheckBox SecuritySystem, Cameras, GatedEntry;
    public RadioButton garagePreferenceYes, garagePreferrenceNo;
    public RadioButton flatLand, hillyLand, slopedLand;
    public CheckBox electricityUtilityLand, waterUtilityLand, sewageUtilityLand;
    public RadioButton pavedRoadAccessLand, unpavedRoadAccessLand;
    public CheckBox poolAmenitiyResidentialUnit, gymAmenityResidentialUnit, playgroundAmenityResidentialUnit;
    public RadioButton closetStorageResidentialUnit, basementStorageResidentialUnit, builtInStorageResidentialUnit;
    public RadioButton noiseInsulatedResidentialUnit, noiseExposeResidentialUnit;
    public CheckBox retailPurposeCommercialUnit, officePurposeCommercialUnit, medicalPurposeCommercialUnit, restaurantPurposeCommercialUnit;
    public RadioButton openPlanFloorLayoutCommercialUnit, modularFloorLayoutCommercialUnit, separateFloorLayoutCommercialUnit;
    public CheckBox elevatorCommercialUnit, brailleSignageCommercialUnit, accessibleWashroomCommercialUnit;
    public CheckBox storePurposeBusinessPlace, garagePurposeBusinessPlace;
    public RadioButton standardVentilationBusinessPlace, highPoweredFansVentilationBusinessPlace, airFiltrationVentilationBusinessPlace;
    public CheckBox standardPowerSupplyBusinessPlace, highVoltagePowerBusinessPlace, generatorPowerSupplyBusinessPlace;

    // Property Specific Features
    public Pane specificFeaturesApartment, specificFeaturesHouse, specificFeaturesLand, specificFeaturesResidentialUnit, specificFeaturesCommercialUnit, specificFeaturesBusinessPlace;
    public TextField numberOfBedroomsInApartment, numberOfBathroomsInApartment;
    public TextField floorNumberOfApartment, totalFloorsOfBuildingInApartment;
    public TextField maintenanceServicesApartment;
    public RadioButton apartmentBalconyYes, apartmentBalconyNo;
    public TextField numberOfBedroomsInHouse, numberOfBathroomsInHouse;
    public TextField numberOfFloorsInHouse;
    public RadioButton houseRoofTypeFlat, houseRoofTypeSloped, houseRoofTypeMaterial;
    public RadioButton houseGardenYes, houseGardenNo;
    public RadioButton houseBalconyYes, houseBalconyNo;
    public RadioButton landTypeAgricultural, landTypeResidential, landTypeCommercial, landTypeIndustrial;
    public TextField landLength, landWidth;
    public TextField landArea;
    public ToggleButton toggleBetweenDimensionAndAreaLand;
    public RadioButton landBorderFenced, landBorderUnfenced, landBorderBoundaryMarkers;
    public RadioButton landPreviousDevelopmentYes, landPreviousDevelopmentNo;
    public CheckBox landNearbyInfrastructureRoads, landNearbyInfrastructureSchools, landNearbyInfrastructureHospitals;
    public Spinner<Integer> bhkResidentialUnit;
    public CheckBox residentialUnitLaundry, residentialUnitGarbageDisposal, residentialUnitInternet;
    public TextField residentialUnitOrientation;
    public RadioButton residentialUnitNaturalLightLow, residentialUnitNaturalLightMedium, residentialUnitNaturalLightHigh;
    public RadioButton commercialUnitFacadeGlass, commercialUnitFacadeConcrete, commercialUnitFacadeMetal;
    public CheckBox commercialUnitNearbyAttractionMalls, commercialUnitNearbyAttractionSchools, commercialUnitNearbyAttractionParks, commercialUnitNearbyAttractionRestaurants;
    public CheckBox commercialUnitFireAlarm, commercialUnitFireExtinguisher, commercialUnitEmergencyExit;
    public RadioButton commercialUnitAirConditioningCentral, commercialUnitAirConditioningIndividual, commercialUnitAirConditioningNone;
    public RadioButton businessPlaceAmbientLighting, businessPlaceTaskLighting, businessPlaceAccentLighting;
    public RadioButton businessPlaceDedicatedWaitingArea, businessPlaceSeatingSpaceWaitingArea, businessPlaceNoWaitingArea;
    public RadioButton businessPlaceHighVisibility, businessPlaceMediumVisibility, businessPlaceLowVisibility;
    public TextArea businessPlaceInsuranceDetails;


    // Property Price
    public Pane propertySellPricePane, propertyRentTermAndPriceAndDatePane;
    public TextField propertySalePrice;
    public ChoiceBox<String> propertyRentTermBox;
    public TextField propertyRentPrice;
    public DatePicker propertyAvailableForRentFromDate;

    // Property Location
    public ChoiceBox<String> propertyCountryBox;
    public ChoiceBox<String> propertyStateBox;
    public TextField propertyCity;
    public TextField propertyPostalCode;
    public TextField propertyStreetAddress;

    // Property Pictures
    public Label chosenFilePath;
    public VBox propertyImagesAndAddPropertyButtonVbox;
    public Pane addPropertyButtonPane;
    public Button selectImageButton;
    public File selectedProfilePicture;

    // Map for storing countries and their corresponding states
    private final Map<String, ObservableList<String>> countryStateMap = new HashMap<>();

    // Add property preview
    public ScrollPane addPropertyPreviewPane;
    public ImageView propertyPictureShow;
    public Button nextImageButton;
    public Button previousImageButton;
    public Pane apartmentFeaturesShow;
    public Label petPolicyShow;
    public Label furniturePolicyShow;
    public Label terracePreferrenceShow;
    public Label parkingAvailabilityShow;
    public Pane houseFeaturesShow;
    public Label basementPreferrenceShow;
    public Label securityFeaturesShow;
    public Label garageAvailabilityShow;
    public Pane landFeaturesShow;
    public Label landTopographyShow;
    public Label landUtilitiesShow;
    public Label landRoadAccessShow;
    public Pane residentialUnitFeaturesShow;
    public Label residentialUnitAmenitiesShow;
    public Label residentialUnitStorageShow;
    public Label residentialUnitNoiseInsulationShow;
    public Pane commercialUnitFeaturesShow;
    public Label commercialUnitPurposesShow;
    public Label commercialUnitFloorLayoutShow;
    public Label commercialUnitAccessibilitiesShow;
    public Pane businessPlaceFeaturesShow;
    public Label businessPlacePurposeShow;
    public Label businessPlaceVentilationFaciltiyShow;
    public Label businessPlacePowerSupplyShow;
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


    private List<String> propertyPictures = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private int currentPictureIndex = 0;


    public void addItemsInChoiceBoxes(){
        offerTypeChoiceBox.getItems().addAll(
                "For sale",
                "For rent"
        );
        propertyType.getItems().addAll(
                "Apartment",
                "House",
                "Land",
                "Residential Unit",
                "Commercial Unit",
                "Business Place"
        );
        propertyRentTermBox.getItems().addAll(
                "Weekly",
                "Biweekly",
                "Monthly",
                "Yearly"
        );
    }

    public void showPropertyPricePane(String offerType){
        propertySellPricePane.setVisible(offerType.equals("For sale"));

        propertyRentTermAndPriceAndDatePane.setVisible(offerType.equals("For rent"));
    }

    public void showPropertyFeaturesPane(String propertyType){
        if(propertyType.equals("Apartment")){
            apartmentFeatures.setVisible(true);
            specificFeaturesApartment.setVisible(true);

            apartmentFeaturesShow.setVisible(true);
            apartmentSpecificFeaturesShow.setVisible(true);
        } else{
            apartmentFeatures.setVisible(false);
            specificFeaturesApartment.setVisible(false);

            apartmentFeaturesShow.setVisible(false);
            apartmentSpecificFeaturesShow.setVisible(false);
        }

        if(propertyType.equals("House")){
            houseFeatures.setVisible(true);
            specificFeaturesHouse.setVisible(true);

            houseFeaturesShow.setVisible(true);
            houseSpecificFeaturesShow.setVisible(true);
        } else{
            houseFeatures.setVisible(false);
            specificFeaturesHouse.setVisible(false);

            houseFeaturesShow.setVisible(false);
            houseSpecificFeaturesShow.setVisible(false);
        }

        if(propertyType.equals("Land")){
            landFeatures.setVisible(true);
            specificFeaturesLand.setVisible(true);

            landFeaturesShow.setVisible(true);
            landSpecificFeaturesShow.setVisible(true);
        } else{
            landFeatures.setVisible(false);
            specificFeaturesLand.setVisible(false);

            landFeaturesShow.setVisible(false);
            landSpecificFeaturesShow.setVisible(false);
        }

        if(propertyType.equals("Residential unit")){
            residentialUnitFeatures.setVisible(true);
            specificFeaturesResidentialUnit.setVisible(true);

            residentialUnitFeaturesShow.setVisible(true);
            residentialUnitSpecificFeaturesShow.setVisible(true);
        } else{
            residentialUnitFeatures.setVisible(false);
            specificFeaturesResidentialUnit.setVisible(false);

            residentialUnitFeaturesShow.setVisible(false);
            residentialUnitSpecificFeaturesShow.setVisible(false);
        }

        if(propertyType.equals("Commercial unit")){
            commercialUnitFeatures.setVisible(true);
            specificFeaturesCommercialUnit.setVisible(true);

            commercialUnitFeaturesShow.setVisible(true);
            commercialUnitSpecificFeaturesShow.setVisible(true);
        } else{
            commercialUnitFeatures.setVisible(false);
            specificFeaturesCommercialUnit.setVisible(false);

            commercialUnitFeaturesShow.setVisible(false);
            commercialUnitSpecificFeaturesShow.setVisible(false);
        }

        if(propertyType.equals("Business place")){
            businessPlacesFeatures.setVisible(true);
            specificFeaturesBusinessPlace.setVisible(true);

            businessPlaceFeaturesShow.setVisible(true);
            businessPlaceSpecificFeaturesShow.setVisible(true);
        } else{
            businessPlacesFeatures.setVisible(false);
            specificFeaturesBusinessPlace.setVisible(false);

            businessPlaceFeaturesShow.setVisible(false);
            businessPlaceSpecificFeaturesShow.setVisible(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addItemsInChoiceBoxes();

        showPropertyPricePane("");
        // Add an event listener to the Offer Type ChoiceBox
        offerTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (_, _, newValue) -> {
                    if (newValue != null) {
                        showPropertyPricePane(newValue);
                    }
                }
        );

        showPropertyFeaturesPane("");
        // Add an event listener to the Property Type ChoiceBox
        propertyType.getSelectionModel().selectedItemProperty().addListener(
                (_, _, newValue) -> {
                    // Call your method to show the appropriate feature pane
                    if (newValue != null) {
                        showPropertyFeaturesPane(newValue);
                    }
                }
        );

        // Populate the Country-State data
        loadCountryStateData();

        // Populate the Country ChoiceBox
        ObservableList<String> countries = FXCollections.observableArrayList(countryStateMap.keySet());
        propertyCountryBox.setItems(countries);

        // Add listener to update State ChoiceBox based on selected Country
        propertyCountryBox.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
            if (newValue != null) {
                propertyStateBox.setItems(countryStateMap.get(newValue));
                propertyStateBox.setDisable(false); // Enable the State ChoiceBox
            }
        });

        // Disable the State ChoiceBox initially
        propertyStateBox.setDisable(true);
    }

    private void loadCountryStateData() {
        // United States
        countryStateMap.put("United States", FXCollections.observableArrayList(
                "California", "Texas", "Florida", "New York", "Illinois", "Georgia", "Ohio", "North Carolina", "Michigan", "Virginia"
        ));

        // India
        countryStateMap.put("India", FXCollections.observableArrayList(
                "Maharashtra", "Karnataka", "Tamil Nadu", "Delhi", "Uttar Pradesh", "Rajasthan", "Gujarat", "Kerala", "West Bengal", "Punjab"
        ));

        // Canada
        countryStateMap.put("Canada", FXCollections.observableArrayList(
                "Ontario", "Quebec", "British Columbia", "Alberta", "Manitoba", "Nova Scotia", "New Brunswick", "Saskatchewan", "Newfoundland and Labrador"
        ));

        // Bangladesh
        countryStateMap.put("Bangladesh", FXCollections.observableArrayList(
                "Dhaka", "Chittagong", "Rajshahi", "Khulna", "Sylhet", "Barisal", "Rangpur", "Mymensingh",
                "Gazipur", "Narayanganj", "Comilla", "Narsingdi", "Jessore", "Cox's Bazar", "Bogra",
                "Dinajpur", "Kushtia", "Faridpur", "Feni", "Tangail"
        ));

        // Australia
        countryStateMap.put("Australia", FXCollections.observableArrayList(
                "New South Wales", "Victoria", "Queensland", "Western Australia", "South Australia", "Tasmania", "Northern Territory"
        ));

        // United Kingdom
        countryStateMap.put("United Kingdom", FXCollections.observableArrayList(
                "England", "Scotland", "Wales", "Northern Ireland"
        ));

        // Germany
        countryStateMap.put("Germany", FXCollections.observableArrayList(
                "Bavaria", "Berlin", "Hamburg", "North Rhine-Westphalia", "Hesse", "Lower Saxony", "Saxony", "Baden-Württemberg"
        ));

        // France
        countryStateMap.put("France", FXCollections.observableArrayList(
                "Île-de-France", "Provence-Alpes-Côte d'Azur", "Nouvelle-Aquitaine", "Occitanie", "Auvergne-Rhône-Alpes", "Brittany", "Grand Est"
        ));

        // China
        countryStateMap.put("China", FXCollections.observableArrayList(
                "Beijing", "Shanghai", "Guangdong", "Zhejiang", "Sichuan", "Hubei", "Fujian", "Anhui", "Shandong"
        ));

        // Japan
        countryStateMap.put("Japan", FXCollections.observableArrayList(
                "Tokyo", "Osaka", "Kyoto", "Hokkaido", "Fukuoka", "Okinawa", "Kanagawa", "Nagoya", "Hiroshima"
        ));

        // Brazil
        countryStateMap.put("Brazil", FXCollections.observableArrayList(
                "São Paulo", "Rio de Janeiro", "Bahia", "Minas Gerais", "Paraná", "Rio Grande do Sul", "Pernambuco", "Ceará"
        ));
    }



    public void onLogoClicked(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepage.fxml")));
        Stage stage = (Stage) logoPane.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Your Property");
        stage.setScene(scene);
        stage.show();
    }

    public void onMenuButtonClicked(ActionEvent actionEvent) {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(menuBar);
        transition.setToX(0);
        transition.play();
    }

    public void onCancelClicked(MouseEvent mouseEvent) {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(menuBar);
        transition.setToX(-185);
        transition.play();
    }

    public void onToggleBetweenDimensionAndAreaLandClicked() {
        if (toggleBetweenDimensionAndAreaLand.isSelected()) {
            toggleBetweenDimensionAndAreaLand.setText("Switch to Dimensions");
            landLength.setVisible(false);
            landWidth.setVisible(false);
            landArea.setVisible(true);
        } else {
            toggleBetweenDimensionAndAreaLand.setText("Switch to Area");
            landLength.setVisible(true);
            landWidth.setVisible(true);
            landArea.setVisible(false);
        }
    }

    public void onChoosePropertyPictureClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        // Assuming 'selectImageButton' is in a scene with a Stage
        Stage stage = (Stage) selectImageButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if(selectedFile != null){
            selectedProfilePicture = selectedFile;
            chosenFilePath.setText(selectedFile.getName());
        }
    }

    public void onAddPropertyImageClicked() {
        String pictureName = chosenFilePath.getText();

        if(!pictureName.isEmpty() && !isPictureInVBox(pictureName, propertyImagesAndAddPropertyButtonVbox)){
            propertyPictures.add(selectedProfilePicture.getAbsolutePath());
            HBox picturePane = createPicturePaneInVBox(pictureName, propertyImagesAndAddPropertyButtonVbox);

            // Add the uploaded picture before the Add Property button
            propertyImagesAndAddPropertyButtonVbox.getChildren().removeLast();
            propertyImagesAndAddPropertyButtonVbox.getChildren().add(picturePane);
            propertyImagesAndAddPropertyButtonVbox.getChildren().add(addPropertyButtonPane);

            chosenFilePath.setText("");
        } else if(!pictureName.isEmpty() && isPictureInVBox(pictureName, propertyImagesAndAddPropertyButtonVbox)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Property Picture");
            alert.setHeaderText(null);
            alert.setContentText("Property picture is already added.");
            alert.showAndWait();
        } else if(pictureName.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Property Picture");
            alert.setHeaderText(null);
            alert.setContentText("Select Property picture first.");
            alert.showAndWait();
        }
    }

    private boolean isPictureInVBox(String pictureName, VBox vBox) {
        return vBox.getChildren().stream()
                .filter(node -> node instanceof HBox)  // Check if node is an HBox
                .map(node -> (HBox) node)
                .anyMatch(hBox ->
                        !hBox.getChildren().isEmpty() &&  // Ensure HBox has children
                                hBox.getChildren().getFirst() instanceof Label &&  // First child is a Label
                                ((Label) hBox.getChildren().getFirst()).getText().equals(pictureName)
                );
    }

    // Method to create or show location pane with a delete button
    private HBox createPicturePaneInVBox(String propertyPicture, VBox parentPane) {
        // Label for location name
        Label pictureName = new Label(propertyPicture);
        pictureName.setStyle("-fx-font-size: 14;");
        pictureName.setStyle("-fx-text-fill:  #7c4683");

        // Cross button to remove the location
        ImageView deleteButton = createDeleteIcon();
        if (deleteButton != null) {
            deleteButton.setOnMouseClicked(e -> {
                parentPane.getChildren().remove(deleteButton.getParent());
                propertyPictures.removeIf(picturePath -> picturePath.contains(pictureName.textProperty().getValue()));
            });
        }

        // HBox to contain the label and delete button
        HBox locationPane = new HBox(pictureName, deleteButton);
        locationPane.setAlignment(Pos.CENTER_LEFT);
        locationPane.setSpacing(5);
        locationPane.setPadding(new Insets(5));
        locationPane.setStyle("-fx-background-color: #ecdbee; -fx-border-radius: 5; -fx-background-radius: 5;");

        VBox.setVgrow(locationPane, Priority.NEVER);
        locationPane.setMaxWidth(Region.USE_PREF_SIZE);

        return locationPane;
    }

    // Helper method to load and create the delete icon ImageView
    private ImageView createDeleteIcon() {
        try {
            // Load the image from your file path or resource
            Image deleteImage = new Image(Objects.requireNonNull(getClass().getResource("/images/cancel.png")).toExternalForm());
            ImageView deleteIcon = new ImageView(deleteImage);
            deleteIcon.setFitWidth(16); // Set width of icon
            deleteIcon.setFitHeight(16); // Set height of icon
            deleteIcon.setPreserveRatio(true);
            return deleteIcon;
        } catch (Exception e) {
            System.err.println("Delete icon image not found: " + e.getMessage());
            return null;
        }
    }

    public void onAddPropertyClicked() {
        addPropertyPreviewPane.setVisible(true);

        // Show the first image if the list is not empty
        if (!propertyPictures.isEmpty()) {
            updateShownPropertyPicture();
        }

        // Show key features for the properties
        if (propertyType.getSelectionModel().getSelectedItem().equals("Apartment")) {
            // Key features show
            if(PetPolicyYes.isSelected()) petPolicyShow.setText("Yes");
            else petPolicyShow.setText("No");

            if(furnishedPolicyYes.isSelected()) furniturePolicyShow.setText("Furnished");
            else furniturePolicyShow.setText("Unfurnished");

            if(terraceAvailabilityYes.isSelected()) terracePreferrenceShow.setText("Yes");
            else terracePreferrenceShow.setText("No");

            if(parkingAvailabilityYes.isSelected()) parkingAvailabilityShow.setText("Covered");
            else parkingAvailabilityShow.setText("Uncovered");

            // Specific features show
            apartmentBedroomNumberShow.setText(numberOfBedroomsInApartment.getText());
            apartmentBathroomNumberShow.setText(numberOfBathroomsInApartment.getText());
            apartmentFloorNumberShow.setText(floorNumberOfApartment.getText());
            apartmentTotalFloorNumberShow.setText(totalFloorsOfBuildingInApartment.getText());
            apartmentMaintenanceServicesShow.setText(maintenanceServicesApartment.getText());
            apartmentBalconyAvailabilityShow.setText(apartmentBalconyYes.isSelected() ? "Yes" : "No");
        } else if(propertyType.getSelectionModel().getSelectedItem().equals("House")) {
            // Key features show
            if(basementPreferenceYes.isSelected()) basementPreferrenceShow.setText("Finished");
            else basementPreferrenceShow.setText("Unfinished");

            List<String> securityFeatures = new ArrayList<>();
            if(SecuritySystem.isSelected()) securityFeatures.add("Security System");
            if(GatedEntry.isSelected()) securityFeatures.add("Gated Entry");
            if(Cameras.isSelected()) securityFeatures.add("Cameras");

            StringBuilder securityFeature = new StringBuilder();
            for(int i = 0; i < securityFeatures.size(); i++){
                securityFeature.append(securityFeatures.get(i));
                if(i < securityFeatures.size()-1) securityFeature.append(", ");
            }

            securityFeaturesShow.setText(securityFeature.toString());

            if(garagePreferenceYes.isSelected()) garageAvailabilityShow.setText("Yes");
            else garageAvailabilityShow.setText("No");

            // Specific features show
            houseBedroomNumberShow.setText(numberOfBedroomsInHouse.getText());
            houseBathroomNumberShow.setText(numberOfBathroomsInHouse.getText());
            houseFloorNumberShow.setText(numberOfFloorsInHouse.getText());
            houseRoofTypeShow.setText(houseRoofTypeFlat.isSelected() ? "Flat" : houseRoofTypeSloped.isSelected() ? "Sloped" : "Material");
            houseGardenShow.setText(houseGardenYes.isSelected() ? "Yes" : "No");
            houseBalconyShow.setText(houseBalconyYes.isSelected() ? "Yes" : "No");
        } else if(propertyType.getSelectionModel().getSelectedItem().equals("Land")) {
            // Key features show
            if(flatLand.isSelected()) landTopographyShow.setText("Flat");
            else if(slopedLand.isSelected()) landTopographyShow.setText("Sloped");
            else if(hillyLand.isSelected()) landTopographyShow.setText("Hilly");

            List<String> landUtilities = new ArrayList<>();
            if(electricityUtilityLand.isSelected()) landUtilities.add("Electricity");
            if(waterUtilityLand.isSelected()) landUtilities.add("Water");
            if(sewageUtilityLand.isSelected()) landUtilities.add("Sewage");

            StringBuilder landUtility = new StringBuilder();
            for(int i = 0; i < landUtilities.size(); i++){
                landUtility.append(landUtilities.get(i));
                if(i < landUtilities.size()-1) landUtility.append(", ");
            }

            landUtilitiesShow.setText(landUtility.toString());

            if(pavedRoadAccessLand.isSelected()) landRoadAccessShow.setText("Paved");
            else landRoadAccessShow.setText("Unpaved");

            // Specific features show
            if(landTypeAgricultural.isSelected()) landTypeShow.setText("Agricultural");
            else if(landTypeResidential.isSelected()) landTypeShow.setText("Residential");
            else if(landTypeCommercial.isSelected()) landTypeShow.setText("Commercial");
            else if(landTypeIndustrial.isSelected()) landTypeShow.setText("Industrial");

            if(toggleBetweenDimensionAndAreaLand.isSelected()) landPlotSizeShow.setText(landArea.getText());
            else landPlotSizeShow.setText(landLength.getText() + " x " + landWidth.getText());

            if(landBorderFenced.isSelected()) landBorderShow.setText("Fenced");
            else if(landBorderUnfenced.isSelected()) landBorderShow.setText("Unfenced");
            else if(landBorderBoundaryMarkers.isSelected()) landBorderShow.setText("Boundary Markers");

            if(landPreviousDevelopmentYes.isSelected()) landPreviousDevelopmentShow.setText("Yes");
            else landPreviousDevelopmentShow.setText("No");

            List<String> nearbyInfrastructures = new ArrayList<>();
            if(landNearbyInfrastructureRoads.isSelected()) nearbyInfrastructures.add("Roads");
            if(landNearbyInfrastructureSchools.isSelected()) nearbyInfrastructures.add("Schools");
            if(landNearbyInfrastructureHospitals.isSelected()) nearbyInfrastructures.add("Hospitals");

            StringBuilder nearbyInfrastructure = new StringBuilder();
            for(int i = 0; i < nearbyInfrastructures.size(); i++){
                nearbyInfrastructure.append(nearbyInfrastructures.get(i));
                if(i < nearbyInfrastructures.size()-1) nearbyInfrastructure.append(", ");
            }

            landNearbyInfrastructureShow.setText(nearbyInfrastructure.toString());
        } else if(propertyType.getSelectionModel().getSelectedItem().equals("Residential unit")) {
            // Key features show
            List<String> amenities = new ArrayList<>();
            if(poolAmenitiyResidentialUnit.isSelected()) amenities.add("Pool");
            if(playgroundAmenityResidentialUnit.isSelected()) amenities.add("Kid's Playground");
            if(gymAmenityResidentialUnit.isSelected()) amenities.add("Gym");

            StringBuilder amenity = new StringBuilder();
            for(int i = 0; i < amenities.size(); i++){
                amenity.append(amenities.get(i));
                if(i < amenities.size()-1) amenity.append(", ");
            }

            residentialUnitAmenitiesShow.setText(amenity.toString());

            if(closetStorageResidentialUnit.isSelected()) residentialUnitStorageShow.setText("Closet");
            else if(builtInStorageResidentialUnit.isSelected()) residentialUnitStorageShow.setText("Built-In");
            else if(basementStorageResidentialUnit.isSelected()) residentialUnitStorageShow.setText("Basement");

            if(noiseInsulatedResidentialUnit.isSelected()) residentialUnitNoiseInsulationShow.setText("Insulated");
            else if(noiseExposeResidentialUnit.isSelected()) residentialUnitNoiseInsulationShow.setText("Exposed");

            // Specific features show
            residentialUnitConfigurationShow.setText(bhkResidentialUnit.getValue() + " BHK");

            List<String> residentialUnitSharedFacilities = new ArrayList<>();
            if(residentialUnitLaundry.isSelected()) residentialUnitSharedFacilities.add("Laundry");
            if(residentialUnitGarbageDisposal.isSelected()) residentialUnitSharedFacilities.add("Garbage Disposal");
            if(residentialUnitInternet.isSelected()) residentialUnitSharedFacilities.add("Internet");

            StringBuilder sharedFacility = new StringBuilder();
            for(int i = 0; i < residentialUnitSharedFacilities.size(); i++){
                sharedFacility.append(residentialUnitSharedFacilities.get(i));
                if(i < residentialUnitSharedFacilities.size()-1) sharedFacility.append(", ");
            }

            residentialUnitSharedFacilitiesShow.setText(sharedFacility.toString());

            residentialUnitOrientationShow.setText(residentialUnitOrientation.getText());

            residentialUnitNaturalLightShow.setText(residentialUnitNaturalLightLow.isSelected() ? "Low" : residentialUnitNaturalLightMedium.isSelected() ? "Medium" : "High");
        } else if(propertyType.getSelectionModel().getSelectedItem().equals("Commercial unit")) {
            // Key features show
            List<String> commercialUnitPurposes = new ArrayList<>();
            if(retailPurposeCommercialUnit.isSelected()) commercialUnitPurposes.add("Retail");
            if(officePurposeCommercialUnit.isSelected()) commercialUnitPurposes.add("Office");
            if(restaurantPurposeCommercialUnit.isSelected()) commercialUnitPurposes.add("Restaurant");
            if(medicalPurposeCommercialUnit.isSelected()) commercialUnitPurposes.add("Medical");

            StringBuilder commercialUnitPurpose = new StringBuilder();
            for(int i = 0; i < commercialUnitPurposes.size(); i++){
                commercialUnitPurpose.append(commercialUnitPurposes.get(i));
                if(i < commercialUnitPurposes.size()-1) commercialUnitPurpose.append(", ");
            }

            commercialUnitPurposesShow.setText(commercialUnitPurpose.toString());

            if(openPlanFloorLayoutCommercialUnit.isSelected()) commercialUnitFloorLayoutShow.setText("Open-Plan");
            else if(modularFloorLayoutCommercialUnit.isSelected()) commercialUnitFloorLayoutShow.setText("Modular");
            else if(separateFloorLayoutCommercialUnit.isSelected()) commercialUnitFloorLayoutShow.setText("Separate");

            List<String> accessibiliyFeatures = new ArrayList<>();
            if(elevatorCommercialUnit.isSelected()) accessibiliyFeatures.add("Elevator");
            if(brailleSignageCommercialUnit.isSelected()) accessibiliyFeatures.add("Braille Signage");
            if(accessibleWashroomCommercialUnit.isSelected()) accessibiliyFeatures.add("Accessible Washrooms");

            StringBuilder accessibiliyFeature = new StringBuilder();
            for(int i = 0; i < accessibiliyFeatures.size(); i++){
                accessibiliyFeature.append(accessibiliyFeatures.get(i));
                if(i < accessibiliyFeatures.size()-1) accessibiliyFeature.append(", ");
            }

            commercialUnitAccessibilitiesShow.setText(accessibiliyFeature.toString());

            // Specific features show
            if(commercialUnitFacadeGlass.isSelected()) commercialUnitFacadeTypeShow.setText("Glass");
            else if(commercialUnitFacadeConcrete.isSelected()) commercialUnitFacadeTypeShow.setText("Concrete");
            else if(commercialUnitFacadeMetal.isSelected()) commercialUnitFacadeTypeShow.setText("Metal");

            List<String> nearbyAttractions = new ArrayList<>();
            if(commercialUnitNearbyAttractionMalls.isSelected()) nearbyAttractions.add("Malls");
            if(commercialUnitNearbyAttractionSchools.isSelected()) nearbyAttractions.add("Schools");
            if(commercialUnitNearbyAttractionParks.isSelected()) nearbyAttractions.add("Parks");
            if(commercialUnitNearbyAttractionRestaurants.isSelected()) nearbyAttractions.add("Restaurants");

            StringBuilder nearbyAttraction = new StringBuilder();
            for(int i = 0; i < nearbyAttractions.size(); i++){
                nearbyAttraction.append(nearbyAttractions.get(i));
                if(i < nearbyAttractions.size()-1) nearbyAttraction.append(", ");
            }

            commercialUnitNearbyAttractionsShow.setText(nearbyAttraction.toString());

            List<String> fireSafetyFeatures = new ArrayList<>();
            if(commercialUnitFireAlarm.isSelected()) fireSafetyFeatures.add("Fire Alarm");
            if(commercialUnitFireExtinguisher.isSelected()) fireSafetyFeatures.add("Fire Extinguisher");
            if(commercialUnitEmergencyExit.isSelected()) fireSafetyFeatures.add("Emergency Exit");

            StringBuilder fireSafetyFeature = new StringBuilder();
            for(int i = 0; i < fireSafetyFeatures.size(); i++){
                fireSafetyFeature.append(fireSafetyFeatures.get(i));
                if(i < fireSafetyFeatures.size()-1) fireSafetyFeature.append(", ");
            }

            commercialUnitFireSafetyShow.setText(fireSafetyFeature.toString());

            if(commercialUnitAirConditioningCentral.isSelected()) commercialUnitAirConditioningShow.setText("Central");
            else if(commercialUnitAirConditioningIndividual.isSelected()) commercialUnitAirConditioningShow.setText("Individual");
            else if(commercialUnitAirConditioningNone.isSelected()) commercialUnitAirConditioningShow.setText("None");
        } else if(propertyType.getSelectionModel().getSelectedItem().equals("Business place")) {
            // Key features show
            List<String> businessPlacePurposes = new ArrayList<>();
            if(storePurposeBusinessPlace.isSelected()) businessPlacePurposes.add("Store");
            if(garagePurposeBusinessPlace.isSelected()) businessPlacePurposes.add("Garage");

            StringBuilder businessPlacePurpose = new StringBuilder();
            for(int i = 0; i < businessPlacePurposes.size(); i++){
                businessPlacePurpose.append(businessPlacePurposes.get(i));
                if(i < businessPlacePurposes.size()-1) businessPlacePurpose.append(", ");
            }

            businessPlacePurposeShow.setText(businessPlacePurpose.toString());

            if(standardVentilationBusinessPlace.isSelected()) businessPlaceVentilationFaciltiyShow.setText("Standard");
            else if(highPoweredFansVentilationBusinessPlace.isSelected()) businessPlaceVentilationFaciltiyShow.setText("High-Powered Fans");
            else if(airFiltrationVentilationBusinessPlace.isSelected()) businessPlaceVentilationFaciltiyShow.setText("Air Filtration System");

            List<String> powerSupplyFeatures = new ArrayList<>();
            if(standardPowerSupplyBusinessPlace.isSelected()) powerSupplyFeatures.add("Standard");
            if(highVoltagePowerBusinessPlace.isSelected()) powerSupplyFeatures.add("High Voltage");
            if(generatorPowerSupplyBusinessPlace.isSelected()) powerSupplyFeatures.add("Backup Generator");

            StringBuilder powerSupplyFeature = new StringBuilder();
            for(int i = 0; i < powerSupplyFeatures.size(); i++){
                powerSupplyFeature.append(powerSupplyFeatures.get(i));
                if(i < powerSupplyFeatures.size()-1) powerSupplyFeature.append(", ");
            }

            businessPlacePowerSupplyShow.setText(powerSupplyFeature.toString());

            // Specific features show
            if(businessPlaceAmbientLighting.isSelected()) businessPlaceLightingSetupShow.setText("Ambient Lighting");
            else if(businessPlaceTaskLighting.isSelected()) businessPlaceLightingSetupShow.setText("Task Lighting");
            else if(businessPlaceAccentLighting.isSelected()) businessPlaceLightingSetupShow.setText("Accent Lighting");

            if(businessPlaceDedicatedWaitingArea.isSelected()) businessPlaceWaitingAreaShow.setText("Dedicated Waiting Area");
            else if(businessPlaceSeatingSpaceWaitingArea.isSelected()) businessPlaceWaitingAreaShow.setText("Seating Space");
            else if(businessPlaceNoWaitingArea.isSelected()) businessPlaceWaitingAreaShow.setText("No Waiting Area");

            if(businessPlaceHighVisibility.isSelected()) businessPlaceVisibilityFromRoadShow.setText("High");
            else if(businessPlaceMediumVisibility.isSelected()) businessPlaceVisibilityFromRoadShow.setText("Medium");
            else if(businessPlaceLowVisibility.isSelected()) businessPlaceVisibilityFromRoadShow.setText("Low");

            businessPlaceInsuranceDetailsShow.setText(businessPlaceInsuranceDetails.getText());
        }

        propertyTitleShow.setText(propertyTitle.getText());
        if(offerTypeChoiceBox.getSelectionModel().getSelectedItem().equals("For sale")) {
            showPropertyPrice.setText(propertySalePrice.getText());
            propertyAvailableDatePane.setVisible(false);
        } else if(offerTypeChoiceBox.getSelectionModel().getSelectedItem().equals("For rent")) {
            String rentalTerm = propertyRentTermBox.getSelectionModel().getSelectedItem();
            showPropertyPrice.setText(propertyRentPrice.getText() + " (" + rentalTerm + ")");
            propertyAvailableDatePane.setVisible(true);
            LocalDate propertyAvailabilityDate = propertyAvailableForRentFromDate.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String availableDate = propertyAvailabilityDate.format(formatter);
            propertyAvailableForRentFromDateShow.setText(availableDate);
        }

        String country = propertyCountryBox.getSelectionModel().getSelectedItem();
        String state = propertyStateBox.getSelectionModel().getSelectedItem();
        String location = String.join(", ", propertyStreetAddress.getText(), propertyCity.getText(), state, propertyPostalCode.getText(), country);
        showPropertyLocation.setText(location);

        showPropertyDescription.setText(propertyDescription.getText());

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(0.5));
        translateTransition.setNode(addPropertyPreviewPane);
        translateTransition.setToY(0);
        translateTransition.play();
    }

    public void onShowNextImageClicked(ActionEvent actionEvent) {
        if (!propertyPictures.isEmpty() && currentPictureIndex < propertyPictures.size() - 1) {
            currentPictureIndex++;
            updateShownPropertyPicture();
        }
    }

    public void onShowPreviousImageClicked(ActionEvent actionEvent) {
        if (!propertyPictures.isEmpty() && currentPictureIndex > 0) {
            currentPictureIndex--;
            updateShownPropertyPicture();
        }
    }

    private void updateShownPropertyPicture() {
        String currentImagePath = propertyPictures.get(currentPictureIndex);
        File file = new File(currentImagePath);

        if (file.exists()) {
            propertyPictureShow.setImage(new Image(file.toURI().toString()));
        } else {
            System.err.println("Image file not found: " + currentImagePath);
        }

        // Update visibility of buttons
        previousImageButton.setVisible(currentPictureIndex > 0);  // Hide previous button for the first image
        nextImageButton.setVisible(currentPictureIndex < propertyPictures.size() - 1);  // Hide next button for the last image
    }

    public void onEditAddPropertyClicked(ActionEvent actionEvent) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(0.5));
        translateTransition.setNode(addPropertyPreviewPane);
        translateTransition.setToY(635);
        translateTransition.play();
    }

    public void onSubmitAddPropertyClicked(ActionEvent actionEvent) throws IOException {
//        ObjectNode propertyData = objectMapper.createObjectNode();

        Map<String, Object> propertyData = new HashMap<>();

        String userEmail = YourPropertyUserSession.getInstance().getUserEmail();
        String offerType = offerTypeChoiceBox.getSelectionModel().getSelectedItem();
        String type = propertyType.getSelectionModel().getSelectedItem();
        String title = propertyTitle.getText();
        String description = propertyDescription.getText();

        propertyData.put("userEmail", userEmail);
        propertyData.put("offerType", offerType);
        propertyData.put("propertyType", type);
        propertyData.put("propertyTitle", title);
        propertyData.put("propertyDescription", description);

        if(offerType.equals("For sale")) {
            BigDecimal price = new BigDecimal(propertySalePrice.getText());
            propertyData.put("salePrice", price);
        }
        else if(offerType.equals("For rent")) {
            BigDecimal price = new BigDecimal(propertyRentPrice.getText());
            String term = propertyRentTermBox.getSelectionModel().getSelectedItem();

            LocalDate availableDate = propertyAvailableForRentFromDate.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String rentAvailableDate = availableDate.format(formatter);

            propertyData.put("rentPrice", price);
            propertyData.put("rentTerm", term);
            propertyData.put("rentAvailableDate", rentAvailableDate);
        }

        String country = propertyCountryBox.getSelectionModel().getSelectedItem();
        String state = propertyStateBox.getSelectionModel().getSelectedItem();
        String city = propertyCity.getText();
        String postalCode = propertyPostalCode.getText();
        String street = propertyStreetAddress.getText();

        propertyData.put("country", country);
        propertyData.put("city", city);
        propertyData.put("state", state);
        propertyData.put("postalCode", postalCode);
        propertyData.put("street", street);
        
        propertyData.put("picture", propertyPictures);

        // Collect and add property-specific features
        Map<String, Object> propertyFeatures = collectPropertyFeatures(type);
        if (propertyFeatures != null) {
            propertyData.put(type.toLowerCase() + "Features", propertyFeatures);
        }

        String jsonInputString = objectMapper.writeValueAsString(propertyData);

        String targetUrl = "http://localhost:8080/api/properties/add";

        try{
            Pair<Integer, String> pair = HTTPClient.sendPostRequest(targetUrl, jsonInputString);
            if(pair.getKey() == 200){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Add Property");
                alert.setHeaderText(null);
                alert.setContentText("Property added successfully.");
                alert.showAndWait();

                // Redirect to the dashboard and show the Manage Property pane
                FXMLLoader loader = new FXMLLoader(getClass().getResource("profileDashboardPage.fxml"));
                Parent root = loader.load();

                // Get the controller for the dashboard
                dashboardController dashboardController = loader.getController();

                // Call the method to show the create seller profile pane
                dashboardController.onMyPropertiesOptionClicked();

                // Switch to the dashboard scene
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Profile Dashboard");
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Property");
                alert.setHeaderText("Something went wrong!");
                alert.setContentText("An error occured!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("An error occurred!");
            e.printStackTrace();
        }
    }

    private Map<String, Object> collectPropertyFeatures(String type) {
        Map<String, Object> features = new HashMap<>();

        switch (type) {
            case "Apartment" -> {
                Boolean petPolicy = PetPolicyYes.isSelected() ? true : PetPolicyNo.isSelected() ? false : null;
                Boolean furnishedPolicy = furnishedPolicyYes.isSelected() ? true : furnishedPolicyNo.isSelected() ? false : null;
                Boolean terracePolicy = terraceAvailabilityYes.isSelected() ? true : terraceAvailabilityNo.isSelected() ? false : null;
                Boolean parkingPolicy = parkingAvailabilityYes.isSelected() ? true : parkingAvailabilityNo.isSelected() ? false : null;

                if (petPolicy != null) features.put("petPolicy", petPolicy);
                if (furnishedPolicy != null) features.put("furniturePolicy", furnishedPolicy);
                if (terracePolicy != null) features.put("terracePolicy", terracePolicy);
                if (parkingPolicy != null) features.put("parkingPolicy", parkingPolicy);

                features.put("bedrooms", Integer.parseInt(numberOfBedroomsInApartment.getText()));
                features.put("bathrooms", Integer.parseInt(numberOfBathroomsInApartment.getText()));
                features.put("floor", Integer.parseInt(floorNumberOfApartment.getText()));
                features.put("totalFloor", Integer.parseInt(totalFloorsOfBuildingInApartment.getText()));
                features.put("maintenanceServices", maintenanceServicesApartment.getText());

                Boolean balcony = apartmentBalconyYes.isSelected() ? true : apartmentBalconyNo.isSelected() ? false : null;
                features.put("balconyPolicy", balcony);
            }
            case "House" -> {
                String basement = basementPreferenceYes.isSelected() ? "Finished" : "Unfinished";

                List<String> securityFeatures = new ArrayList<>();
                if (SecuritySystem.isSelected()) securityFeatures.add("Security System");
                if (GatedEntry.isSelected()) securityFeatures.add("Gated Entry");
                if (Cameras.isSelected()) securityFeatures.add("Cameras");

                Boolean garage = garagePreferenceYes.isSelected() ? true : garagePreferrenceNo.isSelected() ? false : null;

                features.put("basementAvailability", basement);
                features.put("securityFeatures", securityFeatures);
                features.put("garageAvailability", garage);

                features.put("bedrooms", Integer.parseInt(numberOfBedroomsInHouse.getText()));
                features.put("bathrooms", Integer.parseInt(numberOfBathroomsInHouse.getText()));
                features.put("floor", Integer.parseInt(numberOfFloorsInHouse.getText()));

                String roofType = houseRoofTypeFlat.isSelected() ? "Flat" : houseRoofTypeSloped.isSelected() ? "Sloped" : houseRoofTypeMaterial.isSelected() ? "Material" : null;
                features.put("roofType", roofType);

                Boolean garden = houseGardenYes.isSelected() ? true : houseGardenNo.isSelected() ? false : null;
                Boolean balcony = houseBalconyYes.isSelected() ? true : houseBalconyNo.isSelected() ? false : null;

                features.put("gardenAvailability", garden);
                features.put("balconyAvailability", balcony);
            }
            case "Land" -> {
                String topography = flatLand.isSelected() ? "Flat" : slopedLand.isSelected() ? "Sloped" : hillyLand.isSelected() ? "Hilly" : null;

                List<String> utilities = new ArrayList<>();
                if (electricityUtilityLand.isSelected()) utilities.add("Electricity");
                if (waterUtilityLand.isSelected()) utilities.add("Water");
                if (sewageUtilityLand.isSelected()) utilities.add("Sewage");

                String roadAccess = pavedRoadAccessLand.isSelected() ? "Paved" : unpavedRoadAccessLand.isSelected() ? "Unpaved" : null;

                features.put("topography", topography);
                features.put("utilities", utilities);
                features.put("roadAccess", roadAccess);

                String landType = landTypeAgricultural.isSelected() ? "Agricultural" : landTypeCommercial.isSelected() ? "Commercial" : landTypeIndustrial.isSelected() ? "Industrial" : landTypeResidential.isSelected() ? "Residential" : null;
                features.put("type", landType);

                if (toggleBetweenDimensionAndAreaLand.isSelected()) {
                    features.put("area", landArea.getText());
                } else {
                    features.put("length", landLength.getText());
                    features.put("width", landWidth.getText());
                }

                String border = landBorderFenced.isSelected() ? "Fenced" : landBorderUnfenced.isSelected() ? "Unfenced" : landBorderBoundaryMarkers.isSelected() ? "Boundary Markers" : null;
                Boolean previousDevelopment = landPreviousDevelopmentYes.isSelected() ? true : landPreviousDevelopmentNo.isSelected() ? false : null;

                List<String> nearbyInfrastructure = new ArrayList<>();
                if (landNearbyInfrastructureHospitals.isSelected()) nearbyInfrastructure.add("Hospitals");
                if (landNearbyInfrastructureRoads.isSelected()) nearbyInfrastructure.add("Roads");
                if (landNearbyInfrastructureSchools.isSelected()) nearbyInfrastructure.add("Schools");

                features.put("border", border);
                features.put("previousDevelopment", previousDevelopment);
                features.put("nearbyInfrastructure", nearbyInfrastructure);
            }
            case "Residential Unit" -> {
                List<String> amenities = new ArrayList<>();
                if (poolAmenitiyResidentialUnit.isSelected()) amenities.add("Pool");
                if (gymAmenityResidentialUnit.isSelected()) amenities.add("Gym");
                if (playgroundAmenityResidentialUnit.isSelected()) amenities.add("Kid's Playground");
                features.put("amenities", amenities);

                String storage = closetStorageResidentialUnit.isSelected() ? "Closet" : builtInStorageResidentialUnit.isSelected() ? "Built-In" : basementStorageResidentialUnit.isSelected() ? "Basement" : null;
                features.put("storage", storage);

                Boolean noiseInsulation = noiseInsulatedResidentialUnit.isSelected() ? true : noiseExposeResidentialUnit.isSelected() ? false : null;
                features.put("noiseInsulation", noiseInsulation);

                features.put("configuration", bhkResidentialUnit.getValue());

                List<String> sharedFacilities = new ArrayList<>();
                if (residentialUnitLaundry.isSelected()) sharedFacilities.add("Laundry");
                if (residentialUnitGarbageDisposal.isSelected()) sharedFacilities.add("Garbage Disposal");
                if (residentialUnitInternet.isSelected()) sharedFacilities.add("Internet");
                features.put("sharedFacilities", sharedFacilities);

                features.put("orientation", residentialUnitOrientation.getText());

                String naturalLight = residentialUnitNaturalLightLow.isSelected() ? "Low" : residentialUnitNaturalLightHigh.isSelected() ? "High" : residentialUnitNaturalLightMedium.isSelected() ? "Medium" : null;
                features.put("naturalLight", naturalLight);
            }
            case "Commercial Unit" -> {
                List<String> purposes = new ArrayList<>();
                if (retailPurposeCommercialUnit.isSelected()) purposes.add("Retail");
                if (officePurposeCommercialUnit.isSelected()) purposes.add("Office");
                if (restaurantPurposeCommercialUnit.isSelected()) purposes.add("Restaurant");
                if (medicalPurposeCommercialUnit.isSelected()) purposes.add("Medical");
                features.put("purpose", purposes);

                String floorLayout = openPlanFloorLayoutCommercialUnit.isSelected() ? "Open-plan" : modularFloorLayoutCommercialUnit.isSelected() ? "Modular" : separateFloorLayoutCommercialUnit.isSelected() ? "Separate" : null;
                features.put("floorLayout", floorLayout);

                List<String> accessibilityFeatures = new ArrayList<>();
                if (elevatorCommercialUnit.isSelected()) accessibilityFeatures.add("Elevator");
                if (brailleSignageCommercialUnit.isSelected()) accessibilityFeatures.add("Braille Signage");
                if (accessibleWashroomCommercialUnit.isSelected()) accessibilityFeatures.add("Accessible Washroom");
                features.put("accessibilityFeatures", accessibilityFeatures);

                String facade = commercialUnitFacadeGlass.isSelected() ? "Glass" : commercialUnitFacadeConcrete.isSelected() ? "Concrete" : commercialUnitFacadeMetal.isSelected() ? "Metal" : null;
                features.put("facade", facade);

                List<String> nearbyAttractions = new ArrayList<>();
                if (commercialUnitNearbyAttractionSchools.isSelected()) nearbyAttractions.add("Schools");
                if (commercialUnitNearbyAttractionMalls.isSelected()) nearbyAttractions.add("Malls");
                if (commercialUnitNearbyAttractionParks.isSelected()) nearbyAttractions.add("Parks");
                if (commercialUnitNearbyAttractionRestaurants.isSelected()) nearbyAttractions.add("Restaurants");
                features.put("nearbyAttractions", nearbyAttractions);

                List<String> fireSafety = new ArrayList<>();
                if (commercialUnitFireAlarm.isSelected()) fireSafety.add("Fire alarms");
                if (commercialUnitFireExtinguisher.isSelected()) fireSafety.add("Extinguishers");
                if (commercialUnitEmergencyExit.isSelected()) fireSafety.add("Emergency Exit");
                features.put("fireSafety", fireSafety);

                String airConditioning = commercialUnitAirConditioningCentral.isSelected() ? "Central" : commercialUnitAirConditioningIndividual.isSelected() ? "Individual" : commercialUnitAirConditioningNone.isSelected() ? "None" : null;
                features.put("airConditioning", airConditioning);
            }
            case "Business Place" -> {
                List<String> purpose = new ArrayList<>();
                if(storePurposeBusinessPlace.isSelected()) purpose.add("Store");
                if(garagePurposeBusinessPlace.isSelected()) purpose.add("Garage");
                features.put("purpose", purpose);

                String ventilation = null;
                if(standardVentilationBusinessPlace.isSelected()) ventilation = "Standard";
                else if(highPoweredFansVentilationBusinessPlace.isSelected()) ventilation = "High-Powered Fans";
                else if(airFiltrationVentilationBusinessPlace.isSelected()) ventilation = "Air Filtration System";
                features.put("ventilation", ventilation);

                List<String> powerSupply = new ArrayList<>();
                if(standardPowerSupplyBusinessPlace.isSelected()) powerSupply.add("Standard");
                else if(highVoltagePowerBusinessPlace.isSelected()) powerSupply.add("High Voltage");
                else if(generatorPowerSupplyBusinessPlace.isSelected()) powerSupply.add("Backup Generator");
                features.put("powerSupply", powerSupply);

                String lightingSetup = null;
                if(businessPlaceAmbientLighting.isSelected()) lightingSetup = "Ambient";
                else if(businessPlaceTaskLighting.isSelected()) lightingSetup = "Task";
                else if(businessPlaceAccentLighting.isSelected()) lightingSetup = "Accent";
                features.put("lightingSetup", lightingSetup);

                String waitingArea = null;
                if(businessPlaceDedicatedWaitingArea.isSelected()) waitingArea = "Dedicated lounge";
                else if(businessPlaceSeatingSpaceWaitingArea.isSelected()) waitingArea = "Seating space";
                else if(businessPlaceNoWaitingArea.isSelected()) waitingArea = "None";
                features.put("waitingArea", waitingArea);

                String visibilityFromRoad = null;
                if(businessPlaceHighVisibility.isSelected()) visibilityFromRoad = "High";
                else if(businessPlaceMediumVisibility.isSelected()) visibilityFromRoad = "Medium";
                else if(businessPlaceLowVisibility.isSelected()) visibilityFromRoad = "Low";
                features.put("visibilityFromRoad", visibilityFromRoad);

                String insuranceDetails = businessPlaceInsuranceDetails.getText();
                features.put("insuranceDetails", insuranceDetails);
            }
        }
        return features;
    }
}
