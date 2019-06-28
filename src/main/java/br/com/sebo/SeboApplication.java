package br.com.sebo;

import br.com.sebo.pojo.Livro;
import br.com.sebo.pojo.Role;
import br.com.sebo.pojo.Usuario;
import br.com.sebo.repository.LivroRepository;
import br.com.sebo.repository.RoleRepository;
import br.com.sebo.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class SeboApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeboApplication.class, args);
    }

    @Bean
    CommandLineRunner init(LivroRepository repository, UsuarioRepository usuarioRepository, RoleRepository roleRepository) {
        return args -> {

            Livro l1 = new Livro("A menina que roubava livros", "1991", "Fantasia");
            Livro l2 = new Livro("O guia do mochileiro das galáxias", "1995", "Fantasia");
            Livro l3 = new Livro("Spring Boot Book", "2017", "Tecnico");
            repository.save(l1);
            repository.save(l2);
            repository.save(l3);

            Role role1 = new Role("ROLE_ADMIN");
            Role role2 = new Role("ROLE_USER");
            roleRepository.save(role1);
            roleRepository.save(role2);

            String senha = new BCryptPasswordEncoder().encode("teste");
            Usuario user1 = new Usuario("thaina", "Thaina",
                    senha, Arrays.asList(role2));
            Usuario user2 = new Usuario("admin", "Administrador",
                    senha, Arrays.asList(role1));
            usuarioRepository.save(user1);
            usuarioRepository.save(user2);
        };
    }
}