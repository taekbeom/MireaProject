package ru.mirea.lebedeva.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Calculator#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Calculator extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Calculator() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Calculator.
     */
    // TODO: Rename and change types and number of parameters
    public static Calculator newInstance(String param1, String param2) {
        Calculator fragment = new Calculator();
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

    public String dumbOne = "";
    public String dumpOp = "";
    public String dumbTwo = "";

    private TextView result;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_calculator, container, false);

        result = rootView.findViewById(R.id.result);

        cringe(rootView, R.id.button1);
        cringe(rootView, R.id.button2);
        cringe(rootView, R.id.button3);
        cringe(rootView, R.id.button4);
        cringe(rootView, R.id.button5);
        cringe(rootView, R.id.button6);
        cringe(rootView, R.id.button7);
        cringe(rootView, R.id.button8);
        cringe(rootView, R.id.button9);
        cringe(rootView, R.id.button0);
        cringe(rootView, R.id.buttonAdd);
        cringe(rootView, R.id.buttonSub);
        cringe(rootView, R.id.buttonMult);
        cringe(rootView, R.id.buttonDiv);
        cringe(rootView, R.id.buttonAccept);

        // Inflate the layout for this fragment
        return rootView;
    }

    public Button buttonP;

    public void cringe(View view, int butId){
        buttonP = view.findViewById(butId);

        if (buttonP.getText().toString().equals("Submit")){
            onAcceptClick(view, butId);
        }
        else if (buttonP.getText().toString().equals("+") || buttonP.getText().toString().equals("-") ||
                buttonP.getText().toString().equals("*") || buttonP.getText().toString().equals("/")){
            onOperationClick(view, butId);
        }
        else {
            onNumberClick(view, butId);
        }
    }


    public void onNumberClick(View view, int butId){

        Button button = (Button) view.findViewById(butId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dumpOp.equals("")){
                    dumbOne += button.getText().toString();
                    result.setText(dumbOne);
                }
                else {
                    dumbTwo += button.getText().toString();
                    result.setText(dumbOne + " " + dumpOp + " " + dumbTwo);
                }
            }
        });

    }

    public void onOperationClick(View view, int  butId){

        Button button = (Button) view.findViewById(butId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!dumbOne.equals("")){
                    dumpOp = button.getText().toString();
                    result.setText(dumbOne + dumpOp);
                }
            }
        });

    }

    public void onAcceptClick(View view, int butId){

        Button button = (Button) view.findViewById(butId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!dumbOne.equals("") && !dumpOp.equals("") && !dumbTwo.equals("")){

                    int One = Integer.parseInt(dumbOne);
                    int Two = Integer.parseInt(dumbTwo);
                    float Res;
                    String resOfRes = "null";
                    switch (dumpOp){
                        case "+":
                            Res = One + Two;
                            resOfRes = String.valueOf(Res);
                            break;
                        case "-":
                            Res = One - Two;
                            resOfRes = String.valueOf(Res);
                            break;
                        case "*":
                            Res = One * Two;
                            resOfRes = String.valueOf(Res);
                            break;
                        case "/":
                            if (Two != 0){
                                Res = (float) One / Two;
                                resOfRes = String.valueOf(Res);
                            }
                            break;
                    }

                    result.setText(resOfRes);
                    dumbOne = "";
                    dumpOp = "";
                    dumbTwo = "";
                }
            }
        });
    }

}