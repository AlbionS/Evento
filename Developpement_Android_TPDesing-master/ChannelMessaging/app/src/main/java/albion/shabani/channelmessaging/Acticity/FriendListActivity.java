package albion.shabani.channelmessaging.Acticity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import java.sql.SQLException;

import albion.shabani.channelmessaging.Adapter.CustomArrayAdapterFriend;
import albion.shabani.channelmessaging.R;
import albion.shabani.channelmessaging.BDD.UserDataSource;

/**
 * Created by shabania on 26/02/2018.
 */
public class FriendListActivity extends Activity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);
        gridView = (GridView) findViewById(R.id.gridview);

        UserDataSource dataSource = new UserDataSource(getApplicationContext());
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(dataSource.getAllUsers() !=null && !dataSource.getAllUsers().isEmpty()){
            CustomArrayAdapterFriend adapter = new CustomArrayAdapterFriend(this.getApplicationContext(),dataSource.getAllUsers());
            gridView.setAdapter(adapter);
        }
        dataSource.close();
    }
}
