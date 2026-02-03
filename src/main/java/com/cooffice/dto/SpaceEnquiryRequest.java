package com.cooffice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SpaceEnquiryRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String spaceType;

    @NotBlank
    private String city;

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getSpaceType() { return spaceType; }
    public void setSpaceType(String spaceType) { this.spaceType = spaceType; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
}
