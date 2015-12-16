package com.daniel.partyhunter;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos on 03-Dec-15.
 */
public class Fiestas extends Fragment {

    ListView listview;
    List<String> list = new ArrayList<String>();
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fiestas, container, false);
        listview = (ListView) view.findViewById(R.id.listviewu);
        Data data = new Data();
        data.execute();
        return view;
    }

    private class Data extends AsyncTask<String, Void, String> {

        ProgressDialog loading;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            loading = ProgressDialog.show(getActivity(), "Por favor espera...",null, true, true);
        }

        @Override
        protected String doInBackground(String... params) {
            try {

                String respuesta = "";
                Hermes connection = new Hermes();
                respuesta = connection.talkToOlimpus("getDataUser.php", "clave=roja");
                System.out.println(respuesta);
                return respuesta;

            } catch (Exception e) {
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            loading.dismiss();

            try {
                ArrayAdapter<String> adapter = fillJson(result);
                listview.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayAdapter<String> fillJson(String json) throws JSONException {

        Object jsonObject = JSONValue.parse(json.toString());
        JSONArray array = (JSONArray)jsonObject;

        for(int i = 0; i < array.size(); i++){
            JSONObject row = (JSONObject) array.get(i);
            list.add(row.get("nombre").toString() + row.get("apellidos").toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1,list);
        return adapter;
    }

}
