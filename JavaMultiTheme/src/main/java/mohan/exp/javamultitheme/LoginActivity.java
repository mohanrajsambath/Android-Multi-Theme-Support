package mohan.exp.javamultitheme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mohan.exp.javamultitheme.refer.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener,View.OnFocusChangeListener {

    public EditText edTxt_email,edTxt_password;
    public Button button_login;
    TextWatcher watch;
    public ImageView imgVw_login_logo;
    SharedPreferences mSpref;
    public String  MyPREFERENCES = "MyPrefs" ;
    public ProgressBar progress_bar;
    private String loggedAccount="";
    private final String LOGIN_TAG_UPS="UPS";
    private final String LOGIN_TAG_HD="HD";
    private final String LOGIN_TAG_CYRANO="CYRANO";
    private final String LOGIN_TAG_NONE="NONE";
    public  final String KEY_CURRENT_THEME = "current_theme";
    public  final String LILAC_THEME = "lilac";
    public  final String MINT_THEME = "mint";
    public  final String CYR_THEME = "cyr";
    public  final String HD_THEME = "hd";
    public  final String UPS_THEME = "ups";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        try {
            mSpref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            iniViews();
        }catch (Exception ex){ex.printStackTrace();}

    }

    private void iniViews(){
        getSupportActionBar().hide();
        Window window = getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));

        edTxt_email =  findViewById(R.id.edTxt_email);
        edTxt_password =  findViewById(R.id.edTxt_password);
        imgVw_login_logo =  findViewById(R.id.imgVw_login_logo);
        button_login =  findViewById(R.id.button_login);
        progress_bar =  findViewById(R.id.progress_bar);
        inittextWatch();

        button_login.setOnClickListener(this);
        edTxt_email.setOnFocusChangeListener(this);
        edTxt_password.setOnFocusChangeListener(this);
        edTxt_email.addTextChangedListener(watch);
    }

    private void inittextWatch() {
        try {
            watch = new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edt) {
                    String matchWords = edTxt_email.getText().toString().toLowerCase().trim();
                    if (edt.length() > 0) {
                        if (matchWords.contains("ups") || matchWords.contains("united parcel service")
                                || matchWords.contains("unitedparcel service")
                                || matchWords.contains("united parcelservice")
                                || matchWords.contains("unitedparcelservice")) {
                            imgVw_login_logo.setImageResource(R.drawable.ups_app_logo);
                            loggedAccount = LOGIN_TAG_UPS;
                            progress_bar.setVisibility(View.GONE);
                        } else if (matchWords.contains("hd")
                                || matchWords.contains("home")
                                || matchWords.contains("depot")) {
                            imgVw_login_logo.setImageResource(R.drawable.hd_app_logo);
                            loggedAccount = LOGIN_TAG_HD;
                            progress_bar.setVisibility(View.GONE);
                        } else if (matchWords.contains("cyr")
                                || matchWords.contains("cyrano")
                                || matchWords.contains("cyranosystems")
                                || matchWords.contains("cyranoapp")) {
                            imgVw_login_logo.setImageResource(R.drawable.cyrano_app_logo);
                            loggedAccount = LOGIN_TAG_CYRANO;
                            progress_bar.setVisibility(View.GONE);
                        } else {
                            imgVw_login_logo.setImageResource(R.drawable.ic_person_outline);
                            loggedAccount = LOGIN_TAG_NONE;
                            progress_bar.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

                }

                @Override
                public void onTextChanged(CharSequence s, int a, int b, int c) {
                }
            };
        }catch (Exception ex){ex.printStackTrace();}

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login:
                String mInputEmail=edTxt_email.getText().toString().trim();
                String mInputPassword=edTxt_password.getText().toString().trim();
                if(isEmailValid(mInputEmail)) {
                    if(mInputPassword.contentEquals("Test@2345")) {
                        if (mInputEmail.length() > 0) {
                            gotoActivity();
                        }
                    }else{
                        Toast.makeText(LoginActivity.this,"Please Enter Valid Password",Toast.LENGTH_SHORT).show();
                    }}else{
                    Toast.makeText(LoginActivity.this,"Please Enter Valid EMail",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void gotoActivity() {
        Intent io = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(io);
        finish();
    }

    public boolean isEmailValid(String email){
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if(matcher.matches())
            return true;
        else
            return false;
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.edTxt_email:
                if(hasFocus){
                    if(progress_bar.getVisibility()==View.GONE){
                        progress_bar.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.edTxt_password:
                if(hasFocus){
                    String mInputEmail=edTxt_email.getText().toString().trim();
                    if(isEmailValid(mInputEmail)) {
                        switch (loggedAccount){
                            case LOGIN_TAG_CYRANO:
                                mSpref.edit().putString(KEY_CURRENT_THEME, CYR_THEME).apply();
                                break;
                            case LOGIN_TAG_HD:
                                mSpref.edit().putString(KEY_CURRENT_THEME, HD_THEME).apply();
                                break;
                            case LOGIN_TAG_UPS:
                                mSpref.edit().putString(KEY_CURRENT_THEME, UPS_THEME).apply();
                                break;
                            case LOGIN_TAG_NONE:
                                mSpref.edit().putString(KEY_CURRENT_THEME, CYR_THEME).apply();
                                break;
                        }
                    }
                }
                break;
            default:
                break;
        }
    }
}
