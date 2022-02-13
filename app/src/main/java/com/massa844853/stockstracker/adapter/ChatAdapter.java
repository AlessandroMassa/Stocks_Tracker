package com.massa844853.stockstracker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
;
import com.massa844853.stockstracker.R;
import com.massa844853.stockstracker.models.Message;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter{

    private List<Message> messagges;

    public ChatAdapter(List<Message> messages)
    {
        this.messagges = messages;
    }

    //ritorno il tipo di messaggio
    @Override
    public int getItemViewType(int position) {
        return messagges.get(position).getTypeMessage();
    }

    //creo l'holder in base al tipo
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;

        switch (viewType)
        {
            case 0:
                view = layoutInflater.inflate(R.layout.item_container_received_message, parent, false);
                return new ViewHolder0(view);
            case 1:
                view = layoutInflater.inflate(R.layout.item_container_sent_message, parent, false);
                return new ViewHolder1(view);
        }

        return null;
    }

    // associo i dati all'holder
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (messagges.get(position).getTypeMessage())
        {
            case 0:
                ViewHolder0 viewHolder0 = (ViewHolder0) holder;
                viewHolder0.textViewUsername.setText(messagges.get(position).getUsername());
                viewHolder0.textViewMessage.setText(messagges.get(position).getMessage());
                break;
            case 1:
                ViewHolder1 viewHolder1 = (ViewHolder1) holder;
                viewHolder1.textViewMessage.setText(messagges.get(position).getMessage());
                break;
        }
    }
    public void addItem(Message message)
    {
        messagges.add(message);
    }

    @Override
    public int getItemCount() {
        return messagges.size();
    }

    //2 classi di viewholder per i 2 tipi di messaggio
    class ViewHolder0 extends RecyclerView.ViewHolder
    {
        TextView textViewUsername;
        TextView textViewMessage;

        public ViewHolder0(@NonNull View itemView) {
            super(itemView);

            textViewUsername = itemView.findViewById(R.id.textUsername);
            textViewMessage = itemView.findViewById(R.id.textMessage);
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder
    {
        TextView textViewMessage;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);

            textViewMessage = itemView.findViewById(R.id.textMessage);
        }
    }
}
