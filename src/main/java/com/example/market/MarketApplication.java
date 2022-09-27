package com.example.market;

import com.example.market.models.*;
import com.example.market.repositories.ClientProductRepository;
import com.example.market.repositories.ClientRepository;
import com.example.market.repositories.ProductRepository;
import com.example.market.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class MarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
	}
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, ProductRepository productRepository, TransactionRepository transactionRepository, ClientProductRepository clientProductRepository){
		return (args) -> {

			Client client1 = new Client("Melba","Morel","melba@mindhub.com",passwordEncoder.encode("123"),0.00,true,true);
			Client client2 = new Client("Tom","Cris","tom@mindhub.com",passwordEncoder.encode("123"),1500.00,true,true);
			Client client3 = new Client("Carlos", "Gomez", "carlos@mindhub.com", passwordEncoder.encode("123"), 1000.00, true, true );
			Client client4= new Client("Fran","Tarifa","fran@mindhub.com",passwordEncoder.encode("123"),2500.00,true,true);
			Client client5 = new Client("John", "Smitt", "john@mindhub.com", passwordEncoder.encode("123"), 50000.00, true, true );

			Client admin1 = new Client("admin","admin","admin@admin.com","admin",0.0,true, true);


			Product product1 = new Product(client1, "Tyke#0945", "Our mission is to connect real estate investors, web3 experts, developers, entrepreneurs, and more to create the future of Digital Real Estate (any application of web3 and real estate).", 10.00, LocalDate.now(), true, "https://img.seadn.io/files/bc2e9c83d646299618bcada2da6d2aff.png?fit=max&w=600", true);
			Product product2 = new Product(client1, "Tyke#0896", "Our mission is to connect real estate investors, web3 experts, developers, entrepreneurs, and more to create the future of Digital Real Estate (any application of web3 and real estate).", 5.00, LocalDate.now(), true, "https://img.seadn.io/files/e647860d7ee3d4240c0fa12b390470f1.png?fit=max&w=600", true);
			Product product3 = new Product(client1, "Tyke#0785", "Our mission is to connect real estate investors, web3 experts, developers, entrepreneurs, and more to create the future of Digital Real Estate (any application of web3 and real estate).", 23.00, LocalDate.now(), true, "https://img.seadn.io/files/23b985cc3fad1830e7a3be6e290a72f1.png?fit=max&w=600", true);
			Product product4 = new Product(client1, "Tyke#0258", "Our mission is to connect real estate investors, web3 experts, developers, entrepreneurs, and more to create the future of Digital Real Estate (any application of web3 and real estate).", 17.00, LocalDate.now(), true, "https://img.seadn.io/files/9c0e7d11c967e766ee7cab805ca6e183.png?fit=max&w=600", true);
			Product product5 = new Product(client1, "Tyke#0369", "Our mission is to connect real estate investors, web3 experts, developers, entrepreneurs, and more to create the future of Digital Real Estate (any application of web3 and real estate).", 12.00, LocalDate.now(), true, "https://img.seadn.io/files/a5d65dad77e920ca65741076b1d538ed.png?fit=max&w=600", true);
			Product product6 = new Product(client1, "Tyke#9852", "Our mission is to connect real estate investors, web3 experts, developers, entrepreneurs, and more to create the future of Digital Real Estate (any application of web3 and real estate).", 18.00, LocalDate.now(), true, "https://img.seadn.io/files/09ade782b7f2831c62aa388183ce97d8.png?fit=max&w=600", true);
			Product product8 = new Product(client1, "Tyke#1254", "Our mission is to connect real estate investors, web3 experts, developers, entrepreneurs, and more to create the future of Digital Real Estate (any application of web3 and real estate).", 22.00, LocalDate.now(), true, "https://img.seadn.io/files/886cd06aa9770f2c3a798efee6e503c8.png?fit=max&w=600", true);
			Product product9 = new Product(client1, "Tyke#9512", "Our mission is to connect real estate investors, web3 experts, developers, entrepreneurs, and more to create the future of Digital Real Estate (any application of web3 and real estate).", 9.00, LocalDate.now(), true, "https://img.seadn.io/files/df3e20a1e5ab7cd593fda42d4718741a.png?fit=max&w=600", true);
			Product product10 = new Product(client1, "Tyke#3259", "Our mission is to connect real estate investors, web3 experts, developers, entrepreneurs, and more to create the future of Digital Real Estate (any application of web3 and real estate).", 16.00, LocalDate.now(), true, "https://cdn.discordapp.com/attachments/1016816395486503006/1021456614160613427/unknown.png", true);
			Product product11 = new Product(client1, "Tyke#8512", "Our mission is to connect real estate investors, web3 experts, developers, entrepreneurs, and more to create the future of Digital Real Estate (any application of web3 and real estate).", 6.00, LocalDate.now(), true, "https://img.seadn.io/files/2baaac11d40d092099d0577d38bb5e44.png?fit=max&w=600", true);


			Product tom1 = new Product(client2,"Tom #1506","Rigs is a generative collection built from 1,074 handcrafted works of art for the builders and creatives of cyberspace",10.0, LocalDate.now(),true,"https://i.seadn.io/gae/j6MLlmneJaZMSuTqSrhctw6uxgMBril1oxgw7Kso2A9ZS98c5n43kuTLh5c9U1HPIoTC_doWaXQOX5ekez5D5lPxXV4Okp0lP0_R?w=500&auto=format",true);
			Product tom2 = new Product(client2,"Tom #1507","Rigs is a generative collection built from 1,074 handcrafted works of art for the builders and creatives of cyberspace",15.0, LocalDate.now(),true,"https://i.seadn.io/gae/6rxvGd741FNx1ZXR2Q3Iyk-dLZVzZikxhhSerol5DmdHrKOtB5kvTPnuwN4w8kYxSc51bxi4uhfiuWnl-Du4uuIorVWqMv3i8R2wVZc?w=500&auto=format",true);
			Product tom3 = new Product(client2,"Tom #1508","Rigs is a generative collection built from 1,074 handcrafted works of art for the builders and creatives of cyberspace",45.0, LocalDate.now(),true,"https://i.seadn.io/gae/1TXC5liac58-61iAzZSNfjNOMGn_yEipn0QGzgI_P3S1JCQwOv-tBgYHZmRxWGToU15Yq46IsXtd5y0bMyRmOQ4q-5rCJjC-MNk1?w=500&auto=format",true);
			Product tom4 = new Product(client2,"Tom #1509","Rigs is a generative collection built from 1,074 handcrafted works of art for the builders and creatives of cyberspace",34.0, LocalDate.now(),true,"https://i.seadn.io/gae/1TXC5liac58-61iAzZSNfjNOMGn_yEipn0QGzgI_P3S1JCQwOv-tBgYHZmRxWGToU15Yq46IsXtd5y0bMyRmOQ4q-5rCJjC-MNk1?w=500&auto=format",true);
			Product tom5 = new Product(client2,"Tom #1600","Rigs is a generative collection built from 1,074 handcrafted works of art for the builders and creatives of cyberspace",23.0, LocalDate.now(),true,"https://i.seadn.io/gae/1TXC5liac58-61iAzZSNfjNOMGn_yEipn0QGzgI_P3S1JCQwOv-tBgYHZmRxWGToU15Yq46IsXtd5y0bMyRmOQ4q-5rCJjC-MNk1?w=500&auto=format",true);
			Product tom6 = new Product(client2,"Tom #1601","Rigs is a generative collection built from 1,074 handcrafted works of art for the builders and creatives of cyberspace",56.0, LocalDate.now(),true,"https://i.seadn.io/gae/-OyWfof8OWkYhhCjnZcQrEFK4-Eh_LLm_DmfXdXzIOaBCszn2cEkYCup3Lgq686kZuWcmC7HvjbvDjvKslWTOBK96PAwNvCDUwa3ybc?w=500&auto=format",true);
			Product tom7 = new Product(client2,"Tom #1602","Rigs is a generative collection built from 1,074 handcrafted works of art for the builders and creatives of cyberspace",34.0, LocalDate.now(),true,"https://i.seadn.io/gae/En-Xxdm8fzJR4vBqiEfOa3HvnxwGcKPadYQowTGujURSDxdEWbf1rT6ADB2adSTpXJMPw4CwQdKCgFi4UD5amXVQhxJOHeVNnsTVEg?w=500&auto=format",true);
			Product tom8 = new Product(client2,"Tom #1603","Rigs is a generative collection built from 1,074 handcrafted works of art for the builders and creatives of cyberspace",23.0, LocalDate.now(),true,"https://i.seadn.io/gae/lbmu_1SqL-izpOTA04EXVyhEtuJhVX1my4h5_mWAB6NbfYSR8WEz6oH6IMnbQs9_nSdfyJ8v93rl0ji7z2LaFqgGmhrMqsHkL6qmK8o?w=500&auto=format",true);
			Product tom9 = new Product(client2,"Tom #1604","Rigs is a generative collection built from 1,074 handcrafted works of art for the builders and creatives of cyberspace",56.0, LocalDate.now(),true,"https://i.seadn.io/gae/bXJolDhNxy3Gb-fwWSEjWvQQx3x2FH6ZUf_pDX9ZfCyWSjqanmajMCXgZX-Xa8xvEJnQWRTPKLprEnUvQPLDcAKAQDmrTGPzMvYExw?w=500&auto=format",true);
			Product tom10 = new Product(client2,"Tom #1605","Rigs is a generative collection built from 1,074 handcrafted works of art for the builders and creatives of cyberspace",12.0, LocalDate.now(),true,"https://i.seadn.io/gae/XWutiba7e1FgYhQxndt5QIIHvRI08IHsnIaL_AUyUmaWRllCMz-l9oTzQXgjNOMWp3f3GxIUHygolQaqiAQMlfZZOXCBq9zYQIjT?w=500&auto=format",true);


			Product productCarlos1 = new Product(client3, "ETERNALS #3831", "Death is the golden key that opens the palace of eternity ♰ A.D. XVI KAL. OCT. MMDCCLXXV A.U.C. ♰", 6.00, LocalDate.now(), true, "https://i.seadn.io/gae/Hc8Y7b3_tnoaVExo7A7EkFJmHzsfETPQYV4_uCN-PwE3IjzHH1cK0h15f5iOoVn8qPJDUg8XTiJxGs2L65g2AJzq2sb4SJ6DRGyfaA?auto=format&w=384", true);
			Product productCarlos2 = new Product(client3, "ETERNALS #1234", "Death is the golden key that opens the palace of eternity ♰ A.D. XVI KAL. OCT. MMDCCLXXV A.U.C. ♰", 12.00, LocalDate.now(), true, "https://i.seadn.io/gae/ZZx00DeuoQ5W0nQTvZQrVosPlZpYdL0GnvcV0NA2J2fvIwqU54AReqgccW4ulDY3pe5_wBDHsaHvuQPsYJS3yhijSX6WRqKj4tMV?auto=format&w=384", true);
			Product productCarlos3 = new Product(client3, "ETERNALS #8965", "Death is the golden key that opens the palace of eternity ♰ A.D. XVI KAL. OCT. MMDCCLXXV A.U.C. ♰", 13.00, LocalDate.now(), true, "https://i.seadn.io/gae/qv77JRmEjthihYV01XvUweZsIdx0hzYoEB5VVcy4koRekaaEFYCCl9hAFvb4Iepl64_hQorb4z36ky93a0OJH7DYp_COTN-X31ShlcA?auto=format&w=384", false);
			Product productCarlos4 = new Product(client3, "ETERNALS #5214", "Death is the golden key that opens the palace of eternity ♰ A.D. XVI KAL. OCT. MMDCCLXXV A.U.C. ♰", 17.00, LocalDate.now(), true, "https://i.seadn.io/gae/QE0dsDQ6QgJj4q2Y6Ap_FrKefVxwL7JuHs4vtKgUd0uBX7txhxRtZAFndQw4azqJSmLbaffql8gemJMhUGh-SJW_BArvPLtfoMtCxdA?auto=format&w=384", true);
			Product productCarlos5 = new Product(client3, "ETERNALS #1248", "Death is the golden key that opens the palace of eternity ♰ A.D. XVI KAL. OCT. MMDCCLXXV A.U.C. ♰", 22.00, LocalDate.now(), true, "https://i.seadn.io/gae/aOM1vUxfXnGi4UmiLbzE1OxihJKNi5sM36SSvtH7GA-XhTX6MZA6orkDDm_gLNB2dHUfxrZP86_wHED-eKq6cY8KswKfPjt_mulChg?auto=format&w=384", true);
			Product productCarlos6 = new Product(client3, "ETERNALS #8521", "Death is the golden key that opens the palace of eternity ♰ A.D. XVI KAL. OCT. MMDCCLXXV A.U.C. ♰", 19.00, LocalDate.now(), true, "https://i.seadn.io/gae/hds7_umRngxZKGIyNT070p5hvV5Ari1dXlBaKJLr2Y0w22jYaL6UfYkd89qqpls7Ve3YFIlBIYxiK5HpGMijjyRUpbLw2eubYp5gFE0?auto=format&w=384", true);
			Product productCarlos7 = new Product(client3, "ETERNALS #9512", "Death is the golden key that opens the palace of eternity ♰ A.D. XVI KAL. OCT. MMDCCLXXV A.U.C. ♰", 18.00, LocalDate.now(), true, "https://i.seadn.io/gae/e5JAJvVJe2MFNriVSYIxEbDdyGB7snUF3fu_0Yoep91FaxXhZ9rWgpHYARYmUqijYzhsM1Vi4LsLe3WzMZDbPgtQtdd78tTYEsK9xj4?auto=format&w=384", true);
			Product productCarlos8 = new Product(client3, "ETERNALS #3574", "Death is the golden key that opens the palace of eternity ♰ A.D. XVI KAL. OCT. MMDCCLXXV A.U.C. ♰", 20.00, LocalDate.now(), true, "https://i.seadn.io/gae/R0VTS6BNPlDGX3ZMhIzWg1XuxG-e42rwIQf5p9yvAbqahGHEEyaj1_034W806kYNZF2Ss_kEKUxssRSxX5TKPamHtRKaE8qS9QpL38s?auto=format&w=384", true);
			Product productCarlos9 = new Product(client3, "ETERNALS #5698", "Death is the golden key that opens the palace of eternity ♰ A.D. XVI KAL. OCT. MMDCCLXXV A.U.C. ♰", 9.00, LocalDate.now(), true, "https://i.seadn.io/gae/FAp3N2LO4pm7QkhK7782KIGcm2nFnK04UzFObDaW3wnSCq_p6jJx4AzSlYn4P2pwY2c6fAR8RmN0LGygB6OC2LW_wikKUzUGgwgVp2A?auto=format&w=384", true);


			Product fran1 = new Product(client4,"Fran #1100","Rigs is a generative collection built from 1,074 handcrafted works of art for the builders and creatives of cyberspace",12.0, LocalDate.now(),true,"https://img.seadn.io/files/21a9a5a01556dcd42bec79d5abca5f30.png?fit=max&w=600",true);
			Product fran2 = new Product(client4,"Fran #1101","Rigs is a generative collection built from 1,074 handcrafted works of art for the builders and creatives of cyberspace",45.0, LocalDate.now(),true,"https://img.seadn.io/files/262b8bc74e4c129b9b46874df2519a94.png?fit=max&w=600",true);
			Product fran3 = new Product(client4,"Fran #1102","Rigs is a generative collection built from 1,074 handcrafted works of art for the builders and creatives of cyberspace",54.0, LocalDate.now(),true,"https://cdn.discordapp.com/attachments/1016816395486503006/1021458896616960113/unknown.png",true);
			Product fran4 = new Product(client4,"Fran #1103","Rigs is a generative collection built from 1,074 handcrafted works of art for the builders and creatives of cyberspace",50.0, LocalDate.now(),true,"https://cdn.discordapp.com/attachments/1016816395486503006/1021458896616960113/unknown.png",true);
			Product fran5 = new Product(client4,"Fran #1104","Rigs is a generative collection built from 1,074 handcrafted works of art for the builders and creatives of cyberspace",65.0, LocalDate.now(),true,"https://img.seadn.io/files/03bb8a027a05075e8a82b7831f490c8a.png?fit=max&w=600",true);
			Product fran6 = new Product(client4,"Fran #1105","Rigs is a generative collection built from 1,074 handcrafted works of art for the builders and creatives of cyberspace",45.0, LocalDate.now(),true,"https://img.seadn.io/files/1e6baafbb54f9860b8203b4101a0028f.png?fit=max&w=600",true);
			Product fran7 = new Product(client4,"Fran #1106","Rigs is a generative collection built from 1,074 handcrafted works of art for the builders and creatives of cyberspace",65.0, LocalDate.now(),true,"https://img.seadn.io/files/182824162b739dfcc8797550d693a94a.png?fit=max&w=600",true);
			Product fran8 = new Product(client4,"Fran #1107","Rigs is a generative collection built from 1,074 handcrafted works of art for the builders and creatives of cyberspace",23.0, LocalDate.now(),true,"https://img.seadn.io/files/c4b32d474ba39af697c115d7783a2261.png?fit=max&w=600",true);
			Product fran9 = new Product(client4,"Fran #1108","Rigs is a generative collection built from 1,074 handcrafted works of art for the builders and creatives of cyberspace",45.0, LocalDate.now(),true,"https://img.seadn.io/files/12c1a0b216a543964dc0bb4028891063.png?fit=max&w=600",true);
			Product fran10 = new Product(client4,"Fran #1110","Rigs is a generative collection built from 1,074 handcrafted works of art for the builders and creatives of cyberspace",23.0, LocalDate.now(),true,"https://img.seadn.io/files/3f0d84589f58f12bd16d3a21d23738ae.png?fit=max&w=600",true);

			Product productJohn1 = new Product(client5, "dog #111", "rektguy is a collection of images of dog", 23.00, LocalDate.now(), true, "https://img.seadn.io/files/8d7eb1269860cb15542c791fd67aca16.png?fit=max&w=1000", true);
			Product productJohn2 = new Product(client5, "dog #112", "rektguy is a collection of images of dog", 12.00, LocalDate.now(), true, "https://img.seadn.io/files/5a6add642e81a37af596c8d996edba53.png?fit=max&w=1000", true);
			Product productJohn3 = new Product(client5, "dog #113", "rektguy is a collection of images of dog", 18.00, LocalDate.now(), true, "https://img.seadn.io/files/96508f28ef6111e412dd4c42595443d5.png?fit=max&w=1000", true);
			Product productJohn4 = new Product(client5, "dog #114", "rektguy is a collection of images of dog", 17.00, LocalDate.now(), true, "https://img.seadn.io/files/0b0f7278cc5b67e02eb06a52348e155e.png?fit=max&w=1000", true);
			Product productJohn5 = new Product(client5, "dog #115", "rektguy is a collection of images of dog", 15.00, LocalDate.now(), true, "https://img.seadn.io/files/c77c1dc01f55b6f2e81342b8039dffc4.png?fit=max&w=1000", true);
			Product productJohn6 = new Product(client5, "dog #116", "rektguy is a collection of images of dog", 9.50, LocalDate.now(), true, "https://img.seadn.io/files/062b1e40c90658c6bb40d85d61395935.png?fit=max&w=1000", true);
			Product productJohn7 = new Product(client5, "dog #117", "rektguy is a collection of images of dog", 11.00, LocalDate.now(), true, "https://img.seadn.io/files/c8d2c66d8737571e8ca3620e5a02df90.png?fit=max&w=1000", true);
			Product productJohn8 = new Product(client5, "dog #118", "rektguy is a collection of images of dog", 21.00, LocalDate.now(), true, "https://img.seadn.io/files/d97558417628fac7bf0e0eaebe738999.png?fit=max&w=1000", true);
			Product productJohn9 = new Product(client5, "dog #119", "rektguy is a collection of images of dog", 19.00, LocalDate.now(), true, "https://img.seadn.io/files/8f95504486c23cfca2aa3ee980759197.png?fit=max&w=1000", true);
			Product productJohn10 = new Product(client5, "dog #120", "rektguy is a collection of images of dog", 50.0, LocalDate.now(), true, "https://img.seadn.io/files/c18adac40f6ccf80b99c9c4aa740d0f9.png?fit=max&w=1000", true);

			Transaction transaction1 = new Transaction(client1, TransactionType.SELL,50.00,"gracias riki", LocalDateTime.now());


			clientRepository.save(client1);
			clientRepository.save(client2);
			clientRepository.save(client3);
			clientRepository.save(client4);
			clientRepository.save(client5);

			clientRepository.save(admin1);

			productRepository.save(product1);
			productRepository.save(product2);
			productRepository.save(product3);
			productRepository.save(product4);
			productRepository.save(product5);
			productRepository.save(product6);
			productRepository.save(product8);
			productRepository.save(product9);
			productRepository.save(product10);
			productRepository.save(product11);

			productRepository.save(tom1);
			productRepository.save(tom2);
			productRepository.save(tom3);
			productRepository.save(tom4);
			productRepository.save(tom5);
			productRepository.save(tom6);
			productRepository.save(tom7);
			productRepository.save(tom8);
			productRepository.save(tom9);
			productRepository.save(tom10);

			productRepository.save(productCarlos1);
			productRepository.save(productCarlos2);
			productRepository.save(productCarlos3);
			productRepository.save(productCarlos4);
			productRepository.save(productCarlos5);
			productRepository.save(productCarlos6);
			productRepository.save(productCarlos7);
			productRepository.save(productCarlos8);
			productRepository.save(productCarlos9);

			productRepository.save(fran1);
			productRepository.save(fran2);
			productRepository.save(fran3);
			productRepository.save(fran4);
			productRepository.save(fran5);
			productRepository.save(fran6);
			productRepository.save(fran7);
			productRepository.save(fran8);
			productRepository.save(fran9);
			productRepository.save(fran10);

			productRepository.save(productJohn1);
			productRepository.save(productJohn2);
			productRepository.save(productJohn3);
			productRepository.save(productJohn4);
			productRepository.save(productJohn5);
			productRepository.save(productJohn6);
			productRepository.save(productJohn7);
			productRepository.save(productJohn8);
			productRepository.save(productJohn9);
			productRepository.save(productJohn10);

			transactionRepository.save(transaction1);

		};

	}

}
