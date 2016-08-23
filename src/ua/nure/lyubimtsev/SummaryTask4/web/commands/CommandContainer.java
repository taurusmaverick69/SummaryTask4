package ua.nure.lyubimtsev.SummaryTask4.web.commands;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.doctor.*;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.patient.*;

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
        commands.put("forward", new ForwardCommand());
        commands.put("PRG", new PRGCommand());

        // doctor commands
        commands.put("doctors", new GetDoctorsCommand());
        commands.put("insertDoctor", new InsertDoctorCommand());
        commands.put("getDoctorOnUpdate", new GetDoctorOnUpdateCommand());
        commands.put("updateDoctor", new UpdateDoctorCommand());
        commands.put("sortDoctors", new SortDoctorsCommand());

        // patient commands
        commands.put("patients", new GetPatientsCommand());
        commands.put("insertPatient", new InsertPatientCommand());
        commands.put("getPatientOnUpdate", new GetPatientOnUpdateCommand());
        commands.put("updatePatient", new UpdatePatientCommand());
        commands.put("sortPatients", new SortPatientsCommand());


        commands.put("getMedicalCard", new GetMedicalCardCommand());
        commands.put("insertAppointment", new InsertAppointmentCommand());

        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns commands object with the given name.
     *
     * @param commandName Name of the commands.
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
