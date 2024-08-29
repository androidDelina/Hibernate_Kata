package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Connection connection = null;

    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "27112001";

    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactoryHibernate() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .setProperty("hibernate.connection.url", URL)
                        .setProperty("connection.driver_class", "com.mysql.cj.jdbc.Driver")
                        .setProperty("hibernate.connection.username", USER_NAME)
                        .setProperty("hibernate.connection.password", PASSWORD)
                        .setProperty("current_session_context_class", "thread")
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                        .setProperty("show_sql", "true")
                        .setProperty("hibernate.hbm2ddl.auto", "update")
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return sessionFactory;
    }

    public static Connection getConnectionJDBC() {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
