package daos;

import java.util.List;

import com.avaje.ebean.Ebean;

import models.Evento;

public class Eventos {

	public static List<Evento> aprovados(boolean situacao) {
		return Ebean.find(Evento.class).where().eq("aprovado", false).findList();
	}
	
}
