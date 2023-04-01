package ru.startandroid.p0741_preferencescode;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;

public class PrefActivity extends PreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceScreen rootScreen = getPreferenceManager().createPreferenceScreen(this);
        setPreferenceScreen(rootScreen);

        CheckBoxPreference chb1 = new CheckBoxPreference(this);
        chb1.setKey("chb1");
        chb1.setTitle("checkBox 1");
        chb1.setSummaryOn("Description of checkBox 1 on");
        chb1.setSummaryOff("Description of checkBox 1 off");

        rootScreen.addPreference(chb1);

        ListPreference listPreference = new ListPreference(this);
        listPreference.setKey("list");
        listPreference.setTitle("List");
        listPreference.setSummary("Description of list");
        listPreference.setEntries(R.array.entries);
        listPreference.setEntryValues(R.array.entry_values);

        rootScreen.addPreference(listPreference);

        CheckBoxPreference chb2 = new CheckBoxPreference(this);
        chb2.setKey("chb2");
        chb2.setTitle("checkBox 2");
        chb2.setSummary("Description of checkBox 2");

        rootScreen.addPreference(chb2);

        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(this);
        screen.setKey("screen");
        screen.setTitle("Screen");
        screen.setSummary("Description of screen");

        final CheckBoxPreference chb3 = new CheckBoxPreference(this);
        chb3.setKey("chb3");
        chb3.setTitle("checkBox 3");
        chb3.setSummary("Description of checkBox 3");

        screen.addPreference(chb3);

        PreferenceCategory categ1 = new PreferenceCategory(this);
        categ1.setKey("categ1");
        categ1.setTitle("Category 1");
        categ1.setSummary("Description of category 1");

        screen.addPreference(categ1);

        CheckBoxPreference chb4 = new CheckBoxPreference(this);
        chb4.setKey("chb4");
        chb4.setTitle("checkBox 4");
        chb4.setSummary("Description of checkBox 4");

        categ1.addPreference(chb4);

        final PreferenceCategory categ2 = new PreferenceCategory(this);
        categ2.setKey("categ2");
        categ2.setTitle("Category 2");
        categ2.setSummary("Description of category 2");

        screen.addPreference(categ2);

        CheckBoxPreference chb5 = new CheckBoxPreference(this);
        chb5.setKey("chb5");
        chb5.setTitle("checkBox 5");
        chb5.setSummary("Description of checkBox 5");

        categ2.addPreference(chb5);

        CheckBoxPreference chb6 = new CheckBoxPreference(this);
        chb6.setKey("chb6");
        chb6.setTitle("checkBox 6");
        chb6.setSummary("Description of checkBox 6");

        categ2.addPreference(chb6);

        rootScreen.addPreference(screen);
        listPreference.setDependency("chb1");
        screen.setDependency("chb2");

        categ2.setEnabled(chb3.isChecked());
        chb3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                categ2.setEnabled(chb3.isChecked());
                return false;
            }
        });
    }
}
