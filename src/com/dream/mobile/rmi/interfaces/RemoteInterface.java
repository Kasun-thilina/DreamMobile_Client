package com.dream.mobile.rmi.interfaces;
import java.rmi.Remote; 
import java.rmi.RemoteException;
import java.sql.Array;
import java.util.*;

/**
 * 
 * @author Kasun Thilina
 * The interface methods for various functionalities with Database Access using SQL queries
 */
public interface RemoteInterface extends Remote {  
	/**
	 * method returns all the questions and answers fetched from database
	 * @return
	 * @throws Exception
	 */
   public List<Questions> getQuestions() throws Exception;  
   /**
	 * saveAnswer saves the data coming from the client to the analytics client of the QuestionsDB Database
	 * @return String
	 * @throws Exception
	 */
   public String saveAnswer(ArrayList<String> answers) throws Exception;
   /**
	 * method returns all the  answers fetched from database according to the coloumn name
	 * @return ArrayList<String>
	 * @throws Exception
	 */
   public ArrayList<Analytics> getAnalytics() throws Exception;
   /**
	 * method returns weather the username and password are correct checked with the database.Returns trues if they are valid
	 * @return Boolean 
	 * @throws Exception
	 */
   public Boolean adminLogin(String username,String password) throws Exception;
   /**
	 * 
	 * getOnlyAnswers method gets answers from database using the answers index
	 * @return ArrayList<String>
	 * @throws Exception
	 */
   public ArrayList<String> getOnlyAnswers(int coloumnID) throws Exception;

   /**
    * This method contains the API Call to the site Quickchart.io
    * method sends the data to Quickchart.io after parsing them as a JSONObject and returns the url to the client
    */
   public String getChart(ArrayList<String> labels,ArrayList<Integer> values,String heading,String chartType) throws Exception;
   
   /**
    * 
    * 
    */
   public int getItemCount(String coloumnName,String itemName) throws Exception;

}