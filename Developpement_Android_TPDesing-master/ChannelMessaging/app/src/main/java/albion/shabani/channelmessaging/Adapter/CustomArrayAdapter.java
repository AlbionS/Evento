package albion.shabani.channelmessaging.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import albion.shabani.channelmessaging.R;
import albion.shabani.channelmessaging.Clases.Channel;

/**
 * Created by shabania on 26/02/2018.
 */
public class CustomArrayAdapter extends ArrayAdapter<Channel> {


    private final Context context;
    private final ArrayList<Channel> values;

    public CustomArrayAdapter(Context context, ArrayList<Channel> values) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.values = values;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textViewName = (TextView) rowView.findViewById(R.id.textViewChannelName);
        TextView textViewUsers = (TextView) rowView.findViewById(R.id.textViewNbUsers);

        textViewName.setText(values.get(position).getName());
        String users = "Nombre d'utilisateur connectés : "+ values.get(position).getConnectedusers();
        textViewUsers.setText(users);
        return rowView;


    }


}