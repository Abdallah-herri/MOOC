package com.example.mooc.Controllers.Common;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;

public abstract class CommonAbstractFragment extends Fragment
{
    public Context getFragmentContext()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            return getContext();
        }
        else
        {
            return getActivity();
        }
    }
}
