package com.example.arlequina.slideviewer;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.arlequina.slideviewer.model.Quote;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by ARLEQUINA on 12/27/2017.
 */

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList arrList;
    List<Quote> quoteList;

    public int[] lst_images = {
        R.drawable.image_1,
        R.drawable.image_2,
        R.drawable.image_3,
        R.drawable.image_4
    };

    public String[] lst_title = {
        "COSMONAUT",
        "SATELITE",
        "GALAXY",
        "ROCKET"
    };
    public String[] lst_desc = {
            "Description 1",
            "Description 2",
            "Description 3",
            "Description 4"
    };

    public int[] lst_background = {
            Color.rgb(55,55,55),
            Color.rgb(239,85,85),
            Color.rgb(110,49,89),
            Color.rgb(1,188,212),
            Color.rgb(217,83,79),
            Color.rgb(91,192,222),
            Color.rgb(92,184,92),
            Color.rgb(66,139,202)
    };

    public SlideAdapter(Context context, List list)
    {
        this.context = context;
        this.quoteList = list;
        Collections.shuffle(quoteList);
    }

    @Override
    public int getCount() {
        return quoteList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide,container,false);
        LinearLayout layoutslide = (LinearLayout) view.findViewById(R.id.slidelinearlayout);
        ImageView imageView = (ImageView) view.findViewById(R.id.slideImage);
        TextView titleView = (TextView) view.findViewById(R.id.slideTitle);
        TextView descView = (TextView) view.findViewById(R.id.slideDesc);

        Random rand = new Random();
        int randNumber = rand.nextInt(8);
        int randImage =rand.nextInt(4);




        layoutslide.setBackgroundColor(lst_background[randNumber]);
        imageView.setImageResource(lst_images[randImage]);
        titleView.setText(quoteList.get(position).getQauth());
        descView.setText(quoteList.get(position).getQtext());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout)object);
    }
}
