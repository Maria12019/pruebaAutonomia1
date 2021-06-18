package udep.poo.luis.pruebaautonomia3.historico;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import udep.poo.luis.pruebaautonomia3.R;

public class IncidenciaAdapter extends RecyclerView.Adapter<IncidenciaAdapter.MyViewHolder2>{
    private List<incidencia> originalList;
    private List<incidencia> incidenciaList;
    RecyclerItemClick itemClick;

    public IncidenciaAdapter(List <incidencia> incidenciaList, RecyclerItemClick itemClick ) {
        this.itemClick = itemClick;
        this.originalList = new ArrayList<>();
        originalList.addAll(incidenciaList);
        this.incidenciaList = incidenciaList;
    }



    @Override
    public MyViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.incidencia_item, parent, false);
        return new MyViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull IncidenciaAdapter.MyViewHolder2 holder, int position) {
        final incidencia incidencia = incidenciaList.get(position);

        holder.n_os.setText(incidencia.n_os);
        holder.estado_proceso.setText(incidencia.estado_proceso);
        holder.fecha_despacho.setText(incidencia.fecha_despacho);
        holder.fecha_salida.setText(incidencia.fecha_salida);
        holder.situacion_encontrada.setText(incidencia.situacion_encontrada);
        holder.actuacion.setText(incidencia.actuacion);
        holder.observaciones.setText(incidencia.observaciones);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(incidencia);

            }
        });

    }

    @Override
    public int getItemCount() {
        return incidenciaList.size();
    }

    public void filter (final String strSearch){
        if (strSearch.length() == 0){
            incidenciaList.clear();
            incidenciaList.addAll(originalList);
        }
        else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                incidenciaList.clear();
                List<incidencia> collect = originalList.stream()
                        .filter(i -> i.getFecha_despacho().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());
                incidenciaList.addAll(collect);
            }
            else {
                incidenciaList.clear();
                for (incidencia i : originalList){
                    if (i.getFecha_despacho().toLowerCase().contains(strSearch)){
                        incidenciaList.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder{
        TextView n_os, estado_proceso, fecha_despacho, fecha_salida, situacion_encontrada, actuacion, observaciones;

        public MyViewHolder2(View itemView) {
            super(itemView);
            n_os = itemView.findViewById(R.id.n_os);
            estado_proceso = itemView.findViewById(R.id.estado_proceso);
            fecha_despacho = itemView.findViewById(R.id.fecha_despacho);
            fecha_salida = itemView.findViewById(R.id.fecha_salida);
            situacion_encontrada = itemView.findViewById(R.id.situacion_encontrada);
            actuacion = itemView.findViewById(R.id.actuacion);
            observaciones = itemView.findViewById(R.id.observaciones);
        }
    }

    public interface RecyclerItemClick {
        void itemClick(incidencia incidencia);
    }
}
