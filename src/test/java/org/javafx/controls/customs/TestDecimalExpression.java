package org.javafx.controls.customs;

public class TestDecimalExpression {
	
	
	
	public static void main(String[] args){
		String text = "1122.asda";
		if(text.matches("^-?[0-9]+([,\\.][0-9]*)?$")){
			System.out.println("Si");
		}else{
			System.out.println("NO");
		}
			
	}
}
