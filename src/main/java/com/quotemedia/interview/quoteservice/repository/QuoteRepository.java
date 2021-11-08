package com.quotemedia.interview.quoteservice.repository;

import com.quotemedia.interview.quoteservice.model.Quote;
import com.quotemedia.interview.quoteservice.model.QuoteAsk;
import com.quotemedia.interview.quoteservice.model.QuoteId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, QuoteId> {

    @Query(value = "SELECT a.symbol, a.bid, a.ask, a.day " +
            "FROM quote a " +
            "JOIN ( " +
            "   SELECT symbol, max(day) day " +
            "   FROM quote " +
            "   GROUP BY symbol " +
            ") b ON a.symbol = b.symbol and a.day = b.day " +
            "WHERE a.symbol LIKE %:symbol% ",
            nativeQuery = true)
    List<Quote> findQuotesBySymbol(@Param("symbol") String symbol);

    @Query(value = "SELECT a.symbol, a.ask " +
            "FROM quote a " +
            "WHERE a.day = :day " +
            "ORDER BY a.ask DESC " +
            "LIMIT 1 ",
            nativeQuery = true)
    List<QuoteAsk> findHighestAskByDayProjectedForQuoteAsk(@Param("day") Date day);
}

