package controllers.admin;

import java.util.List;

import models.Evento;
import play.mvc.Controller;
import play.mvc.Result;

import com.avaje.ebean.Ebean;

import daos.Eventos;

public class TodosEventosController extends Controller {

	public static Result todos() {
		List<Evento> naoAprovados = Eventos.aprovados(false);
		List<Evento> aprovados = Eventos.aprovados(true);
		
		return ok(views.html.eventos.admin.lista.render(naoAprovados, aprovados));
	}
	
	public static Result aprova(Integer id) {
		Evento evento = Ebean.find(Evento.class, id);
		evento.setAprovado(true);
		Ebean.update(evento);
		return redirect(controllers.admin.routes.TodosEventosController.todos());
	}
}
