package upc.edu.oneup.service;

import upc.edu.oneup.model.Device;
import upc.edu.oneup.model.Patient;
import upc.edu.oneup.model.Report;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
    Patient getPatientById(int id);
    Patient savePatient(Patient patient);
    void deletePatient(int id);
    Device getDeviceByPatientId(int id);
    List<Report> getReportByPatientId(int id);
    Patient updatePatient(Patient patient);

    boolean userHasPatient(int userId); // Nuevo método
}
