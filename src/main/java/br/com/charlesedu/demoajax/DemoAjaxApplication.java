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
		SocialMetaTag og = service.getOpenGraphByUrl("https://www.mobly.com.br/poltrona-luiza-madeira-macica-bege-e-nogueira-895520.html?origin=jetmobly&label=");

		System.out.println(og.toString());
	}
}
