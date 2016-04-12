package br.com.battycode.endpoint;

import br.com.battycode.dto.Esfera;
import br.com.battycode.dto.Licitacao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

@SpringBootApplication
@RestController
public class SaelgiEndpoint {

	public static void main(String[] args) {
		SpringApplication.run(SaelgiEndpoint.class, args);
	}

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	@RequestMapping("/resource")
	public Map<String, Object> home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Mapa licitatorio");
		return model;
	}
	
	@RequestMapping(value = "/licitacoes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Licitacao>> licitacoes() {
		List<Licitacao> licitacoes = new ArrayList<>();
		licitacoes.add(new Licitacao("Secretaria de Segurança do Rio Grande do Sul", "SC", Esfera.Estadual, new Date(), new Date(), new Date()));
		licitacoes.add(new Licitacao("Guarnição do 18º Batalhão da Marinha", "RJ", Esfera.Federal, new Date(), new Date(), new Date()));
		return new ResponseEntity<>(licitacoes, HttpStatus.OK);
	}

	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.httpBasic().and().authorizeRequests().antMatchers("/index.html", "/home.html", "/login.html", "/licitacoes.html", "/")
					.permitAll().anyRequest().authenticated().and().csrf().csrfTokenRepository(csrfTokenRepository())
					.and().addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
		}

		private Filter csrfHeaderFilter() {
			return new OncePerRequestFilter() {
				@Override
				protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
						FilterChain filterChain) throws ServletException, IOException {
					CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
					if (csrf != null) {
						Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
						String token = csrf.getToken();
						if (cookie == null || token != null && !token.equals(cookie.getValue())) {
							cookie = new Cookie("XSRF-TOKEN", token);
							cookie.setPath("/");
							response.addCookie(cookie);
						}
					}
					filterChain.doFilter(request, response);
				}
			};
		}

		private CsrfTokenRepository csrfTokenRepository() {
			HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
			repository.setHeaderName("X-XSRF-TOKEN");
			return repository;
		}

		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/img/**");
		}
		
	}
}
