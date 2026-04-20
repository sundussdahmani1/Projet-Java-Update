import java.sql.*;
import java.time.LocalDate;
import java.util.*;
public class EmployeDAO {
    private Connection connection;
    public EmployeDAO() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
    public void save(Employe emp) throws SQLException {
         String sql = """
            INSERT INTO employes
              (nom, mail, depart, date_embauche, type,
               salaire_base, prime_perf, taux_horaire, heures_trav, salaire_net)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, emp.getNom());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getDepartement());
            ps.setDate(4, java.sql.Date.valueOf(emp.getDateEmbauche()));
            ps.setDouble(10, emp.calculerNetAPayer());
            if (emp instanceof EmployeFixe ef) {
                ps.setString(5, "FIXE");
                ps.setDouble(6, ef.getSalaireBase());
                ps.setDouble(7, ef.getPrimePerformance());
                ps.setNull(8, Types.DOUBLE);
                ps.setNull(9, Types.DOUBLE);
            } else if (emp instanceof EmployeHoraire eh) {
                ps.setString(5, "HORAIRE");
                ps.setNull(6, Types.DOUBLE);
                ps.setNull(7, Types.DOUBLE);
                ps.setDouble(8, eh.getTauxHoraire());
                ps.setDouble(9, eh.getHeuresTravaillees());
            }
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) emp.setId(keys.getInt(1));
            }
        }
    }
    public List<Employe> findAll() throws SQLException, InvalidWorkDataException {
        List<Employe> liste=new ArrayList<>();
        try (Statement st=connection.createStatement();
             ResultSet rs=st.executeQuery("SELECT * FROM employes")) {
            while (rs.next()) {
                int id=rs.getInt("id");
                String nom=rs.getString("nom");
                String mail=rs.getString("email");
                String dept=rs.getString("département");
                LocalDate date=rs.getDate("date_embauche").toLocalDate();
                String type=rs.getString("type");
                Employe emp;
                if ("FIXE".equals(type)) {
                    emp = new EmployeFixe(id, nom, mail, dept, date, rs.getDouble("salaire_base"), rs.getDouble("prime_perf"));
                } else {
                    emp = new EmployeHoraire(id, nom, mail, dept, date,rs.getDouble("taux_horaire"), rs.getDouble("heures_trav"));
                }
                liste.add(emp);
            }
        }
        return liste;
    }
    public Map<String, Double> getMasseSalarialeParDept() throws SQLException {
        Map<String, Double> rapport = new LinkedHashMap<>();
        String sql = """
            SELECT   depart, SUM(salaire_net) AS masse
            FROM     employes
            GROUP BY depart
            ORDER BY masse DESC
            """;
        try (Statement st = connection.createStatement();
             ResultSet  rs = st.executeQuery(sql)) {
            while (rs.next())
                rapport.put(rs.getString("departement"), rs.getDouble("masse"));
        }
        return rapport;
    }
    public void augmenterSalaireBase(double pourcentage) throws SQLException {
        String sql = """
            UPDATE employes
            SET salaire_base = salaire_base * (1 + ?),
                salaire_net  = ((salaire_base * (1 + ?)) + prime_perf) * 0.80
            WHERE type = 'FIXE'
            """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, pourcentage / 100.0);
            ps.setDouble(2, pourcentage / 100.0);
            System.out.println(ps.executeUpdate() + " employé(s) fixe(s) mis à jour.");
        }
    }
}
