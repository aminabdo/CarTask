package com.softxpert.CarTask.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softxpert.car.R;
import com.softxpert.CarTask.model.Car;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private static final String TAG = "CarAdapter";
    private ArrayList<Car> mList = new ArrayList<>();
    private Context mContext;
    private boolean isLoaderVisible = false;


    public CarAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        holder.carName.setText(mList.get(position).getBrand());

        Glide.with(mContext).load(mList.get(position).getImageUrl())
                .into(holder.carImage);
        Log.e(TAG, "onBindViewHolder -> getImageUrl ->: "+mList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    public void setList(ArrayList<Car> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public Car getCarAt(int position){
        return mList.get(position);
    }



    public class CarViewHolder extends RecyclerView.ViewHolder {
        private ImageView carImage;
        private TextView carName;
        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            carImage = itemView.findViewById(R.id.car_imageView);
            carName = itemView.findViewById(R.id.car_name_textView);
        }
    }



    public void addItems(List<Car> carItems) {
        mList.addAll(carItems);
        notifyDataSetChanged();
    }


    public void addLoading() {
        isLoaderVisible = true;
        mList.add(new Car());
        notifyItemInserted(mList.size() - 1);
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }
}
