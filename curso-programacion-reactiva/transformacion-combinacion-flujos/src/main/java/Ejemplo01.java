import reactor.core.publisher.Flux;

public class Ejemplo01 {
    public static void main(String[] args) {

        Flux.fromArray(new String[]{"Tom", "Melissa", "Steve", "Megan"})
                .map(String::toUpperCase) //Transformar el contenido del arreglo
                .subscribe(System.out::println);
    }
}
