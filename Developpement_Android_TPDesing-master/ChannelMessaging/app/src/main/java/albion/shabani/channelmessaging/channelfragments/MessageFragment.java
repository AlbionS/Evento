package albion.shabani.channelmessaging.channelfragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.HashMap;

import albion.shabani.channelmessaging.Clases.Connexion;
import albion.shabani.channelmessaging.Adapter.CustomArrayAdapterMessage;
import albion.shabani.channelmessaging.Request.HttpPostHandler;
import albion.shabani.channelmessaging.Container.MessageContainer;
import albion.shabani.channelmessaging.Request.OnDownloadListener;
import albion.shabani.channelmessaging.Request.PostRequest;
import albion.shabani.channelmessaging.R;
import albion.shabani.channelmessaging.Clases.User;
import albion.shabani.channelmessaging.BDD.UserDataSource;

import com.google.gson.Gson;

/**
 * Created by shabania on 02/03/2018.
 */
public class MessageFragment extends Fragment implements View.OnClickListener, OnDownloadListener,AdapterView.OnItemClickListener  {
    private Button sendButton;
    private String channelid;
    private EditText messageText;
    private Handler handler;
    private ListView messagesListView;
    private MessageContainer messages;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.channelfragment, container);

        sendButton = (Button) v.findViewById(R.id.buttonSend);
        messageText = (EditText) v.findViewById(R.id.editTextMessage);
        messagesListView = (ListView) v.findViewById(R.id.listViewMessages);
        messagesListView.setOnItemClickListener(this);
        handler = null;

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            if (savedInstanceState == null) {
                Bundle extras = getActivity().getIntent().getExtras();
                if (extras == null) {
                    channelid = "";
                } else {
                    channelid = extras.getString("channelID");
                }
            } else {
                channelid = (String) savedInstanceState.getSerializable("channelID");
            }
        }

        sendButton.setOnClickListener(this);

        handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                if (getActivity() != null ) {
                    if(channelid!="" || channelid!= null) {
                        HttpPostHandler post = new HttpPostHandler();
                        post.addOnDownloadListener(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete(String downloadedContent) {

                                if (getActivity() != null) {
                                    Gson gson = new Gson();
                                    messages = gson.fromJson(downloadedContent, MessageContainer.class);

                                    CustomArrayAdapterMessage adapter = new CustomArrayAdapterMessage(getActivity().getApplicationContext(), messages.getMessages());
                                    messagesListView.setAdapter(adapter);
                                }
                            }
                            @Override
                            public void onDownloadError(String error) {
                                if (getActivity() != null) {
                                    Toast.makeText(getActivity().getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        HashMap<String, String> hm = new HashMap<String, String>();
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                        hm.put("accesstoken", prefs.getString("AccessToken", ""));
                        hm.put("channelid", channelid);

                        post.setHttpPostHandler(getActivity().getApplicationContext());
                        post.execute(new PostRequest("http://www.raphaelbischof.fr/messaging/?function=getmessages", hm));
                    }
                    handler.postDelayed(this, 1000);
                } else
                    handler.removeCallbacksAndMessages(null);
            }
        };

        handler.postDelayed(r, 1000);

    }
    @Override
    public void onClick(View v) {

        HttpPostHandler post = new HttpPostHandler();
        post.addOnDownloadListener(this);
        HashMap<String,String> hm = new HashMap<String,String>();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        hm.put("accesstoken",prefs.getString("AccessToken",""));
        hm.put("channelid",channelid);
        hm.put("message",messageText.getText().toString());

        post.setHttpPostHandler(getActivity().getApplicationContext());
        post.execute(new PostRequest("http://www.raphaelbischof.fr/messaging/?function=sendmessage",hm));


    }


    @Override
    public void onDownloadComplete(String downloadedContent) {
        Gson gson = new Gson();
        Connexion conn = gson.fromJson(downloadedContent,Connexion.class);
        Toast.makeText(getActivity().getApplicationContext(), conn.getResponse(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDownloadError(String error) {
        Toast.makeText(getActivity().getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("channelID", channelid);
    }

    public void setChannelid(String channelid){
        this.channelid = channelid;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialogMessageAddFriend)
                .setTitle(R.string.dialogMessageAddFriendTitle);


        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                UserDataSource dataSource = new UserDataSource(getActivity().getApplicationContext());
                try {
                    dataSource.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //Todo
                if (dataSource.getAllUsers() != null && !dataSource.getAllUsers().isEmpty()) {
                    boolean ok = true;
                    for (User user : dataSource.getAllUsers()) {
                        if (user.getUsername().equals(messages.getMessages().get(position).getUsername())) {
                            ok = false;
                        }
                    }
                    if (ok) {
                        dataSource.createUser(messages.getMessages().get(position).getUsername(), messages.getMessages().get(position).getImageUrl());
                    } else {
                        dialog.cancel();
                    }
                } else {
                    dataSource.createUser(messages.getMessages().get(position).getUsername(), messages.getMessages().get(position).getImageUrl());
                }
                dataSource.close();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
