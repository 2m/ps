/*
 * DefaultController.java
 *
 * Created on January 22, 2007, 8:42 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package rules.controller;

import java.awt.Font;

/**
 * This controller implements the required methods and provides the properties
 * necessary to work with the DisplayViewPanel and PropertyViewPanel views. Each of
 * methods in this class can be called upon by the views to update to state of the
 * registered models.
 *
 * @author Robert Eckstein
 */
public class AlgoController extends AbstractController
{

    //  Properties this controller expects to be stored in one or more registered models

    /**
     * The document's name
     */
    public static final String ALGO_NAME = "AlgorithmName";
    public static final String ONE_ITER = "OneIteration";
    public static final String ALL_ITER = "AllIterations";
    public static final String LOG = "Log";
    public static final String SELECTED_PRODUCTION = "SelectedProduction";
    public static final String GDB = "GDB";

    /**
     * Change the document name in the model
     * @param newName The new document name
     */
    public void changeAlgoName(String newName) {
        setModelProperty(ALGO_NAME, newName);
    }

    public void changeIterations(Integer iterations) {
        if (iterations.equals(1)) {
            setModelProperty(ONE_ITER, true);
        }
        else {
            setModelProperty(ALL_ITER, true);
        }
    }

}
