package entities;

public class service {
    private int id;
    private String nom ;
    private String type;
    private String nomcat;
    private int prix;

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getNomcat() {
        return nomcat;
    }

    public void setNomcat(String nomcat) {
        this.nomcat = nomcat;
    }

    

   
    public service(int id, String nom,String type , String nomcat , int prix ) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.nomcat = nomcat; 
        this.prix=prix;
        
    }
    public service(int id ) {
        this.id = id;
         
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

   



    
}
