package appointment.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import appointment.Appointment;
import appointment.service.AppointmentService;

public class AppointmentListController extends ParameterizableViewController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		AppointmentService svc = (AppointmentService)getApplicationContext().getBean("appointmentService");
		Map<String, Object> model = new HashMap<String, Object>();
		List<Appointment> appointments = svc.getAppointments();
		model.put("appointments", appointments);
		return new ModelAndView(getViewName(), model);
		
		//return super.handleRequestInternal(request, response);
	}

}
