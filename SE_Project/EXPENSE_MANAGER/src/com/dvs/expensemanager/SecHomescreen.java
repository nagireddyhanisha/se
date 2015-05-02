package com.dvs.expensemanager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
public class SecHomescreen extends Activity {
@Override
protected void onCreate(Bundle sis) {
super.onCreate(sis);
requestWindowFeature(Window.FEATURE_NO_TITLE);
setContentView(R.layout.activity_sec_homescreen);
Button login = (Button)findViewById(R.id.button1);
Button signup =(Button)findViewById(R.id.button2);
login.setOnClickListener(new OnClickListener(){
 @Override
public void onClick(View view) {
Intent i = new Intent(getApplicationContext(), LoginActivity.class);
startActivity(i);
}
});
signup.setOnClickListener(new OnClickListener(){
@Override
public void onClick(View view2) {
Intent i2 = new Intent(getApplicationContext(), Signup.class);
startActivity(i2);
}
});}
int bbct=0;
@Override
public void onBackPressed()
{
if(bbct >= 1)
{
Intent innt = new Intent(Intent.ACTION_MAIN);
innt.addCategory(Intent.CATEGORY_HOME);
innt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
startActivity(innt);
}
else
{
Toast.makeText(this, "Press the back button once again to exit", Toast.LENGTH_SHORT).show();
bbct++;
}
}
@Override
public boolean onCreateOptionsMenu(Menu menu) {
getMenuInflater().inflate(R.menu.defaultmenu, menu);
return true;
}
@Override
public boolean onOptionsItemSelected(MenuItem item) {
return super.onOptionsItemSelected(item);}}