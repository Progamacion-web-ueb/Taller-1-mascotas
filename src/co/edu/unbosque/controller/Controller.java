package co.edu.unbosque.controller;

import co.edu.unbosque.model.Pet;
import co.edu.unbosque.view.ViewConsole;

public class Controller {

    private ViewConsole v;
    private Pet p;

    public Controller (){
        v = new ViewConsole();
        p = new Pet();

        v.mostrarInformacion("Prueba version incial");
    }
}
