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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import baxzel.uoshub.Declutterer;
import baxzel.uoshub.LoginActivity;
import baxzel.uoshub.MyAdapter;
import baxzel.uoshub.R;

public class GradesFragment extends Fragment{
    String URL = Declutterer.URLHolder("Grades");

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if(container != null)
            container.removeAllViews();

        if(LoginActivity.mRequestQueue == null)
            LoginActivity.mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        final View v = inflater.inflate(R.layout.fragment_grades, container, false);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
            new Response.Listener<JSONArray>(){
                public void onResponse(JSONArray response){
                    Log.d("response" , response.toString());
                    try{
                        ListView resultsListView = (ListView) v.findViewById(R.id.grades_list);
                        MyAdapter mMyAdapter = new MyAdapter
                                (getContext(), response, "course","title", "outOf", "grade","grades");
                        resultsListView.setAdapter(mMyAdapter);

                    } catch (JSONException e){
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
        return v;
    }
}