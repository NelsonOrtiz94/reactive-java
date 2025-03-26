import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Ejemplo01 {
    public static void main(String[] args) {

        List<Integer> elementosFromMono = new ArrayList<>();
        List<Integer> elementosFromFlux = new ArrayList<>();

        // Creación de Mono
        Mono<Integer> mono = Mono.just(121);

        // Creación de Flux
        Flux<Integer> flux = Flux.just(1,2,3,4,5);

        // Nos suscribimos al mono
        mono.subscribe(elementosFromMono::add);

        //Nos suscribimos al Flux
        flux.subscribe(elementosFromFlux::add);

        System.out.println(elementosFromMono);
        System.out.println(elementosFromFlux);

    }
}