package com.example.androidfitness.userinterface.micronutrients;

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
                .setMessage("https://blog.nasm.org/micronutrients " +
                        " \n\n https://www.healthline.com/nutrition/micronutrients " +
                        " \n\n https://seven-seas.co.uk/micronutrients ")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                });
        return MicroDialog.create();
    }
}
