package unikl.edu.fyp.iban.dictionary.logic;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class IbanAdapter {

	private Context context;
	private DBHelper dbhelper;
	private SQLiteDatabase database;

	public IbanAdapter(Context context) {
		this.context = context;
	}

	public IbanAdapter open() throws SQLException {
		dbhelper = new DBHelper(context);
		database = dbhelper.getReadableDatabase();
		return this;
	}

	public void close() {
		dbhelper.close();
		database.close();
	}

	public Cursor getDefinition(String keyword, String table) {

		String query = "SELECT * FROM " + table + " WHERE alpha='" + keyword
				+ "'";

		return database.rawQuery(query, null);
	}

	public Cursor getAll(String table) {

		String query = "SELECT * FROM " + table;

		return database.rawQuery(query, null);
	}
}
