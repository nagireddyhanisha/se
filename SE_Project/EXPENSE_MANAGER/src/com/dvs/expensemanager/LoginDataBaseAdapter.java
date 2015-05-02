package com.dvs.expensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter 
{
static final String DATABASE_NAME = "login.db";
static final int DATABASE_VERSION = 1;
public static final int NAME_COLUMN = 1;
static final String DATABASE_CREATE = "create table "+"LOGIN"+"( " +"ID"+" integer primary key autoincrement,"+ "USERNAME  text,PASSWORD text,EXPEND integer); ";
public  SQLiteDatabase db;
private final Context cxt;
private DataBaseHelper dhelp;
public  LoginDataBaseAdapter(Context cxt1) 
{
cxt = cxt1;
dhelp = new DataBaseHelper(cxt, DATABASE_NAME, null, DATABASE_VERSION);
}
public  LoginDataBaseAdapter open() throws SQLException 
{
db = dhelp.getWritableDatabase();
return this;
}
public void close() 
{
db.close();
}

public  SQLiteDatabase getDatabaseInstance()
{
return db;
}

public void insertEntry(String unm,String Pswd, Integer epd)
{
ContentValues nval = new ContentValues();
nval.put("USERNAME", unm);
nval.put("PASSWORD",Pswd);
nval.put("EXPEND", epd);
db.insert("LOGIN", null, nval);
}
public int deleteEntry(String UserName)
{
String where="USERNAME=?";
int ndel= db.delete("LOGIN", where, new String[]{UserName}) ;
return ndel;
}	
public String getSinlgeEntry(String unm)
{
Cursor crsr=db.query("LOGIN", null, " USERNAME=?", new String[]{unm}, null, null, null);
if(crsr.getCount()<1)
{
crsr.close();
return "NOT EXIST";
}
crsr.moveToFirst();
String Pswd= crsr.getString(crsr.getColumnIndex("PASSWORD"));
crsr.close();
return Pswd;				
}
public String getunameEntry(String unm)
{
Cursor crsr=db.query("LOGIN", null, " USERNAME=?", new String[]{unm}, null, null, null);
if(crsr.getCount()<1)
{
crsr.close();
return "NOT EXIST";
}
crsr.moveToFirst();
String Uname= crsr.getString(crsr.getColumnIndex("USERNAME"));
crsr.close();
return Uname;				
}
public String getid(String unm)
{
Cursor crsr=db.query("LOGIN", null, " USERNAME=?", new String[]{unm}, null, null, null);
if(crsr.getCount()<1)
{
crsr.close();
return "NOT EXIST";
}
crsr.moveToFirst();
String id= crsr.getString(crsr.getColumnIndex("ID"));
crsr.close();
return id;				
}
public Integer getexpendEntry(String unm)
{
Cursor crsr=db.query("LOGIN", null, " USERNAME=?", new String[]{unm}, null, null, null);
if(crsr.getCount()<1)
{
crsr.close();
return 0;
}
crsr.moveToFirst();
Integer epd= Integer.parseInt(crsr.getString(crsr.getColumnIndex("EXPEND")));
crsr.close();
return epd;
}
public void  updateEntry(String unm,String Pswd, Integer epd )
{
ContentValues uval = new ContentValues();
uval.put("USERNAME", unm);
uval.put("PASSWORD",Pswd);
uval.put("EXPEND",epd);
String where="USERNAME = ?";
db.update("LOGIN",uval, where, new String[]{unm});			   
}		
}