package com.example.ruslan.dbproject;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ShowFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    private DatabaseHandler databaseHandler;
    private static final String ARG_SECTION_NUMBER = "section_number";
    //private SimpleCursorAdapter scAdaper;
    ListView lvContact;

    public ShowFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ShowFragment newInstance(int sectionNumber) {
        ShowFragment fragment = new ShowFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_show, container, false);
        databaseHandler = new DatabaseHandler(getActivity());

        lvContact = (ListView) rootView.findViewById(R.id.lvContact);
        ArrayList<Contact> contacts = (ArrayList<Contact>) databaseHandler.getAllContacts();
        DataAdapter adapter = new DataAdapter(getActivity(), contacts);
        lvContact.setAdapter(adapter);

        // lvContact.setAdapter(scAdaper);
        //String[] from = new String[]{  };
        //int[] to = new int[]{ R.id.tvShowFirstName, R.id.tvShowLastName, R.id.tvShowAdress, R.id.tvShowPhone };

        //List<Contact> contactList = databaseHandler.getAllContacts();

        /*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*/
        return rootView;
    }

    private String [] getDataSet(){
        String[] mDataSet = new String[100];
        for (int i = 0; i < 100; i++){
            mDataSet[i] = "item" + i;
        }
        return mDataSet;
    }


    @Override
    public void onResume() {
        super.onResume();

    }

}
