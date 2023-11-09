/*
 *  Andrew Ward, ID: 15002106
 */
package quiz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrew Ward W15002106
 */
public final class Load {

    //attributes
    private String filePath;            //filepath where the Quizes are stored
    private File file;                  //File for reading theQuiz text file
    private List<String[]> questions;   //Stores each question as string array

    /**
     * Constructor for Load
     *
     */
    public Load() {
    }

    /**
     *
     * @return Questions as List of String[]
     */
    public List<String[]> getQuestions() {
        return this.questions;
    }

    /**
     * 
     * @return File
     */
    public File getFile() {
        return this.file;
    }

    /**
     * 
     * @return filePath
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     *
     * @param filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     *
     * @param file
     */
    public void setFile(String file) {
        this.file = new File(file + ".txt");
    }

    /**
     * Debug method to print all questions
     */
    public void printQuestions() {
        /*Print to check if done*/
        for (String[] question : this.getQuestions()) {
            for (String q : question) {
                //System.out.println(q);
            }
        }
    }

    /**
     *
     * @param questions
     */
    public void setQuestions(List<String[]> questions) {
        this.questions = questions;
    }

    /**
     *
     * @param question
     */
    public void addQuestions(String[] question) {
        this.questions.add(question);
    }

    /**
     *
     * @param file
     * @return List of String[]
     * @throws FileNotFoundException
     */
    public List<String[]> loadFile(File file) throws FileNotFoundException {
        //Create new List of String Arrays
        List<String[]> fileQuestions = new ArrayList<String[]>();

        try {
            //declaration and assignment
            Scanner sc;
            sc = new Scanner(file);

            //Read each line in file
            while (sc.hasNext()) {
                //Split line into tokens separated by ";"
                String token = sc.nextLine();
                String[] question = token.split(";");

                //Add question array to list
                fileQuestions.add(question);


            }

            //close file
            sc.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setQuestions(fileQuestions);
        return fileQuestions;

    }
}
