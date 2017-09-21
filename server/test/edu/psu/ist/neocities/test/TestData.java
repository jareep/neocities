package edu.psu.ist.neocities.test;

import java.util.ArrayList;

public class TestData {

	ArrayList<Integer> test = new ArrayList<Integer>();
	
	void add(int k){
		
		test.add(k);
	}
	
	void removeAll(){
		
		test.clear();
	}
	
	void showAllContents(){
		
	
		
		for(int h = 0; h<test.size(); h++){
			
			System.out.println(test.get(h));
		}
		
		if(test.size()==0){System.out.println("Nothing to show");}
		
		
		
	}
	
	public static void main(String args[])
	{
		TestData T1 = new TestData();
		
		T1.add(10);
		T1.add(4);
		T1.add(12);
		T1.showAllContents();
		T1.removeAll();
		T1.showAllContents();
		
	}
	
	
}
