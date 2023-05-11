package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
        UserDaoHibernateImpl hibernate = new UserDaoHibernateImpl();
        hibernate.dropUsersTable();
        hibernate.createUsersTable();

        List<User> users = new ArrayList<>();
        users.add(new User("Ivan", "Ivanov", (byte) 35));
        users.add(new User("Sergey", "Grindstone", (byte) 30));
        users.add(new User("John", "Popov", (byte) 28));
        users.add(new User("Maia", "Smith", (byte) 40));

        users.forEach(user -> {
            hibernate.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.printf("User с именем – %s добавлен в базу данных\n", user.getName());
        });

        List<User> usersFromDb = hibernate.getAllUsers();
        usersFromDb.forEach(System.out::println);

        hibernate.cleanUsersTable();
        hibernate.dropUsersTable();
    }
}
