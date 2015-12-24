package edu.upc.eetac.dsa.apartmentshare;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;

import edu.upc.eetac.dsa.apartmentshare.client.ApartmentshareClient;
import edu.upc.eetac.dsa.apartmentshare.client.ApartmentshareClientException;
import edu.upc.eetac.dsa.apartmentshare.client.FlatCollectionAdapter;
import edu.upc.eetac.dsa.apartmentshare.client.entity.Flat;
import edu.upc.eetac.dsa.apartmentshare.client.entity.FlatCollection;

public class Apartmentshare extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final static String TAG = FlatsListActivity.class.toString();
//    private GetStingsTask mGetStingsTask = null;
    private FlatCollection flats = new FlatCollection();
    private FlatCollectionAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartmentshare);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        ListView list = (ListView)findViewById(R.id.list);
        adapter = new FlatCollectionAdapter(this,flats);
        list.setAdapter(adapter);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

//        if (savedInstanceState == null) {
//            // on first time display view for first nav item
//            displayView(0);
//        }

        // Execute AsyncTask
//        mGetStingsTask = new GetStingsTask(null);
//        mGetStingsTask.execute((Void) null);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.apartmentshare, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


         if (id == R.id.nav_login) {

             startActivity(new Intent(Apartmentshare.this, LoginActivity.class));
            // Handle the camera action
        } else if (id == R.id.nav_register) {

             startActivity(new Intent(Apartmentshare.this, RegisterActivity.class));



//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_weare) {


        } else if (id == R.id.nav_contact) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


//    class GetStingsTask extends AsyncTask<Void, Void, String> {
//        private String uri;
//
//        public GetStingsTask(String uri) {
//            this.uri = uri;
//
//        }
//
//        @Override
//        protected String doInBackground(Void... params) {
//            String jsonFlatCollection = null;
//            try {
//                jsonFlatCollection = ApartmentshareClient.getInstance().getFlats(uri);
////                        getFlats(uri);
//            } catch (ApartmentshareClientException e) {
//                // TODO: Handle gracefully
//                Log.d(TAG, e.getMessage());
//            }
//            return jsonFlatCollection;
//        }
//
//        @Override
//        protected void onPostExecute(String jsonFlatCollection) {
//            Log.d(TAG, jsonFlatCollection);
//            FlatCollection flatCollection = (new Gson()).fromJson(jsonFlatCollection, FlatCollection.class);
//            for(Flat flat : flatCollection.getFlats()){
//                flats.getFlats().add(flats.getFlats().size(), flat);
//            }
//            adapter.notifyDataSetChanged();
//        }
//    }

}
