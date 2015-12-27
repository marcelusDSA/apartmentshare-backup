package edu.upc.eetac.dsa.apartmentshare;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;

import edu.upc.eetac.dsa.apartmentshare.client.ApartmentshareClient;
import edu.upc.eetac.dsa.apartmentshare.client.ApartmentshareClientException;
import edu.upc.eetac.dsa.apartmentshare.client.entity.Flat;

/**
 * Created by mazp on 20/12/15.
 */
public class FlatDetailActivity extends AppCompatActivity{


    private final static String TAG = FlatDetailActivity.class.toString();
    private GetStingDetailTask mGetStingDetailTask = null;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_detail);
        String uri = getIntent().getExtras().getString("uri");


        // Launch the task

        mGetStingDetailTask = new GetStingDetailTask(uri);
        mGetStingDetailTask.execute((Void) null);
    }

    class GetStingDetailTask extends AsyncTask<Void, Void, String> {
        private String uri;

        public GetStingDetailTask(String uri) {
            this.uri = uri;

        }

        @Override
        protected String doInBackground(Void... params) {
            String jsonSting = null;
            try {
                jsonSting = ApartmentshareClient.getInstance().getFlat(uri);
            } catch (ApartmentshareClientException e) {
                // TODO: Handle gracefully
                Log.d(TAG, e.getMessage());
            }
            return jsonSting;
        }

        @Override
        protected void onPostExecute(String jsonSting) {
//            TextView tvSubject = (TextView)findViewById(R.id.textsubjectdetail);
            TextView tvContent = (TextView)findViewById(R.id.textiddetail);
            TextView tvCreator = (TextView)findViewById(R.id.textdescriptiondetail);
            TextView tvCreationTime = (TextView)findViewById(R.id.textcreationtimestampdetail);
            TextView tvLastModifTime = (TextView)findViewById(R.id.textlastModifieddetail);

            // json -> sting
            Flat flat = (new Gson()).fromJson(jsonSting, Flat.class);
//            tvSubject.setText(sting.getSubject());
            tvContent.setText(flat.getId());
            tvCreator.setText(flat.getDescription());
            tvCreationTime.setText(sdf.format(flat.getCreationTimestamp()));
            tvLastModifTime.setText(sdf.format(flat.getLastModified()));
        }
    }
}
