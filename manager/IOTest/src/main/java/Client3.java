import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client3 {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("192.168.77.130", 12345);
        socket.setKeepAlive(true);
        socket.setSendBufferSize(8192);
        socket.setReceiveBufferSize(8192);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        while (true) {
            Thread.sleep(500);
            String s = "get test-key我我我我我我我我我我我我我我我问问我我我我我 问问 我我问问我 我 \r\n";
            System.out.println("before write");
            os.write(s.getBytes("UTF-8"));
            System.out.println("end write");

            System.out.println("before flush");
            os.flush();
            System.out.println("end flush");

            System.out.println(s+":"+System.currentTimeMillis());
        }
    }
}