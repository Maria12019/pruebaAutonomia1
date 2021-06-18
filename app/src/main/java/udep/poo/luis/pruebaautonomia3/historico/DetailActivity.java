package udep.poo.luis.pruebaautonomia3.historico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import udep.poo.luis.pruebaautonomia3.R;

public class DetailActivity extends AppCompatActivity {
    private TextView n_osd, estado_procesod, fecha_despachod, fecha_salidad, situacion_encontradad, actuaciond, observacionesd;
    private udep.poo.luis.pruebaautonomia3.historico.incidencia incidenciaDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle(getClass().getSimpleName());

        initViews();
        initValues();
    }

    private void initViews(){
        n_osd = findViewById(R.id.n_osd);
        estado_procesod = findViewById(R.id.estado_procesod);
        fecha_despachod = findViewById(R.id.fecha_despachod);
        fecha_salidad = findViewById(R.id.fecha_salidad);
        situacion_encontradad = findViewById(R.id.situacion_encontradad);
        actuaciond = findViewById(R.id.actuaciond);
        observacionesd = findViewById(R.id.observacionesd);
    }

    private void initValues(){
        incidenciaDetail = (udep.poo.luis.pruebaautonomia3.historico.incidencia) getIntent().getExtras().getSerializable("itemDetail");

        n_osd.setText(incidenciaDetail.getN_os());
        estado_procesod.setText(incidenciaDetail.getEstado_proceso());
        fecha_despachod.setText(incidenciaDetail.getFecha_despacho());
        fecha_salidad.setText(incidenciaDetail.getFecha_salida());
        situacion_encontradad.setText(incidenciaDetail.getSituacion_encontrada());
        actuaciond.setText(incidenciaDetail.getActuacion());
        observacionesd.setText(incidenciaDetail.getObservaciones());
    }

}