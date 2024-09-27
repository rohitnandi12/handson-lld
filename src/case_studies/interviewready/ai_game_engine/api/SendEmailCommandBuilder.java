package case_studies.interviewready.ai_game_engine.api;


import case_studies.interviewready.ai_game_engine.game.User;

public class SendEmailCommandBuilder {

    User receiver;
    String message;
    String link;
    String template;

    public static SendEmailCommandBuilder builder() {
        return new SendEmailCommandBuilder();
    }
    public SendEmailCommandBuilder receiver(User user) {
        this.receiver = user;
        return this;
    }

    public SendEmailCommandBuilder message(String message) {
        this.message = message;
        return this;
    }

    public SendEmailCommand build() {
        return new SendEmailCommand(
                this.receiver,
                this.message,
                this.link,
                this.template
        );
    }

}
