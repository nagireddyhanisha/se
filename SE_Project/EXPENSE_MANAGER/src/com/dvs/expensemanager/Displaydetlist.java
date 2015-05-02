package com.dvs.expensemanager;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
public class Displaydetlist extends Activity
{
ExpenseDataBaseAdapter exp;
LoginDataBaseAdapter log;
ListView list;
ArrayAdapter<String> arradp;
ArrayList<String> arrlst = new ArrayList<String>();
@Override
protected void onCreate(Bundle sis) {
super.onCreate(sis);
setContentView(R.layout.activity_displaylist);
list=(ListView)findViewById(R.id.list);
exp=new ExpenseDataBaseAdapter(this);
exp=exp.open();
log=new LoginDataBaseAdapter(this);
log=log.open();
DataBaseHelper2 dhelp=new DataBaseHelper2(Displaydetlist.this, "exp.db", null, 1);
SQLiteDatabase db =dhelp.getReadableDatabase();
SharedPreferences prfs = PreferenceManager.getDefaultSharedPreferences(this);
final String x = prfs.getString("username","");
String sql="select * from DAILYEXP WHERE USERNAME=?";
Cursor cur =db.rawQuery(sql, new String[]{x});
if(cur.moveToFirst())
{
do{
String Date = cur.getString(cur.getColumnIndex("DATE"));
int Exp  =  cur.getInt(cur.getColumnIndex("EXPENSE"));
String a = Exp+" on "+Date  ;
arrlst.add(a);
}while(cur.moveToNext());
}
arradp = new ArrayAdapter<String>( this,R.layout.listrow_group,R.id.firstLine, arrlst );
list.setAdapter(arradp);
list.setOnItemLongClickListener(new OnItemLongClickListener() {
public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
final int arg2, long arg3) {
String str=(String) list.getItemAtPosition(arg2);
final String subs = str.substring(str.length() - 10); 
Builder bld = new AlertDialog.Builder(Displaydetlist.this);
bld.setTitle("Expenses of "+subs);
bld.setMessage("Do you want to delete ?");
bld.setPositiveButton("Yes",
new DialogInterface.OnClickListener() {

public void onClick(DialogInterface dlg,
int wch) {
Toast.makeText(
getApplicationContext(),
"Expense Deleted", Toast.LENGTH_SHORT).show();
int g=exp.getexpensefromdate(x,subs);
exp.deleteEntry(subs);
int p=g+log.getexpendEntry(x);
log.updateEntry(x,log.getSinlgeEntry(x),p);
Intent intd = new Intent(getApplicationContext(), Det.class);
startActivity(intd);
dlg.cancel();
}
});
bld.setNegativeButton("No",
new DialogInterface.OnClickListener() {
public void onClick(DialogInterface dlg,
int wch) {
dlg.cancel();
}
});
AlertDialog alrt = bld.create();
alrt.show();
return true;
}});}}