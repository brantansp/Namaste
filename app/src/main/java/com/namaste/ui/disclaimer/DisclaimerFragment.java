package com.namaste.ui.disclaimer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.namaste.R;

public class DisclaimerFragment extends Fragment {

    private DisclaimerViewModel disclaimerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        disclaimerViewModel =
                ViewModelProviders.of(this).get(DisclaimerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_disclaimer, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        disclaimerViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}