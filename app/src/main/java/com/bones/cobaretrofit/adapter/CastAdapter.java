package com.bones.cobaretrofit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bones.cobaretrofit.R;
import com.bones.cobaretrofit.model.Cast;
import com.bones.cobaretrofit.model.CreditResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo ip on 23/06/2017.
 */

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder>{
    private List<Cast> list;
    private Context context;

    public CastAdapter(List<Cast> list,Context context){
        this.list = list;
        this.context = context;
    }

    public static class CastViewHolder extends RecyclerView.ViewHolder{
        ImageView castImage;
        TextView castName,castAs;

        public CastViewHolder(View itemView) {
            super(itemView);

            castImage = (ImageView) itemView.findViewById(R.id.castImage);
            castName = (TextView) itemView.findViewById(R.id.castName);
            castAs = (TextView) itemView.findViewById(R.id.castAs);

        }
    }

    @Override
    public CastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cast,null);

        CastViewHolder castViewHolder = new CastViewHolder(itemLayoutView);

        return castViewHolder;
    }

    @Override
    public void onBindViewHolder(CastViewHolder holder, int position) {
        Picasso.with(context)
                .load(list.get(position).getProfilePath())
                .placeholder(R.color.colorPrimary)
                .into(holder.castImage);
        holder.castName.setText(list.get(position).getName());
        holder.castAs.setText(list.get(position).getCharacter());
    }

    @Override
    public int getItemCount() {
        return 5;
    }



}
