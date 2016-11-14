package org.opendevup.web;

import static org.mockito.Mockito.RETURNS_DEFAULTS;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.opendevup.dao.EtudiantRepository;
import org.opendevup.entities.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="/Etudiant")
public class EtudiantController {
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Value("${dir.images}")
	private String imageDir;
	@RequestMapping(value="/Index")
	public String Index(Model model,
			@RequestParam(name="page",defaultValue="0")int p,
			@RequestParam(name="motCle",defaultValue="") String mc)
	{
		Page<Etudiant> pageEtudiants= etudiantRepository
				.chercherEtudiants("%"+mc+"%",new PageRequest(p, 5)); 
		int pageCount=pageEtudiants.getTotalPages();
		int[] pages =new int [pageCount];
		for(int i=0;i<pageCount;i++) pages[i]=i;
		model.addAttribute("pages", pages);
		model.addAttribute("PageEtudiant", pageEtudiants);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		return "etudiant";
	}
	@RequestMapping(value="/Form",method=RequestMethod.GET)
	public String FormEtudiant(Model model)
	{
		model.addAttribute("etudiant",new Etudiant());
		return "FormEtudiant";
	}
	@RequestMapping(value="/SaveEtudiant",method=RequestMethod.POST)
	public String save(@Valid Etudiant et,
			BindingResult bindingResult,
			//utiliser une type file pour le picture "la photo"
			@RequestParam(name="picture") MultipartFile file) throws Exception
	{   
		if(bindingResult.hasErrors())
			{
		return "FormEtudiant";
			}
	   if(file.isEmpty()){
		   //ajouter URL chemain dans la base 
		   et.setPhoto(file.getOriginalFilename());
		   //ajout de la photo dans le dossier
		  System.out.println("-----------------------");
		   file.transferTo(
		   new File(imageDir+
		   file.getOriginalFilename()));
	   }
		etudiantRepository.save(et);
		return "redirect:Index";
	}

}
