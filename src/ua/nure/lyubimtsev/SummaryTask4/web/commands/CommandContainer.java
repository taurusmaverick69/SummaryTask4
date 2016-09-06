package ua.nure.lyubimtsev.SummaryTask4.web.commands;

import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.appointment.GetAppointmentOnUpdateCommand;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.appointment.GetAppointmentsCommand;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.appointment.InsertAppointmentCommand;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.appointment.UpdateAppointmentCommand;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.doctor.*;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.medicalCard.GetMedicalCardCommand;
import ua.nure.lyubimtsev.SummaryTask4.web.commands.patient.*;

import java.util.Map;
import java.util.TreeMap;

/**
 * Holder for all commands.
 *
 * @author Vladislav
 *
 */
public class CommandContainer {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);
    private static Map<String, Command> commands = new TreeMap<>();

    static {

        // common commands
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
//        commands.put("viewSettings", new ViewSettingsCommand());
        commands.put("noCommand", new NoCommand());

        // doctor commands
        commands.put("doctors", new GetDoctorsCommand());
        commands.put("insertDoctor", new InsertDoctorCommand());
        commands.put("getDoctorOnUpdate", new GetDoctorOnUpdateCommand());
        commands.put("updateDoctor", new UpdateDoctorCommand());

        // patient commands
        commands.put("patients", new GetPatientsCommand());
        commands.put("insertPatient", new InsertPatientCommand());
        commands.put("getPatientOnUpdate", new GetPatientOnUpdateCommand());
        commands.put("updatePatient", new UpdatePatientCommand());
        commands.put("getUnassignedPatients", new GetUnassignedPatientsCommand());
        commands.put("assignPatient", new AssignPatientCommand());

        commands.put("getMedicalCard", new GetMedicalCardCommand());

        commands.put("appointments", new GetAppointmentsCommand());
        commands.put("insertAppointment", new InsertAppointmentCommand());
        commands.put("getAppointmentOnUpdate", new GetAppointmentOnUpdateCommand());
        commands.put("updateAppointment", new UpdateAppointmentCommand());



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
