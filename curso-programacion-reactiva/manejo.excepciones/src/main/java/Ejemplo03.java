import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Ejemplo03 {
    public static void main(String[] args) {

        Flux.just(2,0,10, 8,12,22,24)
                .map(element -> {
                    if (element  == 0) {
                        throw new RuntimeException("Excepcion lanzada");
                    }
                    return element;
                }).onErrorContinue((ex, element) -> {
                    System.out.println("Excepcion : " + ex);
                    System.out.println("Elemento que genero el error es: " + element);
                })
                .log()
                .subscribe();
    }
}
