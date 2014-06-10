package controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import models.Evento;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Http.RequestBody;
import play.mvc.Result;

import com.avaje.ebean.Ebean;

public class EventosController extends Controller {
	
	private static Form<Evento> eventoForm = Form.form(Evento.class);

	public static Result novo() {
		return ok(views.html.eventos.novo.render(eventoForm));
	}
	
	public static Result cria() throws IOException {
		Form<Evento> formFromRequest = eventoForm.bindFromRequest();
		if(formFromRequest.hasErrors()) {
			return badRequest(views.html.eventos.novo.render(formFromRequest));
		}
		File destino = gravaDestaque();
		Evento evento = formFromRequest.get();
		evento.setCaminhoImagem(destino.getName());
		try {
			Ebean.save(evento);
		} catch (RuntimeException e) {
			destino.delete();
		}
		return redirect(routes.EventosController.lista());
	}
	
	private static File gravaDestaque() throws IOException {
		RequestBody requestBody = request().body();
		MultipartFormData body = requestBody.asMultipartFormData();
		FilePart filePart = body.getFile("destaque");
		File destaque = filePart.getFile();
		File destino = arquivoDeDestino(filePart);
		FileUtils.moveFile(destaque, destino);
		return destino;
	}
	
	private static File arquivoDeDestino(FilePart destaque) {
		return new File("public/images/destaques", System.currentTimeMillis() + "_" + destaque.getFilename());
	}

	public static Result lista() {
		List<Evento> eventos = Ebean.find(Evento.class).findList();
		return ok(views.html.eventos.lista.render(eventos));
	}
	
}
