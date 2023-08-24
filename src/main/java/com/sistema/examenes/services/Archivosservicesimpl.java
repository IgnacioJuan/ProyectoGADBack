package com.sistema.examenes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class Archivosservicesimpl implements Archivoservices {

    private final Path root = Paths.get("archivosguardados");

    @Override
    @PostConstruct
    public void init() throws IOException {
        Files.createDirectories(root);
    }

    @Override
    public void guardar(MultipartFile file) {
    try {
    Files.copy(file.getInputStream() ,this.root.resolve(file.getOriginalFilename()));
    }catch (IOException e){
    throw new RuntimeException("no se puede guardar el archivo");
    }
    }
    @Override
    public Resource load(String filename) {
try {
    Path file=root.resolve(filename);
    Resource resource= new UrlResource(file.toUri());
    if(resource.exists() || resource.isReadable()){
        return resource;
    }else {
        throw new RuntimeException("No se puede leer el archivo");
    }
}catch (MalformedURLException e) {
    throw new RuntimeException("error"+ e.getMessage());
}
 }

    @Override
    public Stream<Path> lIstar() {
 try {
     return Files.walk(this.root, 1).filter(path -> !path.equals(this.root))
             .map(this.root::relativize);
 }catch (RuntimeException | IOException e ){
     throw new RuntimeException("no se puede cargar los archivos");
 }
 }


    @Override
    public String borrar(String filname) {
       try {
           Boolean borrar =Files.deleteIfExists(this.root.resolve(filname));
           return "Borrado";
       }catch (IOException e){
           e.printStackTrace();
           return "error al borrar";
       }

    }

    @Service
    public static class EmailServiceImpl implements IEmailService {

        @Value("${email.sender}")
        private String emailUser;

        @Autowired
        private JavaMailSender mailSender;

        @Override
        public void sendEmail(String[] toUser, String subject, String message) {

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(emailUser);
            mailMessage.setTo(toUser);
            mailMessage.setSubject(subject);
            mailMessage.setText(message);

            mailSender.send(mailMessage);
        }

        @Override
        public void sendEmailWithFile(String[] toUser, String subject, String message, File file) {

            try {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
                mimeMessageHelper.setFrom(emailUser);
                mimeMessageHelper.setTo(toUser);
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(message);
                mimeMessageHelper.addAttachment(file.getName(), file);
                mailSender.send(mimeMessage);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
