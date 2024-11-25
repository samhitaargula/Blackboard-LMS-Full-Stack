/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author sargula
 */
@Entity
@Table(name = "lesson")
@NamedQuery(name = "Lesson.readAll", query = "select l from Lesson l")
public class Lesson extends AbstractEntity {

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotNull
    @PastOrPresent
    private LocalDate lessonDate;

    @Enumerated(EnumType.STRING)
    private LessonType type;

    /**
     * ManyToMany bi-directional relationship Student is owning side Lesson is
     * inverse side
     *
     * This is inverse side
     *
     */
    @ManyToMany(mappedBy = "lessons")
    private List<Student> student = new ArrayList<>();
    
    /**
     * ManyToMany bi-directional relationship
     * Student is owning side
     * Lesson is inverse side
     *
     * This is inverse side
     *
     */
    @ManyToMany(mappedBy = "lessons")
    private List<Teacher> teacher = new ArrayList<>();

    /**
     * Default constructor
     */
    public Lesson() {
    }

    /**
     * Parameterized constructor
     *
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

        //Since we are using GeneratedValue, one more condition
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
     * Writes Lesson fields to string
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Lesson{" + "id=" + id + ", title=" + title + ", lessonDate=" + lessonDate + ", type=" + type + '}';
    }

    /**
     *
     * @return
     */
    public List<Student> getStudents() {
        return student;
    }

    /**
     *
     * @param student
     */
    public void setStudents(List<Student> student) {
        this.student = student;
    }

    /**
     *
     * @return
     */
    public List<Teacher> getTeachers() {
        return teacher;
    }

    /**
     *
     * @param teacher
     */
    public void setTeachers(List<Teacher> teacher) {
        this.teacher = teacher;
    }

}
