package com.mentorandroid.helpdesk_deskgeo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.mentorandroid.helpdesk_deskgeo.adapter.EventAdapter;
import com.mentorandroid.helpdesk_deskgeo.model.DosList;
import com.mentorandroid.helpdesk_deskgeo.model.Events;
import com.mentorandroid.helpdesk_deskgeo.model.EventsList;
import com.mentorandroid.helpdesk_deskgeo.model.Requisicao;
import com.mentorandroid.helpdesk_deskgeo.webservice.GenericRequest;

import java.util.List;

public class EventsActivity extends AppCompatActivity {

    Context ctx;
    private RecyclerView mRecyclerView;
    private EventAdapter adapter;
    private ProgressDialog pDialog;
    private List<Events> eventsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                loadWebService();
            }
        });
        loadWebService();
    }

    public void loadWebService(){
        //pDialog.show();
        RequestQueue queue = Volley.newRequestQueue(ctx);
        Requisicao req = new Requisicao();
        req.setMsg("GET_DOS");

        GenericRequest<EventsList> myReq = new GenericRequest<EventsList>(Request.Method.POST, "http://deskgeo.com/page/get_all_evets",
                EventsList.class, req, createMyReqSuccessListener(), createMyReqErrorListener());
        Log.d("DEBUG", "Entrou no response");
        queue.add(myReq);
    }

    private Response.Listener<EventsList> createMyReqSuccessListener() {
        return new Response.Listener<EventsList>() {
            @Override
            public void onResponse(EventsList response) {
                eventsList = response.getEventsList();
                pDialog.dismiss();
                adapter = new EventAdapter(ctx , eventsList);
                mRecyclerView.setAdapter(adapter);
                //callWear();

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
