package com.example.chenjiayou.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chenjiayou on 2018/7/19.
 */

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Car>      mCars;
    private CallBack       mCallBack;

    public CarAdapter(Context context) {

        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setCallBack(CallBack callBack) {

        mCallBack = callBack;
    }

    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = mLayoutInflater.inflate(R.layout.adapter_car, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CarAdapter.ViewHolder holder, int position) {

        if (mCars == null) {
            holder.mTVCarBrand.setText("no brand");
            holder.mTVCarType.setText("no type");
        } else {
            holder.mTVCarBrand
                    .setText(mCars.get(position).getBrand() + mCars.get(position).getId());
            holder.mTVCarType.setText(mCars.get(position).getType());
        }
    }

    @Override
    public int getItemCount() {

        return mCars == null ? 0 : mCars.size();
    }

    void setCars(List<Car> cars) {

        this.mCars = cars;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View     mParent;
        TextView mTVCarBrand;
        TextView mTVCarType;

        public ViewHolder(View itemView) {

            super(itemView);
            mTVCarBrand = itemView.findViewById(R.id.car_brand);
            mTVCarType = itemView.findViewById(R.id.car_type);
            mParent = itemView.findViewById(R.id.parent);
            mParent.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    if (mCallBack != null) {
                        mCallBack.onClick(getLayoutPosition());
                    }
                }
            });
        }

    }


    public interface CallBack {

        void onClick(int position);
    }
}
