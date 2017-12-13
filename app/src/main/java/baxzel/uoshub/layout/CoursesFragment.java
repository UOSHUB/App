package baxzel.uoshub.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import baxzel.uoshub.Declutterer;
import baxzel.uoshub.LoginActivity;
import baxzel.uoshub.MyAdapter;
import baxzel.uoshub.R;
import baxzel.uoshub.database.DBManager;

public class CoursesFragment extends Fragment{
    String URL = Declutterer.URLHolder("Courses");

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if (container != null)
            container.removeAllViews();

        final View v = inflater.inflate(R.layout.fragment_list, container, false);

        final DBManager db = new DBManager(getContext());
        db.open();
        JSONArray Courses = db.getCourses();
        if(Courses.length() == 0) {

        if(LoginActivity.mRequestQueue == null)
            LoginActivity.mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
            new Response.Listener<JSONObject>(){
                public void onResponse(JSONObject response){
                    Log.d("response", response.toString());
                    Iterator keys = response.keys();
                    JSONArray courses = new JSONArray();
                    while (keys.hasNext()) {
                        String key = keys.next().toString();
                        JSONObject course = null;
                        try {
                            course = response.getJSONObject(key);
                            course.put("id", key);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        courses.put(course);
                    }
                    db.addCourses(courses);
                    feedAdapter(courses, v);
                }
            },
            new Response.ErrorListener(){
                public void onErrorResponse(VolleyError error){
                    Log.d("VOLLEY", error.getMessage() + "");
                }
            }
        );
        LoginActivity.mRequestQueue.add(jsonObjectRequest);
        } else {
            Log.d("Loading", "from db");
            feedAdapter(Courses, v);

        }
        return v;
    }
        void feedAdapter(JSONArray response, View v) {
            try {
                ListView resultsListView = (ListView) v.findViewById(R.id.items_list);

                MyAdapter mMyAdapter = new MyAdapter
                        (getContext(), response, "title","title","title","title","courses" );
                resultsListView.setAdapter(mMyAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
}