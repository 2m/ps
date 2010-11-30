/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rules.model;

/**
 *
 * @author Martynas
 */
public class Fact {

    private String name;

    public Fact(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        return (o instanceof Fact && ((Fact)o).getName().equals(getName()));
    }

    public int hashCode() {
        return name.hashCode();
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return getName();
    }

    public Fact clone() {
        return new Fact(getName());
    }
}

