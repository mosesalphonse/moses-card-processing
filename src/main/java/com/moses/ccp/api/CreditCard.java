package com.moses.ccp.api;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.Tuple;

/*
* <h1>Card Process App</h1>
* <p>
* CreditCard CRUD for Credit Card Processing
* <p>
* 
* @author  Moses |Alphonse
* @version 1.0
* @since   2022-04-18 
 */
public class CreditCard {

    public Long id;
    public String name;
    public Long cardNumber;
    public Double balance;
    public Double limit;

    /**
     * The Default Constructor
     */
    public CreditCard() {
        // default constructor.
    }

    /**
     * Another constructor for class CreditCard
     * 
     * @param name       CardHolder Name
     * @param cardNumber CreditCard NUmber
     * @param balance    CreditCard Balance
     * @param limit      CreditCard Limit
     */
    public CreditCard(String name, Long cardNumber, Double balance, Double limit) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.limit = limit;
    }

    /**
     * Another constructor for class CreditCard
     * 
     * @param id         Unique Id
     * @param name       CardHolder Name
     * @param cardNumber CreditCard NUmber
     * @param balance    CreditCard Balance
     * @param limit      CreditCard Limit
     */
    public CreditCard(Long id, String name, Long cardNumber, Double balance, Double limit) {
        this.id = id;
        this.name = name;
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.limit = limit;
    }

    /**
     * This method fetches all the CreditCard info from DB in a reative/non-blocking
     * manner
     * 
     * @param client The PGClient
     * @return list of CreditCards
     */
    public static Multi<CreditCard> findAll(PgPool client) {
        return client.query("SELECT id, name, card_number, balance, card_limit FROM cards ORDER BY name ASC").execute()
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(CreditCard::from);
    }

    /**
     * This method saves the CreditCard info into DB in a reative/non-blocking
     * manner
     * 
     * @param client The PGClient
     * @return primary key of the newly created Credit Card
     */
    public Uni<Long> save(PgPool client) {
        return client.preparedQuery(
                "INSERT INTO cards (name, card_number, balance, card_limit) VALUES ($1, $2, $3, $4) RETURNING id")
                .execute(Tuple.of(name, cardNumber, balance, limit))
                .onItem().transform(pgRowSet -> pgRowSet.iterator().next().getLong("id"));
    }

    /**
     * This method converts Resultset into CreditCard Object
     * 
     * @param row The ResultSet of ID
     * @return Credit Card Object
     */
    private static CreditCard from(Row row) {
        return new CreditCard(row.getLong("id"), row.getString("name"), row.getLong("card_number"),
                row.getDouble("balance"), row.getDouble("card_limit"));
    }
}
