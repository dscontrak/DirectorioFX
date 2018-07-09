/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoad3.directoriofx.db;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.DateType;
import java.time.LocalDate;


/**
 *
 * @author daniel_serna
 */
public class LocalDateTime extends DateType{
    
    private static final LocalDateTime INSTANCE = new LocalDateTime();
    
    private LocalDateTime() {
        super(SqlType.DATE, new Class<?>[] { LocalDateTime.class });
    }
    
    public LocalDateTime(SqlType sqlType, Class<?>[] classes) {
        super(sqlType, classes);
    }
    
    public static LocalDateTime getSingleton() {
        return INSTANCE;
    }

    // De java a Base de datos
    @Override
    public Object javaToSqlArg(FieldType fieldType, Object javaObject) {
        LocalDate fecha = (LocalDate) javaObject;
        if(fecha == null){
            return null;
        }else{
            return java.sql.Date.valueOf(fecha);
        }
        
        
        //return super.javaToSqlArg(fieldType, javaObject); //To change body of generated methods, choose Tools | Templates.
    }
    
    // de Base de datos a java
    @Override
    public Object sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos) {
        //return super.sqlArgToJava(fieldType, sqlArg, columnPos); //To change body of generated methods, choose Tools | Templates.
        java.sql.Timestamp  fechaBD = (java.sql.Timestamp) sqlArg;
        if(fechaBD == null){
            return null;
        }else{
            return fechaBD.toLocalDateTime();
        }
    }
    
    
}
