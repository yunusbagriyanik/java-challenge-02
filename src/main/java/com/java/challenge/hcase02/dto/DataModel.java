package com.java.challenge.hcase02.dto;

import com.java.challenge.hcase02.util.CommonUtil;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

public class DataModel implements Serializable {
    private Timestamp createdDate;
    private Integer randomInt;
    private String md5HashValue;
    private String md5Last2Char;

    public static DataModel init() {
        return new DataModel();
    }

    public DataModel createdDate() {
        this.createdDate = Timestamp.from(Instant.now());
        return this;
    }

    public DataModel randomInt() {
        this.randomInt = CommonUtil.fetchRandomIntegerBetween1And100();
        return this;
    }

    public DataModel md5HashValue() {
        this.md5HashValue = CommonUtil.generateMD5HashValue(this.createdDate, this.randomInt);
        return this;
    }

    public DataModel md5Last2Char() {
        this.md5Last2Char = CommonUtil.md5Last2Char(this.md5HashValue);
        return this;
    }

    public DataModel build() {
        return this;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public Integer getRandomInt() {
        return randomInt;
    }

    public String getMd5HashValue() {
        return md5HashValue;
    }

    public String getMd5Last2Char() {
        return md5Last2Char;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "createdDate=" + createdDate +
                ", randomInt=" + randomInt +
                ", md5HashValue='" + md5HashValue + '\'' +
                ", md5Last2Char='" + md5Last2Char + '\'' +
                '}';
    }
}