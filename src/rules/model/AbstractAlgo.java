/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rules.model;

import java.util.Vector;

/**
 *
 * @author Martynas
 */
public abstract class AbstractAlgo {

    protected boolean finished = false;
    protected int productionNum = 0;
    protected Vector<Fact> gdb;
    protected Vector<Production> productions;
    protected Vector<Production> usedProductions;
    protected Fact target;
    protected int actionNum = 0;

    protected LogModel logger;

    public AbstractAlgo(Vector<Production> productions, Vector<Fact> gdb, Fact target, LogModel logger) {

        this.logger = logger;

        try {
            this.productions = (Vector<Production>)productions.clone();
            this.gdb = (Vector<Fact>)gdb.clone();
            this.target = (Fact)target.clone();
        }
        catch (Exception ex) {
            System.err.println("Error while cloning.");
            ex.printStackTrace();
        }

        usedProductions = new Vector<Production>();
    }

    public String getAnswer() {
         if (usedProductions.size() == 0) {
             return "Tuščia taisyklių seka.";
         } else {
            String answer = "{"+usedProductions.elementAt(0).getName();
            for (int i = 1; i < usedProductions.size(); i++) {
                answer += ", "+usedProductions.elementAt(i).getName();
            }
            return answer+"}";
         }
    }

    public String toString() {
        String s = "Taisyklės:\n";
        for (int i = 0; i < productions.size(); i++) {
            s += productions.elementAt(i)+"\n";
        }

        s += "Pradiniai faktai:\n";
        s += gdb+"\n";

        s += "Tikslas:\n";
        s += target+"\n";

        return s;
    }    

    public boolean isFinished() {
        return finished;
    }

    public int getSelectedProduction() {
        return productionNum;
    }

    public String getGDB() {
        return gdb.toString();
    }

    public abstract boolean iterate();
}
