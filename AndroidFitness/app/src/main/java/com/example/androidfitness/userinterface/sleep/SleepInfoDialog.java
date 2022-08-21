package com.example.androidfitness.userinterface.sleep;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class SleepInfoDialog extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState){
        AlertDialog.Builder SleepDialog = new AlertDialog.Builder(getActivity());
        SleepDialog.setTitle("Sleeping Information")
                .setMessage("https://www.sleepassociation.org/about-sleep/stages-of-sleep/deep-sleep/ " +
                        " \n\n https://www.sleepscore.com/blog/get-more-deep-sleep/ " +
                        " \n\n https://www.mindbodygreen.com/articles/how-to-get-more-deep-sleep ")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                });
        return SleepDialog.create();
    }
}
