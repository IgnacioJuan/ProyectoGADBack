package com.sistema.examenes.services.auth;

import com.sistema.examenes.entity.auth.Usuario;
import com.sistema.examenes.projection.ResponsableProjection;
import com.sistema.examenes.repository.auth.UsuarioRepository;
import com.sistema.examenes.services.generic.GenericServiceImpl;
import com.sistema.examenes.util.ExampleReportGenerator;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long> implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    //Inyectamos el ReportGenerator
    @Autowired
    private ExampleReportGenerator petReportGenerator;
    @Override
    public CrudRepository<Usuario, Long> getDao() {
        return usuarioRepository;
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }
    @Override
    public Usuario findAllByUsername(String username) {
        return usuarioRepository.findAllByUsername(username);
    }

    @Override
    public List<ResponsableProjection> responsables() {
        return usuarioRepository.responsables();
    }


    @Override
    public Usuario obtenerId(String username) {
        return usuarioRepository.buscarId(username);
    }

    @Override
    public List<Usuario> listaAdminDatos() {
        return usuarioRepository.listaAdminDatos();
    }

    //Llamamos al ReportGenerator
    @Override
    public byte[] exportPdf() throws JRException, FileNotFoundException {
        return petReportGenerator.exportToPdf(usuarioRepository.listaAdminDatos());
    }
}