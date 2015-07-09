/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package query_object;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import metadata_mapping.DataMap;
import metadata_mapping.Mapper;

/**
 *
 * @author Bassini
 */
public class QueryObject {

    private Mapper mapper;
    private List criteria = new ArrayList();

    public Mapper getMapper() {
        return mapper;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    public List getCriteria() {
        return criteria;
    }

    public void setCriteria(List criteria) {
        this.criteria = criteria;
    }

    public Set execute(DataMap dataMap) {
        try {
            return mapper.findObjectsWhere(generateWhereClause(dataMap));
        } catch (Exception ex) {
            Logger.getLogger(QueryObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    private String generateWhereClause(DataMap dataMap) throws Exception {
        StringBuffer result = new StringBuffer();

        for (Iterator it = criteria.iterator(); it.hasNext();) {
            Criteria c = (Criteria) it.next();

            if (result.length() != 0) {
                result.append(" AND ");
            }

            result.append(c.generateSql(dataMap));
        }
        return result.toString();
    }

}
