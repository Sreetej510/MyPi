package com.omegarts.mypi.ui.nav;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;

import com.omegarts.mypi.R;
import com.omegarts.mypi.databinding.CustomNavBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CustomNav extends Fragment {

    private CustomNavBinding binding;
    private Context context;
    float moveYd;
    float moveYu;

    ObjectAnimator scaleUp, scaleDown;
    AnimatedVectorDrawable bumpUp, bumpDown;

    private static NavController navController;

    public static void setNavController(NavController navController) {
        CustomNav.navController = navController;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = CustomNavBinding.inflate(inflater, container, false);

        View.OnClickListener itemClick = this::onClick;
        binding.item1.setOnClickListener(itemClick);
        binding.item2.setOnClickListener(itemClick);
        binding.item3.setOnClickListener(itemClick);
        binding.item4.setOnClickListener(itemClick);
        binding.item5.setOnClickListener(itemClick);

        context = this.requireContext();
        moveYd = dipToPixels(context, 25);
        moveYu = dipToPixels(context, 10);
        bumpUp = (AnimatedVectorDrawable) binding.selected.getDrawable();
        bumpDown = (AnimatedVectorDrawable) binding.deselected.getDrawable();

        scaleUp = ObjectAnimator.ofPropertyValuesHolder(binding.selectedCircle,
                PropertyValuesHolder.ofFloat("scaleX", 1.1f),
                PropertyValuesHolder.ofFloat("translationY", moveYu),
                PropertyValuesHolder.ofFloat("scaleY", 1.1f));
        scaleUp.setDuration(50);

        scaleDown = ObjectAnimator.ofPropertyValuesHolder(binding.selectedCircle,
                PropertyValuesHolder.ofFloat("scaleX", 0.9f),
                PropertyValuesHolder.ofFloat("translationY", moveYd),
                PropertyValuesHolder.ofFloat("scaleY", 0.9f));
        scaleDown.setDuration(50);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static float dipToPixels(Context context, double dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) dipValue, metrics);
    }

    public void onClick(View view) {


        scaleDown.end();
        float moveX = 0;
        int destIndex = 0;
        int destId = 0;

        switch (view.getId()) {
            case R.id.item1:
                moveX = dipToPixels(context, -165);
                binding.selectedItem.setImageResource(R.drawable.avd_ssh);
                destIndex = 0;
                break;
            case R.id.item2:
                moveX = dipToPixels(context, -82.5);
                binding.selectedItem.setImageResource(R.drawable.avd_monitoring);
                destIndex = 1;
                break;
            case R.id.item3:
                binding.selectedItem.setImageResource(R.drawable.avd_services);
                destId = R.id.servicesFragment;
                destIndex = 2;
                break;
            case R.id.item4:
                moveX = dipToPixels(context, 82.5);
                binding.selectedItem.setImageResource(R.drawable.avd_controls);
                destIndex = 3;
                break;
            case R.id.item5:
                moveX = dipToPixels(context, 165);
                binding.selectedItem.setImageResource(R.drawable.avd_settings);
                destId = R.id.settingsFragment;
                destIndex = 4;
                break;
        }

        binding.item1.setVisibility(View.VISIBLE);
        binding.item2.setVisibility(View.VISIBLE);
        binding.item3.setVisibility(View.VISIBLE);
        binding.item4.setVisibility(View.VISIBLE);
        binding.item5.setVisibility(View.VISIBLE);

        view.setVisibility(View.INVISIBLE);
        binding.selected.setVisibility(View.INVISIBLE);

        ObjectAnimator animMoveX = ObjectAnimator.ofFloat(binding.selectedCircle, "translationX", moveX);
        animMoveX.setDuration(300);
        animMoveX.start();

        scaleDown.start();

        new android.os.Handler().postDelayed(() -> scaleUp.start(), 250);

        float finalMoveX = moveX;
        new android.os.Handler().postDelayed(() -> {
            binding.selected.setTranslationX(finalMoveX);
            binding.selected.setVisibility(View.VISIBLE);
            bumpUp.start();
        }, 100);

        bumpDown.start();

        AnimatedVectorDrawable avdIcon = (AnimatedVectorDrawable) binding.selectedItem.getDrawable();
        avdIcon.start();

        bumpDown.registerAnimationCallback(new Animatable2.AnimationCallback() {
            @Override
            public void onAnimationEnd(Drawable drawable) {
                binding.deselected.setTranslationX(finalMoveX);
                super.onAnimationEnd(drawable);
            }
        });

        int finalDestIndex = destIndex;
        int finalDestId = destId;
        new Handler(Looper.getMainLooper()).post(() -> {
            ArrayList<String> destinationsIndex = new ArrayList<>(
                    Arrays.asList("fragment_ssh", "fragment_monitoring", "fragment_services", "fragment_controls", "fragment_settings")
            );

            int currentIndex = destinationsIndex.indexOf(String.valueOf(navController.getCurrentDestination().getLabel()));

            NavOptions.Builder navBuilder = new NavOptions.Builder();

            if (currentIndex > finalDestIndex) {
                navBuilder.setEnterAnim(R.anim.slide_right).setExitAnim(R.anim.exit_right);
            } else {
                navBuilder.setEnterAnim(R.anim.slide_left).setExitAnim(R.anim.exit_left);
            }
//      TODO remove zero check after adding all fragments
            if (finalDestId != 0) {
                navController.navigate(finalDestId, null, navBuilder.build());
            }});
    }

}
