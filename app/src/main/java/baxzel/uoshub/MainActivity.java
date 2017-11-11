package baxzel.uoshub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.lang.reflect.Field;

import baxzel.uoshub.layout.CoursesFragment;
import baxzel.uoshub.layout.DeadlinesFragment;
import baxzel.uoshub.layout.EmailFragment;
import baxzel.uoshub.layout.UpdatesFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    @SuppressLint("RestrictedApi")
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("UOS HUB");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        //For making the bottom nav icons of same size when active
        try{
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e){
            Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field");
        } catch (IllegalAccessException e){
            Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode");
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){

        public boolean onNavigationItemSelected(MenuItem item){
            switch(item.getItemId()){
                case R.id.navigation_updates:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UpdatesFragment()).commit();
                    return true;

                case R.id.navigation_courses:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CoursesFragment()).commit();
                    return true;

                case R.id.navigation_email:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EmailFragment()).commit();
                    return true;

                case R.id.navigation_deadlines:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DeadlinesFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    public void onBackPressed(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.navigation_calendar){
            startActivity(new Intent(MainActivity.this, MinorActivity.class));
            new Declutterer().setCalendarFragment(true);
            new Declutterer().setGradesFragment(false);
            new Declutterer().setHoldsFragment(false);
        }
        else if(id == R.id.navigation_grades){
            startActivity(new Intent(MainActivity.this, MinorActivity.class));
            new Declutterer().setCalendarFragment(false);
            new Declutterer().setGradesFragment(true);
            new Declutterer().setHoldsFragment(false);
        }
        else if(id == R.id.navigation_holds){
            startActivity(new Intent(MainActivity.this, MinorActivity.class));
            new Declutterer().setCalendarFragment(false);
            new Declutterer().setGradesFragment(false);
            new Declutterer().setHoldsFragment(true);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}