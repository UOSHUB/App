package baxzel.uoshub;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import baxzel.uoshub.layout.TimeFunctions;

public class MyAdapter extends ArrayAdapter{
    public static ArrayList<JSONObject> myData = new ArrayList<>();
    public String mMain;
    public String mSub;
    public String mWaqt;
    public String mInf;
    public String mOriginFragment;

    public MyAdapter( Context context, JSONArray data, String main, String sub, String waqt, String inf, String origin) throws JSONException{
        super(context,R.layout.new_item_layout, myData);

        mOriginFragment = origin;
        Log.v("Origin","Origin is:"+mOriginFragment);

        mMain = main;
        mSub = sub;
        mWaqt = waqt;
        mInf = inf;

        myData.clear();

        for(int i = 0; i < data.length(); i++)
            myData.add(data.getJSONObject(i));
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent){
        LayoutInflater myInflator = LayoutInflater.from(getContext());
        View customView = myInflator.inflate(R.layout.new_item_layout,parent,
                false);

        JSONObject course = myData.get(position);
        TextView itemTitle = (TextView) customView.findViewById(R.id.item_title);
        TextView itemSubTitle = (TextView) customView.findViewById(R.id.item_sub_title);
        TextView itemTime = (TextView) customView.findViewById(R.id.item_time);
        TextView itemInfo = (TextView) customView.findViewById(R.id.item_info);

        try{
            String theTitle = course.getString(mMain),
            theSubTitle = course.getString(mSub),
            theInfo = course.getString(mInf);

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            DateFormat df = new SimpleDateFormat("d MMM, EEE");

            if(mOriginFragment.equals("deadlines")){
                String theTime = course.getString(mWaqt);
                Date timeDate = sf.parse(theTime);
                String theTimeStr = df.format(timeDate);

                Date dueDate = sf.parse(theSubTitle);
                String dueDateStr = TimeFunctions.time_ago(dueDate);

                itemSubTitle.setText(dueDateStr);
                itemTime.setText(theTimeStr);
                itemTitle.setText(theTitle);
                itemInfo.setText(theInfo);
            }
            else if(mOriginFragment.equals("courses")){
                itemTitle.setText(theTitle);
                itemSubTitle.setText("");
                itemTime.setText("");
                itemInfo.setText("");
            }
            else if(mOriginFragment.equals("updates")){
                String theTime = course.getString(mWaqt);
                Date timeDate = sf.parse(theTime);
                String theTimeStr = df.format(timeDate);

                itemTitle.setText(theTitle);
                itemSubTitle.setText(theSubTitle);
                itemTime.setText(theTimeStr);
                itemInfo.setText("");
            }
            else if(mOriginFragment.equals("email")){
                String theTime = course.getString(mWaqt);
                Date timeDate = sf.parse(theTime);
                String theTimeStr = df.format(timeDate);

                itemTitle.setText(theTitle);
                itemSubTitle.setText(theSubTitle);
                itemTime.setText(theTimeStr);
                itemInfo.setText(theInfo);
            }
            else if(mOriginFragment.equals("calendar")){
                String theTime = course.getString(mWaqt);

                itemTitle.setText(theTitle);
                itemSubTitle.setText("");
                itemTime.setText(theTime);
                itemInfo.setText("");
            }
            else if(mOriginFragment.equals("grades")){
                String theTime = course.getString(mWaqt);
                String gradeStr = theSubTitle + ": " + theInfo + "/" + theTime;

                itemTitle.setText(theTitle);
                itemSubTitle.setText(gradeStr);
                itemTime.setText("");
                itemInfo.setText("");
            }



        } catch(Exception e){
            e.printStackTrace();
        }
        return customView;
    }
}