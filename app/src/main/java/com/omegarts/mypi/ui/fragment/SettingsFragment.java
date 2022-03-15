package com.omegarts.mypi.ui.fragment;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.omegarts.MyPi;
import com.omegarts.mypi.R;
import com.omegarts.mypi.databinding.FragmentSettingsBinding;
import com.omegarts.mypi.ui.viewmodels.ServiceViewModel;

public class SettingsFragment extends Fragment {
    private ServiceViewModel settingsServiceModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        com.omegarts.mypi.databinding.FragmentSettingsBinding binding = FragmentSettingsBinding.inflate(inflater, container, false);

        settingsServiceModel = ServiceViewModel.GetInstance();
        MyPi.setStatusBarColor(getResources().getColor(R.color.navbar, getContext().getTheme()));
        return binding.getRoot();
    }
}
