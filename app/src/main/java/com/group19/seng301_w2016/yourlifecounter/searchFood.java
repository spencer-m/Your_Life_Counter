package com.group19.seng301_w2016.yourlifecounter;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.ClipboardManager;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Jennykuma on 2016-03-21.
 * Edited by Spencer on 2016-03-22.
 */
public class searchFood extends AppCompatActivity{


    //searchQuery
    //searchButton
    private Button searchB;
    private TextView searchQ;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_food);

        searchQ = (TextView) findViewById(R.id.searchQuery);

        searchB = (Button) findViewById(R.id.searchButton);
        searchB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //disappear keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                String query = searchQ.getText().toString();

                if (query.length() < 3)
                    Toast.makeText(getApplicationContext(), "Search Query Too Short!", Toast.LENGTH_LONG).show();
                else
                    new FoodSearch().execute(query);
            }


        });
    }

    private class FoodSearch extends AsyncTask<String, Void, ArrayList<Food>> {

        private ProgressDialog dialog;

        protected void onPreExecute() {

            //Toast.makeText(getApplicationContext(), "on pre execute", Toast.LENGTH_LONG).show();
            dialog = ProgressDialog.show(searchFood.this, "Searching Database", "Please wait...");
        }

        protected ArrayList<Food> doInBackground(String... s) {

            ArrayList<Food> result = new ArrayList<>();

            FoodDatabase tempFDB = new FoodDatabase();

            if (s.length == 1)
                result = tempFDB.search(s[0]);

            return result;
        }

        protected void onPostExecute(ArrayList<Food> result) {

            /*
            RecyclerView rvsearch = (RecyclerView) findViewById(R.id.searchResults);
            RecyclerView.Adapter<Food> adapter = new RecyclerView.Adapter<Food>(result);
            rvsearch.setAdapter(adapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            layoutManager.scrollToPosition(0);
            rvsearch.setLayoutManager(layoutManager);

            profiles.add(0, new DiaryEntries("Shannon", "July 1, 2016", "I ate at OTAFEST"));
            adapter.notifyItemInserted(0);
            */

            final ListView searchResList = (ListView) findViewById(R.id.searchResults);

            ArrayList<FoodView> searchRes= new ArrayList<FoodView>();

            for (Food f : result)
                searchRes.add(new FoodView(f.getName(), f.getFoodCategory(), f.getServingSize(), f.getServingUnit(), f.getCaloriesPerServing()));

            ArrayAdapter<FoodView> adapter = new ArrayAdapter<FoodView>(getApplicationContext(), android.R.layout.simple_list_item_1, searchRes){

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {

                    View view = super.getView(position, convertView, parent);
                    TextView text = (TextView) view.findViewById(android.R.id.text1);
                    text.setTextColor(Color.BLACK);

                    return view;
                }
            };
            searchResList.setAdapter(adapter);

            // TODO
            searchResList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int pos, long id) {

                    ClipboardManager clipboard = (ClipboardManager)
                            getSystemService(Context.CLIPBOARD_SERVICE);

                    //clipboard.setText(searchRes.get(pos).split("\n")[2].split(" ")[1]);

                    Toast.makeText(getApplicationContext(), "wow u clicked it ", Toast.LENGTH_SHORT).show();
                }
            });

            //Toast.makeText(getApplicationContext(), "post exec!", Toast.LENGTH_LONG).show();
            dialog.dismiss();

        }

    }
}
