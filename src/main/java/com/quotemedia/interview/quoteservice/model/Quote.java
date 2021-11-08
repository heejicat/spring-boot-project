package com.quotemedia.interview.quoteservice.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Embeddable
@Entity
public class Quote {

    @EmbeddedId
    private QuoteId quoteId;
    @Column
    private BigDecimal Bid;
    @Column
    private BigDecimal Ask;

    public Quote() {
    }

    public Quote(QuoteId quoteId, BigDecimal ask) {
        this.quoteId = quoteId;
        Ask = ask;
    }

    public Quote(QuoteAsk quoteAsk) {
    }

    public QuoteId getQuotePrimaryKey() {
        return quoteId;
    }

    public void setQuotePrimaryKey(QuoteId quotePrimaryKey) {
        this.quoteId = quotePrimaryKey;
    }

    public BigDecimal getBid() {
        return Bid;
    }

    public void setBid(BigDecimal bid) {
        Bid = bid;
    }

    public BigDecimal getAsk() {
        return Ask;
    }

    public void setAsk(BigDecimal ask) {
        Ask = ask;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "quoteId=" + quoteId +
                ", Bid=" + Bid +
                ", Ask=" + Ask +
                '}';
    }
}
