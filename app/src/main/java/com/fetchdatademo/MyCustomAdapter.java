package com.fetchdatademo;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder> {

    private Context context;

    private ArrayList<CountryModel> modelList;

    private LayoutInflater inflater;



    public MyCustomAdapter(Context context, ArrayList<CountryModel> modelList) {

        this.context = context;
        this.modelList = modelList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = inflater.inflate(R.layout.list_item_row, parent, false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int position) {

        final CountryModel currentModel  =  modelList.get(position);
        myViewHolder.tvCountryName.setText(currentModel.getCountry());
        myViewHolder.tvPopulation.setText(currentModel.getPopulation());
        myViewHolder.tvRank.setText(""+currentModel.getRank());
        Picasso.with(context).load(currentModel.getFlagUrl()).into(myViewHolder.imageView);

        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "position" + myViewHolder.getPosition(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtra("Link",modelList.get(position).getFlagUrl());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvCountryName, tvPopulation, tvRank;
        CardView cardView;
        ImageView imageView;


        public MyViewHolder(View itemView) {
            super(itemView);

            tvCountryName = (TextView) itemView.findViewById(R.id.tvCountryName);
            tvPopulation = (TextView) itemView.findViewById(R.id.tvPopulation);
            tvRank = (TextView) itemView.findViewById(R.id.tvRank);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            imageView = (ImageView) itemView.findViewById(R.id.imgView_Flag);

        }
    }
}
