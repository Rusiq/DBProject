package com.example.ruslan.dbproject;


import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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
    RecyclerView rvContact;
    EditText filter;
    private LinearLayoutManager mLayoutManager;
  //  private Context context;
    private DataAdapter adapter;

   private ArrayList<Contact> contacts = new ArrayList<>();
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

        filter = (EditText) rootView.findViewById(R.id.filter);


        rvContact = (RecyclerView) rootView.findViewById(R.id.rvContact);
        rvContact.setLayoutManager(new LinearLayoutManager(getActivity()));

        // rvContact.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        rvContact.setLayoutManager(mLayoutManager);


        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

       // DataAdapter = new DataAdapter(context, );
       // rvContact.setLayoutManager(mLayoutManager);


       // contacts.addAll((ArrayList<Contact>) databaseHandler.getAllContacts());
       // adapter = new DataAdapter(getActivity(), contacts);
       // adapter.setNotifyOnChange(true);
       // rvContact.setAdapter(adapter);
       // lvContact.setAdapter(adapter);

        // lvContact.setAdapter(scAdaper);
        //String[] from = new String[]{  };
        //int[] to = new int[]{ R.id.tvShowFirstName, R.id.tvShowLastName, R.id.tvShowAdress, R.id.tvShowPhone };

        //List<Contact> contactList = databaseHandler.getAllContacts();

        /*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*/
        return rootView;
    }




    public void updateList(){
        contacts.clear();
        contacts.addAll((ArrayList<Contact>) databaseHandler.getAllContacts());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume", "onResume " + databaseHandler);
        updateUI();
    }

    private void updateUI(){
        contacts.clear();
        contacts.addAll((ArrayList<Contact>) databaseHandler.getAllContacts());
        if (adapter == null){
            adapter = new DataAdapter(getActivity(), contacts);
            rvContact.setAdapter(adapter);
        } else adapter.notifyDataSetChanged();
    }
}

















/*databaseHandler = new DatabaseHandler(getActivity());

        rvContact = (RecyclerView) rootView.findViewById(R.id.rvContact);
        rvContact.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvContact.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        rvContact.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<Contact> contacts = (ArrayList<Contact>) databaseHandler.getAllContacts();
        DataAdapter adapter = new DataAdapter(getActivity(), contacts);*/
