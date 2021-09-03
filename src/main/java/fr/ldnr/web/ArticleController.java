package fr.ldnr.web;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.ldnr.dao.ArticleRepository;
import fr.ldnr.entities.Article;

@Controller
public class ArticleController {
	@Autowired
	ArticleRepository articleRepository;
	
	@GetMapping("/index")			 
	public String index(Model model, @RequestParam(name="page" , defaultValue = "0") int page, 
									 @RequestParam(name="keyword" , defaultValue = "") String kw) {	
		Page<Article> articles = articleRepository.findByDescriptionContains(kw , PageRequest.of(page, 5));	

		model.addAttribute("listArticle",articles.getContent());	//pour récupérer sous forme de liste la page pointée		
		
		//pour afficher des liens de pagination permettant à l'utilisateur de passer d'une page à l'autre, il faut :
		//- récupérer le nombre total de pages
		//- l'injecter dans le model sous forme de tableau d'entier
		//- sur la partie html il suffira de boucler sur ce tableau pour afficher toutes les pages
		model.addAttribute("pages", new int[articles.getTotalPages()]);
		
		//s'agissant de l'activation des liens de navigation, il faut transmettre à la vue la page courante
		//thymeleaf pourra delors vérifier si la page courante est égal à l'index de la page active
		model.addAttribute("currentPage",page);
		
		//afin de garder afficher le mot clé dans le formulaire de recherche une fois l'action validée, 
		//il faut le transmettre à la vue via le modèle
		model.addAttribute("keyword",kw);
		
		return "articles";	
	}
	
	@GetMapping("/delete")		//on peut ne pas préciser le paramètre de la requete, il va rechercher les variables correspondantes
	public String delete(Long id, int page, String keyword) {
		articleRepository.deleteById(id);		
		return "redirect:/index?page="+page+"&keyword="+keyword;
	}
	
	@GetMapping("/edit")
	public String edit(Long id, Model model) {
		Article article = articleRepository.getById(id);
		model.addAttribute("article", article);
		return "edit";
	}
	
	@GetMapping("/article")		
	public String article(Model model) {
		model.addAttribute("article" , new Article());		//injection d'un article par défaut dans le formualire de la vue article
		return "article";
	}
	
	@PostMapping("/save")		
	public String save(Model model, @Valid Article article , BindingResult bindingResult) {		
		if(bindingResult.hasErrors())	return "article";	
		// s'il n'y a pas de saisie d'un champ selon certains critères, pas d'insertion en base
		articleRepository.save(article);
		return "redirect:/index";
	}
}
