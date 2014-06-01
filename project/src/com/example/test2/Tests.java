package com.example.test2;

import junit.framework.Assert;

import org.junit.Test;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Tests {
	
	int n = 5;
	int m = 3;
	TextView sum_mark1[];
	EditText lab_value1[];
	CheckBox lab1[][];
	int l1[][];
	
	void calc1() {
		int temp;
		for (int i = 0; i < n; i++) {
			sum_mark1[i].setText("0");
			for (int j = 0; j < m; j++) {
				if (lab1[i][j].isChecked()) {
					temp = Integer.parseInt(sum_mark1[i].getText().toString())+Integer.parseInt(lab_value1[j].getText().toString());
					sum_mark1[i].setText(Integer.toString(temp));
					l1[i][j] = 1;
				}
				else 
					l1[i][j] = 0;

			}
		}
	}
		@Test
	public void Caltest(){
		TextView result[] = new TextView[n];
	  	sum_mark1 = new TextView[n];
	  	lab_value1 = new EditText[m];
	  	lab1 = new CheckBox[n][m];
	  	for (int i = 0; i < n; i++) {
	  		sum_mark1[i].setText(0);
	  		for (int j = 0; j < m; j++) {
	  			if ((j+i)/2 == 0) {
	  				lab1[i][j].setChecked(true);
	  			}
	  			else {
	  	  			lab1[i][j].setChecked(false);
	  			}
	  		}
	 	}
			lab_value1[0].setText(7);
	  		lab_value1[1].setText(2);
	  		lab_value1[2].setText(5);
	  	calc1();	
	  	result[0].setText(12);
	  	result[1].setText(2);
	  	result[2].setText(12);
	  	result[3].setText(2);
	  	result[4].setText(12);
	  	
	  	Assert.assertEquals(result, sum_mark1);
	}
}
