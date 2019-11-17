package com.br.agendacontatos.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.br.agendacontatos.R;
import com.br.agendacontatos.models.MessageModel;
import com.br.agendacontatos.repositories.ChatRepository;
import com.br.agendacontatos.repositories.LoginRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<MessageModel> messages;
    private Context context;

    public ChatAdapter(Context context, List<MessageModel> messages) {
        this.context = context;
        this.messages = messages;
    }

    public void notifyData(ArrayList<MessageModel> data){
        messages = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameTextView.setText(messages.get(position).getMessage());

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String date = formatter.format(messages.get(position).getDate());

        if(messages.get(position).getFrom().equals(LoginRepository.phone)){
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.holderMessageLinearLayout.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            holder.holderMessageLinearLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.item_green));
            holder.holderMessageLinearLayout.setLayoutParams(params);
            holder.phoneTextView.setText(date);
        }else {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.holderMessageLinearLayout.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            holder.holderMessageLinearLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.item_gray));
            holder.holderMessageLinearLayout.setLayoutParams(params);
            holder.phoneTextView.setText(date);
        }
    }

    @Override
    public int getItemCount() {
        if(messages != null) return messages.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout holderMessageLinearLayout;
        private TextView nameTextView;
        private TextView phoneTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_message);
            phoneTextView = itemView.findViewById(R.id.text_phone);
            holderMessageLinearLayout = itemView.findViewById(R.id.holder_message);
        }


    }
}
