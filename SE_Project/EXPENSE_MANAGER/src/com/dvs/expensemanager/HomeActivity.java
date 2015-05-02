package com.dvs.expensemanager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;
public class HomeActivity extends Activity {
@Override
protected void onCreate(Bundle sis) {
super.onCreate(sis);
requestWindowFeature(Window.FEATURE_NO_TITLE);
Thread tmr = new Thread(){
@Override
public void run(){
try{
sleep(3000);
} catch (InterruptedException e){
e.printStackTrace();
}finally{
Intent ohome = new Intent(getApplicationContext(),SecHomescreen.class);
startActivity(ohome);}}};
tmr.start();
}
@Override
protected void onPause() {
super.onPause();
finish();
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
getMenuInflater().inflate(R.menu.defaultmenu, menu);
return true;
}
@Override
public boolean onOptionsItemSelected(MenuItem item) {
return super.onOptionsItemSelected(item);
}}