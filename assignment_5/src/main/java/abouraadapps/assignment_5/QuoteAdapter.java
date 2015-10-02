package abouraadapps.assignment_5;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by marioabouraad on 15-09-22.
 */
public class QuoteAdapter extends ArrayAdapter<String> {

    Context context;
    List<String> quotes;
    LayoutInflater layoutInflater;
    private SparseBooleanArray selectedItems;


    public QuoteAdapter(Context context, int id, List<String> quotes) {
        super(context, id, quotes);
        selectedItems = new SparseBooleanArray();
        this.quotes = quotes;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return quotes.size();
    }

    public List<String> getQuotes() {
        return quotes;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void viewSelection(int position) {
        select(position, !selectedItems.get(position));
    }

    public int getSelectionCount() {
        return selectedItems.size();
    }

    public SparseBooleanArray getSelectedItems() {
        return selectedItems;
    }

    private void select(int p, boolean b) {
        if (b)
            selectedItems.put(p, b);
        else
            selectedItems.delete(p);
        notifyDataSetChanged();
    }

    @Override
    public void remove(String object) {
        quotes.remove(object);
        notifyDataSetChanged();
    }

    public void removeSelection() {
        selectedItems = new SparseBooleanArray();
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.quote_item, parent, false);
        }

        TextView quoteTextView = (TextView) convertView.findViewById(R.id.quote);
        quoteTextView.setText(getQuotes().toString());
        quoteTextView.setText(quotes.get(position).toString());
        return convertView;
    }
}