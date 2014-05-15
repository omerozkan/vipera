package info.ozkan.vipera.doctorviews.patient;

import info.ozkan.vipera.business.patient.PatientManagerResult;
import info.ozkan.vipera.business.patient.PatientSearchFilter;
import info.ozkan.vipera.doctorviews.DoctorSessionBean;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Patient;
import info.ozkan.vipera.views.patient.PatientBrowseBean;

import java.util.List;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

/**
 * Hekim hasta arama ekranı (sadece kendi hastalarını arayabilir)
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorPatientBrowse")
@Scope("session")
public class DoctorPatientBrowseBean extends PatientBrowseBean {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DoctorPatientBrowseBean.class);

    @Override
    public void search() {
        final PatientSearchFilter filter = createFilterFromModel();
        final Doctor doctor = DoctorSessionBean.getDoctor();
        final PatientManagerResult result = patientFacade
                .search(filter, doctor);
        final List<Patient> list = result.getPatients();
        setResult(list);
        LOGGER.info("Found {} patients", list.size());
    }
}
