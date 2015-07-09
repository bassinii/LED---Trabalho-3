/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metadata_mapping;

import db.DB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import model.DomainObject;

/**
 *
 * @author Bassini
 */
public class Mapper {

    //private UnitOfWork uow;
    protected DataMap dataMap;

    public Object findObject(Long key) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        DomainObject result = null;

//        if (uow.isLoaded(key)) {
//            return uow.getObject(key);
//        }
        String sql = "SELECT " + dataMap.columnList() + " FROM " + dataMap.getTableName() + " WHERE ID = ? ";

        try {

            stmt = DB.prepare(sql);
            stmt.setLong(1, key.longValue());
            rs = stmt.executeQuery();
            rs.next();

            result = load(rs);

        } catch (Exception e) {
            System.out.println("Erro:" + e.getLocalizedMessage());;
        } finally {
            DB.cleanUp(stmt, rs);

        }
        return result;
    }

    public DomainObject load(ResultSet rs) throws Exception {

        Long key = new Long(rs.getLong("ID"));
//
//        if (uow.isLoaded(key)) {
//            return uow.getObject(key);
//        }

        DomainObject result = (DomainObject) dataMap.getDomainClass().newInstance();

        result.setID(key);

        //uow.registerClean(result);
        loadFields(rs, result);
        return result;

    }

    private void loadFields(ResultSet rs, DomainObject result) throws Exception {
        for (Iterator it = dataMap.getColumns(); it.hasNext();) {

            ColumnMap columnMap = (ColumnMap) it.next();

            Object columnValue = rs.getObject(columnMap.getColumnName());
            columnMap.setField(result, columnValue);

        }

    }

    public void update(DomainObject obj) {
        String sql = "UPDATE	" + dataMap.getTableName() + dataMap.updateList() + " WHERE ID = ? ";

        PreparedStatement stmt = null;
        try {

            stmt = DB.prepare(sql);
            int argCount = 1;

            for (Iterator it = dataMap.getColumns(); it.hasNext();) {
                ColumnMap col = (ColumnMap) it.next();
                stmt.setObject(argCount++, col.getValue(obj));
            }

            stmt.setLong(argCount, obj.getID().longValue());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            DB.cleanUp(stmt);

        }
    }

    public Long insert(DomainObject obj) {

        String sql = "INSERT INTO " + dataMap.getTableName() + " VALUES (?" + dataMap.insertList() + ")";
        PreparedStatement stmt = null;
        try {

            stmt = DB.prepare(sql);
            stmt.setObject(1, obj.getID());
            int argCount = 2;

            for (Iterator it = dataMap.getColumns(); it.hasNext();) {
                ColumnMap col = (ColumnMap) it.next();
                stmt.setObject(argCount++, col.getValue(obj));

            }
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
        } finally {
            DB.cleanUp(stmt);

        }
        return obj.getID();
    }

    public Set findObjectsWhere(String whereClause) {

        String sql = "SELECT" + dataMap.columnList() + " FROM " + dataMap.getTableName()
                + " WHERE "+ whereClause;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Set result = new HashSet();
        try {

            stmt = DB.prepare(sql);
            rs = stmt.executeQuery();
            result = loadAll(rs);

        } catch (Exception e) {

            System.out.println("Erro: "+e.getMessage());
        } finally {
            DB.cleanUp(stmt, rs);

        }
        return result;

    }

    public Set loadAll(ResultSet rs) throws Exception {

        Set result = new HashSet();
        while (rs.next()) {

            DomainObject newObj = (DomainObject) dataMap.getDomainClass().newInstance();
            newObj = load(rs);

            result.add(newObj);
        }

        return result;
    }

}
