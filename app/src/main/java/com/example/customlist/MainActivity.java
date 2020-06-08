package com.example.customlist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random random = new Random();
    private ItemAdapter itemAdapter;
    private List<Drawable> images = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        ListView listView = findViewById(R.id.listViewMain);

        dropImages();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateRandomItemData();
            }
        });

        itemAdapter = new ItemAdapter(this, null);
        listView.setAdapter(itemAdapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void dropImages() {
        images.add(getDrawable(R.drawable.ic_favorite_border_black_24dp));
        images.add(getDrawable(R.drawable.ic_insert_emoticon_black_24dp));
        images.add(getDrawable(R.drawable.ic_star_border_black_24dp));
    }

    private void generateRandomItemData() {
        itemAdapter.addItem(new ItemData(
                images.get(random.nextInt(images.size())),
                "Hello" + itemAdapter.getCount(),
                "It\'s me",
                random.nextBoolean()));
    }
}
