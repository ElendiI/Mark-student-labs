package com.example.test2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	int n,m;
	TableLayout tl1, tl2;
	Button btn1;
	EditText labs, stud;
	String filename;
	EditText txt[];
  	CheckBox lab[][];
  	TextView sum_mark[], labsText, studText, inviz;
  	TableRow row1[], row2[], val_labsRow, titleRow;
  	int l[][];
  	EditText lab_value[];
  	//Student testclass;
  	boolean created = false;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //ищем уже добавленные Views
        tl1 = (TableLayout) findViewById(R.id.tl1);
        tl2 = (TableLayout) findViewById(R.id.tl2);
        labsText = (TextView) findViewById(R.id.labsText);
        studText = (TextView) findViewById(R.id.studText);
        stud = (EditText) findViewById(R.id.EditStud);
        labs = (EditText) findViewById(R.id.EditLabs);
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(createTable);

		};
		//менюшка
	    public boolean onCreateOptionsMenu(Menu menu) {
	        menu.add(0, 1, 0, "Посчитать");
	        menu.add(0, 2, 1, "Удалить таблицу");
	        menu.add(0, 3, 2, "Сохранить");
	        menu.add(0, 4, 3, "Загрузить");

	        
	        return super.onCreateOptionsMenu(menu);
	      }

	    //обработка нажатия в менюшке
	    public boolean onOptionsItemSelected(MenuItem item){

	    	switch (item.getItemId()) {
	    	//пересчет
	    	case 1:	
		    	if (created) {
		    		calc();
		    		break;

		    	}
	    		//удаление
	    	case 2:
		    	if (created) {
		    		delete();
		    		break;
		    	}
		    		//сохранение
	    	case 3:
		    	if (created) {
		    		calc();
		    		save();
					break;
		    	}
				//загрузка из файла (пока одного)
	    	case 4:
	    	    	load();
	    	    	calc();
				break;
	    	}
			return false;
	    	
	    }

OnClickListener createTable = new OnClickListener(){

	public void onClick(View v) {
		String ss = stud.getText().toString();
		String ls = labs.getText().toString();
		
		if (!ss.isEmpty() & !ls.isEmpty()) {
			n = Integer.parseInt(ss);
			if (n > 50) 
				n = 50;
			m = Integer.parseInt(ls);
			if (m > 20) 
				m = 20;
			create();
		}
		else {
	        Toast.makeText(getApplicationContext(), "Введите данные",Toast.LENGTH_LONG).show();
		}
	 }
};
void delete() {
	tl1.removeAllViews();
	tl2.removeAllViews();
	btn1.setVisibility(View.VISIBLE);
	labs.setVisibility(View.VISIBLE);
	stud.setVisibility(View.VISIBLE);
	created = false;
}
void calc() {
	int temp;
	for (int i = 0; i < n; i++) {
		sum_mark[i].setText("0");
		for (int j = 0; j < m; j++) {
			if (lab[i][j].isChecked()) {
				temp = Integer.parseInt(sum_mark[i].getText().toString())+Integer.parseInt(lab_value[j].getText().toString());
				sum_mark[i].setText(Integer.toString(temp));
				l[i][j] = 1;
			}
			else 
				l[i][j] = 0;

		}
	}
}
void save() {
    //записываем данные
	BufferedWriter bw;
	try {
		bw = new BufferedWriter(new OutputStreamWriter(openFileOutput("testfile.txt", MODE_PRIVATE)));
		bw.write(n + "\r\n" );
		bw.write(m + "\r\n");
		for (int j = 0; j < m; j++) {
			bw.write(lab_value[j].getText().toString() + "\r\n");
		}
		for (int i = 0; i < n; i++){
			bw.write(txt[i].getText().toString() + "\r\n");
		}
		for (int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				bw.write(String.valueOf(l[i][j]));
			}
			bw.write("\r\n");
		}
	    bw.close();
	    
	} 
	catch (FileNotFoundException e) {
		e.printStackTrace();
	      Toast.makeText(this, "Нет файла", Toast.LENGTH_LONG).show();
	} 
	catch (IOException e) {
		e.printStackTrace();
	      Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show();
	}
}
void load() {
	try {
        // открываем поток для чтения
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(openFileInput("testfile.txt")));
        String str = "";
        int i;
        int j;
        //данные о размерах таблицы
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
	  	//пересоздаем содержимое активити, затираем лишнее
        create();
        //заполняем таблицу сохраненными ранее данными
        for (j = 0; j < m; j++) {
        	lab_value[j].setText(br.readLine());
        }
        for (i = 0; i < n; i++) {
        	txt[i].setText(br.readLine());
        }
        for (i = 0; i < n; i++) { //
        	str = br.readLine();
        	for (j = 0; j < m; j++) {
        			if (str.charAt(j) == '1'){
        					lab[i][j].setChecked(true);
        					
        			}
        	}
        }
        }
       
    catch (FileNotFoundException e) {
        e.printStackTrace();
        Toast.makeText(this, "нет файла", Toast.LENGTH_LONG).show();
      } 
    catch (IOException e) {
        e.printStackTrace();
        Toast.makeText(this, "ошибка", Toast.LENGTH_LONG).show();
      }
}
void create() {

		created = true;
		btn1.setVisibility(View.GONE);
		labs.setVisibility(View.GONE);
		stud.setVisibility(View.GONE);
		studText.setVisibility(View.GONE);
		labsText.setVisibility(View.GONE);
		txt = new EditText[n];
	  	lab = new CheckBox[n][m];
	  	lab_value = new EditText[m];
	  	sum_mark = new TextView[n];
	  	row2 = new TableRow[n];
	  	row1 = new TableRow[n];
	  	l = new int[n][m];
	  	inviz = new EditText(getApplicationContext());
	  	inviz.setVisibility(View.INVISIBLE);
	  	val_labsRow = new TableRow(getApplicationContext());
	  	titleRow = new TableRow(getApplicationContext());
	  	titleRow.addView(inviz);
	  	tl1.addView(titleRow);	
	  	for (int j = 0; j < m; j++) {
		  	lab_value[j] = new EditText(getApplicationContext());
		  	lab_value[j].setInputType(InputType.TYPE_CLASS_NUMBER);
		  	val_labsRow.addView(lab_value[j]);
	  	}
		  	tl2.addView(val_labsRow);
	  	for (int i = 0; i < n; i++) {
	  		txt[i] = new EditText(getApplicationContext());
	  		txt[i].setSingleLine();
	  		txt[i].setMinWidth(200);
	  		sum_mark[i] = new TextView(getApplicationContext());
	  		sum_mark[i].setTextSize(25);
	  		sum_mark[i].setText("0");
	  		//sum_mark[i].setTextSize(30);
	  		//sum_mark[i].setPadding(5, 4, 5, 3);
	  		row2[i] = new TableRow(getApplicationContext());
	  		for (int j = 0; j < m; j++) {
	  			l[i][j] = 0;
	  			lab[i][j] = new CheckBox(getApplicationContext());
	  			row2[i].addView(lab[i][j]);
	  		}
		row1[i] = new TableRow(getApplicationContext());
	  	row1[i].addView(txt[i]);
	  	row1[i].addView(sum_mark[i]);
	  	tl1.addView(row1[i]);
	  	tl2.addView(row2[i]);
	  					

	  	}
	  	//testclass.setStudCount(n);
	  	//testclass.setLabCount(m);
    	//testclass.setLabMatrix(l);
	}

	    
};

    
