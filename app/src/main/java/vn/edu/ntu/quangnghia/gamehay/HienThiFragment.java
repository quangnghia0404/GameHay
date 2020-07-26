package vn.edu.ntu.quangnghia.gamehay;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.ntu.quangnghia.controller.IContactController;
import vn.edu.ntu.quangnghia.model.Contact;

public class HienThiFragment extends Fragment {
    EditText edtID,edtName,edtPhone,edtDate,edtAddress;
    Button btnthem;
    IContactController Controller;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hien_thi, container, false);
        addViews(view);
        addEvents();
        return view;
    }

    private void addEvents() {
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact c = new Contact();
                c.setId(Controller.layid());
                c.setName(edtName.getText().toString());
                c.setBirthday(edtDate.getText().toString());
                c.setPhone(edtPhone.getText().toString());
                c.setAddress(edtAddress.getText().toString());
                Controller.addContact(c);
                Toast.makeText(getActivity(),"Đã thêm thành công", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void addViews(View view) {
        edtID = view.findViewById(R.id.edtID);
        edtName = view.findViewById(R.id.edtName);
        edtPhone = view.findViewById(R.id.edtPhone);
        edtDate = view.findViewById(R.id.edtDate);
        btnthem = view.findViewById(R.id.btnthem);
        edtAddress = view.findViewById(R.id.edtAddress);

        Controller = (IContactController)((MainActivity)getActivity()).getApplication();
        edtID.setText(Integer.toString(Controller.layid()));
        navController = NavHostFragment.findNavController(HienThiFragment.this);
        ((MainActivity)getActivity()).navController = navController;
        ((MainActivity)getActivity()).toolbar.setSubtitle("Detal off friends");
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}