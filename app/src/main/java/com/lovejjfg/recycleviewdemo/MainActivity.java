package com.lovejjfg.recycleviewdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;

import com.lovejjfg.recycleviewdemo.adapter.MyAdapter;

public class MainActivity extends AppCompatActivity {

    private static int actionBarSize;
    private Toolbar toolbar;
    private RecyclerView mRecyclerView;

    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CoordinatorLayout parent = (CoordinatorLayout) findViewById(R.id.parent);
        parent.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
//        parent.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
//            @Override
//            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
//                mRecyclerView.setPadding(mRecyclerView.getPaddingLeft(),
//                        insets.getSystemWindowInsetTop() + getActionBarSize
//                                (MainActivity.this),
//                        mRecyclerView.getPaddingRight() + insets.getSystemWindowInsetRight(), // landscape
//                        mRecyclerView.getPaddingBottom());
//                return insets.consumeSystemWindowInsets();
//            }
//        });
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                initHeight();
            }
        });
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(new MyAdapter());

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("scroll::::",dy + "");
            }
        });


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        initHeight();
    }

    @NonNull
    private void initHeight() {
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        final int statusBarHeight = frame.top;
        final int toolbarHeight = toolbar.getHeight();
        Log.e("statusBarHeight:", statusBarHeight + "");
        Log.e("toolbarHeight:", toolbarHeight + "");
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {

                mRecyclerView.setPadding(mRecyclerView.getPaddingLeft(), mRecyclerView.getPaddingTop() +toolbarHeight+500, mRecyclerView.getPaddingRight(), mRecyclerView.getPaddingBottom());
                mRecyclerView.scrollBy(0, -(toolbarHeight));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static int getActionBarSize(Context context) {
        if (actionBarSize < 0) {
            TypedValue value = new TypedValue();
            context.getTheme().resolveAttribute(android.R.attr.actionBarSize, value, true);
            actionBarSize = TypedValue.complexToDimensionPixelSize(value.data, context
                    .getResources().getDisplayMetrics());
        }
        return actionBarSize;
    }
}
