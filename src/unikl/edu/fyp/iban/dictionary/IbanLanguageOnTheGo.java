package unikl.edu.fyp.iban.dictionary;

import java.io.IOException;

import unikl.edu.fyp.iban.dictionary.logic.DBHelper;
import unikl.edu.fyp.iban.dictionary.logic.IbanAdapter;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class IbanLanguageOnTheGo extends Activity {

private EditText edtKeyword;
private Button translateButton;
private Button viewAllButton;
private IbanAdapter db;
private Spinner menu;
private int position;

@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
importDatabase();
initUI();
db = new IbanAdapter(this);
}

private void initUI() {
edtKeyword = (EditText) findViewById(R.id.editText1);
translateButton = (Button) findViewById(R.id.buttonTranslate);
viewAllButton = (Button) findViewById(R.id.button2);
menu = (Spinner) findViewById(R.id.spinner1);
menu.setOnItemSelectedListener(new OnItemSelectedListener() {

@Override
public void onItemSelected(AdapterView<?> arg0, View arg1,
int arg2, long arg3) {
position = arg2;

}

@Override
public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub

}
});
translateButton.setOnClickListener(new OnClickListener() {

@Override
public void onClick(View v) {
showResult(edtKeyword.getText().toString());

}

});
viewAllButton.setOnClickListener(new OnClickListener() {

@Override
public void onClick(View v) {
if (position == 0) {
//	showAll2("iban");
showAll("iban");
} else if (position == 1) {
showAll("malay");	//showAll2("malay");

}else if (position == 2) {
	showAll("english");	//showAll2("malay");
	}
else if (position == 3) {
showAll("fruit");	//showAll2("malay");
}

else if (position == 4) {
showAll("food");	//showAll2("malay");
}
else if (position == 5) {
showAll("destination");	//showAll2("malay");
}
else if (position == 6) {
showAll("ethnic");	//showAll2("malay");
}
else if (position == 7) {
showAll("audio");	//showAll2("malay");
}

}

});

}

private void importDatabase() {
DBHelper dbHelper = new DBHelper(this);
try {
dbHelper.createDataBase();
} catch (IOException e) {
e.printStackTrace();
}

try {
dbHelper.openDataBase();
} catch (SQLException e) {
e.printStackTrace();
} finally {
dbHelper.close();
}
}

private void showResult(String keyword) {
db.open();
Cursor c = db.getDefinition(keyword, "iban");
try {
if (position == 0) {
c = db.getDefinition(keyword, "iban");
} else if (position == 1) {
c = db.getDefinition(keyword, "malay");
}else if (position == 2) {
c = db.getDefinition(keyword, "english");
} else if (position == 3) {
c = db.getDefinition(keyword, "fruit");
} else if (position == 4) {
c = db.getDefinition(keyword, "food");
} else if (position == 5) {
c = db.getDefinition(keyword, "destination");
} else if (position == 6) {
c = db.getDefinition(keyword, "ethnic");
} else if (position == 7) {
c = db.getDefinition(keyword, "audio");
}

c.getCount();
c.moveToFirst();
String result = c.getString(c.getColumnIndex("translate"));
String result2 = c.getString(c.getColumnIndex("desc"));
Toast.makeText(
this,
"Result: " + c.getCount() + " " + result + "\n\ndesc:"
+ result2, Toast.LENGTH_LONG).show();
} catch (Exception e) {
Toast.makeText(getApplicationContext(), "Sorry, No Data Available \nPlease select View All button to view all data available", Toast.LENGTH_LONG).show();
e.printStackTrace();
} finally {
db.close();
}
}

private void showAll2(String table) {
Log.i("hello", "fafafa");
db.open();
Cursor c = db.getAll(table);
c.getCount();
c.moveToFirst();
for (int i = 0; i < c.getCount(); i++) {
String result = c.getString(c.getColumnIndex("translate"));
String result2 = c.getString(c.getColumnIndex("desc"));

Toast.makeText(
this,
"Result: " + c.getCount() + " " + result + "\n\ndesc:"
+ result2, Toast.LENGTH_LONG).show();
c.moveToNext();
}
db.close();
}

private void showAll(String table) {
Log.i("hello", "show all in new screen");

Intent intent = new Intent(this, ViewAll.class);
intent.putExtra("table",table);
startActivity(intent);
}
}
