package com.wasche.www.wasche;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class OrderDetails extends AppCompatActivity {


    public void didTapButton(View view) {
        Button button = (Button)findViewById(R.id.angry_btn);

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.1 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 50);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);
        Intent intent = new Intent(OrderDetails.this,OrderActivity.class);
        startActivity(intent);
    }

    private List<ItemDetails> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemAdapter iAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        recyclerView = findViewById(R.id.recyclerView);

        iAdapter = new ItemAdapter(itemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(iAdapter);

        prepareItemData();
    }

    private void prepareItemData(){
        ItemDetails item = new ItemDetails("Shirt", "0", "20");
        itemList.add(item);

        item = new ItemDetails("Pants", "0", "Rs. 40");
        itemList.add(item);

        item = new ItemDetails("Shalwar Kameez", "0", "Rs. 30");
        itemList.add(item);

        item = new ItemDetails("Jacket", "0", "Rs. 50");
        itemList.add(item);

        item = new ItemDetails("Sweater", "0", "Rs. 50");
        itemList.add(item);

        item = new ItemDetails("Pants", "0", "Rs. 20");
        itemList.add(item);

        item = new ItemDetails("Pants", "0", "Rs. 20");
        itemList.add(item);

        item = new ItemDetails("Pants", "0", "20");
        itemList.add(item);

        item = new ItemDetails("Pants", "0", "20");
        itemList.add(item);

        item = new ItemDetails("Pants", "0", "20");
        itemList.add(item);

        item = new ItemDetails("Pants", "0", "20");
        itemList.add(item);

        item = new ItemDetails("Pants", "0", "20");
        itemList.add(item);

        item = new ItemDetails("Pants", "0", "20");
        itemList.add(item);
    }

}
