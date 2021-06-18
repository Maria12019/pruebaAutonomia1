package udep.poo.luis.pruebaautonomia3.historico;

import java.io.Serializable;

public class incidencia implements Serializable {
    String n_os, estado_proceso, fecha_despacho, fecha_salida, situacion_encontrada, actuacion, observaciones;

    public incidencia(String n_os, String estado_proceso, String fecha_despacho, String fecha_salida, String situacion_encontrada, String actuacion, String observaciones) {
        this.n_os = n_os;
        this.estado_proceso = estado_proceso;
        this.fecha_despacho = fecha_despacho;
        this.fecha_salida = fecha_salida;
        this.situacion_encontrada = situacion_encontrada;
        this.actuacion = actuacion;
        this.observaciones = observaciones;
    }

    public String getN_os() {
        return n_os;
    }

    public void setN_os(String n_os) {
        this.n_os = n_os;
    }

    public String getEstado_proceso() {
        return estado_proceso;
    }

    public void setEstado_proceso(String estado_proceso) {
        this.estado_proceso = estado_proceso;
    }

    public String getFecha_despacho() {
        return fecha_despacho;
    }

    public void setFecha_despacho(String fecha_despacho) {
        this.fecha_despacho = fecha_despacho;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getSituacion_encontrada() {
        return situacion_encontrada;
    }

    public void setSituacion_encontrada(String situacion_encontrada) {
        this.situacion_encontrada = situacion_encontrada;
    }

    public String getActuacion() {
        return actuacion;
    }

    public void setActuacion(String actuacion) {
        this.actuacion = actuacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
