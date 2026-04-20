import java.time.LocalDate;
import java.util.Map;
public class Main {
    public static void main(String[] args) {
        try {
            EmployeDAO dao = new EmployeDAO();
            EmployeFixe ef = new EmployeFixe(
                0, "Amira Ahriz", "amira@rh.com", "RH", LocalDate.of(2018, 3, 15), 8000, 1500
            );
            EmployeHoraire eh = new EmployeHoraire(
                0, "Sabrine Hadi", "sabrine@it.com","IT", LocalDate.of(2022, 9, 1), 45, 200
            );
            dao.save(ef);
            dao.save(eh);
            System.out.println("Liste des employés");
            for (Employe emp : dao.findAll()) {
                System.out.println(emp);
                System.out.printf(
                    " Salaire brut:"+ emp.calculerSalaireBrut()+"DH" "Prime ancienneté:"+ emp.calculerPrimeAnciennete()+"Net:" e.calculerNetAPayer();
                );
            }
            System.out.println("Masse salariale par département");
            Map<String, Double> rapport = dao.getMasseSalarialeParDept();
            rapport.forEach((dept, total) ->
                System.out.printf("Departement "+dept+ " Masse "+total)
            );
            System.out.println("Augmentation 10% des fixes");
            dao.augmenterSalaireBase(10);

        } catch (InvalidWorkDataException e) {
            System.err.println("Erreur données : " + emp.getMessage());
        } catch (Exception emp) {
            emp.printStackTrace();
        }
    }
}
