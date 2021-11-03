package com.quotemedia.interview.quoteservice.service;

import com.quotemedia.interview.quoteservice.model.Quote;
import com.quotemedia.interview.quoteservice.model.QuoteId;
import com.quotemedia.interview.quoteservice.repository.QuoteRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class QuoteServiceImplementation implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    public QuoteServiceImplementation(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Cacheable(value = "quote")
    @Override
    public List<Quote> fetchQuotesBySymbol(String symbol) throws NotFoundException {

        // for caching
        System.out.println(symbol + "searching...");

        // Validation for Symbol parameter
        if (symbol.length() < 2 || symbol.length() > 6) {
            throw new IllegalArgumentException("Bad Request");
        }

        // Get data from DB
        QuoteId quoteId = new QuoteId(symbol);
        List<Quote> quotes = quoteRepository.findQuotesBySymbol(quoteId.getSymbol());

        // Check if data exists
        if (quotes.isEmpty()) {
            throw new NotFoundException("Not Found");
        }

        return quotes;
    }

//    @Cacheable(value = "ask")
//    @Override
//    public Quote fetchHighestQuoteByDay(String day) throws Exception {
//
//        // for caching
//        System.out.println(day + " searching...");
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
//        java.util.Date date = sdf.parse(day);
//        Date sqlDate = new Date(date.getTime());
//
//        // Get data from DB
//        QuoteId quoteId = new QuoteId(sqlDate);
//        Quote quote = quoteRepository.findHighestAskByDay(quoteId.getDay());
//
//        // Check if data exists
//        if (quote.getAsk().equals(null)) {
//            throw new NotFoundException("Not Found");
//        }
//        return quote;
//    }
}
