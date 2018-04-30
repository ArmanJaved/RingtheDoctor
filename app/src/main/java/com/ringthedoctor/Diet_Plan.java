package com.ringthedoctor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Diet_Plan extends AppCompatActivity {

    private ListView mListView;
    public TextView recipe_list;
    RelativeLayout breakfast,lunch,dinner;

    String[] b_namesofdiets = Class_Diet_Constants.b_namesofdiets;
    String[] b_descofdiets = Class_Diet_Constants.b_descofdiets;
    int[] b_imagesofdiets = Class_Diet_Constants.b_imagesofdiets;
    String[] l_namesofdiets = Class_Diet_Constants.l_namesofdiets;
    String[] l_descofdiets = Class_Diet_Constants.l_descofdiets;
    int[] l_imagesofdiets = Class_Diet_Constants.l_imagesofdiets;
    String[] d_namesofdiets = Class_Diet_Constants.d_namesofdiets;
    String[] d_descofdiets = Class_Diet_Constants.d_descofdiets;
    int[] d_imagesofdiets = Class_Diet_Constants.d_imagesofdiets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet__plan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        breakfast = (RelativeLayout)findViewById(R.id.breakfast);
        lunch = (RelativeLayout)findViewById(R.id.lunch);
        dinner = (RelativeLayout)findViewById(R.id.dinner);

        mListView = (ListView) findViewById(R.id.recipe_list_view);
        final ImageView breakfastimg= (ImageView) findViewById(R.id.button_breakfast);
        ImageView launchimg= (ImageView) findViewById(R.id.button_launch);
        final ImageView dinerimg= (ImageView) findViewById(R.id.button_dinner);
//        recipe_list=(TextView) findViewById(R.id.recipe_list_subtitle);

        mListView.setBackgroundResource(R.drawable.customshape);

        final ArrayList<Recipe> recipeList = Recipe.getRecipesFromFile("recipes.json", this);
        final ArrayList<Recipe> recipeList1 = Recipe.getRecipesFromFile1("recipes1.json", this);
        final ArrayList<Recipe> recipeList2 = Recipe.getRecipesFromFile2("recipes2.json", this);


        Adapter adapter = new Adapter(getApplicationContext(), b_namesofdiets , b_descofdiets , b_imagesofdiets,"b");
        mListView.setAdapter(adapter);
        mListView.setBackgroundResource(R.drawable.customshape);

        breakfastimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Adapter adapter = new Adapter(getApplicationContext(), b_namesofdiets , b_descofdiets , b_imagesofdiets,"b");
                mListView.setAdapter(adapter);
                mListView.setBackgroundResource(R.drawable.customshape);

                breakfast.setBackgroundColor(Color.parseColor("#79a6c8"));
                lunch.setBackgroundColor(Color.parseColor("#E6E6FA"));
                dinner.setBackgroundColor(Color.parseColor("#E6E6FA"));

            }
        });

        launchimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Adapter adapter = new Adapter(getApplicationContext(), l_namesofdiets , l_descofdiets , l_imagesofdiets,"l");
                mListView.setAdapter(adapter);
                mListView.setBackgroundResource(R.drawable.customshape);
                breakfast.setBackgroundColor(Color.parseColor("#E6E6FA"));
                lunch.setBackgroundColor(Color.parseColor("#79a6c8"));
                dinner.setBackgroundColor(Color.parseColor("#E6E6FA"));

            }
        });
        dinerimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Adapter adapter = new Adapter(getApplicationContext(), d_namesofdiets , d_descofdiets , d_imagesofdiets,"d");
                mListView.setAdapter(adapter);
                mListView.setBackgroundResource(R.drawable.customshape);
                breakfast.setBackgroundColor(Color.parseColor("#E6E6FA"));
                lunch.setBackgroundColor(Color.parseColor("#E6E6FA"));
                dinner.setBackgroundColor(Color.parseColor("#79a6c8"));
            }
        });




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            // finish the activity

            startActivity(new Intent(Diet_Plan.this, MainPage.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
