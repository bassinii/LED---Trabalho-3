/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import metadata_mapping.DataMap;
import metadata_mapping.PersonMapper;
import model.Person;
import query_object.Criteria;
import query_object.QueryObject;

/**
 *
 * @author Tiago
 */
public class PersonRepository {

    public Set dependentsOf(Person person) {

        Criteria criteria = new Criteria("=","benfactor", person.getID());
        return matching(criteria);
    }
    
    public Set matching(Criteria criteria){
        
        QueryObject queryObject = new QueryObject( );
        List criterios = new ArrayList();
        criterios.add(criteria);
        
        
        PersonMapper mapper = new PersonMapper();
        queryObject.setMapper(mapper);
        
        queryObject.setCriteria(criterios);
        queryObject.setMapper(mapper);
        
        return queryObject.execute(mapper.getDataMap());
    }
    

}