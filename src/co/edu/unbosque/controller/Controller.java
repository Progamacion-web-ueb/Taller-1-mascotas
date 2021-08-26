package co.edu.unbosque.controller;

import co.edu.unbosque.model.Pet;
import co.edu.unbosque.model.persistence.ManagerDao;
import co.edu.unbosque.view.ViewConsole;

public class Controller {

    private ViewConsole v;
    private Pet p;
    private ManagerDao dao;

    public Controller (){
        v = new ViewConsole();
        dao = new ManagerDao();
        mensajeinteraccion();

    }
    public void mensajeinteraccion(){
        v.mostrarInformacion("Bienvenido al programa Ciudadanos de 4 Patas");
        v.mostrarInformacion("Para cargar la informacion de las mascotas presione la tecla : 1");
        v.mostrarInformacion("Para  asignar el id correspondiente a cada mascota presion la telca : 2");
        v.mostrarInformacion("");
        v.ingresarinformacion();
        try {
            int caso =Integer.parseInt(v.getScanner());
            interaccion(caso);
            System.out.println(caso);

        }catch (Exception e){

            v.mostrarInformacion("Porfavor ingrese unicamente las opciones disponibles");
            System.out.flush();
            mensajeinteraccion();
        }
    }
    public void interaccion(int caso){
        switch (caso){
            case 1: {
                dao.cargarCSV();
                System.out.println("caso1");
                for(int i=0;i<=(dao.getContadorAgre()-1);i++){
                    v.mostrarInformacion(dao.getPets().get(i).toString1());
                }
                v.mostrarInformacion("Se cargaron un total de "+dao.getContadorAgre()+" Exitosamente");
                v.mostrarInformacion("Se omitieron un total de "+dao.getContadorN1()+" por error en el tipo de dato en el campo chip");
                v.mostrarInformacion("Se omitieron un total de "+dao.getContadorN2()+" por campo vacio en el atributo neighborhood  ");

                mensajeinteraccion();


            }
            case 2: {
                dao.assignID();
                for(int i=0;i<=(dao.getContadorAgre()-1);i++){
                    v.mostrarInformacion(dao.getPets().get(i).toString());
                }
            }
            case 3: {
            }
        }

    }
}
