package ru.mirea.lebedeva.mireaproject;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Settings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Settings extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Settings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Settings.
     */
    // TODO: Rename and change types and number of parameters
    public static Settings newInstance(String param1, String param2) {
        Settings fragment = new Settings();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Context context;
    Resources resources;

    private Button buttonEdit;
    private EditText editText;
    private TextView languageText;

    final String SAVED_TEXT = "saved_text";
    final String SAVED_TITLE = "saved_title";
    public SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        buttonEdit = rootView.findViewById(R.id.buttonEdit);
        editText = rootView.findViewById(R.id.editText);
        languageText = rootView.findViewById(R.id.languageText);

        preferences = getActivity().getPreferences(MODE_PRIVATE);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().equals("ko") || editText.getText().toString().equals("eng")
                || editText.getText().toString().equals("ja")) {
                    context = LocaleHelper.setLocale(getActivity(), editText.getText().toString());
                    resources = context.getResources();

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(SAVED_TEXT, resources.getString(R.string.language));
                    editor.putString(SAVED_TITLE, resources.getString(R.string.title));
                    editor.apply();

                    String textForField = preferences.getString(SAVED_TEXT, "Empty");
                    String textForTitle = preferences.getString(SAVED_TITLE, "Empty");

                    languageText.setText(textForField);
                    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(textForTitle);
                }
            }
        });

        return rootView;
    }

}