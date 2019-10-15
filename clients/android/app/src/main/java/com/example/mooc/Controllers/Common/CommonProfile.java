package com.example.mooc.Controllers.Common;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mooc.Entities.User;
import com.example.mooc.Others.SPHandler;
import com.example.mooc.R;

public abstract class CommonProfile extends CommonAbstractFragment
{
    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Context context = getFragmentContext();
        View current = onCreateCommonView(inflater, container, savedInstanceState);

        TextView last_name = current.findViewById(R.id.profile_last_name);
        TextView first_name = current.findViewById(R.id.profile_first_name);
        EditText email = current.findViewById(R.id.profile_email);
        EditText current_password = current.findViewById(R.id.profile_curent_password);
        EditText new_password = current.findViewById(R.id.profile_new_password);
        Button submit = current.findViewById(R.id.profile_submit);

        User student = SPHandler.getSavedObjectFromPreference(context, SPHandler.RESSOURCES, SPHandler.USER, getUserType());

        last_name.setText(student.getLastName());
        first_name.setText(student.getFirstName());
        email.setText(student.getLogin());

        return current;
    }

    public abstract Class<? extends User> getUserType();

    public abstract View onCreateCommonView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
}
