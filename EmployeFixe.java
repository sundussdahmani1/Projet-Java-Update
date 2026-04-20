import java.time.LocalDate;
public class EmployeFixe extends Employe {
    private double salaireBase;
    private double primePerformance;
    public EmployeFixe(int id, String nom, String mail, String depart, LocalDate dateEmbauche, double salaireBase, double primePerformance)
            throws InvalidWorkDataException {
        super(id, nom, mail, depart, dateEmbauche);
        if (salaireBase < 3000)
            throw new InvalidWorkDataException(
                "Salaire " + salaireBase + " DH inférieur au SMIG");
        this.salaireBase=salaireBase;
        this.primePerformance=primePerformance;
    }
    @Override
    public double calculerSalaireBrut() {
        return salaireBase+primePerformance;
    }
    public double getSalaireBase(){ 
        return salaireBase;
         }
    public double getPrimePerformance(){ 
        return primePerformance; 
        }
    public void   setSalaireBase(double salaire){ 
        this.salaireBase = salaire; 
        }
}
