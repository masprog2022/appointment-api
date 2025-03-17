CREATE TABLE tb_appointment (
  id serial PRIMARY KEY,
  description varchar(255),
  date_time timestamp,
  created_at timestamp,
  patient_id integer,
  CONSTRAINT fk_appointment_patient FOREIGN KEY (patient_id) REFERENCES tb_patient(id)
)