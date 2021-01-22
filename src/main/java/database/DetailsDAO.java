package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO parameters & values of details
 */
class DetailsDAO {

    private static final String ASSORTMENT_QUERY = "ProfileName";
    private static final String TYPE_QUERY = "ProfileTypeName";
    private static final String NUMBER_QUERY = "ProfileNumberName";
    private static final String VALUE_QUERY = "AreaCut_Value";

    private static final String VALUE_SQL_QUERY = "select AreaCut_Value from "
            + "Profiles, ProfileTypes, ProfileNumbers, NumberValues "
            + "where Profiles.Profile_ID = ProfileTypes.Profile_ID and "
            + "ProfileTypes.ProfileType_ID = ProfileNumbers.ProfileType_ID and "
            + "ProfileNumbers.ProfileNumber_ID = NumberValues.Number_ID and "
            + "Profiles.ProfileName = ? and "
            + "ProfileTypes.ProfileTypeName = ? and "
            + "ProfileNumbers.ProfileNumberName = ?";

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

    DetailsDAO(Connector connector) {
        this.executor = new Executor();
        executor.addConnection(connector);
    }

    /**
     * Create all assortment list from Database for combo-box
     * assortment application menu
     * @return List of assortments detail's menu
     * @throws SQLException
     */
    List<String> createAssortmentList() throws SQLException {
        return executor.executorQuery(PROFILES_SQL_QUERY,
                resultSet -> create(resultSet, ASSORTMENT_QUERY));
    }

    /**
     * Create list of detail's type from selected assortment
     * for combo-box type menu
     * @param assortment selected assortment from combo-box
     * @return List of type detail's menu
     * @throws SQLException
     */
    List<String> createTypeList(String assortment) throws SQLException {
        return executor.executorQuery(TYPES_SQL_QUERY,
                resultSet -> create(resultSet, TYPE_QUERY),
                assortment);
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
        return executor.executorQuery(NUMBERS_SQL_QUERY,
                resultSet -> create(resultSet, NUMBER_QUERY),
                assortment, type);
    }

    /*
     * Created list for combo-boxes menu
     */
    private List<String> create(ResultSet resultSet, String query) throws SQLException {
        List<String> menuList = new ArrayList<>();
        while(resultSet.next()){
            String elementMenu = resultSet.getString(query);
            menuList.add(elementMenu);
        }
        return menuList;
    }

    /**
     * Get detail's value for calculation mass
     * @param assortment assortment detail's name
     * @param type type detail's name
     * @param number number of profile detail's name
     * @return detail's value
     * @throws SQLException
     */
    double getValue(String assortment, String type, String number) throws SQLException {
        return executor.executorQuery(VALUE_SQL_QUERY,
                resultSet -> resultSet.getDouble(VALUE_QUERY),
                assortment, type, number);
    }

    void connectionClose() {
        executor.connectionClose();
    }
}
