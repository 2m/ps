/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rules.model;

import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author Martynas
 */
public class Production {

    private Vector<Fact> in;
    private Vector<Fact> out;

    private String name;

    public Production(String name, Vector<Fact> in, Vector<Fact> out) {
        this.name = name;
        this.in = in;
        this.out = out;
    }

    public boolean canBeAppliedOn(Vector<Fact> gdb) {
        Iterator<Fact> iter = in.iterator();
        while (iter.hasNext()) {
            Fact f = iter.next();

            if (!gdb.contains(f)) {
                // required input fact is not in gdb
                return false;
            }
        }

        // all required facts are in gdb
        return true;
    }

    public Vector<Fact> getInput() {
        return in;
    }

    public Vector<Fact> getOutput() {
        return out;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return String.format("%s: %s -> %s", name, in, out);
    }

}

