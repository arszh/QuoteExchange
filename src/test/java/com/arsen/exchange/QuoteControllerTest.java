package com.arsen.exchange;

import com.arsen.exchange.controller.QuoteController;
import com.arsen.exchange.model.Item;
import com.arsen.exchange.model.Quote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuoteControllerTest {

    @Autowired
    QuoteController quoteController;


    @Test(expected = ConstraintViolationException.class)
    public void testNegativePrice() {

        Quote quote = new Quote("name", 1, Arrays.asList(new Item(), new Item()));

        quoteController.create(quote);

    }


    @Test(expected = ConstraintViolationException.class)
    public void testEmptyNameOfQuote() {

        Quote quote = new Quote("", 3, Arrays.asList(new Item(), new Item()));

        quoteController.create(quote);
    }


}
