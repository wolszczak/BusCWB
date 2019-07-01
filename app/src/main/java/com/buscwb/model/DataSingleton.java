package com.buscwb.model;

import android.content.Context;

/**
 * Created by Wolszczak on 27/06/2019.
 */
public class DataSingleton {
    private static DataContext data = null;

    public static DataContext getInstance(Context context) {
        if (data==null) {

            data = new DataContext(context);
        }
        return data;
    }
}
