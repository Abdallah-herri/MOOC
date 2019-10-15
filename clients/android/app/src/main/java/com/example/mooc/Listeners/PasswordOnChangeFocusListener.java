package com.example.mooc.Listeners;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.EditText;

import com.example.mooc.R;

public class PasswordOnChangeFocusListener implements View.OnFocusChangeListener {

    private EditText edt;
    private Context context;

    public PasswordOnChangeFocusListener(EditText edt, Context context)
    {
        this.edt = edt;
        this.context = context;
    }
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus && !isPasswordValid(edt)) {
            edt.setError(context.getResources().getString(R.string.error_invalid_email));
        }
    }

    boolean isPasswordValid(EditText password)
    {
        return password.getText().toString().length() >= 7;
    }
}
