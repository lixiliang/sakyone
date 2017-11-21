package socket;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;

public class SocketClient {
    // 搭建客户端
    public static void main(String[] args) throws IOException {
        for (int i=0;i<4;i++){
            sendreq();
        }
        System.in.read();
    }



    private static void sendreq() throws IOException {
        Socket socket = new Socket();
        try {
            // 1、创建客户端Socket，指定服务器地址和端口
            // Socket socket=new Socket("127.0.0.1",5200);
            socket.setSoTimeout(15*60*1000);
            socket.connect( new InetSocketAddress( "192.168.10.236", 8080 ), 3*1000 );
//            Socket socket = new Socket("192.168.10.236", 8081);
            System.out.println("连接创建成功:"+socket.toString());
            // 2、获取输出流，向服务器端发送信息
            // 向本机的52000端口发出客户请求
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // 由系统标准输入设备构造BufferedReader对象
            PrintWriter write = new PrintWriter(socket.getOutputStream());
            // 由Socket对象得到输出流，并构造PrintWriter对象
            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
            StringBuffer sb = new StringBuffer();
            sb.append("GET /longpolling/deferred HTTP/1.1\r\n");
            sb.append("Host: localhost:8080\r\n");
            sb.append("Connection: Keep-Alive\r\n");
//            sb.append("Connection: Close\r\n");
            // 注，这是关键的关键，忘了这里让我搞了半个小时。这里一定要一个回车换行，表示消息头完，不然服务器会等待
            sb.append("\r\n");
            osw.write(sb.toString());
            osw.flush();

           /* InputStream inputStream = socket.getInputStream();
            inputStream.read();
            System.out.println("read..");*/

            //3、获取输入流，并读取服务器端的响应信息
            new Thread(new Runnable() {
                @Override
                public void run() {
                    BufferedReader in = null;
                    try {
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        while (in.readLine()!=null){
                            System.out.println("Server response:" + in.readLine());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            /*String readline;
            readline = br.readLine(); // 从系统标准输入读入一字符串
            while (!readline.equals("end")) {
                // 若从标准输入读入的字符串为 "end"则停止循环
                write.println(readline);
                // 将从系统标准输入读入的字符串输出到Server
                write.flush();
                // 刷新输出流，使Server马上收到该字符串
                System.out.println("Client:" + readline);
                // 在系统标准输出上打印读入的字符串
                System.out.println("Server:" + in.readLine());
                // 从Server读入一字符串，并打印到标准输出上
                readline = br.readLine(); // 从系统标准输入读入一字符串
            } // 继续循环*/
            //4、关闭资源
//            System.out.println("close source.");
//            write.close(); // 关闭Socket输出流
//            in.close(); // 关闭Socket输入流

        } catch (Exception e) {
            System.out.println("error:" + e);// 出错，打印出错信息
        }finally {
//            socket.close(); // 关闭Socket

        }
    }

}