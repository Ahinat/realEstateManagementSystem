package com.example.springbootforyourproperty.yourpropertyuser;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

@Service
public class YourPropertyUserService {
    @Autowired
    private YourPropertyUserRepository yourPropertyUserRepository;

    public YourPropertyUser storeUser(YourPropertyUser user) {
        return yourPropertyUserRepository.save(user);
    }

    public YourPropertyUser getUserByEmail(String email) {
        return yourPropertyUserRepository.findByEmail(email);
    }

    public YourPropertyUser editUserProfileDetails(String email, YourPropertyUser newUserAccountDetails) throws IOException {
        // Find the user by email
        YourPropertyUser existingUser = yourPropertyUserRepository.findByEmail(email);

        // Check if the user exists
        if (existingUser == null) {
            throw new NoSuchElementException("User not found with email: " + email);
        }

        // Update fields with the new details
        if(Utility.isNotNullOrEmpty(newUserAccountDetails.getFirstName())) existingUser.setFirstName(newUserAccountDetails.getFirstName());
        if(Utility.isNotNullOrEmpty(newUserAccountDetails.getLastName())) existingUser.setLastName(newUserAccountDetails.getLastName());
        if(Utility.isNotNullOrEmpty(newUserAccountDetails.getBirthday())) existingUser.setBirthday(newUserAccountDetails.getBirthday());
        if(Utility.isNotNullOrEmpty(newUserAccountDetails.getGender())) existingUser.setGender(newUserAccountDetails.getGender());
        if(Utility.isNotNullOrEmpty(newUserAccountDetails.getBio())) existingUser.setBio(newUserAccountDetails.getBio());
        if(Utility.isNotNullOrEmpty(newUserAccountDetails.getEmail())) existingUser.setEmail(newUserAccountDetails.getEmail());
        if(Utility.isNotNullOrEmpty(newUserAccountDetails.getPassword())) existingUser.setPassword(newUserAccountDetails.getPassword());
        if(Utility.isNotNullOrEmpty(newUserAccountDetails.getMobileNumber())) existingUser.setMobileNumber(newUserAccountDetails.getMobileNumber());
        if(Utility.isNotNullOrEmpty(newUserAccountDetails.getSecurityQuestion())) existingUser.setSecurityQuestion(newUserAccountDetails.getSecurityQuestion());
        if(Utility.isNotNullOrEmpty(newUserAccountDetails.getSecurityAnswer())) existingUser.setSecurityAnswer(newUserAccountDetails.getSecurityAnswer());
        if(Utility.isNotNullOrEmpty(newUserAccountDetails.getFacebookLink())) existingUser.setFacebookLink(newUserAccountDetails.getFacebookLink());
        if(Utility.isNotNullOrEmpty(newUserAccountDetails.getTwitterLink())) existingUser.setTwitterLink(newUserAccountDetails.getTwitterLink());
        if(Utility.isNotNullOrEmpty(newUserAccountDetails.getGoogleLink())) existingUser.setGoogleLink(newUserAccountDetails.getGoogleLink());
        if(Utility.isNotNullOrEmpty(newUserAccountDetails.getProfilePicture())) existingUser.setProfilePicture(newUserAccountDetails.getProfilePicture());

//        // Handle profile picture update if file is provided
//        if (file != null && !file.isEmpty()) {
//            String fileName = email + "_" + file.getOriginalFilename();
//            Path path = Paths.get(profilePicturesDirectory, fileName);
//            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//            existingUser.setProfilePicture(fileName);
//        }

        // Save and return the updated user
        return yourPropertyUserRepository.save(existingUser);
    }


//    public YourPropertyUser editUserProfileDetails(String email, YourPropertyUser newUserAccountDetails) {
//        // Find the user by email
//        YourPropertyUser existingUser = yourPropertyUserRepository.findByEmail(email);
//
//        // Check if the user exists
//        if (existingUser == null) {
//            throw new NoSuchElementException("User not found with email: " + email);
//        }
//
//        // Update fields with the new details
//        if(!newUserAccountDetails.getFirstName().isEmpty()) existingUser.setFirstName(newUserAccountDetails.getFirstName());
//        if(!newUserAccountDetails.getLastName().isEmpty()) existingUser.setLastName(newUserAccountDetails.getLastName());
//        if(!newUserAccountDetails.getBirthday().isEmpty()) existingUser.setBirthday(newUserAccountDetails.getBirthday());
//        if(!newUserAccountDetails.getGender().isEmpty()) existingUser.setGender(newUserAccountDetails.getGender());
//        if(!newUserAccountDetails.getBio().isEmpty()) existingUser.setBio(newUserAccountDetails.getBio());
//        if(!newUserAccountDetails.getEmail().isEmpty()) existingUser.setEmail(newUserAccountDetails.getEmail());
//        if(!newUserAccountDetails.getPassword().isEmpty()) existingUser.setPassword(newUserAccountDetails.getPassword());
//        if(!newUserAccountDetails.getMobileNumber().isEmpty()) existingUser.setMobileNumber(newUserAccountDetails.getMobileNumber());
//        if(!newUserAccountDetails.getSecurityQuestion().isEmpty()) existingUser.setSecurityQuestion(newUserAccountDetails.getSecurityQuestion());
//        if(!newUserAccountDetails.getSecurityAnswer().isEmpty()) existingUser.setSecurityAnswer(newUserAccountDetails.getSecurityAnswer());
//        if(!newUserAccountDetails.getProfilePicture().isEmpty()) existingUser.setProfilePicture(newUserAccountDetails.getProfilePicture());
//
//        // Save and return the updated user
//        return yourPropertyUserRepository.addProperty(existingUser);
//    }

//    public YourPropertyUser editUserLoginDetails(String email, YourPropertyUser newUserAccountDetails) {
//        // Find the user by email
//        YourPropertyUser existingUser = yourPropertyUserRepository.findByEmail(email);
//
//        // Check if the user exists
//        if (existingUser == null) {
//            throw new NoSuchElementException("User not found with email: " + email);
//        }
//
//        // Update fields with the new details
//        existingUser.setEmail(newUserAccountDetails.getEmail());
//        existingUser.setPassword(newUserAccountDetails.getPassword());
//        existingUser.setMobileNumber(newUserAccountDetails.getMobileNumber());
//        existingUser.setSecurityQuestion(newUserAccountDetails.getSecurityQuestion());
//        existingUser.setSecurityAnswer(newUserAccountDetails.getSecurityAnswer());
//
//        // Save and return the updated user
//        return yourPropertyUserRepository.addProperty(existingUser);
//    }

    public boolean deleteUserById(Long userId) {
        if (yourPropertyUserRepository.existsById(userId)) {
            yourPropertyUserRepository.deleteById(userId);
            return true;
        }
        return false;
    }

//    public boolean deleteUserAccount(String email) {
//        // Find the user by email
//        YourPropertyUser existingUser = yourPropertyUserRepository.findByEmail(email);
//
//        // Check if the user exists
//        if (existingUser == null) {
//            throw new NoSuchElementException("User not found with email: " + email);
//        }
//
//
//    }

    @Value("${profile.pictures.directory}")
    private String profilePicturesDirectory;

    public String saveProfilePicture(MultipartFile file, String email) throws IOException {
        // Ensure the directory exists
        File directory = new File(profilePicturesDirectory);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it does not exist
        }

        // Create a unique filename using the user email and original filename
        String fileName = email + "_" + file.getOriginalFilename();
        Path path = Paths.get(profilePicturesDirectory, fileName); // Combine directory and file name

        // Save the file to the specified path
        Files.copy(file.getInputStream(), path);

        // Update the user's profile picture in the database
        // Find the user by email
        YourPropertyUser existingUser = yourPropertyUserRepository.findByEmail(email);

        // Check if the user exists
        if (existingUser == null) {
            throw new NoSuchElementException("User not found with email: " + email);
        }
        existingUser.setProfilePicture(fileName); // Save only the file name in the database
        yourPropertyUserRepository.save(existingUser);

        return fileName; // Return the filename
    }
}


