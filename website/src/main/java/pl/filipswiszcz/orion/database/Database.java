package pl.filipswiszcz.orion.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.cj.jdbc.MysqlDataSource;

public final class Database {

    private final MysqlDataSource dataSource;

    public Database(final String host, final int port,
        final String name, final String user, final String password) {
            this.dataSource = new MysqlDataSource();
            this.dataSource.setUrl(
                "jdbc:mysql://" + host + ":" + String.valueOf(port) + "/" + name
            );
            this.dataSource.setUser(user);
            this.dataSource.setPassword(password);
            this.initDefaultProperties();
        }

    private void initDefaultProperties() {
        try {
            this.dataSource.setCachePrepStmts(true);
            this.dataSource.setUseServerPrepStmts(true);
            this.dataSource.setPrepStmtCacheSize(250);
            this.dataSource.setPrepStmtCacheSqlLimit(2048);
        } catch (final SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    protected ResultSet getSelectedValues(final String statement, final List<Object> parameters) {

        final Connection connection = this.getConnection();

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(statement);

            if (!parameters.isEmpty()) {
                int i = 1;
                for (final Object parameter : parameters) {
                    preparedStatement.setObject(i, parameter); i++;
                }
            }

            return preparedStatement.executeQuery();
        } catch (final SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            this.closeConnection(connection);
        }
    }

    protected void executeStatement(final String statement, final List<Object> values) {

        final Connection connection = this.getConnection();

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(statement);

            if (!values.isEmpty()) {
                int i = 1;
                for (final Object value : values) {
                    preparedStatement.setObject(i, value); i++;
                }
                preparedStatement.executeUpdate();
            } else preparedStatement.execute();
        } catch (final SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            this.closeConnection(connection);
        }
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (final SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void closeConnection(final Connection connection) {
        try {
            connection.close();
        } catch (final SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
    
}
