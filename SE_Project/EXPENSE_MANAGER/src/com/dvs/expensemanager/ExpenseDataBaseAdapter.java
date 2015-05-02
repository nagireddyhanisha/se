package com.dvs.expensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ExpenseDataBaseAdapter 
{
static final String DATABASE_NAME = "exp.db";
static final int DATABASE_VERSION = 1;
public static final int nmcl = 1;
static final String DATABASE_CREATE = "create table "+"DAILYEXP"+"( " +"ID"+" integer primary key autoincrement,"+ "USERNAME  text,DATE date,EXPENSE integer); ";
public  SQLiteDatabase db2;
private final Context cxt2;
private DataBaseHelper2 dbh2;
public  ExpenseDataBaseAdapter(Context cnxt) 
{
cxt2 = cnxt;
dbh2 = new DataBaseHelper2(cxt2, DATABASE_NAME, null, DATABASE_VERSION);
}
public  ExpenseDataBaseAdapter open() throws SQLException 
{
db2 = dbh2.getWritableDatabase();
return this;
}
public void close() 
{
db2.close();
}

public  SQLiteDatabase getDatabaseInstance()
{
return db2;
}

public void insertEntry(String uName,String Date, Integer Expense)
{
ContentValues nval = new ContentValues();
nval.put("USERNAME", uName);
nval.put("DATE",Date);
nval.put("EXPENSE", Expense);
db2.insert("DAILYEXP", null, nval);
}
public void deleteEntry(String Date)
{
    db2.delete("DAILYEXP","DATE=?",  new String[]{Date}) ;
    }
public void updateExpense(String uName,String Date,Integer Expense)
{
ContentValues upval = new ContentValues();
upval.put("DATE",Date);
upval.put("EXPENSE",Expense);
String where="USERNAME=? AND DATE = ?";
    db2.update("DAILYEXP",upval, where, new String[]{uName,Date});
}
public String getDateEntry(String uName)
{
Cursor cursor=db2.query("DAILYEXP", null, " USERNAME=?", new String[]{uName}, null, null, null);
if(cursor.getCount()<1)
{
cursor.close();
return "NOT EXIST";
}
cursor.moveToFirst();
String date= cursor.getString(cursor.getColumnIndex("DATE"));
cursor.close();
return date;
}

public String getunameEntry(String uName)
{
Cursor cursor=db2.query("DAILYEXP", null, " USERNAME=?", new String[]{uName}, null, null, null);
if(cursor.getCount()<1)
{
cursor.close();
return "NOT EXIST";
}
cursor.moveToFirst();
String Uname= cursor.getString(cursor.getColumnIndex("USERNAME"));
cursor.close();
return Uname;
}

public Integer getexpenseEntry(String uName)
{
Cursor cursor=db2.query("DAILYEXP", null, " USERNAME=?", new String[]{uName}, null, null, null);
if(cursor.getCount()<1)
{
cursor.close();
return 0;
}
cursor.moveToFirst();
Integer Expense= Integer.parseInt(cursor.getString(cursor.getColumnIndex("EXPENSE")));
cursor.close();
return Expense;
}
public Integer getexpensefromdate(String uName,String Date)
{
Cursor cursor=db2.query("DAILYEXP", null, " USERNAME=? AND DATE=?", new String[]{uName,Date}, null, null, null);
if(cursor.getCount()<1)
{
cursor.close();
return 0;
}
cursor.moveToFirst();
Integer Expense= Integer.parseInt(cursor.getString(cursor.getColumnIndex("EXPENSE")));
cursor.close();
return Expense;
}
public void  updateEntry(String uName,String Date, Integer Expense )
{
ContentValues upval = new ContentValues();
upval.put("USERNAME", uName);
upval.put("DATE",Date);
upval.put("EXPENSE",Expense);
String where="DATE = ?";
db2.update("DAILYEXP",upval, where, new String[]{Date});   
}
}