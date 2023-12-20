package com.example.smarthome.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smarthome.R;
import com.example.smarthome.databinding.FragmentMineBinding;

public class MineSettingsFragment extends Fragment implements View.OnClickListener{

    private FragmentMineBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MineSettingsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(MineSettingsViewModel.class);

        binding = FragmentMineBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       /* final TextView textView = binding.textMine;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.userButton){

        }else if(v.getId()==R.id.networkButton){

        }else if (v.getId()==R.id.gestureButton){

        }else if(v.getId()==R.id.switchoverButton){

        }
    }
}