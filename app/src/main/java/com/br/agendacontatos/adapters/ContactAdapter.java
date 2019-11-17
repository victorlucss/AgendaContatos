package com.br.agendacontatos.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.br.agendacontatos.R;
import com.br.agendacontatos.models.ContactModel;
import com.br.agendacontatos.views.ContactDetailActivity;
import com.br.agendacontatos.views.ContactsActivity;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<ContactModel> contacts;
    private Context context;

    public ContactAdapter(Context context, List<ContactModel> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    public void notifyData(ArrayList<ContactModel> data){
        contacts = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_contact, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameTextView.setText(contacts.get(position).getName());
        holder.phoneTextView.setText(contacts.get(position).getPhone());


    }

    @Override
    public int getItemCount() {
        if(contacts != null) return contacts.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView phoneTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_name);
            phoneTextView = itemView.findViewById(R.id.text_phone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();

                    if(pos != RecyclerView.NO_POSITION){
                        ContactModel contactDetail = contacts.get(pos);

                        Intent i = new Intent(context, ContactDetailActivity.class);
                        i.putExtra("contact", contactDetail);
                        context.startActivity(i);
                    }
                }
            });
        }


    }
}
