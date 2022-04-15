
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
    public boolean luhn(String cc) {
        final boolean[] dbl = { false };
        return cc
                .chars()
                .map(c -> Character.digit((char) c, 10))
                .map(i -> ((dbl[0] = !dbl[0])) ? (((i * 2) > 9) ? (i * 2) - 9 : i * 2) : i)
                .sum() % 10 == 0;
    }

}
