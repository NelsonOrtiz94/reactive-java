import reactor.core.publisher.Flux;

public class Ejemplo02 {
    public static void main(String[] args) {

        Flux.fromArray(new String[]{"Tom", "Melissa", "Steve", "Megan"})
                .filter(str -> str.length() > 5)
                .map(String::toUpperCase) //Transformar el contenido del arreglo
                .subscribe(System.out::println);
    }
}
