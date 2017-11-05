package pl.oskarpolak.chat.models.commands;

import pl.oskarpolak.chat.models.UserModel;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by lukasz on 2017-11-05.
 */
public class PrivateMessage implements Command {
    @Override
    public void parseCommand(UserModel sender, List<UserModel> userModelList, String... args) {
        String nickToSendPm = args[0];
        Optional<UserModel> userModel = userModelList.stream()
                .filter(s -> s.getNickname().equals(nickToSendPm))
                .findAny();

        if(userModel.isPresent()){
            try {
                userModel.get().getSession().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            sender.sendMessage("Taki user nie istnieje");
        }
    }

    @Override
    public int argsCount() {
        return 2;
    }

    @Override
    public String error() {
        return "Użycie komendy to: /pm nickname";
    }
}
