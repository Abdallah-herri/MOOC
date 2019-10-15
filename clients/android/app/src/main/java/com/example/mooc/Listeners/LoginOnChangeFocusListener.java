package com.example.mooc.Listeners;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.EditText;

import com.example.mooc.R;

public class LoginOnChangeFocusListener implements View.OnFocusChangeListener {

    private EditText edt;
    private Context context;
    public LoginOnChangeFocusListener(EditText edt, Context context)
    {
        this.context = context;
        this.edt = edt;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus && !isEmailValid(edt)) {
            edt.setError(context.getResources().getString(R.string.error_invalid_email));
        }
    }

    boolean isEmailValid(EditText email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches();
    }
}
