package com.example.mooc.Controllers.Guest;


import com.example.mooc.APIClasses.RequestManager;
import com.example.mooc.Controllers.Child.ChildMainActivity;
import com.example.mooc.Controllers.Parent.ParentMainActivity;
import com.example.mooc.APIClasses.QueryHandler;
import com.example.mooc.Entities.UserParent;
import com.example.mooc.Entities.UserStudent;
import com.example.mooc.Others.BasicAlertDialog;
import com.example.mooc.Entities.User;
import com.example.mooc.Entities.UserType;
import com.example.mooc.Listeners.LoginOnChangeFocusListener;
import com.example.mooc.Listeners.PasswordOnChangeFocusListener;
import com.example.mooc.Others.SPHandler;
import com.example.mooc.R;
import com.example.mooc.APIClasses.Interfaces.ChildAPI;
import com.example.mooc.APIClasses.RetrievedData;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import  android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import android.util.DisplayMetrics;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;

public class GuestMainActivity extends AppCompatActivity {

    private View active_layout;
    private int active_layout_id;
    private int active_button_id;

    private HashMap<String, View> layouts = new HashMap<>();
    private HashMap<Integer, Integer> buttonsStyle = new HashMap<>();




    RequestManager<ChildAPI> childRequestManager;
    ChildAPI childApi;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        childRequestManager = new RequestManager<>(ChildAPI.class, this);
        childApi = childRequestManager.getService();

        setContentView(R.layout.guest_activity_start);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        initilizeUI();
        listeners();
    }

    @Override
    public void onBackPressed() {
        AppBarLayout app = findViewById(R.id.appbar);

        if(app.getY() != 0)
        {
            setToolbarPosition(true);
        }
        else
        {
            BasicAlertDialog builder = new BasicAlertDialog(this, getResources().getString(R.string.alert_quit)) {
                @Override
                public void OnPositive() {
                    finish();
                }
            };

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void initilizeUI()
    {
        AppBarLayout appbar = findViewById(R.id.appbar);
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams)appbar.getLayoutParams();
        lp.height = getScreenHeight();

        View login_view = findViewById(R.id.login_page);
        View signup_view = findViewById(R.id.signup_page);

        active_button_id = 0;
        active_layout_id = R.id.welcome_page;
        active_layout = findViewById(active_layout_id);

        layouts.put(getString(R.string.welcome_text), findViewById(R.id.welcome_page));
        layouts.put(getString(R.string.login_text), findViewById(R.id.login_page));
        layouts.put(getString(R.string.signup_text), findViewById(R.id.signup_page));

        buttonsStyle.put(R.id.guest_home_button, R.id.guest_home_underline);
        buttonsStyle.put(R.id.guest_signup_button, R.id.guest_signup_underline);
        buttonsStyle.put(R.id.guest_login_button, R.id.guest_login_underline);

        login_view.setVisibility(View.INVISIBLE);
        signup_view.setVisibility(View.INVISIBLE);
    }

    public void listeners()
    {
        final EditText login_email = findViewById(R.id.login_email);
        final EditText login_password = findViewById(R.id.login_password);
        final EditText signup_email = findViewById(R.id.signup_email);
        final EditText signup_password = findViewById(R.id.signup_password);

        login_email.setOnFocusChangeListener(new LoginOnChangeFocusListener(login_email, this));
        signup_email.setOnFocusChangeListener(new LoginOnChangeFocusListener(signup_email, this));

        login_password.setOnFocusChangeListener(new PasswordOnChangeFocusListener(login_password, this));
        signup_password.setOnFocusChangeListener(new PasswordOnChangeFocusListener(signup_password, this));
    }


    public void changeContext(View view)
    {
        Button clicked =  findViewById(view.getId());
        String name = clicked.getText().toString();

        view.setPressed(true);
        active_layout.setVisibility(view.INVISIBLE);

        active_layout = layouts.get(name);
        active_layout_id = layouts.get(name).getId();
        active_layout.setVisibility(view.VISIBLE);

        if(active_button_id == 0)
            active_button_id = view.getId();

        changeStyle(active_button_id, R.color.textDefault, buttonsStyle.get(active_button_id), view.INVISIBLE);
        changeStyle(view.getId(), R.color.colorPrimaryDark, buttonsStyle.get(view.getId()), view.VISIBLE);
        active_button_id = view.getId();
        setToolbarPosition(false);
    }

    public void changeStyle(int id_button, int id_color_button,  int id_underline, int visibility)
    {
        Button clicked = findViewById(id_button);
        View underlined = findViewById(id_underline);

        clicked.setTextColor(getResources().getColor(id_color_button));
        underlined.setVisibility(visibility);
    }

    public int getScreenHeight()
    {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.heightPixels;

    }

    public void setToolbarPosition(boolean expand)
    {
        AppBarLayout appbar = findViewById(R.id.appbar);
        appbar.setExpanded(expand,true);
    }

    public void checkLogin(View view)
    {
        boolean is_error = false;

        EditText login = findViewById(R.id.login_email);
        EditText password = findViewById(R.id.login_password);

        is_error = !isEmailValid(login) || is_error;
        is_error = !isPasswordValid(password) || is_error;

        if(is_error)
            return;

        loginUser(login.getText().toString(), password.getText().toString());
    }


    public void loginUser(String login, String password)
    {
        Call<RetrievedData<User>> call = childApi.loginClient(
                login,
                password
        );

        childRequestManager.request(call, new QueryHandler<User>() {
            @Override
            public void onSuccess(User response, Context context) {
                Intent intent;
                User user = response;

                switch (response.getType().getId())
                {

                    case UserType.STUDENT :
                        UserStudent student = (UserStudent) response;
                        SPHandler.saveObjectToSharedPreference(context, SPHandler.RESSOURCES, SPHandler.USER, student);
                        intent = new Intent(context, ChildMainActivity.class);
                        break;

                    case UserType.PARENT :
                        UserParent parent = (UserParent) response;
                        SPHandler.saveObjectToSharedPreference(context, SPHandler.RESSOURCES, SPHandler.USER, parent);
                        intent = new Intent(context, ParentMainActivity.class);
                        break;

                    default:

                        Toast.makeText(context,getResources().getString(R.string.error_invalid_coordinates), Toast.LENGTH_SHORT).show();
                        return;
                }

                SPHandler.saveObjectToSharedPreference(context, SPHandler.RESSOURCES, SPHandler.USERTYPE, user.getType());

                startActivity(intent);
            }
        });
    }

    public void checkSignup(View view)
    {
        boolean is_error = false;

        final EditText fname = findViewById(R.id.signup_first_name);
        final EditText lname = findViewById(R.id.signup_last_name);
        final EditText login = findViewById(R.id.signup_email);
        final EditText password = findViewById(R.id.signup_password);
        final RadioGroup type = findViewById(R.id.son_parent_button_view);
        String user_type = (type.getCheckedRadioButtonId() == R.id.radio_child) ? "4" : "3";

        is_error = isEmptyEditText(fname) || is_error;
        is_error = isEmptyEditText(lname) || is_error;
        is_error = !isEmailValid(login) || is_error;
        is_error = !isPasswordValid(password) || is_error;

        if (is_error)
        {
            return;
        }


        Call<RetrievedData<User>> call = childApi.signupClient(
            login.getText().toString(),
            password.getText().toString(),
            fname.getText().toString(),
            lname.getText().toString(),
            user_type
        );

        childRequestManager.request(call, new QueryHandler<User>() {
            @Override
            public void onSuccess(User response, Context context) {
                loginUser(login.getText().toString(),  password.getText().toString());
            }
        });
    }

    boolean isEmailValid(EditText email)
    {
        boolean isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches();

        if(!isValid)
            email.setError(getResources().getString(R.string.error_invalid_email));
        else
            email.setError(null);

        return isValid;
    }

    boolean isPasswordValid(EditText password)
    {
        boolean isValid = password.getText().toString().length() >= 7;

        if(!isValid)
            password.setError(getResources().getString(R.string.error_invalid_password));
        else
        {
            password.setError(null);
        }

        return isValid;
    }

    boolean isEmptyEditText(EditText edt)
    {
        boolean isValid = edt.getText().toString().length() == 0;

        if(isValid)
            edt.setError(this.getResources().getString(R.string.error_empty_field));
        else
            edt.setError(null);

        return isValid;
    }



}
