package mohan.exp.javamultitheme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import mohan.exp.javamultitheme.refer.R;

public class HomeActivity extends BaseConfigActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_layout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overflow_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent io;
        switch (item.getItemId()) {
            case R.id.settings:
                // Action goes here
                 io = new Intent(HomeActivity.this,SettingsThemeActivity.class);
                startActivity(io);
                return true;
            case R.id.logout:
                // Action goes here
                 io = new Intent(HomeActivity.this,LoginActivity.class);
                 startActivity(io);
                 finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
