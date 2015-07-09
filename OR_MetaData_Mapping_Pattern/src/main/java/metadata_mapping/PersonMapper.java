/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metadata_mapping;

import db.DB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;
import model.Person;
import org.omg.CORBA.portable.ApplicationException;

/**
 *
 * @author Bassini
 */
public class PersonMapper  extends Mapper{
    DataMap dataMap;

    public DataMap getDataMap() {
        return dataMap;
    }

    public void setDataMap(DataMap dataMap) {
        this.dataMap = dataMap;
    }

    
    
    
    
    protected void loadDataMap() {

        dataMap = new DataMap(Person.class, "people");
        dataMap.addColumn("lastname", "varchar", "lastName");
        dataMap.addColumn("firstname", "varchar", "firstName");
        dataMap.addColumn("number_of_dependents", "int", "numberOfDependents");
    }

    public Person find(Long key) {
        return (Person) findObject(key);
    }

    public Set findLastNamesLike(String pattern) {
        String sql  = "SELECT" + dataMap.columnList()
                    + "	FROM" + dataMap.getTableName()
                    + "	WHERE UPPER(lastName) like UPPER(?)";
        PreparedStatement stmt = null;

        ResultSet rs = null;
        try {

            stmt = DB.prepare(sql);
            stmt.setString(1, pattern);
            rs = stmt.executeQuery();
            return loadAll(rs);

        } catch (Exception e) {
            System.out.println("erro: "+e.getMessage());
        } finally {
            DB.cleanUp(stmt, rs);
        }
        return null;
    }

}
