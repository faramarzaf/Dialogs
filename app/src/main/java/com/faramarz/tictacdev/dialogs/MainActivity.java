package com.faramarz.tictacdev.dialogs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSimple, btnMulti, btnYesNo, btnProgress, btnAlert, btnCustom, btnProgressHorizontal;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        btnSimple.setOnClickListener(this);
        btnMulti.setOnClickListener(this);
        btnYesNo.setOnClickListener(this);
        btnProgress.setOnClickListener(this);
        btnAlert.setOnClickListener(this);
        btnCustom.setOnClickListener(this);
        btnProgressHorizontal.setOnClickListener(this);
    }

    private void bind() {
        btnSimple = findViewById(R.id.btnSimple);
        btnMulti = findViewById(R.id.btnMulti);
        btnYesNo = findViewById(R.id.btnYesNo);
        btnProgress = findViewById(R.id.btnProgress);
        btnAlert = findViewById(R.id.btnAlert);
        btnCustom = findViewById(R.id.btnCustom);
        btnProgressHorizontal = findViewById(R.id.btnProgressHorizontal);
    }

    @Override
    public void onClick(View v) {
        int selected = v.getId();
        switch (selected) {
            case R.id.btnSimple:
                simpleDialog();
                break;

            case R.id.btnMulti:
                multiElementDialog();
                break;

            case R.id.btnYesNo:
                yesNoDialog();
                break;

            case R.id.btnProgress:
                progressDialog();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress.dismiss();
                    }
                }, 2500);

                break;

            case R.id.btnProgressHorizontal:
                progressHorizontalDialog();

                break;

            case R.id.btnAlert:
                alertDialog();
                break;

            case R.id.btnCustom:
                customDialog();
                break;

            default:
                break;
        }

    }

    private void simpleDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Simple Dialog");
        alertDialog.setMessage("This is a simple dialog");
        // if you want user could not exit from dialog----> alertDialog.setCancelable(false);
        alertDialog.show();
    }

    private void multiElementDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an animal");
        String[] animals = {"horse", "cow", "camel", "sheep", "goat"};
        builder.setItems(animals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Toast.makeText(MainActivity.this, "horse", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "cow", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "camel", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "sheep", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(MainActivity.this, "goat", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void yesNoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Yes/No Dialog");
        builder.setMessage("Which one do you select?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "You selected Yes", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "You selected No", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void alertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Delete")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_delete)
                .show();
    }

    private void progressDialog() {
        progress = ProgressDialog.show(MainActivity.this, "Loading...", "Please wait to load data", true);
    }

    private void progressHorizontalDialog() {
        progress = new ProgressDialog(MainActivity.this);
        progress.setTitle("Downloading Image ...");
        progress.setMessage("Download in progress ...");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setProgress(0);
        progress.setMax(20);
        progress.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (progress.getProgress() <= progress.getMax()) {
                        Thread.sleep(500);
                        handle.sendMessage(handle.obtainMessage());
                        if (progress.getProgress() == progress.getMax()) {
                            progress.dismiss();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @SuppressLint("HandlerLeak")
    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progress.incrementProgressBy(1);
        }
    };

    private void customDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog_layout, viewGroup, false);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
