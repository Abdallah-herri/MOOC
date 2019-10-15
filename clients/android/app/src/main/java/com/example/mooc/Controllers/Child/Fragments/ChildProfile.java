package com.example.mooc.Controllers.Child.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.example.mooc.Controllers.Common.CommonProfile;
import com.example.mooc.Entities.User;
import com.example.mooc.Entities.UserStudent;
import com.example.mooc.Listeners.ButtonCopyListener;
import com.example.mooc.Listeners.SharingButtonListener;
import com.example.mooc.Others.SPHandler;
import com.example.mooc.R;

public class ChildProfile extends CommonProfile
{

    Context context;
    UserStudent student;

    @Override
    public Class<? extends User> getUserType()
    {
        return UserStudent.class;
    }

    @Override
    public View onCreateCommonView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.child_fragment_profile, container, false);
        context = getFragmentContext();
        student = (UserStudent) SPHandler.getSavedObjectFromPreference(context, SPHandler.RESSOURCES, SPHandler.USER, getUserType());

        EditText text_key = view.findViewById(R.id.profile_child_key);
        Button copy_button = view.findViewById(R.id.profile_child_copy_key);
        ImageButton share_button = view.findViewById(R.id.profile_child_share_key);

        text_key.setText(student.getStudentKey());

        String copy_info = getResources().getString(R.string.profile_button_copy_message);
        copy_button.setOnClickListener(new ButtonCopyListener(context, text_key, copy_info));

        String share_title = getResources().getString(R.string.profile_share_title);
        share_button.setOnClickListener(new SharingButtonListener(context, text_key, share_title));

        return view;
    }
}
