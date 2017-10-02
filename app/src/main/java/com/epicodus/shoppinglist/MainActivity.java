package com.epicodus.shoppinglist;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @Bind(R.id.findItemsButton) Button mFindItemsButton;
    @Bind(R.id.itemEditText) EditText mItemEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface dancingScriptRegular = Typeface.createFromAsset(getAssets(),"fonts/dancingscriptregular.otf");
        mAppNameTextView.setTypeface(dancingScriptRegular);

        mFindItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = mItemEditText.getText().toString();
                Intent intent = new Intent (MainActivity.this, ItemsActivity.class);
                intent.putExtra("item", item);
                startActivity(intent);
            }
        });
    }
}