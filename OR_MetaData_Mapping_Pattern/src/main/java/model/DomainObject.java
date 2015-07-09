/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Bassini
 */
public class DomainObject {

    private Long ID;

    public DomainObject() {}
    
    public DomainObject(Long ID) {
        this.ID = ID;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

}
