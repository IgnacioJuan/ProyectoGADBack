package com.sistema.examenes.services.auth;

import com.sistema.examenes.entity.auth.Usuario;
import com.sistema.examenes.projection.ResponsableProjection;
import com.sistema.examenes.services.generic.GenericService;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;

public interface UsuarioService extends GenericService<Usuario, Long> {
    public Usuario obtenerUsuario(String username);

    public Usuario obtenerId(String username);

    public Usuario findAllByUsername(String username);

    public List<ResponsableProjection> responsables();
    public List<Usuario> listaAdminDatos();
    byte[] exportPdf() throws JRException, FileNotFoundException;

}
