package pl.oskarpolak.chat.models.commands;

import org.apache.catalina.User;
import pl.oskarpolak.chat.models.UserModel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandFactory {

    private static Map<String, Command> stringCommandMap;

    static  {
        stringCommandMap = new HashMap<>();
        stringCommandMap.put("kick", new KickCommand());
        stringCommandMap.put("pm", new PmCommand());
        stringCommandMap.put("help", new HelpCommand());
        stringCommandMap.put("user", new UserCommand());
    }


    private List<UserModel> userList;

    public CommandFactory(List<UserModel> list){
        userList = list;
    }

    public boolean parseCommand(UserModel userModel, String s){
        if(!s.startsWith("/")){
            return false;
        }
        // /kick Oskar Cos Cos Cos Cos Cos
        String[] parts = s.split(" ");
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);
        String commandAlone = parts[0].substring(1, parts[0].length());
        if(!stringCommandMap.containsKey(commandAlone)){
            userModel.sendMessage("Taka komenda nie istnieje!");
            return true;
        }

        Command command = stringCommandMap.get(commandAlone);
        if(command.argsCount() != -1){
            if(command.argsCount() != args.length){
                userModel.sendMessage(command.error());
                return true;
            }

        }

        command.parseCommand(userModel, userList, args);
        return true;
    }

    public static Map<String, Command> getCommandMap() {
        return stringCommandMap;
    }
}