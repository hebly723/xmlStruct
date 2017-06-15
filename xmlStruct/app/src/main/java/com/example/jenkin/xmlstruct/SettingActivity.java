package com.example.jenkin.xmlstruct;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class SettingActivity extends PreferenceActivity implements
        Preference.OnPreferenceChangeListener {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getPreferenceManager().setSharedPreferencesName("setting");
        addPreferencesFromResource(R.xml.preference_setting);

        Preference individualNamePreference = findPreference("individual_name");
        SharedPreferences sharedPreferences= individualNamePreference.getSharedPreferences();
        individualNamePreference.setSummary(sharedPreferences.getString("individual_name", ""));

        Preference servicePortPreference = findPreference("service_port");
        SharedPreferences servicePortPreferences= servicePortPreference.getSharedPreferences();
        servicePortPreference.setSummary(servicePortPreferences.getString("service_port", ""));

        Preference serviceIpPreference = findPreference("service_ip");
        SharedPreferences serviceIpPreferences= serviceIpPreference.getSharedPreferences();
        serviceIpPreference.setSummary(serviceIpPreferences.getString("service_ip", ""));

        if (sharedPreferences.getBoolean("yesno_save_individual_info", false))
        {
            individualNamePreference.setEnabled(true);
            serviceIpPreference.setEnabled(true);
            servicePortPreference.setEnabled(true);
        }
        else
        {
            individualNamePreference.setEnabled(false);
            serviceIpPreference.setEnabled(false);
            servicePortPreference.setEnabled(false);
        }
        serviceIpPreference.setOnPreferenceChangeListener(this);
        servicePortPreference.setOnPreferenceChangeListener(this);
        individualNamePreference.setOnPreferenceChangeListener(this);


    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue)
    {
        preference.setSummary(String.valueOf(newValue));
        return true;
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
                                         Preference preference)
    {

        if ("yesno_save_individual_info".equals(preference.getKey()))
        {
            findPreference("individual_name").setEnabled(
                    !findPreference("individual_name").isEnabled());
            findPreference("service_port").setEnabled(
                    !findPreference("service_port").isEnabled());
            findPreference("service_ip").setEnabled(
                    !findPreference("service_ip").isEnabled());
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
}
