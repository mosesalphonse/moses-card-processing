package com.moses.ccp.api;

import io.quarkus.runtime.StartupEvent;
import io.vertx.mutiny.pgclient.PgPool;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

/*
* <h1>Card Process App</h1>
* DB Initialization for Credit Card Processing
*
* @author  Moses |Alphonse
* @version 1.0
* @since   2022-04-18 
 */

@ApplicationScoped
public class DBInit {

    private final PgPool client;
    private final boolean schemaCreate;

    public DBInit(PgPool client,
            @ConfigProperty(name = "myapp.schema.create", defaultValue = "true") boolean schemaCreate) {
        this.client = client;
        this.schemaCreate = schemaCreate;
    }

    void onStart(@Observes StartupEvent ev) {
        if (schemaCreate) {
            initdb();
        }
    }

    private void initdb() {
        client.query("DROP TABLE IF EXISTS cards").execute()
                .flatMap(r -> client.query(
                        "CREATE TABLE cards (id SERIAL PRIMARY KEY, name TEXT NOT NULL, card_number BIGINT NOT NULL, balance DOUBLE PRECISION NOT NULL, card_limit DOUBLE PRECISION NOT NULL)")
                        .execute())
                .flatMap(r -> client.query(
                        "INSERT INTO cards (name, card_number, balance, card_limit) VALUES ('Alice', 1111222233334444, -1045,2000)")
                        .execute())
                .flatMap(r -> client.query(
                        "INSERT INTO cards (name, card_number, balance, card_limit) VALUES ('Bob', 4444333322221111, 10.24, 5000)")
                        .execute())
                .await().indefinitely();
    }
}
