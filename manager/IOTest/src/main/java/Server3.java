import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server3 {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket ss = new ServerSocket(12345);
        ss.setReceiveBufferSize(111);
        while (true) {
            Socket socket = ss.accept();
            int i = 0;
            while (i < 10) {
                Thread.sleep(1000 * 10);
                i++;
            }
            try {
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                try {
                    byte[] bytes = new byte[1024];
                    while (is.read(bytes) > -1) {
                        System.out.println(System.currentTimeMillis() + " received message: " + new String(bytes, "UTF-8").trim());
                        bytes = new byte[1024];
                    }
                    System.out.println("socket closer");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (!socket.isInputShutdown()) {
                        socket.shutdownInput();
                    }
                    if (!socket.isOutputShutdown()) {
                        socket.shutdownOutput();
                    }
                    if (!socket.isClosed()) {

                        System.out.println("!socket.isClosed()");

                        socket.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
//
//            System.out.println(socket);
//            new Thread(() -> {
//                try {
//                    InputStream is = socket.getInputStream();
//                    OutputStream os = socket.getOutputStream();
//                    try {
//                        byte[] bytes = new byte[1024];
//                        while (is.read(bytes) > -1) {
//                            System.out.println(System.currentTimeMillis() + " received message: " + new String(bytes, "UTF-8").trim());
//                            bytes = new byte[1024];
//                        }
//                        System.out.println("socket closer");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } finally {
//                        if (!socket.isInputShutdown()) {
//                            socket.shutdownInput();
//                        }
//                        if (!socket.isOutputShutdown()) {
//                            socket.shutdownOutput();
//                        }
//                        if (!socket.isClosed()) {
//
//                            System.out.println("!socket.isClosed()");
//
//                            socket.close();
//                        }
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }).start();
        }
    }
}
