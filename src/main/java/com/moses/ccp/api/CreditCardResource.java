
package com.moses.ccp.api;

import java.net.URI;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.logging.Logger;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;

/*
* <h1>Card Process App</h1>
* <p> 
* REST API for Credit Card Processing
* <p>
*
* @author  Moses |Alphonse
* @version 1.0
* @since   2022-04-18 
 */
@Path("cards")
public class CreditCardResource {

    /**
     * The Logger instance
     */
    private static final Logger LOG = Logger.getLogger(CreditCardResource.class);

    /**
     * Postgress Reactive SQL Client
     */
    @Inject
    private final PgPool client;

    /**
     * CreditCardService instance
     */
    @Inject
    CardService cardService;

    public CreditCardResource(PgPool client) {
        this.client = client;
    }

    /**
     * This method returns the list of Credit Cards in ra reative non-blocking
     * manner
     * 
     * @return list of CreditCards
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<CreditCard> get() {
        return CreditCard.findAll(client);
    }

    /**
     * This method validate and saves the CreditCard info into DB in a reative
     * non-blocking manner
     * 
     * @param card The Credit Card Info.
     * @return Http Response code
     */
    @POST
    public Uni<Response> create(CreditCard card) {
        boolean isValidCard = cardService.isValidCard(card.cardNumber.toString().replaceAll("\\s+", ""));
        LOG.info("Card Number :" + card.cardNumber.toString().replaceAll("\\s+", ""));
        LOG.info("Card Number isValid :" + isValidCard);
        card.balance = 0.0;
        if (!isValidCard || card.cardNumber == 0)
               return Uni(Response.status(400).build());
        else
            return card.save(client)
                .onItem().transform(id -> URI.create("/cards/" + id))
                .onItem().transform(uri -> Response.created(uri).build());

    }

    private Uni<Response> Uni(Response build) {
        return null;
    }

}
