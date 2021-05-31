package ru.mirea.velikanov.mireaproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressLint("UseSwitchCompatOrMaterialCode")
public class SettingsFragment extends Fragment {
    Switch switchToDark;
    Switch switchNotific;
    private SharedPreferences preferences;
    final String DARK_THEME = "darkTheme";
    final String NOTIFIC = "notification";
    AudioManager audioManager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        preferences = getActivity().getPreferences(MODE_PRIVATE);

        switchToDark = view.findViewById(R.id.switchToDark);
        switchNotific = view.findViewById(R.id.swicthToasts);
        if(preferences.getString(NOTIFIC, "Default").equals("On")) {
            switchNotific.setChecked(true);
        } else if (preferences.getString(NOTIFIC, "Default").equals("Off")) {
            switchNotific.setChecked(false);
        }
        if(preferences.getString(DARK_THEME, "Default").equals("On")) {
            switchToDark.setChecked(true);
        } else if (preferences.getString(DARK_THEME, "Default").equals("Off")) {
            switchToDark.setChecked(false);
        }
        if (switchToDark != null) {
            switchToDark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    SharedPreferences.Editor editor = preferences.edit();
                    if (isChecked) {
                        editor.putString(DARK_THEME, "On");
                    } else {
                        editor.putString(DARK_THEME, "Off");
                    }
                    editor.apply();
                }
            });
        }
        if (switchNotific != null) {
            switchNotific.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    SharedPreferences.Editor editor = preferences.edit();
                    if (isChecked) {
                        editor.putString(NOTIFIC, "On");
                        audioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
                        audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
                    } else {
                        audioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
                        audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
                        editor.putString(NOTIFIC, "Off");
                    }
                    editor.apply();
                }
            });
        }

        return view;
    }
}