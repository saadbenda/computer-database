package validation;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mapper.MapperDates;

import model.Computer;

@Component
public class Validation {

	@Autowired
	MapperDates mapperDates;

	public void createComputer(Computer computer) throws Exception {
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

	public void discoMustIfIntro(LocalDate intro, LocalDate disco) throws Exception {
		if (disco != null && intro == null) {
			throw new Exception("if discontinued date present there must be an introduced date");
		}
	}

	public void after1970before2038(LocalDate date) throws Exception {
		LocalDate after1970 = LocalDate.parse("1970-01-01");
		LocalDate before2038 = LocalDate.parse("2038-01-19");
		if (date.compareTo(after1970) <= 0) {
			throw new Exception("the date must be strictly greater than 1970-01-01");
		}
		if (date.compareTo(before2038) >= 0) {
			throw new Exception("the date must be strictly less than 2038-01-19");
		}

	}

	public void dateIntroDisco(LocalDate intro, LocalDate disco) throws Exception {
		if (intro.compareTo(disco) > 0) {
			throw new Exception("introdudction date must be less than or equal to the discontinued date");
		}
	}

}
