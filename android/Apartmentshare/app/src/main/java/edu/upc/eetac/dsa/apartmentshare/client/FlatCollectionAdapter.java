package edu.upc.eetac.dsa.apartmentshare.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import edu.upc.eetac.dsa.apartmentshare.R;
import edu.upc.eetac.dsa.apartmentshare.client.entity.FlatCollection;

/**
 * Created by mazp on 20/12/15.
 */
public class FlatCollectionAdapter extends BaseAdapter {
    private FlatCollection flatCollection;
    private LayoutInflater layoutInflater;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public FlatCollectionAdapter(Context context, FlatCollection flatCollection){
        layoutInflater = LayoutInflater.from(context);
        this.flatCollection = flatCollection;
    }



    @Override
    public int getCount() {
        return flatCollection.getFlats().size();
    }

    @Override
    public Object getItem(int position) {
        return flatCollection.getFlats().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_flat_row, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String textname = flatCollection.getFlats().get(position).getId();
        String textdescription = flatCollection.getFlats().get(position).getDescription();
        long textcreationtimestamp = flatCollection.getFlats().get(position).getCreationTimestamp();


        viewHolder.textname.setText(textname);
        viewHolder.textdescription.setText(textdescription);
        viewHolder.textcreationtimestamp.setText(sdf.format(textcreationtimestamp));
        return convertView;
    }

    class ViewHolder{
        TextView textname;
        TextView textdescription;
        TextView textcreationtimestamp;

        ViewHolder(View row){
            this.textname = (TextView) row
                    .findViewById(R.id.textname);
            this.textdescription = (TextView) row
                    .findViewById(R.id.textdescription);
            this.textcreationtimestamp = (TextView) row
                    .findViewById(R.id.textcreationtimestamp);
        }
    }

}
