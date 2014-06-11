package controllers;

import models.Evento;
import play.api.templates.Html;

import com.typesafe.plugin.MailerAPI;
import com.typesafe.plugin.MailerPlugin;

public class ControladorDeEmails {

	public static void informaNovo(Evento evento) {
		MailerAPI mail = play.Play.application().plugin(MailerPlugin.class).email();
		mail.setSubject("mailer").addFrom("cadastros@agendatech.com.br").addRecipient("destino@gmail.com");
		Html render = views.html.email.novo.render();
		mail.sendHtml(render.body());
	}
	
}
