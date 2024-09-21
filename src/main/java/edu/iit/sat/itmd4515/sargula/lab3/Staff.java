/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.lab3;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

/**
 *
 * @author sargula
 */
public class Staff {
    
    @NotNull
    @Positive
    private Integer id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    @Positive
    private Integer addressId;
    @Email(message = "invalid email")
    private String email;
    @NotNull
    @Positive
    private Integer storeId;
    @NotNull
    private Boolean active;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @PastOrPresent
    private LocalDate lastUpdate;

    /**
     * Default constructor
     */
    public Staff() {
    }

    /**
     * @param id
     * @param firstName
     * @param lastName
     * @param addressId
     * @param email
     * @param storeId
     * @param active
     * @param username
     * @param password
     * @param lastUpdate
     */
    public Staff(Integer id, String firstName, String lastName, Integer addressId, String email, Integer storeId, Boolean active, String username, String password, LocalDate lastUpdate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressId = addressId;
        this.email = email;
        this.storeId = storeId;
        this.active = active;
        this.username = username;
        this.password = password;
        this.lastUpdate = lastUpdate;
    }

    
    /**
     * Get the value of Staff id
     *
     * @return the value of id
     */
    public Integer getId() {
        return id;
    }
    /**
     * Set the value of Staff id
     *
     * @param id new value of id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get the value of Staff first name
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of Staff first name
     * 
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the value of Staff last name
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Set the value of Staff last name
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the value of Staff address
     *
     * @return
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * Set the value of Staff address
     *
     * @param addressId
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * Get the value of Staff email
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of Staff email
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of store id
     *
     * @return
     */
    public Integer getStoreId() {
        return storeId;
    }

    /**
     * Set the value of store id
     *
     * @param storeId
     */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /**
     * Get the value of active
     *
     * @return
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Set the value of active
     *
     * @param active
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Get the value of Staff username
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of Staff username
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the value of password
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the value of Staff row last update
     *
     * @return
     */
    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Set the value of Staff row last update
     *
     * @param lastUpdate
     */
    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Staff{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", addressId=" + addressId + ", email=" + email + ", storeId=" + storeId + ", active=" + active + ", username=" + username + ", password=" + password + ", lastUpdate=" + lastUpdate + '}';
    } 
    
}
