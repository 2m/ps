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
public class FileInputController extends AbstractController
{

    //  Properties this controller expects to be stored in one or more registered models

    /**
     * The document's name
     */
    public static final String INPUT_FILE_LIST = "InputFileList";
    public static final String INPUT_FILE_NAME = "InputFileName";
    public static final String INPUT_PRODUCTIONS = "InputProductions";
    public static final String INPUT_FACTS = "InputFacts";
    public static final String INPUT_TARGET = "InputTarget";

    /**
     * Change the document name in the model
     * @param newName The new document name
     */
    public void changeInputFileList(String newName) {
        setModelProperty(INPUT_FILE_LIST, newName);
    }

    public void changeInputFileName(String newName) {
        setModelProperty(INPUT_FILE_NAME, newName);
    }
}
