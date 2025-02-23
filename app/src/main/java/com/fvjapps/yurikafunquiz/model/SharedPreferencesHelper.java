package com.fvjapps.yurikafunquiz.model;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {
    private static final String PREFS_NAME = "QuizAppPreferences";
    private static final String KEY_QUIZ_DATA = "quiz_data";

    public static void overwriteQuizData(Context context, String newData) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_QUIZ_DATA, newData);
        editor.apply();
    }

    public static String getQuizData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(KEY_QUIZ_DATA)) {
            return sharedPreferences.getString(KEY_QUIZ_DATA, DEFAULT_QUIZ_DATA);
        } else {
            return DEFAULT_QUIZ_DATA;
        }
    }

    public static final String DEFAULT_QUIZ_DATA = "[ { \"question\": \"What is React?\", \"choice_1\": \"A JavaScript library\", \"choice_2\": \"A CSS framework\", \"choice_3\": \"A database\", \"choice_4\": \"A programming language\", \"correct_choice\": \"choice_1\" }, { \"question\": \"What is JSX?\", \"choice_1\": \"A syntax extension for JavaScript\", \"choice_2\": \"A new programming language\", \"choice_3\": \"A type of database\", \"choice_4\": \"A CSS preprocessor\", \"correct_choice\": \"choice_1\" }, { \"question\": \"What is the virtual DOM?\", \"choice_1\": \"A copy of the real DOM\", \"choice_2\": \"A cloud storage service\", \"choice_3\": \"A new JavaScript framework\", \"choice_4\": \"A server-side rendering technique\", \"correct_choice\": \"choice_1\" }, { \"question\": \"Which function is used to create components in React?\", \"choice_1\": \"React.createComponent()\", \"choice_2\": \"React.createElement()\", \"choice_3\": \"React.render()\", \"choice_4\": \"React.init()\", \"correct_choice\": \"choice_2\" }, { \"question\": \"What is a React Hook?\", \"choice_1\": \"A function that lets you use state and other features in functional components\", \"choice_2\": \"A method for handling AJAX calls\", \"choice_3\": \"A built-in debugging tool\", \"choice_4\": \"A feature that replaces React components\", \"correct_choice\": \"choice_1\" }, { \"question\": \"Which hook is used to manage state in a functional component?\", \"choice_1\": \"useState\", \"choice_2\": \"useEffect\", \"choice_3\": \"useContext\", \"choice_4\": \"useRef\", \"correct_choice\": \"choice_1\" }, { \"question\": \"Which React method is used to update the UI?\", \"choice_1\": \"setState\", \"choice_2\": \"render\", \"choice_3\": \"updateDOM\", \"choice_4\": \"updateUI\", \"correct_choice\": \"choice_1\" }, { \"question\": \"What does useEffect do?\", \"choice_1\": \"Performs side effects in function components\", \"choice_2\": \"Creates a new React component\", \"choice_3\": \"Updates the component's state\", \"choice_4\": \"Replaces the render method\", \"correct_choice\": \"choice_1\" }, { \"question\": \"How do you pass data to a child component?\", \"choice_1\": \"Using state\", \"choice_2\": \"Using props\", \"choice_3\": \"Using Redux\", \"choice_4\": \"Using context\", \"correct_choice\": \"choice_2\" }, { \"question\": \"What is a higher-order component (HOC)?\", \"choice_1\": \"A function that returns a new component\", \"choice_2\": \"A React hook\", \"choice_3\": \"A built-in component from React\", \"choice_4\": \"A CSS-in-JS library\", \"correct_choice\": \"choice_1\" }, { \"question\": \"What is the main advantage of React?\", \"choice_1\": \"Improved performance due to the virtual DOM\", \"choice_2\": \"Built-in database management\", \"choice_3\": \"Automatic UI design\", \"choice_4\": \"Server-side rendering only\", \"correct_choice\": \"choice_1\" }, { \"question\": \"What is Redux used for in a React app?\", \"choice_1\": \"Managing global state\", \"choice_2\": \"Creating React components\", \"choice_3\": \"Styling components\", \"choice_4\": \"Handling form validation\", \"correct_choice\": \"choice_1\" }, { \"question\": \"What is React Router used for?\", \"choice_1\": \"Navigation and routing\", \"choice_2\": \"State management\", \"choice_3\": \"Styling components\", \"choice_4\": \"Handling API requests\", \"correct_choice\": \"choice_1\" }, { \"question\": \"Which of these is a valid way to conditionally render a component?\", \"choice_1\": \"Using the ternary operator\", \"choice_2\": \"Using a for loop\", \"choice_3\": \"Using an event listener\", \"choice_4\": \"Using an API call\", \"correct_choice\": \"choice_1\" }, { \"question\": \"What is the purpose of useContext?\", \"choice_1\": \"To manage global state without prop drilling\", \"choice_2\": \"To replace Redux\", \"choice_3\": \"To handle form validation\", \"choice_4\": \"To create event listeners\", \"correct_choice\": \"choice_1\" } ]";
}
