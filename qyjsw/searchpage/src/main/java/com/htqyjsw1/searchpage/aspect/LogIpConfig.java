package com.htqyjsw1.searchpage.aspect;

import ch.qos.logback.core.PropertyDefinerBase;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class LogIpConfig extends PropertyDefinerBase {

    @Override
    public String getPropertyValue() {
        String ip = null;
        try {
            ip= InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
        }
        return ip;
    }
}