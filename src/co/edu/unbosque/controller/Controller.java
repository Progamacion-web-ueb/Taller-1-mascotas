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
        v.mostrarInformacion("Para cargar la informacion de las mascotas presione la tecla: 1");
        v.mostrarInformacion("Para  asignar el id correspondiente a cada mascota presion la telca: 2");
        v.mostrarInformacion("Para buscar los datos de una mascota por microchip presione la tecla: 3");
        v.mostrarInformacion("Para contar la cantida de mascotas por especie presione la tecla: 4");
        v.mostrarInformacion("Para contar la cantidad de animaler por localidad presione la tecla: 5");
        v.mostrarInformacion("Para listar los animaler por peligrosidad presione la tecla: 6");
        v.mostrarInformacion("Para mostrar todos los datos presione la tecla: 7 ");

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
        int caso1=0;
        switch (caso){
            case 1: {

                if( dao.uploadData()){
                    v.mostrarInformacion("Se cargaron un total de "+dao.getContadorAgre()+" datos Exitosamente");
                    v.mostrarInformacion("Se omitieron un total de "+dao.getContadorN1()+" por error en el tipo de dato en el campo chip");
                    v.mostrarInformacion("Se omitieron un total de "+dao.getContadorN2()+" por campo vacio en el atributo neighborhood  ");
                    v.mostrarInformacion(" ");
                    v.mostrarInformacion("Para volver al menu principal presione la tecla: 1");
                    v.mostrarInformacion("Para terminar el programa presion la tecla:2");
                    v.ingresarinformacion();
                    caso1= Integer.parseInt(v.getScanner());
                }else {
                    v.mostrarInformacion("Error al cargar los datos");
                    v.mostrarInformacion("Para poder asignar los id se deben cargar los datos antes");
                    v.mostrarInformacion("Para volveral menu principal presione la tecla: 1");
                    v.mostrarInformacion("Para terminar el programa presion la tecla:2");
                    v.ingresarinformacion();
                    caso1= Integer.parseInt(v.getScanner());
                }
                break;
            }
            case 2: {

                if( dao.assignID()){
                    v.mostrarInformacion("Se asignaro los id de las mascotas exitosamente");
                    v.mostrarInformacion(" ");
                    v.mostrarInformacion("Para volver a al menu principal presione la tecla: 1");
                    v.mostrarInformacion("Para terminar el programa presion la tecla:2");
                    v.ingresarinformacion();
                    caso1= Integer.parseInt(v.getScanner());
                }else {
                    v.mostrarInformacion("Erro al asignar los id");
                    v.mostrarInformacion("Se deben cargar los datos antes de asignar los id");
                    v.mostrarInformacion(" ");
                    v.mostrarInformacion("Para volver al menu principal presione la tecla: 1");
                    v.mostrarInformacion("Para terminar el programa presion la tecla:2");
                    v.ingresarinformacion();
                    caso1= Integer.parseInt(v.getScanner());
                }
                break;
            }
            case 3: {
                v.mostrarInformacion("Por favor ingrese unicamente el numero del microochip que desea buscar");
                v.ingresarinformacion();
                String consola=v.getScanner();
                long chip= Long.parseLong(consola);
                v.mostrarInformacion("La mascota que ha buscado "+dao.findByMicrochip(chip));
                v.mostrarInformacion(" ");
                v.mostrarInformacion("Para volver al menu principal presione la tecla: 1");
                v.mostrarInformacion("Para terminar el programa presion la tecla:2");
                v.ingresarinformacion();
                caso1= Integer.parseInt(v.getScanner());
                break;
            }
            case 4: {
                v.mostrarInformacion("Por favor ingrese 1 para buscar felinos. ingrese 2 para buscar caninos");
                v.mostrarInformacion("y 3 para ver la cantidad de felinos y caninos");
                v.ingresarinformacion();
                int consola=Integer.parseInt(v.getScanner());
                v.mostrarInformacion(dao.countBySpecies(consola));
                v.mostrarInformacion(" ");
                v.mostrarInformacion("Para volver al menu principal presione la tecla: 1");
                v.mostrarInformacion("Para terminar el programa presion la tecla:2");
                v.ingresarinformacion();
                caso1= Integer.parseInt(v.getScanner());
                break;
            }
            case 5: {
                v.mostrarInformacion("Por favor ingrese unicamente la localidad en la que desea contar las mascotas");
                v.ingresarinformacion();
                String consola=v.getScanner();
                v.mostrarInformacion(dao.countByNeighborhood(consola));
                v.mostrarInformacion(" ");
                v.mostrarInformacion("Para volver al menu principal presione la tecla: 1");
                v.mostrarInformacion("Para terminar el programa presion la tecla:2");
                v.ingresarinformacion();
                caso1= Integer.parseInt(v.getScanner());
                break;
            }
            case 6: {
                dao.llenarArray();
                v.mostrarInformacion("Ingrese el numero de datos a mostrar");
                v.ingresarinformacion();
                int n=0;
                if(!v.getScanner().isEmpty()){
                    n=Integer.parseInt(v.getScanner());
                }
                v.mostrarInformacion("Ingrese la posicion (top,last)");
                v.ingresarinformacion();
                String position=v.getScanner();
                v.mostrarInformacion("Ingrese la especie (canino,felino)");
                v.ingresarinformacion();
                String spices=v.getScanner();
                v.mostrarInformacion("Ingrese el sexo(macho,hembra)");
                v.ingresarinformacion();
                String sex=v.getScanner();
                v.mostrarInformacion("Ingrese el tamaño(pequeno,mediano,grande)");
                v.ingresarinformacion();
                String size=v.getScanner();
                v.mostrarInformacion("Ingrese is es un animal potencialmente peligroso(true,false)");
                v.ingresarinformacion();
                String dangerous=v.getScanner();
                v.mostrarInformacion("Ingrese la localidad");
                v.ingresarinformacion();
                String neighborhood=v.getScanner();
                if(n==0&&position.isEmpty()&&spices.isEmpty()&&sex.isEmpty()&&size.isEmpty()&&dangerous.isEmpty()&&neighborhood.isEmpty()){
                    v.mostrarInformacion("Por favor ingrese al menos un atributo de busqueda");
                    mensajeinteraccion();
                }
                String pets =dao.findByMultipleFields(1,n,position,spices,sex,size,dangerous,neighborhood);
                String[] parts = pets.split("},");
                for(int i=0;i<parts.length;i++){
                    v.mostrarInformacion(parts[i]);
                }
                v.mostrarInformacion(" ");
                v.mostrarInformacion("Para volver al menu principal presione la tecla: 1");
                v.mostrarInformacion("Para terminar el programa presion la tecla:2");
                v.ingresarinformacion();
                caso1= Integer.parseInt(v.getScanner());
            }
            case 7: {
               for(int i=0;i<dao.getPets().size();i++){
                   v.mostrarInformacion(dao.getPets().get(i)+"");
               }
            }
        }
        switch (caso1){
            case 1: {
                System.out.flush();
                for (int i=0; i < 10; i++)
                {
                    System.out.println("");
                }
                mensajeinteraccion();
            }
            case 2: {

            }

        }

    }
}
