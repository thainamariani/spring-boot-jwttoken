package br.com.sebo.jwttoken;

import br.com.sebo.pojo.Usuario;
import br.com.sebo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = repository.findByLogin(login);
        if(usuario == null){
            throw new UsernameNotFoundException("O usuário não existe no banco de dados");
        }
        return new User(usuario.getUsername(), usuario.getPassword(),
                true, true,
                true, true, usuario.getAuthorities());
    }
}
