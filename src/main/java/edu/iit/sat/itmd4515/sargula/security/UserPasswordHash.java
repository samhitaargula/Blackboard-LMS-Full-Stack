/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.security;

import jakarta.inject.Inject;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 * UserPasswordHash class to encrypt user passwords with hashing.
 *
 * @author sargula
 */
public class UserPasswordHash {

    @Inject
    private Pbkdf2PasswordHash hash;

    /**
     * Method to encrypt user passwords with hashing.
     */
    @PrePersist
    @PreUpdate
    private void hashPassword(User u) {
        u.setPassword(hash.generate(u.getPassword().toCharArray()));
    }
}
