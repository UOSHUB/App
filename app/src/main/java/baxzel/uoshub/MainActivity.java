package baxzel.uoshub;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import java.lang.reflect.Field;
import baxzel.uoshub.layout.CoursesFragment;
import baxzel.uoshub.layout.DeadlinesFragment;
import baxzel.uoshub.layout.EmailFragment;
import baxzel.uoshub.layout.UpdatesFragment;

public class MainActivity extends AppCompatActivity{
    @SuppressLint("RestrictedApi")
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("UOS HUB");
        toolbar.setNavigationIcon(R.drawable.icon_navigationdrawer);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UpdatesFragment()).commit();

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
}