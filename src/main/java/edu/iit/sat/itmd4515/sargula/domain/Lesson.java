/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author sargula
 */
@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false)
    private String title;
    
    @PastOrPresent
    private LocalDate lessonDate;
    
    @Enumerated(EnumType.STRING)
    private LessonType type;

    /**
     * Default constructor
     */
    public Lesson() {
    }

    /**
     * Parameterized constructor
     * @param title
     * @param lessonDate
     * @param type
     */
    public Lesson(String title, LocalDate lessonDate, LessonType type) {
        this.title = title;
        this.lessonDate = lessonDate;
        this.type = type;
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
     * Get the value of title
     * 
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value of title
     * 
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Method to get hash code
     * 
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Equals method to compare objects
     * 
     * @param obj
     * @return Boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lesson other = (Lesson) obj;

        if (this.id == null || other.id == null) {
            return false;
        }

        return Objects.equals(this.id, other.id);
    }

    /**
     * Get the value of date
     * 
     * @return LocalDate
     */
    public LocalDate getLessonDate() {
        return lessonDate;
    }

    /**
     * Set the value of date
     * 
     * @param lessonDate
     */
    public void setLessonDate(LocalDate lessonDate) {
        this.lessonDate = lessonDate;
    }

    /**
     * Get the value of type
     * 
     * @return LessonType
     */
    public LessonType getType() {
        return type;
    }

    /**
     * Set the value of type
     * 
     * @param type
     */
    public void setType(LessonType type) {
        this.type = type;
    }

    /**
     * Writes Lesson fields in string
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Lesson{" + "id=" + id + ", title=" + title + ", lessonDate=" + lessonDate + ", type=" + type + '}';
    }

}
