
package com.moses.ccp.api;

import javax.enterprise.context.ApplicationScoped;

/*
* <h1>Card Process App</h1>
* CreditCard Services Bean 
*
* @author  Moses |Alphonse
* @version 1.0
* @since   2022-04-18 
 */
@ApplicationScoped
public class CardService {

    /**
     * This method verifies the CredditCard Number using Luhn Algorithm
     * 
     * @param cc The CreditCard Number as String
     * @return boolean value
     */
    public boolean isValidCard(String cardNumber)
    {
        int[] a = {cardNumber.length() % 2 == 0 ? 1 : 2}; 
        return cardNumber.chars()
        .map(i -> i - '0')                          // convert to the int equivalent
        .map(n -> n * (a[0] = a[0] == 1 ? 2 : 1))   // multiply by 1, 2 alternating
        .map(n -> n > 9 ? n - 9 : n)                // handle sum of digits
        .sum() % 10 == 0; 
    }

}
