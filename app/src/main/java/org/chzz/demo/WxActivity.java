package org.chzz.demo;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.ViewConfiguration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class WxActivity  extends AppCompatActivity {
   ViewPager viewPager;
    private FragmentPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wx);
       // setOverflowButtonAlways();
         Toolbar mToolbar= (Toolbar) findViewById(R.id.toolbar);
      List<String> tab=new ArrayList<>();
        tab.add("1");
        tab.add("1");
        tab.add("1");
        tab.add("1");
       adapter=new FragmentAdapter(getSupportFragmentManager(),tab);
        setSupportActionBar(mToolbar);
        viewPager= (ViewPager) findViewById(R.id.viewPage);
        viewPager.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return  super.onCreateOptionsMenu(menu);
    }
    private void setOverflowButtonAlways()
    {
        try
        {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKey = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            menuKey.setAccessible(true);
            menuKey.setBoolean(config, false);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
