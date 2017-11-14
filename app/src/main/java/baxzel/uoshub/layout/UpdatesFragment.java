package baxzel.uoshub.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import baxzel.uoshub.Declutterer;
import baxzel.uoshub.LoginActivity;
import baxzel.uoshub.R;

public class UpdatesFragment extends Fragment{
    String URL = Declutterer.URLHolder("Updates");

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if(container != null)
            container.removeAllViews();

        if(LoginActivity.mRequestQueue == null)
            LoginActivity.mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        final View v = inflater.inflate(R.layout.fragment_updates, container, false);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
            new Response.Listener<JSONArray>(){
            public void onResponse(JSONArray response){
                Log.d("response" , response.toString());
                try{
                    ListView resultsListView = (ListView) v.findViewById(R.id.updates_list);
                    List<LinkedHashMap<String, String>> mList = new ArrayList<>();
                    SimpleAdapter mSimpleAdapter = new SimpleAdapter(getContext(), mList, R.layout.fragment_updates,
                        new String[]{"First Line", "Second Line"},
                        new int[]{R.id.item, R.id.sub_item});

                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                    DateFormat df = new DateFormat();
                    String outFormat = "EEEE dd-MM-yyyy hh:mm aa";

                    for(int i=0;i<response.length();i++){
                        LinkedHashMap<String, String> resultsmap = new LinkedHashMap<>();

                        String theTime = new JSONObject(response.get(i).toString()).get("time").toString();
                        Date date = sf.parse(theTime);

                        resultsmap.put("First Line", String.valueOf(df.format(outFormat, date)));

                        String theTitle = new JSONObject(response.get(i).toString()).get("title").toString();
                        resultsmap.put("Second Line", theTitle);
                        mList.add(resultsmap);
                    }
                        resultsListView.setAdapter(mSimpleAdapter);

                        } catch (JSONException e){
                            e.printStackTrace();
                        } catch (ParseException e){
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
        LoginActivity.mRequestQueue.add(jsonArrayRequest);
        return v;
    }
}