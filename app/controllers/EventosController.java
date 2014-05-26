package controllers;

import java.util.List;

import models.Evento;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import com.avaje.ebean.Ebean;

public class EventosController extends Controller {
	
	private static Form<Evento> eventoForm = Form.form(Evento.class);

	public static Result novo() {
		return ok(views.html.eventos.novo.render(eventoForm));
	}
	
	public static Result cria() {
		Form<Evento> formFromRequest = eventoForm.bindFromRequest();
		if(formFromRequest.hasErrors()) {
			return badRequest(views.html.eventos.novo.render(formFromRequest));
		}
		Evento evento = formFromRequest.get();
		Ebean.save(evento);
		return redirect(routes.EventosController.lista());
	}
	
	public static Result lista() {
		List<Evento> eventos = Ebean.find(Evento.class).findList();
		return ok(views.html.eventos.lista.render(eventos));
	}
	
}
