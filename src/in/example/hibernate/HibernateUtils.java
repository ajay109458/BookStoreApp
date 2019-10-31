package in.example.hibernate;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import in.example.entity.Book;

public class HibernateUtils {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				// Hibernate settings equivalent to hibernate.cfg.xml's properties

				Properties settings = getConfigurationSettings();

				configuration.setProperties(settings);
				configuration.addAnnotatedClass(Book.class);
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

	public static Properties getConfigurationSettings() {
		Properties settings = new Properties();
		settings.put(Environment.DRIVER, "org.postgresql.Driver");
		settings.put(Environment.URL, "jdbc:postgresql://127.0.0.1:5432/onlinebookstore");
		settings.put(Environment.USER, "postgres");
		settings.put(Environment.PASS, "postgres");
		settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
		settings.put(Environment.SHOW_SQL, "true");
		settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
		//settings.put(Environment.HBM2DDL_AUTO, "create-drop");

		return settings;
	}
}
