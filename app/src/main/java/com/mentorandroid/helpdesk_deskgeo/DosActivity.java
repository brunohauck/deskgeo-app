package com.mentorandroid.helpdesk_deskgeo;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.mentorandroid.helpdesk_deskgeo.adapter.DosAdapter;
import com.mentorandroid.helpdesk_deskgeo.model.Dos;
import com.mentorandroid.helpdesk_deskgeo.model.DosList;
import com.mentorandroid.helpdesk_deskgeo.model.Requisicao;
import com.mentorandroid.helpdesk_deskgeo.webservice.GenericRequest;

import java.io.Serializable;
import java.util.List;

public class DosActivity extends AppCompatActivity {

    private Context ctx;
    private List<Dos> dosList;
    private RecyclerView mRecyclerView;
    private DosAdapter adapter;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ctx = this;
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ctx));
        pDialog = new ProgressDialog(ctx);
        pDialog.setMessage("Loading DOS Logs. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //callWear();
                loadWebService();
            }
        });
        //callWear();
        loadWebService();
    }

    public void loadWebService(){
        //pDialog.show();
        RequestQueue queue = Volley.newRequestQueue(ctx);
        Requisicao req = new Requisicao();
        req.setMsg("GET_DOS");

        GenericRequest<DosList> myReq = new GenericRequest<DosList>(Request.Method.POST, "http://deskgeo.com/page/get_all_dos",
                DosList.class, req, createMyReqSuccessListener(), createMyReqErrorListener());
        Log.d("DEBUG", "Entrou no response");
        queue.add(myReq);
    }

    public void callWear(){

        Log.d("DEBUG ->", "Action");

        String eventId = "1";
        String eventTitle = "DESK GEO";
        String eventLocation = "Dos Attack";

        int notificationId = 001;
        // Build intent for notification content
        Intent viewIntent = new Intent(this, SupportTeamActivity.class);

        viewIntent.putExtra("id", eventId);

        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, viewIntent, 0);

        Log.d("DEBUG ->", "Inicio Notificacao");

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_alarm_24dp)
                        .setLargeIcon(BitmapFactory.decodeResource(
                                getResources(), R.drawable.ddos))
                        .setContentTitle(eventTitle)
                        .setContentText(eventLocation)
                        .setContentIntent(viewPendingIntent);

// Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

// Build the notification and issues it with notification manager.
        notificationManager.notify(notificationId, notificationBuilder.build());

    }
    private Response.Listener<DosList> createMyReqSuccessListener() {
        return new Response.Listener<DosList>() {
            @Override
            public void onResponse(DosList response) {
                dosList = response.getDosList();
                pDialog.dismiss();
                adapter = new DosAdapter(ctx , dosList);
                mRecyclerView.setAdapter(adapter);
                callWear();

//                adapter.setOnCardViewClickListener(new UserListAdapter.OnCardViewClickListener() {
//                    @Override
//                    public void onClick(User user) {
//                        Intent intent = new Intent(ctx, ChatActivity.class);
//                        intent.putExtra("object", (Serializable) user);
//                        startActivity(intent);
//                    }
//
//                });

            }
        };
    }

    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Do whatever you want to do with error.getMessage();

                Log.d("DEBUG", "Entrou no ERRO");
                Toast.makeText(ctx, "ERRO", Toast.LENGTH_LONG).show();
            }
        };
    }

}
