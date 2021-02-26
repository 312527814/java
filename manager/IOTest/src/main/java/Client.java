import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("192.168.77.130", 9090);
        socket.setKeepAlive(true);
        socket.setSendBufferSize(8192);
        socket.setReceiveBufferSize(8192);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        os.write("get test-key".getBytes("UTF-8"));
        os.flush();
        Thread.sleep(155 * 1000L);
        os.write("get test-key".getBytes("UTF-8"));
        os.flush();
        byte[] bytes = new byte[1024];
        while (is.read(bytes) > -1) {
            System.out.println(System.currentTimeMillis() + " received message: " + new String(bytes, "UTF-8").trim());
            bytes = new byte[1024];
        }
        if (!socket.isOutputShutdown()) {
            socket.shutdownOutput();
        }
        if (!socket.isInputShutdown()) {
            socket.shutdownInput();
        }
        if (!socket.isClosed()) {
            socket.close();
        }
    }
}