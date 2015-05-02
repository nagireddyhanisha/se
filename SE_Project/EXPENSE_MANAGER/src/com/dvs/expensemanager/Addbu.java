package com.dvs.expensemanager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Addbu extends Activity {	
LoginDataBaseAdapter ldba;
@Override
protected void onCreate(Bundle sis) {
super.onCreate(sis);
setContentView(R.layout.addb);
ldba=new LoginDataBaseAdapter(this);
ldba=ldba.open();
SharedPreferences pfs = PreferenceManager.getDefaultSharedPreferences(this);
final String x = pfs.getString("username","");
Integer e1 = ldba.getexpendEntry(x);
final Integer e2=e1;
Button addb=(Button)findViewById(R.id.addbud);
	
addb.setOnClickListener(new View.OnClickListener() {	
@Override
public void onClick(View v) {
String bgt=((EditText)findViewById(R.id.budgetentry)).getText().toString();
if(bgt.equals("") || bgt.equals("0"))
{
Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
return;
}
else{
Integer Bgt1 = Integer.parseInt(bgt);
Integer expd= e2 + Bgt1;
String unm = x;
String pswd=ldba.getSinlgeEntry(unm);
ldba.updateEntry(unm,pswd,expd);
Intent innt=new Intent(getApplicationContext(),Det.class);
startActivity(innt);
Toast.makeText(Addbu.this, "Budget Added", Toast.LENGTH_LONG).show();				
}}});}
@Override
protected void onPause() {
super.onPause();
finish();
}}
