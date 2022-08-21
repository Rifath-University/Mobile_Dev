package com.example.androidfitness.userinterface.steps;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class StepsInfoDialog extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState){
        AlertDialog.Builder StepsDialog = new AlertDialog.Builder(getActivity());
        StepsDialog.setTitle("More information on how to get more steps")
                .setMessage("https://www.healthline.com/health/walking-vs-running " +
                        " \n\n https://www.verywellfit.com/does-running-burn-more-calories-than-walking-3435132 " +
                        " \n\n https://livehealthy.chron.com/amount-calories-burnt-running-vs-walking-4593.html ")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                });
        return StepsDialog.create();
    }

}
