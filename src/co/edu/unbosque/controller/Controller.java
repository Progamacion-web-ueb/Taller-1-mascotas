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
                dao.uploadData();
                System.out.println("caso1");
                for(int i=0;i<=(dao.getContadorAgre()-1);i++){
                    v.mostrarInformacion(dao.getPets().get(i).toString());
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
                mensajeinteraccion();
            }
            case 3: {
                v.mostrarInformacion("Por favor ingrese unicamente el numero del microochip que desea buscar");
                v.ingresarinformacion();
                String consola=v.getScanner();
                long chip= Long.parseLong(consola);
                v.mostrarInformacion("La mascota que ha buscado "+dao.findByMicrochip(chip));
                mensajeinteraccion();

            }
            case 4: {
                v.mostrarInformacion("Por favor ingrese 1 para buscar felinos. ingrese 2 para buscar caninos");
                v.mostrarInformacion("y 3 para ver la cantidad de felinos y caninos");
                v.ingresarinformacion();
                int consola=Integer.parseInt(v.getScanner());
                v.mostrarInformacion(dao.countBySpecies(consola));
                mensajeinteraccion();
            }
            case 5: {
                v.mostrarInformacion("Por favor ingrese unicamente la localidad en la que desea contar las mascotas");
                v.ingresarinformacion();
                String consola=v.getScanner();
                v.mostrarInformacion(dao.countByNeighborhood(consola));
                mensajeinteraccion();
            }
            case 6: {
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
                v.mostrarInformacion(dao.findByMultipleFields(1,n,position,spices,sex,size,dangerous,neighborhood));
                //mensajeinteraccion();
            }
        }

    }
}
