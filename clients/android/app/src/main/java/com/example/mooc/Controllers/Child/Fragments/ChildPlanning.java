package com.example.mooc.Controllers.Child.Fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mooc.Controllers.Common.CommonAbstractFragment;
import com.example.mooc.R;


public class ChildPlanning extends CommonAbstractFragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.child_fragment_planning, container, false);

        return view;
    }

}
