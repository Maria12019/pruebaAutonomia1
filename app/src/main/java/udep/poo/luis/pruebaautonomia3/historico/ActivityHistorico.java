package udep.poo.luis.pruebaautonomia3.historico;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.widget.SearchView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import udep.poo.luis.pruebaautonomia3.R;


public class ActivityHistorico extends AppCompatActivity implements IncidenciaAdapter.RecyclerItemClick, SearchView.OnQueryTextListener{
    private SearchView svSearch;
    private RecyclerView recyclerView2;
    private IncidenciaAdapter adapter;
    private ArrayList<incidencia> incidenciaList;
    ProgressDialog progressDialog;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Obteniendo data...");
        progressDialog.show();

        svSearch = findViewById(R.id.svSearch);
        recyclerView2 = findViewById(R.id.recyclerview2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        incidenciaList = new ArrayList<incidencia>();
        adapter = new IncidenciaAdapter(incidenciaList, ActivityHistorico.this);
        recyclerView2.setAdapter(adapter);

        getIncidencias();
        initListener();

    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }

    private void getIncidencias() {
        db.collection("historico").document("LI00282").collection("incidencias").orderBy("fecha_despacho", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                        if (error != null){
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                incidenciaList.add(dc.getDocument().toObject(incidencia.class));
                            }
                            adapter.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    }
                });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }

    @Override
    public void itemClick(incidencia incidencia) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("itemDetail", incidencia);
        startActivity(intent);
    }
}