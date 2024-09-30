/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.sargula.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * @author sargula
 */
@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @FutureOrPresent
    private LocalDate date;
    private LocalTime time;

    /**
     * ManyToOne bi-directional relationship Assignment is owning side Student
     * is inverse side
     *
     * This is owning side
     *
     */
    @ManyToOne
    private Student student;

    /**
     * ManyToOne bi-directional relationship Assignment is owning side Teacher
     * is inverse side
     *
     * This is owning side
     *
     */
    @ManyToOne
    private Teacher teacher;

    /**
     * ManyToOne uni-directional relationship Assignment is owning side
     *
     */
    @ManyToOne
    private Lesson lesson;

    /**
     * Default constructor
     */
    public Assignment() {
    }

    /**
     * Parameterized constructor
     *
     * @param date
     * @param time
     */
    public Assignment(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
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
     * Get the value of date
     *
     * @return LocalDate
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Set the value of date
     *
     * @param date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Get the value of time
     *
     * @return LocalTime
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Set the value of time
     *
     * @param time
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Get the value of student
     *
     * @return Student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Set the value of student
     *
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Get the value of teacher
     *
     * @return Teacher
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * Set the value of teacher
     *
     * @param teacher
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * Get the value of lesson
     *
     * @return Lesson
     */
    public Lesson getLesson() {
        return lesson;
    }

    /**
     * Set the value of lesson
     *
     * @param lesson
     */
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    /**
     * Writes Assignment fields to string
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Assignment{" + "id=" + id + ", date=" + date + ", time=" + time + ", student=" + student + ", teacher=" + teacher + ", lesson=" + lesson + '}';
    }

    /**
     * Method to get hash code
     *
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Assignment other = (Assignment) obj;

        //Since we are using GeneratedValue, one more condition
        if (this.id == null || other.id == null) {
            return false;
        }

        return Objects.equals(this.id, other.id);
    }
}
