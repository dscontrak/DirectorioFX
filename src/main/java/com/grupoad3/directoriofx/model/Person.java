/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoad3.directoriofx.model;

import com.grupoad3.directoriofx.db.LocalDatePersister;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author daniel_serna
 */
@DatabaseTable(tableName = "persons")
public class Person {

    @DatabaseField(generatedId = true, columnName = "id")
    private int _id;
    private IntegerProperty id;

    @DatabaseField(columnName = "firstName", canBeNull = false, index = true)
    private String _firstName;
    private StringProperty firstName;

    @DatabaseField(columnName = "lastName", canBeNull = false, index = true)
    private String _lastName;
    private StringProperty lastName;

    @DatabaseField(columnName = "street")
    private String _street;
    private StringProperty street;

    @DatabaseField(columnName = "postalCode")
    private int _postalCode;
    private IntegerProperty postalCode;

    @DatabaseField(columnName = "city")
    private String _city;
    private StringProperty city;

    @DatabaseField(columnName = "birthday", persisterClass = LocalDatePersister.class)
    private LocalDate _birthday;
    private ObjectProperty<LocalDate> birthday;

    /**
     * Default constructor.
     */
    public Person() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param firstName
     * @param lastName
     */
    public Person(String firstName, String lastName) {
        this.id = new SimpleIntegerProperty(1);

        //this.firstName = new SimpleStringProperty(firstName);
        //this.lastName = new SimpleStringProperty(lastName);
        this._firstName = firstName;
        this._lastName = lastName;

        // Some initial dummy data, just for convenient testing.
        /*this.street = new SimpleStringProperty("some street");
        //new SimpleStringProperty
        this.postalCode = new SimpleIntegerProperty(1234);
        this.city = new SimpleStringProperty("some city");
        this.birthday = new SimpleObjectProperty<>(LocalDate.of(1999, 2, 21));*/
        _street = "some street";
        _postalCode = 23232;
        _city = "somo city";

        _birthday = LocalDate.of(1999, 2, 21);
    }

    public int getId() {
        if (id == null) {
            return _id;
        } else {
            return id.get();
        }
    }

    public void setId(Integer id) {
        if (this.id == null) {
            _id = id;
        } else {
            _id = id;
            this.id.set(id);
        }

    }

    public IntegerProperty idProperty() {
        if (id == null) {
            id = new SimpleIntegerProperty(this, "id", _id);
        }
        return id;
    }

    public String getFirstName() {
        if (firstName == null) {
            return _firstName;
        } else {
            return firstName.get();
        }

    }

    public void setFirstName(String firstName) {
        if (this.firstName == null) {
            _firstName = firstName;
        } else {
            _firstName = firstName;
            this.firstName.set(firstName);
        }
    }

    public StringProperty firstNameProperty() {
        if (firstName == null) {
            firstName = new SimpleStringProperty(this, "firstName", _firstName);
        }
        return firstName;
    }

    public String getLastName() {
        if (lastName == null) {
            return _lastName;
        } else {
            return lastName.get();
        }
    }

    public void setLastName(String lastName) {
        if (this.lastName == null) {
            _lastName = lastName;
        } else {
            _lastName = lastName;
            this.lastName.set(lastName);
        }

    }

    public StringProperty lastNameProperty() {
        if (lastName == null) {
            lastName = new SimpleStringProperty(this, "lastName", _lastName);
        }
        return lastName;
    }

    public String getStreet() {
        if (street == null) {
            return _street;
        } else {
            return street.get();
        }

    }

    public void setStreet(String street) {
        if (this.street == null) {
            _street = street;
        } else {
            _street = street;
            this.street.set(street);
        }

    }

    public StringProperty streetProperty() {
        if (street == null) {
            street = new SimpleStringProperty(this, "street", _street);
        }
        return street;
    }

    public int getPostalCode() {
        if (postalCode == null) {
            return _postalCode;
        } else {
            return postalCode.get();
        }
    }

    public void setPostalCode(int postalCode) {
        if (this.postalCode == null) {
            _postalCode = postalCode;
        } else {
            _postalCode = postalCode;
            this.postalCode.set(postalCode);

        }
    }

    public IntegerProperty postalCodeProperty() {
        if (postalCode == null) {
            postalCode = new SimpleIntegerProperty(this, "postalCode", _postalCode);
        }
        return postalCode;
    }

    public String getCity() {
        if (city == null) {
            return _city;
        } else {
            return city.get();
        }

    }

    public void setCity(String city) {
        if (this.city == null) {
            _city = city;
        } else {
            _city = city;
            this.city.set(city);
        }

    }

    public StringProperty cityProperty() {
        if (city == null) {
            city = new SimpleStringProperty(this, "city", _city);
        }
        return city;
    }

    public LocalDate getBirthday() {
        if (birthday == null) {
            return _birthday;
        } else {
            return birthday.get();
        }
    }

    public void setBirthday(LocalDate birthday) {
        if (this.birthday == null) {
            _birthday = birthday;
        } else {
            _birthday = birthday;
            this.birthday.set(birthday);
        }

    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        if (birthday == null) {
            birthday = new SimpleObjectProperty<>(this, "birthday", _birthday);
        }
        return birthday;
    }
}
