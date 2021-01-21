package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created list of parameters of details for menu combo-boxes
 */
class DetailParametersListCreator {

    private static final String ASSORTMENT_QUERY = "ProfileName";
    private static final String TYPE_QUERY = "ProfileTypeName";
    private static final String NUMBER_QUERY = "ProfileNumberName";

    private static final String PROFILES_SQL_QUERY =
            "select ProfileName from Profiles";

    private static final String TYPES_SQL_QUERY =
            "select ProfileTypeName from Profiles, ProfileTypes "
                    + "where Profiles.Profile_ID = ProfileTypes.Profile_ID and "
                    + "Profiles.ProfileName = ?";

    private static final String NUMBERS_SQL_QUERY =
            "select ProfileNumberName from "
                    + "Profiles, ProfileTypes, ProfileNumbers "
                    + "where Profiles.Profile_ID = ProfileTypes.Profile_ID and "
                    + "ProfileTypes.ProfileType_ID = ProfileNumbers.ProfileType_ID and "
                    + "Profiles.ProfileName = ? and "
                    + "ProfileTypes.ProfileTypeName = ?";

    private final Executor executor;

    DetailParametersListCreator(Executor executor) {
        this.executor = executor;
    }

    /**
     * Create all assortment list from Database for combo-box
     * assortment application menu
     * @return List of assortments detail's menu
     * @throws SQLException
     */
    List<String> createAssortmentList() throws SQLException {
        PreparedStatement preparedStatement = executor.getPreparedStatement(PROFILES_SQL_QUERY);
        return createList(preparedStatement, ASSORTMENT_QUERY);
    }

    /**
     * Create list of detail's type from selected assortment
     * for combo-box type menu
     * @param assortment selected assortment from combo-box
     * @return List of type detail's menu
     * @throws SQLException
     */
    List<String> createTypeList(String assortment) throws SQLException {
        PreparedStatement preparedStatement = executor.getPreparedStatement(TYPES_SQL_QUERY);
        executor.initPreparedStatement(preparedStatement, 1, assortment);
        return createList(preparedStatement, TYPE_QUERY);
    }

    /**
     * Create list of detail's numbers of detail's profiles
     * from selected detail's assortment & type
     * @param assortment selected detail's assortment from combo-box
     * @param type selected detail's type from combo-box
     * @return detail's number of detail's profile
     * @throws SQLException
     */
    List<String> createNumberList(String assortment, String type) throws SQLException {
        PreparedStatement preparedStatement = executor.getPreparedStatement(NUMBERS_SQL_QUERY);
        executor.initPreparedStatement(preparedStatement, 1, assortment);
        executor.initPreparedStatement(preparedStatement, 2, type);
        return createList(preparedStatement, NUMBER_QUERY);
    }

    /*
     * Created list for combo-boxes menu
     */
    private List<String> createList(PreparedStatement preparedStatement, String query) throws SQLException {
        List<String> menuList = new ArrayList<>();
        ResultSet resultSet = this.executor.getResultSet(preparedStatement);
        while(resultSet.next()){
            String elementMenu = resultSet.getString(query);
            menuList.add(elementMenu);
        }
        return menuList;
    }
}
