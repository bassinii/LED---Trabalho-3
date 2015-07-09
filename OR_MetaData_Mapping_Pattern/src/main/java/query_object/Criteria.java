/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package query_object;

import metadata_mapping.DataMap;

/**
 *
 * @author Bassini
 */
public class Criteria {

    private String sqlOperator;
    protected String field;
    protected Object value;

    public static Criteria greaterThan(String fieldName, int value) {
        return Criteria.greaterThan(fieldName, new Integer(value));
    }

    public static Criteria greaterThan(String fieldName, Object value) {
        return new Criteria(" > ", fieldName, value);
    }

    public Criteria(String sql, String field, Object value) {
        this.sqlOperator = sql;
        this.field = field;
        this.value = value;
    }

    public String generateSql(DataMap dataMap) throws Exception {
        return dataMap.getColumnForField(field) + sqlOperator + value;
    }

}
