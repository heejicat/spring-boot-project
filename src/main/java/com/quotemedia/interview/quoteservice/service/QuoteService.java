package com.quotemedia.interview.quoteservice.service;

import com.quotemedia.interview.quoteservice.model.Quote;
import com.quotemedia.interview.quoteservice.model.QuoteAsk;
import javassist.NotFoundException;

import java.util.List;

public interface QuoteService {

    List<Quote> fetchQuotesBySymbol(String symbol) throws NotFoundException;
    List<QuoteAsk> fetchHighestQuoteByDay(String day) throws Exception;

}
