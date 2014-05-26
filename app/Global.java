
import java.util.Calendar;

import play.Application;
import play.GlobalSettings;
import play.data.format.Formatters;
import formatters.Html5CalendarFormatter;

public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		Formatters.register(Calendar.class, new Html5CalendarFormatter());
	}
}
