package com.java.challenge.hcase02.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "data_model")
public class DataModelEntity {
    private Long id;
    private Timestamp createdDate;
    private Integer randomInt;
    private String md5HashValue;
    private String md5Last2Char;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "random_int")
    public Integer getRandomInt() {
        return randomInt;
    }

    public void setRandomInt(Integer randomInt) {
        this.randomInt = randomInt;
    }

    @Basic
    @Column(name = "md5_hash_value")
    public String getMd5HashValue() {
        return md5HashValue;
    }

    public void setMd5HashValue(String md5HashValue) {
        this.md5HashValue = md5HashValue;
    }

    @Basic
    @Column(name = "md5_ls_2_ch")
    public String getMd5Last2Char() {
        return md5Last2Char;
    }

    public void setMd5Last2Char(String md5Last2Char) {
        this.md5Last2Char = md5Last2Char;
    }
}