package com.quotemedia.interview.quoteservice.model;

import java.math.BigDecimal;

public class QuoteResult {

    private BigDecimal bid;
    private BigDecimal ask;

    public QuoteResult() {
    }

    public QuoteResult(BigDecimal bid, BigDecimal ask) {
        this.bid = bid;
        this.ask = ask;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    @Override
    public String toString() {
        return "QuoteResult{" +
                "Bid=" + this.bid +
                ", Ask=" + this.ask +
                '}';
    }
}
