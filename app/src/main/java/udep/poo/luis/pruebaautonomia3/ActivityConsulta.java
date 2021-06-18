package udep.poo.luis.pruebaautonomia3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

public class ActivityConsulta extends AppCompatActivity implements View.OnClickListener {
    EditText inputCodigo;
    Button btnConsultar, btnRegresar;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        btnConsultar = findViewById(R.id.btnConsultar);
        btnConsultar.setOnClickListener(this);
        btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(this);
        inputCodigo = findViewById(R.id.inputCodigo);
        db = FirebaseFirestore.getInstance();
        Bundle bundleTecnico = getIntent().getExtras();
        TextView nombreTecnico = findViewById(R.id.nombreTecnico);
        if(nombreTecnico != null) {
            String nombreTecnico1 = bundleTecnico.getString("nombreTecnico");
            nombreTecnico.setText(nombreTecnico1);
        }
    }

    @Override
    public void onClick(View v) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Buscando sitio...");
        progressDialog.show();
        switch (v.getId()) {
            case R.id.btnRegresar:
                Intent i1 = new Intent("android.intent.action.MAIN");
                startActivity(i1);

            case R.id.btnConsultar:
                db.collection("base de sitios")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {


                            @Override
                            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot doc : task.getResult()) {
                                        String a = doc.getString("id_sitio");
                                        String a1 = inputCodigo.getText().toString().trim();
                                        if (a.equalsIgnoreCase(a1)) {
                                            String b = doc.getString("Nombre_Local");

                                            Bundle bundleSitio = new Bundle();
                                            bundleSitio.putString("nombreSitio", b);
                                            Bundle bundleCodigo = new Bundle();
                                            bundleCodigo.putString("codigo",a);

                                            Intent i2 = new Intent("android.intent.action.MENU");
                                            i2.putExtras(bundleSitio);
                                            i2.putExtras(bundleCodigo);
                                            startActivity(i2);
                                            if(progressDialog.isShowing())
                                                progressDialog.dismiss();
                                            Toast.makeText(ActivityConsulta.this, "Sitio encontrado", Toast.LENGTH_SHORT).show();
                                            break;
                                        } else
                                            if(progressDialog.isShowing())
                                            progressDialog.dismiss();
                                            Toast.makeText(ActivityConsulta.this, "Código inválido", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });

        }
    }
}