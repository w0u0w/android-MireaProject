package ru.mirea.velikanov.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalculateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculateFragment newInstance(String param1, String param2) {

        CalculateFragment fragment = new CalculateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    EditText editTextNumber3, editTextNumber2;
    Button umnBtn, sumBtn, minusBtn, delBtn;
    TextView answText;
    int num1, num2;
    float resultCalc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calculate, container, false);
        editTextNumber2 = (EditText)view.findViewById(R.id.editTextNumber2);
        editTextNumber3 = (EditText) view.findViewById(R.id.editTextNumber3);
            sumBtn = (Button)view.findViewById(R.id.sumBtn);
            minusBtn = (Button)view.findViewById(R.id.minusBtn);
            umnBtn = (Button)view.findViewById(R.id.umnBtn);
            delBtn = (Button)view.findViewById(R.id.delBtn);
        answText = (TextView)view.findViewById(R.id.answText);


        sumBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resultCalc = 0;

                    num1 = Integer.parseInt(editTextNumber2.getText().toString());
                    num2 = Integer.parseInt(editTextNumber3.getText().toString());
                    resultCalc = num1 + num2;
                    answText.setText(String.valueOf(resultCalc));
                }
            });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultCalc = 0;
                num1 = Integer.parseInt(editTextNumber2.getText().toString());
                num2 = Integer.parseInt(editTextNumber3.getText().toString());
                resultCalc = num1 - num2;
                answText.setText(String.valueOf(resultCalc));
            }
        });
        umnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultCalc = 0;
                num1 = Integer.parseInt(editTextNumber2.getText().toString());
                num2 = Integer.parseInt(editTextNumber3.getText().toString());
                resultCalc = num1 * num2;
                answText.setText(String.valueOf(resultCalc));
            }
        });
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultCalc = 0;
                num1 = Integer.parseInt(editTextNumber2.getText().toString());
                num2 = Integer.parseInt(editTextNumber3.getText().toString());
                resultCalc = num1 / num2;
                answText.setText(String.valueOf(resultCalc));
            }
        });

        return view;
    }










}