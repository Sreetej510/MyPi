package com.omegarts.mypi.ui.viewmodels;

import androidx.lifecycle.ViewModel;

public class ServiceViewModel  extends ViewModel {

    //region singleton

    private static ServiceViewModel _instance = null;

    public static ServiceViewModel GetInstance() {
        if (_instance == null) {
            _instance = new ServiceViewModel();
        }

        return _instance;
    }
    //endregion

    private ServiceViewModel(){

    }
}
