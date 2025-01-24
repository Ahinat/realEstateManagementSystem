package com.example.springbootforyourproperty.yourpropertyuser;

import com.example.springbootforyourproperty.yourproperty.SavedProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Alternative options: SINGLE_TABLE or TABLE_PER_CLASS
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public class YourPropertyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("UserId")
    private Long userId;

    @Column(name = "First Name")
    @JsonProperty("FirstName")
    private String firstName;

    @Column(name = "Last Name")
    @JsonProperty("LastName")
    private String lastName;

    @Column(name = "Password", nullable = true)
    @JsonProperty("Password")
    private String password;

    @Column(name = "Birthday")
    @JsonProperty("Birthday")
    private String birthday;

    @Column(name = "Gender", nullable = true)
    @JsonProperty("Gender")
    private String gender;

    @Column(name = "E-mail", unique = true, nullable = true)
    @JsonProperty("Email")
    private String email;

    @Column(name = "Mobile Number")
    @JsonProperty("MobileNumber")
    private String mobileNumber;

    @Column(name = "Security Question", nullable = true)
    @JsonProperty("SecurityQuestion")
    private String securityQuestion;

    @Column(name = "Security Answer", nullable = true)
    @JsonProperty("SecurityAnswer")
    private String securityAnswer;

    @Column(name = "Bio", nullable = true)
    @JsonProperty("Bio")
    private String bio;

    @Column(name = "profile_picture")
    @JsonProperty("ProfilePicture")
    private String profilePicture;

    @Column(name = "Facebook Link")
    @JsonProperty("Facebook")
    private String facebookLink;

    @Column(name = "Twitter Link")
    @JsonProperty("Twitter")
    private String twitterLink;

    @Column(name = "Google Link")
    @JsonProperty("Google")
    private String googleLink;

    // @OneToMany(mappedBy = "yourPropertyUser")
    // @JsonProperty("SavedProperties")
    // @JsonManagedReference
    // private List<SavedProperty> savedProperties;

    // Constructor for setting required fields like email
    public YourPropertyUser(String email) {
        this.email = email;
    }

    // Default constructor for JPA
    protected YourPropertyUser() {}

    // getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public String getGoogleLink() {
        return googleLink;
    }

    public void setGoogleLink(String googleLink) {
        this.googleLink = googleLink;
    }

    // public List<SavedProperty> getSavedProperties() {
    //     return savedProperties;
    // }

    // public void setSavedProperties(List<SavedProperty> savedProperties) {
    //     this.savedProperties = savedProperties;
    // }
}

