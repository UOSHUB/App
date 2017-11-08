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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import baxzel.uoshub.Declutterer;
import baxzel.uoshub.LoginActivity;
import baxzel.uoshub.R;

public class CoursesFragment extends Fragment{
    String URL = new Declutterer().URLHolder("Courses");

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if (container != null)
            container.removeAllViews();

        if(LoginActivity.mRequestQueue == null)
            LoginActivity.mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        final View v = inflater.inflate(R.layout.fragment_courses, container, false);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
            new Response.Listener<JSONObject>(){
                public void onResponse(JSONObject response){
                    Log.d("response", response.toString());
                    try{
                        ListView resultsListView = (ListView) v.findViewById(R.id.courses_list);
                        HashMap<String, String> mHashMap = new HashMap<>();
                        for(Iterator<String> iter = response.keys(); iter.hasNext();){
                            String key = iter.next();
                            JSONObject course = response.getJSONObject(key);
                            String theCRN = course.getString("crn");
                            String theTitle = course.getString("title");
                            mHashMap.put(theCRN, theTitle);
                        }

                        List<LinkedHashMap<String, String>> mList = new ArrayList<>();
                        SimpleAdapter mSimpleAdapter = new SimpleAdapter(getContext(), mList, R.layout.fragment_courses,
                            new String[]{"First Line", "Second Line"},
                            new int[]{R.id.item, R.id.sub_item});


                        Iterator mIterator = mHashMap.entrySet().iterator();
                        while(mIterator.hasNext()){
                            LinkedHashMap<String, String> resultsmap = new LinkedHashMap<>();
                            Map.Entry pair = (Map.Entry)mIterator.next();
                            resultsmap.put("First Line", pair.getKey().toString());
                            resultsmap.put("Second Line", pair.getValue().toString());
                            mList.add(resultsmap);
                        }
                        resultsListView.setAdapter(mSimpleAdapter);

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener(){
                public void onErrorResponse(VolleyError error){
                    Log.d("VOLLEY", "ERROR");
                }
            }
        );
        LoginActivity.mRequestQueue.add(jsonObjectRequest);
        return v;
    }
}