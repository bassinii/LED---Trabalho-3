/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.Set;
import repository.PersonRepository;

/**
 *
 * @author Bassini
 */
public class Person extends DomainObject {

    private String lastname;
    private String firstname;
    private int numberOfDependents;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getNumberOfDependents() {
        return numberOfDependents;
    }

    public void setNumberOfDependents(int numberOfDependents) {
        this.numberOfDependents = numberOfDependents;
    }
    
    public Set dependents() {
        PersonRepository repository = new PersonRepository();
        return repository.dependentsOf(this);

    }

}
