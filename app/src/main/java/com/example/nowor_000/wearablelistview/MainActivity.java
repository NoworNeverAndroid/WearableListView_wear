package com.example.nowor_000.wearablelistview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private static ArrayList<Integer> mIcons;
    private TextView mHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * lista de iconos
         */
        mIcons = new ArrayList<Integer>();
        mIcons.add(R.drawable.gps);
        mIcons.add(R.drawable.wifi);
        mIcons.add(R.drawable.datos);
        mIcons.add(android.R.drawable.ic_popup_reminder);


        // titulo de la lista
        mHeader = (TextView) findViewById(R.id.tvTitulo);

        WearableListView wearableListView = (WearableListView) findViewById(R.id.wearable_List);
        wearableListView.setAdapter(new WearableAdapter(this, mIcons));
        wearableListView.setClickListener(mClickListener);
        wearableListView.addOnScrollListener(mOnScrollListener);
    }

    // eventos items de la lista
    private WearableListView.ClickListener mClickListener =
            new WearableListView.ClickListener() {
                @Override
                public void onClick(WearableListView.ViewHolder viewHolder) {

                    Toast.makeText(MainActivity.this,
                            String.format("You selected item #%s",
                                    viewHolder.getLayoutPosition() + 1),
                            Toast.LENGTH_SHORT).show();


                    if(viewHolder.getLayoutPosition()==0){
                        startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                    }


                }

                @Override
                public void onTopEmptyRegionClick() {
                    Toast.makeText(MainActivity.this,
                            "Top empty area tapped", Toast.LENGTH_SHORT).show();
                }
            };

    // The following code ensures that the title scrolls as the user scrolls up
    // or down the list
    private WearableListView.OnScrollListener mOnScrollListener =
            new WearableListView.OnScrollListener() {
                @Override
                public void onAbsoluteScrollChange(int i) {
                    // Only scroll the title up from its original base position
                    // and not down.
                    if (i > 0) {
                        mHeader.setY(-i);
                    }
                }

                @Override
                public void onScroll(int i) {
                    // Placeholder
                }

                @Override
                public void onScrollStateChanged(int i) {
                    // Placeholder
                }

                @Override
                public void onCentralPositionChanged(int i) {
                    // Placeholder
                }
            };
}