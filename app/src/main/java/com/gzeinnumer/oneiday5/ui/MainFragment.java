package com.gzeinnumer.oneiday5.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gzeinnumer.oneiday5.R;
import com.gzeinnumer.oneiday5.adapter.RvUserAdapter;
import com.gzeinnumer.oneiday5.entity.User;
import com.gzeinnumer.oneiday5.viewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

//public MainFragment()
//public static MainFragment newInstance
//onCreateView
public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    private UserViewModel userViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    EditText edFname;
    EditText edLname;
    RecyclerView rv;
    Button button;

    List<User> list = new ArrayList<>();
    RvUserAdapter rvUserAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edFname = view.findViewById(R.id.ed_fname);
        edLname = view.findViewById(R.id.ed_lname);
        rv = view.findViewById(R.id.rv);
        button = view.findViewById(R.id.btn_simpan);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edFname.getText().toString().length()>0 && edLname.getText().toString().length()>0){
                    //simpan data
                    onSave();
                } else {
                    Toast.makeText(requireContext(), "Silahkan isi semua form", Toast.LENGTH_SHORT).show();
                }
            }
        });

        userViewModel.getAllUsers().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                list = users;
                initRv();
            }
        });
    }

    private void initRv() {
        rvUserAdapter = new RvUserAdapter(list);
        rv.setAdapter(rvUserAdapter);
        rv.hasFixedSize();
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void onSave() {
        User user =new User();
        user.firstName = edFname.getText().toString();
        user.lastName = edLname.getText().toString();
        userViewModel.insert(user);
    }
}