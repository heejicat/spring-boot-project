package com.quotemedia.interview.quoteservice.service;

import com.quotemedia.interview.quoteservice.model.Quote;
import com.quotemedia.interview.quoteservice.model.QuoteId;
import com.quotemedia.interview.quoteservice.repository.QuoteRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class QuoteServiceImplementationTest {

    @Mock
    private QuoteRepository quoteRepository;
    private QuoteServiceImplementation quoteServiceImplementation;

    @BeforeEach
    void setUp() {
        quoteServiceImplementation = new QuoteServiceImplementation(quoteRepository);
    }

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");

    @Test
    void willThrowWhenQuoteNotFound() throws NotFoundException {
        // given
        Quote quote = new Quote();
        QuoteId quoteId = new QuoteId("MSFT", new Date(System.currentTimeMillis()));
        quote.setQuotePrimaryKey(quoteId);
        quote.setAsk(BigDecimal.valueOf(2.13));
        quote.setBid(BigDecimal.valueOf(4.56));

        quoteRepository.save(quote);

        // then
        assertThatThrownBy(() -> quoteServiceImplementation.fetchQuotesBySymbol("AA"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Not Found");
    }

    @Test
    void willThrowWhenQuoteBadRequest() throws NotFoundException {
        // given
        Quote quote = new Quote();
        QuoteId quoteId = new QuoteId("MSFT", new Date(System.currentTimeMillis()));
        quote.setQuotePrimaryKey(quoteId);
        quote.setAsk(BigDecimal.valueOf(2.13));
        quote.setBid(BigDecimal.valueOf(4.56));

        quoteRepository.save(quote);

        // then
        assertThatThrownBy(() -> quoteServiceImplementation.fetchQuotesBySymbol("A"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Bad Request");
    }
}