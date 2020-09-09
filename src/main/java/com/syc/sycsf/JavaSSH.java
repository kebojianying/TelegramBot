package com.syc.sycsf;

import org.apache.sshd.ClientChannel;
import org.apache.sshd.ClientSession;
import org.apache.sshd.SshClient;
import org.apache.sshd.client.channel.ChannelExec;

import java.io.IOException;

public class JavaSSH {
    public static void main(String agrs[]){
        clentTest();
    }
    public void clentTest() throws IOException
    {
        String cmd="ifconfig";
        SshClient client=SshClient.setUpDefaultClient();
        client.start();
        ClientSession session=client.connect("bellring", "10.2.48.179", 22).await().getSession();
        session.addPasswordIdentity("bellring");
        if(!session.auth().await().isSuccess())
            System.out.println("auth failed");

        ChannelExec ec=session.createExecChannel(cmd);
        ec.setOut(System.out);
        ec.open();
        ec.waitFor(ClientChannel.CLOSED, 0);
        ec.close();

        client.stop();
    }
}
