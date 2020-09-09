package com.syc.sycsf;

import org.apache.sshd.ClientChannel;
import org.apache.sshd.ClientSession;
import org.apache.sshd.SshClient;
import org.apache.sshd.client.channel.ChannelExec;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SycsfApplicationTests {

	@Test
	void contextLoads() {
		try{
			String cmd="ifconfig";
			SshClient client=SshClient.setUpDefaultClient();
			client.start();
			ClientSession session=client.connect("root", "148.66.10.210", 33888).await().getSession();
			session.addPasswordIdentity("AdGwz+^9BW6z");
			if(!session.auth().await().isSuccess())
				System.out.println("auth failed");
			ChannelExec ec=session.createExecChannel(cmd);
			ec.setOut(System.out);
			ec.open();
			ec.waitFor(ClientChannel.CLOSED, 0);
			ec.close(true);
			client.stop();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
