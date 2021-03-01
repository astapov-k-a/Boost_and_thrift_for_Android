package com.simple.exec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.simple.exec.R;


public class MainActivity extends AppCompatActivity implements OnClickListener {

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }
 
  @Override
  public void onClick(View v) {
  }
}