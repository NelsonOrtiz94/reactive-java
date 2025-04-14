import reactor.core.publisher.Flux;

public class Ejemplo01 {
    public static void main(String[] args) {

        Flux.just(2,7,10)
                .concatWith(Flux.error(new RuntimeException("Excepcion lanzada")))
                .concatWith(Flux.just(12))
                .onErrorReturn(72)
                .log()
                .subscribe();
    }
}
