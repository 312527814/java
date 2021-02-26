
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SelectorServer2 {

    private Selector selector;

    public SelectorServer2() {
        ServerSocketChannel ssc = null;
        try {
            int _port = 8089;
            /* 获取通道 */
            ssc = ServerSocketChannel.open();
            /* 配置非阻塞 */
            ssc.configureBlocking(false);
            /**
             * 配置绑定端口 ServerSocketChannel没有bind()方法，
             * 因此有必要取出对等的ServerSocket并使用它来绑定到一
             * 个端口以开始监听连接
             */
            ssc.socket().bind(new InetSocketAddress("localhost", _port));
            /* 获取选择器 */
            this.selector = Selector.open();
            /* 将通道注册到选择器 */
            ssc.register(this.selector, SelectionKey.OP_ACCEPT);


            System.out.println("启动成功！");
        } catch (ClosedChannelException e1) {
            System.out.println("关闭的通道,无法注册到选择器");
            e1.printStackTrace();
        } catch (IOException e2) {
            try {
                if (ssc != null) ssc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("服务器绑定端口冲突");
            e2.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        SelectorServer2 selectorServer2 = new SelectorServer2();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.schedule(() -> {
            System.out.println("before wakeup");
            selectorServer2.selector.wakeup();
            System.out.println("after wakeup");
        }, 5, TimeUnit.SECONDS);
        selectorServer2.startSubselect();
    }


    public void startSubselect() {
        try {
            System.out.println("selector waiting...");
            int select = this.selector.select();
            System.out.println(select + "  selector wait pass...");
//            while (this.selector.select() > 0) {
//                System.out.println("selector wait pass...");
//                Iterator<SelectionKey> it = this.selector.selectedKeys().iterator();
//                while (it.hasNext()) {
//                    SelectionKey selectKey = it.next();
//                    it.remove();
//                    try {
//                        process(selectKey);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        continue;
//                    }
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Selector getSelector() {
        return this.selector;
    }

    private void process(SelectionKey selectKey) {
        if (selectKey.isAcceptable()) {
            acceptHandler(selectKey);
        } else if (selectKey.isReadable()) {
            readeHanlder(selectKey);
        }
    }

    private void readeHanlder(SelectionKey selectKey) {
        SocketChannel channel = (SocketChannel) selectKey.channel();
        try {
            selectKey.cancel();

            // 创建读取的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(100);
            channel.read(buffer);
            byte[] data = buffer.array();
            String msg = new String(data).trim();

            System.out.println("groupName: subselect, 线程Id:" + Thread.currentThread().getId() + "：客户端：" + msg + ":" + channel.getRemoteAddress());

            String s = channel.getRemoteAddress() + "服务器返回 你好," + msg + "\r\n";
            if (msg.equals("beybey")) {
                s = "beybey";
            }
            ByteBuffer outBuffer = ByteBuffer.wrap(s.getBytes());
            // 将消息回送给客户端
            channel.write(outBuffer);
            if (msg.equals("beybey")) {
                System.out.print(channel.getRemoteAddress() + ":客户端下线");
                channel.close();
            }
        } catch (Exception e) {
            try {
                channel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    private void acceptHandler(SelectionKey selectKey) {

        ServerSocketChannel ssc = null;
        try {
            ssc = (ServerSocketChannel) selectKey
                    .channel();
            SocketChannel sc = ssc.accept();
            sc.configureBlocking(false);
            sc.register(this.selector, SelectionKey.OP_READ, "政治格局");
        } catch (ClosedChannelException e) {

        } catch (IOException e) {

        }
    }

}
