package comparador;

public class Ordenador {
    private int id;
    private String modelo;
    private String marca;
    private String procesador;
    private String memoriaTipo;
    private int memoriaCapacidad;
    private String discoTipo;
    private int discoCapacidad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public String getMemoriaTipo() {
        return memoriaTipo;
    }

    public void setMemoriaTipo(String memoriaTipo) {
        this.memoriaTipo = memoriaTipo;
    }

    public int getMemoriaCapacidad() {
        return memoriaCapacidad;
    }

    public void setMemoriaCapacidad(int memoriaCapacidad) {
        this.memoriaCapacidad = memoriaCapacidad;
    }

    public String getDiscoTipo() {
        return discoTipo;
    }

    public void setDiscoTipo(String discoTipo) {
        this.discoTipo = discoTipo;
    }

    public int getDiscoCapacidad() {
        return discoCapacidad;
    }

    public void setDiscoCapacidad(int discoCapacidad) {
        this.discoCapacidad = discoCapacidad;
    }

    public String toString() {
        return modelo + " " + marca + " " + procesador + " "+ memoriaTipo + " " + memoriaCapacidad + " " + discoTipo + " " + discoCapacidad;
    }

}
