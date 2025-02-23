package com.fvjapps.yurikafunquiz.model;

import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class QuizReader {
    public static List<QuizItem> getItemsFromJSON(String jsonString) {
        List<QuizItem> quizItems = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                QuizItem quizItem = new QuizItem(
                        jsonObject.getString("question"),
                        jsonObject.getString("choice_1"),
                        jsonObject.getString("choice_2"),
                        jsonObject.getString("choice_3"),
                        jsonObject.getString("choice_4"),
                        jsonObject.getString("correct_choice")
                );
                quizItems.add(quizItem);
            }
        } catch (Exception e) {
            Log.e("QuizReader", "Error parsing JSON", e);
        }
        return quizItems;
    }

    public static void processJsonData(Context context, String jsonString) {
        SharedPreferencesHelper.overwriteQuizData(context, jsonString);
    }
}
