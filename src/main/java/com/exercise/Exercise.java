package com.exercise;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

@Slf4j
public class Exercise {
    public static void main(String args[])
    {

        if(args.length == 0)
        {
            log.info("Proper Usage is: java program filename");
            System.exit(0);
        }
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            Checks checks = mapper.readValue(new File(args[0]), Checks.class);
            checks.getChecks()
                    .getPing()
                    .forEach((site, url) -> pingHost(site, url));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void pingHost(String k, String v){
        log.debug("host:" + v.split(":")[0]);
        if (pingHost(v.split(":")[0], Integer.parseInt(v.split(":")[1]),10)){
            log.info("OK {}", k);
        }
        else {
            log.info("KO {}", k);
        }
    }

    private static boolean pingHost(String host, int port, int timeout) {
        log.debug("Host to ping:" + host);
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), timeout);
            return true;
        } catch (IOException e) {
            return false; // Either timeout or unreachable or failed DNS lookup.
        }
    }
}
