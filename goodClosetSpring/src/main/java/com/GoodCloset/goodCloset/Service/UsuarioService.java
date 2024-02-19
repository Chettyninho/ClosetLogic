package com.GoodCloset.goodCloset.Service;

import com.GoodCloset.goodCloset.Models.Seguidor;
import com.GoodCloset.goodCloset.Models.Usuario;
import com.GoodCloset.goodCloset.Repository.SeguidorRepository;
import com.GoodCloset.goodCloset.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private SeguidorRepository seguidorRepositorys;
    public Usuario postImageProfile(Usuario usuario){return usuarioRepository.save(usuario);}
    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }
    public Usuario getUsuarioById(Integer id){
        return usuarioRepository.findById(id).get();
    }

    public Usuario comprobarYRegistrarUsuario(Usuario usuario) {
        // Verificar si el nombre de usuario ya existe
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            // El nombre de usuario ya está en uso, retorna null
            return null;
        }

        // Verificar si el correo electrónico ya existe
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            // El correo electrónico ya está en uso, retorna null
            return null;
        }

        // Si el nombre de usuario y el correo electrónico no existen, entonces hashear la contraseña
        byte[] salt = generateSalt();
        String hashedPassword = hashPassword(usuario.getPassword(), salt);
        usuario.setPassword(hashedPassword);
        usuario.setSalt(salt);

        // Guardar el usuario en la base de datos
        return usuarioRepository.save(usuario);
    }
    public boolean existsByUsername(String username) { return usuarioRepository.existsByUsername(username); }

    //generar sal y generar contraseña hasseada
    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16]; // Tamaño recomendado para el salt: 16 bytes
        random.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Agregar el salt a la contraseña antes de aplicar el hash
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());

            // Convertir el hash a una representación en formato Base64 para meterlo a la bbdd
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            // Manejar la excepción apropiadamente
            e.printStackTrace();
            return null;
        }
    }

    public List<Usuario> obtenerUsuariosSeguidosPorUsuario(Integer idSeguidor) {
        return usuarioRepository.findUsuariosSeguidosPorUsuario(idSeguidor);
    }

    public boolean deleteUser(Integer idUsuario) {
        if (usuarioRepository.existsById(idUsuario)) {
            usuarioRepository.deleteById(idUsuario);
            return true;
        }
        return false;
    }

    public byte[] getFotoUsuarioPorId(Integer id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            return usuarioOptional.get().getFotoUsuario();
        } else {
            return null;
        }
    }
    public void follow4Follow(Integer idSeguido, Integer idSeguidor) {
        Seguidor usuarioSeguido = new Seguidor(usuarioRepository.findById(idSeguido).get(),usuarioRepository.findById(idSeguidor).get());
//        Seguidor usuarioSeguidor = new Usuario(idSeguidor);
//        Seguidor ns = new Seguidor();
        seguidorRepositorys.save(usuarioSeguido);
    }

}
