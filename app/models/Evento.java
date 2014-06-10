package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.URL;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.data.validation.ValidationError;
import validators.annotations.FromNow;

@Entity
public class Evento {

	@Id
	@GeneratedValue
	@Getter @Setter
	private Integer id;

	@Email
	@Getter @Setter
	private String emailParaContato;

	@Column(length = 2)
	@Enumerated(EnumType.STRING)
	@Getter @Setter
	private Estado estado;

	@Required
	@Column(columnDefinition = "text")
	@Getter @Setter
	private String descricao;

	@URL
	@Getter @Setter
	private String site;

	@Getter @Setter
	private String twitter;

	@Required
	@Getter @Setter
	private String nome;

	@FromNow
	@Getter @Setter
	private Calendar dataDeInicio;

	@Getter @Setter
	private Calendar dataDeFim;
	
	@Getter @Setter
	private String caminhoImagem;

	public List<ValidationError> validate() {
		ArrayList<ValidationError> errors = new ArrayList<ValidationError>();
		if(dataDeFim == null) {
			this.dataDeFim = (Calendar) dataDeInicio.clone();
			return null;
		}
		if(!dataDeFim.after(dataDeInicio)) {
			errors.add(new ValidationError("dataDeFim", "O fim deve ser após o início"));
		}
		return errors.isEmpty() ? null : errors;
	}

}
