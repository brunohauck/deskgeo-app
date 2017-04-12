package com.mentorandroid.helpdesk_deskgeo.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mentorandroid.helpdesk_deskgeo.R;
import com.mentorandroid.helpdesk_deskgeo.model.Dos;

import java.util.List;

/**
 * Created by brunodelhferreira on 25/03/17.
 */

public class DosAdapter extends RecyclerView.Adapter<DosAdapter.ViewHolder>  {

    private List<Dos> dosList;

    private Context mContext;

    private OnCardViewClickListener onCardViewClickListener;

    public OnCardViewClickListener getOnCardViewClickListener() {
        return onCardViewClickListener;
    }

    public void setOnCardViewClickListener(OnCardViewClickListener onCardViewClickListener) {
        this.onCardViewClickListener = onCardViewClickListener;
    }

    public DosAdapter(Context context, List<Dos> dosList) {
        this.dosList = dosList;
        this.mContext = context;
        //this.fmAdapter = fm;
    }

    @Override
    public DosAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_dos, null);
        ViewHolder mh = new ViewHolder(v);

        return mh;
    }
    // Inserir isso depois no
    class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView mensagem;
        ImageView img;
        public ViewHolder(View itemView){
            super(itemView);
            //foto = (ImageView) itemView.findViewById(R.id.foto);
            //nome = (TextView) itemView.findViewById(R.id.nome);
            mensagem = (TextView) itemView.findViewById(R.id.mensagem);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            img = (ImageView) itemView.findViewById(R.id.imageView4);
            cardView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    // CallIntent(getAdapterPosition());
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder feedListRowHolder, int i) {
        final Dos msg =  dosList.get(i);
        Log.d("MSG",msg.getMsgEnviada());
        feedListRowHolder.mensagem.setText(msg.getMsgEnviada().toString());

        if(msg.getMsgEnviada().toString().equals("The traffic is normal")){
            feedListRowHolder.img.setBackgroundResource(R.drawable.deskgeo);
        }
        //qImageView.setBackgroundResource(R.drawable.thumbs_down);
    }

    @Override
    public int getItemCount() {
        return (null !=  dosList ?  dosList.size() : 0);
    }


    public interface OnCardViewClickListener {

        void onClick(
                Dos dos
        );

    }
}
