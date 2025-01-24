package com.example.realestatemanagementsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.scene.control.Alert;
import javafx.util.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HTTPClient {
    public static Pair<Integer, String> sendPostRequest(String targetUrl, String jsonInputString) throws IOException {
        URL url = new URL(targetUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        connection.setConnectTimeout(5000);  // 5 seconds
        connection.setReadTimeout(5000);     // 5 seconds

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        Integer responseCode = connection.getResponseCode();
        BufferedReader reader;

        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
        }

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        return new Pair<>(responseCode, response.toString());
    }

    public static JSONObject sendGetRequest(String targetUrl, String email) throws IOException {
        URL url = new URL(targetUrl + "/" + email);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException(("Failed : HTTP error code : " + connection.getResponseCode()));
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        connection.disconnect();

        JSONObject jsonObject = new JSONObject(response.toString());
        return jsonObject;
    }

    public static JSONArray sendGetRentedProperties(String targetUrl, String email) throws IOException {
        URL url = new URL(targetUrl + "/" + email);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException(("Failed : HTTP error code : " + connection.getResponseCode()));
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        connection.disconnect();

        JSONArray jsonArray = new JSONArray(response.toString());
        return jsonArray;
    }

    public static JSONArray sendGetListRequest(String targetUrl, String email) throws IOException {
        URL url = new URL(targetUrl + "/" + email);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("Failed : HTTP error code : " + connection.getResponseCode());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        reader.close();
        connection.disconnect();

        return new JSONArray(response.toString());
    }

     public static JSONObject sendGetListRequest(String targetUrl, String email, String title, String address) throws IOException {
         // Construct the JSON body
         JSONObject jsonBody = new JSONObject();
         jsonBody.put("email", email);
         jsonBody.put("title", title);
         jsonBody.put("address", address);

         // Open connection
         URL url = new URL(targetUrl);
         HttpURLConnection connection = (HttpURLConnection) url.openConnection();
         connection.setRequestMethod("POST");
         connection.setRequestProperty("Content-Type", "application/json");
         connection.setDoOutput(true);

         // Write JSON body to the request
         try (OutputStream os = connection.getOutputStream()) {
             byte[] input = jsonBody.toString().getBytes(StandardCharsets.UTF_8);
             os.write(input, 0, input.length);
         }

         // Read response
         if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
             throw new RuntimeException("Failed: HTTP error code " + connection.getResponseCode());
         }

         BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
         StringBuilder response = new StringBuilder();
         String responseLine;
         while ((responseLine = reader.readLine()) != null) {
             response.append(responseLine);
         }

         connection.disconnect();

         return new JSONObject(response.toString());
     }

    public static JSONObject sendGetPropertySellerRequest(String targetUrl, String title, String address) throws IOException {
        // Construct the JSON body
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("title", title);
        jsonBody.put("address", address);

        // Open connection
        URL url = new URL(targetUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Write JSON body to the request
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonBody.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Read response
        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed: HTTP error code " + connection.getResponseCode());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        connection.disconnect();

        return new JSONObject(response.toString());
    }

    public static Pair<Integer, String> sendPutRequestForEditAccountDetails(String targetUrl, String jsonInputString, String email) throws IOException {
        URL url = new URL(targetUrl + "/" + email + "/accountdetails");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        Integer responseCode = connection.getResponseCode();

        BufferedReader reader;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        return new Pair<>(responseCode, response.toString());
    }

    public static Pair<Integer, String> sendPutRequestForEditPropertyDetails(String targetUrl, String jsonInputString) throws IOException {
        URL url = new URL(targetUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        Integer responseCode = connection.getResponseCode();

        BufferedReader reader;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        return new Pair<>(responseCode, response.toString());
    }

    public static Pair<Integer, String> sendPutRequestForEditLoginDetails(String targetUrl, String jsonInputString, String email) throws IOException {
        URL url = new URL(targetUrl + "/" + email + "/logindetails");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        Integer responseCode = connection.getResponseCode();

        BufferedReader reader;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        return new Pair<>(responseCode, response.toString());
    }

    public static Pair<Integer, String> sendPutRequestForEditProfileDetails(String targetUrl, String jsonInputString, String email) throws IOException {
        URL url = new URL(targetUrl + "/" + email + "/profiledetails");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        Integer responseCode = connection.getResponseCode();

        BufferedReader reader;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        return new Pair<>(responseCode, response.toString());
    }

    public static void deleteUserAccount(Long userId) {
        try {
            String url = "http://localhost:8080/api/yourpropertyuser/delete/" + userId;
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("DELETE");
            connection.setDoOutput(true);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                Alert infoAlert = new Alert(Alert.AlertType.INFORMATION, "Account deleted successfully.");
                infoAlert.showAndWait();
                // Redirect or close the application as needed
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Failed to delete the account.");
                errorAlert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendDeletePropertyRequest(String targetUrl, String email, String title, String address) throws IOException {
        // Construct the JSON body
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("email", email);
        jsonBody.put("title", title);
        jsonBody.put("address", address);

        // Open connection
        URL url = new URL(targetUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Write JSON body to the request
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonBody.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Read response
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Property deleted successfully");
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            throw new RuntimeException("Failed: HTTP error code " + responseCode + " - " + response);
        }

        connection.disconnect();
    }

    public static PaginatedResponse<Property> getProperties(StringBuilder targetUrl, int page, int size, String category, String offerType, BigDecimal minPrice, BigDecimal maxPrice, String sortOrder) throws IOException {
        URL url;
        targetUrl.append("?page=").append(page).append("&size=").append(size);
        if(category != null){
            if(category.contains(" ")) {
                String[] split = category.split(" ");
                targetUrl.append("&category=").append(split[0]).append("%20").append(split[1]);
            } else {
                targetUrl.append("&category=").append(category);
            }
        }
        if(offerType != null) {
            String[] split = offerType.split(" ");
            targetUrl.append("&offerType=").append(split[0]).append("%20").append(split[1]);
        }
        if(minPrice != null) targetUrl.append("&minPrice=").append(minPrice);
        if(maxPrice != null) targetUrl.append("&maxPrice=").append(maxPrice);
        if(sortOrder  != null) targetUrl.append("&sortBy=").append(sortOrder);
        url = new URL(targetUrl.toString());
//        if(category == null) url = new URL(targetUrl + "?page=" + page + "&size=" + size);
//        else url = new URL(targetUrl + "?category=" + category + "&page=" + page + "&size=" + size);
//        System.out.println(url.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException(("Failed : HTTP error code : " + connection.getResponseCode()));
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        connection.disconnect();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.toString(), new TypeReference<>() {
        });
    }

    public static List<Property> getPropertiesWithSearchKey(String targetUrl, String searchKey) throws IOException {
//        JSONObject jsonBody = new JSONObject();
//        jsonBody.put("searchKey", searchKey);

        URL url = new URL(targetUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = searchKey.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException(("Failed : HTTP error code : " + connection.getResponseCode()));
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        connection.disconnect();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.toString(), new TypeReference<>() {});
    }

    public static Property getProperty(String targetUrl, String title, String address) throws IOException {
        // Construct the JSON body
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("title", title);
        jsonBody.put("address", address);

        URL url = new URL(targetUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Write JSON body to the request
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonBody.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException(("Failed : HTTP error code : " + connection.getResponseCode()));
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        connection.disconnect();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.toString(), Property.class);
    }



    public static Property getPropertyById(String targetUrl, Long id) throws IOException {
        URL url = new URL(targetUrl + "/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException(("Failed : HTTP error code : " + connection.getResponseCode()));
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        connection.disconnect();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.toString(), Property.class);
    }

    public static Pair<Integer, String> saveProperty(String targetUrl, String email, String title, String address) throws IOException{
        // Construct the JSON body
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("email", email);
        jsonBody.put("title", title);
        jsonBody.put("address", address);
        
        // Open connection
        URL url = new URL(targetUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Write JSON body to the request
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonBody.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        Integer responseCode = connection.getResponseCode();
        BufferedReader reader;

        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
        }

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        return new Pair<>(responseCode, response.toString());
    }

    public static Pair<Integer, String> sendChatMessage(String targetUrl, String senderEmail, String receiverEmail, String propertyTitle, String propertyLocation, String message) throws IOException {
        // Construct the json object
        JSONObject chatData = new JSONObject();
        chatData.put("senderEmail", senderEmail);
        chatData.put("receiverEmail", receiverEmail);
        chatData.put("propertyTitle", propertyTitle);
        chatData.put("propertyAddress", propertyLocation);
        chatData.put("message", message);

        // Open connection
        URL url = new URL(targetUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Write JSON body to the request
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = chatData.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException(("Failed : HTTP error code : " + connection.getResponseCode()));
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        Integer responseCode = connection.getResponseCode();

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        connection.disconnect();

        return new Pair<>(responseCode, response.toString());
    }

    public static List<ChatMessage> getChatMessages(String targetUrl, String senderEmail, String receiverEmail, String propertyTitle, String propertyLocation) throws IOException {
        // Construct the json object
        JSONObject chatData = new JSONObject();
        chatData.put("senderEmail", senderEmail);
        chatData.put("receiverEmail", receiverEmail);
        chatData.put("propertyTitle", propertyTitle);
        chatData.put("propertyAddress", propertyLocation);

        // Open connection
        URL url = new URL(targetUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Write JSON body to the request
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = chatData.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException(("Failed : HTTP error code : " + connection.getResponseCode()));
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        connection.disconnect();

        // Deserialize the response into a list of ChatMessage objects
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules(); // Handles Java 8 date/time types like LocalDateTime
        return mapper.readValue(response.toString(), new TypeReference<>() {});
    }

    public static List<Object> getChatAndNegotiationHistory(String targetUrl, String senderEmail, String receiverEmail, String propertyTitle, String propertyLocation) throws IOException {
        // Construct the JSON object
        JSONObject chatData = new JSONObject();
        chatData.put("senderEmail", senderEmail);
        chatData.put("receiverEmail", receiverEmail);
        chatData.put("propertyTitle", propertyTitle);
        chatData.put("propertyAddress", propertyLocation);

        // Open connection
        URL url = new URL(targetUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Write JSON body to the request
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = chatData.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed: HTTP error code: " + connection.getResponseCode());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        connection.disconnect();

        // Deserialize the response into a mixed list of ChatMessage and Negotiation objects
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules(); // For LocalDateTime support
        List<Map<String, Object>> rawEntries = mapper.readValue(response.toString(), new TypeReference<>() {});

        List<Object> combinedHistory = new ArrayList<>();
        for (Map<String, Object> entry : rawEntries) {
            String type = (String) entry.get("type");
            if ("CHAT".equals(type)) {
                ChatMessage chatMessage = mapper.convertValue(entry, ChatMessage.class);
                combinedHistory.add(chatMessage);
            } else if ("NEGOTIATION".equals(type)) {
                Negotiation negotiation = mapper.convertValue(entry, Negotiation.class);
                combinedHistory.add(negotiation);
            }
        }

        // Sort the combined history by timestamp
        combinedHistory.sort((o1, o2) -> {
            LocalDateTime time1 = (o1 instanceof ChatMessage) ? ((ChatMessage) o1).getTimeStamp() : ((Negotiation) o1).getOfferedAt();
            LocalDateTime time2 = (o2 instanceof ChatMessage) ? ((ChatMessage) o2).getTimeStamp() : ((Negotiation) o2).getOfferedAt();
            return time1.compareTo(time2);
        });

        return combinedHistory;
    }


//    public static Pair<Integer, String> sendPriceNegotiationOffer(String targetUrl, String senderEmail, String receiverEmail, String propertyTitle, String propertyLocation, String message) throws IOException {
//        // Construct the json object
//        JSONObject chatData = new JSONObject();
//        chatData.put("senderEmail", senderEmail);
//        chatData.put("receiverEmail", receiverEmail);
//        chatData.put("propertyTitle", propertyTitle);
//        chatData.put("propertyAddress", propertyLocation);
//        chatData.put("message", message);
//
//        // Open connection
//        URL url = new URL(targetUrl);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("POST");
//        connection.setRequestProperty("Content-Type", "application/json");
//        connection.setDoOutput(true);
//
//        // Write JSON body to the request
//        try (OutputStream os = connection.getOutputStream()) {
//            byte[] input = chatData.toString().getBytes(StandardCharsets.UTF_8);
//            os.write(input, 0, input.length);
//        }
//
//        if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
//            throw new RuntimeException(("Failed : HTTP error code : " + connection.getResponseCode()));
//        }
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//        Integer responseCode = connection.getResponseCode();
//
//        StringBuilder response = new StringBuilder();
//        String responseLine;
//        while ((responseLine = reader.readLine()) != null) {
//            response.append(responseLine);
//        }
//
//        connection.disconnect();
//
//        return new Pair<>(responseCode, response.toString());
//    }
}