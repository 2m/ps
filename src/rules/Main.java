package rules;

import rules.model.FileInputModel;
import rules.view.FileInputViewPanel;
import rules.controller.FileInputController;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.UIManager;
import rules.controller.AlgoController;
import rules.model.AlgoModel;
import rules.model.LogModel;
import rules.view.AlgoView;
import rules.view.LogView;

public class Main {

	public Main(String mode) {

        FileInputModel fileInputModel = new FileInputModel();
        LogModel logModel = new LogModel();
        AlgoModel algoModel = new AlgoModel(fileInputModel, logModel);

		FileInputController fileInputController = new FileInputController();
        AlgoController algoController = new AlgoController();

		FileInputViewPanel fileInputViewPanel = new FileInputViewPanel(fileInputController);
        LogView logView = new LogView(algoController);
        AlgoView algoView = new AlgoView(algoController, mode);

		fileInputController.addView(fileInputViewPanel);
        fileInputController.addModel(fileInputModel);
        fileInputController.addModel(algoModel);

        algoController.addView(algoView);
        algoController.addModel(algoModel);
        algoController.addView(logView);
        algoController.addModel(logModel);

		fileInputModel.initDefault();
        logModel.initDefault();

        JFrame displayFrame = new JFrame("Rules");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(fileInputViewPanel, BorderLayout.CENTER);
        panel.add(algoView, BorderLayout.PAGE_END);

        displayFrame.getContentPane().add(panel, BorderLayout.WEST);
        displayFrame.getContentPane().add(logView, BorderLayout.CENTER);

        displayFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        displayFrame.pack();

        displayFrame.setVisible(true);
	}

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {
            System.out.println("Look and feel was not found.");
        }

        final String mode;

        if (args.length == 1) {
            mode = args[0];
        } else {
            mode = "";
        }

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main(mode);
            }
        });
    }

}
