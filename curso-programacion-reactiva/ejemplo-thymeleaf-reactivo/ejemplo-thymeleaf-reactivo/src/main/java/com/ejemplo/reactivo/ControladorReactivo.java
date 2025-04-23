package com.ejemplo.reactivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

@Controller
public class ControladorReactivo {

    @Autowired
    private ProductoRepository productoRepository;

    @RequestMapping("/lista")
    public String listarProductos(Model model) {
        // Variable reactiva que contiene la lista de productos
        IReactiveDataDriverContextVariable listaReactiva =
                new ReactiveDataDriverContextVariable(productoRepository.buscarTodos(),1);
        model.addAttribute("listarProductos", listaReactiva);
        return "lista";
    }
}
