package com.example.flyport.airportpoints;

public class AirportPointQueries {
    public RegistrationPoint registrationPoint;
    public BoardingGates boardingGates;
    public SecurityPoint securityPoint;
    public PassportPoint passportPoint;

    AirportPointQueries() {
        this.registrationPoint = new RegistrationPoint();
        this.boardingGates = new BoardingGates();
        this.securityPoint = new SecurityPoint();
        this.passportPoint = new PassportPoint();
    }

    public final class RegistrationPoint {
        public final String registerUserFlight = "INSERT INTO USER_FLIGHT (USER_ID, FLIGHT_ID) VALUES (?,?)";
        public final String addRegistrationPointToUserFlight = "UPDATE " +
                "    USER_FLIGHT A " +
                "SET " +
                "    A.REGISTRATION_POINT = ( " +
                "        SELECT " +
                "            AIRPORT_POINT_ID " +
                "        FROM " +
                "            AIRPORT_POINT " +
                "        WHERE " +
                "                TYPE = 'R' " +
                "        ORDER BY QUEUE ASC " +
                "        LIMIT 1 " +
                "    ) " +
                "WHERE " +
                "        A.USER_FLIGHT_ID = ?";
        public final String addQueueToRegistrationPoint = "UPDATE " +
                "    AIRPORT_POINT A " +
                "SET " +
                "    A.QUEUE = A.QUEUE + 1 " +
                "WHERE " +
                "        AIRPORT_POINT_ID = ( " +
                "        SELECT " +
                "            REGISTRATION_POINT " +
                "        FROM " +
                "            USER_FLIGHT " +
                "        WHERE " +
                "                USER_FLIGHT_ID = ? " +
                "    )";
        public final String getRegistrationPoint = "SELECT A.AIRPORT_POINT_ID, A.TYPE, A.POINT_NUMBER FROM AIRPORT_POINT A, USER_FLIGHT B WHERE B.USER_FLIGHT_ID=? AND A.AIRPORT_POINT_ID = B.REGISTRATION_POINT";
        public final String removeQueueFromRegistrationPoint = "UPDATE " +
                "    AIRPORT_POINT A " +
                "SET " +
                "    A.QUEUE = A.QUEUE - 1 " +
                "WHERE " +
                "        AIRPORT_POINT_ID = ( " +
                "        SELECT " +
                "            REGISTRATION_POINT " +
                "        FROM " +
                "            USER_FLIGHT " +
                "        WHERE " +
                "                USER_FLIGHT_ID = ? " +
                "    )";
    };

    public final class SecurityPoint {
        public final String addSecurityPointToUserFlight = "UPDATE " +
                "    USER_FLIGHT A " +
                "SET " +
                "    A.SECURITY_POINT = ( " +
                "        SELECT " +
                "            AIRPORT_POINT_ID " +
                "        FROM " +
                "            AIRPORT_POINT " +
                "        WHERE " +
                "                TYPE = 'S' " +
                "        ORDER BY QUEUE ASC " +
                "        LIMIT 1 " +
                "    ) " +
                "WHERE " +
                "        A.USER_FLIGHT_ID = ?";
        public final String addQueueToSecurityPoint = "UPDATE " +
                "    AIRPORT_POINT A " +
                "SET " +
                "    A.QUEUE = A.QUEUE + 1 " +
                "WHERE " +
                "        AIRPORT_POINT_ID = ( " +
                "        SELECT " +
                "            SECURITY_POINT " +
                "        FROM " +
                "            USER_FLIGHT " +
                "        WHERE " +
                "                USER_FLIGHT_ID = ? " +
                "    )";
        public final String getSecurityPoint = "SELECT A.AIRPORT_POINT_ID, A.TYPE, A.POINT_NUMBER FROM AIRPORT_POINT A, USER_FLIGHT B WHERE B.USER_FLIGHT_ID=? AND A.AIRPORT_POINT_ID = B.SECURITY_POINT";
        public final String removeQueueFromSecurityPoint = "UPDATE " +
                "    AIRPORT_POINT A " +
                "SET " +
                "    A.QUEUE = A.QUEUE - 1 " +
                "WHERE " +
                "        AIRPORT_POINT_ID = ( " +
                "        SELECT " +
                "            SECURITY_POINT " +
                "        FROM " +
                "            USER_FLIGHT " +
                "        WHERE " +
                "                USER_FLIGHT_ID = ? " +
                "    )";
    };

    public final class BoardingGates {
        public final String addBoardingGatesToUserFlight = "UPDATE " +
                "  USER_FLIGHT A " +
                "  INNER JOIN FLIGHT B " +
                "SET " +
                "  A.BOARDING_GATES = B.GATE " +
                "WHERE " +
                "  A.USER_FLIGHT_ID = ? " +
                "  AND A.FLIGHT_ID = B.FLIGHT_ID";
        public final String getBoardingGates = "SELECT A.AIRPORT_POINT_ID, A.TYPE, A.POINT_NUMBER FROM AIRPORT_POINT A, USER_FLIGHT B WHERE B.USER_FLIGHT_ID=? AND A.AIRPORT_POINT_ID = B.BOARDING_GATES";
        public final String completeFlight = "UPDATE USER_FLIGHT A SET COMPLETED=1 WHERE A.USER_FLIGHT_ID = ?";
    };

    public final class PassportPoint {
        public final String addPassportPointToUserFlight = "UPDATE " +
                "    USER_FLIGHT A " +
                "SET " +
                "    A.PASSPORT_POINT = ( " +
                "        SELECT " +
                "            AIRPORT_POINT_ID " +
                "        FROM " +
                "            AIRPORT_POINT " +
                "        WHERE " +
                "                TYPE = 'P' " +
                "        ORDER BY QUEUE ASC " +
                "        LIMIT 1 " +
                "    ) " +
                "WHERE " +
                "        A.USER_FLIGHT_ID = ?";
        public final String addQueueToPassportPoint = "UPDATE " +
                "    AIRPORT_POINT A " +
                "SET " +
                "    A.QUEUE = A.QUEUE + 1 " +
                "WHERE " +
                "        AIRPORT_POINT_ID = ( " +
                "        SELECT " +
                "            PASSPORT_POINT " +
                "        FROM " +
                "            USER_FLIGHT " +
                "        WHERE " +
                "                USER_FLIGHT_ID = ? " +
                "    )";
        public final String getPassportPoint = "SELECT A.AIRPORT_POINT_ID, A.TYPE, A.POINT_NUMBER FROM AIRPORT_POINT A, USER_FLIGHT B WHERE B.USER_FLIGHT_ID=? AND A.AIRPORT_POINT_ID = B.PASSPORT_POINT";
        public final String removeQueueFromPassportPoint = "UPDATE " +
                "    AIRPORT_POINT A " +
                "SET " +
                "    A.QUEUE = A.QUEUE - 1 " +
                "WHERE " +
                "        AIRPORT_POINT_ID = ( " +
                "        SELECT " +
                "            PASSPORT_POINT " +
                "        FROM " +
                "            USER_FLIGHT " +
                "        WHERE " +
                "                USER_FLIGHT_ID = ? " +
                "    )";
    };

}
