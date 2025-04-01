import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Ejemplo05 {
    public static void main(String[] args) {
        Flux<String> fluxlux = Flux.fromArray(new String[]{"a", "b", "c"});
        Mono<String> mono = Mono.just("f");

        Flux<String> fluxConcat = fluxlux.concatWith(mono);

        fluxConcat.subscribe(element -> System.out.println(element + " "));
    }
}
