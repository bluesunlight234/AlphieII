package com.example.kenneth.alphieii;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Kenneth on 12/19/2017.
 */

public class AlphieHelp extends AppCompatActivity {

    int photosetting = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alphie_help);

    }


    void changePhoto(View view) {
        ImageView photo = (ImageView) findViewById(R.id.photo);

        switch (photosetting) {
            case 1:
                photo.setImageResource(R.drawable.card_2);
                photosetting++;
                break;
            case 2:
                photo.setImageResource(R.drawable.card_3);
                photosetting++;
                break;
            case 3:
                photo.setImageResource(R.drawable.card_4);
                photosetting++;
                break;
            case 4:
                photo.setImageResource(R.drawable.card_5);
                photosetting++;
                break;
            case 5:
                photo.setImageResource(R.drawable.card_6);
                photosetting++;
                break;
            case 6:
                photo.setImageResource(R.drawable.card_7);
                photosetting++;
                break;
            case 7:
                photo.setImageResource(R.drawable.card_8);
                photosetting++;
                break;
            case 8:
                photo.setImageResource(R.drawable.card_9);
                photosetting++;
                break;
            case 9:
                photo.setImageResource(R.drawable.card_10);
                photosetting++;
                break;
            case 10:
                photo.setImageResource(R.drawable.card_11);
                photosetting++;
                break;
            case 11:
                photo.setImageResource(R.drawable.card_12);
                photosetting++;
                break;
            case 12:
                photo.setImageResource(R.drawable.card_13);
                photosetting++;
                break;
            case 13:
                photo.setImageResource(R.drawable.card_14);
                photosetting++;
                break;
            case 14:
                photo.setImageResource(R.drawable.card_15);
                photosetting++;
                break;
            case 15:
                photo.setImageResource(R.drawable.card_1);
                photosetting = 1;
                break;

        }
    }

    void goback(View view) {
        Intent intent = new Intent(this, AlphieII.class);

        startActivity(intent);

        finish();
    }




}
