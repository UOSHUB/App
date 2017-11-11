package baxzel.uoshub.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import baxzel.uoshub.Declutterer;
import baxzel.uoshub.LoginActivity;
import baxzel.uoshub.R;

public class HoldsFragment extends Fragment{
    String URL = new Declutterer().URLHolder("Holds");

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if(container != null)
            container.removeAllViews();

        if(LoginActivity.mRequestQueue == null)
            LoginActivity.mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        final View v = inflater.inflate(R.layout.fragment_holds, container, false);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>(){
                    public void onResponse(JSONArray response){
                        Log.d("response" , response.toString());
                        try{
                            ListView resultsListView = (ListView) v.findViewById(R.id.holds_list);
                            List<LinkedHashMap<String, String>> mList = new ArrayList<>();
                            SimpleAdapter mSimpleAdapter = new SimpleAdapter(getContext(), mList, R.layout.fragment_holds,
                                    new String[]{"First Line", "Second Line"},
                                    new int[]{R.id.item, R.id.sub_item});

                            for(int i=0;i<response.length();i++){
                                LinkedHashMap<String, String> resultsmap = new LinkedHashMap<>();

                                String theCourse = new JSONObject(response.get(i).toString()).get("course").toString();
                                resultsmap.put("First Line", theCourse);

                                String theOutOf = new JSONObject(response.get(i).toString()).get("outOf").toString();

                                String theGrade = new JSONObject(response.get(i).toString()).get("grade").toString();
                                resultsmap.put("Second Line", theGrade + "/" + theOutOf);

                                mList.add(resultsmap);
                            }
                            resultsListView.setAdapter(mSimpleAdapter);

                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    public void onErrorResponse(VolleyError error){
                        Log.d("VOLLEY", error.getMessage());
                    }
                }
        );
        LoginActivity.mRequestQueue.add(jsonObjectRequest);
        return v;
    }
}