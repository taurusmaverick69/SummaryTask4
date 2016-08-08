package ua.nure.lyubimtsev.summarytask4.web.command;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);
    private static Map<String, Command> commands = new TreeMap<>();

    static {

        // common commands
        commands.put("login", new LoginCommand());
//        commands.put("logout", new LogoutCommand());
//        commands.put("viewSettings", new ViewSettingsCommand());
        commands.put("noCommand", new NoCommand());


        // doctor commands
        commands.put("login", new LoginCommand());
        commands.put("doctors", new DoctorCommand());
        commands.put("insertDoctor", new InsertDoctorCommand());
        commands.put("displayInsertDoctor", new DisplayInsertDoctorCommand());
        commands.put("getDoctorOnUpdate", new GetDoctorOnUpdateCommand());
        commands.put("updateDoctor", new UpdateDoctorCommand());
        commands.put("sortDoctorsCommand", new SortDoctorsCommand());


        commands.put("patients", new PatientCommand());
        commands.put("getPatientOnUpdate", new GetPatientOnUpdateCommand());
        commands.put("sortPatientsCommand", new SortPatientsCommand());


        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());

    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName
     *            Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }

}
