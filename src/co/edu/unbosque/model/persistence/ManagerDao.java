package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.Pet;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ManagerDao {

    private ArrayList<Pet> pets;
    private ArrayList<Pet> aux;
    private File file = new File("data/pets-citizens-pruebas.csv");
    private Pet archivoP;
    private int contadorAgre;
    private int contadorN1;
    private int contadorN2;
    private BufferedReader br;
    private FileReader fr;


    public ManagerDao(){
        if (file.exists()) {
            System.out.println("El archivo existe");
            pets= new ArrayList<Pet>();
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public void uploadData() {
        String line;
        contadorAgre=0;
        contadorN1=0;
        contadorN2=0;
        boolean potentDangerous = true;
        try {
            fr = new FileReader(file, StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            while((line = br.readLine()) != null){
                String[] parts = line.split(";");
                if(!parts[0].equalsIgnoreCase("microchip")){
                    try {
                        String id="NO ASIGNADO";
                        long microchip= Long.parseLong(parts[0]);
                        String species=parts[1];
                        String sex=parts[2];
                        String size=parts[3];
                        if(parts[4].equalsIgnoreCase("NO")){
                             potentDangerous=false;
                        }else if(parts[4].equalsIgnoreCase("SI")) {
                             potentDangerous = true;
                        }
                        String neighborhood=parts[5];
                        char[] csize = size.toCharArray();
                        char psize='P';
                        if(csize[0]==psize){
                            size="PEQUENO";
                        }
                        Pet nuevo = new Pet(id, microchip, species, sex, size, potentDangerous, neighborhood);
                        pets.add(nuevo);
                        contadorAgre++;
                    } catch (NumberFormatException e) {
                        //System.out.println("EL formato del chip no es long, el registro fue omitido");
                        contadorN1++;
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        //System.out.println("EL atributo del neighborhood se encuentra vacio,  el registro fue omitido");
                        contadorN2++;
                    }
                }
                //System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Se cargaron un total de "+contadorAgre+" Exitosamente");
        System.out.println("Se omitieron un total de "+contadorN1+" por error en el tipo de dato en el campo chip");
        System.out.println("Se omitieron un total de "+contadorN2+" por campo vacio en el atributo neighborhood  ");
    }
    public void assignID(){
        String strdangerous ;
        String id = null;
        for (int i=0;i<pets.size();i++) {

            String strchip = pets.get(i).getMicroship()+"";
            String strspecies = pets.get(i).getSpcies()+"";
            String strsex = pets.get(i).getSex()+"";
            String strsize = pets.get(i).getSize()+"";
            boolean strapeligroso = pets.get(i).isPotentDangeorous();
            char[] chip = strchip.toCharArray();
            char[] species = strspecies.toCharArray();
            char[] sex = strsex.toCharArray();
            String size;

            if(strsize.equalsIgnoreCase("MINIATURA")){
                size = "Mi";
            }else if(strsize.equalsIgnoreCase("PEQUENO")){
                size = "P";
            }else if(strsize.equalsIgnoreCase("MEDIANO")){
                size = "M";
            }else if(strsize.equalsIgnoreCase("GRANDE")){
                size = "G";
            }else{
                size = "G";
            }
            if(strapeligroso){
               strdangerous="T";
            }else{
               strdangerous="F";
            }
            id = ((chip[chip.length - 2]) + "" + (chip[chip.length - 1]) + "-"
                    + species[0] + "" + sex[0] + "" + size+""+strdangerous);
             ArrayList<String> ids= new ArrayList<String>();
             ids.add(id);
            busqueda(ids,"00-CHMF");
            Pet nuevo = new Pet(id,pets.get(i).getMicroship(),pets.get(i).getSpcies(),pets.get(i).getSex(),
                                 pets.get(i).getSize(),pets.get(i).isPotentDangeorous(),pets.get(i).getNeighborhood());
            pets.set(i, nuevo);
        }
    }

    public int busqueda(ArrayList<String> arreglo, String busqueda){
        int izquierda = 0, derecha = arreglo.size() - 1;
        while (izquierda <= derecha) {
            int indiceDelElementoDelMedio = (int) Math.floor((izquierda + derecha) / 2);
            String elementoDelMedio = arreglo.get(indiceDelElementoDelMedio);
            int resultadoDeLaComparacion = busqueda.compareTo(elementoDelMedio);
            if (resultadoDeLaComparacion == 0) {
                return indiceDelElementoDelMedio;
            }
            if (resultadoDeLaComparacion < 0) {
                derecha = indiceDelElementoDelMedio - 1;
            } else {
                izquierda = indiceDelElementoDelMedio + 1;
            }
        }
        return -1;
    }


    public String findByMicrochip(long microchip){
        String mensaje="no se ha encontrado";
        for(int i=0;i<pets.size();i++){
            if (microchip==pets.get(i).getMicroship()){
                mensaje=pets.get(i).toString();
                break;
            }
        }
        return mensaje ;
    }
    public String countBySpecies(int species){
        String mensaje="";
        int contadorC=0;
        int contadorF=0;
        for(int i=0;i<pets.size();i++){

            if(pets.get(i).getSpcies().equalsIgnoreCase("CANINO")){
                contadorC++;
            }else {
                contadorF++;
            }

            if(species==3){
                mensaje="La cantidad de felinos es "+contadorF+" y la cantidad de caninos es"+contadorC;
            }else if(species==1){
                mensaje="La cantidad de felinos es "+contadorF;
            }else if (species==2){
                mensaje="La cantidad de caninos es "+contadorC;
            }

        }
        return mensaje;
    }
    public String countByNeighborhood(String neighborhood){
        String mensaje="";
        int contador=0;

        if(neighborhood.isEmpty()){
            ArrayList <String>barrio = new ArrayList();
            ArrayList <Integer>acontador = new ArrayList();
            barrio.add(pets.get(0).getNeighborhood());
            acontador.add(contador);
            int i=0;
            for(int j=0;j<23;j++){
                while (i<pets.size()){
                   //barrio.get(j).equalsIgnoreCase(pets.get(i).getNeighborhood())
                    String barrio1=barrio.get(j);
                    String barrio2=pets.get(i).getNeighborhood();
                    if(barrio1.equalsIgnoreCase(barrio2)){
                        contador++;
                        acontador.set(j,contador);
                    }else{
                        barrio.add(pets.get(i).getNeighborhood());
                        acontador.add(1);
                        i++;
                        contador=1;
                        break;
                    }
                    i++;
                }

            }

                for(int u=0;u<22;u++){
                    mensaje=mensaje+barrio.get(u)+": "+acontador.get(u)+'\'';
                }



        }else{
            for(int i=0;i<pets.size();i++){
                if(pets.get(i).getNeighborhood().equalsIgnoreCase(neighborhood)){
                    contador++;

                }
            }
            mensaje=neighborhood+": "+contador;
        }


        return mensaje ;
    }

    public String findByMultipleFields(int caso, int n, String position, String spices, String sex, String size, String dangerous, String neighborhood){
        System.out.println(n+"," +position + "," + spices + "," + sex + "," + size + "," + dangerous + "," + neighborhood);
        ArrayList<Pet> petsMF= new ArrayList();
        petsMF =pets;

        switch (caso){
            //solo posicion
            case 1: {
                if(n!=0){
                    System.out.println("ejecucion caso 1");
                    int tamaño=petsMF.size();
                    if(position.equalsIgnoreCase("top")){
                        while (petsMF.size()!=n){
                            petsMF.remove(n);
                        }
                    }else if(position.equalsIgnoreCase("last")){
                        while (petsMF.size()!=n){
                            petsMF.remove(0);
                        }
                    }
                    findByMultipleFields(2,n,position,spices,sex,size,dangerous,neighborhood);
                }
                break;
            }
            //solo species
            case 2: {
                if(!spices.isEmpty()){
                    System.out.println("ejecucion caso 2");
                    findByMultipleFields(3,n,position,spices,sex,size,dangerous,neighborhood);
                }
                break;
            }
            //solo sex
            case 3: {
                if(!sex.isEmpty()){
                    System.out.println("ejecucion caso 3");
                    findByMultipleFields(4,n,position,spices,sex,size,dangerous,neighborhood);
                }
                break;
            }
            //solo size
            case 4: {
                if(!size.isEmpty()){
                    System.out.println("ejecucion caso 4");
                    findByMultipleFields(5,n,position,spices,sex,size,dangerous,neighborhood);
                }
                break;
            }
            //solo dangerous
            case 5: {
                if(!dangerous.isEmpty()){
                    System.out.println("ejecucion caso 5");
                    findByMultipleFields(6,n,position,spices,sex,size,dangerous,neighborhood);
                }
                break;
            }
            //solo neighborhood
            case 6: {
                if(!neighborhood.isEmpty()){
                    System.out.println("ejecucion caso 6");
                    findByMultipleFields(0,n,position,spices,sex,size,dangerous,neighborhood);
                }
                break;
            }
        }
      return  petsMF.toString();
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }
    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }
    public int getContadorAgre() {
        return contadorAgre;
    }
    public void setContadorAgre(int contadorAgre) {
        this.contadorAgre = contadorAgre;
    }
    public int getContadorN1() {
        return contadorN1;
    }
    public void setContadorN1(int contadorN1) {
        this.contadorN1 = contadorN1;
    }
    public int getContadorN2() {
        return contadorN2;
    }
    public void setContadorN2(int contadorN2) {
        this.contadorN2 = contadorN2;
    }
}
