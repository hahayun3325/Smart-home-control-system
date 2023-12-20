package com.example.smarthome.ui.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smarthome.R;
import com.example.smarthome.RecyclerViewActivity;
import com.example.smarthome.aesutils.aesUtils;
import com.example.smarthome.databinding.FragmentHomeBinding;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphImageButton;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TextView passwordView;
    private int mTemperature = 25;
    int currentIndex1 = 0;
    int currentIndex2 = 0;
    String[] modes = {"制冷模式", "制热模式", "通风模式"};
    String[] strength = {"低风", "中风", "高风"};
    private int mLight = 10;
    private final Handler mHandler = new Handler();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button disposableButton = binding.disposableButton;
        NeumorphImageButton lightButton = binding.lightButton;
        NeumorphImageButton airButton = binding.airButton;
        NeumorphImageButton moreButton = binding.moreButton;
        NeumorphImageButton refreshButton = binding.refreshButton;
        NeumorphImageButton sensorButton=binding.sensorButton;
        Button messageButton = binding.messageButton;
        NeumorphImageButton chartButton = binding.chartButton;
        NeumorphImageButton irButton = binding.irButton;

        disposableButton.setOnClickListener(v -> passwordShowDialog());
        airButton.setOnClickListener(v -> mHandler.post(this::airConditionerDialog));
        chartButton.setOnClickListener(v -> mHandler.post(this::chartShowDialog));
        lightButton.setOnClickListener(v -> mHandler.post(this::lightDialog));
        moreButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), RecyclerViewActivity.class);
            startActivity(intent);
        });
        irButton.setOnClickListener(v -> irDialog());
        sensorButton.setOnClickListener(v->sensorDialog());
        messageButton.setOnClickListener(v -> messageDialog());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //临时密码发送弹窗
    private void passwordShowDialog() {
        //Create the dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //Set the dialog view
        LayoutInflater inflater = LayoutInflater.from(getContext());
        ConstraintLayout mDialogLayout = (ConstraintLayout) inflater.inflate(R.layout.my_dialog_layout, null);
        passwordView = mDialogLayout.findViewById(R.id.password_once);
        Button generateButton = mDialogLayout.findViewById(R.id.generateButton);
        Button sendButton = mDialogLayout.findViewById(R.id.sendButton);

        passwordView.setText("------");
        AlertDialog dialog = builder.setView(mDialogLayout).create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //给生成按钮添加事件监听
        generateButton.setOnClickListener(v -> {
            //Use the Handler to post a Runnable object to generate the password
            mHandler.post(() -> passwordView.setText(aesUtils.oncePassword()));
        });

        //给发送按钮添加事件监听
        sendButton.setOnClickListener(v -> {
            //Use the Handler to post a message to handle the send action
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    //Hand the send action
                }
            });
        });

        dialog.show();
    }

    //开灯时长等可视化数据弹窗
    private void chartShowDialog() {

        //创建一个对话框，并设置其标题、内容视图
        //Create the dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //Set the dialog view
        LayoutInflater inflater = LayoutInflater.from(getContext());
        LinearLayout chartDialogLayout = (LinearLayout) inflater.inflate(R.layout.chart_dialog, null);
        // 创建一个HorizontalScrollView对象
        HorizontalScrollView scrollView = new HorizontalScrollView(getContext());

// 创建一个LinearLayout对象，用于将两个BarChart添加进去
        LinearLayout chartLayout = new LinearLayout(getContext());
        chartLayout.setOrientation(LinearLayout.HORIZONTAL);

// 创建一个开灯时长的BarChart
        BarChart lightChart = new BarChart(getContext());
        ArrayList<BarEntry> lightEntries = new ArrayList<>();
        lightEntries.add(new BarEntry(0, 0f));
        lightEntries.add(new BarEntry(1, 0f));
        lightEntries.add(new BarEntry(2, 0f));
        lightEntries.add(new BarEntry(3, 0f));
        lightEntries.add(new BarEntry(4, 0f));
        lightEntries.add(new BarEntry(5, 0f));
        lightEntries.add(new BarEntry(6, 0f));
        lightEntries.add(new BarEntry(7, 0f));
        lightEntries.add(new BarEntry(8, 0f));
        lightEntries.add(new BarEntry(9, 0f));
        lightEntries.add(new BarEntry(10, 0f));
        lightEntries.add(new BarEntry(11, 0f));
        lightEntries.add(new BarEntry(12, 0f));
        lightEntries.add(new BarEntry(13, 0f));
        lightEntries.add(new BarEntry(14, 0f));
        lightEntries.add(new BarEntry(15, 0f));
        lightEntries.add(new BarEntry(16, 0f));
        lightEntries.add(new BarEntry(17, 0f));
        lightEntries.add(new BarEntry(18, 0f));
        lightEntries.add(new BarEntry(19, 0f));
        lightEntries.add(new BarEntry(20, 0f));
        lightEntries.add(new BarEntry(21, 0f));
        lightEntries.add(new BarEntry(22, 0f));
        lightEntries.add(new BarEntry(23, 0f));

// 模拟数据
        float[] hourData = {2.5f, 3.8f, 4.3f, 3.9f, 2.7f, 1.6f, 1.1f, 4.0f, 8.0f, 10.0f,
                6.0f, 2.3f, 4.2f, 2.5f, 3.8f, 4.3f, 3.9f, 2.7f, 1.6f, 1.1f, 4.0f, 8.0f, 10.0f, 6.0f};
        for (int i = 0; i < hourData.length; i++) {
            lightEntries.set(i, new BarEntry(i, hourData[i]));
        }

        BarDataSet lightSet = new BarDataSet(lightEntries, "开灯时长");
        lightSet.setColor(Color.parseColor("#f99e1a"));
        BarData lightData = new BarData(lightSet);
        lightData.setBarWidth(0.9f);

// 修改x轴的标签为24小时
        XAxis xAxis = lightChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(24);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int hour = (int) value;
                return hour + ":00";
            }
        });

        lightChart.setData(lightData);


// 创建一个睡眠时长的BarChart
        BarChart sleepChart = new BarChart(getContext());
        ArrayList<BarEntry> sleepEntries = new ArrayList<>();
        sleepEntries.add(new BarEntry(0, 7.3f));
        sleepEntries.add(new BarEntry(1, 6.5f));
        sleepEntries.add(new BarEntry(2, 7.2f));
        sleepEntries.add(new BarEntry(3, 8.1f));
        sleepEntries.add(new BarEntry(4, 7.7f));
        sleepEntries.add(new BarEntry(5, 7.9f));
        sleepEntries.add(new BarEntry(6, 7.6f));
        BarDataSet sleepSet = new BarDataSet(sleepEntries, "睡眠时长");
        sleepSet.setColor(Color.parseColor("#5ab5e5"));
        BarData sleepData = new BarData(sleepSet);
        sleepData.setBarWidth(0.9f);
        sleepChart.setData(sleepData);

// 设置 X 轴的标签
        final String[] daysOfWeek = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        XAxis xAxis2 = sleepChart.getXAxis();
        xAxis2.setValueFormatter(new IndexAxisValueFormatter(daysOfWeek));
        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis2.setGranularity(1f);


// 设置BarChart的宽度和高度
        int chartWidth = 900; // 设置为你想要的宽度
        int chartHeight = 900; // 设置为你想要的高度
        lightChart.setLayoutParams(new LinearLayout.LayoutParams(chartWidth, chartHeight));
        sleepChart.setLayoutParams(new LinearLayout.LayoutParams(chartWidth, chartHeight));

// 设置BarChart之间的间距
        int chartMargin = 50; // 设置为你想要的间距
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) lightChart.getLayoutParams();
        params1.rightMargin = chartMargin;
        lightChart.setLayoutParams(params1);
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) sleepChart.getLayoutParams();
        params2.leftMargin = chartMargin;
        sleepChart.setLayoutParams(params2);

// 将两个BarChart添加进LinearLayout中
        chartLayout.addView(lightChart);
        chartLayout.addView(sleepChart);

// 将LinearLayout添加进HorizontalScrollView中
        scrollView.addView(chartLayout);

// 将HorizontalScrollView添加进你的布局中
        chartDialogLayout.addView(scrollView);

// 设置一些其他属性
        Description description1 = new Description();
        description1.setText("开灯时长");
        description1.setPosition(lightChart.getWidth() - 30, lightChart.getHeight() - 20); // 设置位置为右下角
        description1.setTextAlign(Paint.Align.RIGHT); // 设置对齐方式为右对齐
        lightChart.setDescription(description1);

        Description description2 = new Description();
        description2.setText("睡眠时长");
        description2.setPosition(lightChart.getWidth() - 30, lightChart.getHeight() - 20); // 设置位置为右下角
        description2.setTextAlign(Paint.Align.RIGHT); // 设置对齐方式为右对齐
        sleepChart.setDescription(description2);

        /* lightChart.getDescription().setText("开灯时长");*/
        /*sleepChart.getDescription().setText("睡眠时长");*/

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 设置X轴标签的样式
        xAxis.setTextSize(12f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(7);

// 设置Y轴标签的样式
        YAxis leftAxis = lightChart.getAxisLeft();
        leftAxis.setTextSize(12f);
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);

// 隐藏右侧的Y轴
        lightChart.getAxisRight().setEnabled(false);
        sleepChart.getAxisRight().setEnabled(false);

// 设置BarChart的动画效果
        lightChart.animateY(1000);
        sleepChart.animateY(1000);

// 刷新BarChart
        lightChart.invalidate();
        sleepChart.invalidate();
        // 禁用缩放
        lightChart.setScaleEnabled(false);
        sleepChart.setScaleEnabled(false);

// 禁用拖动
        lightChart.setDragEnabled(false);
        sleepChart.setDragEnabled(false);


        builder.setPositiveButton("EXIT", (dialog, which) -> dialog.dismiss());


        AlertDialog dialog = builder.setView(chartDialogLayout).create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    //空调控制弹窗
    private void airConditionerDialog() {
        //Create the dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //Set the dialog view
        LayoutInflater inflater = LayoutInflater.from(getContext());
        ConstraintLayout mDialogLayout = (ConstraintLayout) inflater.inflate(R.layout.airconditioner_dialog, null);
        AlertDialog dialog = builder.setView(mDialogLayout).create();
        TextView mTemperatureTextView = mDialogLayout.findViewById(R.id.temperatureView);
        TextView patternTextView = mDialogLayout.findViewById(R.id.patternTextView);
        TextView strengthTextView = mDialogLayout.findViewById(R.id.strengthTextView);
        SeekBar mTemperatureSeekBar = mDialogLayout.findViewById(R.id.temperatureSeekBar);

        //初始化textview和seekbar的值
        mTemperatureTextView.setText(String.format(getResources().getString(R.string.current_temperature), mTemperature));
        mTemperatureSeekBar.setMax(20);
        mTemperatureSeekBar.setProgress(mTemperature - 10);
        //加按钮的点击事件
        mDialogLayout.findViewById(R.id.plusButton).setOnClickListener(v -> {
            mTemperature++;
            if (mTemperature > 30) {
                mTemperature = 30;
            }
            mTemperatureTextView.setText(String.format(getResources().getString(R.string.current_temperature), mTemperature));
            mTemperatureSeekBar.setProgress(mTemperature - 10);
        });
        //减按钮的点击事件
        mDialogLayout.findViewById(R.id.minusButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTemperature--;
                if (mTemperature < 10) {
                    mTemperature = 10;
                }
                mTemperatureTextView.setText(String.format(getResources().getString(R.string.current_temperature), mTemperature));
                mTemperatureSeekBar.setProgress(mTemperature - 10);
            }
        });
        //seekBar的滑动事件
        mTemperatureSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTemperature = progress + 10;
                mTemperatureTextView.setText(String.format(getResources().getString(R.string.current_temperature), mTemperature));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        mDialogLayout.findViewById(R.id.patternButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //切换当前模式
                currentIndex1 = (currentIndex1 + 1) % modes.length;
                //更新内容
                patternTextView.setText(modes[currentIndex1]);
            }
        });
        mDialogLayout.findViewById(R.id.windButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex2 = (currentIndex2 + 1) % strength.length;
                strengthTextView.setText(strength[currentIndex2]);
            }
        });


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        dialog.show();
    }

    //灯光控制弹窗
    private void lightDialog() {
        //Create the dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //Set the dialog view
        LayoutInflater inflater = LayoutInflater.from(getContext());
        ConstraintLayout mDialogLayout = (ConstraintLayout) inflater.inflate(R.layout.light_dialog, null);
        AlertDialog dialog = builder.setView(mDialogLayout).create();

        SeekBar mLightSeekBar = mDialogLayout.findViewById(R.id.lightBar);
        mLightSeekBar.setProgress(mLight);
        mLightSeekBar.setMax(20);
        mDialogLayout.findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLight++;
                mLightSeekBar.setProgress(mLight);
            }
        });
        mDialogLayout.findViewById(R.id.minusButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLight--;
                mLightSeekBar.setProgress(mLight);
            }
        });
        mLightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mLight = progress + 1;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void irDialog() {
        //Create the dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //Set the dialog view
        LayoutInflater inflater = LayoutInflater.from(getContext());
        ConstraintLayout mDialogLayout = (ConstraintLayout) inflater.inflate(R.layout.launch_dialog, null);
        AlertDialog dialog = builder.setView(mDialogLayout).create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    //留言板弹窗
    private void messageDialog() {
        //Create the dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //Set the dialog view
        LayoutInflater inflater = LayoutInflater.from(getContext());
        ConstraintLayout mDialogLayout = (ConstraintLayout) inflater.inflate(R.layout.message_board_dialog, null);
        AlertDialog dialog = builder.setView(mDialogLayout).create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void setupTemperatureChart(LineChart temperatureChart) {
        List<Entry> entries = new ArrayList<>();

        // Add some mock data
        for (int i = 0; i < 24; i++) {
            float temperature = (float) (Math.random() * 10 + 20);
            entries.add(new Entry(i, temperature));
        }

        // Create a LineDataSet from the entries
        LineDataSet dataSet = new LineDataSet(entries, "Temperature");
        dataSet.setColor(Color.RED);
        dataSet.setDrawValues(false);

        // Configure the chart
        temperatureChart.setData(new LineData(dataSet));
        temperatureChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        temperatureChart.getAxisLeft().setAxisMinimum(0);
        temperatureChart.getAxisLeft().setAxisMaximum(40);
        temperatureChart.getAxisRight().setEnabled(false);
        temperatureChart.getDescription().setEnabled(false);
        temperatureChart.setTouchEnabled(false);
        temperatureChart.setDragEnabled(false);
        temperatureChart.setScaleEnabled(false);
    }

    private void setupHumidityChart(LineChart humidityChart) {
        List<Entry> entries = new ArrayList<>();

        // Add some mock data
        for (int i = 0; i < 24; i++) {
            float humidity = (float) (Math.random() * 20 + 40);
            entries.add(new Entry(i, humidity));
        }

        // Create a LineDataSet from the entries
        LineDataSet dataSet = new LineDataSet(entries, "Humidity");
        dataSet.setColor(Color.BLUE);
        dataSet.setDrawValues(false);

        // Configure the chart
        humidityChart.setData(new LineData(dataSet));
        humidityChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        humidityChart.getAxisLeft().setAxisMinimum(0);
        humidityChart.getAxisLeft().setAxisMaximum(100);
        humidityChart.getAxisRight().setEnabled(false);
        humidityChart.getDescription().setEnabled(false);
        humidityChart.setTouchEnabled(false);
        humidityChart.setDragEnabled(false);
        humidityChart.setScaleEnabled(false);

    }

    private void sensorDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //Set the dialog view
        LayoutInflater inflater = LayoutInflater.from(getContext());
        LinearLayout mDialogLayout = (LinearLayout) inflater.inflate(R.layout.sensor_dialog, null);
        LineChart temperatureChart = mDialogLayout.findViewById(R.id.temperature_chart);
        setupTemperatureChart(temperatureChart);

        LineChart humidityChart = mDialogLayout.findViewById(R.id.humidity_chart);
        setupHumidityChart(humidityChart);
        AlertDialog dialog = builder.setView(mDialogLayout).create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}