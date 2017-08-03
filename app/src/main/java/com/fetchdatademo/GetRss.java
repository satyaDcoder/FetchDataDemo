package com.fetchdatademo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by SATYAPRAKASH on 02-08-2017.
 */

public class GetRss extends AsyncTask {
    ArrayList<CountryModel> itemModelArrayList;
    RecyclerView recyclerView;
    MyCustomAdapter adapter;

    private Context context;
    private ProgressDialog progressDialog;
    private String addressString = "http://www.androidbegin.com/tutorial/jsonparsetutorial.txt";
    private URL url;
    public GetRss(Context context, RecyclerView recyclerView) {
        this.context =  context;
        this.recyclerView = recyclerView;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait, Loading ...");
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
        super.onPreExecute();

    }

    @Override
    protected Object doInBackground(Object[] params) {

        HttpURLConnection httpURLConnection = null; // For the Connection to Server
        BufferedReader bufferedReader = null;  // For Reading The Data
        try {
            URL url =  new URL(addressString);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String  line = "";
            while ((line=bufferedReader.readLine())!=null)
            {
                stringBuffer.append(line);

            }
            String finalJsonString = stringBuffer.toString();
            JSONObject parentObject = new JSONObject(finalJsonString);
            JSONArray arr = parentObject.getJSONArray("worldpopulation");


           itemModelArrayList =  new ArrayList<>();


            for (int i = 0; i <arr.length() ; i++) {
                JSONObject c = arr.getJSONObject(i);
                CountryModel model =  new CountryModel();

                model.setCountry(c.getString("country"));
                model.setFlagUrl(c.getString("flag"));
                model.setPopulation(c.getString("population"));
                model.setRank(c.getInt("rank"));
                itemModelArrayList.add(model);
            }



                /*JSONArray parentArray = parentObject.getJSONArray("movies");

                JSONObject finalObject = parentArray.getJSONObject(0);

                String movieName =  finalObject.getString("movie");
                int year =  finalObject.getInt("year");*/


            return  null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection!=null)
                httpURLConnection.disconnect();
            try {
                if (bufferedReader!=null)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }



    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Object o) {
        progressDialog.dismiss();
        super.onPostExecute(o);
        if (itemModelArrayList.size()>0){
            try {
                adapter = new MyCustomAdapter(context  , itemModelArrayList);
                recyclerView.setAdapter(adapter);
                GridLayoutManager mGridLayoutManager = new GridLayoutManager(context, 1); // (Context context, int spanCount)
                recyclerView.setLayoutManager(mGridLayoutManager);
            }catch (Exception e){

            }
        }
    }

    public Document getData(){
        try {
            url =  new URL(addressString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream =  connection.getInputStream();
            DocumentBuilderFactory builderFactory =  DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument =  builder.parse(inputStream);
            return xmlDocument;

        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
}
