package com.example.ruslan.dbproject;


public class Contact {

    //private variables
    int _id;
    String _firstName;
    String _lastName;
    String _adress;
    String _phone_number;

    // Empty constructor
    public Contact(){

    }
    // constructor
    public Contact(int id, String firstName, String lastName, String adress, String _phone_number){
        this._id = id;
        this._firstName = firstName;
        this._lastName = lastName;
        this._adress = adress;
        this._phone_number = _phone_number;
    }

    // constructor
    public Contact(String firstName, String lastName, String adress, String _phone_number){
        this._firstName = firstName;
        this._lastName = lastName;
        this._adress = adress;
        this._phone_number = _phone_number;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting first name
    public String getFirstName(){
        return this._firstName;
    }

    // setting first name
    public void setFirstName(String firstName){
        this._firstName = firstName;
    }

    // getting last name
    public String getLastName(){
        return this._lastName;
    }

    // setting last name
    public void setLastName(String lastName){
        this._lastName = lastName;
    }

    // getting adress
    public String getAdress(){
        return this._adress;
    }

    // setting adress
    public void setAdress(String adress){
        this._adress = adress;
    }

    // getting phone number
    public String getPhoneNumber(){
        return this._phone_number;
    }

    // setting phone number
    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }


}
