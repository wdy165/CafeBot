package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FragmentBlended extends Fragment {
    int subcategoryState = 0;
    RadioGroup radioGroup;
    RadioButton frappe;
    RadioButton shake;
    RadioButton smoothie;
    RadioButton juice;

    OnTimePickerSetListener onTimePickerSetListner;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnTimePickerSetListener){
            onTimePickerSetListner = (OnTimePickerSetListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_blended, container, false);

        if(getArguments() != null){
            subcategoryState = getArguments().getInt("subcategoryState");
        }

        frappe = (RadioButton)rootView.findViewById(R.id.frappe);
        shake = (RadioButton)rootView.findViewById(R.id.shake);
        smoothie = (RadioButton)rootView.findViewById(R.id.smoothie);
        juice = (RadioButton)rootView.findViewById(R.id.juice);

        switch (subcategoryState){
            case 1:
                frappe.setChecked(true);
                break;
            case 2:
                shake.setChecked(true);
                break;
            case 3:
                smoothie.setChecked(true);
                break;
            case 4:
                juice.setChecked(true);
                break;
            default:
                break;
        }

        radioGroup = (RadioGroup)rootView.findViewById(R.id.radioGroup5);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.frappe :
                        subcategoryState = 1;
                        onTimePickerSetListner.onTimePickerSet(subcategoryState);
                        break;
                    case R.id.shake :
                        subcategoryState = 2;
                        onTimePickerSetListner.onTimePickerSet(subcategoryState);
                        break;
                    case R.id.smoothie :
                        subcategoryState = 3;
                        onTimePickerSetListner.onTimePickerSet(subcategoryState);
                        break;
                    case R.id.juice :
                        subcategoryState = 4;
                        onTimePickerSetListner.onTimePickerSet(subcategoryState);
                        break;
                    default:
                        break;
                }
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}