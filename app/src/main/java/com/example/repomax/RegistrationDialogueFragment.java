package com.example.repomax;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class RegistrationDialogueFragment extends DialogFragment {

    private final
    String[] academics = {"Escuela Elemental", "Escuela Intermedia", "Escuela Superior"};
    private List<Integer> academicSelection = new ArrayList<>();


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext(),0);
        builder.setTitle("Seleccione Nivel");
        builder.setSingleChoiceItems(academics,-1,((dialogInterface, i) -> {

            academicSelection.clear();
            academicSelection.add(i);

        }));

        builder.setPositiveButton("Ok", (dialog, which) -> {
            Bundle result = new Bundle();

            result.putString(RegisterFragment.RESULT_KEY, academics[academicSelection.get(0)]);
            getParentFragmentManager().setFragmentResult(RegisterFragment.REQUEST_KEY, result);
            dialog.dismiss();
        });
        builder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());


      return builder.create();

    }
}
