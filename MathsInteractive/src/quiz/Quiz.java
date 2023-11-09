/*
 *  Andrew Ward, ID: 15002106
 */
package quiz;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrew Ward W15002106
 */
public class Quiz {
    private static int quizID=1;
    private static int userID;

    /**
     * 
     * @param quizID 
     * @param userID 
     * @throws java.io.FileNotFoundException 
     */
    public Quiz(int quizID, int userID) throws FileNotFoundException {
        Quiz.quizID = quizID;
        Quiz.userID=userID;
//        System.out.println(getQuizTopic(quizID));
        String questionTopic = getQuizTopic(quizID);
        //load Questions
        List<String[]> questionFile = getFiles(questionTopic);
        //pass all questions to gui
        quizGUI qGUI = new quizGUI(questionFile, userID, quizID);
        qGUI.setLocationRelativeTo(null);
        qGUI.setVisible(true);
    }

    /**
     * 
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
//    public static void main(String[] args) throws FileNotFoundException {
//        System.out.println(getQuizTopic(quizID));   
////        String questionTopic = getQuizTopic(quizID);
//        //load Questions
//        List<String[]> questionFile = getFiles(questionTopic);
//        //pass all questions to gui
//        quizGUI qGUI;
//        qGUI = new quizGUI(questionFile, userID, quizID);
//
//    }

    /**
     * 
     * @return int guiz id
     */
    public int getQuizID() {
        return Quiz.quizID;
    }

    /**
     * 
     * @return Connection connection
     */
    public static Connection connectDB() {
        String url = "jdbc:sqlite:Database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Failed to connect to DB", "Connection Failed",
                    JOptionPane.PLAIN_MESSAGE);
        }
        return conn;
    }

    /**
     * Method to get text file containing all questions for a quiz
     * @param questionTopic
     * @return List<String[]>fileQuestions
     * @throws FileNotFoundException 
     */
    public static List<String[]> getFiles(String questionTopic) throws FileNotFoundException {
        //load Questions
        Load loadQuestions = new Load();
        loadQuestions.setFilePath("Questions/");
        loadQuestions.setFile(loadQuestions.getFilePath() + questionTopic);

        List<String[]> fileQuestions;
        fileQuestions = new ArrayList<>();

        //loads data from file, stores each row from file in String array, 
        //stores each array in the list, returns list
        fileQuestions = loadQuestions.loadFile(loadQuestions.getFile());
        return fileQuestions;
    }

    /**
     * Method to get the quiz topic from database by passing in the quiz id
     * @param quizID
     * @return quizTopic
     */
    public static String getQuizTopic(int quizID) {
        String quizTopic = "";
        String quizIDSQL = "SELECT title FROM Quiz WHERE quiz_id=" + quizID;
        try {
            Connection conn = connectDB();
            PreparedStatement pstmt = conn.prepareStatement(quizIDSQL);

            ResultSet quizDetails = pstmt.executeQuery();

            quizTopic = quizDetails.getString("title");
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return quizTopic;
    }
}
