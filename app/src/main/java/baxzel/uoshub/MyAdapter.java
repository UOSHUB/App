package baxzel.uoshub;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Muhammad Owais on 26-Nov-17.
 */



public class MyAdapter extends ArrayAdapter
{
    public static ArrayList<JSONObject> myData = new ArrayList<>();
    public String mMain;
    public String mSub;
    public String mWaqt;
    public String mInf;

    public MyAdapter( Context context, JSONArray data,
          String main, String sub, String waqt, String inf )
            throws JSONException {
        super(context,R.layout.new_item_layout, myData);

        mMain = main;
        mSub = sub;
        mWaqt = waqt;
        mInf = inf;

        for(int i = 0; i < data.length(); i++)
            myData.add(data.getJSONObject(i));
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent)
    {
        LayoutInflater myInflator = LayoutInflater.from(getContext());
        View customView = myInflator.inflate(R.layout.new_item_layout,parent,false);

        JSONObject course = myData.get(position);
        TextView itemTitle = (TextView) customView.findViewById(R.id.item_title);
        TextView itemSubTitle = (TextView) customView.findViewById(R.id.item_sub_title);
        TextView itemTime = (TextView) customView.findViewById(R.id.item_time);
        TextView itemInfo = (TextView) customView.findViewById(R.id.item_info);


        String theTitle = null, theSubTitle = null, theTime = null, theInfo = null;
        try {
            theTitle = course.getString(mMain);
            theTime = course.getString(mWaqt);
            theSubTitle = course.getString(mSub);
            theInfo = course.getString(mInf);
        } catch (JSONException e) {
            e.printStackTrace();
        }

      itemTitle.setText(theTitle);
      itemSubTitle.setText(theSubTitle);
      itemTime.setText(theTime);
      itemInfo.setText(theInfo);


        return customView;
    }
}
