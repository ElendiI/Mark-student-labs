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
//тест
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
};

    
