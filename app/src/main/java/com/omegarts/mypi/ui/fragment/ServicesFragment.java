package com.omegarts.mypi.ui.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omegarts.MyPi;
import com.omegarts.mypi.R;
import com.omegarts.mypi.databinding.FragmentServicesBinding;
import com.omegarts.mypi.ui.viewmodels.ServiceViewModel;

public class ServicesFragment extends Fragment {

    private FragmentServicesBinding binding;
    public ServiceViewModel serviceViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentServicesBinding.inflate(inflater, container, false);

        serviceViewModel = ServiceViewModel.GetInstance();
        MyPi.setStatusBarColor(getResources().getColor(R.color.head_orange, getContext().getTheme()));

        setServices();

        return binding.getRoot();
    }

    private void setServices() {
        binding.service1.serviceName.setText("Monitoring");
        binding.service2.serviceName.setText("OmegaFlex");
        binding.service3.serviceName.setText("IP Update");
        binding.service4.serviceName.setText("Fan Control");
        binding.service5.serviceName.setText("Database");

        binding.service1.serviceImg.setImageResource(R.drawable.service_montoring);
        binding.service2.serviceImg.setImageResource(R.drawable.service_omegaflex);
        binding.service3.serviceImg.setImageResource(R.drawable.service_ipupdate);
        binding.service4.serviceImg.setImageResource(R.drawable.service_fancontrol);
        binding.service5.serviceImg.setImageResource(R.drawable.service_db);
    }
}