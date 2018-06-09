package albion.shabani.channelmessaging.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import albion.shabani.channelmessaging.R;
import albion.shabani.channelmessaging.Clases.Message;

/**
 * Created by shabania on 26/02/2018.
 */
public class CustomArrayAdapterMessage extends ArrayAdapter<Message> {


    private final Context context;
    private final ArrayList<Message> values;

    public CustomArrayAdapterMessage(Context context, ArrayList<Message> values) {
        super(context, R.layout.rowlayoutmessage, values);
        this.context = context;
        this.values = values;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayoutmessage, parent, false);
        TextView textViewMessage = (TextView) rowView.findViewById(R.id.textViewMessage);
        TextView textViewDate = (TextView) rowView.findViewById(R.id.textViewDateMessage);

        String mess = values.get(position).getUsername()+" - "+ values.get(position).getMessage();
        textViewMessage.setText(mess);
        textViewDate.setText(values.get(position).getDate());
        return rowView;


    }


}