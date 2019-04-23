package mohan.exp.javamultitheme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CompoundButton;
import android.widget.Switch;

import mohan.exp.javamultitheme.refer.R;

/*
 * Copyright (c) 2019. Created by Mohanraj.S,Innobot Systems on 23/4/19 for MultiThemeWorkspace
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
                   // finish();
                }else{
                    mSpref.edit().putString(KEY_CURRENT_THEME, LILAC_THEME).apply();
                    //finish();
                }

            }
        });

    }
}
