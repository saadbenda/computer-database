package ui;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.Company;
import model.Computer;
import service.Service;
import validation.Validation;

public class Cli {
	public static Scanner scan;
	/*
	public Cli() {
		
		
		
		
		Service service=new Service();
		Validation validation=new Validation();
		int answer;
		boolean finished=false;
		scan = new Scanner(System.in);
		//while (!finished) {
		System.out.println("Bonjour, que voulez vous faire ?\n1. afficher la liste des ordinateurs\n2. afficher la liste des entreprises\n3. ajouter un ordinateur\n4. éditer un ordinateur\n0. quitter");
		answer=Integer.parseInt(scan.nextLine());
		validation.validationChoice(answer);
		switch(answer) { 
		case 4://add a computer
			System.out.println("quel est le nom ");
			String name=scan.nextLine();
			validation.validationName(name);
			System.out.println("quel est la date d'introduction sous la forme YYYY-MM-DD (sinon null)");		
			String i = scan.nextLine();
			LocalDate intro=validation.validationDateFormat(intro);
			//LocalDate i=service.fromStringToLocalDate(intro);
			System.out.println("quel est la date de disparition sous la forme YYYY-MM-DD (sinon null)");		
			String d = scan.nextLine();
			LocalDate disco=validation.validationDateFormat(disco);
			//validation.validationDiscontinued(disco);
			//LocalDate d=service.fromStringToLocalDate(intro);
			//validation.validationIntroDisco(i, d);
			System.out.println("tapez l'id de l'entreprise");
			String company = scan.nextLine();
			long company_id=validation.validationCompany(company);
			Computer cp=service.createComputer(name, i, disco, company1);
			//service.display(cp);
			break;
		case 6://update a computer
			System.out.println("entrez le nom d'un ordinateur");
			break;
		case 5:
			Computer computer;
			System.out.println("entrez le nom de l'ordinateur");
			name=scan.nextLine();
			validation.validationName(name);
			ArrayList<Computer> computers=validation.existingName(name);
			if((computers.size()>2)) {
				System.out.println("plusieurs ordinateurs avec ce nom existent, lequel voulez vous ? (écrivez l'id)");
				System.out.println(computers.toString());
				String computer_id = scan.nextLine();
				//Optional<Computer> comp =computers.stream().filter(c->Long.valueOf(c.getId()).equals(Long.parseLong(computer_id))).findFirst();
				//computers.set(0, comp.get());
			}
			computer=computers.get(0);
			boolean finishedUpdate=false;
			while(!finishedUpdate) {
			System.out.println("que voulez vous modifier ?\n1. le nom\n2. la date d'introduction\n3. la date de disparition \n4. le nom de l'entreprise\n0. quitter");
			int ans = Integer.parseInt(scan.next());
			String date1 = null;
			
			
				switch(ans) {
					case 1:
						System.out.println("veuillez entrer le nouveau nom");
						String str=scan.next();
						validation.validationName(str);
						service.updateComputerName(str,computer);
						break;
					case 2:
						System.out.println("veuillez entrer la nouvelle date d'introduction sous la forme YYYY-MM-DD");
						date1=scan.next();
						validation.validationDateFormat(date1);
						service.updateComputerIntro(date1,computer);
						break;
					case 3:
						System.out.println("veuillez entrer la nouvelle date de disparition sous la forme YYYY-MM-DD");
						String date2=scan.next();
						validation.validationDateFormat(date2);
						validation.validationIntroDisco(date1,date2);
						service.updateComputerDisco(date2,computer);						
						break;
					case 4:
						System.out.println("veuillez entrer le nouveau nom de l'entreprise");
						str=scan.next();
						validation.validationCompany(str);
						service.updateComputerCompany(str, computer);
						break;
					case 0:
						finishedUpdate=true;
						break;
					default:
						break;
					}
			}
			break;
		case 0://quit
			service.quit();
			finished=true;
		default:
			break;
		}
		*/
		}

