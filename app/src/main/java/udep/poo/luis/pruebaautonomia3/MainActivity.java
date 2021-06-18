package udep.poo.luis.pruebaautonomia3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText inputDni, inputContrasena;
    Button btnIngresar;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(this);
        inputDni = (EditText) findViewById(R.id.inputDni);
        inputContrasena = (EditText) findViewById(R.id.inputContrasena);

    }


    @Override
    public void onClick(View v) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Validando usuario...");
        progressDialog.show();
        switch (v.getId()) {
            case R.id.btnIngresar:
                if (inputDni.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Por favor ingrese DNI válido", Toast.LENGTH_SHORT).show();
                } else if (inputContrasena.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Por favor ingrese contraseña válida", Toast.LENGTH_SHORT).show();
                }
                db.collection("tecnicos")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                   @Override
                                                   public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                                                       if (task.isSuccessful()) {
                                                           for (QueryDocumentSnapshot doc : task.getResult()) {
                                                               String a = doc.getString("dniTecnico");
                                                               String b = doc.getString("contrasena");
                                                               String a1 = inputDni.getText().toString().trim();
                                                               String b1 = inputContrasena.getText().toString().trim();

                                                               if (a.equalsIgnoreCase(a1)  & b.equalsIgnoreCase(b1)) {
                                                                   String c = doc.getString("nombreTecnico");
                                                                   Bundle bundleTecnico = new Bundle();
                                                                   bundleTecnico.putString("nombreTecnico", c);
                                                                   Intent i = new Intent("android.intent.action.CONSULTA");
                                                                   i.putExtras(bundleTecnico);
                                                                   startActivity(i);
                                                                   if(progressDialog.isShowing())
                                                                       progressDialog.dismiss();
                                                                   Toast.makeText(MainActivity.this, "Sesión iniciada", Toast.LENGTH_SHORT).show();
                                                                   break;
                                                               } else
                                                                    if(progressDialog.isShowing()) progressDialog.dismiss();
                                                                   Toast.makeText(MainActivity.this, "DNI y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                                                           }
                                                       }
                                                   }
                                               });
        }
    }
}









