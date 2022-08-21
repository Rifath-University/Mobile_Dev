package com.example.androidfitness.userinterface.macronutrients;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class MacroInfoDialog extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState) {
        AlertDialog.Builder MacroDialog = new AlertDialog.Builder(getActivity());
        MacroDialog.setTitle("Macronutrients Information")
                .setMessage("P - Protein \nF - Fat \nC - Carbohydrates \nF - Fibre \nS - Salt")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                });
        return MacroDialog.create();
    }
}
