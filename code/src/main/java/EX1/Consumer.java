package EX1;
/*
 *
 * @author Danilo Sambugaro created on 08/12/2019 inside the package - EX1
 *
 */

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class Consumer implements Runnable {

    private Pipe pipe;

    public Consumer(Pipe pipe) {
        this.pipe = pipe;
    }

    @Override
    public void run() {
        while (true) {
            Pipe.SourceChannel sourceChannel = pipe.source();

            ByteBuffer buffer = ByteBuffer.allocate(16);
            buffer.clear();

            try {
                sourceChannel.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }

            buffer.flip();

            System.out.println("[ " + Thread.currentThread().getName() + " ] Consuming " + buffer.getInt() + "...");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
