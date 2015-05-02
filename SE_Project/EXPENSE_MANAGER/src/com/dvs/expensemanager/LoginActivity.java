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
public class LoginActivity extends Activity {
EditText unm,pswd;
Button sign_in_button;
LoginDataBaseAdapter ldba;
public static final String Username="com.dvs.expensemanager.Username";
@Override
protected void onCreate(Bundle sis) {
super.onCreate(sis);
requestWindowFeature(Window.FEATURE_NO_TITLE);
setContentView(R.layout.activity_login);
ldba=new LoginDataBaseAdapter(this);
ldba=ldba.open();
final  EditText unm=(EditText)findViewById(R.id.uname2l);
final  EditText pswd=(EditText)findViewById(R.id.password2l);    
Button sign_in_button=(Button)findViewById(R.id.sign_in_button);
sign_in_button.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
String uNm=unm.getText().toString();
final String x =uNm;
String Pswd=pswd.getText().toString();
String stPswd=ldba.getSinlgeEntry(uNm);
if(Pswd.equals(stPswd))
{
Intent innt=new Intent(getApplicationContext(),Success.class);
EditText cs=(EditText)findViewById(R.id.uname2l);
String u=cs.getText().toString();
innt.putExtra(Username,u);
startActivity(innt);
Toast.makeText(LoginActivity.this, "Congrats : Login Successfull", Toast.LENGTH_SHORT).show();
Toast.makeText(LoginActivity.this, "Welcome "+x, Toast.LENGTH_LONG).show();
}
else
{
Toast.makeText(LoginActivity.this, "User Name or Password does not match", Toast.LENGTH_SHORT).show();
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
super.onCreateOptionsMenu(menu);
getMenuInflater().inflate(R.menu.defaultmenu, menu);
return true;
}
@Override
public boolean onOptionsItemSelected(MenuItem item) {
return super.onOptionsItemSelected(item);
}}