package com.limkee.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.oss.steve.http.Model;

public class User extends Model {
	private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private boolean isAdmin;
    private boolean isMale;
    private Date birthday;
    private int memberId;
    private String facebookId;
    private String bio;

    public User() throws SQLException {
        super("user");
    }

    public User(ResultSet resultSet) throws SQLException {
        super("user", resultSet);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() { return lastName; }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isMale() {
        return isMale;
    }

    public Date getBirthday() {
        return birthday;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}