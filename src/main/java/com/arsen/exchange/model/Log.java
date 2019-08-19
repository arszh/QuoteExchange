package com.arsen.exchange.model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
public abstract class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @CreationTimestamp
    private Date date;
    @Enumerated(EnumType.STRING)
    private RequestMethod operation;
    private int errorCode;
    private String message;
    private Long loggedObjId;

    public Log() {
    }

    public Log(RequestMethod operation, int errorCode, String message, Long loggedObjId) {
        this.operation = operation;
        this.errorCode = errorCode;
        this.message = message;
        this.loggedObjId = loggedObjId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public RequestMethod getOperation() {
        return operation;
    }

    public void setOperation(RequestMethod operation) {
        this.operation = operation;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getLoggedObjId() {
        return loggedObjId;
    }

    public void setLoggedObjId(Long loggedObjId) {
        this.loggedObjId = loggedObjId;
    }
}
