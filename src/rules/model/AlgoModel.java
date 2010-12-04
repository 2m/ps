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
public class AlgoModel extends AbstractModel {

    FileInputModel fileInputModel;
    LogModel logModel;

    AbstractAlgo algo;
    int selectedProduction = 0;
    String gdb = "";

    public AlgoModel(FileInputModel fileInputModel, LogModel logModel) {
        this.fileInputModel = fileInputModel;
        this.logModel = logModel;
    }

    public void setAlgorithmName(String algoName) {
        System.err.println("Creating algo.");
        if (algoName.equals("Forward")) {
            algo = new ForwardChaining(fileInputModel.getInputProductions(), fileInputModel.getInputFacts(), fileInputModel.getInputTarget(), logModel);
        } else {
            algo = new BackwardChaining(fileInputModel.getInputProductions(), fileInputModel.getInputFacts(), fileInputModel.getInputTarget(), logModel);
        }

        updateView(true);
        logModel.log("\nPrograma pradeda darbą.\n");
    }

    public void setOneIteration(Boolean b) {

        // iterate if not already finished
        if (!algo.isFinished()) {
            
            if (algo.iterate()) {

                // iteration successfully completed
                if (algo.isFinished()) {

                    // this was last iteration, print results
                    logModel.log("\nSprendimas rastas.\n");
                    logModel.log("Rezultatas: %s", algo.getAnswer());
                }

            } else {
                // iteration did not succeed.
                logModel.log("\nSprendimas neegzistuoja.\n");
            }
        }

        updateView();
    }

    public void setAllIterations(Boolean b) {

        if (algo.isFinished()) {
            return;
        }

        boolean success = false;

        while (!algo.isFinished()) {
            success = algo.iterate();
            updateView();
        }

        if (success) {
            logModel.log("\nSprendimas rastas.\n");
            logModel.log("Rezultatas: %s", algo.getAnswer());
        } else {
            logModel.log("\nSprendimas neegzistuoja.\n");
        }
    }

    public void updateView() {
        updateView(false);
    }

    public void updateView(boolean clear) {

        if (clear) {
            selectedProduction = -1;
            logModel.clear();
        }

        firePropertyChange(AlgoController.SELECTED_PRODUCTION, -2, selectedProduction);
        selectedProduction = algo.getSelectedProduction();

        gdb = algo.getGDB();
        firePropertyChange(AlgoController.GDB, "a", gdb);
    }

}
