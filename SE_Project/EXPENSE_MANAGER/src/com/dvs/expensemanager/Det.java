package com.dvs.expensemanager;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class Det extends Activity {
Button btad;
ExpenseDataBaseAdapter edba;
LoginDataBaseAdapter ldba;
@Override
protected void onCreate(Bundle sis) {
super.onCreate(sis);
setContentView(R.layout.activity_det);
edba=new ExpenseDataBaseAdapter(this);
edba=edba.open();
ldba=new LoginDataBaseAdapter(this);
ldba=ldba.open();
btad=(Button)findViewById(R.id.addExp);
SharedPreferences pfs = PreferenceManager.getDefaultSharedPreferences(this);
String x = pfs.getString("username","");
Integer expd = ldba.getexpendEntry(x);
((TextView)findViewById(R.id.expenseview)).setText(Integer.toString(expd));
TextView btndisp = (TextView)findViewById(R.id.expenseview);
btndisp.setOnClickListener(new View.OnClickListener() {

public void onClick(View v2) { 
Intent it1=new Intent(getApplicationContext(),Displaydetlist.class);
startActivity(it1);
}});


SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
String dtntm = sdf.format(new Date());
((TextView)findViewById(R.id.date)).setText(dtntm);
}

public void addexp(View V)
{
final Dialog dialog = new Dialog(Det.this);
dialog.setContentView(R.layout.add);
dialog.setTitle("Add Expense");
final  EditText edtxtdt=(EditText)dialog.findViewById(R.id.dateentry);
final  EditText edtxtexp=(EditText)dialog.findViewById(R.id.expenseentry);
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
String dtntm = sdf.format(new Date());
edtxtdt.setText(dtntm);
Button btad=(Button)dialog.findViewById(R.id.addexp);
SharedPreferences pfs = PreferenceManager.getDefaultSharedPreferences(this);
final String x = pfs.getString("username","");
final Integer expd = ldba.getexpendEntry(x);
btad.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
String Date=edtxtdt.getText().toString();
String E  = edtxtexp.getText().toString();
if(Date.equals("")||E.equals(""))
{
Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
return;
}
Integer Expense = Integer.parseInt(E);
Integer epd= expd - Expense;
String unm = x;
String pswd=ldba.getSinlgeEntry(unm);
Integer dlyep= edba.getexpensefromdate(unm,Date);
if(epd<0)
{
Toast.makeText(getApplicationContext(), "Insufficient Funds", Toast.LENGTH_LONG).show();
dialog.dismiss();
Toast.makeText(getApplicationContext(), "Please Increment your Budget", Toast.LENGTH_LONG).show();
}
else
{
if(dlyep==0){
edba.insertEntry(unm, Date, Expense);
ldba.updateEntry(unm,pswd,epd);
Toast.makeText(Det.this, "Expense Added", Toast.LENGTH_LONG).show();
dialog.dismiss();
Intent it2=new Intent(getApplicationContext(),Det.class);
startActivity(it2);
}
else
{
Integer upexp=dlyep+Expense;
edba.updateEntry(unm, Date, upexp);
ldba.updateEntry(unm,pswd,epd);
Toast.makeText(Det.this, "Expense Added", Toast.LENGTH_LONG).show();
dialog.dismiss();
Intent it2=new Intent(getApplicationContext(),Det.class);
startActivity(it2);
}}}});
dialog.show();
}
@Override
protected void onDestroy() {
super.onDestroy();
edba.close();
}
int bbct=0;
@Override
public void onBackPressed()
{   
if(bbct >= 1)
{
Intent it2 = new Intent(Intent.ACTION_MAIN);
it2.addCategory(Intent.CATEGORY_HOME);
it2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
startActivity(it2);
}
else
{
Toast.makeText(this, "Press the back button once again to exit", Toast.LENGTH_SHORT).show();
bbct++;
}
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
getMenuInflater().inflate(R.menu.menu2, menu);
return true;
}
 @Override
public boolean onOptionsItemSelected(MenuItem item) {
switch (item.getItemId()) {
case R.id.logout:
Intent i5 = new Intent(getApplicationContext(), HomeActivity.class);
startActivity(i5);
SharedPreferences pfs = PreferenceManager.getDefaultSharedPreferences(this);
SharedPreferences.Editor edt = pfs.edit();
edt.putString("username","");
edt.commit();
Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_LONG).show();
return true;
case R.id.abudget:
Intent i6 = new Intent(getApplicationContext(), Addbu.class);
startActivity(i6);
break;
default:
return super.onOptionsItemSelected(item);
}
return false;
}}