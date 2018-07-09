/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoad3.directoriofx.db;

import com.grupoad3.directoriofx.model.Person;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.io.File;
//import com.j256.ormlite.jdbc.;
import java.sql.SQLException;

/**
 *
 * @author daniel_serna
 */
public class DataBaseManager {

    // we are using the in-memory SQLite
    private final static String DATABASE_URL = "jdbc:sqlite:";
    private Dao<Person, Integer> personDao;
    private ConnectionSource connectionSource;

    private File databaseFile;

    public Dao<Person, Integer> getPersonDao() {
        return personDao;
    }

    public DataBaseManager(File fileDB) throws SQLException {
        databaseFile = fileDB;
        // create our data-source for the database
        connectionSource = new JdbcConnectionSource(DATABASE_URL + databaseFile.getAbsolutePath());

        try {
            //Loading the sqlite drivers
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            //Should never happen
            throw new SQLException(e);
        }

        // Create dao
        personDao = DaoManager.createDao(connectionSource, Person.class);

    }

    /**
     * Setup our database and DAOs
     */
    public void setupDatabase() throws Exception {

        personDao = DaoManager.createDao(connectionSource, Person.class);

        // if you need to create the table
        TableUtils.createTableIfNotExists(connectionSource, Person.class);
    }

    public void savePerson(Person p) throws SQLException {
        personDao.create(p);
    }
    
    public void editPerson(Person p) throws SQLException {
        personDao.update(p);
    }
    
    public void deletePerson(Person p) throws SQLException {
        personDao.delete(p);
    }
   

}
