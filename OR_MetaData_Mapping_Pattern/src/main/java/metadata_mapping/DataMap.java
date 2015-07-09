/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metadata_mapping;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Person;

/**
 *
 * @author Bassini
 */
public class DataMap {

    private Class domainClass;
    private String tableName;
    private List columnMaps = new ArrayList();

    public DataMap(Class<Person> aClass, String tableName) {
        this.domainClass = aClass;
        this.tableName = tableName;

    }

    public String columnList() {
        StringBuffer result = new StringBuffer("	ID");

        for (Iterator it = columnMaps.iterator(); it.hasNext();) {
            result.append(",");

            ColumnMap columnMap = (ColumnMap) it.next();
            result.append(columnMap.getColumnName());

        }
        return result.toString();

    }

    public String getTableName() {
        return tableName;

    }

    public String insertList() {

        StringBuffer result = new StringBuffer();
        for (int i = 0; i < columnMaps.size(); i++) {

            result.append(",");
            result.append("?");
        }

        return result.toString();
    }

    public String updateList() {

        StringBuffer result = new StringBuffer(" SET ");

        for (Iterator it = columnMaps.iterator(); it.hasNext();) {
            ColumnMap columnMap = (ColumnMap) it.next();
            result.append(columnMap.getColumnName());
            result.append("=?,");

        }

        result.setLength(result.length() - 1);
        return result.toString();

    }

    public Iterator getColumns() {
        return Collections.unmodifiableCollection(columnMaps).iterator();

    }

    void addColumn(String columnName, String fieldType, String fieldName) {
        ColumnMap coluna;
        try {
            coluna = new ColumnMap(columnName, fieldName, this);
            this.columnMaps.add(coluna);
        } catch (Exception ex) {
            Logger.getLogger(DataMap.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Class getDomainClass() {
        return domainClass;
    }

    public void setDomainClass(Class domainClass) {
        this.domainClass = domainClass;
    }

    public List getColumnMaps() {
        return columnMaps;
    }

    public void setColumnMaps(List columnMaps) {
        this.columnMaps = columnMaps;
    }

    public String getColumnForField(String fieldName) throws Exception {
        for (Iterator it = getColumns(); it.hasNext();) {

            ColumnMap columnMap = (ColumnMap) it.next();

            if (columnMap.getFieldName().equals(fieldName)) {
                return columnMap.getColumnName();
            }

        }
        throw new Exception("Unable to find column for " + fieldName);
    }

}
