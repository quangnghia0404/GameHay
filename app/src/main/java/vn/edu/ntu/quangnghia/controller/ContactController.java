package vn.edu.ntu.quangnghia.controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.quangnghia.model.Contact;

public class ContactController extends Application implements IContactController {
    List<Contact> listcontacts = new ArrayList<>();

    public ContactController()
    {
        listcontacts.add(new Contact(1,"Nghĩa","26/09/1999","0772465715","Ninh Hòa"));
        listcontacts.add(new Contact(2,"Thúy","26/09/1999","0772465715","Ninh Hòa"));
        listcontacts.add(new Contact(3,"Duy","26/09/1999","0772465715","Ninh Hòa"));
        listcontacts.add(new Contact(4,"Kiệt","26/09/1999","0772465715","Ninh Hòa"));
        listcontacts.add(new Contact(5,"Dũng","26/09/1999","0772465715","Ninh Hòa"));
    }
    @Override
    public List<Contact> getAllContacts() {
        return listcontacts;
    }

    @Override
    public void addContact(Contact contact) {
        listcontacts.add(contact);
    }

    @Override
    public int layid() {
        return listcontacts.get(listcontacts.size() - 1).getId() +1;
    }
}
