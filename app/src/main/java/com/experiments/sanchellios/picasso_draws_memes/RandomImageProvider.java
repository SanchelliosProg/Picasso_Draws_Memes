package com.experiments.sanchellios.picasso_draws_memes;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by aleksandrvasilenko on 10.04.16.
 */
public class RandomImageProvider {
    private ArrayList<String> memeURLs;
    private Queue<String> randomMemes;
    private Context context;

    public RandomImageProvider(Context context){
        this.context = context;
        fillMemeList();
    }

    private void fillMemeList(){
        String[] memes = context.getResources().getStringArray(R.array.memes);
        memeURLs = new ArrayList<>(Arrays.asList(memes));
    }

    public Queue<String> getPortionOfMemes(){
        Queue<String> randomMemes = new LinkedList<String>();
        int index;
        for(int i = 0; i < 3; i++){
            if(memeURLs.isEmpty())
                fillMemeList();
            index = getIndex();
            randomMemes.add(memeURLs.get(index));
            memeURLs.remove(index);
        }
        return randomMemes;
    }

    private int getIndex(){
        Random rnd = new Random();
        int index;
        try {
            index = rnd.nextInt(memeURLs.size()-1);
        }catch (RuntimeException e){
            index = 0;
        }
        return index;
    }
}
