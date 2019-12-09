package EX1;
/*
 * Implementar o problema do produtor-consumidor usando Java NIO: Pipe, Pipe.SinkChannel e Pipe.SourceChannel.
 *
 * @author Danilo Sambugaro created on 08/12/2019 inside the package - EX1
 *
 */

import java.io.IOException;
import java.nio.channels.Pipe;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        try {
            Pipe pipe = Pipe.open();

            Random r = new Random();

            Producer producer = new Producer(pipe, r);
            Consumer consumer = new Consumer(pipe);

            new Thread(producer, "Producer").start();
            new Thread(consumer, "Consumer").start();


        } catch (IOException e) {

        }
    }
}
