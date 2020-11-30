package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class DetailsValue {

    // SQL запрос значения из базы данных
    private static final String SQL_QUERY_VALUE = "select AreaCut_Value from "
            + "Profiles, ProfileTypes, ProfileNumbers, NumberValues "
            + "where Profiles.Profile_ID = ProfileTypes.Profile_ID and "
            + "ProfileTypes.ProfileType_ID = ProfileNumbers.ProfileType_ID and "
            + "ProfileNumbers.ProfileNumber_ID = NumberValues.Number_ID and "
            + "Profiles.ProfileName = ? and "
            + "ProfileTypes.ProfileTypeName = ? and "
            + "ProfileNumbers.ProfileNumberName = ?";

    private static final String VALUE = "AreaCut_Value";
    private final Data data;

    DetailsValue(Data data) {
        this.data = data;
    }

    double getValue(String assortment, String type, String number) throws SQLException {
        PreparedStatement preparedStatement = data.getPreparedStatement(SQL_QUERY_VALUE);
        // передача значений входных параметров
        preparedStatement.setString(1, assortment);
        preparedStatement.setString(2, type);
        preparedStatement.setString(3, number);
        // регистрация возвращаемого параметра
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.getDouble(VALUE);
    }
}
