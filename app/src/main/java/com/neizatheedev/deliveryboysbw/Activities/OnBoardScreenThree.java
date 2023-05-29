package com.neizatheedev.deliveryboysbw.Activities;

/**
 * @author Monei Bakang Motshegwe
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.neizatheedev.deliveryboysbw.Activities.RegisterUserActivity;
import com.neizatheedev.deliveryboysbw.R;

public class OnBoardScreenThree extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board_screen_three);
        button = (Button) findViewById(R.id.nextbutton03);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToOnBoardScreenActivity();
            }
        });
    }

    public void goToOnBoardScreenActivity() {
        Intent intent = new Intent(this, RegisterUserActivity.class);
        startActivity(intent);
    }


}