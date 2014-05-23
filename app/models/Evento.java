package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Evento {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String emailParaContato;
	
	@Column(length=2)
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	@Column(columnDefinition="text")
	private String descricao;
	
	private String site;
	
	private String twitter;
	
	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmailParaContato() {
		return emailParaContato;
	}

	public void setEmailParaContato(String emailParaContato) {
		this.emailParaContato = emailParaContato;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
