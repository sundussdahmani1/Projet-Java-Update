CREATE DATABASE IF NOT EXISTS paie_db;
USE paie_db;

CREATE TABLE IF NOT EXISTS employes (
    id                 INT PRIMARY KEY AUTO_INCREMENT,
    nom                VARCHAR(100),
    mail               VARCHAR(100),
    depart             VARCHAR(50),
    date_embauche      DATE,
    type               VARCHAR(10),
    salaire_base       DOUBLE DEFAULT NULL,
    prime_perf         DOUBLE DEFAULT NULL,
    taux_horaire       DOUBLE DEFAULT NULL,
    heures_trav        DOUBLE DEFAULT NULL,
    salaire_net        DOUBLE DEFAULT 0
);
