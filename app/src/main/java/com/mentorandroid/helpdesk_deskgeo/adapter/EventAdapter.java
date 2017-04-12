package com.mentorandroid.helpdesk_deskgeo.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mentorandroid.helpdesk_deskgeo.R;
import com.mentorandroid.helpdesk_deskgeo.model.Dos;
import com.mentorandroid.helpdesk_deskgeo.model.Events;

import java.util.List;

/**
 * Created by brunodelhferreira on 02/04/17.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private List<Events> eventList;

    private Context mContext;

    private EventAdapter.OnCardViewClickListener onCardViewClickListener;

    public EventAdapter.OnCardViewClickListener getOnCardViewClickListener() {
        return onCardViewClickListener;
    }

    public void setOnCardViewClickListener(EventAdapter.OnCardViewClickListener onCardViewClickListener) {
        this.onCardViewClickListener = onCardViewClickListener;
    }

    public EventAdapter(Context context, List<Events> eventList) {
        this.eventList = eventList;
        this.mContext = context;
        //this.fmAdapter = fm;
    }

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_event, null);
        EventAdapter.ViewHolder mh = new EventAdapter.ViewHolder(v);
        return mh;
    }
    // Inserir isso depois no
    class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView description;
        TextView status;
        public ViewHolder(View itemView){
            super(itemView);
            //foto = (ImageView) itemView.findViewById(R.id.foto);
            status = (TextView) itemView.findViewById(R.id.textViewStatus);
            description = (TextView) itemView.findViewById(R.id.textViewDescription);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            cardView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    // CallIntent(getAdapterPosition());
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder feedListRowHolder, int i) {
        final Events msg =  eventList.get(i);
        Log.d("MSG",msg.getDescription());
        feedListRowHolder.description.setText(msg.getDescription().toString());
        feedListRowHolder.status.setText(msg.getStatus().toString());
    }

    @Override
    public int getItemCount() {
        return (null !=  eventList ?  eventList.size() : 0);
    }


    public interface OnCardViewClickListener {

        void onClick(
                Dos dos
        );

    }
}
