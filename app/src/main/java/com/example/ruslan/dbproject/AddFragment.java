package com.example.ruslan.dbproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddFragment extends Fragment implements View.OnClickListener {
    EditText etFirstName, etLastName, etAdress, etPhone;
    Button btnAdd;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private DatabaseHandler db;

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


        etFirstName = (EditText) rootView.findViewById(R.id.etFirstName);
        etLastName = (EditText) rootView.findViewById(R.id.etLastName);
        etAdress = (EditText) rootView.findViewById(R.id.etAdress);
        etPhone = (EditText) rootView.findViewById(R.id.etPhone);
        btnAdd = (Button) rootView.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(this);

        DatabaseHandler db = new DatabaseHandler(getActivity());




       /* TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*/
        return rootView;
    }

    @Override
    public void onClick(View view) {

        // Inserting Contacts

        db.addContact(new Contact(etFirstName.getText().toString(), etLastName.getText().toString(), etAdress.getText().toString(), etPhone.getText().toString()));
    }
}
