package com.desafio.model;

import javax.persistence.*;

@Entity
@Table(name = "curses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "university_cod")
    private Long universityCod;

    @Column(name = "name")
    private String name;

    @Column(name = "turno")
    private String turno;

    public Course() {

    }

    public Course(Long universityCod, String name) {
        super();
        this.universityCod = universityCod;
        this.name = name;
        this.turno = turno;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getUniversityCod() {
        return universityCod;
    }

    public void setUniversityCod(Long universityCod) {
        this.universityCod = universityCod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}