package co.edu.unbosque.model;

public class Pet {
    private String id;
    private long microship;
    private String spcies;
    private String sex;
    private String size;
    private boolean potentDangeorous;
    private String neighborhood;

    public Pet(String id, long microship, String spcies, String sex, String size, boolean potentDangeorous, String neighborhood) {
        this.id = id;
        this.microship = microship;
        this.spcies = spcies;
        this.sex = sex;
        this.size = size;
        this.potentDangeorous = potentDangeorous;
        this.neighborhood = neighborhood;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id='" + id + '\'' +
                ", microship=" + microship +
                ", spcies='" + spcies + '\'' +
                ", sex='" + sex + '\'' +
                ", size='" + size + '\'' +
                ", potentDangeorous=" + potentDangeorous +
                ", neighborhood='" + neighborhood + '\'' +
                '}';
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getMicroship() {
        return microship;
    }

    public void setMicroship(long microship) {
        this.microship = microship;
    }

    public String getSpcies() {
        return spcies;
    }

    public void setSpcies(String spcies) {
        this.spcies = spcies;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isPotentDangeorous() {
        return potentDangeorous;
    }

    public void setPotentDangeorous(boolean potentDangeorous) {
        this.potentDangeorous = potentDangeorous;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
}
