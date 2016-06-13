package game.project.lcarv.carvalhogameproject;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by PC on 11/28/2015.
 */
public class Animation {

    public ArrayList<Integer> SageSprites;
    public ArrayList<Integer> GeneralSprites;
    public ArrayList<Integer> PaladinSprites;

    public ArrayList<ImageView> chars;

    public Animation(ArrayList<ImageView> chars)
    {
        this.chars=chars;

        SageSprites=new ArrayList<Integer>();
        GeneralSprites=new ArrayList<Integer>();
        PaladinSprites=new ArrayList<Integer>();

        SageSprites.add(R.drawable.sage0);
        SageSprites.add(R.drawable.sage1);
        SageSprites.add(R.drawable.sage2);


        GeneralSprites.add(R.drawable.gen0);
        GeneralSprites.add(R.drawable.gen1);
        GeneralSprites.add(R.drawable.gen2);
        GeneralSprites.add(R.drawable.gen3);

        PaladinSprites.add(R.drawable.pal0);
        PaladinSprites.add(R.drawable.pal1);
        PaladinSprites.add(R.drawable.pal2);
        PaladinSprites.add(R.drawable.pal3);
        PaladinSprites.add(R.drawable.pal4);





    }

    public void changeSprite(ImageView iv)
    {
        int tag=Integer.valueOf(iv.getTag().toString());

        if(tag==0)
        {
            switch((Integer)iv.getId())
            {
                case R.id.generalA:
                    iv.setTag(R.drawable.gen0);
                    break;
                case R.id.generalB:
                    iv.setTag(R.drawable.gen0);
                    break;
                case R.id.paladinA:
                    iv.setTag(R.drawable.pal0);
                    break;
                case R.id.paladinB:
                    iv.setTag(R.drawable.pal0);
                    break;
                case R.id.Sage:
                    iv.setTag(R.drawable.sage0);
                    break;
            }
        }

       //Check if it's a Sage, General, or Paladin
        switch(tag)
        {
            case R.drawable.sage0:
                iv.setImageResource(R.drawable.sage1);
                iv.setTag(R.drawable.sage1);
                break;
            case R.drawable.sage1:
                iv.setImageResource(R.drawable.sage2);
                iv.setTag(R.drawable.sage2);
                break;
            case R.drawable.sage2:
                iv.setImageResource(R.drawable.sage0);
                iv.setTag(R.drawable.sage0);
                break;


            case R.drawable.gen0:
                iv.setImageResource(R.drawable.gen1);
                iv.setTag(R.drawable.gen1);
                break;

            case R.drawable.gen1:
                iv.setImageResource(R.drawable.gen2);
                iv.setTag(R.drawable.gen2);
                break;

            case R.drawable.gen2:
                iv.setImageResource(R.drawable.gen3);
                iv.setTag(R.drawable.gen3);
                break;

            case R.drawable.gen3:
                iv.setImageResource(R.drawable.gen0);
                iv.setTag(R.drawable.gen0);
                break;

            case R.drawable.pal0:
                iv.setImageResource(R.drawable.pal1);
                iv.setTag(R.drawable.pal1);
                break;

            case R.drawable.pal1:
                iv.setImageResource(R.drawable.pal2);
                iv.setTag(R.drawable.pal2);
                break;
            case R.drawable.pal2:
                iv.setImageResource(R.drawable.pal3);
                iv.setTag(R.drawable.pal3);
                break;
            case R.drawable.pal3:
                iv.setImageResource(R.drawable.pal4);
                iv.setTag(R.drawable.pal4);
                break;
            case R.drawable.pal4:
                iv.setImageResource(R.drawable.pal0);
                iv.setTag(R.drawable.pal0);
                break;

        }




    }

    public void updateChars()
    {
        for(ImageView v: chars)
        {
            changeSprite(v);
        }
    }



}
