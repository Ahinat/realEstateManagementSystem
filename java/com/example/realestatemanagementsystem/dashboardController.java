package com.example.realestatemanagementsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

//import java.awt.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class dashboardController implements Initializable {

    // different panes
    public AnchorPane dashboardLayer;
    public AnchorPane myPropertyLayer;
    public AnchorPane savedPropertyLayer;
    public ScrollPane profileSettingsLayer;
    public AnchorPane manageProfile;
    public AnchorPane manageBuyerProfile;
    public AnchorPane manageRenterProfile;
    public AnchorPane manageSellerProfile;
    public ScrollPane editPropertyLayer;

    // dashboard details
    public Label numberOfPropertiesDashboard;
    public Label numberOfTenantsDashboard;
    public Label estimatedRevenueDashboard;

    // profile details
    public ImageView profilePictureInProfileDetails;
    public Label userNameInProfileDetails;
    public Label userEmailInProfileDetails;
    public Label firstName;
    public Label lastName;
    public Label birthday;
    public Label gender;
    public Label password;
    public Label mobileNumber;
    public Label securityQuestion;
    public Label securityAnswer;
    public Label bio;
    public String facebook, google, twitter;

    // update details options
    public RadioButton newMaleSelected;
    public RadioButton newFemaleSelected;
    public RadioButton newOtherSelected;
    public TextField newFirstName;
    public TextField newLastName;
    public TextField newEmail;
    public DatePicker newBirthday;
    public TextField newPassword;
    public TextField reEnteredNewPassword;
    public ComboBox<String> newCountryCode;
    public TextField newMobileNumber;
    public ComboBox<String> newSecurityQuestionBox;
    public TextField newSecurityAnswer;
    public TextArea newBio;
    public TextField newFacebookLink;
    public TextField newGoogleLink;
    public TextField newTwitterLink;
    public File selectedProfilePicture;
    public Button selectImageButton;
    public Label chosenFilePath;

    // Dashboard display
    public ImageView profilePicture;
    public Label userName;
    public Label userEmail;

    // Create buyer profile
    public AnchorPane createBuyerProfile, createBuyerProfileFields;
    public TextField buyerMinimumBudget;
    public TextField buyerMaximumBudget;
    public CheckBox buyerProfileApartmentChecked, buyerProfileHousesChecked, buyerProfileLandsChecked, buyerProfileResidentialUnitsChecked, buyerProfileCommercialUnitsChecked, buyerProfileBusinessPlacesChecked;
    public FlowPane buyerPreferredLocationFlowPane;
    public TextField buyerPreferredLocationField;

    // Update buyer profile
    public AnchorPane updateBuyerProfile;
    public TextField minimumBudgetBuyerUpdate, maximumBudgetBuyerUpdate;
    public CheckBox buyerProfileApartmentUpdateChecked, buyerProfileHouseUpdateChecked, buyerProfileLandUpdateChecked, buyerProfileResidentialUnitUpdateChecked, buyerProfileCommercialUnitUpdateChecked, buyerProfileBusinessPlaceUpdateChecked;
    public TextField buyerNewPreferredLocationField;
    public VBox buyerPreferredLocationVBox;
    public Button updateBuyerProfileButton;

    // Create renter profile
    public AnchorPane createRenterProfile;
    public ScrollPane createRenterProfileFields;
    public TextField renterMinimumBudget, renterMaximumBudget;
    public CheckBox renterProfileApartmentChecked, renterProfileHousesChecked, renterProfileLandsChecked, renterProfileResidentialUnitsChecked, renterProfileCommercialUnitsChecked, renterProfileBusinessPlacesChecked;
    public Pane preferredLocationAndCreateButtonPane;
    public TextField renterPreferredLocationField;
    public FlowPane renterPreferredLocationFlowPane;
    public DatePicker renterPreferredMoveInDate;
    public ComboBox<String> preferredRentalTermComboBox;
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
    public VBox showPreferredFeaturesVbox;
    public Pane apartmentFeatures, houseFeatures, landFeatures, residentialUnitFeatures, commercialUnitFeatures, businessPlacesFeatures;
    public CheckBox poolAmenitiyResidentialUnit, gymAmenityResidentialUnit, playgroundAmenityResidentialUnit;
    public RadioButton closetStorageResidentialUnit, basementStorageResidentialUnit, builtInStorageResidentialUnit;
    public RadioButton noiseInsulatedResidentialUnit, noiseExposeResidentialUnit;
    public CheckBox retailPurposeCommercialUnit, officePurposeCommercialUnit, medicalPurposeCommercialUnit, restaurantPurposeCommercialUnit;
    public RadioButton openPlanFloorLayoutCommercialUnit, modularFloorLayoutCommercialUnit, separateFloorLayoutCommercialUnit;
    public CheckBox elevatorCommercialUnit, brailleSignageCommercialUnit, accessibleWashroomCommercialUnit;
    public CheckBox storePurposeBusinessPlace, garagePurposeBusinessPlace;
    public RadioButton standardVentilationBusinessPlace, highPoweredFansVentilationBusinessPlace, airFiltrationVentilationBusinessPlace;
    public CheckBox standardPowerSupplyBusinessPlace, highVoltagePowerBusinessPlace, generatorPowerSupplyBusinessPlace;

    // Update renter profile
    public ScrollPane updateRenterProfile;
    public Label renterBudgetShow, renterMoveInDateShow, renterRentalTermShow, renterPropertyTypesShow, renterPreferredLocationsShow;
    public Pane renterApartmentFeaturesShow;
    public Label renterPetPolicyShow;
    public Label renterFurniturePolicyShow;
    public Label renterTerracePreferrenceShow;
    public Label renterParkingAvailabilityShow;
    public Pane renterHouseFeaturesShow;
    public Label renterBasementPreferrenceShow;
    public Label renterSecurityFeaturesShow;
    public Label renterGarageAvailabilityShow;
    public Pane renterLandFeaturesShow;
    public Label renterLandTopographyShow;
    public Label renterLandUtilitiesShow;
    public Label renterLandRoadAccessShow;
    public Pane renterResidentialUnitFeaturesShow;
    public Label renterResidentialUnitAmenitiesShow;
    public Label renterResidentialUnitStorageShow;
    public Label renterResidentialUnitNoiseInsulationShow;
    public Pane renterCommercialUnitFeaturesShow;
    public Label renterCommercialUnitPurposesShow;
    public Label renterCommercialUnitFloorLayoutShow;
    public Label renterCommercialUnitAccessibilitiesShow;
    public Pane renterBusinessPlaceFeaturesShow;
    public Label renterBusinessPlacePurposeShow;
    public Label renterBusinessPlaceVentilationFaciltiyShow;
    public Label renterBusinessPlacePowerSupplyShow;
    public Button updateRenterProfileButton;
    public VBox renterPrefferedPropertyFeaturesShow;

    // Create seller profile
    public AnchorPane createSellerProfile, createSellerProfileFields;
    public TextField sellerLicenseNumber;
    public CheckBox sellerProfileApartmentChecked, sellerProfileHousesChecked, sellerProfileLandsChecked, sellerProfileResidentialUnitsChecked, sellerProfileCommercialUnitsChecked, sellerProfileBusinessPlacesChecked;
    public CheckBox sellerOpenCommunicationWithBuyer, sellerOpenCommunicationWithRenter, sellerOpenCommunicationWithAgent;
    

    // Update Seller Profile
    public AnchorPane updateSellerProfile;
    public Label sellerLicenseNumberShow, sellerPropertyTypesShow, sellerCommunicationInterestShow;

    // property list on dashboard in my properties
    public VBox propertyListContainer;

    // property list on dashboard in saved properties
    public VBox savedPropertyListContainer;

    // property preview
    public AnchorPane propertyDetailsLayer;
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
    public Button editPropertyFromPropertyDetailsButton;

    // edit property fields
    public TextField newPropertyTitle;
    public TextArea newPropertyDescription;
    public Pane newPropertyRentTermAndPriceAndDatePane;
    public TextField newPropertyRentPrice;
    public DatePicker newPropertyAvailableForRentFromDate;
    public ChoiceBox<String> newPropertyRentTermBox;
    public Pane newPropertySellPricePane;
    public TextField newPropertySalePrice;
    public Pane newApartmentFeatures, newHouseFeatures, newLandFeatures, newResidentialUnitFeatures, newCommercialUnitFeatures, newBusinessPlacesFeatures;
    public RadioButton newPetPolicyYes, newPetPolicyNo;
    public RadioButton newfurnishedPolicyYes, newfurnishedPolicyNo;
    public RadioButton newterraceAvailabilityYes, newterraceAvailabilityNo;
    public RadioButton newparkingAvailabilityYes, newparkingAvailabilityNo;
    public RadioButton newbasementPreferenceYes, newbasementPreferrenceNo;
    public CheckBox newSecuritySystem, newGatedEntry, newCameras;
    public RadioButton newGaragePreferenceYes, newGaragePreferrenceNo;
    public RadioButton newFlatLand, newHillyLand, newSlopedLand;
    public CheckBox newElectricityUtilityLand, newWaterUtilityLand, newSewageUtilityLand;
    public RadioButton newPavedRoadAccessLand, newUnpavedRoadAccessLand;
    public CheckBox newPoolAmenitiyResidentialUnit, newGymAmenityResidentialUnit, newPlaygroundAmenityResidentialUnit;
    public RadioButton newClosetStorageResidentialUnit, newBasementStorageResidentialUnit, newBuiltInStorageResidentialUnit;
    public RadioButton newNoiseInsulatedResidentialUnit, newNoiseExposeResidentialUnit;
    public CheckBox newRetailPurposeCommercialUnit, newOfficePurposeCommercialUnit, newMedicalPurposeCommercialUnit, newRestaurantPurposeCommercialUnit;
    public RadioButton newOpenPlanFloorLayoutCommercialUnit, newModularFloorLayoutCommercialUnit, newSeparateFloorLayoutCommercialUnit;
    public CheckBox newElevatorCommercialUnit, newBrailleSignageCommercialUnit, newAccessibleWashroomCommercialUnit;
    public CheckBox newStorePurposeBusinessPlace, newGaragePurposeBusinessPlace;
    public RadioButton newStandardVentilationBusinessPlace, newHighPoweredFansVentilationBusinessPlace, newAirFiltrationVentilationBusinessPlace;
    public CheckBox newStandardPowerSupplyBusinessPlace, newHighVoltagePowerBusinessPlace, newGeneratorPowerSupplyBusinessPlace;
    public Pane newSpecificFeaturesApartment, newSpecificFeaturesHouse, newSpecificFeaturesLand, newSpecificFeaturesResidentialUnit, newSpecificFeaturesCommercialUnit, newSpecificFeaturesBusinessPlace;
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
    public Pane propertyLocationPane;
    public ChoiceBox<String> propertyNewCountryBox;
    public ChoiceBox<String> propertyNewStateBox;
    public TextField propertyNewCity;
    public TextField propertyNewPostalCode;
    public TextField propertyNewStreetAddress;
    public VBox editPropertyImagesAndSubmitButtonVBox;
    public FlowPane propertyImagesEditFlowPane;
    public Pane addNewPropertyImageButtonPane;
    public Button addNewPropertyImageButton;
    public Pane submitEditPropertyPane;
    public Label propertyIdInEditProperty;

    // inbox messages
    public VBox inboxInDashboardVBox;
    public Label noMessageFoundLabel;
    public FlowPane rentedPropertiesInDashboardFlowPane;
    public Label noRentedPropertiesFoundLabel;
    public FlowPane rentalPropertiesInHomepageFlowPane;
    public Label noRentalPropertiesFoundLabel;


    private List<String> buyerPreferredLocations = new ArrayList<>();
    private List<String> buyerPreferredNewLocations = new ArrayList<>();
    private List<String> renterPreferredLocations = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    int CurrentIndex;
    List<String> imagePaths = new ArrayList<>();
    private final Map<String, ObservableList<String>> countryStateMap = new HashMap<>();

    public void onLogoClicked(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepage.fxml")));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Your Property");
        stage.setScene(scene);
        stage.show();
    }

    public void onLogOutClicked(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        ButtonType userConfirmation = alert.showAndWait().get();

        if(ButtonType.OK.equals(userConfirmation)) {
            YourPropertyUserSession.getInstance().logoutUser();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepage.fxml")));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Your Property");
            stage.setScene(scene);
            stage.show();
        } else {
            alert.close();
        }
    }

    public void onProfileSettingsClicked() {
        dashboardLayer.setVisible(false);
        myPropertyLayer.setVisible(false);
        savedPropertyLayer.setVisible(false);
        manageProfile.setVisible(false);
        profileSettingsLayer.setVisible(true);
        propertyDetailsLayer.setVisible(false);
        editPropertyLayer.setVisible(false);

        refreshUserProfileDetails();
        addItemsInSecurityQuestionBox();
        addItemsInCountryCodeMobileNumber();
    }

    public void refreshUserProfileDetails(){
        // Fetch user email
        String user_Email = YourPropertyUserSession.getInstance().getUserEmail();

        // Get user info from database
        String targetUrl = "http://localhost:8080/api/yourpropertyuser";
        try {
            JSONObject jsonUser = HTTPClient.sendGetRequest(targetUrl, user_Email);

            String firstName1 = jsonUser.optString("FirstName", "");
            String lastName1 = jsonUser.optString("LastName", "");
            String birthday1 = jsonUser.optString("Birthday", "");
            String gender1 = jsonUser.optString("Gender", "");
            String password1 = jsonUser.optString("Password", "");
            String mobileNumber1 = jsonUser.optString("MobileNumber", "");
            String securityQuestion1 = jsonUser.optString("SecurityQuestion", "");
            String securityAnswer1 = jsonUser.optString("SecurityAnswer", "");
            String bio1 = jsonUser.optString("Bio","");
            String profilePicture1 = jsonUser.optString("ProfilePicture", "");

            facebook = jsonUser.optString("Facebook", "");
            google = jsonUser.optString("Google", "");
            twitter = jsonUser.optString("Twitter", "");

            userNameInProfileDetails.setText(firstName1 + " " + lastName1);
            userEmailInProfileDetails.setText(user_Email);
            firstName.setText(firstName1);
            lastName.setText(lastName1);
            birthday.setText(birthday1);
            gender.setText(gender1);
            password.setText(password1.replaceAll(".", "*"));
            mobileNumber.setText(mobileNumber1);
            securityQuestion.setText(securityQuestion1);
            securityAnswer.setText(securityAnswer1);
            bio.setText(bio1);
            if(profilePicture1 != null) profilePictureInProfileDetails.setImage(new Image(new File(profilePicture1).toURI().toString()));

            userName.setText(firstName1 + " " + lastName1);
            userEmail.setText(user_Email);
            if(profilePicture1 != null) profilePicture.setImage(new Image(new File(profilePicture1).toURI().toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onPasswordInProfileSettingsClicked(MouseEvent mouseEvent) {
        password.setText(YourPropertyUserSession.getInstance().getUserPassword());
    }

    public void openFacebookAccountInBrowser() {
        if(facebook.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Open Facebook");
            alert.setHeaderText(null);
            alert.setContentText("You don't have any facebook account associated with your user account yet.");
            alert.showAndWait();
        } else {
            openLink(facebook);
        }
    }

    public void openGoogleAccountInBrowser() {
        if(google.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Open Google");
            alert.setHeaderText(null);
            alert.setContentText("You don't have any google account associated with your user account yet.");
            alert.showAndWait();
        } else {
            openLink(google);
        }
    }

    public void openTwitterAccountInBrowser() {
        if(twitter.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Open Twitter");
            alert.setHeaderText(null);
            alert.setContentText("You don't have any twitter account associated with your user account yet.");
            alert.showAndWait();
        } else {
            openLink(twitter);
        }
    }

    private void openLink(String url) {
        try{
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                System.out.println("Desktop browsing is not supported on your system.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // dashboardLayer.setVisible(true);
        // profileSettingsLayer.setVisible(false);
        // manageProfile.setVisible(false);

        onDashboardOptionClicked();

        // Fetch user email
        String user_Email = YourPropertyUserSession.getInstance().getUserEmail();

        // Get user info from database
        String targetUrl = "http://localhost:8080/api/yourpropertyuser";
        try {
            JSONObject jsonUser = HTTPClient.sendGetRequest(targetUrl, user_Email);

            String firstName1 = jsonUser.optString("FirstName", "");
            String lastName1 = jsonUser.optString("LastName", "");
            String profilePicture1 = jsonUser.optString("ProfilePicture", "");

            userName.setText(firstName1 + " " + lastName1);
            userEmail.setText(user_Email);
            if(profilePicture1 != null) profilePicture.setImage(new Image(new File(profilePicture1).toURI().toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDashboardOptionClicked() {
        dashboardLayer.setVisible(true);
        myPropertyLayer.setVisible(false);
        savedPropertyLayer.setVisible(false);
        profileSettingsLayer.setVisible(false);
        manageProfile.setVisible(false);
        propertyDetailsLayer.setVisible(false);
        editPropertyLayer.setVisible(false);

        String userEmail = YourPropertyUserSession.getInstance().getUserEmail();
        String targetUrl = "http://localhost:8080/api/properties/email";

        try {
            // Fetch properties from backend
            JSONArray properties = HTTPClient.sendGetListRequest(targetUrl, userEmail);
            if(properties != null) {
                int numberOfProperties = properties.length();
                numberOfPropertiesDashboard.setText(String.valueOf(numberOfProperties));

                BigDecimal revenue = new BigDecimal(0);

                for(int i = 0; i < properties.length(); i++){
                    JSONObject property = properties.getJSONObject(i);

                    if ("For sale".equals(property.getString("OfferType"))) {
                        revenue = revenue.add(property.getBigDecimal("SalePrice"));
                    } else if ("For rent".equals(property.getString("OfferType"))) {
                        revenue = revenue.add(property.getBigDecimal("RentPrice"));
                    }
                }
                estimatedRevenueDashboard.setText(revenue.toString());
            } else {
                numberOfPropertiesDashboard.setText("N/A");
                estimatedRevenueDashboard.setText("N/A");
            }

            String targetURL = "http://localhost:8080/api/payment/rented";
            JSONArray rentedPropertiesArray = HTTPClient.sendGetRentedProperties(targetURL, userEmail);
            Boolean notSeller = true;
            if(!rentedPropertiesArray.isEmpty()) {
                int numberOfTenants = 0;
                rentedPropertiesInDashboardFlowPane.getChildren().clear();
                for(int i = 0; i < rentedPropertiesArray.length(); i++) {
                    JSONObject rentedPropertyJson = rentedPropertiesArray.getJSONObject(i);
                    if(rentedPropertyJson != null && rentedPropertyJson.getString("offerType").equals("For rent")) {
                        numberOfTenants++;
                        Pane rentedPropertyPane = createRentedPropertyPane(rentedPropertyJson);
                        rentedPropertiesInDashboardFlowPane.getChildren().add(rentedPropertyPane);
                        notSeller = false;
                    }
                }
                numberOfTenantsDashboard.setText(String.valueOf(numberOfTenants));
            }
            if(notSeller){
                numberOfTenantsDashboard.setText("N/A");
                rentedPropertiesInDashboardFlowPane.getChildren().add(noRentedPropertiesFoundLabel);
            }

            String targetURL1 = "http://localhost:8080/api/payment/rental";
            JSONArray rentalPropertiesArray = HTTPClient.sendGetRentedProperties(targetURL1, userEmail);
            Boolean notPurchaser = true;
            if(!rentalPropertiesArray.isEmpty()) {
                rentalPropertiesInHomepageFlowPane.getChildren().clear();
                for(int i = 0; i < rentalPropertiesArray.length(); i++) {
                    JSONObject rentalPropertyJson = rentalPropertiesArray.getJSONObject(i);
                    if(rentalPropertyJson != null && rentalPropertyJson.getString("offerType").equals("For rent")) {
                        Pane rentalPropertyPane = createRentedPropertyPane(rentalPropertyJson);
                        rentedPropertiesInDashboardFlowPane.getChildren().add(rentalPropertyPane);
                        notPurchaser = false;
                    }
                }
            }
            if(notPurchaser){
                rentalPropertiesInHomepageFlowPane.getChildren().add(noRentalPropertiesFoundLabel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadInboxMessages();
    }

    private void loadInboxMessages() {
        inboxInDashboardVBox.getChildren().clear();

        String user_Email = YourPropertyUserSession.getInstance().getUserEmail();
        String targetUrl = "http://localhost:8080/api/chat/inbox";

        try {
            JSONArray inboxMessages = HTTPClient.sendGetListRequest(targetUrl, user_Email);
//            System.out.println(inboxMessages);
            if(!inboxMessages.isEmpty()){
                for(int i = 0; i < inboxMessages.length(); i++){
                    JSONObject message = inboxMessages.getJSONObject(i);

                    // Create an FXMLLoader instance and load the FXML
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/inboxMessageInDashboardTemplate.fxml"));
                    Parent inboxMessagePane = loader.load();

                    ImageView senderProfilePicture = (ImageView) inboxMessagePane.lookup("#profilePicture");
                    senderProfilePicture.setImage(new Image(new File(message.getString("chatWithProfilePicture")).toURI().toString()));

                    Label senderName = (Label) inboxMessagePane.lookup("#name");
                    senderName.setText(message.getString("chatWithName"));

                    Label senderEmail = (Label) inboxMessagePane.lookup("#email");
                    senderEmail.setText(message.getString("chatWithEmail"));

                    Label senderMessage = (Label) inboxMessagePane.lookup("#message");
                    if(!Objects.equals(message.getString("senderEmail"), user_Email))
                        senderMessage.setText(message.getString("recentMessage"));
                    else
                        senderMessage.setText("You: " + message.getString("recentMessage"));

                    LocalDateTime messageDateTime = OffsetDateTime.parse(message.getString("recentMessageTimeStamp"), DateTimeFormatter.ISO_OFFSET_DATE_TIME).atZoneSameInstant(ZoneId.of("Asia/Dhaka")).toLocalDateTime();
                    LocalTime messageTime = messageDateTime.toLocalTime();

                    Label time = (Label) inboxMessagePane.lookup("#time");
                    time.setText(messageTime.format(DateTimeFormatter.ofPattern("hh:mm a")));

                    ImageView propertyPicture = (ImageView) inboxMessagePane.lookup("#propertyPicture");
                    propertyPicture.setImage(new Image(new File(message.getString("propertyPicture")).toURI().toString()));

                    Label propertyTitle = (Label) inboxMessagePane.lookup("#propertyTitle");
                    propertyTitle.setText(message.getString("propertyTitle"));

                    Label propertyPrice = (Label) inboxMessagePane.lookup("#propertyPrice");
                    propertyPrice.setText(message.get("propertyPrice").toString());

                    Label propertyLocation = (Label) inboxMessagePane.lookup("#propertyLocation");
                    propertyLocation.setText(message.getString("propertyLocation"));

                    inboxMessagePane.setOnMouseClicked(_ -> {
                        // Create a new stage for the edit window
                        Stage stage = new Stage();
                        stage.setTitle("Chat with user");
                        stage.initModality(Modality.APPLICATION_MODAL);

                        try{
                            // Call the HTTPClient getPropertySeller method
                            JSONObject userJson = HTTPClient.sendGetPropertySellerRequest("http://localhost:8080/api/properties/propertyseller", propertyTitle.getText(), propertyLocation.getText());

                            // Create a new stage for the edit window
                            Stage stage2 = new Stage();
                            stage.setTitle("Chat with user");
                            stage.initModality(Modality.APPLICATION_MODAL);

                            // Create an FXMLLoader instance and load the FXML
                            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("chatBoxPage.fxml"));
                            Parent chatBox = loader2.load();

                            // Get the controller for chatBox
                            chatBoxController chatBoxController = loader2.getController();
                            chatBoxController.setUserDetails(message.getString("chatWithProfilePicture"), message.getString("chatWithName"));
                            chatBoxController.setUserEmail(user_Email, message.getString("chatWithEmail"));
                            chatBoxController.setPropertyData(propertyTitle.getText(), propertyLocation.getText(), propertyPrice.getText(), propertyPicture.getImage().getUrl());
                            chatBoxController.setSellerEmail(userJson.getString("sellerEmail"));
                            chatBoxController.loadChatHistory();

                            // Set up the scene and show the stage
                            Scene scene = new Scene(chatBox);
                            stage.setScene(scene);
                            stage.showAndWait();  // Waits until the edit window is closed
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    inboxInDashboardVBox.getChildren().add(inboxMessagePane);
                }
            } else {
                inboxInDashboardVBox.getChildren().add(noMessageFoundLabel);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Pane createRentedPropertyPane(JSONObject rentedPropertyJson) {
        try {
            // Load the FXML for each property pane
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/rentedPropertyDashboardTemplatePane.fxml"));
            Pane propertyPane = loader.load();

            Label propertyType = (Label) propertyPane.lookup("#showPropertyType");
            Label propertyOfferType = (Label) propertyPane.lookup("#propertyOfferType");
            Label titleMyProperty = (Label) propertyPane.lookup("#titleMyProperty");
            Label myPropertyPrice = (Label) propertyPane.lookup("#myPropertyPrice");
            Label myPropertyLocation = (Label) propertyPane.lookup("#myPropertyLocation");
            ImageView propertyImage = (ImageView) propertyPane.lookup("#propertyImage");

            propertyOfferType.setText(rentedPropertyJson.getString("offerType"));
            propertyType.setText(rentedPropertyJson.getString("type"));
            titleMyProperty.setText(rentedPropertyJson.getString("title"));
            if(propertyOfferType.getText().equals("For sale")){
                myPropertyPrice.setText(rentedPropertyJson.getBigDecimal("price").toString());
            } else if(propertyOfferType.getText().equals("For rent")) {
                myPropertyPrice.setText(rentedPropertyJson.getBigDecimal("price").toString() + " (" + rentedPropertyJson.getString("term") + ")");
            }
            myPropertyLocation.setText(rentedPropertyJson.getString("address"));
            propertyImage.setImage(new Image(new File(rentedPropertyJson.getString("picture")).toURI().toString()));

            Button viewPropertyButton = (Button) propertyPane.lookup("#viewPropertyButton");
            if(viewPropertyButton != null) {
                viewPropertyButton.setOnAction(_ -> {
                    try {
                        String titleString = titleMyProperty.getText();
                        String address = myPropertyLocation.getText();
                        String email = YourPropertyUserSession.getInstance().getUserEmail();

                        // Call the HTTPClient getProperty method
                        JSONObject propertyJson = HTTPClient.sendGetListRequest("http://localhost:8080/api/properties/property", email, titleString, address);

//                            System.out.println(propertyJson);
                        dashboardLayer.setVisible(false);
                        myPropertyLayer.setVisible(false);
                        savedPropertyLayer.setVisible(false);
                        profileSettingsLayer.setVisible(false);
                        manageProfile.setVisible(false);
                        propertyDetailsLayer.setVisible(true);
                        editPropertyLayer.setVisible(false);

                        boolean isOwner = true;
                        if(rentedPropertyJson.getString("email").equals(email)) {
                            isOwner = false;
                        }

                        onPropertyPreviewClicked(propertyJson, isOwner);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            Pane renterDetailsPane = (Pane) propertyPane.lookup("#renterDetails");
            Label renterEmail = (Label) renterDetailsPane.lookup("#renterEmail");
            Label renterName = (Label) renterDetailsPane.lookup("#renterName");
            ImageView renterPicture = (ImageView) renterDetailsPane.lookup("#renterPicture");

            renterEmail.setText(rentedPropertyJson.getString("email"));
            renterName.setText(rentedPropertyJson.getString("name"));
            renterPicture.setImage(new Image(new File(rentedPropertyJson.getString("profilePicture")).toURI().toString()));

            renterDetailsPane.setOnMouseClicked(_ -> {
                try{
                    // Create a new stage for the edit window
                    Stage stage2 = new Stage();
                    stage2.setTitle("Chat with user");
                    stage2.initModality(Modality.APPLICATION_MODAL);
                    // Create an FXMLLoader instance and load the FXML
                    FXMLLoader loader2 = new FXMLLoader(getClass().getResource("chatBoxPage.fxml"));
                    Parent chatBox = loader2.load();

                    // Get the controller for chatBox
                    chatBoxController chatBoxController = loader2.getController();
                    chatBoxController.setUserDetails(rentedPropertyJson.getString("profilePicture"), rentedPropertyJson.getString("name"));
                    chatBoxController.setUserEmail(YourPropertyUserSession.getInstance().getUserEmail(), rentedPropertyJson.getString("email"));
                    chatBoxController.setPropertyData(titleMyProperty.getText(), myPropertyLocation.getText(), myPropertyPrice.getText(), rentedPropertyJson.getString("picture"));
                    chatBoxController.setSellerEmail(YourPropertyUserSession.getInstance().getUserEmail());
                    chatBoxController.loadChatHistory();

                    // Set up the scene and show the stage
                    Scene scene = new Scene(chatBox);
                    stage2.setScene(scene);
                    stage2.showAndWait();  // Waits until the edit window is closed
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            return propertyPane;
        } catch (Exception e) {
            throw new RuntimeException(e);
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

    public void addCustomSecurityQuestion(ActionEvent actionEvent) {

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

    public void addItemsInRentalTermChoice(){
        preferredRentalTermComboBox.getItems().addAll(
                "Weekly",
                "Biweekly",
                "Monthly",
                "Yearly"
        );
    }
    public void addItemsInPropertyEditChoice(){
        newPropertyRentTermBox.getItems().addAll(
                "Weekly",
                "Biweekly",
                "Monthly",
                "Yearly"
        );

    }

    public void chooseProfilePicture(ActionEvent actionEvent) {
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

    public void onUpdateDetailsClicked(ActionEvent actionEvent) {
        // Fetch user email
        String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

        // Getting the input values
        String firstName = newFirstName.getText();
        String lastName = newLastName.getText();

        String birthday;
        if(newBirthday.getValue() != null){
            LocalDate birthdayValue = newBirthday.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            birthday = birthdayValue.format(formatter);
        } else birthday = "";

        String gender;
        if(newMaleSelected.isSelected()) gender = "Male";
        else if(newFemaleSelected.isSelected()) gender = "Female";
        else if(newOtherSelected.isSelected()) gender = "Other";
        else gender = "";

        String email = newEmail.getText();
        String password = newPassword.getText();
        String mobileNumber;
        if(newCountryCode.getSelectionModel().getSelectedItem() != null)
            mobileNumber = newCountryCode.getSelectionModel().getSelectedItem() + newMobileNumber.getText();
        else mobileNumber = "";
        String securityQuestion = newSecurityQuestionBox.getValue();
        String securityAnswer = newSecurityAnswer.getText();

        String bio = newBio.getText();
        String facebookLink = newFacebookLink.getText();
        String googleLink = newGoogleLink.getText();
        String twitterLink = newTwitterLink.getText();

        String profilePicture;
        if(selectedProfilePicture != null){
            profilePicture = selectedProfilePicture.getAbsolutePath();
        } else profilePicture = "";

        // Changing user session email
        if(!email.isEmpty()) YourPropertyUserSession.getInstance().setUserEmail(email);

        // Create json object and generate jsonInputString
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("FirstName", firstName);
        jsonObject.put("LastName", lastName);
        jsonObject.put("Birthday", birthday);
        jsonObject.put("Gender", gender);
        jsonObject.put("Email", email);
        jsonObject.put("Password", password);
        jsonObject.put("MobileNumber", mobileNumber);
        jsonObject.put("SecurityQuestion", securityQuestion);
        jsonObject.put("SecurityAnswer", securityAnswer);
        jsonObject.put("Bio", bio);
        jsonObject.put("Facebook", facebookLink);
        jsonObject.put("Google", googleLink);
        jsonObject.put("Twitter", twitterLink);
        jsonObject.put("ProfilePicture", profilePicture);

        String jsonInputString = jsonObject.toString();

        String targetUrl = "http://localhost:8080/api/yourpropertyuser";

        try{
            Pair<Integer, String> pair = HTTPClient.sendPutRequestForEditProfileDetails(targetUrl, jsonInputString, userEmail);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Profile Details");
            alert.setHeaderText(null);
            alert.setContentText(pair.getValue());
            alert.showAndWait();

            refreshUserProfileDetails();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

                // Update the user login session
                YourPropertyUserSession.getInstance().logoutUser();

                // Redirect to homepage
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepage.fxml")));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("Your Property");
                stage.setScene(scene);
                stage.show();
            } catch(Exception e){
                e.printStackTrace();
            }
        } else {
            alert.close();
        }
    }

    public void onManageProfileClicked(ActionEvent actionEvent) {
        dashboardLayer.setVisible(false);
        myPropertyLayer.setVisible(false);
        savedPropertyLayer.setVisible(false);
        profileSettingsLayer.setVisible(false);
        manageProfile.setVisible(true);
        propertyDetailsLayer.setVisible(false);
        editPropertyLayer.setVisible(false);

        manageBuyerProfile.setVisible(true);
        manageRenterProfile.setVisible(false);
        manageSellerProfile.setVisible(false);
        onManageBuyerProfileOptionClicked();
    }

    public void onCreateBuyerProfileClicked(ActionEvent actionEvent) {
        try {
            String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

            BigDecimal minBudget = new BigDecimal(buyerMinimumBudget.getText());
            BigDecimal maxBudget = new BigDecimal(buyerMaximumBudget.getText());

            // Collect preferred property types
            List<String> preferredPropertyTypes = new ArrayList<>();
            if(buyerProfileApartmentChecked.isSelected()) preferredPropertyTypes.add("Apartment");
            if(buyerProfileHousesChecked.isSelected()) preferredPropertyTypes.add("Houses");
            if(buyerProfileLandsChecked.isSelected()) preferredPropertyTypes.add("Lands");
            if(buyerProfileResidentialUnitsChecked.isSelected()) preferredPropertyTypes.add("Residential Units");
            if(buyerProfileCommercialUnitsChecked.isSelected()) preferredPropertyTypes.add("Commercial Units");
            if(buyerProfileBusinessPlacesChecked.isSelected()) preferredPropertyTypes.add("Business Places");

            // Create JSON Object
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", userEmail);
            jsonObject.put("minBudget", minBudget);
            jsonObject.put("maxBudget", maxBudget);
            jsonObject.put("propertyTypes", new JSONArray(preferredPropertyTypes));
            jsonObject.put("locations", new JSONArray(buyerPreferredLocations));

            String jsonInputString = jsonObject.toString();

            String targetUrl = "http://localhost:8080/api/yourpropertybuyer/register";

            try {
                Pair<Integer, String> pair = HTTPClient.sendPostRequest(targetUrl, jsonInputString);
                if(pair.getKey() == 200){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Create your buyer profile");
                    alert.setHeaderText(null);
                    alert.setContentText(pair.getValue());
                    alert.showAndWait();

                    onManageBuyerProfileOptionClicked();
                } else if(pair.getKey() == 409){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Something went wrong!");
                    alert.setContentText("You already have an active buyer profile!");

                    alert.showAndWait();
                } else if(pair.getKey() == 500){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Something went wrong!");
                    alert.setContentText("An error occurred!");

                    alert.showAndWait();
                }
            } catch (Exception e) {
                System.out.println("An error occurred!");
                e.printStackTrace();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to submit profile: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void onUpdateBuyerProfileClicked(ActionEvent actionEvent){

    }

    public void onBuyerAddPreferredLocationClicked(ActionEvent actionEvent) {
        System.out.println("Buyer add preferred location clicked");

        String location = buyerPreferredLocationField.getText().trim(); // Get user input
        System.out.println("Location entered: " + location);

        if (!location.isEmpty() && !isLocationInFlowPane(location, buyerPreferredLocationFlowPane)) {
            System.out.println("Adding location: " + location);
            buyerPreferredLocations.add(location);
            HBox locationPane = createLocationPane(location, buyerPreferredLocationFlowPane);
            buyerPreferredLocationFlowPane.getChildren().add(locationPane);
            buyerPreferredLocationField.clear(); // Clear input field
        } else {
            System.out.println("Location is empty or already exists");
        }
    }

    public void onBuyerAddNewPreferredLocationClicked(ActionEvent actionEvent) {
        String location = buyerNewPreferredLocationField.getText().trim();

        if (!location.isEmpty() && !isLocationInVBox(location, buyerPreferredLocationVBox)) {
            buyerPreferredNewLocations.add(location);
            HBox locationPane = createLocationPaneInVBox(location, buyerPreferredLocationVBox);

            // Add the new location before the update button
            buyerPreferredLocationVBox.getChildren().removeLast();
            buyerPreferredLocationVBox.getChildren().add(locationPane);
            buyerPreferredLocationVBox.getChildren().add(updateBuyerProfileButton);

            buyerNewPreferredLocationField.clear();
        }
    }

    // Method to create a location pane with a delete button
    private HBox createLocationPane(String location, FlowPane parentPane) {
        System.out.println("Creating location pane for: " + location);

        // Label for location name
        Label locationLabel = new Label(location);
        locationLabel.setStyle("-fx-font-size: 14; -fx-text-fill: #7c4683;");

        // Cross button to remove the location
        ImageView deleteButton = createDeleteIcon();
        if (deleteButton != null) {
            deleteButton.setOnMouseClicked(e -> {
//                System.out.println("Removing location: " + location);
                parentPane.getChildren().remove(deleteButton.getParent());
                buyerPreferredLocations.remove(location);
            });
        }

        // HBox to contain the label and delete button
        HBox locationPane = new HBox(locationLabel, deleteButton);
        locationPane.setAlignment(Pos.CENTER_LEFT);
        locationPane.setSpacing(5);
        locationPane.setPadding(new Insets(5));
        locationPane.setStyle("-fx-background-color: #ecdbee; -fx-border-radius: 5; -fx-background-radius: 5;");

        return locationPane;
    }

    // Method to create or show location pane with a delete button
    private HBox createLocationPaneInVBox(String location, VBox parentPane) {
        // Label for location name
        Label locationLabel = new Label(location);
        locationLabel.setStyle("-fx-font-size: 14;");
        locationLabel.setStyle("-fx-text-fill:  #7c4683");

        // Cross button to remove the location
        ImageView deleteButton = createDeleteIcon();
        if (deleteButton != null) {
            deleteButton.setOnMouseClicked(e -> parentPane.getChildren().remove(deleteButton.getParent()));
        }

        // HBox to contain the label and delete button
        HBox locationPane = new HBox(locationLabel, deleteButton);
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
            URL resource = getClass().getResource("/images/cancel.png");
            if (resource == null) {
                System.out.println("Delete icon resource not found!");
                return null;
            } else {
                System.out.println("Delete icon resource found: " + resource.toExternalForm());
            }

            Image deleteImage = new Image(resource.toExternalForm());
            ImageView deleteIcon = new ImageView(deleteImage);
            deleteIcon.setFitWidth(16); // Set width of icon
            deleteIcon.setFitHeight(16); // Set height of icon
            deleteIcon.setPreserveRatio(true);
            return deleteIcon;
        } catch (Exception e) {
            System.err.println("Error loading delete icon: " + e.getMessage());
            return null;
        }
    }

    // Helper method to check if a location already exists in the FlowPane
    private boolean isLocationInFlowPane(String location, FlowPane flowPane) {
        System.out.println("Checking if location exists: " + location);
        return flowPane.getChildren().stream().anyMatch(node -> {
            HBox hbox = (HBox) node;
            Label label = (Label) hbox.getChildren().get(0); // Get the label inside the HBox
            return label.getText().equals(location);
        });
    }

    // Helper method to check if a location already exists in the VBox
    // private boolean isLocationInVBox(String location, VBox vBox) {
    //     return vBox.getChildren().stream().anyMatch(node -> ((Label) ((HBox) node).getChildren().get(0)).getText().equals(location));
    // }
    private boolean isLocationInVBox(String location, VBox vBox) {
        return vBox.getChildren().stream()
            .filter(node -> node instanceof HBox)  // Check if node is an HBox
            .map(node -> (HBox) node)
            .anyMatch(hBox -> 
                !hBox.getChildren().isEmpty() &&  // Ensure HBox has children
                hBox.getChildren().getFirst() instanceof Label &&  // First child is a Label
                ((Label) hBox.getChildren().getFirst()).getText().equals(location)
            );
    }
    

    public void onCancelCreateBuyerClicked(MouseEvent mouseEvent) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(createBuyerProfileFields);
        translateTransition.setDuration(Duration.seconds(0.5));
        translateTransition.setToY(575);
        translateTransition.play();

        createBuyerProfileFields.setVisible(false);
    }

    public void onCreateNewBuyerProfileClicked(ActionEvent actionEvent) {
        createBuyerProfileFields.setVisible(true);

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(createBuyerProfileFields);
        translateTransition.setDuration(Duration.seconds(0.5));
        translateTransition.setToY(0);
        translateTransition.play();
    }

    public void onCreateNewRenterProfileClicked(ActionEvent actionEvent) {
        addItemsInRentalTermChoice();
        createRenterProfileFields.setVisible(true);

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(createRenterProfileFields);
        translateTransition.setDuration(Duration.seconds(0.5));
        translateTransition.setToY(0);
        translateTransition.play();
    }

    public void onRenterAddPreferredLocationClicked(ActionEvent actionEvent) {
        String location = renterPreferredLocationField.getText().trim();
    
        if (!location.isEmpty() && !isLocationInFlowPane(location, renterPreferredLocationFlowPane)) { 
            // Note the `!` to ensure we only add if the location is NOT already in the FlowPane
            renterPreferredLocations.add(location);
            HBox locationPane = createLocationPane(location, renterPreferredLocationFlowPane);
            renterPreferredLocationFlowPane.getChildren().add(locationPane);
            renterPreferredLocationField.clear();
        } else {
            System.out.println("Location is empty or already exists.");
        }
    }
    

    public void activeTransitionOnY(Node c, double move){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(c);
        transition.setToY(move);
        transition.play();
    }

    public void onCreateRenterProfileClicked(ActionEvent actionEvent) {
        try{
            ObjectNode renterProfileData = objectMapper.createObjectNode();

            String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

            BigDecimal minBudget = new BigDecimal(renterMinimumBudget.getText());
            BigDecimal maxBudget = new BigDecimal(renterMaximumBudget.getText());

            LocalDate moveInDatevalue = renterPreferredMoveInDate.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String moveInDate = moveInDatevalue.format(formatter);

            String rentalTerm = preferredRentalTermComboBox.getSelectionModel().getSelectedItem();

            // Collect preferred property types
            List<String> preferredPropertyTypes = new ArrayList<>();
            if(renterProfileApartmentChecked.isSelected()) preferredPropertyTypes.add("Apartment");
            if(renterProfileHousesChecked.isSelected()) preferredPropertyTypes.add("Houses");
            if(renterProfileLandsChecked.isSelected()) preferredPropertyTypes.add("Lands");
            if(renterProfileResidentialUnitsChecked.isSelected()) preferredPropertyTypes.add("Residential Units");
            if(renterProfileCommercialUnitsChecked.isSelected()) preferredPropertyTypes.add("Commercial Units");
            if(renterProfileBusinessPlacesChecked.isSelected()) preferredPropertyTypes.add("Business Places");

            ArrayNode preferredPropertyType = objectMapper.createArrayNode();
            preferredPropertyTypes.forEach(preferredPropertyType::add);

            ArrayNode preferredLocations = objectMapper.createArrayNode();
            renterPreferredLocations.forEach(preferredLocations::add);

            renterProfileData.put("email", userEmail);
            renterProfileData.put("minBudget", minBudget);
            renterProfileData.put("maxBudget", maxBudget);
            renterProfileData.put("moveInDate", moveInDate);
            renterProfileData.put("rentalTerm", rentalTerm);
            renterProfileData.put("propertyTypes", preferredPropertyType);
            renterProfileData.put("locations", preferredLocations);

            if(renterProfileApartmentChecked.isSelected()) {
                Boolean petPolicy = null, furnished = null, terrace = null, parking = null;
                if(PetPolicyYes.isSelected()) petPolicy = true;
                else if(PetPolicyNo.isSelected()) petPolicy = false;
                if(furnishedPolicyYes.isSelected()) furnished = true;
                else if(furnishedPolicyNo.isSelected()) furnished = false;
                if(terraceAvailabilityYes.isSelected()) terrace = true;
                else if(terraceAvailabilityNo.isSelected()) terrace = false;
                if(parkingAvailabilityYes.isSelected()) parking = true;
                else if(parkingAvailabilityNo.isSelected()) parking = false;

                ObjectNode apartmentFeatures = objectMapper.createObjectNode();
                apartmentFeatures.put("petPolicy", petPolicy);
                apartmentFeatures.put("furnished", furnished);
                apartmentFeatures.put("parkingAvailability", parking);
                apartmentFeatures.put("terraceAvailability", terrace);

                renterProfileData.put("ApartmentFeatures", apartmentFeatures);
            }

            if(renterProfileHousesChecked.isSelected()) {
                String basementPreferrence = "";
                if(basementPreferenceYes.isSelected()) basementPreferrence = "Finished";
                else if(basementPreferrenceNo.isSelected()) basementPreferrence = "Unfinished";

                List<String> securityFeatures = new ArrayList<>();
                if(SecuritySystem.isSelected()) securityFeatures.add("Security System");
                if(Cameras.isSelected()) securityFeatures.add("Camera");
                if(GatedEntry.isSelected()) securityFeatures.add("Gated Entry");

                ArrayNode securityFeaturesArray = objectMapper.createArrayNode();
                securityFeatures.forEach(securityFeaturesArray::add);

                Boolean garage = null;
                if(garagePreferenceYes.isSelected()) garage = true;
                else if(garagePreferrenceNo.isSelected()) garage = false;

                ObjectNode houseFeatures = objectMapper.createObjectNode();
                houseFeatures.put("basement", basementPreferrence);
                houseFeatures.put("securityFeatures", securityFeaturesArray);
                houseFeatures.put("garage", garage);

                renterProfileData.put("HouseFeatures", houseFeatures);
            }

            if(renterProfileLandsChecked.isSelected()) {
                String topographyPreferrence = "";
                if(flatLand.isSelected()) topographyPreferrence = "Flat";
                else if(hillyLand.isSelected()) topographyPreferrence = "Hilly";
                else if(slopedLand.isSelected()) topographyPreferrence = "Sloped";

                String roadAccessPreferrence = "";
                if(pavedRoadAccessLand.isSelected()) roadAccessPreferrence = "Paved";
                else if(unpavedRoadAccessLand.isSelected()) roadAccessPreferrence = "Unpaved";

                List<String> utilities = new ArrayList<>();
                if(electricityUtilityLand.isSelected()) utilities.add("Electricity");
                if(waterUtilityLand.isSelected()) utilities.add("Water");
                if(sewageUtilityLand.isSelected()) utilities.add("Sewage");

                ArrayNode utilitiesArray = objectMapper.createArrayNode();
                utilities.forEach(utilitiesArray::add);

                ObjectNode landFeatures = objectMapper.createObjectNode();
                landFeatures.put("topography", topographyPreferrence);
                landFeatures.put("utilities", utilitiesArray);
                landFeatures.put("roadAccess", roadAccessPreferrence);

                renterProfileData.put("LandFeatures", landFeatures);
            }

            if(renterProfileResidentialUnitsChecked.isSelected()) {
                List<String> amenities = new ArrayList<>();
                if(poolAmenitiyResidentialUnit.isSelected()) amenities.add("Pool");
                if(gymAmenityResidentialUnit.isSelected()) amenities.add("Gym");
                if(playgroundAmenityResidentialUnit.isSelected()) amenities.add("Kid's Playground");

                ArrayNode amenitiesArray = objectMapper.createArrayNode();
                amenities.forEach(amenitiesArray::add);

                String storageSpace = "";
                if(closetStorageResidentialUnit.isSelected()) storageSpace = "Closet";
                else if(basementStorageResidentialUnit.isSelected()) storageSpace = "Basement";
                else if(builtInStorageResidentialUnit.isSelected()) storageSpace = "Built-In";

                Boolean noiseInsulation = null;
                if(noiseInsulatedResidentialUnit.isSelected()) noiseInsulation = true;
                else if(noiseExposeResidentialUnit.isSelected()) noiseInsulation = false;

                ObjectNode residentialFeatures = objectMapper.createObjectNode();
                residentialFeatures.put("amenities", amenitiesArray);
                residentialFeatures.put("storageSpace", storageSpace);
                residentialFeatures.put("noiseInsulation", noiseInsulation);

                renterProfileData.put("ResidentialUnitFeatures", residentialFeatures);
            }

            if(renterProfileCommercialUnitsChecked.isSelected()) {
                List<String> commercialPurpose = new ArrayList<>();
                if(retailPurposeCommercialUnit.isSelected()) commercialPurpose.add("Retail");
                if(officePurposeCommercialUnit.isSelected()) commercialPurpose.add("Office");
                if(medicalPurposeCommercialUnit.isSelected()) commercialPurpose.add("Medical");
                if(restaurantPurposeCommercialUnit.isSelected()) commercialPurpose.add("Restaurant");

                ArrayNode commercialPurposeArray = objectMapper.createArrayNode();
                commercialPurpose.forEach(commercialPurposeArray::add);

                String floorLayout = "";
                if(openPlanFloorLayoutCommercialUnit.isSelected()) floorLayout = "Open Plan";
                else if(modularFloorLayoutCommercialUnit.isSelected()) floorLayout = "Modular";
                else if(separateFloorLayoutCommercialUnit.isSelected()) floorLayout = "Separate";

                List<String> accessibility = new ArrayList<>();
                if(elevatorCommercialUnit.isSelected()) accessibility.add("Elevator");
                if(brailleSignageCommercialUnit.isSelected()) accessibility.add("Braille Signage");
                if(accessibleWashroomCommercialUnit.isSelected()) accessibility.add("Accessible Washroom");

                ArrayNode accessibilityArray = objectMapper.createArrayNode();
                accessibility.forEach(accessibilityArray::add);

                ObjectNode commercialFeatures = objectMapper.createObjectNode();
                commercialFeatures.put("accessibilities", accessibilityArray);
                commercialFeatures.put("floorLayout", floorLayout);
                commercialFeatures.put("purposes", commercialPurposeArray);

                renterProfileData.put("CommercialUnitFeatures", commercialFeatures);
            }

            if(renterProfileBusinessPlacesChecked.isSelected()) {
                List<String> businessPurpose = new ArrayList<>();
                if(storePurposeBusinessPlace.isSelected()) businessPurpose.add("Store");
                if(garagePurposeBusinessPlace.isSelected()) businessPurpose.add("Garage");

                ArrayNode businessPurposeArray = objectMapper.createArrayNode();
                businessPurpose.forEach(businessPurposeArray::add);

                String ventilation = "";
                if(standardVentilationBusinessPlace.isSelected()) ventilation = "Standard";
                else if(highPoweredFansVentilationBusinessPlace.isSelected()) ventilation = "High Powered Fans";
                else if(airFiltrationVentilationBusinessPlace.isSelected()) ventilation = "Air Filtration";

                List<String> powerSupply = new ArrayList<>();
                if(standardPowerSupplyBusinessPlace.isSelected()) powerSupply.add("Standard");
                if(highVoltagePowerBusinessPlace.isSelected()) powerSupply.add("High Voltage");
                if(generatorPowerSupplyBusinessPlace.isSelected()) powerSupply.add("Backup Generator");

                ArrayNode powerSupplyArray = objectMapper.createArrayNode();
                powerSupply.forEach(powerSupplyArray::add);

                ObjectNode businessFeatures = objectMapper.createObjectNode();
                businessFeatures.put("powerSupplies", powerSupplyArray);
                businessFeatures.put("ventilation", ventilation);
                businessFeatures.put("purposes", businessPurposeArray);

                renterProfileData.put("BusinessPlaceFeatures", businessFeatures);
            }

            String jsonInputString = renterProfileData.toString();

            String targetUrl = "http://localhost:8080/api/yourpropertyrenter/register";

            try {
                Pair<Integer, String> pair = HTTPClient.sendPostRequest(targetUrl, jsonInputString);
                if(pair.getKey() == 200){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Create your renter profile");
                    alert.setHeaderText(null);
                    alert.setContentText(pair.getValue());
                    alert.showAndWait();

                    onManageRenterProfileOptionClicked();
                } else if(pair.getKey() == 409){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Something went wrong!");
                    alert.setContentText("You already have an active renter profile!");

                    alert.showAndWait();
                } else if(pair.getKey() == 500){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Something went wrong!");
                    alert.setContentText("An error occurred!");

                    alert.showAndWait();
                }
            } catch (Exception e) {
                System.out.println("An error occurred!");
                e.printStackTrace();
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to submit profile: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void onCancelCreateRenterClicked(MouseEvent mouseEvent) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(createRenterProfileFields);
        translateTransition.setDuration(Duration.seconds(0.5));
        translateTransition.setToY(575);
        translateTransition.play();

        createRenterProfileFields.setVisible(false);
    }

    public void onManageRenterProfileOptionClicked() {
        manageProfile.setVisible(true);
        String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

        String targetUrl = "http://localhost:8080/api/yourpropertyrenter";

        try{
            JSONObject jsonObject = HTTPClient.sendGetRequest(targetUrl, userEmail);

            System.out.println(jsonObject);

            if(jsonObject != null) {
                String budget = jsonObject.getBigDecimal("minBudget").toString() + " - " + jsonObject.getBigDecimal("maxBudget").toString();
                String moveInDate = jsonObject.getString("moveInDate");
                String rentalTerm = jsonObject.getString("rentalTerm");

                renterBudgetShow.setText(budget);
                renterMoveInDateShow.setText(moveInDate);
                renterRentalTermShow.setText(rentalTerm);

                JSONArray propertyTypeArray = jsonObject.optJSONArray("preferredPropertyTypes");
                StringBuilder propertyTypes = new StringBuilder();
                for(int i = 0; i < propertyTypeArray.length(); i++){
                    propertyTypes.append(propertyTypeArray.getString(i));
                    if(i < propertyTypeArray.length()-1) propertyTypes.append(", ");
                }
                renterPropertyTypesShow.setText(propertyTypes.toString());

                JSONArray preferredLocations = jsonObject.optJSONArray("preferredLocations");
                StringBuilder locations = new StringBuilder();
                for(int i = 0; i < preferredLocations.length(); i++){
                    locations.append(preferredLocations.getString(i));
                    if(i < preferredLocations.length()-1) locations.append(", ");
                }
                renterPreferredLocationsShow.setText(locations.toString());

                // Clear current pane order in vboxContainer
                renterPrefferedPropertyFeaturesShow.getChildren().clear();

                if(jsonObject.has("apartmentFeatures")){
                    JSONArray apartmentFeatures = jsonObject.optJSONArray("apartmentFeatures");
                    JSONObject apartmentFeature = apartmentFeatures.getJSONObject(0);

                    String petPolicy = apartmentFeature.getBoolean("petPolicy") ? "Yes" : "No";
                    String furniturePolicy = apartmentFeature.getBoolean("furnished") ? "Furnished" : "Unfurnished";
                    String terraceAvailability = apartmentFeature.getBoolean("terraceAvailability") ? "Yes" : "No";
                    String parkingAvailability = apartmentFeature.getBoolean("parkingAvailability") ? "Covered" : "Uncovered";

                    renterApartmentFeaturesShow.setVisible(true);
                    renterPetPolicyShow.setText(petPolicy);
                    renterFurniturePolicyShow.setText(furniturePolicy);
                    renterTerracePreferrenceShow.setText(terraceAvailability);
                    renterParkingAvailabilityShow.setText(parkingAvailability);
                    renterPrefferedPropertyFeaturesShow.getChildren().add(renterApartmentFeaturesShow);
                } else {
                    renterApartmentFeaturesShow.setVisible(false);
                }

                if(jsonObject.has("houseFeatures")){
                    JSONArray houseFeatures = jsonObject.optJSONArray("houseFeatures");
                    JSONObject houseFeature = houseFeatures.getJSONObject(0);

                    String basement = houseFeature.getString("basement");

                    JSONArray securityFeatures = houseFeature.getJSONArray("security_features");
                    StringBuilder securityFeature = new StringBuilder();
                    for(int i = 0; i < securityFeatures.length(); i++){
                        securityFeature.append(securityFeatures.getString(i));
                        if(i < securityFeatures.length()-1) securityFeature.append(", ");
                    }

                    String garage = houseFeature.getBoolean("garage") ? "Yes" : "No";

                    renterHouseFeaturesShow.setVisible(true);
                    renterBasementPreferrenceShow.setText(basement);
                    renterSecurityFeaturesShow.setText(securityFeature.toString());
                    renterGarageAvailabilityShow.setText(garage);
                    renterPrefferedPropertyFeaturesShow.getChildren().add(renterHouseFeaturesShow);
                } else {
                    renterHouseFeaturesShow.setVisible(false);
                }

                if(jsonObject.has("landFeatures")){
                    JSONArray landFeatures = jsonObject.optJSONArray("landFeatures");
                    JSONObject landFeature = landFeatures.getJSONObject(0);

                    String topography = landFeature.getString("topography");

                    JSONArray landUtilities = landFeature.getJSONArray("utilites");
                    StringBuilder landUtility = new StringBuilder();
                    for(int i = 0; i < landUtilities.length(); i++){
                        landUtility.append(landUtilities.getString(i));
                        if(i < landUtilities.length()-1) landUtility.append(", ");
                    }

                    String roadAccess = landFeature.getString("roadAccess");

                    renterLandFeaturesShow.setVisible(true);
                    renterLandTopographyShow.setText(topography);
                    renterLandUtilitiesShow.setText(landUtility.toString());
                    renterLandRoadAccessShow.setText(roadAccess);
                    renterPrefferedPropertyFeaturesShow.getChildren().add(renterLandFeaturesShow);
                } else {
                    renterLandFeaturesShow.setVisible(false);
                }

                if(jsonObject.has("residentialUnitFeatures")){
                    JSONArray residentialUnitFeatures = jsonObject.optJSONArray("residentialUnitFeatures");
                    JSONObject residentialUnitFeature = residentialUnitFeatures.getJSONObject(0);

                    JSONArray amenities = residentialUnitFeature.getJSONArray("amenities");
                    StringBuilder amenity = new StringBuilder();
                    for(int i = 0; i < amenities.length(); i++){
                        amenity.append(amenities.getString(i));
                        if(i < amenities.length()-1) amenity.append(", ");
                    }

                    String storage = residentialUnitFeature.getString("storageSpace");
                    String noiseInsulation = residentialUnitFeature.getBoolean("noiseInsulation") ? "Insulated" : "Exposed";

                    renterResidentialUnitFeaturesShow.setVisible(true);
                    renterResidentialUnitAmenitiesShow.setText(amenity.toString());
                    renterResidentialUnitStorageShow.setText(storage);
                    renterResidentialUnitNoiseInsulationShow.setText(noiseInsulation);
                    renterPrefferedPropertyFeaturesShow.getChildren().add(renterResidentialUnitFeaturesShow);
                } else {
                    renterResidentialUnitFeaturesShow.setVisible(false);
                }

                if(jsonObject.has("commercialUnitFeatures")){
                    JSONArray commercialUnitFeatures = jsonObject.optJSONArray("commercialUnitFeatures");
                    JSONObject commercialUnitFeature = commercialUnitFeatures.getJSONObject(0);

                    JSONArray commercialUnitPurposes = commercialUnitFeature.getJSONArray("purpose");
                    StringBuilder commercialUnitPurpose = new StringBuilder();
                    for(int i = 0; i < commercialUnitPurposes.length(); i++){
                        commercialUnitPurpose.append(commercialUnitPurposes.getString(i));
                        if(i < commercialUnitPurposes.length() - 1) commercialUnitPurpose.append(", ");
                    }

                    String floorLayout = commercialUnitFeature.getString("floorLayout");

                    JSONArray commercialUnitAccessibilities = commercialUnitFeature.getJSONArray("accessibility");
                    StringBuilder commercialUnitAccessibility = new StringBuilder();
                    for(int i = 0; i < commercialUnitAccessibilities.length(); i++){
                        commercialUnitAccessibility.append(commercialUnitAccessibilities.getString(i));
                        if(i < commercialUnitAccessibilities.length()-1) commercialUnitAccessibility.append(", ");
                    }

                    renterCommercialUnitFeaturesShow.setVisible(true);
                    renterCommercialUnitAccessibilitiesShow.setText(commercialUnitAccessibility.toString());
                    renterCommercialUnitPurposesShow.setText(commercialUnitPurpose.toString());
                    renterCommercialUnitFloorLayoutShow.setText(floorLayout);
                    renterPrefferedPropertyFeaturesShow.getChildren().add(renterCommercialUnitFeaturesShow);
                } else {
                    renterCommercialUnitFeaturesShow.setVisible(false);
                }

                if(jsonObject.has("businessPlaceFeatures")) {
                    JSONArray businessPlaceFeatures = jsonObject.optJSONArray("businessPlaceFeatures");
                    JSONObject businessPlaceFeature = businessPlaceFeatures.getJSONObject(0);

                    JSONArray businessPlacePurposes = businessPlaceFeature.getJSONArray("purpose");
                    StringBuilder businessPlacePurpose = new StringBuilder();
                    for(int i = 0; i < businessPlacePurposes.length(); i++){
                        businessPlacePurpose.append(businessPlacePurposes.getString(i));
                        if(i < businessPlacePurposes.length()-1) businessPlacePurpose.append(", ");
                    }

                    String ventilation = businessPlaceFeature.getString("ventilation");

                    JSONArray businessUnitPowerSupply = businessPlaceFeature.getJSONArray("powerSupply");
                    StringBuilder powerSupply = new StringBuilder();
                    for(int i = 0; i < businessUnitPowerSupply.length(); i++){
                        powerSupply.append(businessUnitPowerSupply.getString(i));
                        if(i < businessUnitPowerSupply.length()-1) powerSupply.append(", ");
                    }

                    renterBusinessPlaceFeaturesShow.setVisible(true);
                    renterBusinessPlacePurposeShow.setText(businessPlacePurpose.toString());
                    renterBusinessPlaceVentilationFaciltiyShow.setText(ventilation);
                    renterBusinessPlacePowerSupplyShow.setText(powerSupply.toString());
                    renterPrefferedPropertyFeaturesShow.getChildren().add(renterBusinessPlaceFeaturesShow);
                } else {
                    renterBusinessPlaceFeaturesShow.setVisible(false);
                }

                // Add the updateRenterProfile button at the end of the vboxContainer
                renterPrefferedPropertyFeaturesShow.getChildren().add(updateRenterProfileButton);

                updateRenterProfile.setVisible(true);
                createRenterProfile.setVisible(false);
            } else {
                updateRenterProfile.setVisible(false);
                createRenterProfile.setVisible(true);
            }
            
        } catch (RuntimeException | IOException e) {
            if (e.getMessage().contains("HTTP error code : 404")) {
                // If a 404 error occurs, show create profile pane instead
                updateRenterProfile.setVisible(false);
                createRenterProfile.setVisible(true);
            } else {
                // Handle other exceptions
                e.printStackTrace();
            }
        }

        manageRenterProfile.setVisible(true);
        manageBuyerProfile.setVisible(false);
        manageSellerProfile.setVisible(false);

        showPreferredFeatures();
    }

    public void showRenterPreferredPropertyFeatures(){



    }

    public void showPreferredFeatures() {
        // Clear current pane order in vboxContainer
        showPreferredFeaturesVbox.getChildren().clear();

        // Add panes to vboxContainer based on checkbox state and in desired order
        if(renterProfileApartmentChecked.isSelected()) {
            apartmentFeatures.setVisible(true);
            showPreferredFeaturesVbox.getChildren().add(apartmentFeatures);
        } else {
            apartmentFeatures.setVisible(false);
        }

        if(renterProfileHousesChecked.isSelected()) {
            houseFeatures.setVisible(true);
            showPreferredFeaturesVbox.getChildren().add(houseFeatures);
        } else {
            houseFeatures.setVisible(false);
        }

        if(renterProfileLandsChecked.isSelected()) {
            landFeatures.setVisible(true);
            showPreferredFeaturesVbox.getChildren().add(landFeatures);
        } else {
            landFeatures.setVisible(false);
        }

        if(renterProfileResidentialUnitsChecked.isSelected()) {
            residentialUnitFeatures.setVisible(true);
            showPreferredFeaturesVbox.getChildren().add(residentialUnitFeatures);
        } else {
            residentialUnitFeatures.setVisible(false);
        }

        if(renterProfileCommercialUnitsChecked.isSelected()) {
            commercialUnitFeatures.setVisible(true);
            showPreferredFeaturesVbox.getChildren().add(commercialUnitFeatures);
        } else {
            commercialUnitFeatures.setVisible(false);
        }

        if(renterProfileBusinessPlacesChecked.isSelected()) {
            businessPlacesFeatures.setVisible(true);
            showPreferredFeaturesVbox.getChildren().add(businessPlacesFeatures);
        } else {
            businessPlacesFeatures.setVisible(false);
        }

        // Add the createRenterProfile pane at the end of the vboxContainer
        showPreferredFeaturesVbox.getChildren().add(preferredLocationAndCreateButtonPane);
    }

    public void onManageBuyerProfileOptionClicked() {
        manageProfile.setVisible(true);
        String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

        String targetUrl = "http://localhost:8080/api/yourpropertybuyer";

        try{
            JSONObject jsonObject = HTTPClient.sendGetRequest(targetUrl, userEmail);

            // System.out.println(jsonObject);

            if(jsonObject != null) {
                minimumBudgetBuyerUpdate.setText(jsonObject.optString("minBudget", ""));
                maximumBudgetBuyerUpdate.setText(jsonObject.optString("maxBudget", ""));

                JSONArray locations = jsonObject.optJSONArray("preferredLocations");
                if(locations != null) {
                    buyerPreferredLocationVBox.getChildren().clear();
                    for (int i = 0; i < locations.length(); i++) {
                        buyerPreferredNewLocations.add(locations.getString(i));
                        HBox locationPane = createLocationPaneInVBox(locations.getString(i), buyerPreferredLocationVBox);
                        buyerPreferredLocationVBox.getChildren().add(locationPane);
                    }
                    buyerPreferredLocationVBox.getChildren().add(updateBuyerProfileButton);
                }

                JSONArray propertyTypes = jsonObject.optJSONArray("preferredPropertyTypes");
                if(propertyTypes != null) {
                    for (int i = 0; i < propertyTypes.length(); i++) {
                        String propertyType = propertyTypes.getString(i);
                        switch (propertyType) {
                            case "Apartment":
                                buyerProfileApartmentUpdateChecked.setSelected(true);
                                break;
                            case "Houses":
                                buyerProfileHouseUpdateChecked.setSelected(true);
                                break;
                            case "Lands":
                                buyerProfileLandUpdateChecked.setSelected(true);
                                break;
                            case "Residential Units":
                                buyerProfileResidentialUnitUpdateChecked.setSelected(true);
                                break;
                            case "Commercial Units":
                                buyerProfileCommercialUnitUpdateChecked.setSelected(true);
                                break;
                            case "Business Places":
                                buyerProfileBusinessPlaceUpdateChecked.setSelected(true);
                                break;
                        }
                    }
                }

                updateBuyerProfile.setVisible(true);
                createBuyerProfile.setVisible(false);

            } else {
                updateBuyerProfile.setVisible(false);
                createBuyerProfile.setVisible(true);
            }
            
        } catch (RuntimeException | IOException e) {
            if (e.getMessage().contains("HTTP error code : 404")) {
                // If a 404 error occurs, show create profile pane instead
                updateBuyerProfile.setVisible(false);
                createBuyerProfile.setVisible(true);
            } else {
                // Handle other exceptions
                e.printStackTrace();
            }
        }

        manageBuyerProfile.setVisible(true);
        manageRenterProfile.setVisible(false);
        manageSellerProfile.setVisible(false);
    }


    public void onCreateNewSellerProfileClicked(ActionEvent actionEvent) {
        createSellerProfileFields.setVisible(true);

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(createSellerProfileFields);
        translateTransition.setDuration(Duration.seconds(0.5));
        translateTransition.setToY(0);
        translateTransition.play();
    }

    public void onCancelCreateSellerClicked(MouseEvent mouseEvent) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(createSellerProfileFields);
        translateTransition.setDuration(Duration.seconds(0.5));
        translateTransition.setToY(575);
        translateTransition.play();

        createSellerProfileFields.setVisible(false);
    }

    public void onCreateSellerProfileClicked(ActionEvent actionEvent) {
        String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

        String sellerLicense = sellerLicenseNumber.getText();

        // Collect owned property types
        List<String> ownedPropertyTypes = new ArrayList<>();
        if(sellerProfileApartmentChecked.isSelected()) ownedPropertyTypes.add("Apartment");
        if(sellerProfileHousesChecked.isSelected()) ownedPropertyTypes.add("Houses");
        if(sellerProfileLandsChecked.isSelected()) ownedPropertyTypes.add("Lands");
        if(sellerProfileResidentialUnitsChecked.isSelected()) ownedPropertyTypes.add("Residential Units");
        if(sellerProfileCommercialUnitsChecked.isSelected()) ownedPropertyTypes.add("Commercial Units");
        if(sellerProfileBusinessPlacesChecked.isSelected()) ownedPropertyTypes.add("Business Places");

        ArrayNode propertyTypesArray = objectMapper.createArrayNode();
        ownedPropertyTypes.forEach(propertyTypesArray::add);

        // Collect communication interests
        List<String> openedCommunication = new ArrayList<>();
        if(sellerOpenCommunicationWithBuyer.isSelected()) openedCommunication.add("Buyer");
        if(sellerOpenCommunicationWithRenter.isSelected()) openedCommunication.add("Renter");
        if(sellerOpenCommunicationWithAgent.isSelected()) openedCommunication.add("Agent");

        ArrayNode openedCommunicationArray = objectMapper.createArrayNode();
        openedCommunication.forEach(openedCommunicationArray::add);

        ObjectNode sellerProfileData = objectMapper.createObjectNode();
        sellerProfileData.put("email", userEmail);
        sellerProfileData.put("sellerLicense", sellerLicense);
        sellerProfileData.put("openCommunication", openedCommunicationArray);
        sellerProfileData.put("propertyTypes", propertyTypesArray);

        String jsonInputString = sellerProfileData.toString();

        String targetUrl = "http://localhost:8080/api/yourpropertyseller/register";

        try {
            Pair<Integer, String> pair = HTTPClient.sendPostRequest(targetUrl, jsonInputString);
            if(pair.getKey() == 200){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Create your seller profile");
                alert.setHeaderText(null);
                alert.setContentText(pair.getValue());
                alert.showAndWait();

                onManageSellerProfileOptionClicked();
            } else if(pair.getKey() == 409){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Something went wrong!");
                alert.setContentText("You already have an active renter profile!");

                alert.showAndWait();
            } else if(pair.getKey() == 500){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Something went wrong!");
                alert.setContentText("An error occurred!");

                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("An error occurred!");
            e.printStackTrace();
        }
    }

    public void onManageSellerProfileOptionClicked() {
        manageProfile.setVisible(true);
        String userEmail = YourPropertyUserSession.getInstance().getUserEmail();

        String targetUrl = "http://localhost:8080/api/yourpropertyseller";

        try{
            JSONObject jsonObject = HTTPClient.sendGetRequest(targetUrl, userEmail);

            // System.out.println(jsonObject);

            if(jsonObject != null) {
                sellerLicenseNumberShow.setText(jsonObject.optString("sellerLicense", ""));

                JSONArray propertyTypeArray = jsonObject.optJSONArray("sellerOwnedPropertyType");
                StringBuilder propertyTypes = new StringBuilder();
                for(int i = 0; i < propertyTypeArray.length(); i++){
                    propertyTypes.append(propertyTypeArray.getString(i));
                    if(i < propertyTypeArray.length()-1) propertyTypes.append(", ");
                }
                sellerPropertyTypesShow.setText(propertyTypes.toString());

                JSONArray communicationInterestArray = jsonObject.optJSONArray("sellerOpenedCommunictionUserType");
                StringBuilder communicationInterest = new StringBuilder();
                for(int i = 0; i < communicationInterestArray.length(); i++){
                    communicationInterest.append(communicationInterestArray.getString(i));
                    if(i < communicationInterestArray.length() - 1) communicationInterest.append(", ");
                }
                sellerCommunicationInterestShow.setText(communicationInterest.toString());

                updateSellerProfile.setVisible(true);
                createSellerProfile.setVisible(false);

            } else {
                updateSellerProfile.setVisible(false);
                createSellerProfile.setVisible(true);
            }
            
        } catch (RuntimeException | IOException e) {
            if (e.getMessage().contains("HTTP error code : 404")) {
                // If a 404 error occurs, show create profile pane instead
                updateSellerProfile.setVisible(false);
                createSellerProfile.setVisible(true);
            } else {
                // Handle other exceptions
                e.printStackTrace();
            }
        }

        manageBuyerProfile.setVisible(false);
        manageRenterProfile.setVisible(false);
        manageSellerProfile.setVisible(true);
    }

    public void onUpdateRenterProfileClicked(ActionEvent actionEvent) {

    }

    public void onUpdateSellerProfileClicked(ActionEvent actionEvent) {

    }

    public void onAddNewPropertyClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addPropertyPage.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add New Property");
        stage.setScene(scene);
        stage.show();
    }

    public void onMyPropertiesOptionClicked() throws IOException {
        dashboardLayer.setVisible(false);
        myPropertyLayer.setVisible(true);
        savedPropertyLayer.setVisible(false);
        profileSettingsLayer.setVisible(false);
        manageProfile.setVisible(false);
        propertyDetailsLayer.setVisible(false);
        editPropertyLayer.setVisible(false);

        // String targetUrl = "http://localhost:8080/api/properties/email";
        loadProperties();
    }

    private void loadProperties() throws IOException {
        String userEmail = YourPropertyUserSession.getInstance().getUserEmail();
        String targetUrl = "http://localhost:8080/api/properties/email";

        try {
            // Fetch properties from backend
            JSONArray properties = HTTPClient.sendGetListRequest(targetUrl, userEmail);

            // Populate the VBox
            propertyListContainer.getChildren().clear();
            for(int i = 0; i < properties.length(); i++){
                JSONObject property = properties.getJSONObject(i);
                // System.out.println(property);
                Pane propertyPane = createPropertyPane(property, "myProperty");
                propertyListContainer.getChildren().add(propertyPane);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Pane createPropertyPane(JSONObject property, String sourceContainer) {
        try {
            // Load the template FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/myPropertyTemplatePane.fxml"));
            Pane propertyPane = loader.load();

            propertyPane.setPrefHeight(209);
            propertyPane.setPrefWidth(705);
            propertyPane.setMinHeight(209);
            propertyPane.setMinWidth(705);

            Rectangle clip = new Rectangle((int) propertyPane.getPrefWidth(), (int) propertyPane.getPrefHeight());
            propertyPane.setClip(clip);


            // Dynamically update the components
            Label titleLabel = (Label) propertyPane.lookup("#titleMyProperty");
            if (titleLabel != null) {
                titleLabel.setText(property.getString("PropertyTitle"));
            }

            Label locationLabel = (Label) propertyPane.lookup("#myPropertyLocation");
            if (locationLabel != null) {
                if(sourceContainer.equals("myProperty")) {
                    locationLabel.setText(
                        property.getString("Street") + ", " +
                                property.getString("City") + ", " +
                                property.getString("State") + ", " +
                                property.getString("PostCode") + ", " +
                                property.getString("Country")
                )   ;
                } else {
                    locationLabel.setText(property.getString("PropertyLocation"));
                }                
            }

            Label priceLabel = (Label) propertyPane.lookup("#myPropertyPrice");
            if (priceLabel != null) {
                if(sourceContainer.equals("myProperty")) {
                    if ("For sale".equals(property.getString("OfferType"))) {
                        priceLabel.setText(property.getBigDecimal("SalePrice").toString());
                    } else if ("For rent".equals(property.getString("OfferType"))) {
                        priceLabel.setText(
                                property.getBigDecimal("RentPrice").toString() +
                                        " (" + property.getString("RentTerm") + ")"
                        );
                    }
                } else {
                    if ("For sale".equals(property.getString("OfferType"))) {
                        priceLabel.setText(property.getBigDecimal("PropertyPrice").toString());
                    } else if ("For rent".equals(property.getString("OfferType"))) {
                        priceLabel.setText(property.getString("PropertyPrice"));
                    }
                }
            }

            Label typeLabel = (Label) propertyPane.lookup("#myPropertyType");
            if (typeLabel != null) {
                typeLabel.setText(property.getString("PropertyType"));
            }

            Label offerTypeLabel = (Label) propertyPane.lookup("#myPropertyOfferType");
            if (offerTypeLabel != null) {
                offerTypeLabel.setText(property.getString("OfferType"));
            }

            ImageView imageView = (ImageView) propertyPane.lookup("#myPropertyDisplayImage");
            if (imageView != null) {
                JSONArray pictures = property.optJSONArray("picture");
                if (pictures != null && pictures.length() > 0) {
                    imageView.setImage(new Image(new File(pictures.getString(0)).toURI().toString()));
                }
            }

            Button editButton = (Button) propertyPane.lookup("#editPropertyButton");
            if (editButton != null) {
                if(sourceContainer.equals("savedProperty")) {
                    if(property.getBoolean("isOwner")) editButton.setVisible(true);
                    else editButton.setVisible(false);
                }
                editButton.setOnAction(_ -> {
                    try {
                        // Get the property details from the pane
                        Label title = (Label) propertyPane.lookup("#titleMyProperty");
                        Label location = (Label) propertyPane.lookup("#myPropertyLocation");

                        if (titleLabel != null && locationLabel != null) {
                            String titleString = title.getText();
                            String address = location.getText();
//                            String email = YourPropertyUserSession.getInstance().getUserEmail();

                            // Call the HTTPClient delete method
//                            JSONObject propertyJson = HTTPClient.sendGetListRequest("http://localhost:8080/api/properties/property", email, titleString, address);
                            Property propertyClass = HTTPClient.getProperty("http://localhost:8080/api/properties/property/get", titleString, address);

                            // System.out.println(propertyJson);
                            dashboardLayer.setVisible(false);
                            myPropertyLayer.setVisible(false);
                            savedPropertyLayer.setVisible(false);
                            profileSettingsLayer.setVisible(false);
                            manageProfile.setVisible(false);
                            propertyDetailsLayer.setVisible(false);
                            editPropertyLayer.setVisible(true);
                            addItemsInPropertyEditChoice();
                            loadCountryStateData();
                            ObservableList<String> countries = FXCollections.observableArrayList(countryStateMap.keySet());
                            propertyNewCountryBox.setItems(countries);
                            propertyNewCountryBox.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
                                if (newValue != null) {
                                    propertyNewStateBox.setItems(countryStateMap.get(newValue));
                                }
                            });

                            onEditPropertyClicked(propertyClass);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            Button deleteButton = (Button) propertyPane.lookup("#deletePropertyButton");
            if (deleteButton != null) {
                if(sourceContainer.equals("savedProperty")) {
                    deleteButton.setText("Remove");
                }
                deleteButton.setOnAction(_ -> {
                    try {
                        // Get the property details from the pane
                        Label title = (Label) propertyPane.lookup("#titleMyProperty");
                        Label location = (Label) propertyPane.lookup("#myPropertyLocation");

                        if (titleLabel != null && locationLabel != null) {
                            String titleString = title.getText();
                            String address = location.getText();
                            String email = YourPropertyUserSession.getInstance().getUserEmail();

                            // Call the HTTPClient delete method
                            if(sourceContainer.equals("myProperty")) {
                                HTTPClient.sendDeletePropertyRequest("http://localhost:8080/api/properties/delete", email, titleString, address);
                            } else {
                                HTTPClient.sendDeletePropertyRequest("http://localhost:8080/api/saved-properties/delete", email, titleString, address);
                            }

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Success");
                            if(sourceContainer.equals("myProperty")) {
                                alert.setHeaderText("Property Deleted");
                                alert.setContentText("The property has been successfully deleted.");
                            } else {
                                alert.setHeaderText("Property Removed");
                                alert.setContentText("The property has been successfully removed from saved properties.");
                            }
                            alert.showAndWait();

                            // Optionally remove the pane from the UI
                            ((VBox) propertyPane.getParent()).getChildren().remove(propertyPane);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Failed to Delete Property");
                        alert.setContentText("An error occurred while deleting the property: " + e.getMessage());
                        alert.showAndWait();
                    }
                });
            }

            Button previewButton = (Button) propertyPane.lookup("#previewPropertyButton");
            if (previewButton != null) {
                if(sourceContainer.equals("savedProperty")) {
                    previewButton.setText("View");
                }
                previewButton.setOnAction(_ -> {
                    try {
                        // Get the property details from the pane
                        Label title = (Label) propertyPane.lookup("#titleMyProperty");
                        Label location = (Label) propertyPane.lookup("#myPropertyLocation");

                        if (titleLabel != null && locationLabel != null) {
                            String titleString = title.getText();
                            String address = location.getText();
                            String email = YourPropertyUserSession.getInstance().getUserEmail();

                            // Call the HTTPClient getProperty method
                            JSONObject propertyJson = HTTPClient.sendGetListRequest("http://localhost:8080/api/properties/property", email, titleString, address);

//                            System.out.println(propertyJson);
                            dashboardLayer.setVisible(false);
                            myPropertyLayer.setVisible(false);
                            savedPropertyLayer.setVisible(false);
                            profileSettingsLayer.setVisible(false);
                            manageProfile.setVisible(false);
                            propertyDetailsLayer.setVisible(true);
                            editPropertyLayer.setVisible(false);

                            boolean isOwner = true;
                            if(property.has("isOwner") && !property.getBoolean("isOwner")) {
                                isOwner = false;
                            }

                            onPropertyPreviewClicked(propertyJson, isOwner);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            // Return the dynamically updated pane
            return propertyPane;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void onPropertyPreviewClicked(JSONObject propertyJson, boolean isOwner) {
        CurrentIndex = 0;
        JSONArray pictures = propertyJson.getJSONArray("picture");
        imagePaths.clear();
        for(int i = 0; i < pictures.length(); i++) {
            imagePaths.add(pictures.getString(i));
        }
        loadImages(CurrentIndex, imagePaths);

        String propertyType = propertyJson.getString("PropertyType");
        showPropertyFeaturesInPropertyPreviewPane(propertyType);
        if(propertyType.equals("Apartment")) {
            petPolicyShow.setText(propertyJson.getBoolean("petPolicy") ? "Yes" : "No");
            furniturePolicyShow.setText(propertyJson.getBoolean("furniturePolicy") ? "Furnished" : "Unfurnished");
            terracePreferrenceShow.setText(propertyJson.getBoolean("terracePolicy") ? "Yes" : "No");
            parkingAvailabilityShow.setText(propertyJson.getBoolean("parkingPolicy") ? "Covered" : "Uncovered");

            apartmentBedroomNumberShow.setText(String.valueOf(propertyJson.get("bedrooms")));
            apartmentBathroomNumberShow.setText(String.valueOf(propertyJson.get("bathrooms")));
            apartmentFloorNumberShow.setText(String.valueOf(propertyJson.get("floor")));
            apartmentTotalFloorNumberShow.setText(String.valueOf(propertyJson.get("totalFloor")));
            apartmentMaintenanceServicesShow.setText(propertyJson.getString("maintenanceServices"));
            apartmentBalconyAvailabilityShow.setText(propertyJson.getBoolean("balconyPolicy") ? "Yes" : "No");
        } else if(propertyType.equals("House")) {
            basementPreferrenceShow.setText(propertyJson.getString("basementAvailability"));
            JSONArray securityFeatures = propertyJson.getJSONArray("securityFeatures");
            StringBuilder securityFeature = new StringBuilder();
            for(int i = 0; i < securityFeatures.length(); i++){
                securityFeature.append(securityFeatures.get(i));
                if(i < securityFeatures.length()-1) securityFeature.append(", ");
            }
            securityFeaturesShow.setText(securityFeature.toString());
            garageAvailabilityShow.setText(propertyJson.getBoolean("garageAvailability") ? "Yes" : "No");

            houseBedroomNumberShow.setText(String.valueOf(propertyJson.get("bedrooms")));
            houseBathroomNumberShow.setText(String.valueOf(propertyJson.get("bathrooms")));
            houseFloorNumberShow.setText(String.valueOf(propertyJson.get("floor")));
            houseRoofTypeShow.setText(propertyJson.getString("roofType"));
            houseGardenShow.setText(propertyJson.getBoolean("gardenAvailability") ? "Yes" : "No");
            houseBalconyShow.setText(propertyJson.getBoolean("balconyAvailability") ? "Yes" : "No");
        } else if(propertyType.equals("Land")) {
            landTopographyShow.setText(propertyJson.getString("topography"));
            JSONArray utilities = propertyJson.getJSONArray("utilities");
            StringBuilder utility = new StringBuilder();
            for(int i = 0; i < utilities.length(); i++){
                utility.append(utilities.get(i));
                if(i < utilities.length()-1) utility.append(", ");
            }
            landUtilitiesShow.setText(utility.toString());
            landRoadAccessShow.setText(propertyJson.getString("roadAccess"));

            landTypeShow.setText(propertyJson.getString("type"));
            if(propertyJson.has("length") && propertyJson.has("width")) {
                landPlotSizeShow.setText(propertyJson.getBigDecimal("length").toString() + " x " + propertyJson.getBigDecimal("width").toString());
            } else if(propertyJson.has("area")) {
                landPlotSizeShow.setText((String) propertyJson.get("area"));
            }
            landBorderShow.setText(propertyJson.getString("border"));
            landPreviousDevelopmentShow.setText(propertyJson.getBoolean("previousDevelopment") ? "Yes" : "No");
            JSONArray nearbyInfrastructures = propertyJson.getJSONArray("nearbyInfrastructure");
            StringBuilder nearbyInfrastructure = new StringBuilder();
            for(int i = 0; i < nearbyInfrastructures.length(); i++){
                nearbyInfrastructure.append(nearbyInfrastructures.get(i));
                if(i < nearbyInfrastructures.length()-1) nearbyInfrastructure.append(", ");
            }
            landNearbyInfrastructureShow.setText(nearbyInfrastructure.toString());
        } else if(propertyType.equals("Residential unit")) {
            JSONArray amenities = propertyJson.getJSONArray("amenities");
            StringBuilder amenity = new StringBuilder();
            for(int i = 0; i < amenities.length(); i++){
                amenity.append(amenities.get(i));
                if(i < amenities.length()-1) amenity.append(", ");
            }
            residentialUnitAmenitiesShow.setText(amenity.toString());
            residentialUnitStorageShow.setText(propertyJson.getString("storageType"));
            residentialUnitNoiseInsulationShow.setText(propertyJson.getBoolean("noiseInsulation")? "Insulated" : "Exposed");

            residentialUnitConfigurationShow.setText(String.valueOf(propertyJson.get("configuration")) + " BHK");
            JSONArray facilities = propertyJson.getJSONArray("sharedFacilities");
            StringBuilder sharedFacility = new StringBuilder();
            for(int i = 0; i < facilities.length(); i++){
                sharedFacility.append(facilities.get(i));
                if(i < facilities.length()-1) sharedFacility.append(", ");
            }
            residentialUnitSharedFacilitiesShow.setText(sharedFacility.toString());
            residentialUnitOrientationShow.setText(propertyJson.getString("orientation"));
            residentialUnitNaturalLightShow.setText(propertyJson.getString("naturalLight"));
        } else if(propertyType.equals("Commercial unit")) {
            JSONArray purposes = propertyJson.getJSONArray("purpose");
            StringBuilder purpose = new StringBuilder();
            for(int i = 0; i < purposes.length(); i++){
                purpose.append(purposes.get(i));
                if(i < purposes.length()-1) purpose.append(", ");
            }
            commercialUnitPurposesShow.setText(purpose.toString());
            commercialUnitFloorLayoutShow.setText(propertyJson.getString("floorLayout"));
            JSONArray accessibilitiyFeatures = propertyJson.getJSONArray("accessibilityFeatures");
            StringBuilder accessibilityFeature = new StringBuilder();
            for(int i = 0; i < accessibilitiyFeatures.length(); i++){
                accessibilityFeature.append(accessibilitiyFeatures.get(i));
                if(i < accessibilitiyFeatures.length()-1) accessibilityFeature.append(", ");
            }
            commercialUnitAccessibilitiesShow.setText(accessibilityFeature.toString());

            commercialUnitFacadeTypeShow.setText(propertyJson.getString("facade"));
            JSONArray nearbyAttractions = propertyJson.getJSONArray("nearbyAttractions");
            StringBuilder nearbyAttraction = new StringBuilder();
            for(int i = 0; i < nearbyAttractions.length(); i++){
                nearbyAttraction.append(nearbyAttractions.get(i));
                if(i < nearbyAttractions.length()-1) nearbyAttraction.append(", ");
            }
            commercialUnitNearbyAttractionsShow.setText(nearbyAttraction.toString());
            JSONArray fireSafeties = propertyJson.getJSONArray("fireSafety");
            StringBuilder fireSafety = new StringBuilder();
            for(int i = 0; i < fireSafeties.length(); i++){
                fireSafety.append(fireSafeties.get(i));
                if(i < fireSafeties.length()-1) fireSafety.append(", ");
            }
            commercialUnitFireSafetyShow.setText(fireSafety.toString());
            commercialUnitAirConditioningShow.setText(propertyJson.getString("airCondition"));
        } else if(propertyType.equals("Business place")) {
            JSONArray purposes = propertyJson.getJSONArray("purpose");
            StringBuilder purpose = new StringBuilder();
            for(int i = 0; i < purposes.length(); i++){
                purpose.append(purposes.get(i));
                if(i < purposes.length()-1) purpose.append(", ");
            }
            businessPlacePurposeShow.setText(purpose.toString());
            businessPlaceVentilationFaciltiyShow.setText(propertyJson.getString("ventilation"));
            JSONArray powerSupplies = propertyJson.getJSONArray("powerSupply");
            StringBuilder powerSupply = new StringBuilder();
            for(int i = 0; i < powerSupplies.length(); i++){
                powerSupply.append(powerSupplies.get(i));
                if(i < powerSupplies.length()-1) powerSupply.append(", ");
            }
            businessPlacePowerSupplyShow.setText(powerSupply.toString());

            businessPlaceLightingSetupShow.setText(propertyJson.getString("lightingSetup"));
            businessPlaceWaitingAreaShow.setText(propertyJson.getString("waitingArea"));
            businessPlaceVisibilityFromRoadShow.setText(propertyJson.getString("visibilityFromRoad"));
            businessPlaceInsuranceDetailsShow.setText(propertyJson.getString("insuranceDetails"));
        }

        propertyTitleShow.setText(propertyJson.getString("PropertyTitle"));
        if(propertyJson.getString("OfferType").equals("For sale")) {
            showPropertyPrice.setText(propertyJson.getBigDecimal("SalePrice").toString());
            propertyAvailableDatePane.setVisible(false);
        } else if(propertyJson.getString("OfferType").equals("For rent")) {
            showPropertyPrice.setText(propertyJson.getBigDecimal("RentPrice").toString() + " (" + propertyJson.getString("RentTerm") + ")");
            propertyAvailableForRentFromDateShow.setText(propertyJson.getString("RentAvailableDate"));
            propertyAvailableDatePane.setVisible(true);
        }
        showPropertyLocation.setText(propertyJson.getString("Street") + ", " + propertyJson.getString("City") + ", " + propertyJson.getString("State") + ", " + propertyJson.getString("PostCode") + ", " + propertyJson.getString("Country"));
        showPropertyDescription.setText(propertyJson.getString("PropertyDescription"));

        editPropertyFromPropertyDetailsButton.setVisible(isOwner);
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

    private void showPropertyFeaturesInEditPropertyPane(String propertyType) {
        if(propertyType.equals("Apartment")){
            newApartmentFeatures.setVisible(true);
            newSpecificFeaturesApartment.setVisible(true);
        } else{
            newApartmentFeatures.setVisible(false);
            newSpecificFeaturesApartment.setVisible(false);
        }

        if(propertyType.equals("House")){
            newHouseFeatures.setVisible(true);
            newSpecificFeaturesHouse.setVisible(true);
        } else{
            newHouseFeatures.setVisible(false);
            newSpecificFeaturesHouse.setVisible(false);
        }

        if(propertyType.equals("Land")){
            newLandFeatures.setVisible(true);
            newSpecificFeaturesLand.setVisible(true);
        } else{
            newLandFeatures.setVisible(false);
            newSpecificFeaturesLand.setVisible(false);
        }

        if(propertyType.equals("Residential unit")){
            newResidentialUnitFeatures.setVisible(true);
            newSpecificFeaturesResidentialUnit.setVisible(true);
        } else{
            newResidentialUnitFeatures.setVisible(false);
            newSpecificFeaturesResidentialUnit.setVisible(false);
        }

        if(propertyType.equals("Commercial unit")){
            newCommercialUnitFeatures.setVisible(true);
            newSpecificFeaturesCommercialUnit.setVisible(true);
        } else{
            newCommercialUnitFeatures.setVisible(false);
            newSpecificFeaturesCommercialUnit.setVisible(false);
        }

        if(propertyType.equals("Business place")){
            newBusinessPlacesFeatures.setVisible(true);
            newSpecificFeaturesBusinessPlace.setVisible(true);
        } else{
            newBusinessPlacesFeatures.setVisible(false);
            newSpecificFeaturesBusinessPlace.setVisible(false);
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

    public void onEditPropertyClicked(Property property) {
        propertyIdInEditProperty.setText(property.getPropertyId().toString());
        newPropertyTitle.setText(property.getPropertyTitle());
        newPropertyDescription.setText(property.getPropertyDescription());
        if(property.getOfferType().equals("For sale")) {
            newPropertySellPricePane.setVisible(true);
            newPropertyRentTermAndPriceAndDatePane.setVisible(false);
            newPropertySalePrice.setText(property.getSalePrice().toString());
        } else if(property.getOfferType().equals("For rent")) {
            newPropertySellPricePane.setVisible(false);
            newPropertyRentTermAndPriceAndDatePane.setVisible(true);
            newPropertyRentPrice.setText(property.getRentPrice().toString());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate rentAvailableDate = LocalDate.parse(property.getRentAvailableDate(), formatter);
            newPropertyAvailableForRentFromDate.setValue(rentAvailableDate);
            newPropertyRentTermBox.setValue(property.getRentTerm());
        }
        propertyNewCountryBox.setValue(property.getCountry());
        propertyNewStateBox.setValue(property.getState());
        propertyNewCity.setText(property.getCity());
        propertyNewPostalCode.setText(property.getPostalCode());
        propertyNewStreetAddress.setText(property.getStreet());

        if(property instanceof Apartment apartment) {
            property.setPropertyType("Apartment");
            // Apartment key features
            if(apartment.getPetPolicy()) newPetPolicyYes.setSelected(true);
            else newPetPolicyNo.setSelected(true);

            if(apartment.getFurniturePolicy()) newfurnishedPolicyYes.setSelected(true);
            else newfurnishedPolicyNo.setSelected(true);

            if(apartment.getTerracePolicy()) newterraceAvailabilityYes.setSelected(true);
            else newterraceAvailabilityNo.setSelected(true);

            if(apartment.getParkingPolicy()) newparkingAvailabilityYes.setSelected(true);
            else newparkingAvailabilityNo.setSelected(true);

            // Apartment specific features
            numberOfBedroomsInApartment.setText(String.valueOf(apartment.getBedrooms()));
            numberOfBathroomsInApartment.setText(String.valueOf(apartment.getBathrooms()));
            floorNumberOfApartment.setText(String.valueOf(apartment.getFloor()));
            totalFloorsOfBuildingInApartment.setText(String.valueOf(apartment.getTotalFloor()));
            maintenanceServicesApartment.setText(apartment.getMaintenanceServices());
            if(apartment.getBalconyPolicy()) apartmentBalconyYes.setSelected(true);
            else apartmentBalconyNo.setSelected(true);
        } else if(property instanceof House house) {
            property.setPropertyType("House");
            // House key features
            if(house.getBasementAvailability().equals("Finished")) newbasementPreferenceYes.setSelected(true);
            else if(house.getBasementAvailability().equals("Unfinished")) newbasementPreferrenceNo.setSelected(true);

            if(house.getSecurityFeatures().contains("Security Sytem")) newSecuritySystem.setSelected(true);
            if(house.getSecurityFeatures().contains("Gated Entry")) newGatedEntry.setSelected(true);
            if(house.getSecurityFeatures().contains("Cameras")) newCameras.setSelected(true);

            if(house.getGarageAvailability()) newGaragePreferenceYes.setSelected(true);
            else newGaragePreferrenceNo.setSelected(true);

            // House specific features
            numberOfBedroomsInHouse.setText(String.valueOf(house.getBedrooms()));
            numberOfBathroomsInHouse.setText(String.valueOf(house.getBathrooms()));
            numberOfFloorsInHouse.setText(String.valueOf(house.getFloor()));

            switch (house.getRoofType()) {
                case "Flat" -> houseRoofTypeFlat.setSelected(true);
                case "Sloped" -> houseRoofTypeSloped.setSelected(true);
                case "Material" -> houseRoofTypeMaterial.setSelected(true);
            }

            if(house.getGarageAvailability()) houseGardenYes.setSelected(true);
            else houseGardenNo.setSelected(true);

            if(house.getBalconyAvailability()) houseBalconyYes.setSelected(true);
            else houseBalconyNo.setSelected(true);
        } else if(property instanceof Land land) {
            property.setPropertyType("Land");
            // Land key features
            switch (land.getTopography()) {
                case "Flat" -> newFlatLand.setSelected(true);
                case "Sloped" -> newSlopedLand.setSelected(true);
                case "Hilly" -> newHillyLand.setSelected(true);
            }

            if(land.getUtilities().contains("Electricity")) newElectricityUtilityLand.setSelected(true);
            if(land.getUtilities().contains("Water")) newWaterUtilityLand.setSelected(true);
            if(land.getUtilities().contains("Sewage")) newSewageUtilityLand.setSelected(true);

            if(land.getRoadAccess().equals("Paved")) newPavedRoadAccessLand.setSelected(true);
            else if(land.getRoadAccess().equals("Unpaved")) newUnpavedRoadAccessLand.setSelected(true);

            // Land specific features
            switch (land.getType()) {
                case "Agricultural" -> landTypeAgricultural.setSelected(true);
                case "Residential" -> landTypeResidential.setSelected(true);
                case "Commercial" -> landTypeCommercial.setSelected(true);
                case "Industrial" -> landTypeIndustrial.setSelected(true);
            }

            if(toggleBetweenDimensionAndAreaLand.isSelected()) {
                landArea.setText(land.getArea().toString());
            } else {
                landLength.setText(land.getLength().toString());
                landWidth.setText(land.getWidth().toString());
            }

            switch (land.getBorder()) {
                case "Fenced" -> landBorderFenced.setSelected(true);
                case "Unfenced" -> landBorderUnfenced.setSelected(true);
                case "Boundary Markers" -> landBorderBoundaryMarkers.setSelected(true);
            }

            if(land.getPreviousDevelopment()) landPreviousDevelopmentYes.setSelected(true);
            else landPreviousDevelopmentNo.setSelected(true);

            if(land.getNearbyInfrastructure().contains("Roads")) landNearbyInfrastructureRoads.setSelected(true);
            if(land.getNearbyInfrastructure().contains("Schools")) landNearbyInfrastructureSchools.setSelected(true);
            if(land.getNearbyInfrastructure().contains("Hospitals")) landNearbyInfrastructureHospitals.setSelected(true);
        } else if(property instanceof ResidentialUnit residentialUnit) {
            property.setPropertyType("Residential Unit");
            // Residential Unit key features
            if(residentialUnit.getAmenities().contains("Pool")) newPoolAmenitiyResidentialUnit.setSelected(true);
            if(residentialUnit.getAmenities().contains("Gym")) newGymAmenityResidentialUnit.setSelected(true);
            if(residentialUnit.getAmenities().contains("Kid's Playground")) newPlaygroundAmenityResidentialUnit.setSelected(true);

            switch (residentialUnit.getStorageType()) {
                case "Closet" -> newClosetStorageResidentialUnit.setSelected(true);
                case "Basement" -> newBasementStorageResidentialUnit.setSelected(true);
                case "Built-In" -> newBuiltInStorageResidentialUnit.setSelected(true);
            }

            if(residentialUnit.getNoiseInsulation()) newNoiseInsulatedResidentialUnit.setSelected(true);
            else newNoiseExposeResidentialUnit.setSelected(true);

            // Residential Unit specific features
            bhkResidentialUnit.getValueFactory().setValue(residentialUnit.getConfiguration());
            if(residentialUnit.getSharedFacilities().contains("Laundry")) residentialUnitLaundry.setSelected(true);
            if(residentialUnit.getSharedFacilities().contains("Garbage Disposal")) residentialUnitGarbageDisposal.setSelected(true);
            if(residentialUnit.getSharedFacilities().contains("Internet")) residentialUnitInternet.setSelected(true);
            residentialUnitOrientation.setText(residentialUnit.getOrientation());
            switch (residentialUnit.getNaturalLight()) {
                case "Low" -> residentialUnitNaturalLightLow.setSelected(true);
                case "Medium" -> residentialUnitNaturalLightMedium.setSelected(true);
                case "High" -> residentialUnitNaturalLightHigh.setSelected(true);
            }
        } else if(property instanceof CommercialUnit commercialUnit) {
            property.setPropertyType("Commercial Unit");
            // Commercial Unit key features
            if(commercialUnit.getPurpose().contains("Retail")) newRetailPurposeCommercialUnit.setSelected(true);
            if(commercialUnit.getPurpose().contains("Office")) newOfficePurposeCommercialUnit.setSelected(true);
            if(commercialUnit.getPurpose().contains("Medical")) newMedicalPurposeCommercialUnit.setSelected(true);
            if(commercialUnit.getPurpose().contains("Restaurant")) newRestaurantPurposeCommercialUnit.setSelected(true);

            switch (commercialUnit.getFloorLayout()) {
                case "Open Plan" -> newOpenPlanFloorLayoutCommercialUnit.setSelected(true);
                case "Modular" -> newModularFloorLayoutCommercialUnit.setSelected(true);
                case "Separate" -> newSeparateFloorLayoutCommercialUnit.setSelected(true);
            }

            if(commercialUnit.getAccessibilityFeatures().contains("Elevator")) newElevatorCommercialUnit.setSelected(true);
            if(commercialUnit.getAccessibilityFeatures().contains("Braille Signage")) newBrailleSignageCommercialUnit.setSelected(true);
            if(commercialUnit.getAccessibilityFeatures().contains("Accessible Washroom")) newAccessibleWashroomCommercialUnit.setSelected(true);

            // Commercial Unit specific features
            switch (commercialUnit.getFacade()) {
                case "Glass" -> commercialUnitFacadeGlass.setSelected(true);
                case "Concrete" -> commercialUnitFacadeConcrete.setSelected(true);
                case "Metal" -> commercialUnitFacadeMetal.setSelected(true);
            }

            if(commercialUnit.getNearbyAttractions().contains("Malls")) commercialUnitNearbyAttractionMalls.setSelected(true);
            if(commercialUnit.getNearbyAttractions().contains("Schools")) commercialUnitNearbyAttractionSchools.setSelected(true);
            if(commercialUnit.getNearbyAttractions().contains("Parks")) commercialUnitNearbyAttractionParks.setSelected(true);
            if(commercialUnit.getNearbyAttractions().contains("Restaurants")) commercialUnitNearbyAttractionRestaurants.setSelected(true);

            if(commercialUnit.getFireSafety().contains("Fire Alarm")) commercialUnitFireAlarm.setSelected(true);
            if(commercialUnit.getFireSafety().contains("Fire Extinguisher")) commercialUnitFireExtinguisher.setSelected(true);
            if(commercialUnit.getFireSafety().contains("Emergency Exit")) commercialUnitEmergencyExit.setSelected(true);

            switch (commercialUnit.getAirCondition()) {
                case "Central" -> commercialUnitAirConditioningCentral.setSelected(true);
                case "Individual" -> commercialUnitAirConditioningIndividual.setSelected(true);
                case "None" -> commercialUnitAirConditioningNone.setSelected(true);
            }
        } else if(property instanceof BusinessPlace businessPlace) {
            property.setPropertyType("Business Place");
            // Business Place key features
            if(businessPlace.getPurpose().contains("Store")) newStorePurposeBusinessPlace.setSelected(true);
            if(businessPlace.getPurpose().contains("Garage")) newGaragePurposeBusinessPlace.setSelected(true);

            switch (businessPlace.getVentilation()) {
                case "Standard" -> newStandardVentilationBusinessPlace.setSelected(true);
                case "High Powered Fans" -> newHighPoweredFansVentilationBusinessPlace.setSelected(true);
                case "Air Filtration" -> newAirFiltrationVentilationBusinessPlace.setSelected(true);
            }

            if(businessPlace.getPowerSupply().contains("Standard")) newStandardPowerSupplyBusinessPlace.setSelected(true);
            if(businessPlace.getPowerSupply().contains("High Voltage")) newHighVoltagePowerBusinessPlace.setSelected(true);
            if(businessPlace.getPowerSupply().contains("Backup Generator")) newGeneratorPowerSupplyBusinessPlace.setSelected(true);

            // Business Place specific features
            switch (businessPlace.getLightingSetup()) {
                case "Ambient" -> businessPlaceAmbientLighting.setSelected(true);
                case "Task" -> businessPlaceTaskLighting.setSelected(true);
                case "Accent" -> businessPlaceAccentLighting.setSelected(true);
            }

            switch (businessPlace.getWaitingArea()) {
                case "Dedicated" -> businessPlaceDedicatedWaitingArea.setSelected(true);
                case "Seating Space" -> businessPlaceSeatingSpaceWaitingArea.setSelected(true);
                case "None" -> businessPlaceNoWaitingArea.setSelected(true);
            }

            switch (businessPlace.getVisibilityFromRoad()) {
                case "High" -> businessPlaceHighVisibility.setSelected(true);
                case "Medium" -> businessPlaceMediumVisibility.setSelected(true);
                case "Low" -> businessPlaceLowVisibility.setSelected(true);
            }

            businessPlaceInsuranceDetails.setText(businessPlace.getInsuranceDetails());
        }
        showPropertyFeaturesInEditPropertyPane(property.getPropertyType());

        populatePicturesFlowPaneInEditProperty(property.getPicture());
    }

    private void populatePicturesFlowPaneInEditProperty(List<String> pictures) {
        propertyImagesEditFlowPane.getChildren().clear();
        for(int i = 0; i < pictures.size(); i++) {
            String picture = pictures.get(i);
            Pane picturePane = createPicturePaneInEditProperty(picture);
            propertyImagesEditFlowPane.getChildren().add(picturePane);
        }
        propertyImagesEditFlowPane.getChildren().add(addNewPropertyImageButtonPane);
    }

    public void onAddNewPropertyImageButtonClicked(ActionEvent actionEvent) {
        propertyImagesEditFlowPane.getChildren().remove(addNewPropertyImageButtonPane);

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        // Assuming 'selectImageButton' is in a scene with a Stage
        Stage stage = (Stage) propertyImagesEditFlowPane.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if(selectedFile != null){
            Pane picturePane = createPicturePaneInEditProperty(selectedFile.getAbsolutePath());
            propertyImagesEditFlowPane.getChildren().add(picturePane);
        }

        propertyImagesEditFlowPane.getChildren().add(addNewPropertyImageButtonPane);
    }

    private Pane createPicturePaneInEditProperty(String picture) {
        try{
            // Load the template FXML
//            System.out.println(picture);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/picturePaneInEditPropertyTemplate.fxml"));
            Pane picturePane = loader.load();

            picturePane.setPrefHeight(150);
            picturePane.setPrefWidth(150);
            picturePane.setMinHeight(150);
            picturePane.setMinWidth(150);

            Rectangle clip = new Rectangle((int) picturePane.getPrefWidth(), (int) picturePane.getPrefHeight());
            picturePane.setClip(clip);

            ImageView pictureImage = (ImageView) picturePane.lookup("#picture");
            if(pictureImage != null) {
                pictureImage.setImage(new Image(new File(picture).toURI().toString()));
            }

            Button editImageButton = (Button) picturePane.lookup("#editImageButton");
            if(editImageButton != null) {
                editImageButton.setOnAction(_ -> {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));

                    // Assuming 'selectImageButton' is in a scene with a Stage
                    Stage stage = (Stage) propertyImagesEditFlowPane.getScene().getWindow();
                    File selectedFile = fileChooser.showOpenDialog(stage);

                    if(selectedFile != null){
                        pictureImage.setImage(new Image(selectedFile.toURI().toString()));
//                    selectedProfilePicture = selectedFile;
//                    chosenFilePath.setText(selectedFile.getName());
                    }
                });
            }

            Button removeImageButton = (Button) picturePane.lookup("#removeImageButton");
            if(removeImageButton != null) {
                removeImageButton.setOnAction(_ -> {
                    propertyImagesEditFlowPane.getChildren().remove(picturePane);
                });
            }

            return picturePane;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onSavedPropertiesOptionClicked() throws IOException {
        dashboardLayer.setVisible(false);
        myPropertyLayer.setVisible(false);
        savedPropertyLayer.setVisible(true);
        profileSettingsLayer.setVisible(false);
        manageProfile.setVisible(false);
        propertyDetailsLayer.setVisible(false);
        editPropertyLayer.setVisible(false);

        // String targetUrl = "http://localhost:8080/api/saved-properties/email";
        loadSavedProperties();
    }

    private void loadSavedProperties() throws IOException {
        String userEmail = YourPropertyUserSession.getInstance().getUserEmail();
        String targetUrl = "http://localhost:8080/api/saved-properties/email";

        try {
            // Fetch properties from backend
            JSONArray properties = HTTPClient.sendGetListRequest(targetUrl, userEmail);

            // for(int i = 0; i < properties.length(); i++){
            //     JSONObject savedProperty = properties.getJSONObject(i);
            //     JSONObject property = savedProperty.getJSONObject("property");
            //     System.out.println(property);
            // }

            // Populate the VBox
            savedPropertyListContainer.getChildren().clear();
            for(int i = 0; i < properties.length(); i++){
                JSONObject property = properties.getJSONObject(i);
//                System.out.println(property);
                Pane propertyPane = createPropertyPane(property, "savedProperty");
                savedPropertyListContainer.getChildren().add(propertyPane);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void onEditPropertyFromPropertyDetailsClicked(ActionEvent actionEvent) throws IOException {
        Node parentPane = ((Node) actionEvent.getSource()).getParent();
        Label titleLabel = (Label) parentPane.lookup("#propertyTitleShow");
        String title = titleLabel.getText();
        Label addressLabel = (Label) parentPane.lookup("#showPropertyLocation");
        String address = addressLabel.getText();

        Property propertyClass = HTTPClient.getProperty("http://localhost:8080/api/properties/property/get", title, address);

        // System.out.println(propertyJson);
        dashboardLayer.setVisible(false);
        myPropertyLayer.setVisible(false);
        savedPropertyLayer.setVisible(false);
        profileSettingsLayer.setVisible(false);
        manageProfile.setVisible(false);
        propertyDetailsLayer.setVisible(false);
        editPropertyLayer.setVisible(true);
        addItemsInPropertyEditChoice();
        loadCountryStateData();
        ObservableList<String> countries = FXCollections.observableArrayList(countryStateMap.keySet());
        propertyNewCountryBox.setItems(countries);
        propertyNewCountryBox.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
            if (newValue != null) {
                propertyNewStateBox.setItems(countryStateMap.get(newValue));
            }
        });

        onEditPropertyClicked(propertyClass);
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

    public void onSubmitEditPropertyButtonClicked(ActionEvent actionEvent) throws IOException {
        long propertyId = Long.parseLong(propertyIdInEditProperty.getText());
        String propertyTargetUrl = "http://localhost:8080/api/properties";
        Property property = HTTPClient.getPropertyById(propertyTargetUrl, propertyId);

        Map<String, Object> propertyData = new HashMap<>();
        propertyData.put("propertyId", property.getPropertyId());
        propertyData.put("propertyTitle", newPropertyTitle.getText());
        propertyData.put("propertyDescription", newPropertyDescription.getText());
        propertyData.put("offerType", property.getOfferType());
        propertyData.put("propertyType", property.getPropertyType());
        if (property.getOfferType().equals("For sale")) {
            propertyData.put("salePrice", new BigDecimal(newPropertySalePrice.getText()));
        } else if (property.getOfferType().equals("For rent")) {
            propertyData.put("rentPrice", new BigDecimal(newPropertyRentPrice.getText()));
            propertyData.put("rentAvailableDate", newPropertyAvailableForRentFromDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            propertyData.put("rentTerm", newPropertyRentTermBox.getValue());
        }
        propertyData.put("country", propertyNewCountryBox.getSelectionModel().getSelectedItem());
        propertyData.put("state", propertyNewStateBox.getSelectionModel().getSelectedItem());
        propertyData.put("city", propertyNewCity.getText());
        propertyData.put("postalCode", propertyNewPostalCode.getText());
        propertyData.put("street", propertyNewStreetAddress.getText());

        List<String> pictures = new ArrayList<>();
        for (Node node : propertyImagesEditFlowPane.getChildren()) {
            if (node instanceof Pane) {
                ImageView imageView = (ImageView) node.lookup("#picture");
                if (imageView != null && imageView.getImage() != null) {
                    // Remove the "file:" prefix
                    String urlEncodedPath = imageView.getImage().getUrl().substring(6);

                    // Decode the URL to handle spaces (%20)
                    String decodedPath = URLDecoder.decode(urlEncodedPath, StandardCharsets.UTF_8);

                    // Normalize the path to use the system-specific format
                    String normalizedPath = Paths.get(decodedPath).normalize().toString();

                    // Add to the list
                    pictures.add(normalizedPath);
                }
            }
        }
        propertyData.put("picture", pictures);

        switch (property.getPropertyType()) {
            case "Apartment" -> {
                Map<String, Object> apartmentFeatures = new HashMap<>();
                apartmentFeatures.put("petPolicy", newPetPolicyYes.isSelected());
                apartmentFeatures.put("furniturePolicy", newfurnishedPolicyYes.isSelected());
                apartmentFeatures.put("terracePolicy", newterraceAvailabilityYes.isSelected());
                apartmentFeatures.put("parkingPolicy", newparkingAvailabilityYes.isSelected());
                apartmentFeatures.put("bedrooms", Integer.parseInt(numberOfBedroomsInApartment.getText()));
                apartmentFeatures.put("bathrooms", Integer.parseInt(numberOfBathroomsInApartment.getText()));
                apartmentFeatures.put("floor", Integer.parseInt(floorNumberOfApartment.getText()));
                apartmentFeatures.put("totalFloor", Integer.parseInt(totalFloorsOfBuildingInApartment.getText()));
                apartmentFeatures.put("maintenanceServices", maintenanceServicesApartment.getText());
                apartmentFeatures.put("balconyPolicy", apartmentBalconyYes.isSelected());
                propertyData.put("apartmentFeatures", apartmentFeatures);
            }
            case "House" -> {
                Map<String, Object> houseFeatures = new HashMap<>();
                houseFeatures.put("basementAvailability", newbasementPreferenceYes.isSelected() ? "Finished" : "Unfinished");
                List<String> securityFeatures = new ArrayList<>();
                if (newSecuritySystem.isSelected()) securityFeatures.add("Security System");
                if (newGatedEntry.isSelected()) securityFeatures.add("Gated Entry");
                if (newCameras.isSelected()) securityFeatures.add("Cameras");
                houseFeatures.put("securityFeatures", securityFeatures);
                houseFeatures.put("garageAvailability", newGaragePreferenceYes.isSelected());
                houseFeatures.put("bedrooms", Integer.parseInt(numberOfBedroomsInHouse.getText()));
                houseFeatures.put("bathrooms", Integer.parseInt(numberOfBathroomsInHouse.getText()));
                houseFeatures.put("floor", Integer.parseInt(numberOfFloorsInHouse.getText()));
                String roofType = houseRoofTypeFlat.isSelected() ? "Flat" : houseRoofTypeSloped.isSelected() ? "Sloped" : "Material";
                houseFeatures.put("roofType", roofType);
                houseFeatures.put("gardenAvailability", houseGardenYes.isSelected());
                houseFeatures.put("balconyAvailability", houseBalconyYes.isSelected());
                propertyData.put("houseFeatures", houseFeatures);
            }
            case "Land" -> {
                Map<String, Object> landFeatures = new HashMap<>();
                landFeatures.put("topography", newFlatLand.isSelected() ? "Flat" : newSlopedLand.isSelected() ? "Sloped" : "Hilly");
                List<String> utilities = new ArrayList<>();
                if (newElectricityUtilityLand.isSelected()) utilities.add("Electricity");
                if (newWaterUtilityLand.isSelected()) utilities.add("Water");
                if (newSewageUtilityLand.isSelected()) utilities.add("Sewage");
                landFeatures.put("utilities", utilities);
                landFeatures.put("roadAccess", newPavedRoadAccessLand.isSelected() ? "Paved" : "Unpaved");
                String type = landTypeAgricultural.isSelected() ? "Agricultural" : landTypeResidential.isSelected() ? "Residential" : landTypeCommercial.isSelected() ? "Commercial" : "Industrial";
                landFeatures.put("type", type);
                if (toggleBetweenDimensionAndAreaLand.isSelected()) {
                    landFeatures.put("area", new BigDecimal(landArea.getText()));
                } else {
                    landFeatures.put("length", new BigDecimal(landLength.getText()));
                    landFeatures.put("width", new BigDecimal(landWidth.getText()));
                }
                String border = landBorderFenced.isSelected() ? "Fenced" : landBorderUnfenced.isSelected() ? "Unfenced" : "Boundary Markers";
                landFeatures.put("border", border);
                landFeatures.put("previousDevelopment", landPreviousDevelopmentYes.isSelected());
                List<String> nearbyInfrastructure = new ArrayList<>();
                if (landNearbyInfrastructureRoads.isSelected()) nearbyInfrastructure.add("Roads");
                if (landNearbyInfrastructureSchools.isSelected()) nearbyInfrastructure.add("Schools");
                if (landNearbyInfrastructureHospitals.isSelected()) nearbyInfrastructure.add("Hospitals");
                landFeatures.put("nearbyInfrastructure", nearbyInfrastructure);
                propertyData.put("landFeatures", landFeatures);
            }
            case "Residential Unit" -> {
                Map<String, Object> residentialUnitFeatures = new HashMap<>();
                List<String> amenities = new ArrayList<>();
                if (newPoolAmenitiyResidentialUnit.isSelected()) amenities.add("Pool");
                if (newGymAmenityResidentialUnit.isSelected()) amenities.add("Gym");
                if (newPlaygroundAmenityResidentialUnit.isSelected()) amenities.add("Kid's Playground");
                residentialUnitFeatures.put("amenities", amenities);
                String storageType = newClosetStorageResidentialUnit.isSelected() ? "Closet" : newBasementStorageResidentialUnit.isSelected() ? "Basement" : "Built-In";
                residentialUnitFeatures.put("storageType", storageType);
                residentialUnitFeatures.put("noiseInsulation", newNoiseInsulatedResidentialUnit.isSelected());
                residentialUnitFeatures.put("configuration", bhkResidentialUnit.getValue());
                List<String> sharedFacilities = new ArrayList<>();
                if (residentialUnitLaundry.isSelected()) sharedFacilities.add("Laundry");
                if (residentialUnitGarbageDisposal.isSelected()) sharedFacilities.add("Garbage Disposal");
                if (residentialUnitInternet.isSelected()) sharedFacilities.add("Internet");
                residentialUnitFeatures.put("sharedFacilities", sharedFacilities);
                residentialUnitFeatures.put("orientation", residentialUnitOrientation.getText());
                String naturalLight = residentialUnitNaturalLightLow.isSelected() ? "Low" : residentialUnitNaturalLightMedium.isSelected() ? "Medium" : "High";
                residentialUnitFeatures.put("naturalLight", naturalLight);
                propertyData.put("residentialUnitFeatures", residentialUnitFeatures);
            }
            case "Commercial Unit" -> {
                Map<String, Object> commercialUnitFeatures = new HashMap<>();
                List<String> purpose = new ArrayList<>();
                if (newRetailPurposeCommercialUnit.isSelected()) purpose.add("Retail");
                if (newOfficePurposeCommercialUnit.isSelected()) purpose.add("Office");
                if (newMedicalPurposeCommercialUnit.isSelected()) purpose.add("Medical");
                if (newRestaurantPurposeCommercialUnit.isSelected()) purpose.add("Restaurant");
                commercialUnitFeatures.put("purpose", purpose);
                String floorLayout = newOpenPlanFloorLayoutCommercialUnit.isSelected() ? "Open Plan" : newModularFloorLayoutCommercialUnit.isSelected() ? "Modular" : "Separate";
                commercialUnitFeatures.put("floorLayout", floorLayout);
                List<String> accessibilityFeatures = new ArrayList<>();
                if (newElevatorCommercialUnit.isSelected()) accessibilityFeatures.add("Elevator");
                if (newBrailleSignageCommercialUnit.isSelected()) accessibilityFeatures.add("Braille Signage");
                if (newAccessibleWashroomCommercialUnit.isSelected()) accessibilityFeatures.add("Accessible Washroom");
                commercialUnitFeatures.put("accessibilityFeatures", accessibilityFeatures);
                String facade = commercialUnitFacadeGlass.isSelected() ? "Glass" : commercialUnitFacadeConcrete.isSelected() ? "Concrete" : "Metal";
                commercialUnitFeatures.put("facade", facade);
                List<String> nearbyAttractions = new ArrayList<>();
                if (commercialUnitNearbyAttractionMalls.isSelected()) nearbyAttractions.add("Malls");
                if (commercialUnitNearbyAttractionSchools.isSelected()) nearbyAttractions.add("Schools");
                if (commercialUnitNearbyAttractionParks.isSelected()) nearbyAttractions.add("Parks");
                if (commercialUnitNearbyAttractionRestaurants.isSelected()) nearbyAttractions.add("Restaurants");
                commercialUnitFeatures.put("nearbyAttractions", nearbyAttractions);
                List<String> fireSafety = new ArrayList<>();
                if (commercialUnitFireAlarm.isSelected()) fireSafety.add("Fire Alarm");
                if (commercialUnitFireExtinguisher.isSelected()) fireSafety.add("Fire Extinguisher");
                if (commercialUnitEmergencyExit.isSelected()) fireSafety.add("Emergency Exit");
                commercialUnitFeatures.put("fireSafety", fireSafety);
                String airCondition = commercialUnitAirConditioningCentral.isSelected() ? "Central" : commercialUnitAirConditioningIndividual.isSelected() ? "Individual" : "None";
                commercialUnitFeatures.put("airCondition", airCondition);
                propertyData.put("commercialUnitFeatures", commercialUnitFeatures);
            }
            case "Business Place" -> {
                Map<String, Object> businessPlaceFeatures = new HashMap<>();
                List<String> purpose = new ArrayList<>();
                if (newStorePurposeBusinessPlace.isSelected()) purpose.add("Store");
                if (newGaragePurposeBusinessPlace.isSelected()) purpose.add("Garage");
                businessPlaceFeatures.put("purpose", purpose);
                String ventilation = newStandardVentilationBusinessPlace.isSelected() ? "Standard" : newHighPoweredFansVentilationBusinessPlace.isSelected() ? "High Powered Fans" : "Air Filtration";
                businessPlaceFeatures.put("ventilation", ventilation);
                List<String> powerSupply = new ArrayList<>();
                if (newStandardPowerSupplyBusinessPlace.isSelected()) powerSupply.add("Standard");
                if (newHighVoltagePowerBusinessPlace.isSelected()) powerSupply.add("High Voltage");
                if (newGeneratorPowerSupplyBusinessPlace.isSelected()) powerSupply.add("Backup Generator");
                businessPlaceFeatures.put("powerSupply", powerSupply);
                String lightingSetup = businessPlaceAmbientLighting.isSelected() ? "Ambient" : businessPlaceTaskLighting.isSelected() ? "Task" : "Accent";
                businessPlaceFeatures.put("lightingSetup", lightingSetup);
                String waitingArea = businessPlaceDedicatedWaitingArea.isSelected() ? "Dedicated" : businessPlaceSeatingSpaceWaitingArea.isSelected() ? "Seating Space" : "None";
                businessPlaceFeatures.put("waitingArea", waitingArea);
                String visibilityFromRoad = businessPlaceHighVisibility.isSelected() ? "High" : businessPlaceMediumVisibility.isSelected() ? "Medium" : "Low";
                businessPlaceFeatures.put("visibilityFromRoad", visibilityFromRoad);
                businessPlaceFeatures.put("insuranceDetails", businessPlaceInsuranceDetails.getText());
                propertyData.put("businessPlaceFeatures", businessPlaceFeatures);
            }
        }

        // property.setPropertyTitle(title);
        // property.setPropertyDescription(newPropertyDescription.getText());
        // if(property.getOfferType().equals("For sale")) {
        //     property.setSalePrice(new BigDecimal(newPropertySalePrice.getText()));
        // } else if(property.getOfferType().equals("For rent")) {
        //     property.setRentPrice(new BigDecimal(newPropertyRentPrice.getText()));
        //     property.setRentAvailableDate(newPropertyAvailableForRentFromDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        //     property.setRentTerm(newPropertyRentTermBox.getValue());
        // }
        // property.setCountry(country);
        // property.setState(state);
        // property.setCity(propertyNewCity.getText());
        // property.setPostalCode(propertyNewPostalCode.getText());
        // property.setStreet(propertyNewStreetAddress.getText());

        // List<String> pictures = new ArrayList<>();
        // for (Node node : propertyImagesEditFlowPane.getChildren()) {
        //     if (node instanceof Pane) {
        //         ImageView imageView = (ImageView) node.lookup("#picture");
        //         if (imageView != null && imageView.getImage() != null) {
        //             String imagePath = imageView.getImage().getUrl().substring(5); // Remove "file:" prefix
        //             pictures.add(imagePath);
        //         }
        //     }
        // }
        // property.setPicture(pictures);

        // switch (property.getPropertyType()) {
        //     case "Apartment" -> {
        //         Apartment apartment = (Apartment) property;
        //         apartment.setPetPolicy(newPetPolicyYes.isSelected());
        //         apartment.setFurniturePolicy(newfurnishedPolicyYes.isSelected());
        //         apartment.setTerracePolicy(newterraceAvailabilityYes.isSelected());
        //         apartment.setParkingPolicy(newparkingAvailabilityYes.isSelected());

        //         apartment.setBedrooms(Integer.parseInt(numberOfBedroomsInApartment.getText()));
        //         apartment.setBathrooms(Integer.parseInt(numberOfBathroomsInApartment.getText()));
        //         apartment.setFloor(Integer.parseInt(floorNumberOfApartment.getText()));
        //         apartment.setTotalFloor(Integer.parseInt(totalFloorsOfBuildingInApartment.getText()));
        //         apartment.setMaintenanceServices(maintenanceServicesApartment.getText());
        //         apartment.setBalconyPolicy(apartmentBalconyYes.isSelected());
        //     }
        //     case "House" -> {
        //         House house = (House) property;
        //         house.setBasementAvailability(newbasementPreferenceYes.isSelected() ? "Finished" : "Unfinished");

        //         List<String> securityFeatures = new ArrayList<>();
        //         if (newSecuritySystem.isSelected()) securityFeatures.add("Security System");
        //         if (newGatedEntry.isSelected()) securityFeatures.add("Gated Entry");
        //         if (newCameras.isSelected()) securityFeatures.add("Cameras");
        //         house.setSecurityFeatures(securityFeatures);

        //         house.setGarageAvailability(newGaragePreferenceYes.isSelected());

        //         house.setBedrooms(Integer.parseInt(numberOfBedroomsInHouse.getText()));
        //         house.setBathrooms(Integer.parseInt(numberOfBathroomsInHouse.getText()));
        //         house.setFloor(Integer.parseInt(numberOfFloorsInHouse.getText()));

        //         String roofType = houseRoofTypeFlat.isSelected() ? "Flat" : houseRoofTypeSloped.isSelected() ? "Sloped" : "Material";
        //         house.setRoofType(roofType);

        //         house.setGardenAvailability(houseGardenYes.isSelected());
        //         house.setBalconyAvailability(houseBalconyYes.isSelected());
        //     }
        //     case "Land" -> {
        //         Land land = (Land) property;
        //         land.setTopography(newFlatLand.isSelected() ? "Flat" : newSlopedLand.isSelected() ? "Sloped" : "Hilly");

        //         List<String> utilities = new ArrayList<>();
        //         if (newElectricityUtilityLand.isSelected()) utilities.add("Electricity");
        //         if (newWaterUtilityLand.isSelected()) utilities.add("Water");
        //         if (newSewageUtilityLand.isSelected()) utilities.add("Sewage");
        //         land.setUtilities(utilities);

        //         land.setRoadAccess(newPavedRoadAccessLand.isSelected() ? "Paved" : "Unpaved");

        //         String type = landTypeAgricultural.isSelected() ? "Agricultural" : landTypeResidential.isSelected() ? "Residential" : landTypeCommercial.isSelected() ? "Commercial" : "Industrial";
        //         land.setType(type);

        //         if (toggleBetweenDimensionAndAreaLand.isSelected()) {
        //             land.setArea(new BigDecimal(landArea.getText()));
        //         } else {
        //             land.setLength(new BigDecimal(landLength.getText()));
        //             land.setWidth(new BigDecimal(landWidth.getText()));
        //         }

        //         String border = landBorderFenced.isSelected() ? "Fenced" : landBorderUnfenced.isSelected() ? "Unfenced" : "Boundary Markers";
        //         land.setBorder(border);

        //         land.setPreviousDevelopment(landPreviousDevelopmentYes.isSelected());

        //         List<String> nearbyInfrastructure = new ArrayList<>();
        //         if (landNearbyInfrastructureRoads.isSelected()) nearbyInfrastructure.add("Roads");
        //         if (landNearbyInfrastructureSchools.isSelected()) nearbyInfrastructure.add("Schools");
        //         if (landNearbyInfrastructureHospitals.isSelected()) nearbyInfrastructure.add("Hospitals");
        //         land.setNearbyInfrastructure(nearbyInfrastructure);
        //     }
        //     case "Residential Unit" -> {
        //         ResidentialUnit residentialUnit = (ResidentialUnit) property;
        //         List<String> amenities = new ArrayList<>();
        //         if (newPoolAmenitiyResidentialUnit.isSelected()) amenities.add("Pool");
        //         if (newGymAmenityResidentialUnit.isSelected()) amenities.add("Gym");
        //         if (newPlaygroundAmenityResidentialUnit.isSelected()) amenities.add("Kid's Playground");
        //         residentialUnit.setAmenities(amenities);

        //         String storageType = newClosetStorageResidentialUnit.isSelected() ? "Closet" : newBasementStorageResidentialUnit.isSelected() ? "Basement" : "Built-In";
        //         residentialUnit.setStorageType(storageType);

        //         residentialUnit.setNoiseInsulation(newNoiseInsulatedResidentialUnit.isSelected());

        //         residentialUnit.setConfiguration(bhkResidentialUnit.getValue());

        //         List<String> sharedFacilities = new ArrayList<>();
        //         if (residentialUnitLaundry.isSelected()) sharedFacilities.add("Laundry");
        //         if (residentialUnitGarbageDisposal.isSelected()) sharedFacilities.add("Garbage Disposal");
        //         if (residentialUnitInternet.isSelected()) sharedFacilities.add("Internet");
        //         residentialUnit.setSharedFacilities(sharedFacilities);

        //         residentialUnit.setOrientation(residentialUnitOrientation.getText());

        //         String naturalLight = residentialUnitNaturalLightLow.isSelected() ? "Low" : residentialUnitNaturalLightMedium.isSelected() ? "Medium" : "High";
        //         residentialUnit.setNaturalLight(naturalLight);
        //     }
        //     case "Commercial Unit" -> {
        //         CommercialUnit commercialUnit = (CommercialUnit) property;
        //         List<String> purpose = new ArrayList<>();
        //         if (newRetailPurposeCommercialUnit.isSelected()) purpose.add("Retail");
        //         if (newOfficePurposeCommercialUnit.isSelected()) purpose.add("Office");
        //         if (newMedicalPurposeCommercialUnit.isSelected()) purpose.add("Medical");
        //         if (newRestaurantPurposeCommercialUnit.isSelected()) purpose.add("Restaurant");
        //         commercialUnit.setPurpose(purpose);

        //         String floorLayout = newOpenPlanFloorLayoutCommercialUnit.isSelected() ? "Open Plan" : newModularFloorLayoutCommercialUnit.isSelected() ? "Modular" : "Separate";
        //         commercialUnit.setFloorLayout(floorLayout);

        //         List<String> accessibilityFeatures = new ArrayList<>();
        //         if (newElevatorCommercialUnit.isSelected()) accessibilityFeatures.add("Elevator");
        //         if (newBrailleSignageCommercialUnit.isSelected()) accessibilityFeatures.add("Braille Signage");
        //         if (newAccessibleWashroomCommercialUnit.isSelected()) accessibilityFeatures.add("Accessible Washroom");
        //         commercialUnit.setAccessibilityFeatures(accessibilityFeatures);

        //         String facade = commercialUnitFacadeGlass.isSelected() ? "Glass" : commercialUnitFacadeConcrete.isSelected() ? "Concrete" : "Metal";
        //         commercialUnit.setFacade(facade);

        //         List<String> nearbyAttractions = new ArrayList<>();
        //         if (commercialUnitNearbyAttractionMalls.isSelected()) nearbyAttractions.add("Malls");
        //         if (commercialUnitNearbyAttractionSchools.isSelected()) nearbyAttractions.add("Schools");
        //         if (commercialUnitNearbyAttractionParks.isSelected()) nearbyAttractions.add("Parks");
        //         if (commercialUnitNearbyAttractionRestaurants.isSelected()) nearbyAttractions.add("Restaurants");
        //         commercialUnit.setNearbyAttractions(nearbyAttractions);

        //         List<String> fireSafety = new ArrayList<>();
        //         if (commercialUnitFireAlarm.isSelected()) fireSafety.add("Fire Alarm");
        //         if (commercialUnitFireExtinguisher.isSelected()) fireSafety.add("Fire Extinguisher");
        //         if (commercialUnitEmergencyExit.isSelected()) fireSafety.add("Emergency Exit");
        //         commercialUnit.setFireSafety(fireSafety);

        //         String airCondition = commercialUnitAirConditioningCentral.isSelected() ? "Central" : commercialUnitAirConditioningIndividual.isSelected() ? "Individual" : "None";
        //         commercialUnit.setAirCondition(airCondition);
        //     }
        //     case "Business Place" -> {
        //         BusinessPlace businessPlace = (BusinessPlace) property;
        //         List<String> purpose = new ArrayList<>();
        //         if (newStorePurposeBusinessPlace.isSelected()) purpose.add("Store");
        //         if (newGaragePurposeBusinessPlace.isSelected()) purpose.add("Garage");
        //         businessPlace.setPurpose(purpose);

        //         String ventilation = newStandardVentilationBusinessPlace.isSelected() ? "Standard" : newHighPoweredFansVentilationBusinessPlace.isSelected() ? "High Powered Fans" : "Air Filtration";
        //         businessPlace.setVentilation(ventilation);

        //         List<String> powerSupply = new ArrayList<>();
        //         if (newStandardPowerSupplyBusinessPlace.isSelected()) powerSupply.add("Standard");
        //         if (newHighVoltagePowerBusinessPlace.isSelected()) powerSupply.add("High Voltage");
        //         if (newGeneratorPowerSupplyBusinessPlace.isSelected()) powerSupply.add("Backup Generator");
        //         businessPlace.setPowerSupply(powerSupply);

        //         String lightingSetup = businessPlaceAmbientLighting.isSelected() ? "Ambient" : businessPlaceTaskLighting.isSelected() ? "Task" : "Accent";
        //         businessPlace.setLightingSetup(lightingSetup);

        //         String waitingArea = businessPlaceDedicatedWaitingArea.isSelected() ? "Dedicated" : businessPlaceSeatingSpaceWaitingArea.isSelected() ? "Seating Space" : "None";
        //         businessPlace.setWaitingArea(waitingArea);

        //         String visibilityFromRoad = businessPlaceHighVisibility.isSelected() ? "High" : businessPlaceMediumVisibility.isSelected() ? "Medium" : "Low";
        //         businessPlace.setVisibilityFromRoad(visibilityFromRoad);

        //         businessPlace.setInsuranceDetails(businessPlaceInsuranceDetails.getText());
        //     }
        // }

//        System.out.println(property.getPicture());
        // ObjectMapper objectMapper = new ObjectMapper();
        // String jsonInputString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(property);
        String jsonInputString = objectMapper.writeValueAsString(propertyData);
        String targetUrl = "http://localhost:8080/api/properties/edit";

        try {
            Pair<Integer, String> pair = HTTPClient.sendPutRequestForEditPropertyDetails(targetUrl, jsonInputString);
            if(pair.getKey() == 200) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update Property Details");
                alert.setHeaderText(null);
                alert.setContentText(pair.getValue());
                alert.showAndWait();

//                Property updatedProperty = HTTPClient.getPropertyById(propertyTargetUrl, propertyId);
//                onEditPropertyClicked(updatedProperty);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
