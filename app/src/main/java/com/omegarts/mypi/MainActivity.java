package com.omegarts.mypi;

import static com.omegarts.MyPi.setWindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.omegarts.mypi.databinding.ActivityMainBinding;
import com.omegarts.mypi.ui.nav.CustomNav;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindow(getWindow());

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavController navController = Navigation.findNavController(this, R.id.main);
        CustomNav.setNavController(navController);

    }
}