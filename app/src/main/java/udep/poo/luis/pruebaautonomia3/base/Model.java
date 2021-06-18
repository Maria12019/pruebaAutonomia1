package udep.poo.luis.pruebaautonomia3.base;

class Model {

    String id_sitio;String Nombre_Local; String Direccion;String Priorización; String ZONA;

    public Model(){}

    public Model(String id_sitio, String nombre_Local, String direccion, String priorización, String zona) {
        this.id_sitio = id_sitio;
        Nombre_Local = nombre_Local;
        Direccion = direccion;
        Priorización = priorización;
        ZONA = zona;
    }

    public String getId_sitio() {
        return id_sitio;
    }

    public void setId_sitio(String id_sitio) {
        this.id_sitio = id_sitio;
    }

    public String getNombre_Local() {
        return Nombre_Local;
    }

    public void setNombre_Local(String nombre_Local) {
        Nombre_Local = nombre_Local;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getPriorización() {
        return Priorización;
    }

    public void setPriorización(String priorización) {
        Priorización = priorización;
    }

    public String getZona() {
        return ZONA;
    }

    public void setZona(String zona) {
        ZONA = zona;
    }

}
