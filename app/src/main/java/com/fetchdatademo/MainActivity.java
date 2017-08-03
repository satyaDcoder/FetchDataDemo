package com.fetchdatademo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);


        if (!Check.InternetService(getApplicationContext())) {
            alertDialogDisplay();
        } else {
            GetRss getRss = new GetRss(MainActivity.this, recyclerView);
            getRss.execute();
        }



    }




    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.sync:
                if (!Check.InternetService(getApplicationContext())) {
                    alertDialogDisplay();
                } else {
                    GetRss getRss = new GetRss(this, recyclerView);
                    getRss.execute();
                }

                break;

            default:
                break;
        }

        return true;
    }

    private void alertDialogDisplay() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("To Fetch data ,On Mobile Data or Connect WiFi")
                .setTitle("No Internet Connection");

        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
