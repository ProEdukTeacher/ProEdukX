package com.example.repomax;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class RegistrationDialogueFragment extends DialogFragment {

    private final
    String[] academics = {"Escuela Elemental", "Escuela Intermedia", "Escuela Superior"};
    private int academicSelection ;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext(),0);
        builder.setTitle("Seleccione Nivel");
        builder.setSingleChoiceItems(academics,academicSelection,(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                academicSelection = i;

            }
        }));

        builder.setPositiveButton("Ok", (dialog, which) -> {
            Bundle result = new Bundle();

            result.putString(RegisterFragment.RESULT_KEY, academics[academicSelection]);
            getParentFragmentManager().setFragmentResult(RegisterFragment.REQUEST_KEY, result);
            dialog.dismiss();
        });
        builder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());


      return builder.create();

    }
}
