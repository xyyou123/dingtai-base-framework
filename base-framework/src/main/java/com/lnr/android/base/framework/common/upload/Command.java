package com.lnr.android.base.framework.common.upload;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * author:lnr
 * date:2018/9/7
 * 命令
 */
public class Command {

    private String type;
    private String cmd;
    private String cmdLine;
    private HashMap<String, String> params;

    private static final String Request = "[Request]";
    private static final String Response = "[Response]";
    private static final String Command = "Command=";

    private Command() {
        this.params = new HashMap<>();
    }

    public Command(String type, String cmd) {
        this.type = type;
        this.cmd =  cmd;
        this.params = new HashMap<>();
    }

    public void setType(String type) {
        this.type = type;
        this.type = type + br();
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
        this.cmdLine = Command + cmd + br();
    }

    public String getCmd() {
        return cmd;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public static Command request(String cmd) {
        return new Command(Request, cmd);
    }

    private static String br() {
        return "\r\n";
    }

    public Command addParams(String key, String value) {
        params.put(key, value);
        return this;
    }


    public byte[] toPacket(byte[] extra) {
        String header = type + br() + Command + cmd;
        String data = header + br();

        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            data = data + entry.getKey() + "=" + entry.getValue();
            if(iterator.hasNext()) {
                data += br();
            }
        }

        byte[] cmdBytes = data.getBytes();
        int cmdLength = cmdBytes.length;
        int packetLength = cmdLength + 4 + (extra == null ? 0 : extra.length);

        byte[] arr = new byte[packetLength + 4];
        arr[0] =  (byte) packetLength;
        arr[1] = (byte) (packetLength >> 8);
        arr[2] = (byte) (packetLength >> 16);
        arr[3] = (byte) (packetLength >> 24);
        arr[4] = (byte) cmdLength;
        arr[5] = (byte) (cmdLength >> 8);
        arr[6] = (byte) (cmdLength >> 16);
        arr[7] = (byte) (cmdLength >> 24);
        System.arraycopy(cmdBytes, 0, arr, 8, cmdLength);

        if(extra != null && extra.length > 0) {
            System.arraycopy(extra, 0, arr, 8 + cmdLength, extra.length);
        }

        return arr;
    }

    public static Command response(byte[] bytes) {
        Command command = new Command();

        String str = new String(bytes);
        String[] datas = str.split(br());

        for (String line : datas) {
            if (Response.startsWith(line)) {
                command.setType(line);
                continue;
            }
            if(line.startsWith(Command)) {
                command.cmd = line.replace(Command, "");
                command.cmdLine = line;
            }
            String[] map = line.split("=");
            if (map.length != 2) {
                continue;
            }
            command.addParams(map[0], map[1]);
        }

        return command;
    }

}
