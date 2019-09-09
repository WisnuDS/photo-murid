package com.dicoding.aplikasiphotomurid.Dataset;

import android.util.Log;

import java.util.ArrayList;

public class DatasetDebug {
    public static String[][] data = new String[][] {
            {"1","Wisnu"},
            {"2","Dewa"},
            {"3","Saputra"}
    };

    public static ArrayList<DataModel> getAllData(){
        DataModel setData ;
        ArrayList<DataModel> list = new ArrayList<>();
        for(String[] Ddata:data){
            setData = new DataModel();
            setData.setId(Integer.parseInt(Ddata[0]));
            setData.setNama(Ddata[1]);
            list.add(setData);
        }
        if (!list.isEmpty()){
            Log.d("data", "getAllData: success");
        }else {
            Log.d("data", "getAllData: failed");
        }
        return list;

    }
}
