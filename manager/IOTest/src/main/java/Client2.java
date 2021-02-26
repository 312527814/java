import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("192.168.77.130", 12345);
        socket.setKeepAlive(true);
        socket.setSendBufferSize(8192);
        socket.setReceiveBufferSize(8192);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        os.write("get test-key".getBytes("UTF-8"));
        os.flush();

        System.in.read();
        socket.close();

        Thread.sleep(1000 * 2000);
//        if (!socket.isOutputShutdown()) {
//            socket.shutdownOutput();
//        }
//        if (!socket.isInputShutdown()) {
//            socket.shutdownInput();
//        }
//        if (!socket.isClosed()) {
//            socket.close();
//        }
    }
}