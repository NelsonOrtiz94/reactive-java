import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;

public class Ejemplo01 {
    public static void main(String[] args) {

        Flux<String> cuidades = Flux.fromIterable(
                new ArrayList<>(Arrays.asList("New york","London", "Paris" , "Toronto" ))
        );
        cuidades.log().subscribe();
    }
}
