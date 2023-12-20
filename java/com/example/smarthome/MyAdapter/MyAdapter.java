package com.example.smarthome.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarthome.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<String> mData;
    private Context mContext;

    public MyAdapter(Context context, List<String> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建ViewHolder对象,加载每个列表项的布局
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        //返回一个包含该布局的ViewHolder对象
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //将数据绑定到视图中
        //获取当前列表项的文本内容，并将其设置到TextView组件中
        String itemText = mData.get(position);
        holder.textView.setText(itemText);

        //根据字符串内容动态生成button
        String[] buttonTexts = getButtonTexts(itemText);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        for (String buttonText : buttonTexts) {
            Button button = new Button(mContext);
            button.setText(buttonText);
            button.setLayoutParams(layoutParams);
            holder.linearLayout.addView(button);
        }

        //创建Spinner和ArrayAdapter对象，并将其关联起来
        Spinner spinner = new Spinner(mContext);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                mContext, android.R.layout.simple_spinner_dropdown_item, getSpinnearItems(itemText));
        spinner.setAdapter(adapter);
        holder.linearLayout.addView(spinner);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
            linearLayout = itemView.findViewById(R.id.linear_layout);
        }
    }

    //根据字符串内容返回Button文本数组
    private String[] getButtonTexts(String text) {
        //根据字符串内容动态生成Button文本
        return new String[]{};
    }

    //根据字符串内容返回Spinner的选项数组
    private String[] getSpinnearItems(String text) {
        //根据字符串内容动态生成Spinner的选项
        return new String[]{};
    }
}
