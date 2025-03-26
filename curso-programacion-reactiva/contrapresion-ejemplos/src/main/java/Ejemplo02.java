import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class Ejemplo02 {
    public static void main(String[] args) {

        Flux<Integer> flux = Flux.range(1, 10).log();
        flux.subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(5);
            }

            @Override
            protected void hookOnNext(Integer value) {
                if(value == 5) {
                    cancel();
                }
            }
        });
    }
}
