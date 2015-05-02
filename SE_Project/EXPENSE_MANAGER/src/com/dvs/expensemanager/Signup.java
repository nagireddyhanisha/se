package com.dvs.expensemanager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Signup extends Activity {
EditText uname,password,cpassword,expend;
Button sign_up_button;
LoginDataBaseAdapter ldba;
@Override
protected void onCreate(Bundle sis) {
super.onCreate(sis);
requestWindowFeature(Window.FEATURE_NO_TITLE);
setContentView(R.layout.activity_signup);
ldba=new LoginDataBaseAdapter(this);
ldba=ldba.open();
uname=(EditText)findViewById(R.id.uname);
password=(EditText)findViewById(R.id.password);
cpassword=(EditText)findViewById(R.id.cpassword);
expend=(EditText)findViewById(R.id.expend);
sign_up_button=(Button)findViewById(R.id.sign_up_button);
sign_up_button.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
String unm=uname.getText().toString();
String pswd=password.getText().toString();
String cpswd=cpassword.getText().toString();
String E=expend.getText().toString();
if(unm.equals(ldba.getunameEntry(unm))){
Toast.makeText(getApplicationContext(), "Username Already Exists", Toast.LENGTH_LONG).show();
return;
}
if(unm.equals("")||pswd.equals("")||cpswd.equals("")||E.equals(""))
{
Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
return;
}
Integer Expend=Integer.parseInt(E);
if(!pswd.equals(cpswd))
{
Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
return;
}
else
{
ldba.insertEntry(unm, pswd, Expend);
Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
Intent i3 = new Intent(getApplicationContext(), SecHomescreen.class);
startActivity(i3);
}}});}
@Override
protected void onDestroy() {
super.onDestroy();

ldba.close();
}
@Override
protected void onPause() {
super.onPause();
finish();
}
@Override
public boolean onCreateOptionsMenu(Menu menu) {
getMenuInflater().inflate(R.menu.defaultmenu, menu);
return true;
}
@Override
public boolean onOptionsItemSelected(MenuItem item) {
return super.onOptionsItemSelected(item);
}}