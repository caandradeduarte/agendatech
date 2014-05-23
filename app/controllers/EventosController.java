package controllers;

import com.avaje.ebean.Ebean;

import models.Evento;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class EventosController extends Controller {
	
	private static Form<Evento> eventoForm = Form.form(Evento.class);

	public static Result novo() {
		return ok(views.html.eventos.novo.render());
	}
	
	public static Result cria() {
		Evento evento = eventoForm.bindFromRequest().get();
		Ebean.save(evento);
		return TODO;
	}
	
}
