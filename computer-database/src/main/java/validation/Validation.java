package validation;

import java.time.LocalDate;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.UserDto;
import exceptions.After1970Exception;
import exceptions.Before2038Exception;
import exceptions.DateIntroDiscoException;
import exceptions.DiscoMustIfIntroException;
import mapper.MapperDates;

import model.Computer;
import persistence.UserRepository;

@Component
public class Validation {

	@Autowired
	MapperDates mapperDates;
	
	@Autowired
	UserRepository userRepo;

	public void createComputer(Computer computer) throws DiscoMustIfIntroException, After1970Exception, Before2038Exception, DateIntroDiscoException  {
		LocalDate intro = computer.getIntroduced();
		LocalDate disco = computer.getDiscontinued();
		this.discoMustIfIntro(intro, disco);

		for (LocalDate date : Arrays.asList(intro, disco)) {
			if (date != null) {
				this.after1970before2038(date);
				this.after1970before2038(date);
			}
		}
		if (intro != null && disco != null) {
			this.dateIntroDisco(intro, disco);
		}
	}

	public void discoMustIfIntro(LocalDate intro, LocalDate disco) throws DiscoMustIfIntroException  {
		if (disco != null && intro == null) {
			throw new DiscoMustIfIntroException();
		}
	}

	public void after1970before2038(LocalDate date) throws After1970Exception, Before2038Exception  {
		LocalDate after1970 = LocalDate.parse("1970-01-01");
		LocalDate before2038 = LocalDate.parse("2038-01-19");
		if (date.compareTo(after1970) <= 0) {
			throw new After1970Exception();
		}
		if (date.compareTo(before2038) >= 0) {
			throw new Before2038Exception();
		}

	}

	public void dateIntroDisco(LocalDate intro, LocalDate disco) throws DateIntroDiscoException  {
		if (intro.compareTo(disco) > 0) {
			throw new DateIntroDiscoException();
		}
	}

	

}
