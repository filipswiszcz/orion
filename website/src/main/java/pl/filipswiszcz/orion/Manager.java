package pl.filipswiszcz.orion;

import java.util.Arrays;

import pl.filipswiszcz.orion.database.Query;

final class Manager {

    Manager() {}

    protected void initDatabaseTables() {
        Arrays.asList(
            this.getUsersTable()
        ).forEach(table -> {

            final Query query = new Query(
                OrionApplication.getDatabase(),
                table
            );

            query.execute();
        });
    }

    private String getUsersTable() {
        return "create table if not exists (" +
            " id int auto_increment primary key";
    }
    
}
