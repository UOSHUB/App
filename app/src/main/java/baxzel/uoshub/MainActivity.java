package baxzel.uoshub;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import baxzel.uoshub.layout.AccountFragment;
import baxzel.uoshub.layout.AnnouncementsFragment;
import baxzel.uoshub.layout.CoursesFragment;

public class MainActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("UOS HUB");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AnnouncementsFragment()).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){

        public boolean onNavigationItemSelected(MenuItem item){
            switch (item.getItemId()){
                case R.id.navigation_announcements:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AnnouncementsFragment()).commit();
                    return true;

                case R.id.navigation_courses:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CoursesFragment()).commit();
                    return true;

                case R.id.navigation_account:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountFragment()).commit();
                    return true;
            }
            return false;
        }
    };
}