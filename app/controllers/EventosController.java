package controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import models.Evento;
import play.cache.Cached;
import play.data.Form;
import play.libs.F.Function;
import play.libs.F.Function0;
import play.libs.F.Promise;
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
	
	public static Promise<Result> cria() throws IOException {
		Form<Evento> formFromRequest = eventoForm.bindFromRequest();
		
		if(formFromRequest.hasErrors()) {
			return Promise.promise(new Function0<Result>() {

				@Override
				public Result apply() throws Throwable {
					return badRequest(views.html.eventos.novo.render(formFromRequest));
				}
			});
		}
		
		File destino = gravaDestaque();
		Evento evento = formFromRequest.get();
		evento.setCaminhoImagem(destino.getName());
		
		try {
			Ebean.save(evento);
		} catch (RuntimeException e) {
			destino.delete();
		}
		
		Promise<Void> enviandoEmails = Promise.promise(new Function0<Void>() {

			@Override
			public Void apply() throws Throwable {
				ControladorDeEmails.informaNovo(evento);
				return null;
			}
		});
		
		Promise<Result> result = enviandoEmails.map(new Function<Void, Result>() {

			@Override
			public Result apply(Void arg0) throws Throwable {
				return redirect(routes.EventosController.lista());
			}
		});

		return result;
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

	@Cached(key="home",duration=3600)
	public static Result lista() {
		List<Evento> eventos = Ebean.find(Evento.class).findList();
		return ok(views.html.eventos.lista.render(eventos));
	}
	
}
