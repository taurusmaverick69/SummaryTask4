package ua.nure.lyubimtsev.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.web.command.doctor.*;
import ua.nure.lyubimtsev.SummaryTask4.web.command.patient.*;

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
        commands.put("forwardCommand", new ForwardCommand());

        // doctor commands
        commands.put("doctors", new GetDoctorsCommand());
        commands.put("insertDoctor", new InsertDoctorCommand());
        commands.put("displayInsertDoctor", new DisplayInsertDoctorCommand());
        commands.put("getDoctorOnUpdate", new GetDoctorOnUpdateCommand());
        commands.put("updateDoctor", new UpdateDoctorCommand());
        commands.put("sortDoctors", new SortDoctorsCommand());


        commands.put("patients", new GetPatientsCommand());
        commands.put("insertPatient", new InsertPatientCommand());
        commands.put("displayInsertPatient", new DisplayInsertPatientCommand());
        commands.put("getPatientOnUpdate", new GetPatientOnUpdateCommand());
        commands.put("updatePatient", new UpdatePatientCommand());
        commands.put("sortPatients", new SortPatientsCommand());


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
