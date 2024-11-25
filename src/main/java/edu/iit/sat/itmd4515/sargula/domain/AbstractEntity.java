/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import java.time.LocalDateTime;

/**
 *
 * @author sargula
 */
@MappedSuperclass
public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Version
    private Long version;

    private LocalDateTime createdTimestamp;

    private LocalDateTime updatedTimestamp;
    
    @PrePersist
    private void initCreatedTimestamp() {
        createdTimestamp = LocalDateTime.now();
    }
    
    @PreUpdate
    private void initUpdatedTimestamp() {
        updatedTimestamp = LocalDateTime.now();
    }


    /**
     * Default constructor
     */
    public AbstractEntity() {
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the value of version
     *
     * @return Long
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Set the value of version
     *
     * @param version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * Get the value of createdTimestamp
     * 
     * @return LocalDateTime
     */
    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    /**
     * Set the value of createdTimestamp
     * 
     * @param createdTimestamp
     */
    public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    public LocalDateTime getUpdatedTimestamp() {
        return updatedTimestamp;
    }
    public void setUpdatedTimestamp(LocalDateTime updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }

}
