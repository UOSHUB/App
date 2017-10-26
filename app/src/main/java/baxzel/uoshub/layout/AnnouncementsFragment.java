package baxzel.uoshub.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import baxzel.uoshub.R;

public class AnnouncementsFragment extends Fragment{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        if (container != null)
            container.removeAllViews();

        View v = inflater.inflate(R.layout.fragment_announcements, container, false);
        ListView resultsListView = (ListView) v.findViewById(R.id.announcements_list);
        HashMap<String, String> mHashMap = new HashMap<>();
        mHashMap.put("Programming 3", "HW2 Submission");
        mHashMap.put("Development of Databases", "Chapter 1.ppt");
        mHashMap.put("Marketing of Design", "Lab Quiz Tomorrow");
        mHashMap.put("Business", "Marks of Midterm");
        mHashMap.put("Senior", "Report 1 Submission");
        mHashMap.put("Business", "Bring your lab coat");
        mHashMap.put("Fundamentals of Drawing", "Papers and Colors available");

        List<LinkedHashMap<String, String>> mList = new ArrayList<>();
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(getContext(), mList, R.layout.fragment_announcements,
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
        return v;
    }
}