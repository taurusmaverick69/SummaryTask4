package ua.nure.lyubimtsev.SummaryTask4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.ForwardCommandTest;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.LoginCommandTest;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.LogoutCommandTest;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.PRGCommandTest;


@RunWith(Suite.class)
@SuiteClasses({ForwardCommandTest.class, LoginCommandTest.class, LogoutCommandTest.class,PRGCommandTest.class,})
public class AllTests {
}
