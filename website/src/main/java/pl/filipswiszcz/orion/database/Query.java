package pl.filipswiszcz.orion.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public final class Query {

    private final Database database;

    private final String statement;
    private final Collection<Object> objects = new ArrayList<>();

    public Query(final Database database, final String statement) {
        this.database = database;
        this.statement = statement;
    }

    public Query addObject(final Object object) {
        this.objects.add(object); return this;
    }

    public Collection<Box> getBoxedResult(final int parametersNum) {
        final Collection<Box> boxes = new ArrayList<>();
        try {
            final ResultSet result = this.database.getSelectedValues(
                this.statement,
                this.objects
            );

            while (result.next()) {
                final Box box = new Box();
                for (int i = 1; i <= parametersNum; i++)
                    box.addValue(result.getObject(i));

                boxes.add(box);
            }
        } catch (final SQLException exception) {
            throw new RuntimeException(exception);
        }
        return boxes;
    }

    public void execute() {
        this.database.executeStatement(this.statement, this.objects);
    }

    public final class Box {

        private final Collection<Object> values = new ArrayList<>();

        public Box() {}

        public Collection<Object> getValues() {
            return Collections.unmodifiableCollection(this.values);
        }

        protected void addValue(final Object value) {
            this.values.add(value);
        }

    }
    
}
