package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.Pet;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ManagerDao {

    private ArrayList<Pet> pets;
    private File file = new File("data/pets-citizens.csv");
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
    public void cargarCSV() {
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
                        String id="0";
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
