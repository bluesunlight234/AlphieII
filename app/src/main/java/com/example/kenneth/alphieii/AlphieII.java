package com.example.kenneth.alphieii;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.MediaPlayer;
import android.widget.VideoView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.Thread.sleep;

public class AlphieII extends AppCompatActivity {

    public int photosetting = 1;
    boolean buttonsdisabled = false;
    boolean AlphieOn = false;
    boolean[] RedButs = new boolean[5];
    boolean[] YellowButs = new boolean[5];
    boolean RedbuttonSet;
    boolean OneYellowbuttonSet;
    boolean TwoYellowbuttonsSet;
    int setting = 4;
    int randomBut;
    VideoView mVideoView;
    ImageButton push;
    boolean threadOn = false;
    int vidstatus = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alphie_ii);
        mVideoView = (VideoView)findViewById(R.id.videoView);
        //mVideoView.setVideoPath("android.resource://".concat(getPackageName()).concat("/raw/")+ R.raw.alphie_vidstart1);
        //mVideoView.start();
        Thread thread = new Thread (playvideo);
        thread.start();
        threadOn = true;
    }

    void Help(View view){

        Intent intent = new Intent(this, AlphieHelp.class);

        startActivity(intent);

        //Intent intent2 = getIntent();
        finish();
        //startActivity(intent2);

    }


    public Handler vidstart = new Handler() {
        public void handleMessage (android.os.Message message){
            mVideoView.setVideoPath("android.resource://".concat(getPackageName()).concat("/raw/")+ R.raw.alphie_vidstart1);
            mVideoView.start();

        }
    };

    public Handler vidprompt = new Handler() {
        public void handleMessage (android.os.Message message){
            mVideoView.setVideoPath("android.resource://".concat(getPackageName()).concat("/raw/")+ R.raw.alphie_vidprompt2);

        }
    };

    public Handler vidcorrect = new Handler() {
        public void handleMessage (android.os.Message message){
            mVideoView.setVideoPath("android.resource://".concat(getPackageName()).concat("/raw/")+ R.raw.alphie_vidcorrect2);

        }
    };

    public Handler vidplay = new Handler() {
        public void handleMessage (android.os.Message message){
            mVideoView.start();

        }
    };

    public Runnable playvideo = new Runnable () {

        public void run() {
            try {
                while (true) {
                    //Thread.sleep (100);

                switch (vidstatus) {
                    case 0:
                        vidstatus = 4;
                        break;
                    case 1:
                        mVideoView.start();
                        Thread.sleep(4200);
                        vidcorrect.sendEmptyMessage(0);
                        vidstatus = 4;
                        break;
                    case 2:
                        vidcorrect.sendEmptyMessage(0);
                        vidstatus = 4;
                        break;
                    case 3:
                        mVideoView.start();
                        vidstatus++;
                        break;
                    case 4:
                        break;
                    case 5:
                        vidstart.sendEmptyMessage(0);
                        Thread.sleep(1000);
                        vidprompt.sendEmptyMessage(0);
                        vidstatus = 4;
                        break;
                }
                }

            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    };


    void changePhoto(View view) {
        ImageView photo = (ImageView) findViewById(R.id.Robot);

        switch (photosetting) {
            case 1:
                photo.setImageResource(R.drawable.alphie_robot2);
                photosetting++;
                break;
            case 2:
                photo.setImageResource(R.drawable.alphie_robot3);
                photosetting++;
                break;
            case 3:
                photo.setImageResource(R.drawable.alphie_robot4);
                photosetting++;
                break;
            case 4:
                photo.setImageResource(R.drawable.alphie_robot5);
                photosetting++;
                break;
            case 5:
                photo.setImageResource(R.drawable.alphie_robot6);
                photosetting++;
                break;
            case 6:
                photo.setImageResource(R.drawable.alphie_robot7);
                photosetting++;
                break;
            case 7:
                photo.setImageResource(R.drawable.alphie_robot8);
                photosetting++;
                break;
            case 8:
                photo.setImageResource(R.drawable.alphie_robot9);
                photosetting++;
                break;
            case 9:
                photo.setImageResource(R.drawable.alphie_robot10);
                photosetting++;
                break;
            case 10:
                photo.setImageResource(R.drawable.alphie_robot11);
                photosetting++;
                break;
            case 11:
                photo.setImageResource(R.drawable.alphie_robot12);
                photosetting++;
                break;
            case 12:
                photo.setImageResource(R.drawable.alphie_robot13);
                photosetting++;
                break;
            case 13:
                photo.setImageResource(R.drawable.alphie_robot14);
                photosetting++;
                break;
            case 14:
                photo.setImageResource(R.drawable.alphie_robot15);
                photosetting++;
                break;
            case 15:
                photo.setImageResource(R.drawable.alphie_robot1);
                photosetting = 1;
                break;

        }
    }




    void ButtonOn(View view) {


        if (!AlphieOn && !buttonsdisabled) {


            AlphieOn = true;
            AlphieReset();
            //play Alphie On video/sound



            AlphieOn();

        }
    }

    void AlphieReset() {
        for (int i = 0; i <= 4; i++) {
            RedButs[i] = false;
            YellowButs[i] = false;
        }
        RedbuttonSet = false;
        OneYellowbuttonSet = false;
        TwoYellowbuttonsSet = false;
    }

    void AlphieOn() {
        MediaPlayer ring= MediaPlayer.create(AlphieII.this,R.raw.alphie_prompt);
        vidstatus = 1;


        try {
            buttonsdisabled = true;
            ring.start();
            sleep(1500);

            buttonsdisabled = false;

        }catch(InterruptedException e){

        }
    }

    void AlphiePrompt() {
        MediaPlayer ring= MediaPlayer.create(AlphieII.this,R.raw.alphie_prompt);
        try {
            buttonsdisabled = true;
            ring.start();
        sleep(1200);
            buttonsdisabled = false;

    }catch(InterruptedException e){

    }
    }


    void AlphieCorrect() {

        MediaPlayer ring= MediaPlayer.create(AlphieII.this,R.raw.alphie_prompt);
        MediaPlayer ring1= MediaPlayer.create(AlphieII.this,R.raw.alphie_correct1);
        try {
            buttonsdisabled = true;
            ring.start();
            sleep(1700);
            vidstatus = 3;
            ring1.start();
            sleep(5200);
            buttonsdisabled = false;

        }catch(InterruptedException e){

        }
    }

    void AlphieIncorrect() {
        MediaPlayer ring= MediaPlayer.create(AlphieII.this,R.raw.alphie_prompt);
        MediaPlayer ring1= MediaPlayer.create(AlphieII.this,R.raw.alphie_incorrect1);
        try {
            buttonsdisabled = true;
            ring.start();
            sleep(1700);
            ring1.start();
            sleep(3200);
            buttonsdisabled = false;

        }catch(InterruptedException e){

        }
    }


    void RedBut0(View view) {
        if (AlphieOn  && !buttonsdisabled) {
            switch (setting) {
                case 0: RedBut0Sqaure();
                    break;
                case 1: RedBut0Star();
                    break;
                case 2: RedBut0Triangle();
                    break;
                case 3: RedBut0Question();
                    break;
                case 4: RedBut0Music();
            }
        }

    }

    void RedBut1(View view) {
        if (AlphieOn  && !buttonsdisabled) {
            switch (setting) {
                case 0: RedBut1Sqaure();
                    break;
                case 1: RedBut1Star();
                    break;
                case 2: RedBut1Triangle();
                    break;
                case 3: RedBut1Question();
                    break;
                case 4: RedBut1Music();
            }
        }
    }

    void RedBut2(View view) {
        if (AlphieOn  && !buttonsdisabled) {
            switch (setting) {
                case 0: RedBut2Sqaure();
                    break;
                case 1: RedBut2Star();
                    break;
                case 2: RedBut2Triangle();
                    break;
                case 3: RedBut2Question();
                    break;
                case 4: RedBut2Music();
            }
        }
    }

    void RedBut3(View view) {
        if (AlphieOn  && !buttonsdisabled) {
            switch (setting) {
                case 0: RedBut3Sqaure();
                    break;
                case 1: RedBut3Star();
                    break;
                case 2: RedBut3Triangle();
                    break;
                case 3: RedBut3Question();
                    break;
                case 4: RedBut3Music();
            }
        }
    }

    void RedBut4(View view) {
        if (AlphieOn  && !buttonsdisabled) {
            switch (setting) {
                case 0: RedBut4Sqaure();
                    break;
                case 1: RedBut4Star();
                    break;
                case 2: RedBut4Triangle();
                    break;
                case 3: RedBut4Question();
                    break;
                case 4: RedBut4Music();
            }
        }
    }

    void YellowBut0(View view) {
        if (AlphieOn  && !buttonsdisabled) {
            switch (setting) {
                case 0: YellowBut0Sqaure();
                    break;
                case 1: YellowBut0Star();
                    break;
                case 2: YellowBut0Triangle();
                    break;
                case 3: YellowBut0Question();
                    break;
                case 4: YellowBut0Music();
            }
        }
    }

    void YellowBut1(View view) {
        if (AlphieOn  && !buttonsdisabled) {
            switch (setting) {
                case 0: YellowBut1Sqaure();
                    break;
                case 1: YellowBut1Star();
                    break;
                case 2: YellowBut1Triangle();
                    break;
                case 3: YellowBut1Question();
                    break;
                case 4: YellowBut1Music();
            }
        }
    }

    void YellowBut2(View view) {
        if (AlphieOn  && !buttonsdisabled) {
            switch (setting) {
                case 0: YellowBut2Sqaure();
                    break;
                case 1: YellowBut2Star();
                    break;
                case 2: YellowBut2Triangle();
                    break;
                case 3: YellowBut2Question();
                    break;
                case 4: YellowBut2Music();
            }
        }
    }

    void YellowBut3(View view) {
        if (AlphieOn  && !buttonsdisabled) {
            switch (setting) {
                case 0: YellowBut3Sqaure();
                    break;
                case 1: YellowBut3Star();
                    break;
                case 2: YellowBut3Triangle();
                    break;
                case 3: YellowBut3Question();
                    break;
                case 4: YellowBut3Music();
            }
        }
    }

    void YellowBut4(View view) {
        if (AlphieOn  && !buttonsdisabled) {
            switch (setting) {
                case 0: YellowBut4Sqaure();
                    break;
                case 1: YellowBut4Star();
                    break;
                case 2: YellowBut4Triangle();
                    break;
                case 3: YellowBut4Question();
                    break;
                case 4: YellowBut4Music();
            }
        }
    }



    void GreenSquareBut(View view) {
        if (AlphieOn  && !buttonsdisabled) {
            setting = 0;
            AlphieReset();
            // play alphie prompt sound
            AlphiePrompt();
        }
    }

    void GreenStarBut(View view) {
        if (AlphieOn  && !buttonsdisabled) {
            setting = 1;
            AlphieReset();
            // play alphie prompt sound
            AlphiePrompt();
        }
    }

    void GreenTriangleBut(View view) {
        if (AlphieOn  && !buttonsdisabled) {
            setting = 2;
            AlphieReset();
            // play alphie prompt sound
            AlphiePrompt();
        }
    }

    void GreenQuestionBut(View view) {
        if (AlphieOn  && !buttonsdisabled) {
            setting = 3;
            AlphieReset();
            randomBut = 0 + (int)(Math.random() * 9);
            // play alphie prompt sound
            AlphiePrompt();
        }
    }

    void GreenMusicBut(View view) {
        if (AlphieOn  && !buttonsdisabled) {
            setting = 4;
            AlphieReset();
            // play alphie prompt sound
            AlphiePrompt();
        }
    }



    void RedBut0Sqaure() {
        if (RedbuttonSet) {
            AlphieReset();
        }

        RedButs[0] = true;
        RedbuttonSet = true;
        //Alphie sound prompt
        AlphiePrompt();
    }

    void RedBut0Star() {
        if (!RedButs[0]) {
            RedButs[0] = true;
            //Alphie sound prompt
            AlphiePrompt();
        }
        else if (RedbuttonSet) {
            AlphieReset();
            RedButs[0] = true;
            //Alphie sound prompt
            AlphiePrompt();
        }
    }

    void RedBut0Triangle() {
        if (RedbuttonSet) {
            AlphieReset();
        }
        RedButs[0] = true;
        RedbuttonSet = true;
        //Alphie sound prompt
        AlphiePrompt();

    }

    void RedBut0Question() {
        if (randomBut == 0) {
            //Alphie sound prompt
            //Alphie Correct
            AlphieCorrect();
        }
        else {
            //Alphie sound prompt
            //Alphie Incorrect
            AlphieIncorrect();
        }
    }

    void RedBut0Music() {
        //Alphie London Bridge Tune Random Finish


        MediaPlayer ring= MediaPlayer.create(AlphieII.this,R.raw.london_bridge);
        int randomtime = 8000 + (int)(Math.random() * 8000);

        try {
            buttonsdisabled = true;
            ring.start();
            sleep(randomtime);
            ring.stop();

            buttonsdisabled = false;

        }catch(InterruptedException e){

        }

    }


    void RedBut1Sqaure() {
        if (RedbuttonSet) {
            AlphieReset();
        }

        RedButs[1] = true;
        RedbuttonSet = true;
        //Alphie sound prompt
        AlphiePrompt();
    }

    void RedBut1Star() {
        if (RedbuttonSet || !RedButs[0]) {
            AlphieReset();
            //Alphie Incorrect
            AlphieIncorrect();
        }
        else {
            RedButs[1] = true;
            RedbuttonSet = true;
            //Alphie sound prompt
            AlphiePrompt();
        }
    }

    void RedBut1Triangle() {
        if (RedbuttonSet) {
            AlphieReset();
        }

        RedButs[1] = true;
        RedbuttonSet = true;
        //Alphie sound prompt
        AlphiePrompt();

    }

    void RedBut1Question() {
        if (randomBut == 1) {
            //Alphie sound prompt
            //Alphie Correct
            TextView msgTextView = (TextView) findViewById(R.id.mytexttester);
            AlphieCorrect();
        }
        else {
            //Alphie sound prompt
            //Alphie Incorrect
            AlphieIncorrect();
        }
    }

    void RedBut1Music() {
        //Alphie Clair de Lune Tune Random Finish


        MediaPlayer ring= MediaPlayer.create(AlphieII.this,R.raw.clair_de_lune);
        int randomtime = 4000 + (int)(Math.random() * 4000);

        try {
            buttonsdisabled = true;
            ring.start();
            sleep(randomtime);
            ring.stop();

            buttonsdisabled = false;

        }catch(InterruptedException e){

        }

    }

    void RedBut2Sqaure() {
        if (RedbuttonSet) {
            AlphieReset();
        }

        RedButs[2] = true;
        RedbuttonSet = true;
        //Alphie sound prompt
        AlphiePrompt();

    }

    void RedBut2Star() {
        if (RedbuttonSet || !RedButs[0]) {
            AlphieReset();
            //Alphie Incorrect
            AlphieIncorrect();
        }
        else {
            RedButs[2] = true;
            RedbuttonSet = true;
            //Alphie sound prompt
            AlphiePrompt();
        }
    }

    void RedBut2Triangle() {
        if (RedbuttonSet) {
            AlphieReset();
        }

        RedButs[2] = true;
        RedbuttonSet = true;
        //Alphie sound prompt
        AlphiePrompt();

    }

    void RedBut2Question() {
        if (randomBut == 2) {
            //Alphie sound prompt
            //Alphie Correct
            AlphieCorrect();
        }
        else {
            //Alphie sound prompt
            //Alphie Incorrect
            AlphieIncorrect();
        }
    }

    void RedBut2Music() {
        //Alphie Old McDonald Random Finish

        MediaPlayer ring= MediaPlayer.create(AlphieII.this,R.raw.old_mcdonald);
        int randomtime = 4000 + (int)(Math.random() * 4000);

        try {
            buttonsdisabled = true;
            ring.start();
            sleep(randomtime);
            ring.stop();

            buttonsdisabled = false;

        }catch(InterruptedException e){

        }

    }

    void RedBut3Sqaure() {
        if (RedbuttonSet) {
            AlphieReset();
        }

        RedButs[3] = true;
        RedbuttonSet = true;
        //Alphie sound prompt
        AlphiePrompt();
    }

    void RedBut3Star() {
        if (RedbuttonSet || !RedButs[0]) {
            AlphieReset();
            //Alphie Incorrect
            AlphieIncorrect();
        }
        else {
            RedButs[3] = true;
            RedbuttonSet = true;
            //Alphie sound prompt
            AlphiePrompt();
        }
    }

    void RedBut3Triangle() {
        if (RedbuttonSet) {
            AlphieReset();
        }

        RedButs[3] = true;
        RedbuttonSet = true;
        //Alphie sound prompt
        AlphiePrompt();

    }

    void RedBut3Question() {
        if (randomBut == 3) {
            //Alphie sound prompt
            //Alphie Correct
            AlphieCorrect();

        }
        else {
            //Alphie sound prompt
            //Alphie Incorrect
            AlphieIncorrect();
        }
    }

    void RedBut3Music() {
        //Alphie Oh Susannah Random Finish

        MediaPlayer ring= MediaPlayer.create(AlphieII.this,R.raw.oh_susannah);
        int randomtime = 4000 + (int)(Math.random() * 4000);

        try {
            buttonsdisabled = true;
            ring.start();
            sleep(randomtime);
            ring.stop();

            buttonsdisabled = false;

        }catch(InterruptedException e){

        }
    }

    void RedBut4Sqaure() {
        if (RedbuttonSet) {
            AlphieReset();
        }

        RedButs[4] = true;
        RedbuttonSet = true;
        //Alphie sound prompt
        AlphiePrompt();
    }

    void RedBut4Star() {
        if (RedbuttonSet || !RedButs[0]) {
            AlphieReset();
            //Alphie Incorrect
            AlphieIncorrect();
        }
        else {
            RedButs[4] = true;
            RedbuttonSet = true;
            //Alphie sound prompt
            AlphiePrompt();
        }
    }

    void RedBut4Triangle() {
        if (RedbuttonSet) {
            AlphieReset();
        }
        RedButs[4] = true;
        RedbuttonSet = true;
        //Alphie sound prompt
        AlphiePrompt();

    }

    void RedBut4Question() {
        if (randomBut == 4) {
            //Alphie sound prompt
            //Alphie Correct
            AlphieCorrect();
        }
        else {
            //Alphie sound prompt
            //Alphie Incorrect
            AlphieIncorrect();
        }
    }

    void RedBut4Music() {
        //Alphie Twinkle, Twinkle Random Finish

        MediaPlayer ring= MediaPlayer.create(AlphieII.this,R.raw.twinkle_twinkle);
        int randomtime = 4000 + (int)(Math.random() * 4000);

        try {
            buttonsdisabled = true;
            ring.start();
            sleep(randomtime);
            ring.stop();

            buttonsdisabled = false;

        }catch(InterruptedException e){

        }

    }

    void YellowBut0Sqaure() {
        if (RedButs[3]) {
            AlphieReset();
            //Play correct video
            AlphieCorrect();
        }
        else {
            AlphieReset();
            //Play incorrect video
            AlphieIncorrect();
        }
    }

    void YellowBut0Star() {
        AlphieReset();
        //Alphie Incorrect
        AlphieIncorrect();
    }

    void YellowBut0Triangle() {
        if ((RedButs[1] && YellowButs[4] && YellowButs[3]) ||
                (RedButs[2] && YellowButs[1] && YellowButs[3])) {
            AlphieReset();
            //Alphie Correct
            AlphieCorrect();
        }
        else if (RedButs[3] && !OneYellowbuttonSet) {
            YellowButs[0] = true;
            OneYellowbuttonSet = true;
            //Alphie Sound Prompt
            AlphiePrompt();
        }
        else {
            AlphieReset();
            //Alphie Incorrect
            AlphieIncorrect();
        }
    }

    void YellowBut0Question() {
        if (randomBut == 5) {
            //Alphie sound prompt
            //Alphie Correct
            AlphieCorrect();
        }
        else {
            //Alphie sound prompt
            //Alphie Incorrect
            AlphieIncorrect();
        }
    }

    void YellowBut0Music() {
        //Alphie London Bridge

        MediaPlayer ring= MediaPlayer.create(AlphieII.this,R.raw.london_bridge);


        try {
            buttonsdisabled = true;
            ring.start();
            sleep(16500);

            buttonsdisabled = false;

        }catch(InterruptedException e){

        }



    }

    void YellowBut1Sqaure() {
        if (RedButs[0]) {
            AlphieReset();
            //Play correct video
            //AlphieVidCorrect();
            AlphieCorrect();
        }
        else {
            AlphieReset();
            //Play incorrect video
            AlphieIncorrect();
        }
    }

    void YellowBut1Star() {
        if (RedButs[0] && RedButs[4]) {
            AlphieReset();
            //Play correct video
            AlphieCorrect();
        }
        else {
            AlphieReset();
            //Play incorrect video
            AlphieIncorrect();
        }
    }

    void YellowBut1Triangle() {
        if ((RedButs[0] || RedButs[2]) && !OneYellowbuttonSet) {
            YellowButs[1] = true;
            OneYellowbuttonSet = true;
            //Alphie Sound Prompt
            AlphiePrompt();
        }
        else {
            AlphieReset();
            //Alphie Incorrect
            AlphieIncorrect();
        }
    }

    void YellowBut1Question() {
        if (randomBut == 6) {
            //Alphie sound prompt
            //Alphie Correct
            AlphieCorrect();
        }
        else {
            //Alphie sound prompt
            //Alphie Incorrect
            AlphieIncorrect();
        }
    }

    void YellowBut1Music() {
        //Alphie Clair de Lune

        MediaPlayer ring= MediaPlayer.create(AlphieII.this,R.raw.clair_de_lune);

        try {
            buttonsdisabled = true;
            ring.start();
            sleep(8500);

            buttonsdisabled = false;

        }catch(InterruptedException e){

        }
    }

    void YellowBut2Sqaure() {
        if (RedButs[4]) {
            AlphieReset();
            //Play correct video
            AlphieCorrect();
        }
        else {
            AlphieReset();
            //Play incorrect video
            AlphieIncorrect();
        }
    }

    void YellowBut2Star() {
        if (RedButs[0] && RedButs[3]) {
            AlphieReset();
            //Play correct video
            AlphieCorrect();
        }
        else {
            AlphieReset();
            //Play incorrect video
            AlphieIncorrect();
        }
    }

    void YellowBut2Triangle() {
        if (RedButs[4] && !OneYellowbuttonSet) {
            YellowButs[2] = true;
            OneYellowbuttonSet = true;
            //Alphie Sound Prompt
            AlphiePrompt();
        }
        else {
            AlphieReset();
            //Alphie Incorrect
            AlphieIncorrect();
        }
    }

    void YellowBut2Question() {
        if (randomBut == 7) {
            //Alphie sound prompt
            //Alphie Correct
            AlphieCorrect();
        }
        else {
            //Alphie sound prompt
            //Alphie Incorrect
            AlphieIncorrect();
        }
    }

    void YellowBut2Music() {
        //Alphie Old McDonald

        MediaPlayer ring= MediaPlayer.create(AlphieII.this,R.raw.old_mcdonald);

        try {
            buttonsdisabled = true;
            ring.start();
            sleep(8500);

            buttonsdisabled = false;

        }catch(InterruptedException e){

        }
    }

    void YellowBut3Sqaure() {
        if (RedButs[2]) {
            AlphieReset();
            //Play correct video
            AlphieCorrect();
        }
        else {
            AlphieReset();
            //Play incorrect video
            AlphieIncorrect();
        }
    }

    void YellowBut3Star() {
        if (RedButs[0] && RedButs[1]) {
            AlphieReset();
            //Play correct video
            AlphieCorrect();
        }
        else {
            AlphieReset();
            //Play incorrect video
            AlphieIncorrect();
        }
    }

    void YellowBut3Triangle() {
        if (OneYellowbuttonSet && !TwoYellowbuttonsSet) {
            YellowButs[3] = true;
            TwoYellowbuttonsSet = true;
            AlphiePrompt();
        }
        else {
            AlphieReset();
            //Play incorrect video
            AlphieIncorrect();
        }
    }

    void YellowBut3Question() {
        if (randomBut == 8) {
            //Alphie sound prompt
            //Alphie Correct
            AlphieCorrect();
        }
        else {
            //Alphie sound prompt
            //Alphie Incorrect
            AlphieIncorrect();
        }
    }

    void YellowBut3Music() {
        //Alphie Oh Susannah

        MediaPlayer ring= MediaPlayer.create(AlphieII.this,R.raw.oh_susannah);

        try {
            buttonsdisabled = true;
            ring.start();
            sleep(8500);

            buttonsdisabled = false;

        }catch(InterruptedException e){

        }

    }

    void YellowBut4Sqaure() {
        if (RedButs[1]) {
            AlphieReset();
            //Play correct video
            AlphieCorrect();
        }
        else {
            AlphieReset();
            //Play incorrect video
            AlphieIncorrect();
        }
    }

    void YellowBut4Star() {
        if (RedButs[0] && RedButs[2]) {
            AlphieReset();
            //Play correct video
            AlphieCorrect();
        }
        else {
            AlphieReset();
            //Play incorrect video
            AlphieIncorrect();
        }
    }

    void YellowBut4Triangle() {
        if ((RedButs[0] && YellowButs[1] && YellowButs[3]) ||
                (RedButs[3] && YellowButs[0] && YellowButs[3]) ||
                (RedButs[4] && YellowButs[2] && YellowButs[3])) {
            AlphieReset();
            //Alphie Correct
            AlphieCorrect();
        }
        else if (RedButs[1] && !OneYellowbuttonSet) {
            YellowButs[4] = true;
            OneYellowbuttonSet = true;
            //Alphie Sound Prompt
            AlphiePrompt();
        }
        else {
            AlphieReset();
            //Alphie Incorrect
            AlphieIncorrect();
        }
    }

    void YellowBut4Question() {
        if (randomBut == 9) {
            //Alphie sound prompt
            //Alphie Correct
            AlphieCorrect();
        }
        else {
            //Alphie sound prompt
            //Alphie Incorrect
            AlphieIncorrect();
        }
    }

    void YellowBut4Music() {
        //Alphie Twinkle, Twinkle
        MediaPlayer ring= MediaPlayer.create(AlphieII.this,R.raw.twinkle_twinkle);

        try {
            buttonsdisabled = true;
            ring.start();
            sleep(8500);

            buttonsdisabled = false;

        }catch(InterruptedException e){

        }

    }


}
