package vn.edu.ntu.quangnghia.gamehay;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.quangnghia.controller.IContactController;
import vn.edu.ntu.quangnghia.model.Contact;

public class DanhSachFragment extends Fragment {
    List<Contact> listcontacts = new ArrayList<>();
    RecyclerView rvListContact;
    ContactAdapter adapter;
    NavController navController;
    IContactController Controller;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_danh_sach, container, false);
        addView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.my_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.itemAdd){
            navController.navigate(R.id.action_danhSachFragment_to_hienThiFragment);
        }
        return super.onOptionsItemSelected(item);
    }

    private void addView(View view) {
        rvListContact = view.findViewById(R.id.rvListContact);
        Controller = (IContactController)((MainActivity)getActivity()).getApplication();
        listcontacts = Controller.getAllContacts();
        adapter = new ContactAdapter(listcontacts);

        rvListContact.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvListContact.setAdapter(adapter);

        navController = NavHostFragment.findNavController(DanhSachFragment.this);
        ((MainActivity)getActivity()).navController = navController;
        ((MainActivity)getActivity()).toolbar.setSubtitle("List of friends");
    }

    public class ContactViewholder extends RecyclerView.ViewHolder{
        TextView txtName, txtDate, txtPhone;

        public ContactViewholder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtPhone = itemView.findViewById(R.id.txtPhone);
        }
        public void bind(Contact contact)
        {
            txtName.setText(contact.getName());
            txtDate.setText(contact.getBirthday());
            txtPhone.setText(contact.getPhone());
        }
    }

    public class ContactAdapter extends RecyclerView.Adapter<ContactViewholder>
    {
        List<Contact> listcontacts = new ArrayList<>();

        public ContactAdapter(List<Contact> listcontacts) {
            this.listcontacts = listcontacts;
        }

        @NonNull
        @Override
        public ContactViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.contact,parent,false);

            return new ContactViewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ContactViewholder holder, int position) {
            holder.bind(listcontacts.get(position));
        }

        @Override
        public int getItemCount() {
            return listcontacts.size();
        }
    }
}