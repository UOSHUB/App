package baxzel.uoshub.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import baxzel.uoshub.R;

public class AccountFragment extends Fragment{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if (container != null)
            container.removeAllViews();

        View v = inflater.inflate(R.layout.fragment_account, container, false);
        ListView resultsListView = (ListView) v.findViewById(R.id.listView1);
        ArrayList<String> arrPersonal = new ArrayList<String>();
        arrPersonal.add("Name:");
        arrPersonal.add("ID:");
        arrPersonal.add("Phone:");
        arrPersonal.add("Address:");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrPersonal);
        resultsListView.setAdapter(arrayAdapter);

        View vv = inflater.inflate(R.layout.fragment_account, container, false);
        ListView results2ListView = (ListView) v.findViewById(R.id.listView2);
        ArrayList<String> arrProgram = new ArrayList<String>();
        arrPersonal.add("College:");
        arrPersonal.add("Major:");
        arrPersonal.add("Level:");

        ArrayAdapter<String> array2Adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrProgram);
        results2ListView.setAdapter(array2Adapter);

        return v;
    }
}