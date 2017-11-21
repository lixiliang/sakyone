package pull;

import java.io.IOException;

/**
 * Created by andy on 17/7/6.
 */
public class ClientBootstrap extends AbstractBootstrap {


    public static void main(String[] args) throws IOException {
        ClientBootstrap bootstrap = new ClientBootstrap();
        //发起longpolling 
        bootstrap.doPoll();

        System.in.read();
    }

}
