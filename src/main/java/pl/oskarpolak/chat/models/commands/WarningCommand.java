package pl.oskarpolak.chat.models.commands;

import pl.oskarpolak.chat.models.UserModel;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class WarningCommand implements Command {


    @Override
    public void parseCommand(UserModel model, List<UserModel> userModelList, String... args) {
        // zabezpieczenie jesli nie ma argumenu po komendzie
        String tempString = "";
        if (!args[0].isEmpty()) tempString = args[0];
        String nick = tempString;
// wyszukiwanie usera
        Optional<UserModel> userModel = userModelList.stream()
                .filter(s -> s.getNickname().equals(nick))
                .findAny();
// jesli jest user to wykonanie if()
        if (userModel.isPresent()) {
//wysyla ostrzezenie do usera i informuje tez od kogo
            userModel.get().sendMessage("Otrzymałeś ostrzeżenie od " + model.getNickname() + " \n");


        }


    }

    @Override
    public int argsCount() {
        return 1;
    }

    @Override
    public String error() {
        return "Użycie komendy to: /warning nickname";
    }
}