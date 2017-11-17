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
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Iterator;

import baxzel.uoshub.layout.CoursesFragment;
import baxzel.uoshub.layout.DeadlinesFragment;
import baxzel.uoshub.layout.EmailFragment;
import baxzel.uoshub.layout.UpdatesFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    String URL = Declutterer.URLHolder("Details");
    @SuppressLint("RestrictedApi")
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("UOS HUB");
        setSupportActionBar(toolbar);

        if(LoginActivity.mRequestQueue == null)
            LoginActivity.mRequestQueue = Volley.newRequestQueue(this.getApplicationContext());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new UpdatesFragment()).commit();

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

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
            new Response.Listener<JSONObject>(){
                public void onResponse(JSONObject response){
                    Log.d("response", response.toString());
                    try{
                        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                        View header = navigationView.getHeaderView(0);

                        final TextView studentName = (TextView) header.findViewById(R.id.navigationDrawerStudentName);
                        final TextView studentId = (TextView) header.findViewById(R.id.navigationDrawerStudentId);
                        final TextView studentMajor = (TextView) header.findViewById(R.id.navigationDrawerStudentMajor);
                        final TextView studentCollege = (TextView) header.findViewById(R.id.navigationDrawerStudentCollege);

                        for(Iterator<String> iter = response.keys(); iter.hasNext();){
                            String key = iter.next();
                            JSONObject course = response.getJSONObject(key);
                            String theFirstName = course.getString("firstName");
                            String theLastName = course.getString("lastName");
                            String theId = course.getString("studentId");
                            String theMajor = course.getString("major");
                            String theCollege = course.getString("college");

                            studentName.setText(theFirstName + " " + theLastName);
                            studentId.setText(theId);
                            studentMajor.setText(theMajor);
                            studentCollege.setText(theCollege);
                        }

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener(){
                public void onErrorResponse(VolleyError error){
                    Log.d("VOLLEY", error.getMessage() + "");
                }
            }
        );
        LoginActivity.mRequestQueue.add(jsonObjectRequest);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){

        public boolean onNavigationItemSelected(MenuItem item){
            switch(item.getItemId()){
                case R.id.navigation_updates:
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new UpdatesFragment()).commit();
                    return true;

                case R.id.navigation_courses:
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new CoursesFragment()).commit();
                    return true;

                case R.id.navigation_email:
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new EmailFragment()).commit();
                    return true;

                case R.id.navigation_deadlines:
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new DeadlinesFragment()).commit();
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
        /* Handle action bar item clicks here. The action bar wil automatically handle clicks
        on the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.*/
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();
        Intent intent = new Intent(MainActivity.this, MinorActivity.class);

        if(id == R.id.navigation_calendar)
            intent.putExtra("fragmentType","calendar");
        else if(id == R.id.navigation_grades)
            intent.putExtra("fragmentType","grades");
        else if(id == R.id.navigation_holds)
            intent.putExtra("fragmentType","holds");

        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}