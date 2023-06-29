CREATE TABLE appointment (
  appointment_id INT NOT NULL AUTO_INCREMENT, 
  patient_name VARCHAR(100), 
  date DATE,
  PRIMARY KEY(appointment_id)
);

INSERT INTO appointment VALUES(NULL, "Bob Jones", NOW());
INSERT INTO appointment VALUES(NULL, "Anna Smith", NOW());