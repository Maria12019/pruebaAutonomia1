package udep.poo.luis.pruebaautonomia3.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import udep.poo.luis.pruebaautonomia3.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    Context context;
    ArrayList<Model> modelArrayList;

    public MyAdapter(Context context, ArrayList<Model> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyAdapter.MyViewHolder holder, int position) {

        Model model = modelArrayList.get(position);

        holder.id_sitio.setText(model.id_sitio);
        holder.Nombre_Local.setText(model.Nombre_Local);
        holder.Direccion.setText(model.Direccion);
        holder.Priorización.setText(model.Priorización);
        holder.ZONA.setText(model.ZONA);

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id_sitio, Nombre_Local, Direccion, Priorización, ZONA;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            id_sitio = itemView.findViewById(R.id.id_sitio);
            Nombre_Local = itemView.findViewById(R.id.Nombre_Local);
            Direccion = itemView.findViewById(R.id.Direccion);
            Priorización = itemView.findViewById(R.id.Priorización);
            ZONA = itemView.findViewById(R.id.ZONA);
        }
    }
}
