package org.opendevup;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.opendevup.dao.EtudiantRepository;
import org.opendevup.entities.Etudiant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class TpSpringMvcApplication {

	public static void main(String[] args) throws ParseException {
		ApplicationContext ctx = SpringApplication.run(TpSpringMvcApplication.class, args);
		EtudiantRepository etudiantRepository=ctx.getBean(EtudiantRepository.class);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		/*etudiantRepository.save(new Etudiant("amine1",  df.parse("1992-10-10"),"Amine1gmail.com", "amine.jpg"));
		etudiantRepository.save(new Etudiant("amine2",  df.parse("1992-10-10"),"Amine2gmail.com", "amine.jpg"));
		etudiantRepository.save(new Etudiant("amine3", df.parse("1992-10-10"), "Amine3gmail.com", "amine.jpg"));
		etudiantRepository.save(new Etudiant("amine4",  df.parse("1992-10-10"),"Amine4gmail.com", "amine.jpg"));
		etudiantRepository.save(new Etudiant("amine5", df.parse("1992-10-10"), "Amine6gmail.com", "amine.jpg"));
		etudiantRepository.save(new Etudiant("amine6",  df.parse("1992-10-10"),"Amine5gmail.com", "amine.jpg"));
		etudiantRepository.save(new Etudiant("amine7",  df.parse("1992-10-10"), "Amine9gmail.com","amine.jpg"));
		etudiantRepository.save(new Etudiant("bbbbamine1",  df.parse("1992-10-10"),"Amineuugmail.com", "amine.jpg"));
		etudiantRepository.save(new Etudiant("bbbbamine2",  df.parse("1992-10-10"),"Aminttegmail.com", "amine.jpg"));
		etudiantRepository.save(new Etudiant("bbbamine3", df.parse("1992-10-10"), "Aminehhgmail.com", "amine.jpg"));
		etudiantRepository.save(new Etudiant("bbbbamine4",  df.parse("1992-10-10"),"Aminegggmail.com", "amine.jpg"));
		etudiantRepository.save(new Etudiant("zzzamine5", df.parse("1992-10-10"), "Amineffgmail.com", "amine.jpg"));
		etudiantRepository.save(new Etudiant("zzzamine6",  df.parse("1992-10-10"),"Aminegmail.com", "amine.jpg"));
		etudiantRepository.save(new Etudiant("hhhhamine7",  df.parse("1992-10-10"), "Amddinegmail.com","amine.jpg"));
		
		*/
		
		/*Page<Etudiant> etds = etudiantRepository.chercherEtudiants("%i%",new PageRequest(0, 5));
		etds.forEach(e->System.out.println(e.getNom()));*/
		Page<Etudiant> etds = etudiantRepository.findAll(new PageRequest(0, 5));
		etds.forEach(e->System.out.println(e.getEmail()));
	}
}
