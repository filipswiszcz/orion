package pl.filipswiszcz.orion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.filipswiszcz.orion.database.Database;

@SpringBootApplication
public class OrionApplication {

	public static final Logger LOGGER = Logger.getLogger("Orion");

	private static String host, port, name, user, password;

	private static Database database;

	public static void main(final String[] args) {

		try (final InputStream input = new FileInputStream("src/main/resources/database.properties")) {
			final Properties databaseProperties = new Properties();

			databaseProperties.load(input);

			host = databaseProperties.getProperty("host");
			port = databaseProperties.getProperty("port");
			name = databaseProperties.getProperty("name");
			user = databaseProperties.getProperty("user");
			password = databaseProperties.getProperty("password");

		} catch (final IOException exception) {
			throw new RuntimeException(exception);
		}

		/*database = new Database(
			"127.0.0.1",
			3306,
			"orion",
			"admin",
			"pass"
		);*/

		SpringApplication.run(OrionApplication.class, args);
	}

	public static Database getDatabase() {
		return database;
	}

}
