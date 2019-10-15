package com.example.mooc.Controllers.Parent.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mooc.Controllers.Common.CommonProfile;
import com.example.mooc.Entities.User;
import com.example.mooc.Entities.UserParent;
import com.example.mooc.R;


public class ParentProfile extends CommonProfile
{
    @Override
    public Class<? extends User> getUserType()
    {
        return UserParent.class;
    }

    @Override
    public View onCreateCommonView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.parent_fragment_profile, container, false);

        return view;
    }

}
