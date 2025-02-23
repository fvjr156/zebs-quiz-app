package com.fvjapps.yurikafunquiz.ui.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.fvjapps.yurikafunquiz.R;
import com.fvjapps.yurikafunquiz.databinding.FragmentQuizBinding;
import com.fvjapps.yurikafunquiz.model.QuizItem;
import com.fvjapps.yurikafunquiz.model.QuizReader;
import com.fvjapps.yurikafunquiz.model.SharedPreferencesHelper;

import java.util.List;

public class QuizFragment extends Fragment {
    private FragmentQuizBinding binding;
    private TextView questionText;
    private Button choice1, choice2, choice3, choice4;
    private int currentIndex = 0;
    private int score = 0;
    private List<QuizItem> quizItems;
    private Drawable[] images;
    private boolean dialogBoxHidden;

    @Nullable
    @Override
    public FrameLayout onCreateView(@NonNull LayoutInflater lInf, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentQuizBinding.inflate(lInf, container, false);

        questionText = binding.fragmentQuizDialogBoxQuestion;
        choice1 = binding.fragmentQuizDialogBoxBtn1;
        choice2 = binding.fragmentQuizDialogBoxBtn2;
        choice3 = binding.fragmentQuizDialogBoxBtn3;
        choice4 = binding.fragmentQuizDialogBoxBtn4;

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        binding.fragmentQuizMessageBox.getLayoutParams().width = metrics.widthPixels - 20;
        binding.fragmentQuizMessageBox2.getLayoutParams().width = metrics.widthPixels - 20;
        binding.fragmentQuizDialogBox.getLayoutParams().width = metrics.widthPixels - 20;

        binding.fragmentQuizDialogBoxToggleBtn.setOnClickListener(v -> {
            dialogBoxHidden = !dialogBoxHidden;
            binding.fragmentQuizDialogBox.setVisibility((dialogBoxHidden) ? View.GONE : View.VISIBLE);
        });

        images = new Drawable[]{
                ContextCompat.getDrawable(getContext(), R.drawable.fix1),
                ContextCompat.getDrawable(getContext(), R.drawable.fix2),
                ContextCompat.getDrawable(getContext(), R.drawable.fix3),
                ContextCompat.getDrawable(getContext(), R.drawable.fix4_modified2),
                ContextCompat.getDrawable(getContext(), R.drawable.fix5),
                ContextCompat.getDrawable(getContext(), R.drawable.fix6_test),
        };

        binding.fragmentQuizScoreBoxRestartBtn.setOnClickListener(v -> {
            binding.fragmentQuizScoreBox.setVisibility(View.GONE);
            score = 0;
            currentIndex = 0;
            changeAvatarImage();
            initStartQuiz();
        });

        binding.fragmentQuizIntroStartBtn.setOnClickListener(v -> {
            initStartQuiz();
        });

        return binding.getRoot();
    }

    private void initStartQuiz() {
        loadQuizData(getContext());
        binding.fragmentQuizDialogBoxToggleBtn.setVisibility(View.VISIBLE);
        binding.fragmentQuizMessageBox.setVisibility(View.GONE);
        binding.fragmentQuizMessageBox2.setVisibility(View.GONE);
        binding.fragmentQuizDialogBox.setVisibility(View.VISIBLE);
    }

    private void loadQuizData(Context context) {
        String jsonData = SharedPreferencesHelper.getQuizData(context);
        quizItems = QuizReader.getItemsFromJSON(jsonData);

        if (quizItems.isEmpty()) {
            Toast.makeText(context, "Invalid quiz data. Please reimport.", Toast.LENGTH_LONG).show();
        } else {
            displayQuestion();
        }
    }

    private void displayQuestion() {
        if (currentIndex < quizItems.size()) {
            QuizItem currentQuiz = quizItems.get(currentIndex);
            questionText.setText(currentQuiz.getQuestion());
            choice1.setText(currentQuiz.getChoice1());
            choice2.setText(currentQuiz.getChoice2());
            choice3.setText(currentQuiz.getChoice3());
            choice4.setText(currentQuiz.getChoice4());

            choice1.setOnClickListener(v -> checkAnswer("choice_1"));
            choice2.setOnClickListener(v -> checkAnswer("choice_2"));
            choice3.setOnClickListener(v -> checkAnswer("choice_3"));
            choice4.setOnClickListener(v -> checkAnswer("choice_4"));
        } else {
            binding.fragmentQuizDialogBoxToggleBtn.setVisibility(View.GONE);
            binding.fragmentQuizDialogBox.setVisibility(View.GONE);
            binding.fragmentQuizScoreBox.setVisibility(View.VISIBLE);
            binding.fragmentQuizScoreBoxScore.setText(String.format("You got: %d/%d", score, quizItems.size()));
        }
    }

    private void checkAnswer(String answer) {
        QuizItem currentQuiz = quizItems.get(currentIndex);
        if (currentQuiz.getCorrectChoice().equals(answer)) {
            score++;
        }
        currentIndex++;
        displayQuestion();
        changeAvatarImage();
    }

    private void changeAvatarImage() {
        int imageChangeInterval = quizItems.size() / (images.length - 1);
        int imageIndex;

        if (score == quizItems.size()) {
            imageIndex = 5;
        } else if (score >= 4 * imageChangeInterval && score <= 5 * imageChangeInterval) {
            imageIndex = 4;
        } else if (score >= 3 * imageChangeInterval && score < 4 * imageChangeInterval) {
            imageIndex = 3;
        } else if (score >= 2 * imageChangeInterval && score < 3 * imageChangeInterval) {
            imageIndex = 2;
        } else if (score >= imageChangeInterval && score < 2 * imageChangeInterval) {
            imageIndex = 1;
        } else {
            imageIndex = 0;
        }

        Log.d("QuizDebug", String.format(
                "quizItems.size() = %d, \nscore = %d, \ncurrentIndex = %d, \nimageIndex = %d",
                quizItems.size(), score, currentIndex, imageIndex
        ));

        Drawable avatarImage = images[imageIndex];
        binding.fragmentQuizAvatar.setImageDrawable(avatarImage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}








