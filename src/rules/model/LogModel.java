/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rules.model;

import rules.controller.AlgoController;

/**
 *
 * @author Martynas
 */
public class LogModel extends AbstractModel {

    private String currentLog;

    public void initDefault() {
        currentLog = "";
    }

    public void log(String format, Object... args) {
        currentLog += String.format(format, args);
        firePropertyChange(AlgoController.LOG, "", currentLog);
    }

    public void clear() {
        currentLog = "";
        firePropertyChange(AlgoController.LOG, "a", currentLog);
    }

}
