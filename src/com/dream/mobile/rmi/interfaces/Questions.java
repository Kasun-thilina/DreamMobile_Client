package com.dream.mobile.rmi.interfaces;

import java.util.ArrayList;

public class Questions implements java.io.Serializable {   
	   private ArrayList<String>  Questions=new ArrayList<String>();
	   private ArrayList<String>  Answer_01=new ArrayList<String>();
	   private ArrayList<String>  Answer_02=new ArrayList<String>();
	   private ArrayList<String>  Answer_03=new ArrayList<String>();
	   private ArrayList<String>  Answer_04=new ArrayList<String>();
	   private ArrayList<String>  Answer_05=new ArrayList<String>();

	public ArrayList<String> getQuestions() {
		return Questions;
	}
	public void setQuestions(ArrayList<String> questions) {
		Questions = questions;
	}
	public ArrayList<String> getAnswer_01() {
		return Answer_01;
	}
	public ArrayList<String> getAnswer_05() {
		return Answer_05;
	}
	public void setAnswer_05(ArrayList<String> answer_05) {
		Answer_05 = answer_05;
	}
	public void setAnswer_01(ArrayList<String> answer_01) {
		Answer_01 = answer_01;
	}
	public ArrayList<String> getAnswer_02() {
		return Answer_02;
	}
	public void setAnswer_02(ArrayList<String> answer_02) {
		Answer_02 = answer_02;
	}
	public ArrayList<String> getAnswer_03() {
		return Answer_03;
	}
	public void setAnswer_03(ArrayList<String> answer_03) {
		Answer_03 = answer_03;
	}
	public ArrayList<String> getAnswer_04() {
		return Answer_04;
	}
	public void setAnswer_04(ArrayList<String> answer_04) {
		Answer_04 = answer_04;
	}


}