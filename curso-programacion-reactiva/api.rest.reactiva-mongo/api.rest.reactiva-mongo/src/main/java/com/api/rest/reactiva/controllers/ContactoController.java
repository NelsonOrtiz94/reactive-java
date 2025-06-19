package com.api.rest.reactiva.controllers;

import com.api.rest.reactiva.documents.Contacto;
import com.api.rest.reactiva.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1")
public class ContactoController {

    @Autowired
    private ContactoRepository contactoRepository;

    @GetMapping("/contactos")
    public Flux<Contacto> listarContactos() {
        return contactoRepository.findAll();
    }

    @GetMapping(value = "/contacto/{id}")
    public Mono<ResponseEntity<Contacto>> obtenerContacto(@PathVariable String id) {
        return contactoRepository.findById(id)
                .map(contacto -> new ResponseEntity<>(contacto, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/contactos/byEmail/{email}")
    public Mono<ResponseEntity<Contacto>> obtenerContactoPorEmail(@PathVariable String email) {
        return contactoRepository.findFirstByEmail(email)
                .map(contacto -> new ResponseEntity<>(contacto, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/contactos")
    public Mono<ResponseEntity<Contacto>> guardarContacto(@RequestBody Contacto contacto) {
        return contactoRepository.insert(contacto)
                .map(contactoGuardado -> new ResponseEntity<>(contactoGuardado, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/contactos/{id")
    public Mono<ResponseEntity<Contacto>> actualizarContacto(@RequestBody Contacto contacto, @PathVariable String id) {
        return contactoRepository.findById(id)
                .flatMap(contactoExistente -> {
                    contactoExistente.setId(id);
                    contactoExistente.setNombre(contacto.getNombre());
                    contactoExistente.setEmail(contacto.getEmail());
                    contactoExistente.setTelefono(contacto.getTelefono());
                    return contactoRepository.save(contactoExistente);
                })
                .map(contactoActualizado -> new ResponseEntity<>(contactoActualizado, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public Mono<Void> eliminarContacto(@PathVariable String id) {
        return contactoRepository.deleteById(id);
    }


}
