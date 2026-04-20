import java.time.LocalDate;
public class EmployeHoraire extends Employe {
    private double tauxHoraire;
    private double heuresTravaillees;
    public EmployeHoraire(int id, String nom, String mail, String depart, LocalDate dateEmbauche, double tauxHoraire, double heuresTravaillees)
            throws InvalidWorkDataException {
        super(id, nom, mail, depart, dateEmbauche);
        if (heuresTravaillees > 240)
            throw new InvalidWorkDataException(
                "Les heures travaillées:" + heuresTravaillees +  "dépassent 240h";
        )        
        this.tauxHoraire=tauxHoraire;
        this.heuresTravaillees=heuresTravaillees;
    }
    @Override
    public double calculerSalaireBrut() {
        if (heuresTravaillees<=180) {
            return heuresTravaillees*tauxHoraire;
        } else {
            double normales=180*tauxHoraire;
            double sup=(heuresTravaillees-180)*tauxHoraire*1.25;
            return normales+sup;
        }
    }
    public double getTauxHoraire(){ 
        return tauxHoraire; 
        }
    public double getHeuresTravaillees(){ 
        return heuresTravaillees;
        }
}
