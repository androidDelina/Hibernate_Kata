package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoHibernateImpl userDaoHibernateImpl = new UserDaoHibernateImpl();
        userDaoHibernateImpl.createUsersTable();

        userDaoHibernateImpl.saveUser("Ivan", "Ivanov", (byte) 13);
        userDaoHibernateImpl.saveUser("Sasha", "Sashkova", (byte) 15);
        userDaoHibernateImpl.saveUser("Sonya", "Sonechkina", (byte) 14);
        userDaoHibernateImpl.saveUser("Roll", "Roller", (byte) 16);

        System.out.println(userDaoHibernateImpl.getAllUsers());

        userDaoHibernateImpl.cleanUsersTable();
        userDaoHibernateImpl.dropUsersTable();
    }
}
