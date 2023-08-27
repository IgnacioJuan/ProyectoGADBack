package com.sistema.examenes.controller;

import com.sistema.examenes.entity.ReformaSuplemento;
import com.sistema.examenes.services.ReformaSuplementoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/reformasuplemento")
public class ReformaSuplementoController {

    @Autowired
    private ReformaSuplementoService ReformaSuplementoSevice;

    //post crear
    @PostMapping("/crear")
    public ResponseEntity<ReformaSuplemento> crear(@RequestBody ReformaSuplemento a){
        try {
            a.setVisible(true);
            return new ResponseEntity<>(ReformaSuplementoSevice.save(a), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get listar
    @GetMapping("/listar")
    public ResponseEntity<List<ReformaSuplemento>> listar(){
        try {
            return new ResponseEntity<>(ReformaSuplementoSevice.listarReformaSuplemento(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody ReformaSuplemento ReformaSuplemento) {
        return ResponseEntity.status(HttpStatus.OK).body(ReformaSuplementoSevice.delete(id));
    }

    @PutMapping("/eliminarlogic/{id}")
    public ResponseEntity<?> eliminarLogic(@PathVariable Long id) {
        ReformaSuplemento a = ReformaSuplementoSevice.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(ReformaSuplementoSevice.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ReformaSuplemento> actualizar(@PathVariable Long id, @RequestBody ReformaSuplemento p) {

        try {
            ReformaSuplemento a = ReformaSuplementoSevice.findById(id);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                a.setValor(p.getValor());
                a.setFecha(p.getFecha());
                a.setActividad(p.getActividad());
                return new ResponseEntity<>(ReformaSuplementoSevice.save(a), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
