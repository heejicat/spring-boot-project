package com.quotemedia.interview.quoteservice.service;

import com.quotemedia.interview.quoteservice.model.Quote;
import javassist.NotFoundException;

import java.util.Date;
import java.util.List;

public interface QuoteService {

    List<Quote> fetchQuotesBySymbol(String symbol) throws NotFoundException;
//    Quote fetchHighestQuoteByDay(String day) throws Exception;

}
