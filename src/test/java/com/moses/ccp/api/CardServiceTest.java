
package com.moses.ccp.api;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

/*
* <h1>Card Process App - Test</h1>
* CreditCard Test Cases for Credit Card Services
*
* @author  Moses |Alphonse
* @version 1.0
* @since   2022-04-18 
 */
@QuarkusTest
public class CardServiceTest {

    @Inject
    CardService cardService;

    /**
     * This testcase verifies the CredditCard Number using Luhn Algorithm
     * 
     */
    @Test
    public void luhnTest()
    {
        Assertions.assertEquals(true, cardService.luhn("1111222233334444"));
        Assertions.assertEquals(false, cardService.luhn("9874366644"));
    }
    
}
