package com.epicodus.shoppinglist;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private Button mFindItemsButton;
    private EditText mItemEditText;
    private TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
        Typeface dancingScriptRegular = Typeface.createFromAsset(getAssets(),"fonts/dancingscriptregular.otf");
        mAppNameTextView.setTypeface(dancingScriptRegular);

        mItemEditText = (EditText) findViewById(R.id.itemEditText);
        mFindItemsButton = (Button) findViewById(R.id.findItemsButton);

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
