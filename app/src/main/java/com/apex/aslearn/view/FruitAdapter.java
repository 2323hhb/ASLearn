package com.apex.aslearn.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apex.aslearn.R;
import com.apex.aslearn.bean.Fruit;

import java.util.ArrayList;
import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder>{
    private List<Fruit> mFruitList;

    public FruitAdapter(List<Fruit> mFruitList) {
        this.mFruitList = mFruitList;
    }

    @NonNull
    @Override
    public FruitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fruit_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.fruitView.setOnClickListener(v -> {
//            int position = holder.getAdapterPosition();//改方法已经弃用了
            int position = holder.getBindingAdapterPosition();
            Fruit fruit = mFruitList.get(position);
            Toast.makeText(v.getContext(),"你点击了"+fruit.getName(),Toast.LENGTH_SHORT).show();
        });
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull FruitAdapter.ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitImageView.setImageResource(fruit.getImageID());
        holder.fruitTextView.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        //用来保存子项最外层布局的实例
        View fruitView;
        ImageView fruitImageView;
        TextView fruitTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            View fruitView;
            fruitImageView = (ImageView) itemView.findViewById(R.id.fruit_image);
            fruitTextView = (TextView) itemView.findViewById(R.id.fruit_name);

        }
    }
}
