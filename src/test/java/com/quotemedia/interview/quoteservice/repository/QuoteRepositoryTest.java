package com.quotemedia.interview.quoteservice.repository;

import com.quotemedia.interview.quoteservice.model.Quote;
import com.quotemedia.interview.quoteservice.model.QuoteId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.Index;
import javax.persistence.Table;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class QuoteRepositoryTest {

    @Autowired
    private QuoteRepository quoteRepository;

    @Test
    public void itShouldHaveQuoteBySymbolTest() {
        // given
        Quote quote = new Quote();
        QuoteId quoteId = new QuoteId("MSFT", new Date(System.currentTimeMillis()));
        quote.setQuotePrimaryKey(quoteId);
        quote.setAsk(BigDecimal.valueOf(2.13));
        quote.setBid(BigDecimal.valueOf(4.56));

        quoteRepository.save(quote);

        // when
        List<Quote> quotes = quoteRepository.findQuotesBySymbol("SF");

        // then
        assertThat(quotes.size()).isGreaterThan(0);
    }

    @Test
    public void itShouldNotHaveQuoteBySymbolTest() {
        // given
        Quote quote = new Quote();
        QuoteId quoteId = new QuoteId("MSFT", new Date(System.currentTimeMillis()));
        quote.setQuotePrimaryKey(quoteId);
        quote.setAsk(BigDecimal.valueOf(2.13));
        quote.setBid(BigDecimal.valueOf(4.56));
        quoteRepository.save(quote);

        // when
        List<Quote> quotes = quoteRepository.findQuotesBySymbol("AA");

        // then
        assertThat(quotes.size()).isZero();
    }
}