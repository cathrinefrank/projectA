package unikl.edu.fyp.iban.dictionary;

import java.io.IOException;
import java.util.ArrayList;

import unikl.edu.fyp.iban.dictionary.logic.DBHelper;
import unikl.edu.fyp.iban.dictionary.logic.IbanAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ViewAll extends Activity {
	private IbanAdapter db;
	 ArrayList<String> list1,list2,list3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_all);
		Intent in=getIntent();
		ListView listView = (ListView)findViewById(R.id.listView1);
		//importDatabase();
		db = new IbanAdapter(this);
		// test final ListView listview = (ListView) findViewById(R.id.listview);
	  
		  list1 = new ArrayList<String>();
		  list2 = new ArrayList<String>();
		list3 = new ArrayList<String>();
		showAll2(in.getStringExtra("table"));
		// list1.add("asdas");
			
		  final MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this,R.layout.row_view, list1,list2,list3);
	    listView.setAdapter(adapter);
		
	
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
	private void showAll2(String table) {
		Log.i("hello", "fafafa");
//hello
		db.open();
		Cursor c = db.getAll(table);
		c.getCount();
		c.moveToFirst();
		for (int i = 0; i < c.getCount(); i++) {
		//	list1.add(object)
			String result = c.getString(c.getColumnIndex("alpha"));
			String result2 = c.getString(c.getColumnIndex("translate"));
			String result3 = c.getString(c.getColumnIndex("desc"));
			list1.add(result);
			list2.add(result2);
			list3.add(result3);
				
			
			c.moveToNext();
		}
		db.close();
	}
	
	public class MySimpleArrayAdapter extends ArrayAdapter<String> {
		  private final Context context;
		  //private final String[] values;
		  ArrayList<String> values = new ArrayList<String>();
		  ArrayList<String> values1 = new ArrayList<String>();
		  ArrayList<String> values2 = new ArrayList<String>();
				  public MySimpleArrayAdapter(Context context, int rid,ArrayList<String> list, ArrayList<String> list2, ArrayList<String> list3) {
		    super(context, rid,list);
		    this.context = context;
		    this.values = list;
		    this.values1 = list2;
		    this.values2 = list3;
			  }

		  @Override
		  public View getView(int position, View convertView, ViewGroup parent) {
		    LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    View rowView = inflater.inflate(R.layout.row_view, parent, false);
		    TextView textView = (TextView) rowView.findViewById(R.id.textView1);
		     textView.setText(values.get(position));
		     TextView textView2 = (TextView) rowView.findViewById(R.id.textView2);
		     textView2.setText(values1.get(position));
		     TextView textView3 = (TextView) rowView.findViewById(R.id.textView3);
		     textView3.setText(values2.get(position));
		    // Change the icon for Windows and iPhone
		
		    return rowView;
		  }
		} 
}
