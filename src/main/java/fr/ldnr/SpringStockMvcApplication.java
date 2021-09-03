package fr.ldnr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.ldnr.dao.ArticleRepository;
import fr.ldnr.entities.Article;

@SpringBootApplication
public class SpringStockMvcApplication implements CommandLineRunner {
	@Autowired
	ArticleRepository articleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringStockMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
//		articleRepository.save(new Article(null,"Samsung S8",250));
//		articleRepository.save(new Article(null,"Samsung S9",300));
//		articleRepository.save(new Article(null,"Iphone 10",500));
//		
//		articleRepository.save(new Article(null,"Xiaomi MI11",100));
//		articleRepository.save(new Article(null,"OnePlus 9 Pro",200));
//		articleRepository.save(new Article(null,"Google Pixel 5",350));
//		articleRepository.save(new Article(null,"Poco F3",150));
//		
//		articleRepository.findAll().forEach(a -> System.out.println(a));
			
	}
}
