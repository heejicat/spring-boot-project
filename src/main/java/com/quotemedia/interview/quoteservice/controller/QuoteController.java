package com.quotemedia.interview.quoteservice.controller;

import com.quotemedia.interview.quoteservice.model.Quote;
import com.quotemedia.interview.quoteservice.model.QuoteResult;
import com.quotemedia.interview.quoteservice.service.QuoteServiceImplementation;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class QuoteController {

    @Autowired
    private QuoteServiceImplementation quoteServiceImplementation;

    @GetMapping("/symbols/{symbol}/quotes/latest")
    public List<QuoteResult> getQuote(@PathVariable String symbol) {
        try {
            // Result
            List<Quote> quotes = quoteServiceImplementation.fetchQuotesBySymbol(symbol);

            // Put result data into new list for showing bid and ask field
            ModelMapper modelMapper = new ModelMapper();
            List<QuoteResult> quoteResults = quotes.stream()
                    .map(quote -> modelMapper.map(quote, QuoteResult.class))
                    .collect(Collectors.toList());

            return quoteResults;
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

//    @GetMapping("/highest/ask/{day}")
//    public Quote getHighestAsk(@PathVariable String day) throws Exception {
//        try {
//            // Result
//            Quote quote = quoteServiceImplementation.fetchHighestQuoteByDay(day);
//
//            return quote;
//        } catch (NotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
//        }
//    }
}
