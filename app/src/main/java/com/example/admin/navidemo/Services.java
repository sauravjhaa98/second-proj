package com.example.admin.navidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class Services extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        getSupportActionBar().setTitle("Our Services");
        MyListData[] myListData = new MyListData[] {
                new MyListData(" 1. 6 Sitting of Laser Hair Reduction Full Face @21000", android.R.drawable.btn_star_big_on),
                new MyListData(" 2. 6 Sitting of Laser Hair Reduction Under arm  @15000", android.R.drawable.star_big_on),
                new MyListData(" 3. 6 Sitting of Belly Reduction  @27000", android.R.drawable.btn_star_big_on),
                new MyListData(" 4. 6 Sitting of Laser Facial  @18000", android.R.drawable.star_big_on),
                new MyListData(" 5. 6 Sitting of Photo Facial  @15000", android.R.drawable.btn_star_big_on),
                new MyListData(" 6.  6 Sitting of PRP Therapy face/ Hair @24000 ", android.R.drawable.star_big_on),
                new MyListData(" 7. 6 Sitting of Laser Hair Reduction Full Face @21000", android.R.drawable.btn_star_big_on),
                new MyListData(" 8. 6 Sitting of Laser Hair Reduction Under arm  @15000", android.R.drawable.star_big_on),
                new MyListData(" 9. 6 Sitting of Belly Reduction  @27000", android.R.drawable.btn_star_big_on),
                new MyListData(" 10. 6 Sitting of Laser Facial  @18000", android.R.drawable.star_big_on),
                new MyListData(" 11. 6 Sitting of Photo Facial  @15000", android.R.drawable.btn_star_big_on),
                new MyListData(" 12.  6 Sitting of PRP Therapy face/ Hair @24000 ", android.R.drawable.star_big_on),
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyListAdapter adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
