package com.omegarts.mypi.ui.viewmodels;

import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {
    //region singleton

    private static SettingsViewModel _instance = null;

    public static SettingsViewModel GetInstance() {
        if (_instance == null) {
            _instance = new SettingsViewModel();
        }

        return _instance;
    }
    //endregion

    private SettingsViewModel(){

    }
}
