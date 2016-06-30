package com.example.ruslan.dbproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddFragment extends Fragment {
    EditText etFirstName, etLastName, etAdress, etPhone;
    Button btnAdd;
    RecyclerView rvContact;

    private DataAdapter adapter;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public AddFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AddFragment newInstance(int sectionNumber) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);

        final DatabaseHandler db = new DatabaseHandler(getActivity());

        etFirstName = (EditText) rootView.findViewById(R.id.etFirstName);
        etLastName = (EditText) rootView.findViewById(R.id.etLastName);
        etAdress = (EditText) rootView.findViewById(R.id.etAdress);
        etPhone = (EditText) rootView.findViewById(R.id.etPhone);
        btnAdd = (Button) rootView.findViewById(R.id.btnAdd);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etFirstName.getText().toString().equals("") || etLastName.getText().toString().equals("") || etAdress.getText().toString().equals("") || etPhone.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getActivity(), "Fields is empty!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    // Inserting Contacts
                    db.addContact(new Contact(etFirstName.getText().toString(), etLastName.getText().toString(), etAdress.getText().toString(), etPhone.getText().toString()));
                    Toast toast = Toast.makeText(getActivity(), "The database is updated", Toast.LENGTH_SHORT);
                    toast.show();

                    //ArrayList<Contact> contacts = (ArrayList<Contact>) databaseHandler.getAllContacts();

                    //mContactList.clear();
                    //adapter.notifyDataSetChanged();
                }

            }
        });





       /* TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*/
        return rootView;
    }

}
