package com.dvs.expensemanager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class DataBaseHelper extends SQLiteOpenHelper
{
public DataBaseHelper(Context cntxt, String nm,CursorFactory fct, int vsn) 
{
super(cntxt, nm, fct, vsn);
}
@Override
public void onCreate(SQLiteDatabase dbms) 
{
dbms.execSQL(LoginDataBaseAdapter.DATABASE_CREATE);
}
@Override
public void onUpgrade(SQLiteDatabase dbms, int olve, int newve) 
{
Log.w("TaskDBAdapter", "Upgrading from version " +olve + " to " +newve + ", which will destroy all old data");
dbms.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
onCreate(dbms);
}}