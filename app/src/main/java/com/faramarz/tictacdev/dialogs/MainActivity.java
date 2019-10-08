package com.faramarz.tictacdev.dialogs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_simple, btn_yes_no, btn_alert, btn_custom, btn_multi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        btn_simple.setOnClickListener(this);
        btn_multi.setOnClickListener(this);
        btn_yes_no.setOnClickListener(this);
        btn_alert.setOnClickListener(this);
        btn_custom.setOnClickListener(this);
    }

    private void bind() {
        btn_simple = findViewById(R.id.btn_simple);
        btn_yes_no = findViewById(R.id.btn_yes_no);
        btn_alert = findViewById(R.id.btn_alert);
        btn_custom = findViewById(R.id.btn_custom);
        btn_multi = findViewById(R.id.btn_multi);
    }

    @Override
    public void onClick(View v) {
        int selected = v.getId();
        switch (selected) {
            case R.id.btn_simple:
                simpleDialog();
                break;

            case R.id.btn_multi:
                multiElementDialog();
                break;

            case R.id.btn_yes_no:
                yesNoDialog();
                break;

            case R.id.btn_alert:
                alertDialog();
                break;

            case R.id.btn_custom:
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
