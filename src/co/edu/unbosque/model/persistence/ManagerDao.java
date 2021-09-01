package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.IdentifierExistsException;
import co.edu.unbosque.model.Pet;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ManagerDao {

    private ArrayList<Pet> pets;
    private ArrayList<Pet> aux;
    private ArrayList<Pet> petsMF;
    private File file = new File("data/pets-citizens.csv");
    private Pet archivoP;

    private int contadorAgre;
    private int contadorN1;
    private int contadorN2;
    private BufferedReader br;
    private FileReader fr;
    private boolean bDangerous;


    public ManagerDao(){
        if (file.exists()) {
            System.out.println("El archivo existe");
            pets= new ArrayList<Pet>();
            petsMF=new ArrayList();
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public boolean uploadData() {
        String line;
        boolean mensaje=false;
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
                        mensaje=true;
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
        //System.out.println("Se cargaron un total de "+contadorAgre+" Exitosamente");
        //System.out.println("Se omitieron un total de "+contadorN1+" por error en el tipo de dato en el campo chip");
        //System.out.println("Se omitieron un total de "+contadorN2+" por campo vacio en el atributo neighborhood  ");
        return mensaje;
    }
    public boolean assignID(){
        String strdangerous ;
        boolean mensaje=false;
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
            try{
            id = ((chip[chip.length - 2]) + "" + (chip[chip.length - 1]) + "-"
                    + species[0] + "" + sex[0] + "" + size+""+strdangerous);
                for(int j=0;j<pets.size();j++){
                    if(id.equalsIgnoreCase(pets.get(j).getId())){
                        throw new IdentifierExistsException("prueba");
                    }else if(pets.get(j).getId().equalsIgnoreCase("NO ASIGNADO")){
                        break;
                    }
                }
            }catch (IdentifierExistsException ex){
                id = ((chip[chip.length - 3]) + "" +(chip[chip.length - 2]) + "" + (chip[chip.length - 1]) + "-"
                        + species[0] + "" + sex[0] + "" + size+""+strdangerous);
            }
            Pet nuevo = new Pet(id,pets.get(i).getMicroship(),pets.get(i).getSpcies(),pets.get(i).getSex(),
                                 pets.get(i).getSize(),pets.get(i).isPotentDangeorous(),pets.get(i).getNeighborhood());
            pets.set(i, nuevo);
            mensaje=true;
        }
        return mensaje;
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
    public void llenarArray(){
        petsMF = new ArrayList<>(pets);
    }

    public String findByMultipleFields(int caso, int n, String position, String spices, String sex, String size, String dangerous, String neighborhood){

        switch (caso){
            //solo posicion
            case 1: {
                if(n!=0){
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
                }
                findByMultipleFields(2,n,position,spices,sex,size,dangerous,neighborhood);
            }
            //solo species
            case 2: {
                if(!spices.isEmpty()){
                    for(int i=0;i<petsMF.size();i++){
                        if(!spices.equalsIgnoreCase(petsMF.get(i).getSpcies())){
                            petsMF.remove(i);
                        }
                    }
                }
                findByMultipleFields(3,n,position,spices,sex,size,dangerous,neighborhood);
            }
            //solo sex
            case 3: {
                if(!sex.isEmpty()){
                    for(int i=0;i<petsMF.size();i++){
                        if(!sex.equalsIgnoreCase(petsMF.get(i).getSex())){
                            petsMF.remove(i);
                        }
                    }
                }
                findByMultipleFields(4,n,position,spices,sex,size,dangerous,neighborhood);
            }
            //solo size
            case 4: {
                if(!size.isEmpty()){
                    for(int i=0;i<petsMF.size();i++){
                        if(!size.equalsIgnoreCase(petsMF.get(i).getSize())){
                            petsMF.remove(i);
                        }
                    }
                }
                findByMultipleFields(5,n,position,spices,sex,size,dangerous,neighborhood);
            }
            //solo dangerous
            case 5: {
                if(!dangerous.isEmpty()){

                    for(int i=0;i<petsMF.size();i++){
                        if(dangerous.equalsIgnoreCase("true")){
                            bDangerous=true;
                        }else  if(dangerous.equalsIgnoreCase("false")){
                            bDangerous=false;
                        }
                        if(bDangerous!=petsMF.get(i).isPotentDangeorous()){
                            petsMF.remove(i);
                        }
                    }
                }
                findByMultipleFields(6,n,position,spices,sex,size,dangerous,neighborhood);
            }
            //solo neighborhood
            case 6: {
                if(!neighborhood.isEmpty()){
                    for(int i=0;i<petsMF.size();i++){
                        if(!neighborhood.equalsIgnoreCase(petsMF.get(i).getNeighborhood())){
                            petsMF.remove(i);
                        }
                    }
                }
                break;
            }
        }
      return  petsMF.toString();
    }

    public ArrayList<Pet> getPetsMF() {
        return petsMF;
    }
    public void setPetsMF(ArrayList<Pet> petsMF) {
        this.petsMF = petsMF;
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
