package com.example.flyport.airportpoints;

public class AirportPointQueries {
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
}
