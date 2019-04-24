package mohan.exp.javamultitheme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import mohan.exp.javamultitheme.refer.R;

public class BaseConfigActivity extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String KEY_CURRENT_THEME = "current_theme";
    public static final String LILAC_THEME = "lilac";
    public static final String MINT_THEME = "mint";
    public static final String CYR_THEME = "cyr";
    public static final String HD_THEME = "hd";
    public static final String UPS_THEME = "ups";

    public String mCurrentTheme="";
    SharedPreferences mSpref;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSpref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        mCurrentTheme = mSpref.getString(KEY_CURRENT_THEME,CYR_THEME);
        setAppTheme(mCurrentTheme);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String mSelectedTheme = mSpref.getString(KEY_CURRENT_THEME,CYR_THEME);
        if(!mCurrentTheme.equals(mSelectedTheme)){
           finish();
        }
    }

    private void setAppTheme(String mCurrentTheme) {
        switch (mCurrentTheme){
            case CYR_THEME:
                setTheme(R.style.Theme_App_Cyr);
                break;
            case HD_THEME:
                setTheme(R.style.Theme_App_Hd);
                break;
            case UPS_THEME:
                setTheme(R.style.Theme_App_Ups);
                break;
        }
    }
}
