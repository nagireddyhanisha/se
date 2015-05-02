package com.dvs.expensemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper2 extends SQLiteOpenHelper
{
public DataBaseHelper2(Context cntxt2, String nm,CursorFactory fct, int vsn) 
{
super(cntxt2, nm, fct, vsn);
}
@Override
public void onCreate(SQLiteDatabase dbms2) 
{
dbms2.execSQL(ExpenseDataBaseAdapter.DATABASE_CREATE);

}
@Override
public void onUpgrade(SQLiteDatabase dbms2, int olve, int newve) 
{
Log.w("TaskDBAdapter", "Upgrading from version " +olve + " to " +newve + ", which will destroy all old data");
dbms2.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
onCreate(dbms2);
}}