package mohan.exp.javamultitheme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CompoundButton;
import android.widget.Switch;

import mohan.exp.javamultitheme.refer.R;

public class SettingsThemeActivity extends BaseConfigActivity {

    public String mCurrentTheme="";
    public Switch switchMintTheme;
    SharedPreferences mSpref;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        switchMintTheme =(Switch)findViewById(R.id.mintTheme);
        mSpref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        mCurrentTheme =mSpref.getString(KEY_CURRENT_THEME,LILAC_THEME);
        if(mCurrentTheme.equals(LILAC_THEME)){
            switchMintTheme.setChecked(true);
        }else{
            switchMintTheme.setChecked(false);
        }

        switchMintTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mSpref.edit().putString(KEY_CURRENT_THEME, MINT_THEME).apply();
                }else{
                    mSpref.edit().putString(KEY_CURRENT_THEME, LILAC_THEME).apply();
                }

            }
        });

    }
}
