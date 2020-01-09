package com.namaste.ui.developedby;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.namaste.R;

public class DevelopedbyFragment extends Fragment {

    private DevelopedbyViewModel developedByViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        developedByViewModel =
                ViewModelProviders.of(this).get(DevelopedbyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_developedby, container, false);
        final TextView textView = root.findViewById(R.id.text_developedby);
        developedByViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}