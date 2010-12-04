/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rules.model;

/**
 *
 * @author Martynas
 */
import java.util.*;

public class ForwardChaining extends AbstractAlgo {

    public ForwardChaining(Vector<Production> productions, Vector<Fact> gdb, Fact target, LogModel logger) {

        super(productions, gdb, target, logger);

        System.err.println(this);
    }

    public boolean iterate() {
        
        if (productionNum < productions.size()) { // {2}

            // check for target
            if (gdb.contains(target)) { // {4}
                logger.log("Tikslas %s pasiektas.\n", target.getName());
                finished = true;
                return true; // {4}
            }

            Production currentProduction = productions.elementAt(productionNum);

            if (usedProductions.contains(currentProduction) || gdb.containsAll(currentProduction.getOutput())) { // {6}
                // selected production has been already used
				// OR
                // selected production output is already in GDB,
                // either way take another production
                productionNum++; // {12}
                return true;
            }

            if (currentProduction.canBeAppliedOn(gdb)) { // {5}
                // add production results to gdb
                gdb.addAll(currentProduction.getOutput()); // {8}

                // mark this productions as used so it is never used again
                usedProductions.add(currentProduction); // {9}

                actionNum++;
                logger.log("%d. Rasta tasyklė %s, gautas faktas %s.\n", actionNum, currentProduction.getName(), currentProduction.getOutput());
                logger.log("Turimi faktai: %s.\n", gdb);
                logger.log("\n");

                // start picking productions from the start
                productionNum = 0; // {10}
            }
            else {
                // get another productions
                productionNum++; // {13}
            }
        }
        else {
            // chaining failed
            finished = true;
            return false; // {15}
        }

        //System.out.println(this);

        // iteration successful
        return true;
    }

}