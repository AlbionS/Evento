package albion.shabani.channelmessaging.Adapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import albion.shabani.channelmessaging.R;
import albion.shabani.channelmessaging.Clases.User;
import albion.shabani.channelmessaging.BDD.UserDataSource;

/**
 * Created by shabania on 26/02/2018.
 */
public class CustomArrayAdapterFriend extends ArrayAdapter<User> {
    private final Context context;
    private final List<User> values;

    public CustomArrayAdapterFriend(Context context, List<User> values) {
        super(context, R.layout.rowfriendlayout, values);
        this.context = context;
        this.values = values;
    }




    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.rowfriendlayout, parent, false);


        TextView textViewUsername = (TextView) rowView.findViewById(R.id.textViewUsername);
        ImageView imageView =(ImageView) rowView.findViewById(R.id.imageView);
        Button buttonDelete = (Button) rowView.findViewById(R.id.buttonDelete);

        Bitmap logo= null;
        if(values.get(position).getImage_url() != null) {
            try {
                InputStream is = new URL(values.get(position).getImage_url()).openStream();
                logo = BitmapFactory.decodeStream(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(logo);
        }
        textViewUsername.setText(values.get(position).getUsername());
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDataSource dataSource = new UserDataSource(v.getContext());
                try {
                    dataSource.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                dataSource.deleteUser(values.get(position));
            }
        });



        return rowView;

    }


}