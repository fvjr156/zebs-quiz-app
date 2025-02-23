package com.fvjapps.yurikafunquiz.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.fvjapps.yurikafunquiz.databinding.FragmentQuizfileimportBinding;
import com.fvjapps.yurikafunquiz.model.QuizReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class QuizFileImportFragment extends Fragment {
    private FragmentQuizfileimportBinding binding;

    private final ActivityResultLauncher<Intent> filePickerLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Uri uri = result.getData().getData();
                    if (uri != null) {
                        readJsonFromUri(uri);
                    }
                }
            });

    @Nullable
    @Override
    public FrameLayout onCreateView(@NonNull LayoutInflater lInf, @Nullable ViewGroup container, @Nullable Bundle savedinstanceState) {
        binding = FragmentQuizfileimportBinding.inflate(lInf, container, false);
        binding.quizfileimportImportbtn.setOnClickListener(v -> openFilePicker());
        return binding.getRoot();
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/json");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        filePickerLauncher.launch(Intent.createChooser(intent, "Select a JSON File"));
    }

    private void readJsonFromUri(Uri uri) {
        try {
            InputStream inputStream = requireActivity().getContentResolver().openInputStream(uri);
            BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = buf.readLine()) != null) {
                builder.append(line);
            }
            buf.close();
            String jsonData = builder.toString();

            QuizReader.processJsonData(requireContext(), jsonData);
            Toast.makeText(requireContext(), "Quiz Data Imported!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("QuizFileImportFragment", "Error reading file", e);
            Toast.makeText(requireContext(), "Error reading file.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
