import java.time.LocalDate;
public abstract class Employe implements IPaye {
    protected int id;
    protected String nom;
    protected String mail;
    protected String depart;
    protected LocalDate dateEmbauche;
    public Employe(int id, String nom, String mail, String depart, LocalDate dateEmbauche) {
        this.id=id;
        this.nom=nom;
        this.mail=mail;
        this.depart=depart;
        this.dateEmbauche=dateEmbauche;
    }
    public int getAnciennete() {
        return LocalDate.now().getYear() - dateEmbauche.getYear();
    }
    @Override
    public double calculerPrimeAnciennete() {
        int anciennete=getAnciennete();
        double brut=calculerSalaireBrut();
        if (anciennete<2){
            return 0.0;
        }
        else if (anciennete <= 5) {
            return brut * 0.05;
        }
        else{
            return brut * 0.10;
        }
    }
    public int getId(){ 
        return id;
        }
    public void setId(int id) { 
        this.id = id; 
        }
    public String getNom() { 
        return nom; 
        }
    public String getEmail() { 
        return mail;
         }
    public String getDepartement() {
         return depart;
          }
    public LocalDate getDateEmbauche() { 
        return dateEmbauche;
         }

    @Override
    public String toString() {
        return "Nom" + getClass().getSimpleName() + nom + "Département" + depart + "Ancienneté" + getAnciennete() +"ans" + "Net" + calculerNetAPayer() + "Dh";
    }
}
