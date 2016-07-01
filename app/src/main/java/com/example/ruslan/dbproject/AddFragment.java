package com.example.ruslan.dbproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.pinball83.maskededittext.MaskedEditText;

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


        etPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher(){
            //we need to know if the user is erasing or inputing some new character
            private boolean backspacingFlag = false;
            //we need to block the :afterTextChanges method to be called again after we just replaced the EditText text
            private boolean editedFlag = false;
            //we need to mark the cursor position and restore it after the edition
            private int cursorComplement;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //we store the cursor local relative to the end of the string in the EditText before the edition
                cursorComplement = s.length()-etPhone.getSelectionStart();
                //we check if the user ir inputing or erasing a character
                if (count > after) {
                    backspacingFlag = true;
                } else {
                    backspacingFlag = false;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // nothing to do here =D
            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                //what matters are the phone digits beneath the mask, so we always work with a raw string with only digits
                String phone = string.replaceAll("[^\\d]", "");

                //if the text was just edited, :afterTextChanged is called another time... so we need to verify the flag of edition
                //if the flag is false, this is a original user-typed entry. so we go on and do some magic
                if (!editedFlag) {

                    //we start verifying the worst case, many characters mask need to be added
                    //example: 999999999 <- 6+ digits already typed
                    // masked: (999) 999-999
                    if (phone.length() >= 6 && !backspacingFlag) {
                        //we will edit. next call on this textWatcher will be ignored
                        editedFlag = true;
                        //here is the core. we substring the raw digits and add the mask as convenient
                        String ans = "(" + phone.substring(0, 3) + ") " + phone.substring(3,6) + "-" + phone.substring(6);
                        etPhone.setText(ans);
                        //we deliver the cursor to its original position relative to the end of the string
                        etPhone.setSelection(etPhone.getText().length()-cursorComplement);

                        //we end at the most simple case, when just one character mask is needed
                        //example: 99999 <- 3+ digits already typed
                        // masked: (999) 99
                    } else if (phone.length() >= 3 && !backspacingFlag) {
                        editedFlag = true;
                        String ans = "(" +phone.substring(0, 3) + ") " + phone.substring(3);
                        etPhone.setText(ans);
                        etPhone.setSelection(etPhone.getText().length()-cursorComplement);
                    }
                    // We just edited the field, ignoring this cicle of the watcher and getting ready for the next
                } else {
                    editedFlag = false;
                }
            }



        });




        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etFirstName.getText().toString().trim().equals("") || etLastName.getText().toString().trim().equals("") || etAdress.getText().toString().trim().equals("") || etPhone.getText().toString().trim().equals("")) {
                    /*Toast toast = Toast.makeText(getActivity(), R.string.empty, Toast.LENGTH_SHORT);
                    toast.show();*/
                    showToast(view);
                } else {
                    // Inserting Contacts
                    db.addContact(new Contact(etFirstName.getText().toString(), etLastName.getText().toString(), etAdress.getText().toString(), etPhone.getText().toString()));
                    Toast toast = Toast.makeText(getActivity(), R.string.update_db, Toast.LENGTH_SHORT);
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

    public void showToast(View view) {
        Toast toastEmpty = Toast.makeText(getActivity(),
                R.string.empty, Toast.LENGTH_LONG);
        toastEmpty.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastContainer = (LinearLayout) toastEmpty.getView();
        ImageView catImageView = new ImageView(getActivity());
        catImageView.setImageResource(R.drawable.ic_error_outline_black_24dp);
        toastContainer.addView(catImageView, 0);
        toastEmpty.show();
    }



}
