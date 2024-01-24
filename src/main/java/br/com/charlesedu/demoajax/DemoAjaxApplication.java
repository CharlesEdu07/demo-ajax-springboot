package br.com.charlesedu.demoajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.charlesedu.demoajax.domain.SocialMetaTag;
import br.com.charlesedu.demoajax.service.SocialMetaTagService;

@SpringBootApplication
public class DemoAjaxApplication implements CommandLineRunner {

	@Autowired
	SocialMetaTagService service;

	public static void main(String[] args) {
		SpringApplication.run(DemoAjaxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SocialMetaTag tag = service.getSocialMetaTagByUrl("https://www.gazin.com.br/produto/7634/tablet-vaio-tl10-104-128gb-8gb-octa-core-2ghz-android-com-teclado-magnetico?cor=preto&voltagem=bivolt&seller_id=6");

		System.out.println(tag.toString());
	}
}
