package co.edu.unbosque.view;

import javax.swing.*;
import java.util.Scanner;

public class ViewConsole {
    private String scanner;
    public ViewConsole() {

    }

    public void ingresarinformacion(){
        Scanner sc = new Scanner (System.in); //Creación de un objeto Scanner
       scanner = sc.nextLine();
    }
    public void mostrarInformacion( String mensaje) {
        System.out.println(mensaje);
    }

    public String getScanner() {
        return scanner;
    }

    public void setScanner(String scanner) {
        this.scanner = scanner;
    }
}
