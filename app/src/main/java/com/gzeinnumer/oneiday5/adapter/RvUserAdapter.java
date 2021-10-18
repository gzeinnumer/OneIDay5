package com.gzeinnumer.oneiday5.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gzeinnumer.oneiday5.R;
import com.gzeinnumer.oneiday5.entity.User;

import java.util.List;

public class RvUserAdapter extends RecyclerView.Adapter<RvUserAdapter.MyHolder> {
    List<User> users;
    public RvUserAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.fName.setText(users.get(position).firstName);
        holder.lName.setText(users.get(position).lastName);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView fName;
        TextView lName;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            fName = itemView.findViewById(R.id.f_name);
            lName = itemView.findViewById(R.id.l_name);
        }
    }
}
