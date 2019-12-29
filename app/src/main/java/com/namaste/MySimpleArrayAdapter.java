package com.namaste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MySimpleArrayAdapter extends ArrayAdapter<String[]> {
    private final Context context;
    private final String[][] values;

    public MySimpleArrayAdapter(Context context, String[][] values) {
        super(context, R.layout.row, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);
        TextView textView1 = (TextView) rowView.findViewById(R.id.text);
        TextView textView2 = (TextView) rowView.findViewById(R.id.text2);
        textView1.setText(values[position][0]);
        textView2.setText(values[position][3]);

        return rowView;
    }
}
