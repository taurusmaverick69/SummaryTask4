package ua.nure.lyubimtsev.SummaryTask4.web.commands;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandContainerTest extends CommandTest{

    @Test
    public void instanceOf() throws Exception {

        new CommandContainer();
        Command test = CommandContainer.get("test");
        assertThat(test, CoreMatchers.instanceOf(NoCommand.class));


        Command login = CommandContainer.get("login");
        assertThat(login, CoreMatchers.instanceOf(LoginCommand.class));

    }
}