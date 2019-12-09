package EX1;
/*
 *
 * @author Danilo Sambugaro created on 08/12/2019 inside the package - EX1
 *
 */

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.Random;

public class Producer  implements Runnable {
    private Pipe pipe;
    private Random r;

    public Producer(Pipe pipe, Random r) {
        this.pipe = pipe;
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {

            Pipe.SinkChannel sinkChannel = pipe.sink();

            System.out.println("[ " + Thread.currentThread().getName() + " ] Producing...");

            ByteBuffer buffer = ByteBuffer.allocate(16);
            buffer.clear();
            buffer.putInt(r.nextInt(100) + 1);
            buffer.flip();

            while (buffer.hasRemaining()) {
                try {
                    sinkChannel.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
