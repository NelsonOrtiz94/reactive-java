import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class EjemplosTests {

    @Test
    public void transformMap(){
        List<String> listaNombres = Arrays.asList("google", "abc", "fb", "Stackoverflow");
        Flux<String> nombresFlux = Flux.fromIterable(listaNombres)
                .filter(nombre -> nombre.length() > 5)
                .map(nombre -> nombre.toUpperCase())
                .log();

        StepVerifier.create(nombresFlux)
                .expectNext("GOOGLE", "STACKOVERFLOW")
                .verifyComplete();
    }

    @Test
    public void testTransformUsingFlatMap(){
        List<String> listaNombres = Arrays.asList("google", "abc", "fb", "Stackoverflow");
        Flux<String> nombresFlux = Flux.fromIterable(listaNombres)
                .filter(nombre -> nombre.length() > 5)
                .flatMap(nombre -> {
                    return Mono.just(nombre.toUpperCase());
                })
                .log();

        StepVerifier.create(nombresFlux)
                .expectNext("GOOGLE", "STACKOVERFLOW")
                .verifyComplete();
    }

    @Test
    public void testCombinarFlujosUsandoMerge(){
        Flux<String> flux1 = Flux.just("Lucho", "Mo", "Julian");
        Flux<String> flux2 = Flux.just("Diaz", "Salah", "Alvarez");

        Flux<String> fluxMerge = Flux.merge(flux1, flux2).log(); //Combina múltiples observables

        StepVerifier.create(fluxMerge)
                .expectSubscription()
                .expectNext("Lucho", "Mo", "Julian","Diaz", "Salah", "Alvarez")
                .verifyComplete();
    }

    @Test
    public void testCombinarFlujosUsandoMergeConDelay(){
        Flux<String> flux1 = Flux.just("Lucho", "Mo", "Julian")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> flux2 = Flux.just("Diaz", "Salah", "Alvarez")
                .delayElements(Duration.ofSeconds(1));

        Flux<String> fluxMerge = Flux.merge(flux1, flux2).log();

        StepVerifier.create(fluxMerge)
                .expectSubscription()
                .expectNextCount(6)
                .verifyComplete();
    }

    @Test
    public void testCombinarFlujosConDelayConOperadorConcat(){
        Flux<String> flux1 = Flux.just("Lucho", "Mo", "Julian")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> flux2 = Flux.just("Diaz", "Salah", "Alvarez")
                .delayElements(Duration.ofSeconds(1));

        Flux<String> fluxConcat = Flux.concat(flux1, flux2).log();

        StepVerifier.create(fluxConcat)
                .expectSubscription()
                .expectNext("Lucho", "Mo", "Julian","Diaz", "Salah", "Alvarez")
                .verifyComplete();
    }

    @Test
    public void testCombinarFlujosConDemoraConOperadorZip(){
        Flux<String> flux1 = Flux.just("Lucho", "Mo", "Julian")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> flux2 = Flux.just("Diaz", "Salah", "Alvarez")
                .delayElements(Duration.ofSeconds(1));

        Flux<String> fluxZip = Flux.zip(flux1,flux2,(f1,f2) -> {
            return f1.concat(" ").concat(f2);
        }).log();

        StepVerifier.create(fluxZip)
                .expectNext("Lucho Diaz", "Mo Salah", "Julian Alvarez")
                .verifyComplete();
    }
}
