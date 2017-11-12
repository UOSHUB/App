package baxzel.uoshub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import baxzel.uoshub.layout.CalendarFragment;
import baxzel.uoshub.layout.GradesFragment;
import baxzel.uoshub.layout.HoldsFragment;

public class MinorActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minor);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("UOS HUB");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String type = getIntent().getStringExtra("fragmentType");
        Log.d("frag",type);

        if(type.compareTo("calendar") == 0)
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container2, new CalendarFragment()).commit();
        else if(type.compareTo("grades") == 0)
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container2, new GradesFragment()).commit();
        else if(type.compareTo("holds") == 0)
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container2, new HoldsFragment()).commit();
    }

    public  boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}