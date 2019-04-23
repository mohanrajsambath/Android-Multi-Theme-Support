package mohan.exp.javamultitheme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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
public class BaseConfigActivity extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String KEY_CURRENT_THEME = "current_theme";
    public static final String LILAC_THEME = "lilac";
    public static final String MINT_THEME = "mint";

    public String mCurrentTheme="";
    SharedPreferences mSpref;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSpref = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        mCurrentTheme = mSpref.getString(KEY_CURRENT_THEME,LILAC_THEME);
        setAppTheme(mCurrentTheme);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String mSelectedTheme = mSpref.getString(KEY_CURRENT_THEME,LILAC_THEME);
        if(!mCurrentTheme.equals(mSelectedTheme)){
           finish();
        }
    }

    private void setAppTheme(String mCurrentTheme) {
        if(mCurrentTheme.equals(MINT_THEME)){
            setTheme(R.style.Theme_App_Mint);
        }else{
            setTheme(R.style.Theme_App_Lilac);
        }
    }
}
