package com.ejemplo.reactivo;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductoRepository {

    private static List<Producto> lista = new ArrayList<>();

    static{
        lista.add(new Producto(1, "ordenador", 100));
        lista.add(new Producto(2, "Diademas", 200));
        lista.add(new Producto(3, "Cascos", 300));
    }

    public Flux<Producto> buscarTodos() {
        return Flux.fromIterable(lista).delayElements(Duration.ofSeconds(3));
    }
}
