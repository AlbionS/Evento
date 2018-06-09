package albion.shabani.channelmessaging.Acticity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;


import albion.shabani.channelmessaging.Acticity.ChannelActivity;
import albion.shabani.channelmessaging.R;
import albion.shabani.channelmessaging.channelfragments.ChannelListFragment;
import albion.shabani.channelmessaging.channelfragments.MessageFragment;
/**
 * Created by shabania on 26/02/2018.
 */
public class ChannelListActivity extends Activity implements AdapterView.OnItemClickListener {

    private ChannelListFragment listFragment;
    private MessageFragment messageFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channellistactivity);
        listFragment = (ChannelListFragment) getFragmentManager().findFragmentById(R.id.fragment_container);
        messageFragment = (MessageFragment) getFragmentManager().findFragmentById(R.id.channelFragment);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if(getFragmentManager().findFragmentById(R.id.channelFragment) != null){
            messageFragment.setChannelid(listFragment.getChannelList().getChannels().get(position).getChannelID());
        }else{
            Intent myIntent = new Intent(getApplicationContext(),ChannelActivity.class);
            myIntent.putExtra("channelID",listFragment.getChannelList().getChannels().get(position).getChannelID());
            startActivity(myIntent);
        }

    }
}

