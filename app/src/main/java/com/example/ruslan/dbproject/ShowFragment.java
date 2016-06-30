package com.example.ruslan.dbproject;


import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;

import java.util.ArrayList;

public class ShowFragment extends Fragment {


    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    private DatabaseHandler databaseHandler;
    private static final String ARG_SECTION_NUMBER = "section_number";
    RecyclerView rvContact;
    EditText filter;
    Button btnSearch;
  //  SearchView mSearchView;
    String searchText;
    private LinearLayoutManager mLayoutManager;
  //  private Context context;
    private DataAdapter adapter;



   private ArrayList<Contact> contacts = new ArrayList<>();
    private ArrayList<Contact> contactsDisplay = new ArrayList<>();
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

        btnSearch = (Button) rootView.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchList(filter.getText().toString());
            }
        });

        filter = (EditText) rootView.findViewById(R.id.filter);
        filter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {


            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            // при изменении текста выполняем фильтрацию
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(filter.getText().length()>2) {
                    searchList(s.toString());
                }
            }
        });

        rvContact = (RecyclerView) rootView.findViewById(R.id.rvContact);
        rvContact.setLayoutManager(new LinearLayoutManager(getActivity()));

       // searchText = filter.getText().toString();
        // rvContact.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        rvContact.setLayoutManager(mLayoutManager);

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

//        try {
//            db.open();
//            cursor = db.database.rawQuery("select * from " + DatabaseHandler.TABLE_CONTACTS, null);
//            String[] headers = new String[]{DatabaseHandler.KEY_FIRST_NAME, DatabaseHandler.KEY_LAST_NAME, DatabaseHandler.KEY_ADRESS, DatabaseHandler.KEY_PH_NO };
//            adapter = new SimpleCursorAdapter(this, R.layout.list_item,
//                    cursor, headers, new int[]{R.id.tvShowFirstName, R.id.tvShowLastName, R.id.tvShowAdress, R.id.tvShowPhone}, 0);
//
//            // если в текстовом поле есть текст, выполняем фильтрацию
//            // данная проверка нужна при переходе от одной ориентации экрана к другой
//            if(!filter.getText().toString().isEmpty())
//                adapter.getFilter().filter(filter.getText().toString());
//
//            // установка слушателя изменения текста
//
//            // устанавливаем провайдер фильтрации
//            adapter.setFilterQueryProvider(new FilterQueryProvider() {
//                @Override
//                public cursor runQuery(CharSequence constraint) {
//
//                    if (constraint == null || constraint.length() == 0) {
//
//                        return db.database.rawQuery("select * from " + DatabaseHandler.TABLE_CONTACTS, null);
//                    }
//                    else {
//                        return db.database.rawQuery("select * from " + DatabaseHandler.TABLE_CONTACTS + " where * " +
//                                /*DatabaseHandler.KEY_FIRST_NAME +*/ " like ?", new String[]{"%" + constraint.toString() + "%"});
//                    }
//                }
//            });
//
//            rvContact.setAdapter(adapter);;
//        }
//        catch (SQLException ex){}

    }

    private void updateUI(){
        contacts.clear();
        contacts.addAll((ArrayList<Contact>) databaseHandler.getAllContacts());
        if (adapter == null){
            adapter = new DataAdapter(getActivity(), contacts);
            rvContact.setAdapter(adapter);
        } else adapter.notifyDataSetChanged();
    }

    private void searchList(String search){
        contacts.clear();
        ArrayList<Contact> inputList =(ArrayList<Contact>) databaseHandler.getAllContacts();
        for(Contact contact: inputList){
            if (!TextUtils.isEmpty(contact.getFirstName())  && contact.getFirstName().toLowerCase().contains(search.toLowerCase())){
                contacts.add(contact);
            }
        }
        adapter.notifyDataSetChanged();
    }
}