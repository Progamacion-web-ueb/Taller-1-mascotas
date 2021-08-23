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
    public String toString1() {
        return "Pet{" +
                ", microship=" + microship +
                ", spcies='" + spcies + '\'' +
                ", sex='" + sex + '\'' +
                ", size='" + size + '\'' +
                ", potentDangeorous=" + potentDangeorous +
                ", neighborhood='" + neighborhood + '\'' +
                '}';
    }
}
