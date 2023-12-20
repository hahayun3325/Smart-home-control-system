package com.example.smarthome.ui.scene;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smarthome.R;
import com.example.smarthome.databinding.FragmentSceneBinding;

public class SceneFragment extends Fragment{

    private FragmentSceneBinding binding;
    private final Handler mHandler = new Handler();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SceneViewModel dashboardViewModel =
                new ViewModelProvider(this).get(SceneViewModel.class);

        binding = FragmentSceneBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button backButton=binding.backButton;
        Button leaveButton=binding.leaveButton;
        Button cinemaButton=binding.cinemaButton;
        Button restButton=binding.restButton;
        backButton.setOnClickListener(v -> mHandler.post(this::backDialog));
        leaveButton.setOnClickListener(v -> mHandler.post(this::leaveDialog));
        cinemaButton.setOnClickListener(v->mHandler.post(this::cinemaDialog));
        restButton.setOnClickListener(v->mHandler.post(this::restDialog));
        /*final TextView textView = binding.textScene;*//*
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //回家场景弹窗
    private void backDialog(){
        //Create the dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //Set the dialog view
        LayoutInflater inflater = LayoutInflater.from(getContext());
        ConstraintLayout mDialogLayout = (ConstraintLayout) inflater.inflate(R.layout.back_scene, null);
        AlertDialog dialog = builder.setView(mDialogLayout).create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    //起床场景弹窗
    private void leaveDialog(){
        //Create the dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //Set the dialog view
        LayoutInflater inflater = LayoutInflater.from(getContext());
        ConstraintLayout mDialogLayout = (ConstraintLayout) inflater.inflate(R.layout.get_up_scene, null);
        AlertDialog dialog = builder.setView(mDialogLayout).create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    //观影场景弹窗
    private void cinemaDialog(){
        //Create the dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //Set the dialog view
        LayoutInflater inflater = LayoutInflater.from(getContext());
        ConstraintLayout mDialogLayout = (ConstraintLayout) inflater.inflate(R.layout.cinema_dialog, null);
        AlertDialog dialog = builder.setView(mDialogLayout).create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    //休息场景弹窗
    private void restDialog(){
        //Create the dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //Set the dialog view
        LayoutInflater inflater = LayoutInflater.from(getContext());
        ConstraintLayout mDialogLayout = (ConstraintLayout) inflater.inflate(R.layout.rest_dialog, null);
        AlertDialog dialog = builder.setView(mDialogLayout).create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}