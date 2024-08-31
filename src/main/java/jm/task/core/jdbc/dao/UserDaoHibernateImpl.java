package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory = Util.getSessionFactoryHibernate();
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS users " +
            "(id BIGINT NOT NULL AUTO_INCREMENT, " +
            "name VARCHAR(50) NOT NULL, last_name VARCHAR(50) NOT NULL, " +
            "age TINYINT NOT NULL, PRIMARY KEY (id)) ENGINE=MyISAM";
    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS users";
    private static final String REMOVE_USER_BY_ID = "delete from User where id = :id";
    private static final String ID_PARAM = "id";
    private static final String GET_ALL_USERS = "from User";
    private static final String REMOVE_ALL_USERS = "delete from User";

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        tableMethodHelper(CREATE_TABLE_QUERY);
    }

    @Override
    public void dropUsersTable() {
        tableMethodHelper(DROP_TABLE_QUERY);
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();

            try {
                session.save(user);
                session.getTransaction().commit();
            } catch (Exception e) {
                System.out.println("Assumed: errors during query execution");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Assumed: errors related to SessionFactory or Session operations");
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            try {
                session.createQuery(REMOVE_USER_BY_ID)
                        .setParameter(ID_PARAM, id)
                        .executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                System.out.println("Assumed: errors during query execution");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Assumed: errors related to SessionFactory or Session operations");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            try {
                users = session.createQuery(GET_ALL_USERS).getResultList();
                session.getTransaction().commit();
            } catch (Exception e) {
                System.out.println("Assumed: errors during query execution");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Assumed: errors related to SessionFactory or Session operations");
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            try {
                session.createQuery(REMOVE_ALL_USERS).executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                System.out.println("Assumed: errors during query execution");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Assumed: errors related to SessionFactory or Session operations");
            e.printStackTrace();
        }

    }

    private static void tableMethodHelper(String query) {
        SessionFactory factory = Util.getSessionFactoryHibernate();
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            try {
                session.createSQLQuery(query).executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                System.out.println("Assumed: errors during query execution");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Assumed: errors related to SessionFactory or Session operations");
            e.printStackTrace();
        }
    }

}
