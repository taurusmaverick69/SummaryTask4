package ua.nure.lyubimtsev.SummaryTask4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.LoginCommandTest;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.LogoutCommandTest;


@RunWith(Suite.class)
@SuiteClasses({LoginCommandTest.class, LogoutCommandTest.class})
public class AllTests {
}
