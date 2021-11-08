package com.quotemedia.interview.quoteservice.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Date;

@Embeddable
public class QuoteId implements Serializable {

    @Column
    private String Symbol;
    @Column
    private Date Day;

    public QuoteId() {}
    public QuoteId(String symbol) { Symbol = symbol; }
    public QuoteId(Date day) { Day = day; }
    public QuoteId(String symbol, Date day) {
        Symbol = symbol;
        Day = day;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public Date getDay() {
        return Day;
    }

    public void setDay(Date day) {
        Day = day;
    }

    @Override
    public String toString() {
        return "QuoteId{" +
                "Symbol='" + Symbol + '\'' +
                ", Day=" + Day +
                '}';
    }

}
