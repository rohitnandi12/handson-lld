package case_studies.interviewready.ai_game_engine.api;

import case_studies.interviewready.ai_game_engine.game.User;

public class SendEmailCommand {

    User receiver;
    String message;
    String link;
    String template;

    public SendEmailCommand(User receiver, String message, String link, String template) {
        this.receiver = receiver;
        this.message = message;
        this.link = link;
        this.template = template;
    }
}
