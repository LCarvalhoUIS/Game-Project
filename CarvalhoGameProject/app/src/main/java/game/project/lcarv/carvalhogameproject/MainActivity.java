package game.project.lcarv.carvalhogameproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder alert;

    ArrayList<ImageView> chars ;

    SoundPool soundPool;
    int mySound = -1;
    Integer counter=0;

    Animation animator;

    Timer timer=new Timer();
    int speed=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chars=new ArrayList<ImageView>();
        GetCharList();
        animator=new Animation(chars);


        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
        AssetManager assetManager=getAssets();

        MediaPlayer play= MediaPlayer.create(this, R.raw.music);
        play.setLooping(true);
        play.start();


        introDisplay();

        final int FPS = 10;
        TimerTask updateGame = new UpdateGameTask();
        timer.scheduleAtFixedRate(updateGame, 0, 1000 / FPS);


        try{
            AssetFileDescriptor desc=assetManager.openFd("music.mp3");
            mySound=soundPool.load(desc,1);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }





    }

    //Get the list with all the characters
    public void GetCharList()
    {
        //Set the initial tags
      //  (ImageView)findViewById(R.id.generalA).setTag(R.id.generalA);


        chars.add((ImageView) findViewById(R.id.generalA));
        chars.add((ImageView) findViewById(R.id.generalB));
        chars.add((ImageView) findViewById(R.id.paladinA));
        chars.add((ImageView)findViewById(R.id.paladinB));
        chars.add((ImageView) findViewById(R.id.Sage));
    }

    int parityOfTap=1;

    public void onTap(View v)
    {
        ImageView sage=(ImageView)findViewById(R.id.Sage);
        if(parityOfTap%2==0)
        {
            sage.setImageResource(R.drawable.sage1);
        }
        else
        {
            sage.setImageResource(R.drawable.sage2);
        }
        parityOfTap++;

        TextView tv=(TextView)findViewById(R.id.ScoreTV);
        counter++;
        tv.setText(counter.toString());
        if(counter==15)
        {
            //You win
            Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            System.exit(0);
        }
    }

    public void introDisplay()
    {

        //Test dialog
        alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Welcome");
        alert.setMessage("The two paladins are charging in your direction. Tap 15 times to win. If they reach the generals you lose. If you win or you lose, the game restarts.");
        alert.setPositiveButton("Begin", null);
        alert.show();
    }

    public void onClick(View v)
    {
        ImageView palA=(ImageView)findViewById( R.id.paladinA);
        ImageView palB=(ImageView)findViewById( R.id.paladinB);
        palB.setX(palB.getX() + 10);
        palA.setX(palA.getX() + 10);
        TextView tv=(TextView)findViewById(R.id.ScoreTV);


        if(counter%2==0)
        {
            palA.setImageResource(R.drawable.pal1);
            palB.setImageResource(R.drawable.pal1);
        }
        else if(counter%3==0)
        {
            palA.setImageResource(R.drawable.pal2);
            palB.setImageResource(R.drawable.pal2);
        }
        else
        {
            palA.setImageResource(R.drawable.pal3);
            palB.setImageResource(R.drawable.pal3);
        }



    }

    class UpdateGameTask extends TimerTask {

        int frameCount=0;
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    ImageView genA=(ImageView)findViewById(R.id.generalA);
                    ImageView genB=(ImageView)findViewById(R.id.generalB);
                    ImageView palA=(ImageView)findViewById( R.id.paladinA);
                    ImageView palB=(ImageView)findViewById( R.id.paladinB);
                    palB.setX(palB.getX()+5);
                    palA.setX(palA.getX() + 5);
                    TextView tv=(TextView)findViewById(R.id.ScoreTV);
                  //  counter++;
                    tv.setText(counter.toString());

                    if(frameCount%3==0)
                    {
                        palA.setImageResource(R.drawable.pal1);
                        palB.setImageResource(R.drawable.pal1);
                    }
                    else if(frameCount%5==0)
                    {
                        palA.setImageResource(R.drawable.pal2);
                        palB.setImageResource(R.drawable.pal2);
                    }
                    else
                    {
                        palA.setImageResource(R.drawable.pal3);
                        palB.setImageResource(R.drawable.pal3);
                    }

                    if((genA.getX()-palA.getX()<15))
                    {

                        //Loss
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        System.exit(0);

                    }
frameCount++;






                }
            });

        }

    }

}




