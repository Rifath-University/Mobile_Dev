package com.example.androidfitness;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class MicroInfoDialog extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState){
        AlertDialog.Builder MicroDialog = new AlertDialog.Builder(getActivity());
        MicroDialog.setTitle("Micronutrients Information")
                .setMessage("B - Vitamin B \n\nC - Vitamin C \nE - Vitamin E \nM - Magnesium \nZ - Zinc ")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                });
        return MicroDialog.create();
    }
}
