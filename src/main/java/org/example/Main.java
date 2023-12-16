package org.example;

import org.example.models.School;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    /**
     * Задание
     * =======
     * Создайте базу данных (например, SchoolDB).
     * В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
     * Настройте Hibernate для работы с вашей базой данных.
     * Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
     * Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
     * Убедитесь, что каждая операция выполняется в отдельной транзакции.
     */

    public static void main(String[] args) {

        //region Создание таблицы в БД без Hibernate
//        String url = "jdbc:mysql://localhost:3306/";
//        String user = "root";
//        String password = "root";
//        try(Connection connection = DriverManager.getConnection(url, user, password)){
//            // Создание базы данных
//            createDatabase(connection);
//            System.out.println("Database created successfully");
//
//            // Использование базы данных
//            useDatabase(connection);
//            System.out.println("Use database successfully");
//
//            // Создание таблицы
//            createTable(connection);
//            System.out.println("Create table successfully");
//
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
        //endregion

        //region Работа с Hibernate

        try(SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(School.class)
                .buildSessionFactory()){

            // Создание сессии
            Session session = sessionFactory.getCurrentSession();

            // Начало транзакции
            session.beginTransaction();

            // Создание объекта
            School group = School.create();
            session.save(group);
            System.out.println("Object group save successfully");

            // Чтение объекта из базы данных
            School retrievedStudent = session.get(School.class, group.getId());
            System.out.println("Object student retrieved successfully");
            System.out.println("Retrieved student object: " + retrievedStudent);

            // Обновление объекта
            retrievedStudent.updateTitle();
            retrievedStudent.updateDuration();
            session.update(retrievedStudent);
            System.out.println("Object student update successfully");

//            session.delete(retrievedStudent);
//            System.out.println("Object student delete successfully");

            session.getTransaction().commit();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        //endregion
    }


    //region Вспомогательные методы

//    private static void createDatabase(Connection connection) throws SQLException {
//        String createDatabaseSQL =  "CREATE DATABASE IF NOT EXISTS schoolDB;";
//        try (PreparedStatement statement = connection.prepareStatement(createDatabaseSQL)) {
//            statement.execute();
//        }
//    }
//
//    private static void useDatabase(Connection connection) throws SQLException {
//        String useDatabaseSQL =  "USE schoolDB;";
//        try (PreparedStatement statement = connection.prepareStatement(useDatabaseSQL)) {
//            statement.execute();
//        }
//    }
//
//    private static void createTable(Connection connection) throws SQLException {
//        String createTableSQL = "CREATE TABLE IF NOT EXISTS Courses (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255), duration DOUBLE);";
//        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
//            statement.execute();
//        }
    //endregion
}