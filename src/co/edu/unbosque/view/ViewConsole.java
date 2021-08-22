package co.edu.unbosque.view;

import javax.swing.*;

public class ViewConsole {
    private String tamano;
    public ViewConsole() {

    }

    public void ingresarinformacion(String mensaje){
        tamano =  JOptionPane.showInputDialog(mensaje);
    }
    public void mostrarInformacion( String mensaje) {
        System.out.print(mensaje);
    }
    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }
}
