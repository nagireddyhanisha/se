package com.dvs.expensemanager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
public class Success extends Activity {
LoginDataBaseAdapter ldba;
@Override
protected void onCreate(Bundle sis){ 
super.onCreate(sis);
ldba=new LoginDataBaseAdapter(this);
ldba=ldba.open();
setContentView(R.layout.activity_success);
Intent innt=getIntent();
String u=innt.getStringExtra(LoginActivity.Username);
SharedPreferences pfs = PreferenceManager.getDefaultSharedPreferences(this);
SharedPreferences.Editor edtr = pfs.edit();
edtr.putString("username",u);
edtr.commit();
Button det = (Button)findViewById(R.id.det);
det.setOnClickListener(new OnClickListener(){
@Override
public void onClick(View view) {
Intent i = new Intent(getApplicationContext(), Det.class);
startActivity(i);
}});}
@Override
protected void onDestroy() {
super.onDestroy();
ldba.close();
}
int bbcnt=0;
@Override
public void onBackPressed()
{
if(bbcnt >= 1)
{
Intent innt = new Intent(Intent.ACTION_MAIN);
innt.addCategory(Intent.CATEGORY_HOME);
innt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
startActivity(innt);
}
else
{
Toast.makeText(this, "Press the back button once again to exit", Toast.LENGTH_SHORT).show();
bbcnt++;
}
}
@Override
public boolean onCreateOptionsMenu(Menu menu) {
getMenuInflater().inflate(R.menu.menu, menu);
return true;
}
 @Override
public boolean onOptionsItemSelected(MenuItem item) {
switch (item.getItemId()) {
case R.id.logout:
Intent i5 = new Intent(getApplicationContext(), HomeActivity.class);
startActivity(i5);
SharedPreferences pfs = PreferenceManager.getDefaultSharedPreferences(this);
SharedPreferences.Editor edtr = pfs.edit();
edtr.putString("username","");
edtr.commit();
Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_LONG).show();
return true;
default:
return super.onOptionsItemSelected(item);
}
}}