package edu.upc.eetac.dsa.apartmentshare;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import edu.upc.eetac.dsa.apartmentshare.client.ApartmentshareClient;
import edu.upc.eetac.dsa.apartmentshare.client.ApartmentshareClientException;
import edu.upc.eetac.dsa.apartmentshare.client.FlatCollectionAdapter;
import edu.upc.eetac.dsa.apartmentshare.client.entity.Flat;
import edu.upc.eetac.dsa.apartmentshare.client.entity.FlatCollection;

public class FlatsListActivity extends AppCompatActivity {


    private final static String TAG = FlatsListActivity.class.toString();
    private GetStingsTask mGetStingsTask = null;
    private FlatCollection flats = new FlatCollection();
    private FlatCollectionAdapter adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_flat_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView list = (ListView)findViewById(R.id.list);
        adapter = new FlatCollectionAdapter(this,flats);
        list.setAdapter(adapter);
//        String uri = getIntent().getExtras().getString("uri");
//


        // set list OnItemClick listener
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FlatsListActivity.this, FlatDetailActivity.class);
                String uri = ApartmentshareClient.getLink(flats.getFlats().get(position).getLinks(), "self").getUri().toString();
                Log.d(TAG, ApartmentshareClient.getLink(flats.getFlats().get(position).getLinks(), "self").getUri().toString());
                intent.putExtra("uri", uri);
                startActivity(intent);
            }
        });

        // Execute AsyncTask
        mGetStingsTask = new GetStingsTask(null);
        mGetStingsTask.execute((Void) null);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    class GetStingsTask extends AsyncTask<Void, Void, String> {
        private String  uri;

//                =  getIntent().getExtras().getString("uri");

        public GetStingsTask(String uri) {

            this.uri = uri;

        }

        @Override
        protected String doInBackground(Void... params) {
            String jsonFlatCollection = null;
            try {
                jsonFlatCollection = ApartmentshareClient.getInstance().getFlats(uri);
//                        getFlats(uri);
            } catch (ApartmentshareClientException e) {
                // TODO: Handle gracefully
                Log.d(TAG, e.getMessage());
            }
            return jsonFlatCollection;
        }

        @Override
        protected void onPostExecute(String jsonFlatCollection) {
            Log.d(TAG, jsonFlatCollection);
            FlatCollection flatCollection = (new Gson()).fromJson(jsonFlatCollection, FlatCollection.class);
            for(Flat flat : flatCollection.getFlats()){
                flats.getFlats().add(flats.getFlats().size(), flat);
            }
            adapter.notifyDataSetChanged();
        }
    }


}
