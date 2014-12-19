package com.example.owner.muccoursework;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by owner on 01/12/2014.
 */
public class AboutDialogue extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder AboutDialog = new AlertDialog.Builder(getActivity());
        AboutDialog.setMessage("This app allows you to browse Formula One drivers from the 2014 season and view their results at each race! News from Autosport's F1 RSS feed will also be displayed").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AboutDialog.setTitle("About");
        AboutDialog.setIcon(R.drawable.ic_menu_action_about);
        return AboutDialog.create();
    }
}
