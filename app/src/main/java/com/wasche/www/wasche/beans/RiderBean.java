package com.wasche.www.wasche.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RiderBean implements Serializable
{

    @SerializedName("response")
    @Expose
    String response;

    @SerializedName("id")
    @Expose
    Integer rider_id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("contact")
    @Expose
    String contact;


    @SerializedName("email")
    @Expose
    String email;


    @SerializedName("address")
    @Expose
    String address;


    @SerializedName("profile_picture")
    @Expose
    String profilePicture;



    @SerializedName("created_at")
    @Expose
    String createdAt;


    @SerializedName("updated_at")
    @Expose
    String updatedAt;

    @SerializedName("created_by")
    @Expose
    Integer createdBy;

    @SerializedName("updated_by")
    @Expose
    Integer updatedBy;

    @SerializedName("active")
    @Expose
    Integer active;

    public Integer getRider_id() {
        return rider_id;
    }

    public void setRider_id(Integer rider_id) {
        this.rider_id = rider_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "RiderBean{" +
                "rider_id=" + rider_id +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", active=" + active +
                '}';
    }
}

