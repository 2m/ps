/*
 * DocumentModel.java
 *
 * Created on January 22, 2007, 3:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package rules.model;

import rules.controller.FileInputController;
import java.beans.PropertyChangeEvent;
import java.util.*;
import java.io.*;

/**
 * A sample class that mimics some properties found in a document, including
 * its name, width, and height.
 *
 * @author Robert Eckstein
 */
public class FileInputModel extends AbstractModel
{
    public static final String INPUT_DIR = "input";

    private Vector<String> fileList;
    private String inputFileName;

    private Vector<Fact> inputFacts;
    private Vector<Production> inputProductions;
    private Fact inputTarget;

    /**
     * Default constructor
     */
    public FileInputModel()
    {

    }

    /**
     * Provides the means to set or reset the model to a default state.
     */
    public void initDefault() {

		File dir = new File(INPUT_DIR);

        if (!dir.isDirectory()) {
            dir.mkdir();
        }

		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File file) {
				return !file.isDirectory();
			}
		};

        setInputProductions(null);
        setInputFacts(null);
        setInputTarget(null);

        try {
            this.setFileList(new Vector<String>(Arrays.asList(dir.list())));
        } catch (Exception ex) {
            // input dir could not be created
            System.out.println("Input directory could not be created.");
        }        
    }


    public void parseInputFile() {
        Vector<Production> productions = null;
        Vector<Fact> facts = null;
        Fact target = null;

        try {
            FileInputStream fstream = new FileInputStream(INPUT_DIR+"/"+inputFileName);
            DataInputStream dis = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(dis));

            String line;

            // read productions
            productions = new Vector<Production>();

            int productionCount = 0;
            while ((line = br.readLine()).length() != 0)   {
				if (line.startsWith("//")) {
					continue;
				}

                Vector<Fact> in = new Vector<Fact>();
                Vector<Fact> out = new Vector<Fact>();

                out.add(new Fact(line.substring(0, 1)));

				int index = 1;
				while (index < line.length() && !line.substring(index, index+1).equals(" ") && !line.substring(index, index+1).equals("\t")) {
					in.add(new Fact(line.substring(index, index+1)));
					index++;
				}

                productionCount++;
                productions.add(new Production("R"+productionCount, in, out));
            }
            //System.out.println(productions);

            // read initial facts
            line = br.readLine();
			while (line.startsWith("//")) {
				line = br.readLine();
			}

            facts = new Vector<Fact>();
            for (int i = 0; i < line.length(); i++) {
                facts.add(new Fact(line.substring(i, i+1)));
            }
            //System.out.println("gdb: "+gdb);

            // read target fact
            br.readLine();
            line = br.readLine();
			while (line.startsWith("//")) {
				line = br.readLine();
			}

            target = new Fact(line.substring(0, 1));
            //System.out.println("target: "+target);

            dis.close();
        }
        catch (Exception e) {
            System.out.println("Klaida skaitant duomenu byla.");
            e.printStackTrace();			
        }

        setInputProductions(productions);
        setInputFacts(facts);
        setInputTarget(target);
    }

    //  Accessors
    public Vector<String> getFileList() {
        return fileList;
    }

    public void setFileList(Vector<String> fileList) {
        Vector<String> oldFileList = this.fileList;
        this.fileList = fileList;

		firePropertyChange(FileInputController.INPUT_FILE_LIST, oldFileList, fileList);
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public void setInputFileName(String name) {
        String oldName = this.inputFileName;
        this.inputFileName = name;

        parseInputFile();
        //firePropertyChange(FileInputController.INPUT_FILE_NAME, oldName, name);
    }

    public void setInputProductions(Vector<Production> productions) {
        Vector<Production> oldProductions = this.inputProductions;
        this.inputProductions = productions;

		firePropertyChange(FileInputController.INPUT_PRODUCTIONS, oldProductions, inputProductions);
    }

    public Vector<Production> getInputProductions() {
        return inputProductions;
    }

    public void setInputFacts(Vector<Fact> facts) {
        Vector<Fact> oldFacts = this.inputFacts;
        this.inputFacts = facts;

		firePropertyChange(FileInputController.INPUT_FACTS, oldFacts, inputFacts);
    }

    public Vector<Fact> getInputFacts() {
        return inputFacts;
    }

    public void setInputTarget(Fact f) {
        Fact oldFact = this.inputTarget;
        this.inputTarget = f;

		firePropertyChange(FileInputController.INPUT_TARGET, oldFact, inputTarget);
    }

    public Fact getInputTarget() {
        return inputTarget;
    }
}


