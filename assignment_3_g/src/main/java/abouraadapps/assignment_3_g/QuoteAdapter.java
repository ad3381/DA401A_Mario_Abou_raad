package abouraadapps.assignment_3_g;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by marioabouraad on 15-09-22.
 */
public class QuoteAdapter extends BaseAdapter {

    List<String> quotes;
    LayoutInflater layoutInflater;


    public QuoteAdapter(List<String> quotes, LayoutInflater layoutInflater) {
        this.quotes = quotes;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return quotes.size();
    }

    @Override
    public Object getItem(int position) {
        return quotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.quote_item, parent, false);


        TextView quoteTextView = (TextView) convertView.findViewById(R.id.quote);
        quoteTextView.setText(getItem(position).toString());

        return convertView;
    }
}