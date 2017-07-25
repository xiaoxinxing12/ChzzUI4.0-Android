package org.chzz.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.chzz.demo.utils.ImageCache02;
import org.chzz.demo.utils.ImageLoader01;
import org.chzz.demo.utils.ImageLoader02;

public class MainActivity extends AppCompatActivity {
    ImageView imageview1;
    ImageLoader01 imageLoader01;
    ImageLoader02 imageLoader02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView= (ImageView) findViewById(R.id.iv_load);

         imageLoader01 =new ImageLoader01();
        //imageLoader02=new ImageLoader02();
      // imageLoader02.displayImage("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png",imageView);
         imageview1 = (ImageView) findViewById(R.id.iv_load1);

        Button load= (Button) findViewById(R.id.but_load);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageLoader01.displayImage("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png",imageview1);

            }
        });

    }
}
