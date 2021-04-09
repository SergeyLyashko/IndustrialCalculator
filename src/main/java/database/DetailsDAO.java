package database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import view.DataReceiver;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * DAO parameters & values of details
 */
@Service("dataReceiver")
public class DetailsDAO implements DataReceiver {

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

    private DatabaseExecutor databaseExecutor;
    private DatabaseConnector databaseConnector;

    @Autowired
    public void setDatabaseConnector(DatabaseConnector databaseConnector){
        this.databaseConnector = databaseConnector;
    }

    @Autowired
    public void setDatabaseExecutor(DatabaseExecutor databaseExecutor){
        this.databaseExecutor = databaseExecutor;
    }

    @PostConstruct
    private void afterPropertiesSet() {
        databaseExecutor.addConnection(databaseConnector);
    }

    /**
     * Create all assortment list from Database for combo-box
     * assortment application menu
     * @return List of assortments detail's menu
     * @throws SQLException
     */
    @Override
    public List<String> receiveAssortmentMenu() throws SQLException {
        return databaseExecutor.executorQuery(PROFILES_SQL_QUERY,
                resultSet -> create(resultSet, ASSORTMENT_QUERY));
    }

    /**
     * Create list of detail's type from selected assortment
     * for combo-box type menu
     * @param assortment selected assortment from combo-box
     * @return List of type detail's menu
     * @throws SQLException
     */
    @Override
    public List<String> receiveTypeMenu(String assortment) throws SQLException {
        return databaseExecutor.executorQuery(TYPES_SQL_QUERY,
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
    @Override
    public List<String> receiveNumberMenu(String assortment, String type) throws SQLException {
        return databaseExecutor.executorQuery(NUMBERS_SQL_QUERY,
                resultSet -> create(resultSet, NUMBER_QUERY),
                assortment, type);
    }

    /*
     * Created list for combo-boxes menu
     */
    private List<String> create(ResultSet resultSet, String query) throws SQLException {
        List<String> menuList = new LinkedList<>();
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
    @Override
    public double receiveValue(String assortment, String type, String number) throws SQLException {
        return databaseExecutor.executorQuery(VALUE_SQL_QUERY,
                resultSet -> resultSet.getDouble(VALUE_QUERY),
                assortment, type, number);
    }

    @PreDestroy
    private void winClose() {
        databaseExecutor.connectionClose();
    }
}
