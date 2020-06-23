package com.example.customlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends BaseAdapter {

    private List<ItemData> listItems;
    private LayoutInflater layoutInflater;

    private CompoundButton.OnCheckedChangeListener checkChange
            = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            listItems.get((Integer) buttonView.getTag()).setChecked(isChecked);
        }
    };

    ItemAdapter(Context context, List<ItemData> items) {
        if (items == null) {
            this.listItems = new ArrayList<>();
        } else {
            this.listItems = items;
        }
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    void addItem(ItemData item) {
        this.listItems.add(item);
        notifyDataSetChanged();
    }

    void removeItem(int position) {
        listItems.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_activity, parent, false);
        }

        ItemData itemData = listItems.get(position);

        ImageView image = view.findViewById(R.id.image);
        TextView title = view.findViewById(R.id.name);
        TextView subtitle = view.findViewById(R.id.text);
        CheckBox checkBox = view.findViewById(R.id.checked);

        image.setImageDrawable(itemData.getImage());
        title.setText(itemData.getTitle());
        subtitle.setText(itemData.getSubtitle());
        checkBox.setOnCheckedChangeListener(checkChange);
        checkBox.setTag(position);
        checkBox.setChecked(itemData.isChecked());

        return view;
    }
}
