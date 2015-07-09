/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metadata_mapping;

import java.lang.reflect.Field;
import org.omg.CORBA.portable.ApplicationException;

/**
 *
 * @author Bassini
 */
public class ColumnMap {

    private String columnName;
    private String fieldName;
    private Field field;
    private DataMap dataMap;
    
    

    public ColumnMap(String columnName, String fieldName, DataMap dataMap) throws Exception {
        this.columnName = columnName;
        this.fieldName = fieldName;
        this.dataMap = dataMap;
        initField();

    }

    private void initField() throws Exception {
        try {

            field = dataMap.getClass().getDeclaredField(getFieldName());
            field.setAccessible(true);

        } catch (Exception e) {
            throw new Exception("unable	to set up field: " + fieldName, e);
        }
    }

    public void setField(Object result, Object columnValue) throws Exception {
        try {
            field.set(result, columnValue);

        } catch (Exception e) {
            throw new Exception("Error in setting " + fieldName, e);
        }

    }

    public Object getValue(Object subject) throws Exception {
        try {

            return field.get(subject);
        } catch (Exception e) {

            throw new Exception(e);
        }
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public DataMap getDataMap() {
        return dataMap;
    }

    public void setDataMap(DataMap dataMap) {
        this.dataMap = dataMap;
    }

}
