package udep.poo.luis.pruebaautonomia3.base;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import udep.poo.luis.pruebaautonomia3.R;

public class ActivityBase extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Model> modelArrayList;
    MyAdapter myAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();



    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Obteniendo data...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        modelArrayList = new ArrayList<Model>();
        myAdapter = new MyAdapter(ActivityBase.this,modelArrayList);

        recyclerView.setAdapter(myAdapter);

        EventChangeListener();
    }

    private void EventChangeListener() {
        Bundle bundleCodigo = new Bundle();
        Query query = db.collection("base de sitios").whereEqualTo("id_sitio", bundleCodigo.getString("codigo"));
        query.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            if (documentSnapshot.exists()) {
                                Model model = documentSnapshot.toObject(Model.class);
                                modelArrayList.add(model);
                                Toast.makeText(ActivityBase.this,"Informacion cargada",Toast.LENGTH_LONG).show();
                            } else {
                              Toast.makeText(ActivityBase.this,"Error",Toast.LENGTH_LONG).show();
                            }
                        }
                        myAdapter.notifyDataSetChanged();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(ActivityBase.this,e.toString(),Toast.LENGTH_LONG).show();
                    }
                });

        /*Query query = db.collection("base de sitios").whereEqualTo("id_sitio",codigoSitio);
        for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(query.get().getResult()).getDocuments()){
            if (documentSnapshot.exists()) {
                modelArrayList.add(documentSnapshot.toObject(Model.class));
            } else {
                Toast.makeText(ActivityBase.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
            myAdapter.notifyDataSetChanged();
            if (progressDialog.isShowing())
                progressDialog.dismiss();
        }*/


        /*cr.whereEqualTo("id_sitio", codigoSitio)
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            modelArrayList.add(documentSnapshot.toObject(Model.class));
                        } else{
                            Toast.makeText(ActivityBase.this, "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {

                    }
                });*/
        /*db.collection("base de sitios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                String a = doc.getString("id_sitio");
                                Bundle bundleCodigo = getIntent().getExtras();
                                String a1 = bundleCodigo.getString("codigoBase");
                                if (a.equalsIgnoreCase(a1)) {
                                    db.collection("base de sitios")
                                            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                                @Override
                                                public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                                                    if (error != null) {

                                                        if (progressDialog.isShowing())
                                                            progressDialog.dismiss();
                                                        Log.e("Firestore error", error.getMessage());
                                                        return;
                                                    }
                                                    for (DocumentChange dc : value.getDocumentChanges()) {
                                                        if (dc.getType() == DocumentChange.Type.ADDED) {
                                                            modelArrayList.add(dc.getDocument().toObject(Model.class));
                                                        }
                                                    }
                                                    myAdapter.notifyDataSetChanged();
                                                    if (progressDialog.isShowing())
                                                        progressDialog.dismiss();
                                                }
                                            });
                                }
                                }
                            }

                        }
                    });*/

        /*db.collection("base de sitios")
            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                    if (error != null) {
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        Log.e("Firestore error", error.getMessage());
                        return;
                    }
                    for (DocumentChange dc : value.getDocumentChanges()) {
                        if (dc.getType() == DocumentChange.Type.ADDED) {

                            modelArrayList.add(dc.getDocument().toObject(Model.class));
                        }
                    }
                    myAdapter.notifyDataSetChanged();
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                }
            });*/
    }
}