import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class EjemplosTests {

    @Test
    public void testsFlux (){
        Flux<Integer> fluxTotest = Flux.just(1, 2, 3);

        // Se crea la prueba
        StepVerifier.create(fluxTotest)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectComplete()
                .verify();
    }

    @Test
    public void testsFluxString(){
        Flux<String> fluxTotest = Flux.just("Cristiano", "Neymar", "Messi", "Lucho", "Salah")
                .filter(nombre -> nombre.length() <= 5)
                .map(String::toUpperCase);

        StepVerifier.create(fluxTotest)
                .expectNext("MESSI")
                .expectNext("LUCHO")
                .expectNextMatches(nombre -> nombre.startsWith("SA"))
                .expectComplete()
                .verify();
    }
}
