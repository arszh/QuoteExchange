package com.arsen.exchange.model;


import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
@AttributeOverrides({
        @AttributeOverride(name = "loggedObjId",
                column = @Column(name = "quoteId"))})
public class QuoteLog extends Log {


    public QuoteLog() {
    }

    public QuoteLog(RequestMethod operation, int errorCode, String message, Long quoteId) {
        super(operation, errorCode, message, quoteId);
    }


}
